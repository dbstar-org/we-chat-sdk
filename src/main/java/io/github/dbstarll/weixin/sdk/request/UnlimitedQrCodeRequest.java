package io.github.dbstarll.weixin.sdk.request;

import java.io.Serializable;

import static org.apache.commons.lang3.Validate.notBlank;

public final class UnlimitedQrCodeRequest implements Serializable {
    /**
     * 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：<span>!#$&nbsp;'()*+,/:;=?@-._~</span>，
     * 其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）.
     */
    private final String scene;

    /**
     * 默认是主页，页面 page，例如 pages/index/index，根路径前不要填加 /，不能携带参数（参数请放在scene字段里），
     * 如果不填写这个字段，默认跳主页面。scancode_time为系统保留参数，不允许配置.
     */
    private final String page;

    /**
     * 默认是true，检查page 是否存在，为 true 时 page 必须是已经发布的小程序存在的页面（否则报错）；
     * 为 false 时允许小程序未发布或者 page 不存在， 但page 有数量上限（60000个）请勿滥用.
     */
    private final Boolean checkPath;

    /**
     * 要打开的小程序版本。正式版为 "release"，体验版为 "trial"，开发版为 "develop"。默认是正式版.
     */
    private String envVersion;

    /**
     * 默认430，二维码的宽度，单位 px，最小 280px，最大 1280px.
     */
    private Integer width;

    /**
     * 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false.
     */
    private Boolean autoColor;

    /**
     * 默认是{"r":0,"g":0,"b":0} 。auto_color 为 false 时生效，
     * 使用 rgb 设置颜色 例如 {"r":xxx,"g":xxx,"b":xxx} 十进制表示.
     */
    private Color lineColor;

    /**
     * 默认是false，是否需要透明底色，为 true 时，生成透明底色的小程序.
     */
    private Boolean isHyaline;

    /**
     * 构建UnlimitedQrCodeRequest.
     *
     * @param scene scene
     */
    public UnlimitedQrCodeRequest(final String scene) {
        this(scene, null);
    }


    /**
     * 构建UnlimitedQrCodeRequest.
     *
     * @param scene scene
     * @param page  page
     */
    public UnlimitedQrCodeRequest(final String scene, final String page) {
        this(scene, page, null);
    }

    /**
     * 构建UnlimitedQrCodeRequest.
     *
     * @param scene     scene
     * @param page      page
     * @param checkPath checkPath
     */
    public UnlimitedQrCodeRequest(final String scene, final String page, final Boolean checkPath) {
        this.scene = notBlank(scene, "scene is blank");
        this.page = page;
        this.checkPath = checkPath;
    }

    /**
     * 获得scene.
     *
     * @return scene
     */
    public String getScene() {
        return scene;
    }

    /**
     * 获得page.
     *
     * @return page
     */
    public String getPage() {
        return page;
    }

    /**
     * 获得checkPath.
     *
     * @return checkPath
     */
    public Boolean isCheckPath() {
        return checkPath;
    }

    /**
     * 获得envVersion.
     *
     * @return envVersion
     */
    public String getEnvVersion() {
        return envVersion;
    }

    /**
     * 设置envVersion.
     *
     * @param envVersion envVersion
     */
    public void setEnvVersion(final String envVersion) {
        this.envVersion = envVersion;
    }

    /**
     * 获得width.
     *
     * @return width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置width.
     *
     * @param width width
     */
    public void setWidth(final Integer width) {
        this.width = width;
    }

    /**
     * 获得autoColor.
     *
     * @return autoColor
     */
    public Boolean getAutoColor() {
        return autoColor;
    }

    /**
     * 设置autoColor.
     *
     * @param autoColor autoColor
     */
    public void setAutoColor(final Boolean autoColor) {
        this.autoColor = autoColor;
    }

    /**
     * 获得lineColor.
     *
     * @return lineColor
     */
    public Color getLineColor() {
        return lineColor;
    }

    /**
     * 设置lineColor.
     *
     * @param lineColor lineColor
     */
    public void setLineColor(final Color lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * 获得isHyaline.
     *
     * @return isHyaline
     */
    public Boolean getIsHyaline() {
        return isHyaline;
    }

    /**
     * 设置isHyaline.
     *
     * @param isHyaline isHyaline
     */
    public void setIsHyaline(final Boolean isHyaline) {
        this.isHyaline = isHyaline;
    }
}
