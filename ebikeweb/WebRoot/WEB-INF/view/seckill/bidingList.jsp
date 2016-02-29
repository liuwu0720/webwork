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
<title>秒杀订单的进行情况</title>

<%@include file="../include/common.jsp" %>
<%@ include file="../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=resizeGrid(400);
	var productId = getQueryString("productId");
	$('#dg').datagrid({     
	    url:'<%=basePath %>seckillAction/openSeckillList?productId='+productId,
	    cache : false,
	    type : 'post',
	    width:w,
	    height:290,
	    fitColumns:true,
	    pagination:true,
	    iconCls:'icon-save',
	    striped:true,  //striped  是否显示斑马线效果。
	    sortName:'id',
	    sortOrder:'desc',
	    loadMsg:'信息加载中……',
	    columns:[[     
	        {field:'vcUsreName',title:'分供方',width:100,align:'left'},     
	        {field:'ntotalPrice',title:'总价',width:100,align:'left'}, 
	        {field:'dtBid',title:'时间',width:100,align:'left',
	        	formatter:function(value,rec,index){
	        		var unixTimestamp = new Date(value);  
                    return unixTimestamp.toLocaleString();
	        	}
	        },
	        {field:'opt',title:'操作',width:50,align:'center',  
                formatter:function(value,rec,index){  
                	var e = '<a href="javascript:;" class="rob" onclick="saveBid(\''+rec.id+'\');" >中标</a> ';
                	return e;
                }  
              }  
	    ]],
	     onLoadError:function(){
                  alert("加载数据失败！");
              },
              onLoadSuccess:function(data){
             	 if(data.total==0){
             		 //当没数据时，显示提示
             		 $('#dg').datagrid('appendRow',{
             			vcUsreName: '没有相关记录'
                	 });
                	 
                	 $('#dg').datagrid('mergeCells',{
                		 index: 0,
                		 field:'vcUsreName',
                		 colspan:4
                	 });
             	 }else{
             		 //有数据时，td增加一个title属性，方便展示
             	 	addTitle();
             	 }
              }
	});   
	
	//定时刷新
	window.setInterval("seacher()",20000);
	
	});
	
	
	//查询方法
	function seacher(){
		$('#dg').datagrid('load');
	}
	
	function saveBid(kid){
		var url = "<%=basePath%>seckillAction/chooseBider";
		var productId = getQueryString("pid");
		$.post(url,{"killInfoId":kid,"productId":productId},function(data){
			showResponse(data);
		});
	}
	
	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  window.parent.$('#dg').datagrid('reload');
			  window.parent.weboxColse(); 
		  }
		}
	
	
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	    $('#dg').datagrid({width:t });
	}
	
	
	
</script>
</head>

<body class="main" onresize="resizeGrid(400);" >
	<dl class="tabs">
        <dd class="form">
			<div class="search">
			 该订单正处于秒杀中，每20秒刷新一次，也可手动刷新。
			 <input value="刷新" onclick="seacher();" type="button"  />
			</div>
			<table  id="dg"   ></table>
		</dd>
    </dl>
</body>
</html>