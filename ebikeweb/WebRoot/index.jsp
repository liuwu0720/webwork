<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My age</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	function ajjson(){
		var url = "${pageContext.request.contextPath}/test/ajaxjson";
		$.post(url,function(data){
			alert(data);
		},"text");
	}
	
	
	</script>
	
  </head>
  
  <body>
    This is my JSP page. <br>

    <input type="button" value="点击1111111111" onclick="ajjson();return false;" />
  </body>
</html>
