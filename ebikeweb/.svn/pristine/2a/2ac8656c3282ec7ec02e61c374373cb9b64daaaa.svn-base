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
<script type="text/javascript" src="<%=basePath %>static/js/libs/jquery-1.7.2.min.js" language="javascript"></script>
<script type="text/javascript" src="<%=basePath %>static/js/libs/jquery.placeholder.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/libs/ks-switch.js"></script>
<script type="text/javascript" src="<%=basePath %>static/js/backhome/backhome.js"></script>
<script type="text/javascript">
function layout(){
	  if (confirm("确认要退出？")) {
          var url = '<%=basePath %>userAction/layoutByPC';
          $.post(url,function(data){
        	  if(data.isSuccess){
        		  alert(data.message);
        		  window.parent.location.href=window.parent.location.href;
        	  }
          },"json");
      }
}
</script>
</head>

<body>
<div class="header">
	<div class="logo"><a href="<%=basePath %>userAction/index" target="_top" title="中联物流"><img src="<%=basePath %>/static/image/backhome/logo.png" alt="中联物流" /></a></div>
	<div class="nav">
        <div class="info">
        	用户名：<a href="javascript:;">${user.vcAccount }</a> 消息：<a href="<%=basePath %>messageAction/openMsgList" target="main">${msgCount }</a> 条未读  <a href="javascript:;"  onclick="layout();"> 退出系统！ </a>
        </div>    
    	<div class="menu">
            <ul>
                <li><a href="<%=basePath %>userAction/index"  target="_top" class="active">首页</a></li>
                <li><a href="reg.html" target="main">产品服务</a></li>
                <li><a href="info.html" target="main">店铺信息</a></li>
                <li><a href="order.html" target="main">金融服务</a></li>
                <li><a href="user.html" target="main">采购招标</a></li>
                <li><a href="resource.html" target="main">我要加盟</a></li>
                <li><a href="#">客户服务</a></li>
                <li><a href="#">联系我们</a></li>          
            </ul>        	
        </div>
    </div>
</div>
</body>
</html>