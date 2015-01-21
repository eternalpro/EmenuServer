package com.yuansewenhua.business.foods.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-08-0008.
 */
@TableBind(tableName = "foodstype", pkName = "id")
public class FoodsType extends Model<FoodsType>{
    public static FoodsType dao = new FoodsType();
    public static DBKit<FoodsType> dbKit = new DBKit(dao);

    public static boolean checkIfHasRecords(Integer id) {
        List<Food> foods = Food.dao.find("select * from foods where foodstypeid = ?", id);
        if(foods != null && foods.size() > 0)
            return true;
        return false;
    }
}
