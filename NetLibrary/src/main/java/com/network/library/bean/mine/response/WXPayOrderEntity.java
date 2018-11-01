package com.network.library.bean.mine.response;

import com.google.gson.annotations.SerializedName;
import com.network.library.bean.BaseEntity;

import java.util.List;

public class WXPayOrderEntity extends BaseEntity<List<WXPayOrderEntity.Data>> {
    public static class Data {

        /**
         * appid : wxc1ec297924f43820
         * partnerid : 1517006681
         * prepayid : wx31170503378919a2e3b922241586898921
         * noncestr : 7872Pi2X9LDHDGTJ7271
         * timestamp : 1540976704
         * package : Sign=WXPay
         * sign : 3B6C9C0C6107A0031B5EBEFE9304254A
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        @SerializedName("package")
        private String packageX;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
