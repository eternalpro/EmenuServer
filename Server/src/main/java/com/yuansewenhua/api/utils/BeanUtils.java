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

    public static List<DrinkBean> copyDrinkBeans(List<Drinks> drinks) {
        List<DrinkBean> drinkBeans = new ArrayList<>();
        for (Drinks drink : drinks) {
            DrinkBean drinkBean = new DrinkBean(
                drink.getInt("id"),
                drink.getStr("name"),
                drink.getStr("smallimagepath"),
                drink.getStr("bigimagepath"),
                drink.getBoolean("cancold"),
                drink.getBoolean("canhot"),
                drink.getBoolean("havesugar"),
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

    public static List<FoodBean> copyFoodBeans(List<Food> foods) {
        List<FoodBean> foodBeans = new ArrayList<>();
        for (Food food : foods) {
            FoodBean foodBean = new FoodBean(
                food.getInt("id"),
                food.getStr("name"),
                food.getStr("smallimagepath"),
                food.getStr("bigimagepath"),
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
