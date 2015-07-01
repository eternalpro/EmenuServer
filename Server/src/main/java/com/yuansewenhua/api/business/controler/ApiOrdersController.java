package com.yuansewenhua.api.business.controler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.business.service.ApiOrderService;
import com.yuansewenhua.business.orders.model.Order;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 订单接口
 * Created by gefangshuai on 2015/7/1.
 */
@ControllerBind(controllerKey = "/api/orders")
public class ApiOrdersController extends Controller {
    private ApiOrderService orderService = new ApiOrderService();

    /**
     * 获取订单列表
     */
    public void list(){
        String pad = getPara();
        Assert.notNull(pad, "pad is not allow null!");
        List<OrderBean> orderBeans = orderService.getNoFinishedByPad(pad);
        renderJson(orderBeans);
    }

    /**
     * 添加订单
     */
    public void add(){

    }
}
