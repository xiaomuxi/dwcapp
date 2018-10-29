package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class GetBalanceInfoEntity extends BaseEntity<List<GetBalanceInfoEntity.Data>>{
    public static class Data {

        /**
         * Name : 尹
         * IsActive : 正常
         * UserTy : 普通用户
         * Sex : 男
         * Integration : 0.0
         * Tel : 13203861885
         * Avator : 132038618851.png
         * IsCertification : 待审核
         * HlDate :
         * CashDeposit : 0.0
         * CashDepositMin : 2000
         */

        private String Name;
        private String IsActive;
        private String UserTy;
        private String Sex;
        private double Integration;
        private String Tel;
        private String Avator;
        private String IsCertification;
        private String HlDate;
        private double CashDeposit;
        private String CashDepositMin;

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getIsActive() {
            return IsActive;
        }

        public void setIsActive(String IsActive) {
            this.IsActive = IsActive;
        }

        public String getUserTy() {
            return UserTy;
        }

        public void setUserTy(String UserTy) {
            this.UserTy = UserTy;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public double getIntegration() {
            return Integration;
        }

        public void setIntegration(double Integration) {
            this.Integration = Integration;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String Tel) {
            this.Tel = Tel;
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

        public String getHlDate() {
            return HlDate;
        }

        public void setHlDate(String HlDate) {
            this.HlDate = HlDate;
        }

        public double getCashDeposit() {
            return CashDeposit;
        }

        public void setCashDeposit(double CashDeposit) {
            this.CashDeposit = CashDeposit;
        }

        public String getCashDepositMin() {
            return CashDepositMin;
        }

        public void setCashDepositMin(String CashDepositMin) {
            this.CashDepositMin = CashDepositMin;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "Name='" + Name + '\'' +
                    ", IsActive='" + IsActive + '\'' +
                    ", UserTy='" + UserTy + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", Integration=" + Integration +
                    ", Tel='" + Tel + '\'' +
                    ", Avator='" + Avator + '\'' +
                    ", IsCertification='" + IsCertification + '\'' +
                    ", HlDate='" + HlDate + '\'' +
                    ", CashDeposit=" + CashDeposit +
                    ", CashDepositMin='" + CashDepositMin + '\'' +
                    '}';
        }
    }
}
