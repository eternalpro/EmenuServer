package com.yuansewenhua.permission.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.config.AppConstants;
import com.yuansewenhua.permission.validator.LoginValidator;

/**
 *
 * Created by fangshuai on 2014-11-01-0001.
 */
@ControllerBind(controllerKey = "/admin", viewPath = "admin")
public class AdminController extends Controller{

    @ActionKey("/admin")
    public void index(){

    }

    @ClearInterceptor(ClearLayer.ALL)
    @ActionKey("/admin/login")
    public void login(){
        setAttr("animate", "bounceInDown");
        renderJsp("permission/login.jsp");
    }

    /**
     * 进行登录操作
     */
    @ClearInterceptor(ClearLayer.ALL)
    @Before(LoginValidator.class)
    public void dologin(){
        String username = getPara("username");
        User user = User.findUserByName(username);
        getSession().setAttribute(AppConstants.SESSION_ADMIN_KEY, user);
        redirect("/admin");
    }

    public void logout(){
        getSession().removeAttribute(AppConstants.SESSION_ADMIN_KEY);
        redirect("/admin/login");
    }

}
