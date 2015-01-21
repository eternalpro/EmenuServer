package com.yuansewenhua.business.settings.roles.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.roles.model.Role;
import com.yuansewenhua.business.settings.users.model.User;
import net.wincn.core.BaseController;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-12-0012.
 */
@ControllerBind(controllerKey = "/admin/settings/roles", viewPath = "admin/settings/roles")
public class AdminSettingsRoleController extends BaseController<Role> {

    @ActionKey("/admin/settings/roles")
    public void index(){
        list(Role.dbKit);
    }

    public void isenable(){
        int roleId = getParaToInt(0);
        int isenable = getParaToInt(1);
        Role role = Role.dao.findById(roleId);
        if (isenable == 0) {
            role.set("isenable", false).update();
        }else{
            role.set("isenable", true).update();
        }
        redirect("/admin/settings/roles");
    }

    public void usersmodal(){
        int roleId = getParaToInt();
        Role role = Role.dao.findById(roleId);
        List<User> users = User.dao.listByRole(role.getStr("name"));
        setAttr("role", role);
        setAttr("users", users);
    }


    @Override
    protected boolean doAfterGetModel(Role model) {
        return true;
    }

    @Override
    protected void doIfNoSave(Role model) {

    }
}
