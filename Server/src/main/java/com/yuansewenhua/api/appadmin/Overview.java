package com.yuansewenhua.api.appadmin;

import com.yuansewenhua.business.foods.model.Food;

import java.util.List;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/7.
 */
public class Overview {
    private double todayResult;         // 当日营业额
    private int todayOrderCount;     // 当日订单数
    private double rateOfTableTurn;     // 当日翻台率
    private double monthResult;         // 本月营业额
    private List<Food> popFoods;            // 最受欢迎菜品 1. 状元, 2. 榜眼, 3. 探花

    public Overview() {
    }

    public double getTodayResult() {
        return todayResult;
    }

    public void setTodayResult(double todayResult) {
        this.todayResult = todayResult;
    }

    public int getTodayOrderCount() {
        return todayOrderCount;
    }

    public void setTodayOrderCount(int todayOrderCount) {
        this.todayOrderCount = todayOrderCount;
    }

    public double getRateOfTableTurn() {
        return rateOfTableTurn;
    }

    public void setRateOfTableTurn(double rateOfTableTurn) {
        this.rateOfTableTurn = rateOfTableTurn;
    }

    public double getMonthResult() {
        return monthResult;
    }

    public void setMonthResult(double monthResult) {
        this.monthResult = monthResult;
    }

    public List<Food> getPopFoods() {
        return popFoods;
    }

    public void setPopFoods(List<Food> popFoods) {
        this.popFoods = popFoods;
    }
}
