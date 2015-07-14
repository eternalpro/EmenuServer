package com.yuansewenhua.api.business.controler;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.DrinkTypeBean;
import com.yuansewenhua.api.business.service.ApiDrinksService;
import com.yuansewenhua.business.drinks.model.Drinks;
import net.wincn.core.BaseController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
@ControllerBind(controllerKey = "/api/drinks")
public class ApiDrinksController extends BaseController<Drinks> {

    ApiDrinksService drinksService = new ApiDrinksService();

    public void types() {
        List<DrinkTypeBean> drinkTypeBeans = drinksService.getDrinkTypeBeans();
        renderJson(drinkTypeBeans);
    }

    @ActionKey("/api/drinks")
    public void getDrinks() throws UnsupportedEncodingException {
        int typeId = getParaToInt(0);
        int page = getParaToInt(1);
        List<DrinkBean> drinkBeans = drinksService.getDrinkBeans(typeId, page);
        renderJson(drinkBeans);
    }



    @Override
    protected boolean doAfterGetModel(Drinks model) {
        return false;
    }

    @Override
    protected void doIfNoSave(Drinks model) {

    }
}
