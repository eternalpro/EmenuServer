package com.yuansewenhua.permission.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.yuansewenhua.business.settings.roles.model.Role;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by fangshuai on 2014-11-12-0012.
 */
public class LoginValidator extends Validator {
    @Override
    protected void validate(Controller c) {

        String username = c.getPara("username");
        String password = c.getPara("password");

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            addError("error", "用户名或密码不能为空！");
        } else if (username.length() > 15 || password.length() > 15) {
            addError("error", "用户名或密码长度不能超过15位！");
        } else {
            User dbUser = User.findUserByName(username);
            if (dbUser == null || !dbUser.get("password").equals(AppUtils.encode(password))) {
                addError("error", "用户名或密码错误！");
            }else if (!Role.isEnable(dbUser.getStr("rolename"))) {
                addError("error", "用户所在角色组已被停用，请联系管理员！");
            }
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.keepPara("username");
        c.keepPara("password");
        c.setAttr("animate", "shake");
        c.renderJsp("permission/login.jsp");
    }
}
