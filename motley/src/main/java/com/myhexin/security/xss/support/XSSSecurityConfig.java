/**
 *
 */
package com.myhexin.security.xss.support;

/**
 * @author winnie
 * 安全过滤配置信息类
 */
public class XSSSecurityConfig {

    /**
     * CHECK_HEADER：是否开启header校验
     */
    public static boolean IS_CHECK_HEADER;

    /**
     * CHECK_PARAMETER：是否开启parameter校验
     */
    public static boolean IS_CHECK_PARAMETER;

    /**
     * IS_LOG：是否记录日志
     */
    public static boolean IS_LOG;

    /**
     * IS_LOG：是否中断操作
     */
    public static boolean IS_CHAIN;

    /**
     * REPLACE：是否开启替换
     */
    public static boolean REPLACE;

    /**
     * XSS_ERROR_PAGE_PATH：检测到Xss攻击之后，跳转到的错误页；只有isChaina=true，此属性才生效
     */
    public static String XSS_ERROR_PAGE_PATH;
}
