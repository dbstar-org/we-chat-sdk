package io.github.dbstarll.weixin.sdk.response;

import java.io.Serializable;
import java.util.StringJoiner;

public final class GenerateUrlLinkResponse implements Serializable {
    /**
     * 生成的小程序 URL Link.
     */
    private String urlLink;

    /**
     * get urlLink.
     *
     * @return urlLink
     */
    public String getUrlLink() {
        return urlLink;
    }

    /**
     * set urlLink.
     *
     * @param urlLink urlLink
     */
    public void setUrlLink(final String urlLink) {
        this.urlLink = urlLink;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GenerateUrlLinkResponse.class.getSimpleName() + "[", "]")
                .add("urlLink='" + urlLink + "'")
                .toString();
    }
}
