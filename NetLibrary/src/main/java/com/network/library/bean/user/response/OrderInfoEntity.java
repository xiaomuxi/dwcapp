package com.network.library.bean.user.response;

import java.io.Serializable;
import java.util.List;

public class OrderInfoEntity implements Serializable {
    private String TheWeddingDateString;
    private int JourneyChoose;
    private int HourChoose;
    private String AreaName;
    private int PriceBaseTimeout;
    private int PriceBaseDistance;
    private int OfferCount;
    private int AmountAverage;
    private String Note;
    private String GatherTime;
    private String GatherCoordinateName;
    private String CustomerAvator;
    private List<MapInfos> MapInfos;
    private List<Drivers> Drivers;
    private String Tel;
    private int AmountAverages;
    private String GatherCoordinateLongitude;
    private String GatherCoordinateLatitude;
    private int AmountAveragesj;
    private String Iscar;
    private String CustomerName;
    private String CustomerSex;
    private String Code;
    private String CarBrandName;
    private String CarModelName;
    private int Count;
    private String ColorName;

    public void setTheWeddingDateString(String TheWeddingDateString) {
        this.TheWeddingDateString = TheWeddingDateString;
    }

    public String getTheWeddingDateString() {
        return TheWeddingDateString;
    }

    public void setJourneyChoose(int JourneyChoose) {
        this.JourneyChoose = JourneyChoose;
    }

    public int getJourneyChoose() {
        return JourneyChoose;
    }

    public void setHourChoose(int HourChoose) {
        this.HourChoose = HourChoose;
    }

    public int getHourChoose() {
        return HourChoose;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setPriceBaseTimeout(int PriceBaseTimeout) {
        this.PriceBaseTimeout = PriceBaseTimeout;
    }

    public int getPriceBaseTimeout() {
        return PriceBaseTimeout;
    }

    public void setPriceBaseDistance(int PriceBaseDistance) {
        this.PriceBaseDistance = PriceBaseDistance;
    }

    public int getPriceBaseDistance() {
        return PriceBaseDistance;
    }

    public void setOfferCount(int OfferCount) {
        this.OfferCount = OfferCount;
    }

    public int getOfferCount() {
        return OfferCount;
    }

    public void setAmountAverage(int AmountAverage) {
        this.AmountAverage = AmountAverage;
    }

    public int getAmountAverage() {
        return AmountAverage;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public String getNote() {
        return Note;
    }

    public void setGatherTime(String GatherTime) {
        this.GatherTime = GatherTime;
    }

    public String getGatherTime() {
        return GatherTime;
    }

    public void setGatherCoordinateName(String GatherCoordinateName) {
        this.GatherCoordinateName = GatherCoordinateName;
    }

    public String getGatherCoordinateName() {
        return GatherCoordinateName;
    }

    public void setCustomerAvator(String CustomerAvator) {
        this.CustomerAvator = CustomerAvator;
    }

    public String getCustomerAvator() {
        return CustomerAvator;
    }

    public void setMapInfos(List<MapInfos> MapInfos) {
        this.MapInfos = MapInfos;
    }

    public List<MapInfos> getMapInfos() {
        return MapInfos;
    }

    public void setDrivers(List<Drivers> Drivers) {
        this.Drivers = Drivers;
    }

    public List<Drivers> getDrivers() {
        return Drivers;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getTel() {
        return Tel;
    }

    public void setAmountAverages(int AmountAverages) {
        this.AmountAverages = AmountAverages;
    }

    public int getAmountAverages() {
        return AmountAverages;
    }

    public void setGatherCoordinateLongitude(String GatherCoordinateLongitude) {
        this.GatherCoordinateLongitude = GatherCoordinateLongitude;
    }

    public String getGatherCoordinateLongitude() {
        return GatherCoordinateLongitude;
    }

    public void setGatherCoordinateLatitude(String GatherCoordinateLatitude) {
        this.GatherCoordinateLatitude = GatherCoordinateLatitude;
    }

    public String getGatherCoordinateLatitude() {
        return GatherCoordinateLatitude;
    }

    public void setAmountAveragesj(int AmountAveragesj) {
        this.AmountAveragesj = AmountAveragesj;
    }

    public int getAmountAveragesj() {
        return AmountAveragesj;
    }

    public void setIscar(String Iscar) {
        this.Iscar = Iscar;
    }

    public String getIscar() {
        return Iscar;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerSex(String CustomerSex) {
        this.CustomerSex = CustomerSex;
    }

    public String getCustomerSex() {
        return CustomerSex;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getCode() {
        return Code;
    }

    public void setCarBrandName(String CarBrandName) {
        this.CarBrandName = CarBrandName;
    }

    public String getCarBrandName() {
        return CarBrandName;
    }

    public void setCarModelName(String CarModelName) {
        this.CarModelName = CarModelName;
    }

    public String getCarModelName() {
        return CarModelName;
    }

    public void setCount(int Count) {
        this.Count = Count;
    }

    public int getCount() {
        return Count;
    }

    public void setColorName(String ColorName) {
        this.ColorName = ColorName;
    }

    public String getColorName() {
        return ColorName;
    }

    public static class MapInfos implements Serializable {
        private String Number;
        private String CoordinateName;
        private String Longitude;
        private String Latitude;

        public void setNumber(String Number) {
            this.Number = Number;
        }

        public String getNumber() {
            return Number;
        }

        public void setCoordinateName(String CoordinateName) {
            this.CoordinateName = CoordinateName;
        }

        public String getCoordinateName() {
            return CoordinateName;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getLatitude() {
            return Latitude;
        }
    }

    public static class Drivers implements Serializable {
        private String CustomerName;
        private String CustomerTel;
        private String CarPlate;
        private String offerPrice;
        private String offerPricesj;
        private String isConfirmDistance;

        public void setCustomerName(String CustomerName) {
            this.CustomerName = CustomerName;
        }

        public String getCustomerName() {
            return CustomerName;
        }

        public void setCustomerTel(String CustomerTel) {
            this.CustomerTel = CustomerTel;
        }

        public String getCustomerTel() {
            return CustomerTel;
        }

        public void setCarPlate(String CarPlate) {
            this.CarPlate = CarPlate;
        }

        public String getCarPlate() {
            return CarPlate;
        }

        public void setOfferPrice(String offerPrice) {
            this.offerPrice = offerPrice;
        }

        public String getOfferPrice() {
            return offerPrice;
        }

        public void setOfferPricesj(String offerPricesj) {
            this.offerPricesj = offerPricesj;
        }

        public String getOfferPricesj() {
            return offerPricesj;
        }

        public void setIsConfirmDistance(String isConfirmDistance) {
            this.isConfirmDistance = isConfirmDistance;
        }

        public String getIsConfirmDistance() {
            return isConfirmDistance;
        }
    }
}
