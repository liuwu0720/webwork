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
<title>角色管理</title>

<link href="<%=basePath%>static/css/jquery-webox.css" rel="stylesheet"
	type="text/css" />
<%@include file="../include/common.jsp" %>
<%@ include file="../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=resizeGrid(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>seckillAction/getSecKillList',
		    cache : false,
		    type : 'post',
		    title:'抢单列表', 
		    width:w,
		    height:400,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true},     
		        {field:'vcKillno',title:'抢单编号',width:100,align:'left'},     
		        {field:'nminPrice',title:'最高价',width:100,align:'right'},
		        {field:'vcStart',title:'起始点',width:100,align:'left'},
		        {field:'vcEnd',title:'目的地',width:100,align:'left'},
		        {field:'dtArriveTime',title:'抵达时间',width:100,align:'left',
		        	formatter:function(value,rec,index){
		        		var unixTimestamp = new Date(value);  
		        		return unixTimestamp.toLocaleDateString()
		        	}},
		        {field:'dtKillStart',title:'开始时间',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
	                        return unixTimestamp.toLocaleString();
			        	}},
		        {field:'dtKillEnd',title:'结束时间',width:100,align:'left',
				        	formatter:function(value,rec,index){
				        		var unixTimestamp = new Date(value);  
		                        return unixTimestamp.toLocaleString();
				        	}},
		        {field:'opt',title:'操作',width:100,align:'center',  
                    formatter:function(value,rec,index){  
                        var s = '<a href="javascript:;"  onclick="power(\''+ rec.id + '\')">关联资源</a> ';  
                        var e = '<a href="javascript:;"   onclick="openAdd(\''+ rec.id + '\')">编辑</a> ';  
                        return s+e;  
                    }  
                  }  
		       
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
		});   
	
	});
	
	
	//关联资源
	function power(roleId){
		var url = "<%=basePath%>role/getRoleResource?roleId="+roleId;
		$.webox({
			height:580,
			width:800,
			bgvisibel:true,
			title:'关联资源',
			iframe:url
	});
	}
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var roleName = $("#vcRoleName").val();
		if(null!=roleName && ""!=roleName){
			jsonStr.vcRoleName = roleName;
		}
		var role = $("#vcRole").val();
		if(null!=role && ""!=role){
			jsonStr.vcRole = role;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	//删除
	function del(){
		var checkedItems = $('#dg').datagrid('getChecked');
		var ids = [];
		$.each(checkedItems, function(index, item){
			ids.push(item.id);
		});               
		if(ids.length>0){
			var roleIds = ids.join(",");
			var url = "<%=basePath%>role/delRole";
			$.post(url,{"roleIds":roleIds},function(data){
				showResponse(data);
			});
		}else{
			alert("请选择要删除的记录！");
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

	function openAdd(id){
		var url = "<%=basePath%>role/getByid";
		var tip = "新增角色";
		if(null!=id && ""!=id){
			url = url+"?roleId="+id;
			tip = "编辑角色"
		}
		
		$.webox({
			height:580,
			width:800,
			bgvisibel:true,
			title:tip,
			iframe:url
		});
	}
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	   
	    $('#dg').datagrid({width:t });
	}
	
	
	
</script>
</head>

<body class="main" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dt><a href="#"><span>角色管理</span></a></dt>
        <dd class="form">
        <div class="batch"><input name="" id="" onclick="openAdd();" type="button" class="f_btn2" value="新增" /> <input name="" type="button" class="f_btn2" value="删除" onclick="del();" /></div>
			<div class="search">
			抢单编号：<input name="vcKillno" class="f_txt" id="vcKillno" /> 起始点：<input  id="vcStart" name="vcStart" class="f_txt" value="${vcStart }" />
			目的地：<input  id="vcEnd" name="vcEnd" class="f_txt" value="${vcEnd }" />
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
		<table  id="dg"   style="width:600px;height:500px"  ></table>
			
		</dd>
    </dl>
</body>
</html>