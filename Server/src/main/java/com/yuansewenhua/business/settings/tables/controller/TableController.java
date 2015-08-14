package com.yuansewenhua.business.settings.tables.controller;

import com.jfinal.core.Controller;
import com.jfinal.ext.route.ControllerBind;
import com.yuansewenhua.business.settings.tables.model.Table;

import java.util.List;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/14.
 */
@ControllerBind(controllerKey = "/admin/settings/tables", viewPath = "admin/settings/table")
public class TableController extends Controller{
    public void index(){
        List<Table> tables = Table.dao.findAll();
        setAttr("tables", tables);
    }

    public void form(){

    }

    public void save() {

        Table table = getModel(Table.class);
        Table dbTable = Table.dao.findFirst("select * from tables where name = ?", table.getStr("name"));
        if (dbTable != null) {
            renderText("台号名称已存在！");
        }else{
            int maxNo = Table.dao.getMaxOrderNo();
            table.set("ordno", maxNo + 1);
            table.save();
            renderText("success");
        }
    }

    public void delete() {
        Integer id = getParaToInt();
        Table.dao.deleteById(id);
        renderText("success");
    }

    public void changeOrder() {
        int sid = getParaToInt(0);
        int tid = getParaToInt(1);

        Table tableSource = Table.dao.findById(sid);
        Table tableTarget = Table.dao.findById(tid);

        int sourceNo = tableSource.getInt("ordno");
        int targetNo = tableTarget.getInt("ordno");

        tableSource.set("ordno", targetNo).update();
        tableTarget.set("ordno", sourceNo).update();

        renderText("success");
    }
}
