/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.myhexin.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;

import com.myhexin.security.xss.support.XSSSecurityConfig;
import com.myhexin.security.xss.support.XSSSecurityManager;
import com.myhexin.util.PropertiesUtil;


/**
 * 
 * http文件上传请求,post上来的参数在servlet容器中使用request.getParameter是获取不到的，需要inputstream来读取。
 * 处理方式跟传统请求不一样。
 * 这个类专门在所有上传业务处理类之前，解析所有参数，进行文件过滤和post请求xss过滤
 * multipart/form-data
 * 
 * @author ljy
 */
public class FileMultipartFilter extends MultipartFilter {

	/**
	 * 是否开启文件过滤
	 */
	private static boolean FILE_FILTER_ISOPEN = Boolean.valueOf(PropertiesUtil.getPropertyValue("file.filter.isopen"));

	/**
	 * 文件过滤的正则表达式
	 */
	private static String FILE_FILTER_REGX = PropertiesUtil.getPropertyValue("file.filter.regx");

	private static Logger logger = LoggerFactory
			.getLogger(FileMultipartFilter.class);

	/**
	 * 改变bean默认的filterMultipartResolver名称；
	 * 跟DispatchServlet使用同一个MultipartResolver
	 */
	protected void initFilterBean() throws ServletException {
		super.initFilterBean();
		setMultipartResolverBeanName("multipartResolver");
	}
	
	/**
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		MultipartResolver multipartResolver = lookupMultipartResolver(request);

		HttpServletRequest processedRequest = request;
		if (multipartResolver.isMultipart(processedRequest)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Resolving multipart request ["
						+ processedRequest.getRequestURI()
						+ "] with MultipartFilter");
			}
			processedRequest = multipartResolver.resolveMultipart(processedRequest);

			if (XSSSecurityConfig.IS_CHECK_HEADER || XSSSecurityConfig.IS_CHECK_PARAMETER) {
				if(isIlleageXssParam((MultipartHttpServletRequest) processedRequest)){//非法，跳转到错误页面
					processedRequest.getRequestDispatcher(XSSSecurityConfig.XSS_ERROR_PAGE_PATH).forward( request, response);
	        		return;
				}
			}

			if (FILE_FILTER_ISOPEN) {
				if(isIllegalFileSuffix((MultipartHttpServletRequest) processedRequest)){//非法，跳转到错误页面
					processedRequest.getRequestDispatcher(XSSSecurityConfig.XSS_ERROR_PAGE_PATH).forward( request, response);
					return;
				}
			}

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("Request [" + processedRequest.getRequestURI()
						+ "] is not a multipart request");
			}
		}

		try {
			filterChain.doFilter(processedRequest, response);
		} finally {
			if (processedRequest instanceof MultipartHttpServletRequest) {
				multipartResolver
						.cleanupMultipart((MultipartHttpServletRequest) processedRequest);
			}
		}
	}

	/**
	 * 
	 * @param mRequest
	 * @return true:参数非法，false：参数正常
	 */
	public boolean isIlleageXssParam(MultipartHttpServletRequest mRequest) {

		// 开始header校验，对header信息进行校验
		if (XSSSecurityConfig.IS_CHECK_HEADER) {
			Enumeration<String> headerParams = mRequest.getHeaderNames();
			while (headerParams.hasMoreElements()) {
				String headerName = headerParams.nextElement();
				String headerValue = mRequest.getHeader(headerName);
				if (XSSSecurityManager.matches(headerValue)) {
					recordXssRequestInfo(mRequest, headerValue);
					return true;
				}
			}
			return false;
		}

		// 开始parameter校验，对parameter信息进行校验
		if (XSSSecurityConfig.IS_CHECK_PARAMETER) {
			Map<String, Object> submitParams = mRequest.getParameterMap();
			Set<String> submitNames = submitParams.keySet();
			for (String submitName : submitNames) {
				Object submitValues = submitParams.get(submitName);
				if (submitValues instanceof String) {
					if (XSSSecurityManager.matches((String) submitValues)) {
						recordXssRequestInfo(mRequest, submitName + "="
								+ submitValues);
						return true;
					}
				} else if (submitValues instanceof String[]) {
					for (String submitValue : (String[]) submitValues) {
						if (XSSSecurityManager.matches((String) submitValue)) {
							recordXssRequestInfo(mRequest, submitName + ""
									+ submitValue);
							return true;
						}
					}
				}
			}
			return false;
		}
		return false;
	}

	public boolean isIllegalFileSuffix(MultipartHttpServletRequest mRequest) {

		Map<String, MultipartFile> fileMap = mRequest.getFileMap();

		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet()
				.iterator(); it.hasNext();) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();

			String origFileName = mFile.getOriginalFilename();
			String name = mFile.getName();
			int a = origFileName.indexOf(".", -1);
			Pattern pattern = Pattern.compile(FILE_FILTER_REGX,
					Pattern.CASE_INSENSITIVE);
			if (a != -1) {
				String suffix = origFileName.substring(a + 1);
				if (pattern.matcher(suffix).matches()) {
					recordXssRequestInfo(mRequest, origFileName);
					return true;
				}else{
					return false;
				}

			} else {
				return false;
			}
		}
		return false;
	}

	private void recordXssRequestInfo(MultipartHttpServletRequest mRequest,
			String illegalParam) {
		Map<String, Object> submitParams = mRequest.getParameterMap();// get
																		// post请求的参数都能获取到
		Set<String> paramName = submitParams.keySet();

		String requestURL = mRequest.getRequestURL().toString();
		String questMethod = mRequest.getMethod();
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
		logger.warn("mulipart !!! " + questMethod + " " + requestURL + "  =="
				+ buffer.toString() + " illegal param	" + illegalParam);
	}

}
