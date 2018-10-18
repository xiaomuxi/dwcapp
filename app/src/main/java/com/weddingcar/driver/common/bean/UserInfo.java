package com.weddingcar.driver.common.bean;

public class UserInfo {

    /**
     * UserId : 18616270226
     * Sex : 男
     * Name : 张硕
     */

    private String UserId;
    private String Sex;
    private String Name;
    private String deviceId;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String Sex) {
        this.Sex = Sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "UserId='" + UserId + '\'' +
                ", Sex='" + Sex + '\'' +
                ", Name='" + Name + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
