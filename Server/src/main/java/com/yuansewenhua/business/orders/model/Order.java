package com.yuansewenhua.business.orders.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-11-0011.
 */
@TableBind(tableName = "orders", pkName = "id")
public class Order extends Model<Order> {
    public static Order dao = new Order();
    public static DBKit<Order> dbKit = new DBKit(dao);

    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<Order> findNoFinishedByPad(String pad) {
        return dao.find("select * from orders t where t.fromwhichpad = ? and t.status <> '2'", pad);
    }

    public List<Order> findNoFinished() {
        return dao.find("select * from orders t where t.status <> '2'");
    }

    public Order findByOrderNo(String orderNo) {
        return dao.findFirst("select * from orders t where t.orderno = ?", orderNo);
    }

    public Order findAppendOrder(String tablenumber) {
        List<Order> orders = find("select * from orders t where t.tablenumber = ? and t.status = 'UNPAY'", tablenumber);
        if (orders != null && orders.size() > 1) {
            throw new RuntimeException(String.format("台号%s有多条未完结的订单，您的操作无法完成！", tablenumber));
        }
        return orders == null || orders.size() == 0 ? null : orders.get(0);
    }
}
