package com.yuansewenhua.api.business.bean;

import java.util.Date;
import java.util.List;

/**
 * 订单信息表
 * Created by gefangshuai on 2015/7/1.
 */
public class OrderBean {
    private Date createTime;        // 创建日期
    private String tableNumber;     // 桌号
    private String fromWhichPad;    // 来自哪个终端(pad 唯一标识)
    private String waiterName;      // 服务员名称
    private int peopleNumber = 0;       // 食客人数
    private OrderStatusEnum status = OrderStatusEnum.UNPAY;
    private List<GoodsForOrder> goodsForOrders; // 订单里所有的条目信息

    public OrderBean() {
    }

    public OrderBean(Date createTime, String tableNumber, String fromWhichPad, String waiterName, int peopleNumber, OrderStatusEnum status) {
        this.createTime = createTime;
        this.tableNumber = tableNumber;
        this.fromWhichPad = fromWhichPad;
        this.waiterName = waiterName;
        this.peopleNumber = peopleNumber;
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getFromWhichPad() {
        return fromWhichPad;
    }

    public void setFromWhichPad(String fromWhichPad) {
        this.fromWhichPad = fromWhichPad;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public List<GoodsForOrder> getGoodsForOrders() {
        return goodsForOrders;
    }

    public void setGoodsForOrders(List<GoodsForOrder> goodsForOrders) {
        this.goodsForOrders = goodsForOrders;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
