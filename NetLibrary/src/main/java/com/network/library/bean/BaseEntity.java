package com.network.library.bean;

public class BaseEntity<T> {
    private String status;
    private String msg;
    private String Count;
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", Count='" + Count + '\'' +
                ", data=" + data +
                '}';
    }
}
