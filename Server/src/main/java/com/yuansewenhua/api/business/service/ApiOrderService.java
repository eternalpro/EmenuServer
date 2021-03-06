package com.yuansewenhua.api.business.service;

import com.yuansewenhua.api.business.bean.GoodsForOrder;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.exception.ObjectSaveFailException;
import com.yuansewenhua.api.exception.OperationFailException;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.api.utils.JsonUtils;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;
import com.yuansewenhua.business.settings.tables.model.Table;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.print.PrintUtils;
import com.yuansewenhua.utils.AppUtils;

import java.util.ArrayList;
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
                    List<OrderItem> orderItems = new ArrayList<>();
                    for (GoodsForOrder goodsForOrder : orderBean.getGoodsForOrders()) {
                        OrderItem orderItem = BeanUtils.copyOrderItem(order.getInt("id"), goodsForOrder);
                        saveOrderItem(orderItem);
                        orderItems.add(orderItem);
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
        PrintUtils.printSubmit(order);
        PrintUtils.printSubmit(order);
    }

    public void appendOrder(String json, Order appendOrder, OrderBean orderBean) throws ObjectSaveFailException {
        try {
            List<OrderItem> orderItems = new ArrayList<>();
            for (GoodsForOrder goodsForOrder : orderBean.getGoodsForOrders()) {
                OrderItem orderItem = BeanUtils.copyOrderItem(appendOrder.getInt("id"), goodsForOrder);
                orderItem.set("status", OrderItem.STATUS_APPEND);
                orderItems.add(orderItem);
                saveOrderItem(orderItem);
            }
            updateOrder(appendOrder);
            PrintUtils.printAdd(appendOrder, orderItems);
            PrintUtils.printAdd(appendOrder, orderItems);
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
            String waiter = orderBean.getWaiterName();
            String password = orderBean.getPassword();
            User user = User.findUserByName(waiter);
            if (user == null || !AppUtils.encode(password).equals(user.get("password")))
                throw new ObjectSaveFailException("服务员名字或密码不正确！");
            List<Order> orders = Order.dao.findNoFinishedByTableNumber(orderBean.getTableNumber());
            if(orders!=null && orders.size() > 0)
                throw new ObjectSaveFailException(String.format("台号“%s”有多条未完结的订单，您的操作无法完成！", orderBean.getTableNumber()));
            saveOrder(orderBean);
        }
    }

    private Order findAppendOrder(String tablenumber) throws ObjectSaveFailException {
        List<Order> orders = Order.dao.findNoFinishedByTableNumber(tablenumber);
        if (orders != null && orders.size() > 1) {
            throw new ObjectSaveFailException(String.format("台号“%s”有多条未完结的订单，您的操作无法完成！", tablenumber));
        }
        return orders == null || orders.size() == 0 ? null : orders.get(0);
    }

    /**
     * 返回条目剩余的数量，如果返回值是0,则pad需要删除记录，
     * @return
     */
    public int deleteItem(int id, int count) {
        int delteCount = count;
        OrderItem orderItem = OrderItem.dao.findById(id);
        count = orderItem.getCount() - count;
        Order order = Order.dao.findById(orderItem.getInt("orderid"));

        PrintUtils.printDelete(order, orderItem, delteCount);
        PrintUtils.printDelete(order, orderItem, delteCount);

        if (count <= 0) {
            orderItem.delete();
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

    /**
     * 台号变更
     * @param orderNo
     * @param newTablenumber
     */
    public void changeTableNumber(String orderNo, String newTablenumber) {
        Order order = Order.dao.findByOrderNo(orderNo);
        if (Table.toNames(Table.dao.findUnused()).contains(newTablenumber)) {
            order.setTableNumber(newTablenumber).update();
        }else {
            throw new OperationFailException("台号已被占用，请选择其他台号！");
        }
    }
}
