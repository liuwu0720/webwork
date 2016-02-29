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
<title>订单信息</title>


<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath%>driverCostCheckAction/getAllDriverCostBySubNo',
		    cache : false,
		    type : 'post',
		    title:'应收单价详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    pageList :[10,15,20],
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		   // singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns : [ [ {
				field : 'ID',
				title : 'id',
				checkbox : true,
				width : 100
			}, {
				field : 'DT_START',
				title : '生效日期',
				width : 100,
				align : 'center'
			}, {
				field : 'DT_END',
				title : '失效日期',
				width : 100,
				align : 'center'
			}, {
				field : 'N_COST',
				title : '单价',
				width : 100,
				align : 'center'
			}, {
				field : 'VC_START_CITY',
				title : '起始地城市',
				width : 100,
				align : 'center'
			}, {
				field : 'VC_END_CITY',
				title : '目的地城市',
				width : 100,
				align : 'center'
			}, {
				field : 'VC_CAR_STYLE',
				title : '商品车车型',
				width : 100,
				align : 'center'
			}, {
				field : 'VC_SHORT_NAME',
				title : '客户',
				width : 100,
				align : 'center'
			}, {
				field : 'DT_ADD',
				title : '维护时间',
				width : 100,
				align : 'center',
				formatter:function(value,index){
					var date = new Date(value);
					//date.format('yyyy-MM-dd');
					return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate().toString()+"  "+date.getHours().toString()+"时"+date.getMinutes().toString()+"分"+ date.getSeconds().toString()+"秒";
				}
				
			}, {
				field : 'I_UPDATE_USER',
				title : '维护人',
				width : 100,
				align : 'center'
				
			},{
				field : 'N_CHECK',
				title : '审核情况',
				width : 100,
				align : 'center',
				formatter:function(value){
					if(value==0){
						return "未审核";
					}else{
						return "已审核";
					}
				}
			}

			] ],
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               fitWidth(data);
            },
		     onLoadError:function(){
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
	
	
	//查询方法
	function seacher() {
		var jsonStr = {};
		var check = $("#check").val();
		var startCity = $("#startCity").val();
		var endCity = $("#endCity").val();
		if (null != check && "" != check) {
			jsonStr.check = check;
		}
		if (null != startCity && "" != startCity) {
			jsonStr.startCity = startCity;
		}
		if (null != endCity && "" != endCity) {
			jsonStr.endCity = endCity;
		}
		$('#dg').datagrid('load', jsonStr);
	}
	
	//审核
	function checkyes(){
		//var selected = $('#dg').datagrid('getSelected');删除一行
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
		if(confirm("确认审核通过所选记录吗？")){
			
			$.post("<%=basePath%>driverCostCheckAction/checkyes",  
					{"array[]":ids},     
					   function (data, textStatus)
					   {     
							if(data.isSuccess == true)
							{
							
								$.messager.show({ // show error message
									title : '提示',
									msg :data.message
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
	
	//取消审核
	function checkno(){
		//var selected = $('#dg').datagrid('getSelected');删除一行
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
		if(confirm("确认审核通过所选记录吗？")){
			
			$.post("<%=basePath%>driverCostCheckAction/checkno",  
					{"array[]":ids},     
					   function (data, textStatus)
					   {     
							if(data.isSuccess == true)
							{
							
								$.messager.show({ // show error message
									title : '提示',
									msg :data.message
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
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>应收单价管理</span>
			</a>
		</dt>
		<dd class="form">
			<div class="batch">
				<input  id="new" onclick="checkyes();" type="button"
					class="f_btn2" value="审核通过" /> <input  id="update"
					onclick="checkno();" type="button" class="f_btn2" value="取消审核" />
				
			</div>
				<div class="search">
					
					<input type="text" id="startCity" class="f_txt f_short"
						placeholder="起始地城市" />
					<input type="text" id="endCity" class="f_txt f_short"
						placeholder="目的地城市" />	 
					<select id="check">
						<option value="0">未审核</option>
						<option value="1">已审核</option>
					</select>	
					<input name="" onclick="seacher();"
						type="button" class="f_btn3" />
					
				</div>
			<table id="dg" style="width:1000px;height:500px">

			</table>

		</dd>
	</dl>
</body>
</html>