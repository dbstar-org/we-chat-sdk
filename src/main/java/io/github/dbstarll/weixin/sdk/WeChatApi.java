package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.dbstarll.utils.http.client.request.RelativeUriResolver;
import io.github.dbstarll.utils.json.jackson.JsonApiClient;
import io.github.dbstarll.utils.net.api.ApiException;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;

import java.io.IOException;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class WeChatApi extends JsonApiClient {
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
        return mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Override
    protected <T> T postProcessing(final ClassicHttpRequest request, final T executeResult) throws ApiException {
        final T superResult = super.postProcessing(request, executeResult);
        if (superResult instanceof ObjectNode) {
            final ObjectNode node = (ObjectNode) superResult;
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
        return execute(auth(post("/sns/jscode2session")
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
        return execute(auth(get("/cgi-bin/token")
                .addParameter("grant_type", "client_credential"), appId), AccessTokenResponse.class);
    }

    private ClassicHttpRequest auth(final ClassicRequestBuilder builder, final String appId) {
        return builder
                .addParameter("appid", notBlank(appId, "appId not set"))
                .addParameter("secret", notBlank(secretHolder.getSecret(appId), "secret not found for {}", appId))
                .build();
    }
}
