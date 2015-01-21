package com.yuansewenhua.business.settings.systems.model;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.DBKit;
import net.wincn.core.TableInfo;

/**
 * Created by fangshuai on 2014-12-03-0003.
 */
@TableInfo(pkType = Integer.class)
@TableBind(tableName = "system", pkName = "id")
public class SystemInfo extends Model<SystemInfo>{
    public static SystemInfo dao = new SystemInfo();
    public static DBKit<SystemInfo> dbKit = new DBKit<>(dao);

}
