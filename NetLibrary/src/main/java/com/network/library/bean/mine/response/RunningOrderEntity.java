package com.network.library.bean.mine.response;

import com.network.library.bean.user.response.OrderRunningListEntity;

import java.io.Serializable;
import java.util.List;

public class RunningOrderEntity implements Serializable {
    private String title;
    private List<OrderRunningListEntity> orderList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<OrderRunningListEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderRunningListEntity> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "RunningOrderEntity{" +
                "title='" + title + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
