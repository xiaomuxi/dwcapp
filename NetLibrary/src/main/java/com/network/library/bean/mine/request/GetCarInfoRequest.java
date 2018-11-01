package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetCarInfoRequest extends BaseRequest<GetCarInfoRequest.Query, Object>{
    public static class Query {

        /**
         * ApiId : HC010108
         */

        private String ApiId;
        private String customerId;
        private String userid;
        private String DEVICEID;

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

        @Override
        public String toString() {
            return "Query{" +
                    "ApiId='" + ApiId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    '}';
        }
    }
}
