package com.yuansewenhua.business.settings.systems.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.systems.model.SystemInfo;
import net.wincn.core.BaseController;

/**
 * Created by fangshuai on 2014-12-07-0007.
 */
@ControllerBind(controllerKey = "/admin/settings/systems", viewPath = "admin/settings/systems")
public class AdminSettingsSystemController extends BaseController<SystemInfo>{


    @ActionKey("/admin/settings/systems")
    public void index() {
        list("key" ,SystemInfo.dbKit);
    }

    public void form() {
        form("system", SystemInfo.dao);
    }

    public void save() {
        saveOrUpdate(null, SystemInfo.class);
        renderText("success");
    }

    public void delete() {
        delete(SystemInfo.dao, null);
        renderText("success");
    }

    @Override
    protected boolean doAfterGetModel(SystemInfo model) {
        return true;
    }

    @Override
    protected void doIfNoSave(SystemInfo model) {

    }
}
