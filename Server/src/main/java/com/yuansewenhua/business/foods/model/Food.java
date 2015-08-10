package com.yuansewenhua.business.foods.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.Date;
import java.util.List;

/**
 * Created by fangshuai on 2014-11-04-0004.
 */
@TableBind(tableName = "foods", pkName = "id")
public class Food extends Model<Food> {
    public static Food dao = new Food();
    public static DBKit<Food> dbKit = new DBKit(dao);

    public String getFoodsType() {
        return FoodsType.dao.findFirst("select * from foodstype where id = ?", get("foodstypeid")).getStr("title");
    }

    public static List<Food> getFoodsByIsenable(String isenable) {
        return dao.find("select * from foods where isenable = ?", isenable);
    }

    public static List<Food> getFoodsByOrderId(int orderId) {
        return dao.find("select * from foods where id in (select item.productid from orderitems item where item.orderid = ? and item.type='FOOD')", orderId);
    }

    public static List<Food> getPopFoods(int limit, Date begin, Date end) {
        return dao.find("select * from foods where id in (" +
                "SELECT item.productid " +
                " from orderitems item " +
                " where item.type='FOOD' " +
                " AND " +
                "item.orderid in (select o.id from orders o where o.createtime between ? and ? and o.status = 'FINISH')" +
                " GROUP BY item.productid " +
                " ORDER BY count(item.productid) DESC " +
                ") LIMIT ?", begin, end, limit);
    }
}
