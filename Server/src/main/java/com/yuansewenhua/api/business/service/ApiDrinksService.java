package com.yuansewenhua.api.business.service;

import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.DrinkTypeBean;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.drinks.model.DrinksType;
import net.wincn.core.DBKit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gefangshuai on 2015/6/23.
 */
public class ApiDrinksService {

    public List<DrinkTypeBean> getDrinkTypeBeans() {
        List<DrinksType> drinksTypes = DrinksType.dbKit.search(new HashMap<String, Object>(), DBKit.ASC);
        return DrinkTypeBean.copy(drinksTypes);

    }

    public List<DrinkBean> getDrinkBeans(int type, int page) {
        Map<String, Object> params = new HashMap<>();
        params.put("drinkstypeid", type);
        Page<Drinks> drinksPage =  Drinks.dbKit.search(page, 9, params, DBKit.ASC);
        List<DrinkBean> drinkBeans = DrinkBean.copy(drinksPage.getList());
        return drinkBeans;
    }
}
