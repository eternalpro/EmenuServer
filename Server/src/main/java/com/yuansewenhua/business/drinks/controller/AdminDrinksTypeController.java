package com.yuansewenhua.business.drinks.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.drinks.model.DrinksType;
import net.wincn.core.BaseController;

/**
 * Created by fangshuai on 2014-11-09-0009.
 */
@ControllerBind(controllerKey = "/admin/drinks/type", viewPath = "admin/drinks/type")
public class AdminDrinksTypeController extends BaseController<DrinksType> {

    @ActionKey("/admin/drinks/type")
    public void index(){
        list(DrinksType.dbKit);
    }

    public void form() {
        form("drinksType", DrinksType.dao);
    }

    public void save() {
        saveOrUpdate(null, DrinksType.class);
        renderText("success");
    }

    public void delete() {
        Integer id = getParaToInt();

        if(DrinksType.checkIfHasRecords(id)){
            renderText("分类下存在记录，删除失败！");
        }else {
            delete(DrinksType.dao, null);
            renderText("success");
        }
    }


    @Override
    protected boolean doAfterGetModel(DrinksType model) {
        return true;
    }

    @Override
    protected void doIfNoSave(DrinksType model) {

    }
}
