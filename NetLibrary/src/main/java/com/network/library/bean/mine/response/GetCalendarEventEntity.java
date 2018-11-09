package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class GetCalendarEventEntity extends BaseEntity<List<GetCalendarEventEntity.Data>> {
    public static class Data {


        /**
         * URSN : 18616270226
         * Orderlist : [{"ID":"2018001164","Time":"2018-11-06"},{"ID":"2018001166","Time":"2018-11-15"}]
         * Buslist : [{"ID":"RL180000317","Time":"2018-09-04"},{"ID":"RL180000446","Time":"2018-05-17"},{"ID":"RL180000447","Time":"2018-05-11"}]
         */

        private String URSN;
        private List<OrderlistBean> Orderlist;
        private List<BuslistBean> Buslist;

        public String getURSN() {
            return URSN;
        }

        public void setURSN(String URSN) {
            this.URSN = URSN;
        }

        public List<OrderlistBean> getOrderlist() {
            return Orderlist;
        }

        public void setOrderlist(List<OrderlistBean> Orderlist) {
            this.Orderlist = Orderlist;
        }

        public List<BuslistBean> getBuslist() {
            return Buslist;
        }

        public void setBuslist(List<BuslistBean> Buslist) {
            this.Buslist = Buslist;
        }

        public static class OrderlistBean {
            /**
             * ID : 2018001164
             * Time : 2018-11-06
             */

            private String ID;
            private String Time;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            @Override
            public String toString() {
                return "OrderlistBean{" +
                        "ID='" + ID + '\'' +
                        ", Time='" + Time + '\'' +
                        '}';
            }
        }

        public static class BuslistBean {
            /**
             * ID : RL180000317
             * Time : 2018-09-04
             */

            private String ID;
            private String Time;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getTime() {
                return Time;
            }

            public void setTime(String Time) {
                this.Time = Time;
            }

            @Override
            public String toString() {
                return "BuslistBean{" +
                        "ID='" + ID + '\'' +
                        ", Time='" + Time + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "URSN='" + URSN + '\'' +
                    ", Orderlist=" + Orderlist +
                    ", Buslist=" + Buslist +
                    '}';
        }
    }
}
