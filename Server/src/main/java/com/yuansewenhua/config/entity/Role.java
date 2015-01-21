package com.yuansewenhua.config.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 角色
 * Created by fangshuai on 2014-11-02-0002.
 */
@XStreamAlias(value = "role")
public class Role {
    private String name;
    private String title;
    private String targeturl;
    private boolean isenable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    public boolean isIsenable() {
        return isenable;
    }

    public void setIsenable(boolean isenable) {
        this.isenable = isenable;
    }


    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", targeturl='" + targeturl + '\'' +
                ", isenable=" + isenable +
                '}';
    }
}
