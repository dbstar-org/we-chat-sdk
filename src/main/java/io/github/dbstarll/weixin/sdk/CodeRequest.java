package io.github.dbstarll.weixin.sdk;

import java.io.Serializable;
import java.util.StringJoiner;

final class CodeRequest implements Serializable {
    private final String code;

    CodeRequest(final String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CodeRequest.class.getSimpleName() + "[", "]")
                .add("code='" + getCode() + "'")
                .toString();
    }
}
