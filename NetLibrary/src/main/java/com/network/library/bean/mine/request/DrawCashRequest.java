package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class DrawCashRequest extends BaseRequest<DrawCashRequest.Query, Object> {
    public static class Query {

        /**
         * ApiId : HC020107
         * CustomerID : 13203861885
         * Amount : 100
         * BankAccount : 13203861885
         * BankName : 尹路佳
         * Type : 余额
         */

        private String userid;
        private String DEVICEID;
        private String ApiId;
        private String CustomerID;
        private String Amount;
        private String BankAccount;
        private String BankName;
        private String Type;

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

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String CustomerID) {
            this.CustomerID = CustomerID;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        public String getBankAccount() {
            return BankAccount;
        }

        public void setBankAccount(String BankAccount) {
            this.BankAccount = BankAccount;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        @Override
        public String toString() {
            return "Query{" +
                    "userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    ", ApiId='" + ApiId + '\'' +
                    ", CustomerID='" + CustomerID + '\'' +
                    ", Amount='" + Amount + '\'' +
                    ", BankAccount='" + BankAccount + '\'' +
                    ", BankName='" + BankName + '\'' +
                    ", Type='" + Type + '\'' +
                    '}';
        }
    }
}
