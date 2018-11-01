package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class CompleteOrderListRequest extends BaseRequest<CompleteOrderListRequest.Query, Object> {
    public static class Query {

        /**
         * customerId : 13203861885
         * state : 已结束
         * pageSize : 10
         * pageIndex : 3
         */

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String customerId;
        private String state;
        private int pageSize;
        private int pageIndex;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String apiId) {
            ApiId = apiId;
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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", state='" + state + '\'' +
                    ", pageSize=" + pageSize +
                    ", pageIndex=" + pageIndex +
                    '}';
        }
    }
}
