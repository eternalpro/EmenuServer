package com.yuansewenhua.business.orders.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.api.business.bean.OrderStatusEnum;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.service.OrderService;
import com.yuansewenhua.print.PrintUtils;
import net.wincn.core.BaseController;

import java.util.Date;

/**
 * Created by fangshuai on 2014-11-11-0011.
 */
@ControllerBind(controllerKey = "/admin/orders", viewPath = "admin/orders")
public class AdminOrderController extends BaseController<Order> {
    private OrderService orderService = new OrderService();

    @ActionKey("/admin/orders")
    public void index() {
        int pageNumber = getParaToInt("page", 1);
        Page<Order> pageRecords = orderService.pageOrders(pageNumber);
        setAttr("pageRecords", pageRecords);
        setAttr("records", pageRecords.getList());
    }

    public void delete() {
        delete(Order.dao, null);
        renderText("success");

    }

    public void pay() {
        int id = getParaToInt();
        Order order = Order.dao.findById(id);
        order.set("status", OrderStatusEnum.PAYED.name());
        order.set("paytime", new Date());
        order.update();
        renderText("success");
    }

    public void print(){
        int id = getParaToInt();
        Order order = Order.dao.findById(id);
        PrintUtils.printFinish(order);
        renderText("success");
    }

    @Override
    protected boolean doAfterGetModel(Order model) {
        return true;
    }

    @Override
    protected void doIfNoSave(Order model) {

    }
}
