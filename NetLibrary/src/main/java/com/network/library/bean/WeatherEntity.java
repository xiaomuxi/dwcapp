package com.network.library.bean;

import java.io.Serializable;
import java.util.Arrays;

public class WeatherEntity extends BaseEntity {
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "result=" + result +
                '}';
    }

    public class Result implements Serializable {
        private String city;
        private int cityid;
        private int citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private int img;
        private String updatetime;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public int getCitycode() {
            return citycode;
        }

        public void setCitycode(int citycode) {
            this.citycode = citycode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "city='" + city + '\'' +
                    ", cityid=" + cityid +
                    ", citycode=" + citycode +
                    ", date='" + date + '\'' +
                    ", week='" + week + '\'' +
                    ", weather='" + weather + '\'' +
                    ", temp='" + temp + '\'' +
                    ", temphigh='" + temphigh + '\'' +
                    ", templow='" + templow + '\'' +
                    ", img=" + img +
                    ", updatetime='" + updatetime + '\'' +
                    '}';
        }
    }
}
