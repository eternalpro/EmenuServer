package com.yuansewenhua.print;

import com.yuansewenhua.business.orders.model.Order;
import com.yuansewenhua.business.orders.model.OrderItem;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/12.
 */
public class PrintFactory {
    private static PrintFactory instance;

    private String ip;
    private int port;
    private String company;

    public void setCompany(String company) {
        this.company = company;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private Socket client;
    private OutputStream output;

    public static synchronized PrintFactory getInstance() {
        if (instance == null) {
            instance = new PrintFactory();
        }
        return instance;
    }

    private PrintFactory() {

    }

    public void open() throws IOException {
        client = new java.net.Socket();
        client.connect(new InetSocketAddress(ip, port), 5000); // 创建一个 socket
        output = client.getOutputStream();

    }

    public void close() throws IOException {
        output.close();
        client.close();
    }

    /**
     * 设置大标题
     * TODO 大的“加”和“减”需要定义
     *
     * @param title
     * @throws IOException
     */
    public void setTitle(String title) throws IOException {
        output.write(new byte[]{0x1B, 0x32}); // 默认行间距
        // 大标题
        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗
        output.write(new byte[]{0x1D, 0x21, 0x22}); // 字体大小
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐

        output.write(title.getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行
    }

    /**
     * 设置大标题
     * TODO 大的“加”和“减”需要定义
     *
     * @param title
     * @throws IOException
     */
    public void setTitle(String title, String bigTitle) throws IOException {
        output.write(new byte[]{0x1B, 0x32}); // 默认行间距
        // 大标题
        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗
        output.write(new byte[]{0x1D, 0x21, 0x22}); // 字体大小
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐

        output.write(title.getBytes("GBK"));    // 解决中文乱码问题
        // TODO 大标题
        output.write(" ".getBytes());
        output.write(new byte[]{0x1D, 0x21, 0x24}); // 字体大小
        output.write(bigTitle.getBytes("GBK"));
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行
    }

    /**
     * 设置公司名称
     *
     * @throws IOException
     */
    public void setCompanyInfo() throws IOException {
        // 公司名称
        output.write(new byte[]{0x1D, 0x21, 0x11}); // 字体大小
        output.write(company.getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行
    }

    /**
     * 设置台号和人数
     *
     * @throws IOException
     */
    public void setTableInfo(String tableNumber, int count) throws IOException {
        // 台号和人数
        output.write(new byte[]{0x1D, 0x21, 0x00}); // 还原字体大小
        output.write(("台号： " + tableNumber + "    人数： " + count).getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行
    }

    public void setOrders(Order order) throws IOException {
        List<OrderItem> items = order.getItems();
        for (int i = 0; i < items.size(); i++) {
            setItem((i + 1), items.get(i).getStr("name"), items.get(i).getInt("count"), items.get(i).getDouble("price"));
        }
    }

    public void setOrders(List<OrderItem> items) throws IOException {
        for (int i = 0; i < items.size(); i++) {
            setItem((i + 1), items.get(i).getStr("name"), items.get(i).getInt("count"), items.get(i).getDouble("price"));
        }
    }

    /**
     * 设置一个条目
     *
     * @param index
     * @param name
     * @param count
     * @param price
     * @throws IOException
     */
    public void setItem(int index, String name, int count, double price) throws IOException {
        // 内容
        output.write(new byte[]{0x1B, 0x45, 0x00}); // 取消加粗
        output.write(new byte[]{0x1B, 0x61, 0x00}); //居左对齐
        output.write(("  " + index + ". " + getName(name, 7) + "  数量：" + count + "  单价：" + price + "元").getBytes("GBK"));
        output.write(new byte[]{0x0A});
    }

    private String getName(String name, int length) {
        if (name.length() > length)
            return name.substring(0, length - 2) + "..";
        if (name.length() < length) {
            StringBuffer sb = new StringBuffer(name);
            for (int i = 0; i < length - name.length(); i++) {
                sb.append("  ");
            }
            return sb.toString();
        }
        return name;
    }

    /**
     * 设置价格
     */
    public void setPriceCount(double priceCount) throws IOException {
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐
        output.write(new byte[]{0x1D, 0x21, 0x11}); // 字体大小
        output.write(("总金额：" + priceCount + "元").getBytes("GBK"));    // 解决中文乱码问题
    }

    /**
     * 设置页尾
     *
     * @throws IOException
     */
    public void setFooter() throws IOException {
        // 页尾
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A});

        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐
        output.write(new byte[]{0x1D, 0x21, 0x00}); // 还原字体大小
        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
        output.write(("时间： " + sdf.format(new Date())).getBytes("GBK"));
        output.write(new byte[]{0x0A});
        output.write(new byte[]{0x1D, 0x21, 0x00}); // 还原字体大小
        output.write("极智菜单管理系统（原色文化出品）".getBytes("GBK"));
    }

    /**
     * 结束打印
     */
    public void finish() throws IOException {
        // 结束打印
        output.write(new byte[]{0x1B, 0x64, 0x06});// 走纸行数
        output.write(new byte[]{0x1D, 0x56, 0x00});// 切割
    }


}
