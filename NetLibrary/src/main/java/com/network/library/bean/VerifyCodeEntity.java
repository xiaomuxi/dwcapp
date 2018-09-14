package com.network.library.bean;

import java.util.List;

public class VerifyCodeEntity extends BaseEntity<List<VerifyCodeEntity.Data>> {
    public static class Data {

        /**
         * VerificationCode : 803200
         */

        private String VerificationCode;

        public String getVerificationCode() {
            return VerificationCode;
        }

        public void setVerificationCode(String VerificationCode) {
            this.VerificationCode = VerificationCode;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "VerificationCode='" + VerificationCode + '\'' +
                    '}';
        }
    }
}
