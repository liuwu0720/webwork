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
<title>结算应收审核</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">
var shipno="";
$(document).ready(function(){

	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);	
		$('#dg').datagrid({     
		    url:'<%=basePath %>arorderAction/getAllArorderInfoBySubno',
		    cache : false,
		    type : 'post',
		    title:'结算应收审核', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		   // singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'ID',title:'结算表ID',checkbox : true,align:'left'},       
		        {field:'VC_CUST_ORDERNO',title:'客户订单号',width:150,align:'left'}, 
		        {field:'VC_SHIPNO',title:'调度指令号',width:150,align:'left'}, 
		        {field:'N_PAYTYPE',title:'支付方式',width:50,align:'right',formatter:function(value,index){
		        	if(value == 0){
		        		return "现金支付";
		        	}else if(value == 1){
		        		return "客户支付";
		        	}
		        }},
		        {field:'VC_START_CITY',title:'起运地',width:100,align:'left'},
		        {field:'VC_DEST_CITY',title:'目的地',width:100,align:'left'},
		        {field:'N_QTY',title:'结算数量',width:50,align:'right' },
		        {field:'N_ARKILOMETER',title:'公里数',width:50,align:'right'},
		        {field:'N_PRICE',title:'单价',width:50,align:'right'},
		       
		        {field:'N_TOTAL_PRICE',title:'总价格',width:50,align:'right',formatter:function(value,row,index){
		        	if(row.N_PAYTYPE == 0){  //现金支付
		        		return row.N_TOTAL_PRICE;
		        	}else if(row.N_PAYTYPE == 1){//客户支付
		        		return row.N_PRICE * row.N_ARKILOMETER*row.N_QTY;
		        	}
		        }},	
		        {field:'VC_CAR_STYLE',title:'车型',width:100,align:'left'},
		        {field:'DT_AUDIT',title:'审核时间',width:100,align:'left',formatter:function(value,index){
		        	var date = new Date(value);
					//date.format('yyyy-MM-dd');
					return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate().toString()+"  "+date.getHours().toString()+"时"+date.getMinutes().toString()+"分"+ date.getSeconds().toString()+"秒";
		        }},
		        {field:'VC_AUDIT_NAME',title:'审核人',width:100,align:'left'}
		    ]],
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
            },
            onLoadError:function(){
                alert("加载数据失败！");
            }
		    
		});   
	
	});
	
	
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
	//取消审核
	function cancelAudit()
	{
		
		var selected = $('#dg').datagrid('getSelections');
		var Ids=[];
		for(var i=0;i<selected.length;i++){
			var Id = selected[i].ID;
			
			Ids.push(Id);
		}
		
		if(selected.length>0){
			$.post("<%=basePath %>arorderAction/cancelAudit", {
				"array[]":Ids
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
	//生成对账单
	function createBill(){
		var selected = $('#dg').datagrid('getSelections');
		var Ids=[];
		for(var i=0;i<selected.length;i++){
			var Id = selected[i].ID;
			
			Ids.push(Id);
		}
		
		if(selected.length>0){
			$.post("<%=basePath %>arorderAction/createBill", {
				"array[]":Ids
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
	 	
		var jsonStr = {};
		var carName = $("#carName").val();
		var orderNo = $("#orderNo").val();	
		if(null!=orderNo && ""!= orderNo){
			jsonStr.orderNo=orderNo;
		}
		if(null!=carName && ""!= carName){
			jsonStr.carName=carName;
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

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>结算应收审核</span> </a></dt>
        <dd class="form">
        <div class="batch">
        	<input name="" id="" onclick="cancelAudit()" type="button" class="f_btn" value="取消审核" /> 
        	<input name="" id="" onclick="createBill()" type="button" class="f_btn" value="生成对帐单" /> 
        </div>
        <div class="search">
			 <input type="text" id="orderNo" class="f_txt f_short" placeholder="客户订单号"/>
			 <input type="text" id="carName" class="f_txt f_short" placeholder="车型"/> 
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
				
			</div>
		<table id="dg" style="width:1000px;height:500px">

			</table>
			
		</dd>
    </dl>
</body>
</html>