package com.yuansewenhua.business.orders.service;

import com.jfinal.plugin.activerecord.Page;
import com.yuansewenhua.api.business.bean.GoodsEnum;
import com.yuansewenhua.api.business.bean.OrderStatusEnum;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;
import com.yuansewenhua.business.settings.systems.model.SystemInfo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by gefangshuai on 2015/7/2.
 */
public class OrderService {
    public Page<Order> pageOrders(int pageNumber) {
        Page<Order> orderPage = Order.dao.paginate(pageNumber, 10, "select * ", " from orders order by id desc");
        for (Order order : orderPage.getList()) {
            List<OrderItem> orderItems = OrderItem.dao.listByOrder(order.getInt("id"));
            for (OrderItem orderItem : orderItems) {
                String imgPath = null;
                if (GoodsEnum.DRINK.toString().equals(orderItem.getStr("type"))) {
                    imgPath = Drinks.dao.findById(orderItem.getInt("productid")).getStr("smallimagepath");
                }
                if (GoodsEnum.FOOD.toString().equals(orderItem.getStr("type"))) {
                    imgPath = Food.dao.findById(orderItem.getInt("productid")).getStr("smallimagepath");
                }
                orderItem.put("imagePath", imgPath);
            }
            order.put("orderItems", orderItems);
        }
        return orderPage;
    }


    public List<Order> getYesterdayOrders() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, -1);    // 昨天
        calendar1.set(Calendar.HOUR_OF_DAY, 0);  // 0时
        calendar1.set(Calendar.MINUTE, 0);   // 0分
        calendar1.set(Calendar.SECOND, 0);   // 0秒

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, -1);    // 昨天
        calendar2.set(Calendar.HOUR_OF_DAY, 24);  // 0时
        calendar2.set(Calendar.MINUTE, 59);   // 0分
        calendar2.set(Calendar.SECOND, 59);   // 0秒

        List<Order> orders = Order.dao.getFinishedOrdersByTimeRange(calendar1.getTime(), calendar2.getTime());
        return orders;
    }

    public List<Order> getTodayOrders() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 0);  // 0时
        calendar1.set(Calendar.MINUTE, 0);   // 0分
        calendar1.set(Calendar.SECOND, 0);   // 0秒

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 24);  // 0时
        calendar2.set(Calendar.MINUTE, 59);   // 0分
        calendar2.set(Calendar.SECOND, 59);   // 0秒

        List<Order> orders = Order.dao.getFinishedOrdersByTimeRange(calendar1.getTime(), calendar2.getTime());
        return orders;
    }

    private List<Order> getMonthOrders() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        Date date1 = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

        Date date2 = calendar.getTime();
        List<Order> orders = Order.dao.getFinishedOrdersByTimeRange(date1, date2);
        return orders;
    }

    public double getYesterdayResult() {

        return getResult(getYesterdayOrders());
    }

    private double getResult(List<Order> orders) {
        double allMoney = 0;
        for (Order order : orders) {
            allMoney += order.getDouble("pricecount");
        }
        return allMoney;
    }

    public double getTodayResult() {
        return getResult(getTodayOrders());
    }

    public double getMonthResult() {
        return getResult(getMonthOrders());
    }

    public int getTodayVisitorCount() {
        List<Order> orders = getTodayOrders();
        int count = 0;
        for (Order order : orders) {
            count += order.getInt("peoplenumber");
        }
        return count;
    }

    /**
     * 获得一年的销售统计饼图
     *
     * @return
     */
    public Map<String, Double> getYearSales() {
        Map<String, Double> map = new HashMap<>();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.MONTH, 0);       // 1月
        calendar1.set(Calendar.DAY_OF_MONTH, 1);    //1日
        calendar1.set(Calendar.HOUR_OF_DAY, 0);  // 0时
        calendar1.set(Calendar.MINUTE, 0);   // 0分
        calendar1.set(Calendar.SECOND, 0);   // 0秒

        Calendar calendar2 = Calendar.getInstance();
        calendar2.roll(Calendar.DAY_OF_YEAR, -1);       // 1月
        calendar2.set(Calendar.HOUR_OF_DAY, 23);  // 0时
        calendar2.set(Calendar.MINUTE, 59);   // 0分
        calendar2.set(Calendar.SECOND, 59);   // 0秒

        List<OrderItem> foodItems = OrderItem.getOrderItemsByTypeAndRange(GoodsEnum.FOOD.name(), calendar1.getTime(), calendar2.getTime());
        List<OrderItem> drinkItems = OrderItem.getOrderItemsByTypeAndRange(GoodsEnum.DRINK.name(), calendar1.getTime(), calendar2.getTime());
        double drinkCount = getPriceCount(drinkItems);
        if (drinkCount > 0)
            map.put("酒水", drinkCount);

        List<FoodsType> foodsTypes = FoodsType.findAll();
        for (FoodsType foodsType : foodsTypes) {
            double foodCount = 0;
            for (OrderItem foodItem : foodItems) {
                Food food = Food.dao.findById(foodItem.getInt("productid"));
                if (food.getInt("foodstypeid") == foodsType.getInt("id")) {
                    foodCount += foodItem.getDouble("price") * foodItem.getInt("count");
                }else{
                    continue;
                }
            }
            if(foodCount>0)
                map.put(foodsType.getStr("title"), foodCount);

        }

        return map;
    }

    private double getPriceCount(List<OrderItem> orderItems) {
        double count = 0;
        for (OrderItem orderItem : orderItems) {
            count += orderItem.getDouble("price") * orderItem.getInt("count");
        }
        return count;
    }

    /**
     * 计算翻台率
     */
    public double getRateOfTableTurn() {

        List<Order> orders = getTodayOrders();
        int i = 0;
        for (Order order : orders) {
            if (order.getStr("status").equals(OrderStatusEnum.FINISH.name())) {
                i++;
            }
        }

        int tableCount = Integer.parseInt(SystemInfo.dao.findByKey(SystemInfo.TABLE_COUNT).getStr("value"));

        BigDecimal bigDecimal = BigDecimal.valueOf(i);
        return bigDecimal.divide(BigDecimal.valueOf(tableCount), 2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 计算每月销售额
     */
    public List<Double> getMonthSales() {
        List<Double> doubles = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, i);       // 1月
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            Date date1 = calendar.getTime();

            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));

            Date date2 = calendar.getTime();
            List<Order> orders = Order.dao.getFinishedOrdersByTimeRange(date1, date2);
            doubles.add(getResult(orders));
        }
        return doubles;
    }

    public static void main(String[] args) {

    }


    /**
     * 获取本月最流行的菜品菜品
     * @return
     */
    public List<Food> getMonthPopFoods(int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        Date date1 = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        Date date2 = calendar.getTime();

        return Food.getPopFoods(count, date1, date2);
    }
}
