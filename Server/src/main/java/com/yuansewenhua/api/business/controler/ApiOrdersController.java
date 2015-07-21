package com.yuansewenhua.api.business.controler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.business.service.ApiOrderService;
import com.yuansewenhua.api.exception.ObjectSaveFailException;
import com.yuansewenhua.api.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
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
    public void list() {
        String pad = getPara();
        List<OrderBean> orderBeans = StringUtils.isNoneBlank(pad) ?
                orderService.getNoFinishedByPad(pad) : orderService.getNoFinished();
        renderJson(orderBeans);
    }

    /**
     * 新增或追加订单
     */
    public void add() throws ObjectSaveFailException {
        String json = JsonUtils.getJsonFromController(this);
        Assert.hasText(json, "json is not allow null");
        orderService.saveOrAppendOrder(json);
        renderText("true");

    }

    public void append() throws ObjectSaveFailException {
        String json = JsonUtils.getJsonFromController(this);
        orderService.saveOrAppendOrder(json);
        renderText("true");

    }

    /**
     * 删除条目
     */
    public void deleteitem() {
        int id = getParaToInt(0);
        int count = getParaToInt(1, 0);
        renderText(orderService.deleteItem(id, count) + "");
    }
}
