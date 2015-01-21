package com.yuansewenhua.business.foods.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.foods.model.FoodsType;
import com.yuansewenhua.business.foods.validator.FoodsTypeFormValidator;
import net.wincn.core.BaseController;

/**
 * Created by fangshuai on 2014-11-09-0009.
 */
@ControllerBind(controllerKey = "/admin/foods/type", viewPath = "admin/foods/type")
public class AdminFoodsTypeController extends BaseController<FoodsType> {

    @ActionKey("/admin/foods/type")
    public void index() {
        list(FoodsType.dbKit);
    }

    public void form() {
        form("foodsType", FoodsType.dao);
    }

    @Before(value = FoodsTypeFormValidator.class)
    public void save() {
        try {
            saveOrUpdate(null, FoodsType.class);
        } catch (Exception e) {
            renderText(e.getMessage());
            return;
        }
        renderText("success");
    }


    public void delete() {
        Integer id = getParaToInt();

        if (FoodsType.checkIfHasRecords(id)) {
            renderText("分类下存在记录，删除失败！");
        } else {
            delete(FoodsType.dao, null);
            renderText("success");
        }

    }

    @Override
    protected boolean doAfterGetModel(FoodsType model) {
        return true;
    }

    @Override
    protected void doIfNoSave(FoodsType model) {

    }
}
