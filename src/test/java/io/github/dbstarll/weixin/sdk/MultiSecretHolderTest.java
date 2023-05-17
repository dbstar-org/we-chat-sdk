package io.github.dbstarll.weixin.sdk;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MultiSecretHolderTest {
    @Test
    void createDefault() {
        assertNull(new MultiSecretHolder().getSecret("appId"));
    }

    @Test
    void createNullMap() {
        assertNull(new MultiSecretHolder(null).getSecret("appId"));
    }

    @Test
    void createSingleMap() {
        final SecretHolder holder = new MultiSecretHolder(Collections.singletonMap("app1", "sec1"));
        assertNull(holder.getSecret("appId"));
        assertEquals("sec1", holder.getSecret("app1"));
    }

    @Test
    void createMultiMap() {
        final Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.put("app" + i, "sec" + i);
        }
        final SecretHolder holder = new MultiSecretHolder(map);
        assertNull(holder.getSecret("appId"));
        for (int i = 0; i < 10; i++) {
            assertEquals("sec" + i, holder.getSecret("app" + i));
        }
    }
}