package com.yuansewenhua.api.appadmin;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.utils.HttpUtils;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.orders.service.OrderService;

import java.util.List;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/7.
 */
@ControllerBind(controllerKey = "/api/admin")
public class APPAdminController extends Controller {
    OrderService orderService = new OrderService();

    public void index() {
        HttpUtils.setCORS(getResponse());

        double todayResult = orderService.getTodayResult();
        int todayOrderCount = orderService.getTodayOrders().size();
        double rateOfTableTurn = orderService.getRateOfTableTurn();
        double monthResult = orderService.getMonthResult();
        List<Food> popFoods = orderService.getMonthPopFoods(3);
        Overview overview = new Overview();
        overview.setTodayResult(todayResult);
        overview.setTodayOrderCount(todayOrderCount);
        overview.setRateOfTableTurn(rateOfTableTurn);
        overview.setMonthResult(monthResult);
        overview.setPopFoods(popFoods);
        renderJson(overview);

    }
}
