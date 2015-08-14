package com.yuansewenhua.business.orders.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.Date;
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
        return dao.find("select * from orders t where t.fromwhichpad = ? and t.status = 'UNPAY'", pad);
    }

    public List<Order> findNoFinished() {
        return dao.find("select * from orders t where t.status= 'UNPAY'");
    }

    public Order findByOrderNo(String orderNo) {
        return dao.findFirst("select * from orders t where t.orderno = ?", orderNo);
    }

    public List<Order> findNoFinishedByTableNumber(String tablenumber) {
        List<Order> orders = find("select * from orders t where t.tablenumber = ? and t.status = 'UNPAY'", tablenumber);
        return orders;
    }

    public List<Order> getPayedOrdersByTimeRange(Date timeSmall, Date timeBig){
        return find("select * from orders t where t.createtime between ? and ? and (t.status = 'PAYED' or t.status = 'FINISH')", timeSmall, timeBig);
    }

    public List<Order> getFinishedOrdersByTimeRange(Date timeSmall, Date timeBig) {
        return find("select * from orders t where t.createtime between ? and ? and t.status = 'FINISH'", timeSmall, timeBig);
    }

    public List<OrderItem> getItems() {
        return OrderItem.dao.find("select * from orderitems where orderid=?", getInt("id"));
    }

    public String getTableNumber() {
        return getStr("tablenumber");
    }
    public int getPeopleNumber() {
        return getInt("peoplenumber");
    }
    public double getPriceCount() {
        return getDouble("pricecount");
    }
}
