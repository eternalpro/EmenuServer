package com.yuansewenhua.api.systems.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.settings.systems.model.SystemInfo;

/**
 * Created by fangshuai on 2014-12-03-0003.
 */
@ControllerBind(controllerKey = "/api/system")
public class ApiSystemController extends Controller{
    public static Order dao = new Order();

    public void key(){
        String key = getPara();
        try {
            renderText(SystemInfo.dao.findByKey(key).getStr("value"));
        } catch (Exception e) {
            renderText("无描述信息！");
        }
    }
}
