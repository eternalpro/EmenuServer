package com.yuansewenhua.business.foods.controller;

import com.google.common.base.Joiner;
import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.upload.UploadFile;
import com.yuansewenhua.business.GoodsAttributes;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.foods.model.Food;
import com.yuansewenhua.business.foods.model.FoodsType;
import net.wincn.core.BaseController;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-04-0004.
 */
@ControllerBind(controllerKey = "/admin/foods", viewPath = "admin/foods")
public class AdminFoodsController extends BaseController<Food> {
    private static String PIC_DIR = "foods";

    @ActionKey("/admin/foods")
    public void index() {
        Integer typeId = getParaToInt(0);
        List<FoodsType> foodsTypeList = FoodsType.dbKit.listRecord("desc");
        params.put("foodstypeid", typeId);
        setAttr("foodsTypes", foodsTypeList);
        searchAndPaginate("name", Food.dbKit);
        setAttr("foodstypeid", typeId);
    }

    public void form() {
        Integer foodstypeid = getParaToInt("foodstypeid");
        if (foodstypeid != null) {
            FoodsType foodsType = FoodsType.dao.findById(foodstypeid);
            setAttr("foodsType", foodsType);
        }
        form("food", Food.dao);
        List<FoodsType> foodsTypeList = FoodsType.dbKit.listRecord("desc");

        GoodsAttributes.FoodAttributesEnum[] foodAttributesEnums = GoodsAttributes.FoodAttributesEnum.values();
        setAttr("foodAttributesEnums", foodAttributesEnums);
        setAttr("foodsTypes", foodsTypeList);
    }

    public void modifytype(){
        Integer foodId = getParaToInt();
        Integer foodstypeid = getParaToInt("foodstypeid");
        if (foodstypeid != null) {
            Food food = Food.dao.findById(foodId);
            food.set("foodstypeid", foodstypeid);
            food.set("typetitle", FoodsType.dao.findById(foodstypeid).getStr("title"));
            food.update();
        }
        redirect("/admin/foods");
    }

    public void save() {
        UploadFile smallFile = getFile("smallpath", PIC_DIR);
        UploadFile largeFile = getFile("largepath", PIC_DIR);

        String smallpath = smallFile == null ? "/resources/img/no-cover.jpg" : "/" + "upload" + "/" + PIC_DIR + "/" + smallFile.getFileName();
        String largepath = largeFile == null ? "/resources/img/no-cover.jpg" : "/" + "upload" + "/" + PIC_DIR + "/" + largeFile.getFileName();
        Food food = getModel(Food.class);

        String[] attributes = getParaValues("attribute");
        if (attributes!=null) {
            String attribute = Joiner.on(",").join(attributes);
            food.set("attribute", attribute);
        }else{
            food.set("attribute", null);
        }

        FoodsType type = FoodsType.dao.findById(food.getInt("foodstypeid"));
        food.set("typetitle", type.getStr("title"));
        Integer id = food.getInt("id");
        if (id != null && id != 0) { // 更新操作
            if (smallFile != null) food.set("smallimagepath", smallpath);
            if (largeFile != null) food.set("bigimagepath", largepath);
            food.update();
        } else {  // 添加
            food.set("smallimagepath", smallpath);
            food.set("bigimagepath", largepath);
            food.save();
        }
        renderText("success");
    }

    public void delete() {
        delete(Food.dao, null);
        renderText("success");
    }

    public void clear() {
        Integer id = getParaToInt(0);
        String isenable = getPara(1);
        Food food = Food.dao.findById(id);
        food.set("isenable", isenable).update();
        renderText("success");
    }

    @Override
    protected boolean doAfterGetModel(Food model) {
        return true;
    }

    @Override
    protected void doIfNoSave(Food model) {

    }
}
