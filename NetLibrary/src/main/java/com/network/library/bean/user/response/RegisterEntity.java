package com.network.library.bean.user.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class RegisterEntity extends BaseEntity<List<RegisterEntity.Data>> {
    public static class Data {

        /**
         * userId : 13203861885
         */

        private String userId;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
