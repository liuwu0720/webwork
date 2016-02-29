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
<title>用户日志</title>

<link href="<%=basePath%>static/css/jquery-webox.css" rel="stylesheet"
	type="text/css" />
<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
		$('#dg').datagrid({     
		    url:'<%=basePath %>userlogAction/getUserLog',
		    cache : false,
		    type : 'post',
		    title:'用户日志', 
		    width:w,
		    height:h,
		    pageSize:size,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
		        {field:'vcUserName',title:'用户名',width:100,align:'left'},     
		        {field:'vcIp',title:'ip',width:100,align:'left'},
		        {field:'vcDesc',title:'访问描述',width:100,align:'left'},
		        {field:'dtAdd',title:'时间',width:100,align:'left',
		        	formatter:function(value,rec,index){
		        		var unixTimestamp = new Date(value);  
                        return unixTimestamp.toLocaleString();
		        	}
		        }
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
		});   
	
	});
	
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var userName = $("#userName").val();
		if(null!=userName && ""!=userName){
			jsonStr.userName = userName;
		}
		var startDate = $("#startDate").val();
		if(null!=startDate && ""!=startDate){
			jsonStr.startDate = startDate;
		}
		var endDate = $("#endDate").val();
		if(null!=endDate && ""!=endDate){
			jsonStr.endDate = endDate;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	function resizeGrid(minWidth){
		
		var t =getWidth(minWidth);
	    var h = getHeight('dg');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size });
		    
	}
	
	
	
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dt><a href="#"><span>用户日志</span></a></dt>
        <dd class="form">
        
			<div class="search">
			用户名称：<input name="userName" class="f_txt" id="userName" /> 开始时间：<input  id="startDate" name="startDate"  class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
			结束时间：<input  id="endDate" name="endDate" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" />
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
		<table  id="dg"   style="width:600px;height:500px"  ></table>
			
		</dd>
    </dl>
</body>
</html>