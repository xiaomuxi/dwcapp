package com.network.library.bean.mine.response;

import com.network.library.bean.BaseEntity;

import java.util.List;

public class GetDriverCarInfoEntity extends BaseEntity<List<GetDriverCarInfoEntity.Data>> {
    public static class Data {

        /**
         * CustomerID : 13203861885
         * Introduce : 哈哈还是是的呢你的呢
         * ImagePathZQ : 1320386188512.png
         * ImagePathYQ : 1320386188513.png
         * ImagePathZH :
         * ImagePathYH :
         * ImagePath1 : 1320386188514.png
         * ImagePath2 : 1320386188515.png
         * ImagePath3 : 1320386188516.png
         * ImagePath4 : 1320386188517.png
         * Plate : 京A000000
         * Name : 尹
         * IsCertification : 待审核
         * phone : 13203861885
         * Sex : 男
         * Avator : 132038618851.png
         * Score : 0.0
         * OrderQuantity : 0
         * CarBrandName : 特殊车型
         * CarModelName : 敞篷
         * CarColorName : 白色
         */

        private String CustomerID;
        private String Introduce;
        private String ImagePathZQ;
        private String ImagePathYQ;
        private String ImagePathZH;
        private String ImagePathYH;
        private String ImagePath1;
        private String ImagePath2;
        private String ImagePath3;
        private String ImagePath4;
        private String Plate;
        private String Name;
        private String IsCertification;
        private String phone;
        private String Sex;
        private String Avator;
        private double Score;
        private int OrderQuantity;
        private String CarBrandName;
        private String CarModelName;
        private String CarColorName;

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String CustomerID) {
            this.CustomerID = CustomerID;
        }

        public String getIntroduce() {
            return Introduce;
        }

        public void setIntroduce(String Introduce) {
            this.Introduce = Introduce;
        }

        public String getImagePathZQ() {
            return ImagePathZQ;
        }

        public void setImagePathZQ(String ImagePathZQ) {
            this.ImagePathZQ = ImagePathZQ;
        }

        public String getImagePathYQ() {
            return ImagePathYQ;
        }

        public void setImagePathYQ(String ImagePathYQ) {
            this.ImagePathYQ = ImagePathYQ;
        }

        public String getImagePathZH() {
            return ImagePathZH;
        }

        public void setImagePathZH(String ImagePathZH) {
            this.ImagePathZH = ImagePathZH;
        }

        public String getImagePathYH() {
            return ImagePathYH;
        }

        public void setImagePathYH(String ImagePathYH) {
            this.ImagePathYH = ImagePathYH;
        }

        public String getImagePath1() {
            return ImagePath1;
        }

        public void setImagePath1(String ImagePath1) {
            this.ImagePath1 = ImagePath1;
        }

        public String getImagePath2() {
            return ImagePath2;
        }

        public void setImagePath2(String ImagePath2) {
            this.ImagePath2 = ImagePath2;
        }

        public String getImagePath3() {
            return ImagePath3;
        }

        public void setImagePath3(String ImagePath3) {
            this.ImagePath3 = ImagePath3;
        }

        public String getImagePath4() {
            return ImagePath4;
        }

        public void setImagePath4(String ImagePath4) {
            this.ImagePath4 = ImagePath4;
        }

        public String getPlate() {
            return Plate;
        }

        public void setPlate(String Plate) {
            this.Plate = Plate;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getIsCertification() {
            return IsCertification;
        }

        public void setIsCertification(String IsCertification) {
            this.IsCertification = IsCertification;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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

        public double getScore() {
            return Score;
        }

        public void setScore(double Score) {
            this.Score = Score;
        }

        public int getOrderQuantity() {
            return OrderQuantity;
        }

        public void setOrderQuantity(int OrderQuantity) {
            this.OrderQuantity = OrderQuantity;
        }

        public String getCarBrandName() {
            return CarBrandName;
        }

        public void setCarBrandName(String CarBrandName) {
            this.CarBrandName = CarBrandName;
        }

        public String getCarModelName() {
            return CarModelName;
        }

        public void setCarModelName(String CarModelName) {
            this.CarModelName = CarModelName;
        }

        public String getCarColorName() {
            return CarColorName;
        }

        public void setCarColorName(String CarColorName) {
            this.CarColorName = CarColorName;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "CustomerID='" + CustomerID + '\'' +
                    ", Introduce='" + Introduce + '\'' +
                    ", ImagePathZQ='" + ImagePathZQ + '\'' +
                    ", ImagePathYQ='" + ImagePathYQ + '\'' +
                    ", ImagePathZH='" + ImagePathZH + '\'' +
                    ", ImagePathYH='" + ImagePathYH + '\'' +
                    ", ImagePath1='" + ImagePath1 + '\'' +
                    ", ImagePath2='" + ImagePath2 + '\'' +
                    ", ImagePath3='" + ImagePath3 + '\'' +
                    ", ImagePath4='" + ImagePath4 + '\'' +
                    ", Plate='" + Plate + '\'' +
                    ", Name='" + Name + '\'' +
                    ", IsCertification='" + IsCertification + '\'' +
                    ", phone='" + phone + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", Avator='" + Avator + '\'' +
                    ", Score=" + Score +
                    ", OrderQuantity=" + OrderQuantity +
                    ", CarBrandName='" + CarBrandName + '\'' +
                    ", CarModelName='" + CarModelName + '\'' +
                    ", CarColorName='" + CarColorName + '\'' +
                    '}';
        }
    }
}
