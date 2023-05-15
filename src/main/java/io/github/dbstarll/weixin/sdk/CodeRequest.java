package io.github.dbstarll.weixin.sdk;

import java.io.Serializable;

final class CodeRequest implements Serializable {
    private final String code;

    CodeRequest(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
