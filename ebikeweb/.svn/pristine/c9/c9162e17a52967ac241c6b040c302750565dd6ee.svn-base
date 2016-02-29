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
<title>分享圈</title>


<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
	
		$('#dg').datagrid({     
		    url:'<%=basePath%>basicManage/getShareTagList',
		    cache : false,
		    type : 'post',
		    title:'分享圈标签', 
		    width:w,
		    height:h,
		    pageSize:size,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
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
				field : 'vcTag',
				title : '分享圈标签',
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
	
	function add()
	{
		var url = "<%=basePath%>basicManage/saveShareTagBefore";
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
		var selected = $('#dg').datagrid('getSelected');
		
	    if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	     
	     	$.post("<%=basePath%>basicManage/del", 
					{ orderID:selected.id },     
					   function (data, textStatus)
					   {     
							
							if(data.isSuccess == true)
							{
								alert(data.message);
								$('#dg').datagrid('deleteRow', index);
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

	function getupdate()
	{
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		     
		     	var url = "<%=basePath%>basicManage/saveShareTagBefore?id="+selected.id;
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
	
	//消息推送
	function push(){
		var url = "<%=basePath%>basicManage/pushShareTag";
		$.webox({
				height:450,
				width:480,
				bgvisibel:true,
				title:' ',
				iframe:url
		});
	}
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>分享圈标签管理</span>
			</a>
		</dt>
		<dd>
			<div class="batch">
				<input name="" id="" onclick="add();" type="button" class="f_btn2"
					value="新增" /> <input name="" id="" onclick="getupdate();"
					type="button" class="f_btn2" value="修改" /> <input name=""
					onclick="push();" type="button" class="f_btn2" value="消息推送" />
			</div>
			<div class="search"></div>
			<table id="dg"></table>


		</dd>
	</dl>
</body>
</html>