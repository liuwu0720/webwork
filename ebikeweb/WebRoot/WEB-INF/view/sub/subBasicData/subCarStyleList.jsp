<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品车信息</title>
<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
		var w=getWidth(400);
		var h = getHeight('dg');
		var size = getPageSize(h);
		$('#dg').datagrid({     
		    url:'<%=basePath%>subCarStyleAction/getAllSubCarStyle',
		    cache : false,
		    type : 'post',
		    title:'详情',  
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,		   
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。		   
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:100},     
		        {field:'vcCarStyle',title:'商品车名字',width:100,align:'center'},     
		        {field:'nenable',title:'有效性',width:100,align:'center',formatter:function(value,row,index){
		        	if(value == 0){
		        		return "有效";
		        	}else{
		        		return "无效";
		        	}
		        }},
		        {field:'',title:'删除',width:50,align:'center',formatter:function(value,row,index){
		        	return '<a href="javascript:del()">删除</a>'
		        }}
		    ]], 
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               fitWidth(data);
            },
		     onLoadError:function(){
                alert("加载数据失败！");
             }
                  
		    
		});   
	
	});
	
	function save()
	{
		var url = "<%=basePath%>subCarStyleAction/saveBefore";
		$.webox({
				height:360,
				width:600,
				bgvisibel:true,
				title:'新增车辆司机信息 ',
				iframe:url
		});
		
		
	}
	function del(){
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		     	$.post("<%=basePath%>subCarStyleAction/del?arkID="+ selected.id,  
						   function (data, textStatus)
						   {     
								if(data.isSuccess == true)
								{
									alert(data.message);
								
									$('#dg').datagrid('reload'); 
								}else
								{
									alert(data.message);
								}
						   }
					  ,"json");
		} else {
			alert("请选择一条数据.");
		}
	}
	
	

	function update(){
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>subCarStyleAction/saveBefore?arkID="+ selected.id;
			$.webox({
				height : 580,
				width : 800,
				bgvisibel : true,
				title : ' ',
				iframe : url
			});
		} else {
			alert("请选择一条数据.");
		}

	}
	//查询方法
	function seacher() {
		var jsonStr = {};
		var carNo = $("#carNo").val();
		var driverName = $("#driverName").val();
		var driverTel = $("#driverTel").val();

		if (null != carNo && "" != carNo) {
			jsonStr.carNo = carNo;
		}
		if (null != driverName && "" != driverName) {
			jsonStr.driverName = driverName;
		}
		if (null != driverTel && "" != driverTel) {
			jsonStr.driverTel = driverTel;
		}
		$('#dg').datagrid('load', jsonStr);
	}

	function resizeGrid(minWidth) {
		var t = getWidth(minWidth);

		$('#dg').datagrid({
			width : t
		});
	}
	function weboxColse() {

		$('.webox').css({
			display : 'none'
		});
		$('.background').css({
			display : 'none'
		});
	}
	//function showDriverInfo(){

	//weboxColse();
	//saveDriverInfo();
	//}
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>商品车维护</span>
			</a>
		</dt>
		<dd class="form">
			<div class="batch">
				<input name="" id="" onclick="save();" type="button" class="f_btn2"
					value="新增" />
			    <input name="" id="" onclick="update();" type="button" class="f_btn2"
					value="修改" />
			     <input name="" onclick="del();" type="button"
					class="f_btn2" value="删除" />
			</div>
			<div class="search">
				<input type="text" id="carNo" class="f_txt f_short"
					placeholder="商品车车名" /> <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
			<table id="dg" style="width:1000px;height:500px">

			</table>

		</dd>
	</dl>
</body>
</html>