package com.yuansewenhua.business.settings.roles.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-12-0012.
 */
@TableBind(tableName = "roles", pkName = "id")
public class Role extends Model<Role>{
    public static final Role dao = new Role();
    public static DBKit<Role> dbKit = new DBKit(dao);

    /**
     * 查询角色状态
     * @param rolename
     * @return
     */
    public static boolean isEnable(String rolename) {
        return dao.findFirst("select * from roles where name = ?", rolename).getStr("isenable").equalsIgnoreCase("t");
    }

    public static List<Role> listAll() {
        return dao.find("select * from roles order by id desc");
    }

    public static List<Role> listValid(){
        return dao.find("select * from roles where isenable=true order by id desc");
    }
}
