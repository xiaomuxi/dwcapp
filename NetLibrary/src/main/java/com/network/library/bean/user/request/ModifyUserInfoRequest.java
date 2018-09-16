package com.network.library.bean.user.request;

import com.network.library.bean.BaseRequest;

public class ModifyUserInfoRequest extends BaseRequest<ModifyUserInfoRequest.Request, ModifyUserInfoRequest.Body>{

    public static class Request {

        /**
         * ApiId : HC020102
         */

        private String ApiId;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }
    }

    public static class Body {

        /**
         * ID : 13203861885
         * Name : heh
         * Sex : 男
         * Avator : aesfasdfasdf
         */

        private String ID;
        private String Name;
        private String Sex;
        private String Avator;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getAvator() {
            return Avator;
        }

        public void setAvator(String Avator) {
            this.Avator = Avator;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "ID='" + ID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", Avator='" + Avator + '\'' +
                    '}';
        }
    }
}
