package com.yuansewenhua.config.entity;

import java.util.List;

/**
 * 菜单
 * Created by fangshuai on 2014-11-02-0002.
 */
public class Menu {
    private String title;
    private String url;
    private String flag;
    private String faIcon;
    private List<String> roles;
    private List<Menu> subMenus;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Menu> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<Menu> subMenus) {
        this.subMenus = subMenus;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", roles=" + roles +
                ", subMenus=" + subMenus +
                '}';
    }
}
