package io.github.dbstarll.weixin.sdk;

import java.io.Serializable;
import java.util.StringJoiner;

public final class AccessTokenResponse implements Serializable {
    private String accessToken;
    private int expiresIn;

    /**
     * 获得获取到的凭证.
     *
     * @return 获取到的凭证
     */
    public String getAccessToken() {
        return accessToken;
    }

    void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获得凭证有效时间，单位：秒.
     *
     * @return 凭证有效时间
     */
    public int getExpiresIn() {
        return expiresIn;
    }

    void setExpiresIn(final int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AccessTokenResponse.class.getSimpleName() + "[", "]")
                .add("expiresIn=" + getExpiresIn())
                .add("accessToken='" + getAccessToken() + "'")
                .toString();
    }
}
