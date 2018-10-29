package com.network.library.bean.user.response;

import java.io.Serializable;
import java.util.List;

public class OrderRunningListEntity implements Serializable {
    private String Name;
    private String CarColorName;
    private int OrderCountOnGoing;
    private float Appraise;
    private int Income;
    private int OrderCountHasEnded;
    private List<String> OrderToday;
    private List<String> OrderWeek;
    private List<String> OrderTripConfirm;
    private List<String> WOrderTripConfirm;
    private String Sex;
    private String Avator;
    private String IsCertification;
    private String CarBrandID;
    private String CarBrandName;
    private String CarModelID;
    private String CarModelName;
    private String CarColorID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCarColorName() {
        return CarColorName;
    }

    public void setCarColorName(String carColorName) {
        CarColorName = carColorName;
    }

    public int getOrderCountOnGoing() {
        return OrderCountOnGoing;
    }

    public void setOrderCountOnGoing(int orderCountOnGoing) {
        OrderCountOnGoing = orderCountOnGoing;
    }

    public float getAppraise() {
        return Appraise;
    }

    public void setAppraise(float appraise) {
        Appraise = appraise;
    }

    public int getIncome() {
        return Income;
    }

    public void setIncome(int income) {
        Income = income;
    }

    public int getOrderCountHasEnded() {
        return OrderCountHasEnded;
    }

    public void setOrderCountHasEnded(int orderCountHasEnded) {
        OrderCountHasEnded = orderCountHasEnded;
    }

    public List<String> getOrderToday() {
        return OrderToday;
    }

    public void setOrderToday(List<String> orderToday) {
        OrderToday = orderToday;
    }

    public List<String> getOrderWeek() {
        return OrderWeek;
    }

    public void setOrderWeek(List<String> orderWeek) {
        OrderWeek = orderWeek;
    }

    public List<String> getOrderTripConfirm() {
        return OrderTripConfirm;
    }

    public void setOrderTripConfirm(List<String> orderTripConfirm) {
        OrderTripConfirm = orderTripConfirm;
    }

    public List<String> getWOrderTripConfirm() {
        return WOrderTripConfirm;
    }

    public void setWOrderTripConfirm(List<String> WOrderTripConfirm) {
        this.WOrderTripConfirm = WOrderTripConfirm;
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

    public String getIsCertification() {
        return IsCertification;
    }

    public void setIsCertification(String isCertification) {
        IsCertification = isCertification;
    }

    public String getCarBrandID() {
        return CarBrandID;
    }

    public void setCarBrandID(String carBrandID) {
        CarBrandID = carBrandID;
    }

    public String getCarBrandName() {
        return CarBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        CarBrandName = carBrandName;
    }

    public String getCarModelID() {
        return CarModelID;
    }

    public void setCarModelID(String carModelID) {
        CarModelID = carModelID;
    }

    public String getCarModelName() {
        return CarModelName;
    }

    public void setCarModelName(String carModelName) {
        CarModelName = carModelName;
    }

    public String getCarColorID() {
        return CarColorID;
    }

    public void setCarColorID(String carColorID) {
        CarColorID = carColorID;
    }

    @Override
    public String toString() {
        return "OrderRunningListEntity{" +
                "Name='" + Name + '\'' +
                ", CarColorName='" + CarColorName + '\'' +
                ", OrderCountOnGoing=" + OrderCountOnGoing +
                ", Appraise=" + Appraise +
                ", Income=" + Income +
                ", OrderCountHasEnded=" + OrderCountHasEnded +
                ", OrderToday=" + OrderToday +
                ", OrderWeek=" + OrderWeek +
                ", OrderTripConfirm=" + OrderTripConfirm +
                ", WOrderTripConfirm=" + WOrderTripConfirm +
                ", Sex='" + Sex + '\'' +
                ", Avator='" + Avator + '\'' +
                ", IsCertification='" + IsCertification + '\'' +
                ", CarBrandID='" + CarBrandID + '\'' +
                ", CarBrandName='" + CarBrandName + '\'' +
                ", CarModelID='" + CarModelID + '\'' +
                ", CarModelName='" + CarModelName + '\'' +
                ", CarColorID='" + CarColorID + '\'' +
                '}';
    }
}
