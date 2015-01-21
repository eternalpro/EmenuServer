package com.yuansewenhua.permission.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import org.apache.log4j.Logger;

/**
 * 董事长权限过滤器
 * Created by fangshuai on 2014-11-01-0001.
 */
public class CEOInterceptor implements Interceptor{

    private Logger logger = Logger.getLogger(CEOInterceptor.class);

    @Override
    public void intercept(ActionInvocation ai) {

    }
}
