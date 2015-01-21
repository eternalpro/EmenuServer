package com.yuansewenhua.permission.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import org.apache.log4j.Logger;

/**
 * 厨师长权限过滤器
 * Created by fangshuai on 2014-11-01-0001.
 */
public class ChefInterceptor implements Interceptor{

    private Logger logger = Logger.getLogger(ChefInterceptor.class);

    @Override
    public void intercept(ActionInvocation ai) {

    }
}
