package com.yuansewenhua.api.business.service;

import com.yuansewenhua.api.business.bean.GoodsForOrder;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.exception.ObjectSaveFailException;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.api.utils.JsonUtils;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
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

    /**
     * 保存订单信息
     * @param json
     * @throws ObjectSaveFailException
     */
    public void saveOrder(String json) throws ObjectSaveFailException {
        if(StringUtils.isBlank(json))
            throw new ObjectSaveFailException("order json is null!");
        OrderBean orderBean = JsonUtils.getObjectFromJson(json, OrderBean.class);

        Order order = new Order();
        order.set("createtime", new Date());
        order.set("status", orderBean.getStatus().toString());
        order.set("tablenumber", orderBean.getTableNumber());
        order.set("fromwhichpad", orderBean.getFromWhichPad());
        order.set("waitername", orderBean.getWaiterName());
        order.set("peoplenumber", orderBean.getPeopleNumber());
        double pricecount = 0;
        try {
            try {
                if (order.save()) {
                    for (GoodsForOrder goodsForOrder : orderBean.getGoodsForOrders()) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.set("name", goodsForOrder.getGoodsName());
                        orderItem.set("orderid", order.get("id"));
                        orderItem.set("productid", goodsForOrder.getMid());
                        orderItem.set("count", goodsForOrder.getCount());
                        orderItem.set("type", goodsForOrder.getType().toString());
                        orderItem.set("status", goodsForOrder.getStatus());
                        orderItem.set("price", goodsForOrder.getPrice());
                        orderItem.set("sellunit", goodsForOrder.getUnit());
                        if (orderItem.save()) {
                            pricecount += goodsForOrder.getPrice() * goodsForOrder.getCount();
                        } else {
                            throw new ObjectSaveFailException("[" + goodsForOrder.getGoodsName() + "] 订单条目保存失败！");
                        }
                    }
                    order.set("pricecount", pricecount);
                    order.update(); // 更新总价信息
                } else {
                    throw new ObjectSaveFailException("订单信息保存失败！");
                }
            } catch (Exception e) {
                order.delete();
                throw new ObjectSaveFailException(e.getMessage());
            }
        } catch (Exception e) {
            throw new ObjectSaveFailException(e.getMessage());
        }
    }

}
