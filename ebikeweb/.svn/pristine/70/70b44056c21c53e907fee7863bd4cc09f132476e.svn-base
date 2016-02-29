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
<title>审核分供方权限申请</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>subsuppliersAction/getAllApplyResource',
		    cache : false,
		    type : 'post',
		    title:'分供方信息', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。 
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'ID',title:'id',checkbox:true,width:30},
		        {field:'VC_ACCOUNT',title:'用户名',width:100,align:'left'},      
		        {field:'VC_USERNAME',title:'姓名',width:100,align:'left'},     
		        {
			        field:'VC_SUBNO',title:'编号',width:100,align:'left',
			        	formatter:function(value,row,index)
			        	{
			        		var result="";
			        		result = '<a href="javascript:showResourceInfo('+row.ID+');" >审批确认</a>';
			        		return result;
			        	}	
		        },  
		        {field:'VC_ALL_NAME',title:'全称',width:100,align:'left'},   
		        {field:'VC_SMIPLE_NAME',title:'简称',width:100,align:'left'}, 
		        {field:'VC_ADDRESS',title:'地址',width:100,align:'left'}
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
	
	function showResourceInfo(usid)
	{
		//alert("subid "+subid);
		var url = "<%=basePath%>subsuppliersAction/getSubApplyResourceByID?usid="+usid;
		$.webox({
				height:450,
				width:780,
				bgvisibel:true,
				title:'审批开通权限 ',
				iframe:url
		});
	}
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var userNo = $("#userNo").val();
		var subNo = $("#subNo").val(); 
		
		if(null!=userNo && ""!= userNo){
			jsonStr.userNo=userNo;
		}
		if(null!=subNo && ""!= subNo){
			jsonStr.subNo=subNo;
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

<body class="main"  >
	<dl class="tabs">
    	<dt><a href="#"><span>审核权限</span></a></dt>
        <dd >
         
        <div class="search">
			 <input type="text" id="userNo" class="f_txt f_short" placeholder="用户名"/>
			 <input type="text" id="subNo" class="f_txt f_short" placeholder="分供方编号"/> 
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			</div>
		<table  id="dg"   ></table>

			
		</dd>
    </dl>
</body>
</html>