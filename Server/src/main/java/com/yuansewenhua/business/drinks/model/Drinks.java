package com.yuansewenhua.business.drinks.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

/**
 * Created by fangshuai on 2014-11-04-0004.
 */
@TableBind(tableName = "drinks", pkName = "id")
public class Drinks extends Model<Drinks> {
    public static Drinks dao = new Drinks();
    public static DBKit<Drinks> dbKit = new DBKit(dao);

    public Drinks findExistByName(String name, Integer id) {
        if(id == null)
            id = 0;
        return dao.findFirst("select * from drinks where name = ? and id != ?", name, id);
    }
}