package com.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * 文件获取工具类
 */
public class PropertyUtil {
    private static Logger logger = Logger.getLogger(PropertyUtil.class);

    private static Properties props;

    static {
        loadProps();
    }

    synchronized static private void loadProps() {
        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        String dbFile = "db.properties";
        InputStream in = null;
        try {
            // 第一种，通过类加载器进行获取properties文件流
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(dbFile);
            // 第二种，通过类进行获取properties文件流
            // in = PropertyUtil.class.getResourceAsStream("/db.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error(dbFile + "文件未找到:" + e.getMessage());
        } catch (IOException e) {
            logger.error("出现IOException:" + e.getMessage());
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(dbFile + "文件流关闭出现异常" + e.getMessage());
            }
        }
        logger.info(dbFile + "加载文件内容完成...........");
        logger.info(dbFile + "文件内容：" + props);
    }

    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
}