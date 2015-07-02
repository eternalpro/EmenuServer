package com.yuansewenhua.business.orders.service;

import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/2.
 */
public class OrderService {
    public Page<Order> pageOrders(int pageNumber) {
        Page<Order> orderPage = Order.dao.paginate(pageNumber, 10, "select * ", " from orders order by status desc");
        for (Order order : orderPage.getList()) {
            List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
            order.put("orderItems", orderItems);
        }
        return orderPage;
    }

}
