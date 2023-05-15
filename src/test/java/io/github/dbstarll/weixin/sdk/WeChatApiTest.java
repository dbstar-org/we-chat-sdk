package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dbstarll.utils.http.client.HttpClientFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class WeChatApiTest {
    private static final String TOKEN_KEY = "WE_CHAT_KEY";

    private String getWeCharKey() {
        final String keyFromProperty = System.getProperty(TOKEN_KEY);
        if (StringUtils.isNotBlank(keyFromProperty)) {
            return keyFromProperty;
        }

        final String opts = System.getenv("MAVEN_OPTS");
        if (StringUtils.isNotBlank(opts)) {
            for (String opt : StringUtils.split(opts)) {
                if (opt.startsWith("-D" + TOKEN_KEY + "=")) {
                    return opt.substring(3 + TOKEN_KEY.length());
                }
            }
        }

        return null;
    }

    private String testAppId;
    private SecretHolder secretHolder;

    @BeforeEach
    void setUp() {
        final String key = getWeCharKey();
        if (StringUtils.isNotBlank(key)) {
            final int index = key.indexOf("=");
            if (index > 0) {
                final String appId = key.substring(0, index);
                final String secret = key.substring(index + 1);
                this.testAppId = appId;
                this.secretHolder = id -> appId.equals(id) ? secret : null;
                return;
            }
        }
        throw new IllegalArgumentException(TOKEN_KEY + " not set");
    }

    @AfterEach
    void tearDown() {
        this.secretHolder = null;
        this.testAppId = null;
    }

    private void useClient(final ThrowingConsumer<HttpClient> consumer) throws Throwable {
        try (CloseableHttpClient client = new HttpClientFactory().build()) {
            consumer.accept(client);
        }
    }

    private void useApi(final ThrowingConsumer<WeChatApi> consumer, final SecretHolder secretHolder) throws Throwable {
        useClient(httpClient -> consumer.accept(new WeChatApi(httpClient, new ObjectMapper(), secretHolder)));
    }

    @Test
    void nullSecretHolder() {
        final Exception e = assertThrowsExactly(NullPointerException.class,
                () -> useApi(api -> {
                }, null));
        assertEquals("secretHolder not set", e.getMessage());
    }

    @Test
    void accessToken() throws Throwable {
        useApi(api -> {
            final AccessTokenResponse response = api.accessToken(testAppId);
            assertNotNull(response.getAccessToken());
            assertEquals(136, response.getAccessToken().length());
            assertEquals(7200, response.getExpiresIn());
        }, secretHolder);
    }

    @Test
    void accessTokenInvalidAppId() throws Throwable {
        useApi(api -> {
            final WeChatResponseException e = assertThrowsExactly(WeChatResponseException.class, () -> api.accessToken("appId"));
            assertEquals(40013, e.getStatusCode());
            assertEquals("invalid appid", e.getReasonPhrase());
            assertNotNull(e.getRid());
        }, appId -> "secret");
    }
}