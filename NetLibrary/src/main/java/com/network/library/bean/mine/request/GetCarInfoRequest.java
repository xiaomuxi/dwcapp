package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetCarInfoRequest extends BaseRequest<GetCarInfoRequest.Query, Object>{
    public static class Query {

        /**
         * ApiId : HC010108
         */

        private String ApiId;
        private String customerId;

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

        @Override
        public String toString() {
            return "Query{" +
                    "ApiId='" + ApiId + '\'' +
                    ", customerId='" + customerId + '\'' +
                    '}';
        }
    }
}
