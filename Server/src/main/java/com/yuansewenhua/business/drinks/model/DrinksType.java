package com.yuansewenhua.business.drinks.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-08-0008.
 */
@TableBind(tableName = "drinkstype", pkName = "id")
public class DrinksType extends Model<DrinksType>{
    public static DrinksType dao = new DrinksType();
    public static DBKit<DrinksType> dbKit = new DBKit(dao);

    public static boolean checkIfHasRecords(int typeId){
        List<Drinks> drinkses = Drinks.dao.find("select * from drinks where drinkstypeid = ?", typeId);
        if(drinkses != null && drinkses.size() > 0)
            return true;
        return false;
    }
}
