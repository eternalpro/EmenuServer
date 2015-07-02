package com.yuansewenhua.business.orders.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.EncryptionKit;
import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.service.OrderService;
import net.wincn.core.BaseController;

/**
 * Created by fangshuai on 2014-11-11-0011.
 */
@ControllerBind(controllerKey = "/admin/orders", viewPath = "admin/orders")
public class AdminOrderController extends BaseController<Order> {
    private OrderService orderService = new OrderService();

    @ActionKey("/admin/orders")
    public void index() {
        int pageNumber = getParaToInt("page", 1);
        Page<Order> pageRecords =orderService.pageOrders(pageNumber);
        setAttr("pageRecords", pageRecords);
        setAttr("records", pageRecords.getList());
    }

    public void delete() {
        delete(Order.dao, null);
        renderText("success");

    }

    public static void main(String[] args) {
        System.out.println(EncryptionKit.md5Encrypt("admin"));
    }

    @Override
    protected boolean doAfterGetModel(Order model) {
        return true;
    }

    @Override
    protected void doIfNoSave(Order model) {

    }
}
