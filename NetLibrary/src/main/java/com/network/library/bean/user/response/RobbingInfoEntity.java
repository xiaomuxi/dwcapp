package com.network.library.bean.user.response;

import java.io.Serializable;
import java.util.List;

public class RobbingInfoEntity implements Serializable {
    private String ID;
    private int Count;
    private String ColorName;
    private int JourneyChoose;
    private int HourChoose;
    private String AreaName;
    private String carlogo;
    private int carCount;
    private String groomName;
    private String State;
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

    public String getCarlogo() {
        return carlogo;
    }

    public void setCarlogo(String carlogo) {
        this.carlogo = carlogo;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    public String getGroomName() {
        return groomName;
    }

    public void setGroomName(String groomName) {
        this.groomName = groomName;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
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
        return "RobbingInfoEntity{" +
                "ID='" + ID + '\'' +
                ", Count=" + Count +
                ", ColorName='" + ColorName + '\'' +
                ", JourneyChoose=" + JourneyChoose +
                ", HourChoose=" + HourChoose +
                ", AreaName='" + AreaName + '\'' +
                ", carlogo='" + carlogo + '\'' +
                ", carCount=" + carCount +
                ", groomName='" + groomName + '\'' +
                ", State='" + State + '\'' +
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
}
