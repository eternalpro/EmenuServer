package com.yuansewenhua.api.utils;

import com.yuansewenhua.api.business.bean.*;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.drinks.model.DrinksType;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
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
                    "t".equalsIgnoreCase(drink.getStr("cancold")),
                    "t".equalsIgnoreCase(drink.getStr("canhot")),
                    "t".equalsIgnoreCase(drink.getStr("havesugar")),
                    drink.getInt("clickcount"),
                    drink.getStr("typetitle"),
                    drink.getStr("sellunit"),
                    drink.getDouble("price")
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
                    "t".equalsIgnoreCase(food.getStr("isqingzhen")),
                    "t".equalsIgnoreCase(food.getStr("issu")),
                    "t".equalsIgnoreCase(food.getStr("isliang")),
                    food.getInt("clickcount"),
                    food.getStr("typetitle"),
                    food.getDouble("price")
            );
            foodBeans.add(foodBean);
        }
        return foodBeans;
    }


    /**
     * 复制order对象值
     *
     * @param orders
     * @return
     */
    public static List<OrderBean> copyOrderBeans(List<Order> orders) {
        List<OrderBean> orderBeans = new ArrayList<>();
        for (Order order : orders) {
            OrderBean orderBean = new OrderBean(
                    order.getStr("orderno"),
                    order.getDate("createtime"),
                    order.getStr("tablenumber"),
                    order.getStr("fromwhichpad"),
                    order.getStr("waitername"),
                    order.getInt("peoplenumber"),
                    OrderStatusEnum.valueOf(order.getStr("status"))
            );
            List<GoodsForOrder> goodsForOrders = new ArrayList<>();
            orderBean.setGoodsForOrders(goodsForOrders);

            for (OrderItem orderItem : order.getOrderItems()) {
                GoodsForOrder goodsForOrder = new GoodsForOrder();
                goodsForOrder.setGoodsName(orderItem.getStr("name"));
                goodsForOrder.setMid(orderItem.getInt("productid"));
                goodsForOrder.setCount(orderItem.getInt("count"));
                goodsForOrder.setPrice(orderItem.getDouble("price"));
                goodsForOrder.setType(GoodsEnum.valueOf(orderItem.getStr("type")));
                goodsForOrder.setUnit(orderItem.getStr("sellunit"));
                goodsForOrder.setStatus(orderItem.getInt("status"));
                goodsForOrders.add(goodsForOrder);
            }

            orderBeans.add(orderBean);
        }
        return orderBeans;
    }

    /**
     * 复制OrderBean对象到Order
     * @param orderBean
     * @return
     */
    public static Order copyOrder(OrderBean orderBean) {
        Order order = new Order();
        order.set("createtime", new Date());
        order.set("status", orderBean.getStatus().toString());
        order.set("tablenumber", orderBean.getTableNumber());
        order.set("fromwhichpad", orderBean.getFromWhichPad());
        order.set("waitername", orderBean.getWaiterName());
        order.set("peoplenumber", orderBean.getPeopleNumber());
        return order;
    }

    /**
     * 复制GoodsForOrder对象
     * @param orderId
     * @param goodsForOrder
     * @return
     */
    public static OrderItem copyOrderItem(int orderId, GoodsForOrder goodsForOrder) {
        OrderItem orderItem = new OrderItem();
        orderItem.set("name", goodsForOrder.getGoodsName());
        orderItem.set("orderid", orderId);
        orderItem.set("productid", goodsForOrder.getMid());
        orderItem.set("count", goodsForOrder.getCount());
        orderItem.set("type", goodsForOrder.getType().toString());
        orderItem.set("status", goodsForOrder.getStatus());
        orderItem.set("price", goodsForOrder.getPrice());
        orderItem.set("sellunit", goodsForOrder.getUnit());
        return orderItem;
    }
}
