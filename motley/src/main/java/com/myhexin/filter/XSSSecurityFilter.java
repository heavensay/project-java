/**
 *
 */
package com.myhexin.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.myhexin.security.xss.support.XSSHttpRequestWrapper;
import com.myhexin.security.xss.support.XSSSecurityConfig;
import com.myhexin.security.xss.support.XSSSecurityManager;


/**
 * @author winnie
 * @date
 * @describe 安全信息审核类
 */
public class XSSSecurityFilter implements Filter {

    private static Logger logger = Logger.getLogger(XSSSecurityFilter.class);

    /**
     * 销毁操作
     */
    public void destroy() {
        logger.info("XSSSecurityFilter destroy() begin");
        XSSSecurityManager.destroy();
        logger.info("XSSSecurityFilter destroy() end");
    }

    /**
     * 安全审核
     * 读取配置信息
     */
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 判断是否使用HTTP
        checkRequestResponse(request, response);
        // 转型
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // http信息封装类
        XSSHttpRequestWrapper xssRequest = new XSSHttpRequestWrapper(httpRequest);

        // 对request信息进行封装并进行校验工作，若校验失败（含非法字符），根据配置信息进行日志记录和请求中断处理
        if (xssRequest.isIllegalParam(httpResponse)) {
            if (XSSSecurityConfig.IS_LOG) {
                // 记录攻击访问日志
                // 可使用数据库、日志、文件等方式
                recordXssRequestInfo(httpRequest);
            }
            if (XSSSecurityConfig.IS_CHAIN) {
                httpRequest.getRequestDispatcher(XSSSecurityConfig.XSS_ERROR_PAGE_PATH).forward(httpRequest, httpResponse);
                return;
            }
        }

        chain.doFilter(xssRequest, response);
    }

    /**
     * 初始化操作
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        XSSSecurityManager.init(filterConfig);
    }

    /**
     * 判断Request ,Response 类型
     * @param request
     *            ServletRequest
     * @param response
     *            ServletResponse
     * @throws ServletException
     */
    private void checkRequestResponse(ServletRequest request,
                                      ServletResponse response) throws ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("Can only process HttpServletRequest");

        }
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("Can only process HttpServletResponse");
        }
    }

    private void recordXssRequestInfo(HttpServletRequest httpRequest) {
        Map<String, Object> submitParams = httpRequest.getParameterMap();//get post请求的参数都能获取到
        Set<String> paramName = submitParams.keySet();

        String requestURL = httpRequest.getRequestURL().toString();
        String questMethod = httpRequest.getMethod();
        StringBuffer buffer = new StringBuffer();
        for (String pn : paramName) {
            Object paramValues = submitParams.get(pn);

            if (paramValues instanceof String[]) {
                buffer.append(pn + "=");
                for (String submitValue : (String[]) paramValues) {
                    buffer.append(submitValue + " ");
                }
                buffer.append(" \\ ");
            } else {
                buffer.append(pn + "=" + paramValues + " \\ ");
            }
        }
        logger.warn("xss attack!!! " + questMethod + " " + requestURL + "  ==" + buffer.toString());
    }
}
