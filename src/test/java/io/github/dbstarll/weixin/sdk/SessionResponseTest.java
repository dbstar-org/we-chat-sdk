package io.github.dbstarll.weixin.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SessionResponseTest {
    @Test
    void testToString() {
        final SessionResponse response = new SessionResponse();
        assertEquals("SessionResponse[openid='null', sessionKey='null', unionid='null']", response.toString());
    }

    @Test
    void getSessionKey() {
        final SessionResponse response = new SessionResponse();
        assertNull(response.getSessionKey());
        response.setSessionKey("setSessionKey");
        assertEquals("setSessionKey", response.getSessionKey());
    }

    @Test
    void getUnionid() {
        final SessionResponse response = new SessionResponse();
        assertNull(response.getUnionid());
        response.setUnionid("setUnionid");
        assertEquals("setUnionid", response.getUnionid());
    }

    @Test
    void getOpenid() {
        final SessionResponse response = new SessionResponse();
        assertNull(response.getOpenid());
        response.setOpenid("setOpenid");
        assertEquals("setOpenid", response.getOpenid());
    }
}