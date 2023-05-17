package io.github.dbstarll.weixin.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.StringJoiner;

@JsonIgnoreProperties({"errcode", "errmsg"})
public final class UserPhoneResponse implements Serializable {
    private PhoneInfo phoneInfo;

    /**
     * 获得用户手机号信息.
     *
     * @return 用户手机号信息
     */
    public PhoneInfo getPhoneInfo() {
        return phoneInfo;
    }

    void setPhoneInfo(final PhoneInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserPhoneResponse.class.getSimpleName() + "[", "]")
                .add("phoneInfo=" + getPhoneInfo())
                .toString();
    }

    public static final class PhoneInfo implements Serializable {
        @JsonProperty("phoneNumber")
        private String phoneNumber;
        @JsonProperty("purePhoneNumber")
        private String purePhoneNumber;
        @JsonProperty("countryCode")
        private String countryCode;
        private Watermark watermark;

        /**
         * 获得用户绑定的手机号（国外手机号会有区号）.
         *
         * @return 用户绑定的手机号
         */
        public String getPhoneNumber() {
            return phoneNumber;
        }

        void setPhoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         * 获得没有区号的手机号.
         *
         * @return 没有区号的手机号
         */
        public String getPurePhoneNumber() {
            return purePhoneNumber;
        }

        void setPurePhoneNumber(final String purePhoneNumber) {
            this.purePhoneNumber = purePhoneNumber;
        }

        /**
         * 获得区号.
         *
         * @return 区号
         */
        public String getCountryCode() {
            return countryCode;
        }

        void setCountryCode(final String countryCode) {
            this.countryCode = countryCode;
        }

        /**
         * 获得数据水印.
         *
         * @return 数据水印
         */
        public Watermark getWatermark() {
            return watermark;
        }

        void setWatermark(final Watermark watermark) {
            this.watermark = watermark;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PhoneInfo.class.getSimpleName() + "[", "]")
                    .add("phoneNumber='" + getPhoneNumber() + "'")
                    .add("purePhoneNumber='" + getPurePhoneNumber() + "'")
                    .add("countryCode='" + getCountryCode() + "'")
                    .add("watermark=" + getWatermark())
                    .toString();
        }
    }

    public static final class Watermark implements Serializable {
        private long timestamp;
        private String appid;

        /**
         * 获得用户获取手机号操作的时间戳.
         *
         * @return 用户获取手机号操作的时间戳
         */
        public long getTimestamp() {
            return timestamp;
        }

        void setTimestamp(final long timestamp) {
            this.timestamp = timestamp;
        }

        /**
         * 获得小程序appid.
         *
         * @return 小程序appid
         */
        public String getAppid() {
            return appid;
        }

        void setAppid(final String appid) {
            this.appid = appid;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Watermark.class.getSimpleName() + "[", "]")
                    .add("timestamp=" + getTimestamp())
                    .add("appid='" + getAppid() + "'")
                    .toString();
        }
    }
}
