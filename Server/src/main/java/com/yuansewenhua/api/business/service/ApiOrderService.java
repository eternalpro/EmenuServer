package com.yuansewenhua.api.business.service;

import com.yuansewenhua.api.business.bean.GoodsForOrder;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.business.bean.server.OrderAndItemBean;
import com.yuansewenhua.api.exception.ObjectSaveFailException;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.api.utils.JsonUtils;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/1.
 */
public class ApiOrderService {

    /**
     * 复制订单信息到OrderBean
     * @param orders
     * @return
     */
    private List<OrderBean> copyOrderBeans(List<Order> orders) {
        for (Order order : orders) {
            List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
            order.setOrderItems(orderItems);
        }
        return BeanUtils.copyOrderBeans(orders);
    }

    public List<OrderBean> getNoFinishedByPad(String pad) {
        List<Order> orders = Order.dao.findNoFinishedByPad(pad);
        return copyOrderBeans(orders);
    }

    public List<OrderBean> getNoFinished() {
        List<Order> orders = Order.dao.findNoFinished();
        return copyOrderBeans(orders);

    }



    /**
     * 保存订单条目
     * @param orderItem
     * @throws ObjectSaveFailException
     */
    private void saveOrderItem(OrderItem orderItem) throws ObjectSaveFailException {
        if (!orderItem.save()) {
            throw new ObjectSaveFailException("[" + orderItem.getStr("name") + "] 订单条目保存失败！");
        }
    }

    /**
     * 更新现有订单信息（主要是订单的价格信息）
     *
     * @param order
     */
    private void updateOrder(Order order) throws ObjectSaveFailException {
        List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
        double pricecount = 0;
        for (OrderItem orderItem : orderItems) {
            pricecount += orderItem.getDouble("price") * orderItem.getInt("count");
        }
        order.set("pricecount", pricecount);
        if(!order.update())
            throw new ObjectSaveFailException("订单信息保存失败！");
    }

    /**
     * 保存订单信息
     *
     * @param orderBean
     * @throws ObjectSaveFailException
     */
    public void saveOrder(OrderBean orderBean) throws ObjectSaveFailException {
        Order order = BeanUtils.copyOrder(orderBean);
        try {
            try {
                if (order.save()) {
                    for (GoodsForOrder goodsForOrder : orderBean.getGoodsForOrders()) {
                        OrderItem orderItem = BeanUtils.copyOrderItem(order.getInt("id"), goodsForOrder);
                        saveOrderItem(orderItem);
                    }
                    updateOrder(order);
                } else {
                    throw new ObjectSaveFailException("订单信息保存失败！");
                }
            } catch (Exception e) {
                if (order.getInt("id") != null && order.getInt("id") != 0)
                    order.delete();
                throw new ObjectSaveFailException(e.getMessage());
            }
        } catch (Exception e) {
            throw new ObjectSaveFailException(e.getMessage());
        }
    }

    public void appendOrder(String json, Order appendOrder, OrderBean orderBean) throws ObjectSaveFailException {
        try {
            for (GoodsForOrder goodsForOrder : orderBean.getGoodsForOrders()) {
                OrderItem orderItem = BeanUtils.copyOrderItem(appendOrder.getInt("id"), goodsForOrder);
                orderItem.set("status", OrderItem.STATUS_APPEND);
                saveOrderItem(orderItem);
            }
            updateOrder(appendOrder);
        } catch (Exception e) {
            throw new ObjectSaveFailException(e.getMessage());
        }
    }

    /**
     * 保存或追加订单
     * @param json
     * @throws ObjectSaveFailException
     */
    public void saveOrAppendOrder(String json) throws ObjectSaveFailException {
        OrderBean orderBean = JsonUtils.getObjectFromJson(json, OrderBean.class);
        if (orderBean.isAppend()) { // 追加
            Order appendOrder = findAppendOrder(orderBean.getTableNumber());
            appendOrder(json, appendOrder, orderBean);
        }else {  //新增
            List<Order> orders = Order.dao.findNoFinishedByTableNumber(orderBean.getTableNumber());
            if(orders!=null && orders.size() > 0)
                throw new ObjectSaveFailException(String.format("台号%s有多条未完结的订单，您的操作无法完成！", orderBean.getTableNumber()));
            saveOrder(orderBean);
        }
    }

    private Order findAppendOrder(String tablenumber) throws ObjectSaveFailException {
        List<Order> orders = Order.dao.findNoFinishedByTableNumber(tablenumber);
        if (orders != null && orders.size() > 1) {
            throw new ObjectSaveFailException(String.format("台号%s有多条未完结的订单，您的操作无法完成！", tablenumber));
        }
        return orders == null || orders.size() == 0 ? null : orders.get(0);
    }

    /**
     * 返回条目剩余的数量，如果返回值是0,则pad需要删除记录，
     * @return
     */
    public int deleteItem(int id, int count) {
        OrderItem orderItem = OrderItem.dao.findById(id);
        count = orderItem.getCount() - count;
        if (count <= 0) {
            orderItem.delete();
            Order order = Order.dao.findById(orderItem.getInt("id"));
            List<OrderItem> remainItems = order.getItems();
            if (remainItems == null || remainItems.size() == 0) {
                order.delete();
            }
            return 0;
        }else {
            orderItem.set("count", count).update();
            return count;
        }
    }
}
