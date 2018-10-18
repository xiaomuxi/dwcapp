package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class BalanceDetailEntity extends BaseEntity<List<BalanceDetailEntity.Data>> {
    public static class Data {

        /**
         * Amount : 2000.0
         * name : 保证金
         * orderID :
         * IntegrationBegin : 4000.0
         * Integration : 2000.0
         * CreateTime : 20181017
         * CreateTimeString : 2018-10-17 10:29:19
         * Type : 充值
         * State : 充值成功
         * StateString : 充值成功
         * MXID : MB1700000302
         */

        private double Amount;
        private String name;
        private String orderID;
        private double IntegrationBegin;
        private double Integration;
        private String CreateTime;
        private String CreateTimeString;
        private String Type;
        private String State;
        private String StateString;
        private String MXID;

        public double getAmount() {
            return Amount;
        }

        public void setAmount(double Amount) {
            this.Amount = Amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public double getIntegrationBegin() {
            return IntegrationBegin;
        }

        public void setIntegrationBegin(double IntegrationBegin) {
            this.IntegrationBegin = IntegrationBegin;
        }

        public double getIntegration() {
            return Integration;
        }

        public void setIntegration(double Integration) {
            this.Integration = Integration;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getCreateTimeString() {
            return CreateTimeString;
        }

        public void setCreateTimeString(String CreateTimeString) {
            this.CreateTimeString = CreateTimeString;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public String getStateString() {
            return StateString;
        }

        public void setStateString(String StateString) {
            this.StateString = StateString;
        }

        public String getMXID() {
            return MXID;
        }

        public void setMXID(String MXID) {
            this.MXID = MXID;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "Amount=" + Amount +
                    ", name='" + name + '\'' +
                    ", orderID='" + orderID + '\'' +
                    ", IntegrationBegin=" + IntegrationBegin +
                    ", Integration=" + Integration +
                    ", CreateTime='" + CreateTime + '\'' +
                    ", CreateTimeString='" + CreateTimeString + '\'' +
                    ", Type='" + Type + '\'' +
                    ", State='" + State + '\'' +
                    ", StateString='" + StateString + '\'' +
                    ", MXID='" + MXID + '\'' +
                    '}';
        }
    }
}
