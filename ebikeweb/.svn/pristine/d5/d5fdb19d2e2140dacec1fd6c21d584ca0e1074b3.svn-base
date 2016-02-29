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
<title>应收单价</title>


<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	    var h = getHeight('dg');
	    var size = getPageSize(h);
	    var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath%>driverCostAction/getAllDriverCostBySubNo',
		    cache : false,
		    type : 'post',
		    title:'应收单价详情', 
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
				field : 'dtStart',
				title : '生效日期',
				width : 100,
				align : 'center',
	        	formatter:function(value,rec,index)
	        	{
	        		var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
	        	}
			}, {
				field : 'dtEnd',
				title : '失效日期',
				width : 100,
				align : 'center',
	        	formatter:function(value,rec,index)
	        	{
	        		var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
	        	}
			}, {
				field : 'nCost',
				title : '单价',
				width : 100,
				align : 'center'
			} ,{
				field : 'nType',
				title : '支付方式',
				width : 100,
				align : 'center',
				formatter:function(value,index){
					if(value == 1){
						return "单公里";
					}
					if(value == 2){
						return "单台";
					}
				}
			}, {
				field : 'vcStartCity',
				title : '起始地城市',
				width : 100,
				align : 'center'
			}, {
				field : 'vcEndCity',
				title : '目的地城市',
				width : 100,
				align : 'center'
			}, {
				field : 'vcCarName',
				title : '商品车车型',
				width : 100,
				align : 'center'
			}, {
				field : 'vcCustomer',
				title : '客户',
				width : 100,
				align : 'center'
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
//修改
function getupdate()
{
	var selected = $('#dg').datagrid('getSelected');
	if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	     
	     	var url = "<%=basePath%>driverCostAction/saveBefore?costID="+selected.id;
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
	var url = "<%=basePath%>driverCostAction/saveBefore";
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
	var selected = $('#dg').datagrid('getSelected');
    if (selected){
       var index = $('#dg').datagrid('getRowIndex', selected);
     
     	$.post("<%=basePath%>driverCostAction/del", 
				{ costID:selected.id},     
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
			  
    }else
    {
    	alert("请选择一条数据.");
    }
}



	function ordClick(obj,driID,index)
	{
		//发运数量
		var ncost = $("#ncost"+driID)[0];
		var dStart = $("#dtStart"+driID)[0];
		var dEnd = $("#dtEnd"+driID)[0];
		
		if(obj.checked==true)
		{
			ncost.readOnly=false;
			ncost.select();
			dStart.disabled=false;
			dEnd.disabled=false;
		}else
		{
			dStart.disabled=true;
			dEnd.disabled=true;
			ncost.readOnly=true;
		}		
	}
	function showDates(obj)
	{
		WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true});
	}
	//验证 输入的单价数字类型  
	function checkCount(obj)
	{
		var nums = parseFloat(obj.value);
		var f_x = parseFloat(nums);
		if (isNaN(f_x))
		{
			alert('请输入数字类型.');
			obj.value="";
			obj.select();
			return false;
		}
		var f_x = f_x.toFixed(2);
		obj.value=f_x;
	}


	//查询方法
	function seacher() {
		var jsonStr = {};
		var carName = $("#carName").val();
		var startCity = $("#startCity").val();
		var endCity = $("#endCity").val();
		if (null != carName && "" != carName) {
			jsonStr.carName = carName;
		}
		if (null != startCity && "" != startCity) {
			jsonStr.startCity = startCity;
		}
		if (null != endCity && "" != endCity) {
			jsonStr.endCity = endCity;
		}
		$('#dg').datagrid('load', jsonStr);
	}

	function weboxColse() {

		$('.webox').css({
			display : 'none'
		});
		$('.background').css({
			display : 'none'
		});
	}

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
				<input  id="new" onclick="add();" type="button"
					class="f_btn2" value="新增" /> <input  id="update"
					onclick="getupdate();" type="button" class="f_btn2" value="修改" />
				<input  onclick="del();" type="button" class="f_btn2"
					value="删除" />

			</div>
				<div class="search">
						
					<input type="text" id="startCity" class="f_txt f_short"
						placeholder="起始地城市" />
					<input type="text" id="endCity" class="f_txt f_short"
						placeholder="目的地城市" />
					<input name="" onclick="seacher();"
						type="button" class="f_btn3" />
					
				</div>
			<table id="dg" style="width:1000px;height:200px">

			</table>

		</dd>
	</dl>
</body>
</html>