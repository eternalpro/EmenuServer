package com.yuansewenhua.api.business.service;

import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.DrinkTypeBean;
import com.yuansewenhua.api.business.bean.FoodBean;
import com.yuansewenhua.api.business.bean.FoodTypeBean;
import com.yuansewenhua.api.utils.BeanUtils;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.drinks.model.DrinksType;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import net.wincn.core.DBKit;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gefangshuai on 2015/6/23.
 */
public class ApiFoodService {

    public List<FoodTypeBean> getFoodTypeBeans() {
        List<FoodsType> foodsTypes = FoodsType.dbKit.search(new HashMap<String, Object>(), DBKit.ASC);
        return BeanUtils.copyFoodTypeBeans(foodsTypes);

    }

    public List<FoodBean> getFoodBeans(int type, int page) throws UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<>();
        params.put("foodstypeid", type);
        Page<Food> foodPage =  Food.dbKit.search(page, 9, params, DBKit.ASC);
        List<FoodBean> foodBeans = BeanUtils.copyFoodBeans(foodPage.getList());
        return foodBeans;
    }
}
