package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetBalanceDetailListRequest extends BaseRequest<GetBalanceDetailListRequest.Query, Object> {
    public static class Query {

        /**
         * ApiId : HC0206200
         * customerId : 13203861885
         * pageIndex : 1
         * pageSize : 10
         */

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String customerId;
        private int pageIndex;
        private int pageSize;

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

        public void setApiId(String apiId) {
            ApiId = apiId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public void setPageIndex(int pageIndex) {
            this.pageIndex = pageIndex;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    ", pageIndex=" + pageIndex +
                    ", pageSize=" + pageSize +
                    '}';
        }
    }
}
