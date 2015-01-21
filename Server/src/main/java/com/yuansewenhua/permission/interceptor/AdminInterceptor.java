package com.yuansewenhua.permission.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.yuansewenhua.business.settings.users.model.User;
import com.yuansewenhua.config.AppConstants;
import org.apache.log4j.Logger;

/**
 * 管理员权限过滤器
 * Created by fangshuai on 2014-09-01-0001.
 */
public class AdminInterceptor implements Interceptor{

    private static Logger logger = Logger.getLogger(AdminInterceptor.class);
    @Override
    public void intercept(ActionInvocation ai) {
        logger.debug("before admin permission check!");
        logger.debug("controller key:" + ai.getActionKey());
        if(ai.getActionKey().startsWith("/admin")) {                    // 以admin开头的链接，以管理员权限处理
            User user = (User) ai.getController().getSession().getAttribute(AppConstants.SESSION_ADMIN_KEY);
            if(user == null) {      // 无管理员权限
                // throw new RuntimeException("Access is prohibited, please check your permissions!");
                ai.getController().redirect("/admin/login");
            }else {
                ai.invoke();
            }
        }else{
            ai.invoke();
        }
        logger.debug("after admin permission check!");
    }
}
