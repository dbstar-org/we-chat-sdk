package io.github.dbstarll.weixin.sdk.request;

import java.io.Serializable;

public final class GenerateUrlLinkRequest implements Serializable {
    /**
     * 通过 URL Link 进入的小程序页面路径，必须是已经发布的小程序存在的页面，不可携带 query 。path 为空时会跳转小程序主页.
     */
    private final String path;

    /**
     * 通过 URL Link 进入小程序时的query，最大1024个字符，只支持数字，大小写英文以及部分特殊字符：!#$&nbsp;'()*+,/:;=?@-._~%.
     */
    private final String query;

    /**
     * 默认值0.小程序 URL Link 失效类型，失效时间：0，失效间隔天数：1.
     */
    private Integer expireType;

    /**
     * 到期失效的 URL Link 的失效时间，为 Unix 时间戳。生成的到期失效 URL Link 在该时间前有效。
     * 最长有效期为30天。expire_type 为 0 必填.
     */
    private Long expireTime;

    /**
     * 到期失效的URL Link的失效间隔天数。生成的到期失效URL Link在该间隔时间到达前有效。
     * 最长间隔天数为30天。expire_type 为 1 必填.
     */
    private Integer expireInterval;

    /**
     * 云开发静态网站自定义 H5 配置参数，可配置中转的云开发 H5 页面。不填默认用官方 H5 页面.
     */
    private CloudBase cloudBase;

    /**
     * 默认值"release"。要打开的小程序版本。正式版为 "release"，体验版为"trial"，开发版为"develop"，仅在微信外打开时生效.
     */
    private String envVersion;

    /**
     * 默认构造函数.
     */
    public GenerateUrlLinkRequest() {
        this(null, null);
    }

    /**
     * 指定path和query的构造函数.
     *
     * @param path  path
     * @param query query
     */
    public GenerateUrlLinkRequest(final String path, final String query) {
        this.path = path;
        this.query = query;
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
     * get query.
     *
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * get expireType.
     *
     * @return expireType
     */
    public Integer getExpireType() {
        return expireType;
    }

    /**
     * get expireTime.
     *
     * @return expireTime
     */
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * set expireTime.
     *
     * @param expireTime expireTime
     */
    public void setExpireTime(final long expireTime) {
        this.expireTime = expireTime;
        this.expireType = 0;
    }

    /**
     * get expireInterval.
     *
     * @return expireInterval
     */
    public Integer getExpireInterval() {
        return expireInterval;
    }

    /**
     * set expireInterval.
     *
     * @param expireInterval expireInterval
     */
    public void setExpireInterval(final int expireInterval) {
        this.expireInterval = expireInterval;
        this.expireType = 1;
    }

    /**
     * get cloudBase.
     *
     * @return cloudBase
     */
    public CloudBase getCloudBase() {
        return cloudBase;
    }

    /**
     * set cloudBase.
     *
     * @param cloudBase cloudBase
     */
    public void setCloudBase(final CloudBase cloudBase) {
        this.cloudBase = cloudBase;
    }

    /**
     * get envVersion.
     *
     * @return envVersion
     */
    public String getEnvVersion() {
        return envVersion;
    }

    /**
     * set envVersion.
     *
     * @param envVersion envVersion
     */
    public void setEnvVersion(final String envVersion) {
        this.envVersion = envVersion;
    }
}
