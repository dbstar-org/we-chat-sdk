package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.StringJoiner;

@JsonIgnoreProperties({"errcode", "errmsg"})
public final class SessionResponse implements Serializable {
    private String sessionKey;
    private String unionid;
    private String openid;

    /**
     * 获得会话密钥.
     *
     * @return 会话密钥
     */
    public String getSessionKey() {
        return sessionKey;
    }

    void setSessionKey(final String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * 获得用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回.
     *
     * @return 用户在开放平台的唯一标识符
     */
    public String getUnionid() {
        return unionid;
    }

    void setUnionid(final String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获得用户唯一标识.
     *
     * @return 用户唯一标识
     */
    public String getOpenid() {
        return openid;
    }

    void setOpenid(final String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SessionResponse.class.getSimpleName() + "[", "]")
                .add("openid='" + getOpenid() + "'")
                .add("sessionKey='" + getSessionKey() + "'")
                .add("unionid='" + getUnionid() + "'")
                .toString();
    }
}
