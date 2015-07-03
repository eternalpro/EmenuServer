package com.yuansewenhua.api.business.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.yuansewenhua.api.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gefangshuai on 2015/7/3.
 */
public class OrderCURDValidator extends Validator{
    @Override
    protected void validate(Controller c) {
        String json = JsonUtils.getJsonFromController(c);
        if(StringUtils.isBlank(json))
            c.renderText("json is not allow null!");
    }

    @Override
    protected void handleError(Controller c) {

    }
}
