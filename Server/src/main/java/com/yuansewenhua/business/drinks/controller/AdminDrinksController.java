package com.yuansewenhua.business.drinks.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.ext.route.ControllerBind;
import com.jfinal.upload.UploadFile;
import com.yuansewenhua.business.drinks.model.Drinks;
import com.yuansewenhua.business.drinks.model.DrinksType;
import net.wincn.core.BaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by fangshuai on 2014-11-04-0004.
 */
@ControllerBind(controllerKey = "/admin/drinks", viewPath = "admin/drinks")
public class AdminDrinksController extends BaseController<Drinks> {
    private static String PIC_DIR = "drinks";

    @ActionKey("/admin/drinks")
    public void index() {
        Integer typeId = getParaToInt(0);
        List<DrinksType> drinksTypeList = DrinksType.dbKit.listRecord("desc");
        params.put("drinkstypeid", typeId);
        setAttr("drinksTypes", drinksTypeList);
        searchAndPaginate("name", Drinks.dbKit);
        setAttr("drinkstypeid", typeId);
    }

    public void form() {
        Integer drinkstypeid = getParaToInt("drinkstypeid");
        if (drinkstypeid != null) {
            DrinksType drinksType = DrinksType.dao.findById(drinkstypeid);
            setAttr("drinksType", drinksType);
        }
        form("drinks", Drinks.dao);
        List<DrinksType> drinksTypeList = DrinksType.dbKit.listRecord("desc");
        setAttr("drinksTypes", drinksTypeList);
    }

    public void modifytype() {
        Integer drinksId = getParaToInt();
        Integer drinkstypeid = getParaToInt("drinkstypeid");
        if (drinkstypeid != null) {
            Drinks drinks = Drinks.dao.findById(drinksId);
            drinks.set("drinkstypeid", drinkstypeid);
            drinks.set("typetitle", DrinksType.dao.findById(drinkstypeid).getStr("title"));
            drinks.update();
        }
        redirect("/admin/drinks");
    }

    public void save() {
        UploadFile smallFile = getFile("smallpath", PIC_DIR);
        UploadFile largeFile = getFile("largepath", PIC_DIR);

        String smallpath = smallFile == null ? "/resources/img/no-cover.jpg" : "/" + "upload" + "/" + PIC_DIR + "/" + smallFile.getFileName();
        String largepath = largeFile == null ? "/resources/img/no-cover.jpg" : "/" + "upload" + "/" + PIC_DIR + "/" + largeFile.getFileName();
        Drinks drinks = getModel(Drinks.class);

        DrinksType type = DrinksType.dao.findById(drinks.getInt("drinkstypeid"));
        drinks.set("typetitle", type.getStr("title"));

        Integer id = drinks.getInt("id");

        if (Drinks.dao.findExistByName(drinks.getStr("name"), id) != null) {
            renderText("名称已存在！");
        }else {
            if (id != null && id != 0) { // 更新操作
                if (smallFile != null) drinks.set("smallimagepath", smallpath);
                if (largeFile != null) drinks.set("bigimagepath", largepath);
                drinks.update();
            } else {  // 添加
                drinks.set("smallimagepath", smallpath);
                drinks.set("bigimagepath", largepath);
                drinks.save();
            }
            renderText("success");
        }
    }

    public void delete() {
        delete(Drinks.dao, null);
        renderText("success");
    }


    @Override
    protected boolean doAfterGetModel(Drinks model) {
        return true;
    }

    @Override
    protected void doIfNoSave(Drinks model) {

    }
}
