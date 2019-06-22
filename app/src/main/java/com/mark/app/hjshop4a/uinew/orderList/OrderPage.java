package com.mark.app.hjshop4a.uinew.orderList;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

public class OrderPage extends BaseModel {
    int all;//全部数量
    int processing;//进行中数量
    int waitDelivery;//待发货数量
    int waitEvaluation;//待评价数量
    int waitReturnMoney;//带返款数量

    int complete;//完成数量
    int close;//失败数量

    List<ShowOrder> orders;//订单详情

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getProcessing() {
        return processing;
    }

    public void setProcessing(int processing) {
        this.processing = processing;
    }

    public int getWaitDelivery() {
        return waitDelivery;
    }

    public void setWaitDelivery(int waitDelivery) {
        this.waitDelivery = waitDelivery;
    }

    public int getWaitEvaluation() {
        return waitEvaluation;
    }

    public void setWaitEvaluation(int waitEvaluation) {
        this.waitEvaluation = waitEvaluation;
    }

    public int getWaitReturnMoney() {
        return waitReturnMoney;
    }

    public void setWaitReturnMoney(int waitReturnMoney) {
        this.waitReturnMoney = waitReturnMoney;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public List<ShowOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShowOrder> orders) {
        this.orders = orders;
    }
}
