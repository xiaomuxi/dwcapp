package com.network.library.bean.user.request;

import com.network.library.bean.BaseRequest;

public class LoginRequest extends BaseRequest<LoginRequest.Request, Object> {
    public static class Request {

        /**
         * ApiId : HC020103
         * Tel : 13203861885
         * Password : 1232222222
         */

        private String ApiId;
        private String Tel;
        private String Password;

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

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        @Override
        public String toString() {
            return "request{" +
                    "ApiId='" + ApiId + '\'' +
                    ", Tel='" + Tel + '\'' +
                    ", Password='" + Password + '\'' +
                    '}';
        }
    }
}
