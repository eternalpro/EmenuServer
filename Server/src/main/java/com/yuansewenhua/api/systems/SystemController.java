package com.yuansewenhua.api.systems;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.settings.systems.model.SystemInfo;

/**
 * Created by fangshuai on 2014-12-03-0003.
 */
@ControllerBind(controllerKey = "/system")
public class SystemController extends Controller{
    public static Order dao = new Order();

    public void key(){
        String key = getPara();
        renderText(SystemInfo.dao.findById(key).getStr("value"));
    }
}
