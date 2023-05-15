package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dbstarll.utils.http.client.HttpClientFactory;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class WeChatApiTest {
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
            final WeChatResponseException e = assertThrowsExactly(WeChatResponseException.class, () -> api.accessToken("appId"));
            assertEquals(40013, e.getStatusCode());
            assertEquals("invalid appid", e.getReasonPhrase());
            assertNotNull(e.getRid());
        }, appId -> "secret");
    }
}