package com.yuansewenhua.net.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 服务端处理会话
 * Created by fangshuai on 2015-01-23-0023.
 */
public class ServerHandler extends IoHandlerAdapter {
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println(message);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }
}
