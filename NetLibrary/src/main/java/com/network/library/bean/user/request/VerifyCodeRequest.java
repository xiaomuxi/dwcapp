package com.network.library.bean.user.request;

import com.network.library.bean.BaseRequest;

public class VerifyCodeRequest extends BaseRequest<VerifyCodeRequest.Request, Object>{
    public static class Request {

        /**
         * ApiId : HC020501
         * Tel : 13203861885
         * Type : 2
         */

        private String ApiId;
        private String Tel;
        private String Type;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }
    }
}
