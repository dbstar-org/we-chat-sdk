package io.github.dbstarll.weixin.sdk.request;

import java.io.Serializable;

public final class CodeRequest implements Serializable {
    private final String code;

    /**
     * 构建CodeRequest.
     *
     * @param code code
     */
    public CodeRequest(final String code) {
        this.code = code;
    }

    /**
     * get code.
     *
     * @return code
     */
    public String getCode() {
        return code;
    }
}
