<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中联物流股份有限公司</title>
<script type="text/javascript" src="static/js/libs/jquery-1.7.2.min.js" ></script>
<%@ include file="./include/datagrid.jsp"  %>
</head>

<script type="text/javascript">

</script>
<body>
<h>1233444555</h>
<div  id="tt" style="height:auto;width:auto" ></div >
 <table id="tt" class="easyui-datagrid" style="width:700px;height:250px"
            url="userAction/pageList"
            title="Load Data" iconCls="icon-save"
            rownumbers="true" pagination="true">
        <thead>
            <tr>
                <th field="vcAccount" width="80" sortable="true">Item ID</th>
                <th field="vcPassword" width="120">Product ID</th>
            </tr>
        </thead>
    </table>
</body>
</html>
