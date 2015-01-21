package com.yuansewenhua.permission.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.yuansewenhua.business.settings.users.model.User;

/**
 * Created by fangshuai on 2014-11-12-0012.
 */
public class UserValidator extends Validator{
    @Override
    protected void validate(Controller c) {
        User user = c.getModel(User.class);
        Integer id = user.get("id");
        String username = user.get("name");
        if (id == null || id == 0) {
            User dbUser = User.findUserByName(username);
            if (dbUser != null) {
                addError("error", "用户已存在！");
            }
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderText("用户已存在");
    }
}
