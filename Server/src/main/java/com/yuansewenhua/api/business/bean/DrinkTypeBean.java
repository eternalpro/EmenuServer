package com.yuansewenhua.api.business.bean;

import com.yuansewenhua.business.drinks.model.DrinksType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
public class DrinkTypeBean {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DrinkTypeBean() {
    }

    public DrinkTypeBean(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public static List<DrinkTypeBean> copy(List<DrinksType> drinksTypes) {
        List<DrinkTypeBean> drinkTypeBeans = new ArrayList<>();
        for (DrinksType drinksType : drinksTypes) {
            DrinkTypeBean bean = new DrinkTypeBean(drinksType.getInt("id"), drinksType.getStr("title"));
            drinkTypeBeans.add(bean);
        }
        return drinkTypeBeans;
    }
}
