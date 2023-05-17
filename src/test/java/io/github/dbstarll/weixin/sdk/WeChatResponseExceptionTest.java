package io.github.dbstarll.weixin.sdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class WeChatResponseExceptionTest {
    @Test
    @SuppressWarnings("ThrowableNotThrown")
    void blankErrMsg() {
        final Exception e = assertThrowsExactly(IllegalArgumentException.class, () -> new WeChatResponseException(123, ""));
        assertEquals("errMsg not set", e.getMessage());
    }

    @Test
    @SuppressWarnings("ThrowableNotThrown")
    void nullErrMsg() {
        final Exception e = assertThrowsExactly(NullPointerException.class, () -> new WeChatResponseException(123, null));
        assertEquals("errMsg not set", e.getMessage());
    }

    @Test
    void noRid() {
        final WeChatResponseException e = new WeChatResponseException(123, "no rid");
        assertEquals("123: no rid", e.getMessage());
        assertEquals(123, e.getStatusCode());
        assertEquals("no rid", e.getReasonPhrase());
        assertNull(e.getRid());
    }

    @Test
    void rid() {
        final WeChatResponseException e = new WeChatResponseException(123, "with rid: rid: myRid");
        assertEquals("123: with rid: rid: myRid", e.getMessage());
        assertEquals(123, e.getStatusCode());
        assertEquals("with rid:", e.getReasonPhrase());
        assertEquals("myRid", e.getRid());
    }

    @Test
    void rid2() {
        final WeChatResponseException e = new WeChatResponseException(123, "with rid, rid: myRid");
        assertEquals("123: with rid, rid: myRid", e.getMessage());
        assertEquals(123, e.getStatusCode());
        assertEquals("with rid", e.getReasonPhrase());
        assertEquals("myRid", e.getRid());
    }
}