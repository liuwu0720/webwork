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
<title>订单信息</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">
$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	
		$('#dg').datagrid({     
		    url:'<%=basePath %>orderAction/getAllOrderBysubno',
		    type : 'post',
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    title:'订单详情', 
		    width:w,
		   // height:h,
		    pageSize:size,
		    fitColumns:true,   //数据列太少 未自适应
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:false,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:80},     
		        {field:'vcOrderno',title:'订单号',width:100,align:'left'},   
		        {field:'vcCustOrderNo',title:'客户单号',width:100,align:'left'},     
		        {field:'vcStartCity',title:'起运城市',width:100,align:'left'}, 
		        {field:'vcDestCity',title:'目的城市',width:100,align:'left'},   
		        {field:'vcReceiveContact',title:'收货人',width:100,align:'left'},
		        {field:'vcReceiveAddress',title:'收货地址',width:100,align:'left'},
		        {field:'vcReceiveTel',title:'收货人电话',width:100,align:'left'},
		        {field:'ntotalCar',title:'数量',width:100,align:'right'},
		        {field:'nshipedQty',title:'已发运数量',width:100,align:'right'},
		        {field:'',title:'操作',width:100,align:'center',
		        	formatter:function(value,row,index){
		        		return "<input type='button' value='修改' onclick='update("+row.id+")'/>";
		        	}
		        }
		    ]],
		     onDblClickRow: function(indx,rowdate) 
		     {
			     var selected = $('#dg').datagrid('getSelected');
			     if (selected){
			   		alert("click");
			    }
			 }, 
			 onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               fitWidth(data);
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
	
	function save()
	{
		var url = "<%=basePath%>orderAction/saveBefore";
		$.webox({
				height:550,
				width:780,
				bgvisibel:true,
				title:'新增订单 ',
				iframe:url
		});
	}
	function del()
	{
		var selected = $('#dg').datagrid('getSelections');
		var ids='';
		for(var i=0;i<selected.length;i++){
			ids+=selected[i].id;
			if(i!=selected.length-1){
				ids+=',';
			}
		}
		//alert("ids:"+ids);
		if (selected.length!=0){
	      // var index = $('#dg').datagrid('getRowIndex', selected);
	     
	     	$.post("<%=basePath%>orderAction/del", 
					{ orderID:ids},     
					   function (data, textStatus)
					   {     
							
							if(data.isSuccess == true)
							{
								alert(data.message);
								//$('#dg').datagrid('deleteRow', index);
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
	function update(id)
	{
		var url = "<%=basePath%>orderAction/saveBefore?orderID="+id;
					$.webox({
							height:550,
							width:780,
							bgvisibel:true,
							title:' ',
							iframe:url
					});
	}
	function getupdate()
	{
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>orderAction/saveBefore?orderID="+selected.id;
					$.webox({
							height:550,
							width:780,
							bgvisibel:true,
							title:' ',
							iframe:url
					});
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
		
		
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
	    var h = getHeight('dg');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size });
	    
	}
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>订单管理</span></a></dt>
        <dd class="form">
        <div class="batch">
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	<!--<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" />--> 
        	<input name="" onclick="del();" type="button" class="f_btn2" value="删除" />
        </div>
        <div class="search">
			 <input type="text" id="orderNo" class="f_txt f_short" placeholder="订单号"/>
			 <input type="text" id="receiveAddress" class="f_txt f_short" placeholder="收货地址"/>
			 <input type="text" id="destCity" class="f_txt f_short" placeholder="目的地"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			</div>
		<table  id="dg" style="width:1000px;height:500px"></table>

			
		</dd>
    </dl>
</body>
</html>