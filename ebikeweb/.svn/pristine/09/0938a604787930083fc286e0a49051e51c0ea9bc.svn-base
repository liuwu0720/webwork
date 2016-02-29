<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath %>/static/css/backhome.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/static/js/libs/jquery-1.6.4.min.js" language="javascript"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/libs/jquery.placeholder.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/libs/ks-switch.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/backhome/backhome.js"></script>
</head>

<body class="main">
	<dl class="tabs">
    	<dt><a href="#"><span>基本信息</span></a><a href="#"><span>收入统计</span></a></dt>
        <dd>
			本月发运次数：20<br />
			未结算订单：12
        </dd>
        
        <dd>
        	<form action="<%=basePath %>share/push" method="post">
        	<button type="submit">submit</button>
        	</form>
        </dd>
    </dl>
</body>
</html>