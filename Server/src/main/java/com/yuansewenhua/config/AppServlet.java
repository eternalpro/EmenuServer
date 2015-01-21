package com.yuansewenhua.config;

import com.yuansewenhua.utils.AppUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 初始化程序信息
 * Created by fangshuai on 2014-11-02-0002.
 */
public class AppServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(AppServlet.class);

    @Override
    public void init() throws ServletException {
        AppConstants.app = AppUtils.loadAppConfig();        // 程序参数信息

        ServletContext application = getServletContext();
        application.setAttribute("app", AppConstants.app);
        logger.debug("init config finished!");
    }
}
