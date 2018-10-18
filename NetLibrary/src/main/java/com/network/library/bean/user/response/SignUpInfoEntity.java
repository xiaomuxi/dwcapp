package com.network.library.bean.user.response;

import java.io.Serializable;
import java.util.List;

public class SignUpInfoEntity implements Serializable {
    private String ID;
    private int Count;
    private String ColorName;
    private int JourneyChoose;
    private int HourChoose;
    private String AreaName;
    private int OfferCount;
    private int AmountAverage;
    private List<OrderOffer> OrderOffers;
    private String Iscar;
    private String TheWeddingDate;
    private String TheWeddingDateString;
    private String CustomerAvator;
    private String CustomerName;
    private String CustomerSex;
    private String Code;
    private String CarBrandName;
    private String CarModelName;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getColorName() {
        return ColorName;
    }

    public void setColorName(String colorName) {
        ColorName = colorName;
    }

    public int getJourneyChoose() {
        return JourneyChoose;
    }

    public void setJourneyChoose(int journeyChoose) {
        JourneyChoose = journeyChoose;
    }

    public int getHourChoose() {
        return HourChoose;
    }

    public void setHourChoose(int hourChoose) {
        HourChoose = hourChoose;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public int getOfferCount() {
        return OfferCount;
    }

    public void setOfferCount(int offerCount) {
        OfferCount = offerCount;
    }

    public int getAmountAverage() {
        return AmountAverage;
    }

    public void setAmountAverage(int amountAverage) {
        AmountAverage = amountAverage;
    }

    public List<OrderOffer> getOrderOffers() {
        return OrderOffers;
    }

    public void setOrderOffers(List<OrderOffer> orderOffers) {
        OrderOffers = orderOffers;
    }

    public String getIscar() {
        return Iscar;
    }

    public void setIscar(String iscar) {
        Iscar = iscar;
    }

    public String getTheWeddingDate() {
        return TheWeddingDate;
    }

    public void setTheWeddingDate(String theWeddingDate) {
        TheWeddingDate = theWeddingDate;
    }

    public String getTheWeddingDateString() {
        return TheWeddingDateString;
    }

    public void setTheWeddingDateString(String theWeddingDateString) {
        TheWeddingDateString = theWeddingDateString;
    }

    public String getCustomerAvator() {
        return CustomerAvator;
    }

    public void setCustomerAvator(String customerAvator) {
        CustomerAvator = customerAvator;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerSex() {
        return CustomerSex;
    }

    public void setCustomerSex(String customerSex) {
        CustomerSex = customerSex;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCarBrandName() {
        return CarBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        CarBrandName = carBrandName;
    }

    public String getCarModelName() {
        return CarModelName;
    }

    public void setCarModelName(String carModelName) {
        CarModelName = carModelName;
    }

    @Override
    public String toString() {
        return "SignUpInfoEntity{" +
                "ID='" + ID + '\'' +
                ", Count=" + Count +
                ", ColorName='" + ColorName + '\'' +
                ", JourneyChoose=" + JourneyChoose +
                ", HourChoose=" + HourChoose +
                ", AreaName='" + AreaName + '\'' +
                ", OfferCount=" + OfferCount +
                ", AmountAverage=" + AmountAverage +
                ", OrderOffers=" + OrderOffers +
                ", Iscar='" + Iscar + '\'' +
                ", TheWeddingDate='" + TheWeddingDate + '\'' +
                ", TheWeddingDateString='" + TheWeddingDateString + '\'' +
                ", CustomerAvator='" + CustomerAvator + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerSex='" + CustomerSex + '\'' +
                ", Code='" + Code + '\'' +
                ", CarBrandName='" + CarBrandName + '\'' +
                ", CarModelName='" + CarModelName + '\'' +
                '}';
    }

    public static class OrderOffer implements Serializable{
        private String OrderOfferID;
        private String CustomerID;
        private String Name;
        private String isConfirmDistance;
        private String carColor;
        private String carPhone;
        private String star;
        private String carPlate;
        private String evaluate;
        private String orderAmount;
        private String Sex;
        private String Avator;
        private String Amount;

        public String getOrderOfferID() {
            return OrderOfferID;
        }

        public void setOrderOfferID(String orderOfferID) {
            OrderOfferID = orderOfferID;
        }

        public String getCustomerID() {
            return CustomerID;
        }

        public void setCustomerID(String customerID) {
            CustomerID = customerID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getIsConfirmDistance() {
            return isConfirmDistance;
        }

        public void setIsConfirmDistance(String isConfirmDistance) {
            this.isConfirmDistance = isConfirmDistance;
        }

        public String getCarColor() {
            return carColor;
        }

        public void setCarColor(String carColor) {
            this.carColor = carColor;
        }

        public String getCarPhone() {
            return carPhone;
        }

        public void setCarPhone(String carPhone) {
            this.carPhone = carPhone;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getCarPlate() {
            return carPlate;
        }

        public void setCarPlate(String carPlate) {
            this.carPlate = carPlate;
        }

        public String getEvaluate() {
            return evaluate;
        }

        public void setEvaluate(String evaluate) {
            this.evaluate = evaluate;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String sex) {
            Sex = sex;
        }

        public String getAvator() {
            return Avator;
        }

        public void setAvator(String avator) {
            Avator = avator;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String amount) {
            Amount = amount;
        }

        @Override
        public String toString() {
            return "OrderOffer{" +
                    "OrderOfferID='" + OrderOfferID + '\'' +
                    ", CustomerID='" + CustomerID + '\'' +
                    ", Name='" + Name + '\'' +
                    ", isConfirmDistance='" + isConfirmDistance + '\'' +
                    ", carColor='" + carColor + '\'' +
                    ", carPhone='" + carPhone + '\'' +
                    ", star='" + star + '\'' +
                    ", carPlate='" + carPlate + '\'' +
                    ", evaluate='" + evaluate + '\'' +
                    ", orderAmount='" + orderAmount + '\'' +
                    ", Sex='" + Sex + '\'' +
                    ", Avator='" + Avator + '\'' +
                    ", Amount='" + Amount + '\'' +
                    '}';
        }
    }
}
