package io.github.dbstarll.weixin.sdk;

public interface SecretHolder {
    /**
     * 根据AppId获得Secret.
     *
     * @param appId appId
     * @return appId对应的secret
     */
    String getSecret(String appId);
}
