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
<title>客户信息</title>
<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript">

$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath%>customerAction/getAllCustomerByNo',
			cache : false,
		    type : 'post',
		    title:'详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,		   
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
			columns : [ [ {
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 100
			}, {
				field : 'vcCustomerNo',
				title : '客户编号',
				width : 100,
				align : 'center'
			}, {
				field : 'vcShortName',
				title : '客户简称',
				width : 100,
				align : 'center'
			}, {
				field : 'vcLinkman',
				title : '客户联系人',
				width : 100,
				align : 'center'
			}, {
				field : 'vcPhone',
				title : '联系电话',
				width : 100,
				align : 'center'
			}, {
				field : 'vcRegAddress',
				title : '公司注册地址',
				width : 150,
				align : 'center'
			}, {
				field : 'vcProvince',
				title : '公司所属省份',
				width : 100,
				align : 'center'
			}, {
				field : 'vcCity',
				title : '公司所属城市',
				width : 100,
				align : 'center'
			}, {
				field : 'vcAddress',
				title : '公司地址',
				width : 100,
				align : 'center'
			}, {
				field : 'vcCustomerName',
				title : ' 客户全名',
				width : 150,
				align : 'center'
			}

			] ],
			onLoadSuccess : function(data) {
				//改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
				// fitWidth(data);
			},
			onLoadError : function() {
				alert("加载数据失败！");
			}

		});

	});

	function resizeGrid(minWidth) {
		var t = getWidth(minWidth);
		var h = getHeight('dg');
		var size = getPageSize(h);
		$('#dg').datagrid({
			width : t,
			height : h,
			pageSize : size
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
	//修改
	function getupdate()
	{
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>customerAction/saveBefore?cuID="+selected.id;
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
	function addCustomer(){
		var url = "<%=basePath%>customerAction/saveBefore";
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
			
			$.post("<%=basePath%>customerAction/del",  
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

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>客户管理</span> </a>
		</dt>
		<dd class="form">
			<div class="batch">
				<input name="" id="" onclick="addCustomer();" type="button" class="f_btn2"
					value="新增" /> <input name="" id="" onclick="getupdate();"
					type="button" class="f_btn2" value="修改" /> <input name=""
					onclick="del();" type="button" class="f_btn2" value="删除" />
			</div>
				<div class="search">
					<input type="text" id="shortname" class="f_txt f_short" 
						placeholder="客户简称" /> <input name=""
						onclick="search();" type="button" class="f_btn3" />

				</div>
			<table id="dg" style="width:1000px;height:500px;">

			</table>

		</dd>
	</dl>
</body>
</html>