<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>买保险页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="../../include/common.jsp" %>
	<%@ include file="../../include/datagrid.jsp"  %>
	<script type="text/javascript">
	
	</script>
  </head>
  <body>
    <dl>
    	<dt></dt>
    	<dd></dd>
    </dl>
  </body>
</html>