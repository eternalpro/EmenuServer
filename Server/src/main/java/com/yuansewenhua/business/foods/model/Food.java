package com.yuansewenhua.business.foods.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

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
}
