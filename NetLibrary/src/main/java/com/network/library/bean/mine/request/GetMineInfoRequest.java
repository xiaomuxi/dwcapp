package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetMineInfoRequest extends BaseRequest<GetMineInfoRequest.Query, Object>{
    public static class Query {

        /**
         * ApiId : HC010108
         */

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String id;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
