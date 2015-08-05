package com.yuansewenhua.api.utils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/3.
 */
public class HttpUtils {
    public static void setCORS(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); //允许哪些url可以跨域请求到本域
        response.setHeader("Access-Control-Allow-Methods", "*"); //允许的请求方法，一般是GET,POST,PUT,DELETE,OPTIONS
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type"); //允许哪些请求头可以跨域

    }
}
