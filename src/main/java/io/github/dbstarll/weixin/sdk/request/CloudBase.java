package io.github.dbstarll.weixin.sdk.request;

import java.io.Serializable;

public final class CloudBase implements Serializable {
    /**
     * 云开发环境.
     */
    private final String env;

    /**
     * 静态网站自定义域名，不填则使用默认域名.
     */
    private String domain;

    /**
     * 云开发静态网站 H5 页面路径，不可携带 query.
     */
    private String path;

    /**
     * 云开发静态网站 H5 页面 query 参数，最大 1024 个字符，只支持数字，大小写英文以及部分特殊字符：`!#$&nbsp;'()*+,/:;=?@-._~%``.
     */
    private String query;

    /**
     * 第三方批量代云开发时必填，表示创建该 env 的 appid （小程序/第三方平台）.
     */
    private String resourceAppid;

    /**
     * 构建CloudBase.
     *
     * @param env env
     */
    public CloudBase(final String env) {
        this.env = env;
    }

    /**
     * get env.
     *
     * @return env
     */
    public String getEnv() {
        return env;
    }

    /**
     * get domain.
     *
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * set domain.
     *
     * @param domain domain
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * get path.
     *
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * set path.
     *
     * @param path path
     */
    public void setPath(final String path) {
        this.path = path;
    }

    /**
     * get query.
     *
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * set query.
     *
     * @param query query
     */
    public void setQuery(final String query) {
        this.query = query;
    }

    /**
     * get resourceAppid.
     *
     * @return resourceAppid
     */
    public String getResourceAppid() {
        return resourceAppid;
    }

    /**
     * set resourceAppid.
     *
     * @param resourceAppid resourceAppid
     */
    public void setResourceAppid(final String resourceAppid) {
        this.resourceAppid = resourceAppid;
    }
}
