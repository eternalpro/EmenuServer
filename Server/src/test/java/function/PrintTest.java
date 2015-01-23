package function;

import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 测试打印机相关功能
 * Created by fangshuai on 2015-01-22-0022.
 */
public class PrintTest {
    /**
     * 测试报送单打印
     */
    @Test
    public void printBaoSong() throws IOException {
        Socket client = new java.net.Socket();
        client.connect(new InetSocketAddress("192.168.123.100", 9100), 5000); // 创建一个 socket
        OutputStream output = client.getOutputStream();// 创建输入输出数据流
        output.write(new byte[]{0x1B, 0x32}); // 默认行间距
        // 大标题
        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗
        output.write(new byte[]{0x1D, 0x21, 0x22}); // 字体大小
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐
        output.write("报送单".getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行

        // 公司名称
        output.write(new byte[]{0x1D, 0x21, 0x11}); // 字体大小
        output.write("北京餐饮管理有限公司".getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行

        // 台号和人数

        output.write(new byte[]{0x1D, 0x21, 0x00}); // 还原字体大小
        output.write("台号： 001    人数： 8".getBytes("GBK"));    // 解决中文乱码问题
        output.write(new byte[]{0x0A}); // 换行
        output.write(new byte[]{0x0A}); // 换行

        // 内容
        output.write(new byte[]{0x1B, 0x45, 0x00}); // 取消加粗
        output.write(new byte[]{0x1B, 0x61, 0x00}); //居左对齐

        output.write("1. 鱼香肉丝      份数：1  单价：58元".getBytes("GBK"));
        output.write(new byte[]{0x0A});
        output.write("2. 宫保鸡丁      份数：1  单价：58元".getBytes("GBK"));
        output.write(new byte[]{0x0A});
        output.write("3. 辣椒炒肉      份数：1  单价：58元".getBytes("GBK"));
        output.write(new byte[]{0x0A});
        output.write("4. 麻辣鸡丝      份数：1  单价：58元".getBytes("GBK"));


        // 页尾
        output.write(new byte[]{0x0A});
        output.write(new byte[]{0x0A});

        output.write(new byte[]{0x1B, 0x45, 0x01}); // 加粗
        output.write(new byte[]{0x1B, 0x61, 0x01}); //居中对齐
        output.write("时间： 2014.08.08  13:23".getBytes("GBK"));
        output.write(new byte[]{0x0A});
        output.write(new byte[]{0x0A});
        output.write(new byte[]{0x1D, 0x21, 0x00}); // 还原字体大小
        output.write("极智菜单管理系统（原色文化出品）".getBytes("GBK"));

        // 结束打印
        output.write(new byte[]{0x1B, 0x64, 0x06});// 走纸行数
        output.write(new byte[]{0x1D, 0x56, 0x00});// 切割
        output.close();
        client.close();
    }
}
