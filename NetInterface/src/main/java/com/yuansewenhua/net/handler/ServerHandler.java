package com.yuansewenhua.net.handler;

import com.yuansewenhua.MessageObject;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 服务端处理会话
 * Created by fangshuai on 2015-01-23-0023.
 */
public class ServerHandler extends IoHandlerAdapter {
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent");
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message);
        session.close(true);
        System.out.println("messageReceived");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("sessionClosed");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("sessionOpened");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        System.out.println("sessionCreated");
    }
}
