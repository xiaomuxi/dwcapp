package com.network.library.bean.user.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class LoginEntity extends BaseEntity<List<LoginEntity.Data>> {
    public static class Data {

        /**
         * UserId : 18616270226
         * Sex : 男
         * Name : 张硕
         */

        private String UserId;
        private String Sex;
        private String Name;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
