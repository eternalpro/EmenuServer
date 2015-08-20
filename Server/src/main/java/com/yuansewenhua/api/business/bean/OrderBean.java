package com.yuansewenhua.api.business.bean;

import java.util.Date;
import java.util.List;

/**
 * 订单信息表
 * Created by gefangshuai on 2015/7/1.
 */
public class OrderBean {
    private String orderNo;            // 订单编号
    private Date createTime;        // 创建日期
    private String tableNumber;     // 桌号
    private String fromWhichPad;    // 来自哪个终端(pad 唯一标识)
    private String waiterName;      // 服务员名称
    private boolean append = false; // 是否追加：true 追加；false 新增
    private int peopleNumber = 0;       // 食客人数
    private OrderStatusEnum status = OrderStatusEnum.UNPAY;
    private List<GoodsForOrder> goodsForOrders; // 订单里所有的条目信息
    private String password;
    public OrderBean() {
    }

    public OrderBean(String orderNo, Date createTime, String tableNumber, String fromWhichPad, String waiterName, boolean append, int peopleNumber, OrderStatusEnum status) {
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.tableNumber = tableNumber;
        this.fromWhichPad = fromWhichPad;
        this.waiterName = waiterName;
        this.append = append;
        this.peopleNumber = peopleNumber;
        this.status = status;
    }

    public OrderBean(String orderNo, Date createTime, String tableNumber, String fromWhichPad, String waiterName, int peopleNumber, OrderStatusEnum status) {
        this.orderNo = orderNo;
        this.createTime = createTime;
        this.tableNumber = tableNumber;
        this.fromWhichPad = fromWhichPad;
        this.waiterName = waiterName;
        this.peopleNumber = peopleNumber;
        this.status = status;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
