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
<title>司机工资系数</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">

$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>driverAction/getDriverSalaryCoefflist',
		    cache : false,
		    type : 'post',
		    title:'司机工资系数详情', 
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
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:100},     
		        {field:'vcDriver',title:'司机姓名',width:100,align:'left'},     
		        {field:'nPrice',title:'提成系数',width:100,align:'left'}, 		         
		        {field:'dtStart',title:'开始时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        },
		        {field:'dtEnd',title:'结束时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        }
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
	
	function save()
	{
		var url = "<%=basePath%>driverAction/saveBeforeSalaryCoff";
		$.webox({
				height:580,
				width:800,
				bgvisibel:true,
				title:' ',
				iframe:url
		});
		
		
	}
	function del()
	{
		var selected = $('#dg').datagrid('getSelected');
		
	    if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	     
	     	$.post("<%=basePath%>driverAction/delSalaryCoff", 
					{ scID:selected.id },     
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
		     
		     	var url = "<%=basePath%>driverAction/saveBeforeSalaryCoff?salaryID="+selected.id;
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
	//查询方法
	function seacher(){
		var jsonStr = {};
		var vcDriver = $("#vcDriver").val();
	
		if(null!=vcDriver && ""!= vcDriver){
			jsonStr.vcDriver=vcDriver;
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
    	<dt><a href="#"><span>司机工资系数管理</span></a></dt>
        <dd class="form">
        <div class="batch">
        	<input  onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	<input  onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        	<input  onclick="del();" type="button" class="f_btn2" value="删除" />
        </div>
        <div class="search">
			 <input type="text" id="vcDriver" class="f_txt f_short" placeholder="司机姓名"/>
			 <input  onclick="seacher();" type="button" class="f_btn3" />  
			
			</div>
		<table  id="dg" style="width:1000px;height:500px"  >
	
		</table>
			
		</dd>
    </dl>
</body>
</html>