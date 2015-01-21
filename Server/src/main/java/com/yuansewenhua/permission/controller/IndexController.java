package com.yuansewenhua.permission.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;

/**
 * Created by fangshuai on 2014-11-01-0001.
 */
public class IndexController extends Controller{

    @ActionKey("/")
    public void index(){
        redirect("/admin/login");
    }
}
