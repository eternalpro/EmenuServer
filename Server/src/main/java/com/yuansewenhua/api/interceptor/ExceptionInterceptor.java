package com.yuansewenhua.api.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.render.RenderFactory;

/**
 * 异常渲染拦截器
 * Created by gefangshuai on 2015/7/3.
 */
public class ExceptionInterceptor implements Interceptor {
    @Override
    public void intercept(ActionInvocation ai) {
            try {
                ai.invoke();
            } catch (Exception e) {
                e.printStackTrace();
                String errorMsg = e.getMessage();
                ai.getController().renderText(errorMsg);
            }
    }
}
