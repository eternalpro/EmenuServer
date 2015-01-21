package com.yuansewenhua.utils;

import com.yuansewenhua.config.entity.App;
import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppUtils {
    private static Logger logger = Logger.getLogger(AppUtils.class);
    private static InputStream loadResource(String location) throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        return resource.getInputStream();
    }

	public static Properties loadAppProperties(){

		Properties properties = new Properties();
		try {
			properties.load(loadResource("classpath:app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

    public static App loadAppConfig() {
        App app = null;
        Yaml yaml = new Yaml(new Constructor(App.class));
        try {
            app = (App) yaml.load(loadResource("classpath:app-config.yml"));
        } catch (IOException e) {
            logger.debug("加载配置文件失败！请联系管理员");
            e.printStackTrace();
        }
        return app;
    }

    /**
     *加密一个字符串
     * @param sourceString 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encode(String sourceString){
        if(sourceString == null){
            return null;
        }
        byte[] testbyte = sourceString.getBytes();
        int length = testbyte.length;
        byte[] result = new byte[length];
        for(int i = 0;i<length;i++){
            result[i] = (byte)(testbyte[i]-10);
        }
        return new String(result);
    }

    /**
     *解密一个字符串
     * @param passString 要解密的字符串
     * @return 解密后的字符串
     */
    public static String decode(String passString){
        if(passString == null){
            return null;
        }
        byte[] testbyte = passString.getBytes();
        int length = testbyte.length;
        byte[] result = new byte[length];
        for(int i = 0;i<length;i++){
            result[i] = (byte)(testbyte[i]+10);
        }
        return new String(result);
    }

    /**
     * 根据传递的排序获得相反的排序
     * @param sort
     * @return
     */
    public static String getReverseSort(String sort) {
        return sort.equals("desc") ? "asc" : "desc";
    }

}
