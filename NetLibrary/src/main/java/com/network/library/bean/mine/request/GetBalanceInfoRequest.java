package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetBalanceInfoRequest extends BaseRequest<GetBalanceInfoRequest.Query, Object>{
    public static class Query {

        /**
         * ApiId : HC010101
         */

        private String ApiId;
        private String id;
        private String userid;
        private String DEVICEID;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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
                    ", id='" + id + '\'' +
                    ", userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    '}';
        }
    }
}
