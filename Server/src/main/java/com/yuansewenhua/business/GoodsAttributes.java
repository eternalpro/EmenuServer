package com.yuansewenhua.business;

/**
 * 食物属性类
 * Created by gefangshuai on 2015/7/20.
 */
public class GoodsAttributes {
    /**
     * 菜品属性
     */
    public enum FoodAttributesEnum {
        QINGZHEN("清真"),
        SU("素菜"),
        HUN("荤菜"),
        LIANG("凉菜"),
        RE("热菜");

        private String title;

        FoodAttributesEnum(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    /**
     * 酒水属性
     */
    public enum DrinksAttributesEnum {
        CANCOLD("冰镇"),
        CANHOT("可加热"),
        SUGAR("含糖"),
        ALCOHOL("含酒精");

        private String title;

        DrinksAttributesEnum(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

}
