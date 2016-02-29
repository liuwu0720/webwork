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
<title>结算信息</title>

<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
var shipno="";
$(document).ready(function(){

	var h = getHeight('dg');
	var size = getPageSize(h);
	var w=getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>arorderAction/getAllReturnInfoBySubno',	  
		    type : 'post',
		    title:'结算应收详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。  
		    striped:true,  //striped  是否显示斑马线效果。
		 
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    nowrap:false,
		    columns:[[   
		        {field:'I_SHIPHEAD',title:'发运主表ID',checkbox : true,align:'left'},       
		        {field:'VC_CUST_ORDERNO',title:'客户订单号',width:100,align:'left'}, 
		        {field:'VC_SHIPNO',title:'发运指令号',width:100,align:'left'}, 
		        {field:'VC_ORDERNO',title:'订单号',width:100,align:'left'},     
		        {field:'VC_TRUCK_NAME',title:'车牌号',width:100,align:'left'}, 
		        {field:'VC_START_CITY',title:'起运地',width:100,align:'left'},
		        {field:'VC_DEST_CITY',title:'目的地',width:100,align:'left'},
		        {field:'N_SHIP_QTY',title:'发运数量',width:50,align:'right'},
		        {field:'DT_SHIP',title:'要求发运日',width:100,align:'right',formatter:function(value,index){
		        	var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
		        }},
		        {field:'DT_ARRIVE',title:'要求抵达日',width:100,align:'right',formatter:function(value,index){
		        	var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
		        }},
		        {field:'N_QTY',title:'结算数量',width:50,align:'right'},
		        {field:'N_ARKILOMETER',title:'应收公里数',width:50,align:'right',formatter:function(value,index){
		        	if(value==null || value == 0){
		        		return "<a href='javascript:synchronous()'>点击同步</a>";
		        	}else{
		        		return value;
		        	}
		        }},
		        {field:'N_PRICE',title:'应收单价',width:50,align:'right',formatter:function(value,index){
		        	if(value==null || value == 0){
		        		return "<a href='javascript:synchronous()'>点击同步</a>";
		        	}else{
		        		return value;
		        	}
		        }},
		        {field:'N_PAYTYPE',title:'支付方式',width:50,align:'right',formatter:function(value,index){
		        	if(value == 0){
		        		return "现金支付";
		        	}else{
		        		return "客户支付";
		        	}
		        }},
		        {field:'N_TOTAL_PRICE',title:'总价',width:50,align:'right'},	
		        {field:'VC_CAR_NAME',title:'车型',width:100,align:'left'},
		        {field:'I_SHIPLINE',title:'发动明细ID',width:100,align:'left',hidden:true}
		        
		    ]],
		    view: detailview,
			detailFormatter:function(index,row){
				return '<div class="ddv" style="padding:5px 0"><table class="ddv"></table></div>';
			},
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');			
				ddv.datagrid({
					url:'<%=basePath %>arorderAction/getAllReturnInfoBySubnoDetail?shiplineId='+row.I_SHIPLINE,
					fitColumns:true,
					rownumbers:true,
					loadMsg:'正在加载...',
					height:'auto',
					columns:[[
						{field:'vcStatusNote',title:'状态',width:150},
						{field:'dtStatus',title:'时间',width:150,formatter:function(value,index){
							var date = new Date(value);
							//date.format('yyyy-MM-dd');
							return date.toLocaleString();
						}},
						{field:'vcAddUser',title:'状态确认人',width:150},
						{field:'vcStatusPlace',title:'详细地址',width:150},
						{field:'vcLongitude',title:'经度',width:150},
						{field:'vcLatitude',title:'纬度',width:100},
						{field:'vcNote',title:'备注',width:100}
					]],
					onResize:function(){
						$('#dg').datagrid('fixDetailRowHeight',index);
					},
					onLoadSuccess:function(){
						setTimeout(function(){
							$('#dg').datagrid('fixDetailRowHeight',index);
						},0);
					}
				});
				$('#dg').datagrid('fixDetailRowHeight',index);
			},
		    
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});   
	
	});
	
	function showArkilomer(stacityID,endcityID)
	{
		var url = "<%=basePath%>arkilometerAction/ArordersaveBefore?stacityID="+stacityID+"&&endcityID="+endcityID;
			$.webox({
					height:580,
					width:800,
					bgvisibel:true,
					title:' ',
					iframe:url
			});
	}
	function showDriverCost(driverID)
	{
		var url = "<%=basePath%>driverCostAction/ArordersaveBefore?driverID="+driverID;
			$.webox({
					height:580,
					width:800,
					bgvisibel:true,
					title:' ',
					iframe:url
			});
	}
	function showDates(obj)
	{
		WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true});
	}
	
	function showheadDetails(headid)
	{
		var url = "<%=basePath%>shipheadAction/intogetShipDetailsByID?headID="+headid;
					$.webox({
							height:500,
							width:600,
							bgvisibel:true,
							title:' 指令详情 ',
							iframe:url
					});
	}

	function save()
	{
		var selected = $('#dg').datagrid('getSelected');
		if(selected){
			$.post("<%=basePath %>arorderAction/saveArorder",{
				"id":selected.I_SHIPLINE
			},function(data, textStatus){
				if(data.isSuccess == true)
				{
					$.messager.show({ 
						title : '提示',
						msg :data.message
					});
					$('#dg').datagrid('reload'); 
				}else
				{
					alert(data.message);
				}
			},"json")
		}
		
	}
	
	function synchronous(){
		var selected = $('#dg').datagrid('getSelected');
		if(selected){
			$.post("<%=basePath %>arorderAction/synchronous",{
				"id":selected.I_SHIPLINE
			},function(data, textStatus){
				if(data.isSuccess == true)
				{
					$.messager.show({ 
						title : '提示',
						msg :data.message
					});
					$('#dg').datagrid('reload'); 
				}else
				{
					alert(data.message);
				}
			},"json")
		}
	}
	
	
	
	//查询方法
	function seacher(){
	 	shipno="";
		var jsonStr = {};
		var orderNo = $("#orderNo").val();
		var shipNo = $("#shipNo").val();
		var driverName = $("#driverName").val();
		
		if(null!=shipNo && ""!= shipNo){
			jsonStr.shipNo=shipNo;
		}
		if(null!=orderNo && ""!= orderNo){
			jsonStr.orderNo=orderNo;
		}
		if(null!=driverName && ""!= driverName){
			jsonStr.driverName=driverName;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	function resizeGrid(minWidth){
		var t = getWidth(minWidth);
		var h = getHeight('dg');
		var size = getPageSize(h);
		$('#dg').datagrid({
			width : t,
			height : h,
			pageSize : size
		});
	}
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
</script>
</head>

<body class="main easyui-layout">
	<dl class="tabs">
    	<dt><a href="#"><span>结算应收管理</span> </a></dt>
        <dd class="form">
        <div class="batch">
       		<input name="" id="" onclick="synchronous();" type="button" class="f_btn2" value="同步" /> 
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="应收确定" />
        	
        </div>
        <div class="search">
			 <input type="text" id="shipNo" class="f_txt f_short" placeholder="指令号"/>
			 <input type="text" id="orderNo" class="f_txt f_short" placeholder="订单号"/>
			  <input type="text" id="driverName" class="f_txt f_short" placeholder="车牌号"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
				
			</div>
		<table id="dg" style="width:1000px;height:500px">

			</table>
			
		</dd>
    </dl>
</body>
</html>