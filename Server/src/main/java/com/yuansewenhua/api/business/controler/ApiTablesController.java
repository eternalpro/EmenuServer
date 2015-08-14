package com.yuansewenhua.api.business.controler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.tables.model.Table;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/14.
 */
@ControllerBind(controllerKey = "/api/tables")
public class ApiTablesController extends Controller{

    public void all(){
        renderJson(Table.toNames(Table.dao.findAll()));
    }

    public void unused(){
        renderJson(Table.toNames(Table.dao.findUnused()));
    }

    public void used(){
        renderJson(Table.toNames(Table.dao.findUsed()));
    }
}
