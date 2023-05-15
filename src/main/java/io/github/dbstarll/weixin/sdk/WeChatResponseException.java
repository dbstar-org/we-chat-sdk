package io.github.dbstarll.weixin.sdk;

import io.github.dbstarll.utils.net.api.ApiProtocolException;
import org.apache.commons.lang3.StringUtils;

public class WeChatResponseException extends ApiProtocolException {
    private static final String RID_SPLIT_TOKEN = " rid: ";

    private final int statusCode;
    private final String reasonPhrase;
    private final String rid;

    /**
     * 构建WeChatResponseException.
     *
     * @param errCode 错误码
     * @param errMsg  错误信息
     */
    public WeChatResponseException(final int errCode, final String errMsg) {
        super(String.format("%d: %s", errCode, errMsg), null);
        this.statusCode = errCode;
        this.reasonPhrase = StringUtils.substringBefore(errMsg, RID_SPLIT_TOKEN);
        this.rid = StringUtils.substringAfter(errMsg, RID_SPLIT_TOKEN);
    }

    /**
     * 获得异常的状态码.
     *
     * @return 状态码
     */
    public final int getStatusCode() {
        return this.statusCode;
    }

    /**
     * 获得异常的原因.
     *
     * @return 异常原因
     */
    public final String getReasonPhrase() {
        return this.reasonPhrase;
    }

    /**
     * 获得rid信息.
     *
     * @return rid信息
     */
    public final String getRid() {
        return rid;
    }
}
