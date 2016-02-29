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
<title>GPS频率定位</title>
<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
		$('#dg').datagrid({     
		    url:'<%=basePath%>basicManage/getGpsRateList',
			cache : false,
			type : 'post',
			title : 'gps频率定位',
			pagination : true, //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			width:w,
		    height:h,
		    pageSize:size, 
			fitColumns : true,//列自适应
			rownumbers : true, //如果为true，则显示一个行号列。
			striped : true, //striped  是否显示斑马线效果。
			singleSelect : true, //如果为true，则只允许选择一行。
			loadMsg : '正在加载,请稍等...',
			columns : [ [ {
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 100
			}, {
				field : 'vcUserNo',
				title : '司机名称',
				width : 100,
				align : 'center'
			}, {
				field : 'nRate',
				title : '频率',				
				width : 100,
				align : 'center'
			}, {
				field : 'nGps',
				title : '是否定位',
				width : 100,
				align : 'center',
				formatter:function(value,rec,index){
	        		var result;
	        		if(value=='0'){
	        			result="定位";
	        		}else{
	        			result="不定位"
	        		}
	        		return result;
	        	}
			}
			] ],
			onLoadSuccess : function(data) {
				//改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
				fitWidth(data);
			},
			onLoadError : function() {
				alert("加载数据失败！");
			}

		});

	});


		function resizeGrid(minWidth){
		    var t =getWidth(minWidth);
		   
		    $('#dg').datagrid({width:t });
		}
	

	function weboxColse() {

		$('.webox').css({
			display : 'none'
		});
		$('.background').css({
			display : 'none'
		});
	}
	//修改
	function getupdate()
	{
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>basicManage/saveGpsRateBefore?cuID="+selected.id;
				$.webox({
						height:580,
						width:800,
						bgvisibel:true,
						title:' ',
						iframe:url
				});
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
	}
	//同步
	function synchronous(){
		$.post("<%=basePath%>basicManage/synchronousDriver",
			function(data,textStatus){
			if(data.isSuccess == true)
			{
			
				$.messager.show({ // show error message
					title : '提示',
					msg :"同步成功"+data.message
				});
				$('#dg').datagrid('reload'); 
			}else
			{
				alert(data.message);
			}
		},"json");
	}
	//删除
	function del(){
		var selected = $('#dg').datagrid('getSelected');//删除一行
		//var selected = $("#dg").datagrid('getSelections');
		if(!selected){
			alert("请至少选择一条");
			return false;
		}
	
		if(confirm("确认要删除所选记录吗？")){
			
			$.post("<%=basePath%>basicManage/delGpsRate",  
					{"selectId":selected.id},     
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
	//查询
	function search(){
		var shortName = $("#shortname").val();
		var custNo = $("#custNo").val();
		var jsonStr = {};
		if(null!=shortName && ""!= shortName){
			jsonStr.shortName=shortName;
		}
		if(null!=custNo && ""!= custNo){
			jsonStr.custNo=custNo;
		}
		
		$('#dg').datagrid('load',jsonStr);
	}
		
	
	
	
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>司机GPS定位管理</span> </a>
		</dt>
		<dd class="form">
			<div class="batch">
				<input name="" id="" onclick="synchronous();" type="button" class="f_btn2"
					value="同步信息" /> <input name="" id="" onclick="getupdate();"
					type="button" class="f_btn2" value="修改" />
					<input  onclick="del();"
					type="button" class="f_btn2" value="删除" />
			</div>
			<div class="search"></div>
			<table id="dg" >

			</table>

		</dd>
	</dl>
</body>
</html>