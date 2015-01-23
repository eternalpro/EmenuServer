package com.yuansewenhua.net.main;

import com.yuansewenhua.net.handler.ServerHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * 服务器实例
 * Created by fangshuai on 2015-01-23-0023.
 */
public class ServerInstance {
    private int port;   // 服务端口号

    private ServerInstance(){

    }
    private ServerInstance(int port){
        this.port = port;
    }
    public static ServerInstance getInstance(int port) {
        return new ServerInstance(port);
    }

    /**
     * 启动服务
     * @throws IOException
     */
    public void start() throws IOException {
        // 创建一个非阻塞的server端Socket ，用NIO
        SocketAcceptor acceptor = new NioSocketAcceptor();

        /*---------接收对象---------*/
        //创建接收数据的过滤器
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        //设定这个过滤器将以对象为单位读取数据
        ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
        chain.addLast("objectFilter", filter);

        //设定服务器消息处理器
        acceptor.setHandler(new ServerHandler());
        //绑定端口，启动服务器
        try {
            acceptor.bind(new InetSocketAddress(port));
        } catch (IOException e) {
            System.out.println("Mina Server start for error!" + port);
            e.printStackTrace();
        }
        System.out.println("Mina Server run done! on port:" + port);
    }

    public static void main(String[] args) throws IOException {
        ServerInstance.getInstance(80).start();
    }
}
