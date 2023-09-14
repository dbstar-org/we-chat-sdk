package io.github.dbstarll.weixin.sdk.request;

import java.io.Serializable;

public final class Color implements Serializable {
    private final int r;
    private final int g;
    private final int b;

    private Color(final int r, final int g, final int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * 使用rgb三原色来构建Color.
     *
     * @param r r
     * @param g g
     * @param b b
     * @return Color
     */
    public static Color rgb(final int r, final int g, final int b) {
        return new Color(r, g, b);
    }

    /**
     * 获得r.
     *
     * @return r
     */
    public int getR() {
        return r;
    }

    /**
     * 获得g.
     *
     * @return g
     */
    public int getG() {
        return g;
    }

    /**
     * 获得b.
     *
     * @return b
     */
    public int getB() {
        return b;
    }
}
