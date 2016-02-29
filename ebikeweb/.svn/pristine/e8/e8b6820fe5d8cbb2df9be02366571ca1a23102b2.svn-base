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
<title>司机补贴关联维护</title>

<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-easyui-1.3.6/jqueryeasyui-detail.js"></script>
<script type="text/javascript">
var shipno="";
$(document).ready(function(){

	var h = getHeight('dg');
	var size = getPageSize(h);
	var w=getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>driverAction/getDriverSubsidiesLinks',	  
		    type : 'post',
		    title:'司机补贴关联维护', 
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
		        {field:'id',title:'司机ID',checkbox : true,align:'left'},       
		        {field:'vcDriverName',title:'司机姓名',width:150,align:'left'}, 
		        {field:'vcDriverTel',title:'司机联系方式',width:150,align:'left'}, 
		        {field:'vcPlace',title:'籍贯',width:150,align:'left'},     
		        {field:'vcDriverLi',title:'驾驶证号',width:100,align:'left'}, 
		        {field:'vcCard',title:'身份证号',width:100,align:'left'}		        
		    ]],
		    view: detailview,
			detailFormatter:function(index,row){
				return '<div class="ddv" style="padding:5px 0"><table class="ddv"></table></div>';
			},
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');			
				ddv.datagrid({
					url:'<%=basePath %>driverAction/getDriverSubside?driverId='+row.id,
					fitColumns:true,
					rownumbers:true,
					loadMsg:'正在加载...',
					height:'auto',
					columns:[[
						{field:'VC_NAME',title:'补贴名称',width:150},
						{field:'N_NUMBER',title:'金额',width:150},
						{field:'DT_START',title:'生效日期',width:150,formatter:function(value,index){
							var date = new Date(value);
							//date.format('yyyy-MM-dd');
							return date.toLocaleString();
						}},
						{field:'DT_END',title:'失效日期',width:150,formatter:function(value,index){
							var date = new Date(value);
							//date.format('yyyy-MM-dd');
							return date.toLocaleString();
						}}
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
	
	function add(){
		var selected = $("#dg").datagrid('getSelections');
		if(selected.length==0){
			alert("请至少选择一条");
			return false;
		}
		var ids=[];
		var strIds = "";
		for(var i=0;i<selected.length;i++){
			var id = selected[i].id;
			ids.push(id);
		}
		strIds = ids.join(",");
		
		 if (selected.length>0){
		       var url = "<%=basePath%>driverAction/saveBeforeSubsidiesLinks?id="+strIds;
				$.webox({
						height:580,
						width:800,
						bgvisibel:true,
						title:'司机补贴维护',
						iframe:url
				});
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
	}
	
	function del(){
		var selected = $("#dg").datagrid('getSelections');
		if(selected.length==0){
			alert("请至少选择一条");
			return false;
		}
		var ids=[];
		var strIds = "";
		for(var i=0;i<selected.length;i++){
			var id = selected[i].id;
			ids.push(id);
		}
		strIds = ids.join(",");
		
		 if (selected.length>0){
			 if(confirm("是否删除该司机的补贴关联?")){
				 
					$.post("<%=basePath%>driverAction/deleteSubsidiesLinks", 
							{ driverIds:strIds },     
							   function (data, textStatus)
							   {     
									if(data.isSuccess == true)
									{
										alert(data.message); 
										$('#dg').datagrid('reload'); 
									}else
									{
										alert(data.message);
									}
							   }
						  ,"json");
						  
				} 
			
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
	}

	
	
	//查询方法
	function seacher(){
	 	shipno="";
		var jsonStr = {};
		
		var driverName = $("#vcDriverName").val();

		if(null!=driverName && ""!= driverName){
			jsonStr.driverName=driverName;
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

<body class="main easyui-layout">
	<dl class="tabs">
    	<dt><a href="#"><span>司机补贴维护</span> </a></dt>
        <dd class="form">
        <div class="batch">
        	<input  onclick="add();" type="button" class="f_btn2" value="新增" /> 
        	<input  onclick="del();" type="button" class="f_btn2" value="删除" /> 
        </div>
        <div class="search">
			  <input type="text" id="vcDriverName" class="f_txt f_short" placeholder="司机姓名"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  			
			</div>
		<table  id="dg"  style="width:1000px;height:500px" >
	
		</table>
			
		</dd>
    </dl>
</body>
</html>