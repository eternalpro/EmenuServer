package com.yuansewenhua.business.orders.service;

import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.api.business.bean.GoodsEnum;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/2.
 */
public class OrderService {
    public Page<Order> pageOrders(int pageNumber) {
        Page<Order> orderPage = Order.dao.paginate(pageNumber, 10, "select * ", " from orders order by id desc");
        for (Order order : orderPage.getList()) {
            List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
            for (OrderItem orderItem : orderItems) {
                String imgPath = null;
                if (GoodsEnum.DRINK.toString().equals(orderItem.getStr("type"))) {
                    imgPath = Drinks.dao.findById(orderItem.getInt("productid")).getStr("smallimagepath");
                }
                if (GoodsEnum.FOOD.toString().equals(orderItem.getStr("type"))) {
                    imgPath = Food.dao.findById(orderItem.getInt("productid")).getStr("smallimagepath");
                }
                orderItem.put("imagePath", imgPath);
            }
            order.put("orderItems", orderItems);
        }
        return orderPage;
    }

}
