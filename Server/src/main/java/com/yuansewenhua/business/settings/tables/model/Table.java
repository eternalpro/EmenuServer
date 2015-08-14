package com.yuansewenhua.business.settings.tables.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/8/14.
 */
@TableBind(tableName = "tables", pkName = "id")
public class Table extends Model<Table> {
    public static final Table dao = new Table();

    public List<Table> findAll() {
        return dao.find("select * from tables order by ordno asc");
    }

    public int getMaxOrderNo() {
        Integer orderNo = dao.findFirst("select max(ordno) orderno from tables").getInt("orderno");
        return orderNo == null ? 0 : orderNo;
    }

    public static List<String> toNames(List<Table> tables) {
        List<String> names = new ArrayList<>();
        for (Table table : tables) {
            names.add(table.getStr("name"));
        }
        return names;
    }

    public List<Table> findUnused() {
        return dao.find("select * from tables where name not in (select tablenumber from orders o where o.status = 'UNPAY')");
    }

    public List<Table> findUsed() {
        return dao.find("select * from tables where name in (select tablenumber from orders o where o.status = 'UNPAY')");
    }
}
