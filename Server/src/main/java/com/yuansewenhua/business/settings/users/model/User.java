package com.yuansewenhua.business.settings.users.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;

import java.util.List;

/**
 * 用户
 * Created by fangshuai on 2014-11-02-0002.
 */
@TableBind(tableName = "users", pkName = "id")
public class User extends Model<User> {
    public static final User dao = new User();
    public static DBKit<User> dbKit = new DBKit<>(dao);

    /**
     * 获得角色
     *
     * @return
     */
    public String getRole() {
        return this.getStr("rolename");
    }

    public static User findUserByName(String username) {
        return User.dao.findFirst("select * from users where name = ?", username);
    }

    public List<User> listByRole(String rolename) {
        return dao.find("select * from users where rolename = ? order by id desc", rolename);
    }

    public User findByUsername(String waiter) {
        return null;
    }
}
