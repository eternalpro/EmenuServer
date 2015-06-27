package com.yuansewenhua.api.utils;

import com.yuansewenhua.api.business.bean.DrinkBean;
import com.yuansewenhua.api.business.bean.DrinkTypeBean;
import com.yuansewenhua.api.business.bean.FoodBean;
import com.yuansewenhua.api.business.bean.FoodTypeBean;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.drinks.model.DrinksType;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import com.yuansewenhua.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
public class BeanUtils {

    public static List<DrinkTypeBean> copyDrinkTypeBeans(List<DrinksType> drinksTypes) {
        List<DrinkTypeBean> drinkTypeBeans = new ArrayList<>();
        for (DrinksType drinksType : drinksTypes) {
            DrinkTypeBean bean = new DrinkTypeBean(drinksType.getInt("id"), drinksType.getStr("title"));
            drinkTypeBeans.add(bean);
        }
        return drinkTypeBeans;
    }

    public static List<DrinkBean> copyDrinkBeans(List<Drinks> drinks) throws UnsupportedEncodingException {
        List<DrinkBean> drinkBeans = new ArrayList<>();
        for (Drinks drink : drinks) {
            DrinkBean drinkBean = new DrinkBean(
                    drink.getInt("id"),
                    drink.getStr("name"),
                    URLEncoder.encode(drink.getStr("smallimagepath"), "utf-8").replace("%2F", "/"),
                    URLEncoder.encode(drink.getStr("bigimagepath"), "utf-8").replace("%2F", "/"),
                    drink.getStr("cancold").equalsIgnoreCase("t"),
                    drink.getStr("canhot").equalsIgnoreCase("t"),
                    drink.getStr("havesugar").equalsIgnoreCase("t"),
                    Integer.parseInt(drink.getStr("clickcount")),
                    drink.getStr("typetitle"),
                    AppUtils.getPriceAndUnit(drink.getStr("price"), drink.getStr("sellunit"))
            );
            drinkBeans.add(drinkBean);
        }
        return drinkBeans;
    }

    public static List<FoodTypeBean> copyFoodTypeBeans(List<FoodsType> foodsTypes) {
        List<FoodTypeBean> foodTypeBeans = new ArrayList<>();
        for (FoodsType foodsType : foodsTypes) {
            FoodTypeBean bean = new FoodTypeBean(foodsType.getInt("id"), foodsType.getStr("title"));
            foodTypeBeans.add(bean);
        }
        return foodTypeBeans;
    }

    public static List<FoodBean> copyFoodBeans(List<Food> foods) throws UnsupportedEncodingException {
        List<FoodBean> foodBeans = new ArrayList<>();
        for (Food food : foods) {
            FoodBean foodBean = new FoodBean(
                    food.getInt("id"),
                    food.getStr("name"),
                    URLEncoder.encode(food.getStr("smallimagepath"), "utf-8").replace("%2F", "/"),
                    URLEncoder.encode(food.getStr("bigimagepath"), "utf-8").replace("%2F", "/"),
                    food.getStr("flavour"),
                    food.getStr("yongliao"),
                    food.getStr("memo"),
                    food.getStr("isqingzhen").equalsIgnoreCase("t"),
                    food.getStr("issu").equalsIgnoreCase("t"),
                    food.getStr("isliang").equalsIgnoreCase("t"),
                    Integer.parseInt(food.getStr("clickcount")),
                    food.getStr("typetitle"),
                    food.getStr("price")
            );
            foodBeans.add(foodBean);
        }
        return foodBeans;
    }


}
