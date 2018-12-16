package com.network.library.bean.mine.request;

import com.network.library.bean.BaseRequest;

public class CarAuthRequest extends BaseRequest<CarAuthRequest.Query, CarAuthRequest.Body> {
    public static class Query {

        /**
         * ApiId : HC020113
         */

        private String ApiId;
        private String userid;
        private String DEVICEID;

        public String getApiId() {
            return ApiId;
        }

        public void setApiId(String ApiId) {
            this.ApiId = ApiId;
        }

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

        @Override
        public String toString() {
            return "Query{" +
                    "ApiId='" + ApiId + '\'' +
                    ", userid='" + userid + '\'' +
                    ", DEVICEID='" + DEVICEID + '\'' +
                    '}';
        }
    }

    public static class Body {
        private String CustomerID;
        private String CarModelID;
        private String CarColorID;
        private String Plate;
        private String BankAccount;
        private String BankName;
        private String Tel;
        private String TelEmergency;
        private String Introduce;
        private String ImagePathZQ;
        private String ImagePathYQ;
        private String ImagePathZH;
        private String ImagePathYH;
        private String ImagePath1;
        private String ImagePath2;
        private String ImagePath3;
        private String ImagePath4;
        private String ImagePath5;
        private String ImagePath6;
        private String ImagePath7;
        private String ImagePath8;
        private double Latitude;
        private double Longitude;

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String customerID) {
            CustomerID = customerID;
        }

        public String getCarModelID() {
            return CarModelID;
        }

        public void setCarModelID(String carModelID) {
            CarModelID = carModelID;
        }

        public String getCarColorID() {
            return CarColorID;
        }

        public void setCarColorID(String carColorID) {
            CarColorID = carColorID;
        }

        public String getPlate() {
            return Plate;
        }

        public void setPlate(String plate) {
            Plate = plate;
        }

        public String getBankAccount() {
            return BankAccount;
        }

        public void setBankAccount(String bankAccount) {
            BankAccount = bankAccount;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String bankName) {
            BankName = bankName;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getTelEmergency() {
            return TelEmergency;
        }

        public void setTelEmergency(String telEmergency) {
            TelEmergency = telEmergency;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String introduce) {
            Introduce = introduce;
        }

        public String getImagePathZQ() {
            return ImagePathZQ;
        }

        public void setImagePathZQ(String imagePathZQ) {
            ImagePathZQ = imagePathZQ;
        }

        public String getImagePathYQ() {
            return ImagePathYQ;
        }

        public void setImagePathYQ(String imagePathYQ) {
            ImagePathYQ = imagePathYQ;
        }

        public String getImagePathZH() {
            return ImagePathZH;
        }

        public void setImagePathZH(String imagePathZH) {
            ImagePathZH = imagePathZH;
        }

        public String getImagePathYH() {
            return ImagePathYH;
        }

        public void setImagePathYH(String imagePathYH) {
            ImagePathYH = imagePathYH;
        }

        public String getImagePath1() {
            return ImagePath1;
        }

        public void setImagePath1(String imagePath1) {
            ImagePath1 = imagePath1;
        }

        public String getImagePath2() {
            return ImagePath2;
        }

        public void setImagePath2(String imagePath2) {
            ImagePath2 = imagePath2;
        }

        public String getImagePath3() {
            return ImagePath3;
        }

        public void setImagePath3(String imagePath3) {
            ImagePath3 = imagePath3;
        }

        public String getImagePath4() {
            return ImagePath4;
        }

        public void setImagePath4(String imagePath4) {
            ImagePath4 = imagePath4;
        }

        public String getImagePath5() {
            return ImagePath5;
        }

        public void setImagePath5(String imagePath5) {
            ImagePath5 = imagePath5;
        }

        public String getImagePath6() {
            return ImagePath6;
        }

        public void setImagePath6(String imagePath6) {
            ImagePath6 = imagePath6;
        }

        public String getImagePath7() {
            return ImagePath7;
        }

        public void setImagePath7(String imagePath7) {
            ImagePath7 = imagePath7;
        }

        public String getImagePath8() {
            return ImagePath8;
        }

        public void setImagePath8(String imagePath8) {
            ImagePath8 = imagePath8;
        }

        public double getLatitude() {
            return Latitude;
        }

        public void setLatitude(double latitude) {
            Latitude = latitude;
        }

        public double getLongitude() {
            return Longitude;
        }

        public void setLongitude(double longitude) {
            Longitude = longitude;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "CustomerID='" + CustomerID + '\'' +
                    ", CarModelID='" + CarModelID + '\'' +
                    ", CarColorID='" + CarColorID + '\'' +
                    ", Plate='" + Plate + '\'' +
                    ", BankAccount='" + BankAccount + '\'' +
                    ", BankName='" + BankName + '\'' +
                    ", Tel='" + Tel + '\'' +
                    ", TelEmergency='" + TelEmergency + '\'' +
                    ", Introduce='" + Introduce + '\'' +
                    ", ImagePathZQ='" + ImagePathZQ + '\'' +
                    ", ImagePathYQ='" + ImagePathYQ + '\'' +
                    ", ImagePathZH='" + ImagePathZH + '\'' +
                    ", ImagePathYH='" + ImagePathYH + '\'' +
                    ", ImagePath1='" + ImagePath1 + '\'' +
                    ", ImagePath2='" + ImagePath2 + '\'' +
                    ", ImagePath3='" + ImagePath3 + '\'' +
                    ", ImagePath4='" + ImagePath4 + '\'' +
                    ", ImagePath5='" + ImagePath5 + '\'' +
                    ", ImagePath6='" + ImagePath6 + '\'' +
                    ", ImagePath7='" + ImagePath7 + '\'' +
                    ", ImagePath8='" + ImagePath8 + '\'' +
                    ", Latitude=" + Latitude +
                    ", Longitude=" + Longitude +
                    '}';
        }
    }
}
