package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.dbstarll.utils.http.client.request.RelativeUriResolver;
import io.github.dbstarll.utils.json.jackson.JsonApiClient;
import io.github.dbstarll.utils.net.api.ApiException;
import io.github.dbstarll.weixin.sdk.request.CodeRequest;
import io.github.dbstarll.weixin.sdk.request.GenerateUrlLinkRequest;
import io.github.dbstarll.weixin.sdk.request.UnlimitedQrCodeRequest;
import io.github.dbstarll.weixin.sdk.response.GenerateUrlLinkResponse;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public final class WeChatApi extends JsonApiClient {
    private final SecretHolder secretHolder;

    /**
     * 构造WeChatApi.
     *
     * @param httpClient   httpClient
     * @param mapper       mapper
     * @param secretHolder SecretHolder
     */
    public WeChatApi(final HttpClient httpClient, final ObjectMapper mapper, final SecretHolder secretHolder) {
        super(httpClient, true, optimize(mapper.copy()));
        this.secretHolder = notNull(secretHolder, "secretHolder not set");
        setUriResolver(new RelativeUriResolver("https://api.weixin.qq.com"));
    }

    private static ObjectMapper optimize(final ObjectMapper mapper) {
        return mapper
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .setSerializationInclusion(Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    protected <T> T postProcessing(final ClassicHttpRequest request, final T executeResult) throws ApiException {
        final T superResult = super.postProcessing(request, executeResult);
        Object copyResult = superResult;
        if (copyResult instanceof byte[]) {
            try {
                copyResult = mapper.readTree(new String((byte[]) copyResult, StandardCharsets.UTF_8));
            } catch (JsonProcessingException e) {
                // 不是json字符串
            }
        }
        if (copyResult instanceof ObjectNode) {
            final ObjectNode node = (ObjectNode) copyResult;
            final int errcode = node.path("errcode").asInt(0);
            if (errcode != 0) {
                throw new WeChatResponseException(errcode, node.path("errmsg").asText());
            }
        }
        return superResult;
    }

    /**
     * 登录凭证校验.
     *
     * @param appId 小程序 appId
     * @param code  登录时获取的 code
     * @return SessionResponse
     * @throws IOException  in case of a problem or the connection was aborted
     * @throws ApiException in case of an api error
     */
    public SessionResponse session(final String appId, final String code) throws IOException, ApiException {
        return execute(authByAppId(post("/sns/jscode2session")
                .addParameter("grant_type", "authorization_code")
                .addParameter("js_code", notBlank(code, "code not set")), appId), SessionResponse.class);
    }

    /**
     * 获取小程序全局唯一后台接口调用凭据，开发者需要进行妥善保存.
     *
     * @param appId 小程序唯一凭证，即 AppID，可在「微信公众平台 - 设置 - 开发设置」页中获得
     * @return AccessTokenResponse
     * @throws IOException  in case of a problem or the connection was aborted
     * @throws ApiException in case of an api error
     */
    public AccessTokenResponse accessToken(final String appId) throws IOException, ApiException {
        return execute(authByAppId(get("/cgi-bin/token")
                .addParameter("grant_type", "client_credential"), appId), AccessTokenResponse.class);
    }

    /**
     * 该接口用于将code换取用户手机号。 说明，每个code只能使用一次，code的有效期为5min.
     *
     * @param accessToken 接口调用凭证
     * @param code        手机号获取凭证
     * @return UserPhoneResponse
     * @throws IOException  in case of a problem or the connection was aborted
     * @throws ApiException in case of an api error
     */
    public UserPhoneResponse phone(final String accessToken, final String code) throws IOException, ApiException {
        return execute(authByAccessToken(post("/wxa/business/getuserphonenumber")
                        .setEntity(jsonEntity(new CodeRequest(notBlank(code, "code not set")))),
                accessToken), UserPhoneResponse.class);
    }

    /**
     * 该接口用于获取小程序码，适用于需要的码数量极多的业务场景。通过该接口生成的小程序码，永久有效，数量暂无限制.
     *
     * @param accessToken 接口调用凭证
     * @param request     请求参数
     * @return 图片二进制
     * @throws IOException  in case of a problem or the connection was aborted
     * @throws ApiException in case of an api error
     */
    public byte[] unlimitedQrCode(final String accessToken, final UnlimitedQrCodeRequest request)
            throws IOException, ApiException {
        return execute(authByAccessToken(post("/wxa/getwxacodeunlimit")
                        .setEntity(jsonEntity(notNull(request, "request not set"))),
                accessToken), byte[].class);
    }

    /**
     * 获取小程序 URL Link，适用于短信、邮件、网页、微信内等拉起小程序的业务场景。目前仅针对国内非个人主体的小程序开放.
     *
     * @param accessToken 接口调用凭证
     * @param request     请求参数
     * @return 生成的小程序 URL Link
     * @throws IOException  in case of a problem or the connection was aborted
     * @throws ApiException in case of an api error
     */
    public String generateUrlLink(final String accessToken, final GenerateUrlLinkRequest request)
            throws IOException, ApiException {
        return execute(authByAccessToken(post("/wxa/generate_urllink")
                        .setEntity(jsonEntity(notNull(request, "request not set"))),
                accessToken), GenerateUrlLinkResponse.class).getUrlLink();
    }

    private ClassicHttpRequest authByAppId(final ClassicRequestBuilder builder, final String appId) {
        return builder
                .addParameter("appid", notBlank(appId, "appId not set"))
                .addParameter("secret", notBlank(secretHolder.getSecret(appId), "secret not found for %s", appId))
                .build();
    }

    private ClassicHttpRequest authByAccessToken(final ClassicRequestBuilder builder, final String accessToken) {
        return builder
                .addParameter("access_token", notBlank(accessToken, "accessToken not set"))
                .build();
    }
}
