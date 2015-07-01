package com.yuansewenhua.api.business.bean;

/**
 * 订单状态枚举
 * Created by gefangshuai on 2015/7/1.
 */
public enum OrderStatusEnum {
    UNPAY,  // 未付款
    PAYED,  // 已付款
    FINISH  // 已完结（小票打印完毕，订单不能改变）
}
