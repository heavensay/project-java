/*
 * Copyright 2011-2015 10jqka.com.cn All right reserved. This software is the confidential and proprietary information
 * of 10jqka.com.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered into with 10jqka.com.cn.
 */
package com.myhexin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类PropertiesUtil.java的实现描述：读取配置文件Util
 *
 * @author wangjie 2013-2-23 下午03:48:42
 */
public class PropertiesUtil {

    private static Properties properties = new Properties();

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    //	private static String DEFAULT_ENCODING = "UTF-8";
    static {
        loadProperties("common.properties");
    }


    public static String getPropertyValue(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

    /**
     *
     */
    public static void loadProperties(String filePath) {
        InputStream is;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
            properties.load(is);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("properties parse error!", e);
        }
    }
}
