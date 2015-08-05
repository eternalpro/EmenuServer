package com.yuansewenhua.api.business.controler;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.FoodBean;
import com.yuansewenhua.api.business.bean.FoodTypeBean;
import com.yuansewenhua.api.business.service.ApiFoodService;
import com.yuansewenhua.api.utils.HttpUtils;
import com.yuansewenhua.business.foods.model.Food;
import net.wincn.core.BaseController;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
@ControllerBind(controllerKey = "/api/foods")
public class ApiFoodsController extends BaseController<Food> {

    ApiFoodService foodService = new ApiFoodService();

    public void types() {
        HttpUtils.setCORS(getResponse());
        List<FoodTypeBean> foodTypeBeans = foodService.getFoodTypeBeans();
        renderJson(foodTypeBeans);
    }

    @ActionKey("/api/foods")
    public void getFoods() throws UnsupportedEncodingException {
        HttpUtils.setCORS(getResponse());
        Integer typeId = getParaToInt(0);
        Integer page = getParaToInt(1);
        if (typeId == null)
            typeId = 0;
        Assert.notNull(page, "页码不能够为空！");
        List<FoodBean> foodBeans = foodService.getFoodBeans(typeId, page);
        renderJson(foodBeans);
    }

    /**
     * 根据属性获得数据列表
     *
     * @throws UnsupportedEncodingException
     */
    public void attribute() throws UnsupportedEncodingException {
        String attribute = getPara(0);
        int page = getParaToInt(1);
        List<FoodBean> foodBeans = foodService.getFoodBeans(attribute, page);
        renderJson(foodBeans);
    }


    @Override
    protected boolean doAfterGetModel(Food model) {
        return false;
    }

    @Override
    protected void doIfNoSave(Food model) {

    }
}
