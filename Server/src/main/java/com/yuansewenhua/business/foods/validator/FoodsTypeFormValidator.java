package com.yuansewenhua.business.foods.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

/**
 * Created by fangshuai on 2014-12-03-0003.
 */
public class FoodsTypeFormValidator extends Validator{

    @Override
    protected void validate(Controller c) {
        String noTitles = "酒水, 素菜, 凉菜, 荤菜, 热菜, 清真";
        String title = c.getPara("foodsType.title");
        if (noTitles.contains(title)) {
            validateToken("error", "名称禁止使用预留字符");
        }
    }

    @Override
    protected void handleError(Controller c) {
        c.renderText("名称禁止使用预留字符");
    }
}
