package com.yuansewenhua.api.business.controler;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.DrinkTypeBean;
import com.yuansewenhua.api.business.bean.FoodBean;
import com.yuansewenhua.api.business.bean.FoodTypeBean;
import com.yuansewenhua.api.business.service.ApiDrinksService;
import com.yuansewenhua.api.business.service.ApiFoodService;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import net.wincn.core.BaseController;

import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
@ControllerBind(controllerKey = "/api/foods")
public class ApiFoodsController extends BaseController<Food> {

    ApiFoodService foodService = new ApiFoodService();

    public void types() {
        List<FoodTypeBean> foodTypeBeans = foodService.getFoodTypeBeans();
        renderJson(foodTypeBeans);
    }

    @ActionKey("/api/foods")
    public void getFoods(){
        int typeId = getParaToInt(0);
        int page = getParaToInt(1);
        List<FoodBean> foodBeans = foodService.getFoodBeans(typeId, page);
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
