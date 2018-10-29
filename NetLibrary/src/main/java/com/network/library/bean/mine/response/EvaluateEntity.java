package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class EvaluateEntity extends BaseEntity<List<EvaluateEntity.Data>>{
    public static class Data {

        /**
         * CustomerName : 赵
         * CustomerAvator : 1801751236626.png
         * Star : 5
         * content :
         * Date :
         * DateString : 1537088400000
         * orderId : 2018000880
         * Sex : 男
         */

        private String CustomerName;
        private String CustomerAvator;
        private String Star;
        private String content;
        private String Date;
        private String DateString;
        private String orderId;
        private String Sex;

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getCustomerAvator() {
            return CustomerAvator;
        }

        public void setCustomerAvator(String CustomerAvator) {
            this.CustomerAvator = CustomerAvator;
        }

        public String getStar() {
            return Star;
        }

        public void setStar(String Star) {
            this.Star = Star;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return Date;
        }

        public void setDate(String Date) {
            this.Date = Date;
        }

        public String getDateString() {
            return DateString;
        }

        public void setDateString(String DateString) {
            this.DateString = DateString;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "CustomerName='" + CustomerName + '\'' +
                    ", CustomerAvator='" + CustomerAvator + '\'' +
                    ", Star='" + Star + '\'' +
                    ", content='" + content + '\'' +
                    ", Date='" + Date + '\'' +
                    ", DateString='" + DateString + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", Sex='" + Sex + '\'' +
                    '}';
        }
    }
}
