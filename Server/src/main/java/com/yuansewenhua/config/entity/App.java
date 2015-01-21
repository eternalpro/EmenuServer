package com.yuansewenhua.config.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 系统配置映射类
 * Created by fangshuai on 2014-11-02-0002.
 */
@XStreamAlias(value = "app")
public class App {
    private String title;
    private String version;
    private List<Menu> menus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "App{" +
                "title='" + title + '\'' +
                ", version='" + version + '\'' +
                ", menus=" + menus +
                '}';
    }
}
