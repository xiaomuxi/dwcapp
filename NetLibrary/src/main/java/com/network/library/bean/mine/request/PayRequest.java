package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class PayRequest extends BaseRequest<PayRequest.Query, Object> {
    public static class Query {

        /**
         * ApiId : HC020620
         * customerId : 13203861885
         * Type : 2
         * Amount : 200
         */

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String customerId;
        private String Type;
        private String Amount;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(String DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", Type='" + Type + '\'' +
                    ", Amount='" + Amount + '\'' +
                    '}';
        }
    }
}
