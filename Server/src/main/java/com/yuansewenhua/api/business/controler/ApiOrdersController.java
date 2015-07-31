package com.yuansewenhua.api.business.controler;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.GoodsEnum;
import com.yuansewenhua.api.business.bean.OrderBean;
import com.yuansewenhua.api.business.service.ApiOrderService;
import com.yuansewenhua.api.exception.ObjectSaveFailException;
import com.yuansewenhua.api.utils.JsonUtils;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.utils.AppUtils;
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
        String waiter = getPara("waiter");
        String password = getPara("password", "");
        User user = User.findUserByName(waiter);
        if (user == null || !AppUtils.encode(password).equals(user.get("password")))
            renderText("服务员名字或密码不正确！");
        else
            renderText(orderService.deleteItem(id, count) + "");
    }

    public void checkifClear() {
        int id = getParaToInt(0);
        GoodsEnum type = GoodsEnum.valueOf(getPara(1));
        if (type == GoodsEnum.FOOD) {
            Food food = Food.dao.findById(id);
            renderText(food.getStr("isenable"));
        }else {
            Drinks drinks = Drinks.dao.findById("isenable");
            renderText(drinks.getStr("isenable"));
        }
    }
}
