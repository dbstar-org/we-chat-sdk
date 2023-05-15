package io.github.dbstarll.weixin.sdk;

import io.github.dbstarll.utils.net.api.ApiResponseException;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.HttpResponseException;

public class WeChatResponseException extends ApiResponseException {
    private static final String RID_SPLIT_TOKEN = " rid: ";

    private final String rid;

    /**
     * 构建WeChatResponseException.
     *
     * @param errCode 错误码
     * @param errMsg  错误信息
     */
    public WeChatResponseException(final int errCode, final String errMsg) {
        super(new HttpResponseException(errCode, StringUtils.substringBefore(errMsg, RID_SPLIT_TOKEN)));
        this.rid = StringUtils.substringAfter(errMsg, RID_SPLIT_TOKEN);
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
