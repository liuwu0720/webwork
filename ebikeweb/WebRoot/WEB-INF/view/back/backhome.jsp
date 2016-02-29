<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中联物流股份有限公司</title>
<link href="<%=basePath %>static/css/backhome.css" rel="stylesheet" type="text/css" />

</head>
<frameset rows ="112px,*">
  	<frame noresize="noresize" frameborder="0"  scrolling="no" name="header" id="header" src="<%=basePath %>back/getHeader"/>
    <frameset id="frameset" cols="200px,10,*">
        <frame noresize="noresize" frameborder="0" scrolling="auto"  id="sidebar" name="sidebar" src="<%=basePath %>back/getSidebar">
        <frame noresize="noresize" frameborder="0" scrolling="no"  name="control" id="control" src="<%=basePath %>back/getControl">
        <frame noresize="noresize" frameborder="0" scrolling="auto" id="main" name="main" src="<%=basePath %>back/getMain">
    </frameset>
</frameset><noframes></noframes>
</html>