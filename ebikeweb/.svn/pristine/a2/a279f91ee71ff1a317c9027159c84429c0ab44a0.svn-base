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
<title>审核分供方下单信息</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>subsuppliersAction/getAllApplyOrder',
		    cache : false,
		    type : 'post',
		    title:'分供方信息', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		     fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:100},
		        {field:'vcSubno',title:'编号',width:100,align:'left'},      
		        {field:'vcAllName',title:'分供方全称',width:100,align:'left'},     
		        {field:'vcSmipleName',title:'简称',width:100,align:'left'}, 
		        {field:'vcAddress',title:'地址',width:100,align:'left'},   
		        {field:'vcScale',title:'规模',width:100,align:'left'},
		        {
		        	field:'NQTY',title:'审批确认',width:80,align:'center',
		        	formatter:function(value,row,index)
		        	{
		        		var result="";
		        		result = '<a href="javascript:showSubInfo('+row.id+');" >审批确认</a>';
		        		return result;
		        	}	
		       }
		    ]],
		     onDblClickRow: function(indx,rowdate) 
		     {
			     var selected = $('#dg').datagrid('getSelected');
			     if (selected){
			   		alert("click");
			    }
			 }, onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               //fitWidth(data);
            },
			 onCheck:function (indx,rowdate)
			 {
			 	//indx  从0开始   行   rowdate 该行数据
			 },   
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});   
	
	});
	
	function showSubInfo(subid)
	{
		//alert("subid "+subid);
		var url = "<%=basePath%>subsuppliersAction/getSubApprovalInfoByID?subID="+subid;
		$.webox({
				height:450,
				width:780,
				bgvisibel:true,
				title:'审批下单资质 ',
				iframe:url
		});
	}
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var orderNo = $("#orderNo").val();
		var receiveAddress = $("#receiveAddress").val();
		var destCity = $("#destCity").val();
		if(null!=receiveAddress && ""!= receiveAddress){
			jsonStr.receiveAddress=receiveAddress;
		}
		if(null!=orderNo && ""!= orderNo){
			jsonStr.orderNo=orderNo;
		}
		if(null!=destCity && ""!= destCity){
			jsonStr.destCity=destCity;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	   
	    $('#dg').datagrid({width:t });
	}
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>审核下单</span></a></dt>
        <dd >
        <div class="batch">
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        	<input name="" onclick="del();" type="button" class="f_btn2" value="删除" />
        </div>
        <div class="search">
			 <input type="text" id="orderNo" class="f_txt f_short" placeholder="订单号"/>
			 <input type="text" id="receiveAddress" class="f_txt f_short" placeholder="收货地址"/>
			 <input type="text" id="destCity" class="f_txt f_short" placeholder="目的地"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			</div>
		<table  id="dg"   ></table>

			
		</dd>
    </dl>
</body>
</html>