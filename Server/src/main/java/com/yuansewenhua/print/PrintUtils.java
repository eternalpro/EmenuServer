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

    public static void printFinish(Order order) {
        PrintFactory printFactory = PrintFactory.getInstance();
        try {
            printFactory.open();
            printFactory.setTitle("结账单");
            printFactory.setCompanyInfo();
            printFactory.setTableInfo(order.getTableNumber(), order.getPeopleNumber());
            List<OrderItem> items = order.getItems();
            for (int i=0; i< items.size(); i++) {
                printFactory.setItem((i + 1), items.get(i).getStr("name"), items.get(i).getInt("count"), items.get(i).getDouble("price"));
            }
            printFactory.setPriceCount(order.getPriceCount());
            printFactory.setFooter();
            printFactory.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            printFactory.close();
        }
    }
}
