package com.yuansewenhua.business.orders.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

/**
 * Created by fangshuai on 2014-11-11-0011.
 */
@TableBind(tableName = "orders", pkName = "id")
public class Order extends Model<Order>{
    public static Order dao = new Order();
    public static DBKit<Order> dbKit = new DBKit(dao);
}
