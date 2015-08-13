package com.yuansewenhua.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.util.JdbcConstants;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.*;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.yuansewenhua.api.interceptor.ExceptionInterceptor;
import com.yuansewenhua.permission.interceptor.AdminInterceptor;
import com.yuansewenhua.print.PrintFactory;
import net.wincn.core.AutoTableInfoPlugin;

import java.util.Properties;

public class AppConfig extends JFinalConfig {
    private Properties appProperties;   // properties 配置文件

    /**
     * 工程配置信息
     *
     * @param me
     */
    @Override
    public void configConstant(Constants me) {
        // 加载properties文件
        appProperties = loadPropertyFile("app.properties");

        PrintFactory printFactory = PrintFactory.getInstance();
        printFactory.setIp(appProperties.getProperty("print.ip"));
        printFactory.setPort(Integer.parseInt(appProperties.getProperty("print.port")));
        printFactory.setCompany(appProperties.getProperty("print.company"));
        //设置视图jsp
        me.setViewType(ViewType.JSP);

        // 设置视图目录
        me.setBaseViewPath("/WEB-INF/views/");

        // 设置错误页面
        me.setError404View("/error/404.jsp");
        //me.setError500View("/error/500.jsp");
        /**
         * 设置未授权
         */
        //me.setErrorView(401, "/WEB-INF/views/error/unauthorized.jsp");
        //me.setErrorView(403, "/WEB-INF/views/error/forbid.jsp");

        // 开启debug模式
        me.setDevMode(true);

    }

    /**
     * 配置路由
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        // 开启注解模式
        AutoBindRoutes routes = new AutoBindRoutes();
        me.add(routes);
    }

    /**
     * 配置插件
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        // 添加数据库连接池
        DruidPlugin dPlugin = new DruidPlugin(appProperties.getProperty("jdbc.url"), appProperties.getProperty("jdbc.username"), appProperties.getProperty("jdbc.password"), appProperties.getProperty("jdbc.driver"));
        dPlugin.addFilter(new StatFilter());
        WallFilter wall = new WallFilter();
        wall.setDbType(JdbcConstants.MYSQL);
        dPlugin.addFilter(wall);
        me.add(dPlugin);

        // 添加自动绑定model与表插件
        AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(dPlugin);
        autoTableBindPlugin.setDialect(new MysqlDialect());
        autoTableBindPlugin.setShowSql(true);
        me.add(autoTableBindPlugin);

        // 添加自定义插件
        AutoTableInfoPlugin autoTableInfoPlugin = new AutoTableInfoPlugin();
        me.add(autoTableInfoPlugin);
    }

    /**
     * 配置全局拦截器
     *
     * @param me
     */
    @Override
    public void configInterceptor(Interceptors me) {
        //me.add(new TxByRegex(".*save.*"));
        //me.add(new TxByActionMethods("save", "update", "add"));
//        me.add(new UserInterceptor());
        me.add(new AdminInterceptor());
        me.add(new ExceptionInterceptor());
    }

    @Override
    public void configHandler(Handlers me) {

    }

}
