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

//        if (drinks.getBoolean("havesugar") == null)
//            drinks.set("havesugar", false);
//        if (drinks.getBoolean("cancold") == null)
//            drinks.set("cancold", false);
//        if (drinks.getBoolean("canhot") == null)
//            drinks.set("canhot", false);

        String[] prices = getParaValues("prices");
        String[] sellunits = getParaValues("sellunits");

        drinks.set("price", StringUtils.join(prices, ","));

        String su = "";
        for (int i = 0; i < prices.length; i++) {
            String str = "瓶";
            if (StringUtils.isNoneBlank(sellunits[i]))
                str = sellunits[i];
            su = su + str + ",";
        }
        su = su.substring(0, su.length() - 1);
        drinks.set("sellunit", su);

        DrinksType type = DrinksType.dao.findById(drinks.getInt("drinkstypeid"));
        drinks.set("typetitle", type.getStr("title"));

        Integer id = drinks.getInt("id");
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
