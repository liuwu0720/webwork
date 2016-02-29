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
<title>司机分类</title>
<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
		$('#dg').datagrid({     
		    url:'<%=basePath%>basicManage/getCarPicStyleList',
			cache : false,
			type : 'post',
			title : '车型商标管理',
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
				field : 'vcAdduser',
				title : '维护人',
				width : 100,
				align : 'center'
			}, {
				field : 'dtAdd',
				title : '维护时间',
				formatter:function(value,row,index){
					var newtime = new Date(value);
					return newtime.toLocaleString();
				},
				width : 100,
				align : 'center'
			}, {
				field : 'vcStyle',
				title : '商标类型',
				width : 100,
				align : 'center'
			}, {
				field : 'vcPath',
				title : '图片路径',
				width : 100,
				align : 'center'
			}, {
				field : 'nEnable',
				title : '有效性',
				width : 100,
				align : 'center',
				formatter:function(value,rec,index){
	        		var result;
	        		if(value=='0'){
	        			result="有  效";
	        		}else{
	        			result="无  效"
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
		     
		     	var url = "<%=basePath%>basicManage/saveCarStylePicBefore?cuID="+selected.id;
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
	//新增
	function add(){
		var url = "<%=basePath%>basicManage/saveCarStylePicBefore";
		$.webox({
				height:580,
				width:800,
				bgvisibel:true,
				title:' ',
				iframe:url
		});
	}
	//删除
	function del(){
		//var selected = $('#dg').datagrid('getSelected');删除一行
		var selected = $("#dg").datagrid('getSelections');
		if(selected.length==0){
			alert("请至少选择一条");
			return false;
		}
		var ids=[];
		for(var i=0;i<selected.length;i++){
			var id = selected[i].id;
			ids.push(id);
		}
		if(confirm("确认要删除所选记录吗？")){
			
			$.post("<%=basePath%>basicManage/del",  
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
			<a href="#"><span>司机分类管理</span> </a>
		</dt>
		<dd class="form">
			<div class="batch">
				<input name="" id="" onclick="add();" type="button" class="f_btn2"
					value="新增" /> <input name="" id="" onclick="getupdate();"
					type="button" class="f_btn2" value="修改" />
			</div>
			<div class="search"></div>
			<table id="dg" >

			</table>

		</dd>
	</dl>
</body>
</html>