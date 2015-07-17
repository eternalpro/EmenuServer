package com.yuansewenhua.api.business.bean.server;

import com.yuansewenhua.api.business.bean.GoodsForOrder;

/**
 * 订单追加类
 * Created by gefangshuai on 2015/7/3.
 */
public class OrderAndItemBean {
    private String orderNo;
    private GoodsForOrder goodsForOrder;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public GoodsForOrder getGoodsForOrder() {
        return goodsForOrder;
    }

    public void setGoodsForOrder(GoodsForOrder goodsForOrder) {
        this.goodsForOrder = goodsForOrder;
    }
}
