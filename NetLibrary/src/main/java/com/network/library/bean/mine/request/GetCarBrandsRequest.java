package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class GetCarBrandsRequest extends BaseRequest<GetCarBrandsRequest.Query, Object>{

    public static class Query {

        /**
         * ApiId : HC010201
         */

        private String ApiId;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "ApiId='" + ApiId + '\'' +
                    '}';
        }
    }
}
