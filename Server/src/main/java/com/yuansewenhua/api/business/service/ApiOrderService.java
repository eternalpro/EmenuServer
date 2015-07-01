package com.yuansewenhua.api.business.service;

import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/1.
 */
public class ApiOrderService {
    public List<OrderBean> getNoFinishedByPad(String pad) {
        List<Order> orders = Order.dao.findNoFinishedByPad(pad);
        for (Order order : orders) {
            List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
            order.setOrderItems(orderItems);
        }
        return BeanUtils.copyOrderTypeBeans(orders);
    }
}
