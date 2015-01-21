package com.yuansewenhua.business.orders.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.kit.EncryptionKit;
import com.yuansewenhua.business.orders.model.Order;
import net.wincn.core.BaseController;

/**
 * Created by fangshuai on 2014-11-11-0011.
 */
@ControllerBind(controllerKey = "/admin/orders", viewPath = "admin/orders")
public class AdminOrderController extends BaseController<Order> {

    @ActionKey("/admin/orders")
    public void index(){
        list(Order.dbKit);
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
