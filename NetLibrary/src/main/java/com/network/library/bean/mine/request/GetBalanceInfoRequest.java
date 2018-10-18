package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetBalanceInfoRequest extends BaseRequest<GetBalanceInfoRequest.Query, Object>{
    public static class Query {

        /**
         * ApiId : HC010101
         */

        private String ApiId;
        private String id;

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
                    "ApiId='" + ApiId + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
