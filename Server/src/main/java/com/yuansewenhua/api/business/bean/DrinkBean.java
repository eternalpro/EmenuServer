package com.yuansewenhua.api.business.bean;

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
    private boolean haveAlcohol = false; // 是否有酒精
    private int clickcount = 0; //点击次数
    private String type;    // 类别名称
    private String unit; // 单位
    private double price;   // 单价


    public boolean isHaveAlcohol() {
        return haveAlcohol;
    }

    public void setHaveAlcohol(boolean haveAlcohol) {
        this.haveAlcohol = haveAlcohol;
    }

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DrinkBean() {
    }


    public DrinkBean(int id, String name, String smallImagePath, String bigImagePath, boolean cancold, boolean canhot, boolean haveSugar, boolean haveAlcohol, int clickcount, String type, String unit, double price) {
        this.id = id;
        this.name = name;
        this.smallImagePath = smallImagePath;
        this.bigImagePath = bigImagePath;
        this.cancold = cancold;
        this.canhot = canhot;
        this.haveSugar = haveSugar;
        this.haveAlcohol = haveAlcohol;
        this.clickcount = clickcount;
        this.type = type;
        this.unit = unit;
        this.price = price;
    }
}
