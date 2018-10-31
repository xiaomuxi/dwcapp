package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class DrawCashEntity extends BaseEntity<List<DrawCashEntity.Data>> {

    public static class Data {

        /**
         * Mxid : MX170000126
         */

        private String Mxid;

        public String getMxid() {
            return Mxid;
        }

        public void setMxid(String Mxid) {
            this.Mxid = Mxid;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "Mxid='" + Mxid + '\'' +
                    '}';
        }
    }
}
