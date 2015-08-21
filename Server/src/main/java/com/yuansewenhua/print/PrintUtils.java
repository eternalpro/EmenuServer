package com.yuansewenhua.print;

import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.io.IOException;
import java.util.List;

/**
 * 打印机控制类
 *
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/12.
 */
public final class PrintUtils {




    /**
     * 结账单
     * @param order
     */
    public static void printFinish(Order order) {
        PrintFactory printFactory = PrintFactory.getInstance();
        try {
            printFactory.open();
            printFactory.setTitle("结账单");
            printFactory.setCompanyInfo();
            printFactory.setTableInfo(order.getTableNumber(), order.getPeopleNumber());
            printFactory.setOrders(order);
            printFactory.setPriceCount(order.getPriceCount());
            printFactory.setFooter();
            printFactory.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printFactory.close();
        }
    }

    /**
     * 报送单
     * @param order
     */
    public static void printSubmit(Order order){
        PrintFactory printFactory = PrintFactory.getInstance();
        try {
            printFactory.open();
            printFactory.setTitle("报送单");
            printFactory.setCompanyInfo();
            printFactory.setTableInfo(order.getTableNumber(), order.getPeopleNumber());
            printFactory.setOrders(order);
            printFactory.setFooter();
            printFactory.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printFactory.close();
        }
    }

    /**
     * 追加订单
     */
    public static void printAdd(Order order, List<OrderItem> orderItems) {
        PrintFactory printFactory = PrintFactory.getInstance();
        try {
            printFactory.open();
            printFactory.setTitle("报送单", "加");
            printFactory.setCompanyInfo();
            printFactory.setTableInfo(order.getTableNumber(), order.getPeopleNumber());
            printFactory.setOrders(orderItems);
            printFactory.setFooter();
            printFactory.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printFactory.close();
        }
    }

    /**
     * 删除订单
     */
    public static void printDelete(Order order, List<OrderItem> orderItems) {
        PrintFactory printFactory = PrintFactory.getInstance();
        try {
            printFactory.open();
            printFactory.setTitle("报送单", "减");
            printFactory.setCompanyInfo();
            printFactory.setTableInfo(order.getTableNumber(), order.getPeopleNumber());
            printFactory.setOrders(orderItems);
            printFactory.setFooter();
            printFactory.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printFactory.close();
        }
    }
}
