package com.yuansewenhua.api.business.bean;

/**
 * 用于pad传输的酒水类
 * Created by gefangshuai on 2015/6/23.
 */
public class DrinkBean {
    private int id; // id
    private String name;    // 名称
    private String smallImagePath; // 缩略图地址
    private String bigImagePath; // 原图地址
    private String attribute;
    private int clickcount = 0; //点击次数
    private String type;    // 类别名称
    private String unit; // 单位
    private double price;   // 单价
    private String isenable; // 是否估清

    public DrinkBean() {
    }

    public DrinkBean(int id, String name, String smallImagePath, String bigImagePath, String attribute, int clickcount, String type, String unit, double price, String isenable) {
        this.id = id;
        this.name = name;
        this.smallImagePath = smallImagePath;
        this.bigImagePath = bigImagePath;
        this.attribute = attribute;
        this.clickcount = clickcount;
        this.type = type;
        this.unit = unit;
        this.price = price;
        this.isenable = isenable;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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

    public String getIsenable() {
        return isenable;
    }

    public void setIsenable(String isenable) {
        this.isenable = isenable;
    }
}
