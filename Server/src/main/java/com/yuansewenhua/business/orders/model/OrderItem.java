package com.yuansewenhua.business.orders.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.yuansewenhua.api.business.bean.GoodsEnum;
import net.wincn.core.DBKit;

import java.util.Date;
import java.util.List;

/**
 * Created by gefangshuai on 2015/7/1.
 */
@TableBind(tableName = "orderitems", pkName = "id")
public class OrderItem extends Model<OrderItem> {
    public static OrderItem dao = new OrderItem();
    public static DBKit<OrderItem> dbKit = new DBKit(dao);
    public static final int STATUS_NEW = 0;
    public static final int STATUS_APPEND = 1;

    public List<OrderItem> listByOrder(int orderId) {
        return dao.find("select * from orderitems t where t.orderid = ? and t.status != -1", orderId);
    }

    /**
     * 根据订单编号、菜品id、菜品类型查询
     *
     * @param orderId
     * @param mid
     * @param type
     * @return
     */
    public OrderItem findOne(int orderId, long mid, GoodsEnum type) {
        return dao.findFirst("select * from orderitems t where t.orderid = ? and t.productid = ? and t.type = ?", orderId, mid, type.toString());
    }

    public int getCount() {
        return this.getInt("count");
    }

    public static List<OrderItem> getOrderItemsByTypeAndRange(String type, Date begin, Date end) {
        return dao.find("select * from orderitems t where t.type = ? " +
                "and t.status != -1 " +
                "and t.orderid in " +
                "(select o.id from orders o where o.createtime between ? and ?)", type, begin, end);
    }
}
