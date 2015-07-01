package com.yuansewenhua.api.business.service;

import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.business.orders.model.Order;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/1.
 */
public class ApiOrderService {
    public List<OrderBean> getNoFinishedByPad(String pad) {
        List<Order> orders = Order.dao.findNoFinishedByPad(pad);
        return BeanUtils.copyOrderTypeBeans(orders);
    }
}
