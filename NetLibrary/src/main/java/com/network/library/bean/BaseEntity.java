package com.network.library.bean;

public class BaseEntity<T> {
    /**
     * status : 1
     * msg : Successd
     * Count : 1
     * data : [{"UserId":"18616270226","Sex":"男","Name":"张硕"}]
     */

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

    public void setCount(String Count) {
        this.Count = Count;
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
