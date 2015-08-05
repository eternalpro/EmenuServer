package com.yuansewenhua.permission.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.ClearInterceptor;
import com.jfinal.aop.ClearLayer;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.JsonKit;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.orders.service.OrderService;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.config.AppConstants;
import com.yuansewenhua.permission.validator.LoginValidator;
import com.yuansewenhua.utils.AppUtils;

/**
 * Created by fangshuai on 2014-11-01-0001.
 */
@ControllerBind(controllerKey = "/admin", viewPath = "admin")
public class AdminController extends Controller {
    OrderService orderService = new OrderService();

    @ActionKey("/admin")
    public void index() {
        setAttr("yesterdayResult", orderService.getYesterdayResult());
        setAttr("todayResult", orderService.getTodayResult());
        setAttr("visitorCount", orderService.getTodayVisitorCount());
        setAttr("clearFoods", Food.getFoodsByIsenable("false"));
        setAttr("yearSales", AppUtils.getPieStr(orderService.getYearSales()));
    }

    @ClearInterceptor(ClearLayer.ALL)
    @ActionKey("/admin/login")
    public void login() {
        setAttr("animate", "bounceInDown");
        renderJsp("permission/login.jsp");
    }

    /**
     * 进行登录操作
     */
    @ClearInterceptor(ClearLayer.ALL)
    @Before(LoginValidator.class)
    public void dologin() {
        String username = getPara("username");
        User user = User.findUserByName(username);
        getSession().setAttribute(AppConstants.SESSION_ADMIN_KEY, user);
        redirect("/admin");
    }

    public void logout() {
        getSession().removeAttribute(AppConstants.SESSION_ADMIN_KEY);
        redirect("/admin/login");
    }

}
