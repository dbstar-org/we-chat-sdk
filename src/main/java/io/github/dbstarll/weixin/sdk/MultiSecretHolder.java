package io.github.dbstarll.weixin.sdk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MultiSecretHolder implements SecretHolder {
    private final ConcurrentMap<String, String> secretMap;

    /**
     * 构建一个空的MultiSecretHolder.
     */
    public MultiSecretHolder() {
        this.secretMap = new ConcurrentHashMap<>();
    }

    /**
     * 从外部数据来构建MultiSecretHolder.
     *
     * @param secrets 外部数据
     */
    public MultiSecretHolder(final Map<String, String> secrets) {
        this();
        addSecrets(secrets);
    }

    protected final void addSecrets(final Map<String, String> secrets) {
        if (secrets != null) {
            secrets.forEach(this::addSecret);
        }
    }

    protected final void addSecret(final String appId, final String secret) {
        secretMap.putIfAbsent(appId, secret);
    }

    @Override
    public final String getSecret(final String appId) {
        return secretMap.get(appId);
    }
}
