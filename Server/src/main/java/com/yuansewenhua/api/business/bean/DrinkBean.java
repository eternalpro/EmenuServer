package com.yuansewenhua.api.business.bean;

import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于pad传输的酒水类
 * Created by gefangshuai on 2015/6/23.
 */
public class DrinkBean {
    private int id; // id
    private String name;    // 名称
    private String smallImagePath; // 缩略图地址
    private String bigImagePath; // 原图地址
    private boolean cancold = false; // 是否冰镇
    private boolean canhot = false; // 是否加热
    private boolean haveSugar = false;  // 是否加糖
    private int clickcount = 0; //点击次数
    private String type;    // 类别名称
    private List<String> priceAndUnits; // 价格及单位

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallImagePath() {
        return smallImagePath;
    }

    public void setSmallImagePath(String smallImagePath) {
        this.smallImagePath = smallImagePath;
    }

    public String getBigImagePath() {
        return bigImagePath;
    }

    public void setBigImagePath(String bigImagePath) {
        this.bigImagePath = bigImagePath;
    }

    public boolean isCancold() {
        return cancold;
    }

    public void setCancold(boolean cancold) {
        this.cancold = cancold;
    }

    public boolean isCanhot() {
        return canhot;
    }

    public void setCanhot(boolean canhot) {
        this.canhot = canhot;
    }

    public boolean isHaveSugar() {
        return haveSugar;
    }

    public void setHaveSugar(boolean haveSugar) {
        this.haveSugar = haveSugar;
    }

    public int getClickcount() {
        return clickcount;
    }

    public void setClickcount(int clickcount) {
        this.clickcount = clickcount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPriceAndUnits() {
        return priceAndUnits;
    }

    public void setPriceAndUnits(List<String> priceAndUnits) {
        this.priceAndUnits = priceAndUnits;
    }

    public DrinkBean() {
    }


    public DrinkBean(int id, String name, String smallImagePath, String bigImagePath, boolean cancold, boolean canhot, boolean haveSugar, int clickcount, String type, List<String> priceAndUnits) {
        this.id  = id;
        this.name = name;
        this.smallImagePath = smallImagePath;
        this.bigImagePath = bigImagePath;
        this.cancold = cancold;
        this.canhot = canhot;
        this.haveSugar = haveSugar;
        this.clickcount = clickcount;
        this.type = type;
        this.priceAndUnits = priceAndUnits;
    }

    public static List<DrinkBean> copy(List<Drinks> drinks) {
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


}
