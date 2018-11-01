package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class UpdatePayResultRequest extends BaseRequest<UpdatePayResultRequest.Query, Object> {
    public static class Query {

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String ID;
        private String reslut;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(String DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getReslut() {
            return reslut;
        }

        public void setReslut(String reslut) {
            this.reslut = reslut;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", ID='" + ID + '\'' +
                    ", reslut='" + reslut + '\'' +
                    '}';
        }
    }
}
