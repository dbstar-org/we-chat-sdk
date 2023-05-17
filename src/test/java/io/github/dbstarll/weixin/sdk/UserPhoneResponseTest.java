package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.github.dbstarll.weixin.sdk.UserPhoneResponse.PhoneInfo;
import io.github.dbstarll.weixin.sdk.UserPhoneResponse.Watermark;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserPhoneResponseTest {
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
        final String json = "{\"errcode\":0,\"errmsg\":\"ok\",\"phone_info\":{\"phoneNumber\":\"8618918600000\",\"purePhoneNumber\":\"18918600000\",\"countryCode\":\"86\",\"watermark\":{\"timestamp\":1684307304,\"appid\":\"wxa9b59367ce000000\"}}}";
        final UserPhoneResponse response = mapper.readValue(json, UserPhoneResponse.class);
        assertEquals(187, response.toString().length());
        assertNotNull(response.getPhoneInfo());
        final PhoneInfo phoneInfo = response.getPhoneInfo();
        assertEquals("8618918600000", phoneInfo.getPhoneNumber());
        assertEquals("18918600000", phoneInfo.getPurePhoneNumber());
        assertEquals("86", phoneInfo.getCountryCode());
        assertNotNull(phoneInfo.getWatermark());
        final Watermark watermark = phoneInfo.getWatermark();
        assertEquals("wxa9b59367ce000000", watermark.getAppid());
        assertEquals(1684307304, watermark.getTimestamp());
    }
}