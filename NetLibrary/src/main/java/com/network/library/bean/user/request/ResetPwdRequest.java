package com.network.library.bean.user.request;

import com.network.library.bean.BaseRequest;

public class ResetPwdRequest extends BaseRequest<ResetPwdRequest.Request, Object>{
    public static class Request {

        /**
         * ApiId : HC020105
         * Tel : 13203861885
         * Password : 123
         * NewPassword : 1234
         * ID : Android
         */

        private String ApiId;
        private String Tel;
        private String Password;
        private String NewPassword;
        private String ID;

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

        public String getNewPassword() {
            return NewPassword;
        }

        public void setNewPassword(String NewPassword) {
            this.NewPassword = NewPassword;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "ApiId='" + ApiId + '\'' +
                    ", Tel='" + Tel + '\'' +
                    ", Password='" + Password + '\'' +
                    ", NewPassword='" + NewPassword + '\'' +
                    ", ID='" + ID + '\'' +
                    '}';
        }
    }
}
