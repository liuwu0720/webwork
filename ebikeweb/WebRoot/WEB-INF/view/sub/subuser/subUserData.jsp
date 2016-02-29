<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	//var h = getHeight('dg');
	//var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
			  url:'<%=basePath %>subAdminStaffAction/subUserDataList',
			    cache : false,
			    type : 'post',
			    title:'用户个人管理', 
			    width:w,
			    height:355,
			   // pageSize:size,
			    fitColumns:true,
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			    rownumbers:true,   //如果为true，则显示一个行号列。
			    striped:true,  //striped  是否显示斑马线效果。
			    sortOrder:'desc',
			    loadMsg:'信息加载中……',
			    columns:[[     
			        {field:'ID',title:'id',checkbox:true,width:100},     
			        {field:'VC_USERNAME',title:'员工姓名',width:100,align:'left'},     
			        {field:'VC_USERCARDID',title:'员工身份证号码',width:100,align:'left'}, 
			        {field:'VC_CONTACT',title:'联系方式',width:100,align:'left'},   
			        {field:'VC_DEPT',title:'所属部门',width:100,align:'left'},
			        {field:'DT_JOIN',title:'入职日期',width:100,align:'left'}
			      
			    ]],
			     onLoadError:function(){
	                      alert("加载数据失败！");
	             }
			});   
		});
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	    var h = getHeight('dg');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size });
	}
	
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>用户管理</span></a></dt>
        <dd >
        <div class="batch">
        <input onclick="userEdite();" type="button" class="f_btn2" value="修改个人信息" />
        <input onclick="disableUser();" type="button" class="f_btn2" value="禁用" /></div>
        <div class="search">
			用户名：<input name="userName" id="userName" class="f_txt" /> 账号：<input class="f_txt"  id="vcAccount" name="vcAccount" />
			<input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
		<table  id="dg" ></table>
		</dd>
    </dl>
</body>
</html>