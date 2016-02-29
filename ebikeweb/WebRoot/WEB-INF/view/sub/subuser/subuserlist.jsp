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
			  url:'<%=basePath %>subAdminStaffAction/pageList',
			    cache : false,
			    type : 'post',
			    title:'登陆用户管理', 
			    width:w,
			    height:355,
			   // pageSize:size,
			    fitColumns:true,
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			    rownumbers:true,   //如果为true，则显示一个行号列。
			    striped:true,  //striped  是否显示斑马线效果。
			    sortName:'id',
			    sortOrder:'desc',
			    loadMsg:'信息加载中……',
			    columns:[[     
			        {field:'ID',title:'id',checkbox:true,width:100},     
			        {field:'VC_USERNAME',title:'用户名',width:100,align:'left'},     
			        {field:'VC_ACCOUNT',title:'登录名',width:100,align:'left'}, 
			        {field:'DT_ADDTIME',title:'添加时间',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
	                        return unixTimestamp.toLocaleString();
			        	}
			        },   
			        {field:'I_ARCHIVE_TYPE',title:'所属档案',width:100,align:'left',formatter:function(value,index){
			        	if(value == 1){
			        		return "内部";
			        	}
			        	if(value == 2){
			        		return "分供方";
			        	}
			        	if(value == 3){
			        		return "司机";
			        	}
			        	if(value == 4){
			        		return "金融公司";
			        	}
			        	
			        }},
			        {field:'N_ENABLE',title:'有效性',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		if(value == 1){
				        		return "无效";
				        	}
				        	if(value == 0){
				        		return "有效";
				        	}
			        	}
			        }, 
			        {field:'opt',title:'操作',width:100,align:'center',  
	                    formatter:function(value,rec,index){  
	                        var s = '<a href="javascript:;"  onclick="power(\''+ rec.ID + '\')">授  权</a> ';  
	                        return s;  
	                    }  
	                  }  
			    ]],
			     onLoadError:function(){
	                      alert("加载数据失败！");
	                  }
			});   
		});
	
	
	//授权
	function power(userid){
		var url = "<%=basePath%>subAdminStaffAction/giveRol?userId="+userid;
		$.webox({
			height:400,
			width:350,
			bgvisibel:true,
			title:'用户授权',
			iframe:url
		});
	}
	
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var userName1 = $("#userName").val();
		if(null!=userName1){
			jsonStr.userName=userName1;
		}
		var vcAccount1 = $("#vcAccount").val();
		if(null!=vcAccount1){
			jsonStr.vcAccount = vcAccount1;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	//禁用用户
	function disableUser(){
		var selected = $("#dg").datagrid('getSelections');
		if(selected.length==0){
			alert("请至少选择一条");
			return false;
		}
		var ids=[];
		for(var i=0;i<selected.length;i++){
			var id = selected[i].ID;
			ids.push(id);
		}
		if(confirm("确认要禁用所选记录吗？")){
			
			$.post("<%=basePath%>subAdminStaffAction/delUser",  
					{"array[]":ids},     
					   function (data, textStatus)
					   {     
							if(data.isSuccess == true)
							{
							
								$.messager.show({ // show error message
									title : '提示',
									msg :"删除成功"
								});
								$('#dg').datagrid('reload'); 
							}else
							{
								alert(data.message);
							}
					   }
				  ,"json");
		}
			
	}

	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  $('#dg').datagrid('reload'); 
		  }
		}
	//打开新增页面
	function openAdd(){
		var url = "<%=basePath%>subAdminStaffAction/openAddUser";
		$.webox({
			height:380,
			width:580,
			bgvisibel:true,
			title:'新增用户',
			iframe:url
		});
	}
	//编辑
	function openEdite(){
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>subAdminStaffAction/openAddUser?userId="+selected.ID;
		     	$.webox({
					height:380,
					width:580,
					bgvisibel:true,
					title:'编辑用户',
					iframe:url
				});
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
	}
	
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
        <input onclick="openAdd();" type="button" class="f_btn2" value="新增" /> 
        <input onclick="openEdite();" type="button" class="f_btn2" value="修改密码" />
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