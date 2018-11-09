package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class OperateCalendarEventRequest extends BaseRequest<OperateCalendarEventRequest.Query, Object>{

    public static class Query {

        /**
         * ApiId : HC030003
         * CustomerID : 17612157345
         * DEVICEID : asdfasdf
         * userid : 17612157345
         */

        private String ApiId;
        private String CustomerID;
        private String Time;
        private String DEVICEID;
        private String userid;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String CustomerID) {
            this.CustomerID = CustomerID;
        }

        public String getDEVICEID() {
            return DEVICEID;
        }

        public void setDEVICEID(String DEVICEID) {
            this.DEVICEID = DEVICEID;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTime() {
            return Time;
        }

        public void setTime(String time) {
            Time = time;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "ApiId='" + ApiId + '\'' +
                    ", CustomerID='" + CustomerID + '\'' +
                    ", Time='" + Time + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", userid='" + userid + '\'' +
                    '}';
        }
    }
}
