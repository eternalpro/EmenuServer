package com.yuansewenhua.api.business.bean;

/**
 * 用于pad传输的酒水类
 * Created by gefangshuai on 2015/6/23.
 */
public class FoodBean {
    private int id; // id
    private String name;    // 名称
    private String smallImagePath; // 缩略图地址
    private String bigImagePath; // 原图地址
    private String flavour;     // 口味
    private String yongliao; // 菜品用料
    private String memo;    // 明细
    private String attribute;   // 属性
    private int clickcount = 0; //点击次数
    private String type;    // 类别名称
    private double price;   //价格

    public FoodBean() {
    }

    public FoodBean(int id, String name, String smallImagePath, String bigImagePath, String flavour, String yongliao, String memo, String attribute, int clickcount, String type, double price) {
        this.id = id;
        this.name = name;
        this.smallImagePath = smallImagePath;
        this.bigImagePath = bigImagePath;
        this.flavour = flavour;
        this.yongliao = yongliao;
        this.memo = memo;
        this.attribute = attribute;
        this.clickcount = clickcount;
        this.type = type;
        this.price = price;
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

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getYongliao() {
        return yongliao;
    }

    public void setYongliao(String yongliao) {
        this.yongliao = yongliao;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
