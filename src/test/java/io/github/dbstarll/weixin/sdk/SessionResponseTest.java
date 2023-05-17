package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionResponseTest {
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @AfterEach
    void tearDown() {
        mapper = null;
    }

    @Test
    void readValue() throws JsonProcessingException {
        final String json = "{\"session_key\":\"LbQKDE/KXI47DqZQtaQZiQ==\",\"openid\":\"oM_r64gOfnupu5c1kPz1CDcD0WyA\",\"unionid\":\"wxa9b59367ce000000\"}";
        final SessionResponse response = mapper.readValue(json, SessionResponse.class);
        assertEquals(123, response.toString().length());
        assertEquals("oM_r64gOfnupu5c1kPz1CDcD0WyA", response.getOpenid());
        assertEquals("LbQKDE/KXI47DqZQtaQZiQ==", response.getSessionKey());
        assertEquals("wxa9b59367ce000000", response.getUnionid());
    }
}