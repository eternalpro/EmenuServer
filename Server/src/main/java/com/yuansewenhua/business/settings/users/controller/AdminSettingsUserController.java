package com.yuansewenhua.business.settings.users.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.roles.model.Role;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.permission.validator.UserValidator;
import com.yuansewenhua.utils.AppUtils;
import net.wincn.core.BaseController;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-12-0012.
 */
@ControllerBind(controllerKey = "/admin/settings/users", viewPath = "admin/settings/users")
public class AdminSettingsUserController extends BaseController<User> {

    @ActionKey("/admin/settings/users")
    public void index() {
        params.put("ne_name", "admin");
        searchAndPaginate("name", User.dbKit);
    }

    public void form() {
        form("user", User.dao);
        List<Role> roles = Role.listValid();
        setAttr("roles", roles);
    }

    @Before(UserValidator.class)
    public void save() {
        saveOrUpdate(null, User.class);
        renderText("success");
    }

    public void delete() {
        delete(User.dao, null);
        renderText("success");

    }

    @Override
    protected boolean doAfterGetModel(User model) {
        Integer id = model.getInt("id");
        if (id == null || id == 0) {
            model.set("password", AppUtils.encode("12345678"));
        }
        return true;
    }

    @Override
    protected void doIfNoSave(User model) {

    }
}
