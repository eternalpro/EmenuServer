package com.yuansewenhua.business.orders.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * Created by gefangshuai on 2015/7/1.
 */
@TableBind(tableName = "orderitems", pkName = "id")
public class OrderItem extends Model<OrderItem>{
    public static OrderItem dao = new OrderItem();
    public static DBKit<OrderItem> dbKit = new DBKit(dao);

    public List<OrderItem> listByOrder(int orderId) {
        return dao.find("select * from orderitems t where t.orderid = ?", orderId);
    }

}
