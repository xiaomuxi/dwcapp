package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class GetMineInfoEntity extends BaseEntity<List<GetMineInfoEntity.Data>> {
    public static class Data {

        /**
         * Name : 尹
         * CarColorName : 白色
         * OrderCountOnGoing : 0
         * Appraise : 0.0
         * Income : 0.0
         * OrderCountHasEnded : 0
         * OrderToday : []
         * OrderWeek : []
         * OrderTripConfirm : []
         * WOrderTripConfirm : []
         * Sex : 男
         * Avator : 132038618851.png
         * IsCertification : 待审核
         * CarBrandID : 1
         * CarBrandName : 奥迪
         * CarModelID : Q5
         * CarModelName : Q5
         * CarColorID : 白色
         */

        private String Name;
        private String CarColorName;
        private int OrderCountOnGoing;
        private double Appraise;
        private double Income;
        private int OrderCountHasEnded;
        private String Sex;
        private String Avator;
        private String IsCertification;
        private String CarBrandID;
        private String CarBrandName;
        private String CarModelID;
        private String CarModelName;
        private String CarColorID;
        private List<?> OrderToday;
        private List<?> OrderWeek;
        private List<?> OrderTripConfirm;
        private List<?> WOrderTripConfirm;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getCarColorName() {
            return CarColorName;
        }

        public void setCarColorName(String CarColorName) {
            this.CarColorName = CarColorName;
        }

        public int getOrderCountOnGoing() {
            return OrderCountOnGoing;
        }

        public void setOrderCountOnGoing(int OrderCountOnGoing) {
            this.OrderCountOnGoing = OrderCountOnGoing;
        }

        public double getAppraise() {
            return Appraise;
        }

        public void setAppraise(double Appraise) {
            this.Appraise = Appraise;
        }

        public double getIncome() {
            return Income;
        }

        public void setIncome(double Income) {
            this.Income = Income;
        }

        public int getOrderCountHasEnded() {
            return OrderCountHasEnded;
        }

        public void setOrderCountHasEnded(int OrderCountHasEnded) {
            this.OrderCountHasEnded = OrderCountHasEnded;
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

        public String getIsCertification() {
            return IsCertification;
        }

        public void setIsCertification(String IsCertification) {
            this.IsCertification = IsCertification;
        }

        public String getCarBrandID() {
            return CarBrandID;
        }

        public void setCarBrandID(String CarBrandID) {
            this.CarBrandID = CarBrandID;
        }

        public String getCarBrandName() {
            return CarBrandName;
        }

        public void setCarBrandName(String CarBrandName) {
            this.CarBrandName = CarBrandName;
        }

        public String getCarModelID() {
            return CarModelID;
        }

        public void setCarModelID(String CarModelID) {
            this.CarModelID = CarModelID;
        }

        public String getCarModelName() {
            return CarModelName;
        }

        public void setCarModelName(String CarModelName) {
            this.CarModelName = CarModelName;
        }

        public String getCarColorID() {
            return CarColorID;
        }

        public void setCarColorID(String CarColorID) {
            this.CarColorID = CarColorID;
        }

        public List<?> getOrderToday() {
            return OrderToday;
        }

        public void setOrderToday(List<?> OrderToday) {
            this.OrderToday = OrderToday;
        }

        public List<?> getOrderWeek() {
            return OrderWeek;
        }

        public void setOrderWeek(List<?> OrderWeek) {
            this.OrderWeek = OrderWeek;
        }

        public List<?> getOrderTripConfirm() {
            return OrderTripConfirm;
        }

        public void setOrderTripConfirm(List<?> OrderTripConfirm) {
            this.OrderTripConfirm = OrderTripConfirm;
        }

        public List<?> getWOrderTripConfirm() {
            return WOrderTripConfirm;
        }

        public void setWOrderTripConfirm(List<?> WOrderTripConfirm) {
            this.WOrderTripConfirm = WOrderTripConfirm;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "Name='" + Name + '\'' +
                    ", CarColorName='" + CarColorName + '\'' +
                    ", OrderCountOnGoing=" + OrderCountOnGoing +
                    ", Appraise=" + Appraise +
                    ", Income=" + Income +
                    ", OrderCountHasEnded=" + OrderCountHasEnded +
                    ", Sex='" + Sex + '\'' +
                    ", Avator='" + Avator + '\'' +
                    ", IsCertification='" + IsCertification + '\'' +
                    ", CarBrandID='" + CarBrandID + '\'' +
                    ", CarBrandName='" + CarBrandName + '\'' +
                    ", CarModelID='" + CarModelID + '\'' +
                    ", CarModelName='" + CarModelName + '\'' +
                    ", CarColorID='" + CarColorID + '\'' +
                    ", OrderToday=" + OrderToday +
                    ", OrderWeek=" + OrderWeek +
                    ", OrderTripConfirm=" + OrderTripConfirm +
                    ", WOrderTripConfirm=" + WOrderTripConfirm +
                    '}';
        }
    }
}
