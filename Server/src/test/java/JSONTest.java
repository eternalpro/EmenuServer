import com.google.gson.Gson;
import com.yuansewenhua.api.business.bean.OrderBean;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by gefangshuai on 2015/7/2.
 */
public class JSONTest {
    @Test
    public void testGson() throws IOException {
        String json = FileUtils.readFileToString(new File("C:\\Users\\gefan\\Desktop\\OrderJSON.txt"));
        Gson gson = new Gson();

        OrderBean orderBean = gson.fromJson(json, OrderBean.class);
    }
}
