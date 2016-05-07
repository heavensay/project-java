<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path ;
	String content = request.getParameter("content");  
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
  <!-- 
	<script>alert('xss attacked 1')</script>
	 -->
	<%=content%>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is xss test page. <br>
    
    <a href="hello">hello <%=content%></a>
		<form action="<%=basePath%>/xss/upload?name='中国abc'" method="post"  enctype="multipart/form-data" >
			用户名:
			<input type="text" name="username" ><%=content%></input>
			<br />
			
			选择文件1<input type="file" name="fileNames">
			<!-- 
			选择文件2<input type="file" name="fileNames">
			 -->
			<br />
			<input type="submit" value="上传文件" />
		</form>
	</body>
	
	<a href="<%=basePath%>/page/xss.jsp">xss附件下载执行</a>
</html>
