package com.yuansewenhua.api.business.controler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.service.ApiOrderService;
import com.yuansewenhua.business.settings.tables.model.Table;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/14.
 */
@ControllerBind(controllerKey = "/api/tables")
public class ApiTablesController extends Controller {
    private ApiOrderService orderService = new ApiOrderService();

    public void all() {
        renderJson(Table.toNames(Table.dao.findAll()));
    }

    public void unused() {
        renderJson(Table.toNames(Table.dao.findUnused()));
    }

    public void used() {
        renderJson(Table.toNames(Table.dao.findUsed()));
    }

    public void change() {
        String orderNo = getPara("orderNo");
        String newTablenumber = getPara("tablenumber");
        try {
            if (StringUtils.isBlank(orderNo)) {
                renderText("订单编号为空！");
            } else if (StringUtils.isBlank(newTablenumber)) {
                renderText("新台号为空！");
            } else {
                orderService.changeTableNumber(orderNo, newTablenumber);
                renderText("true");
            }
        } catch (Exception e) {
            renderText(e.getMessage());
        }
    }
}
