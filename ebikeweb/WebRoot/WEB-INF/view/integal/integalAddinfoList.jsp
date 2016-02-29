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
<title>积分增加类型</title>


<%@include file="../include/common.jsp" %>
<%@ include file="../include/datagrid.jsp"  %>

<script type="text/javascript">

$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
	 
		$('#dg').datagrid({     
		    url:'<%=basePath %>integalAddAction/getAllIntegalAdds',
		    cache : false,
		    type : 'post',
		    title:'积分增加类型信息', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		   	width:w,
		    height:h,
		    pageSize:size, 
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:100},     
		        {field:'vcCause',title:'加分原因',width:100,align:'left'}, 
		        {field:'ncredit',title:'金额',width:100,align:'right'}, 
		        {field:'nintegal',title:'积分数',width:100,align:'right'},
		        {field:'dtBegin',title:'开始时间',width:100,align:'right',
		        	formatter:function(value,rec,index)
		        	{
					        		if(null!=value && ""!=value)
					        		{
						        		var unixTimestamp = new Date(value);  
				                        return unixTimestamp.toLocaleDateString();
					        			
					        		}
		        	}
		        
		        },
		        {field:'dtEnd',title:'结束时间',width:140,align:'right',
		        	formatter:function(value,rec,index)
			        {
					        		if(null!=value && ""!=value)
					        		{
						        		var unixTimestamp = new Date(value);  
				                        return unixTimestamp.toLocaleDateString();
					        			
					        		}
			        }
		        }
		        
		        
		    ]], 
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               fitWidth(data);
            },
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});   
	
	});
	
	function save()
	{
		var url = "<%=basePath%>integalAddAction/saveBefore";
		$.webox({
				height:360,
				width:600,
				bgvisibel:true,
				title:'新增扣分类型信息 ',
				iframe:url
		});
		
		
	}
	  
	 
	function del()
	{
		var selected = $('#dg').datagrid('getSelected');
		
	    if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	   
	     	$.post("<%=basePath%>integalAddAction/delIntegalAdds", 
					{ inteCutAddID:selected.id },     
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
		     
		     	var url = "<%=basePath%>integalAddAction/saveBefore?inteCutAddID="+selected.id;
				$.webox({
						height:360,
						width:600,
						bgvisibel:true,
						title:'修改加分类型',
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
		var carNo = $("#carNo").val();
		var driverName = $("#driverName").val();
		var driverTel = $("#driverTel").val();
		
		if(null!=carNo && ""!= carNo){
			jsonStr.carNo=carNo;
		}
		if(null!=driverName && ""!= driverName){
			jsonStr.driverName=driverName;
		}
		if(null!=driverTel && ""!= driverTel){
			jsonStr.driverTel=driverTel;
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
	//function showDriverInfo(){
	
		//weboxColse();
		//saveDriverInfo();
	//}
	
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>加分类型管理</span></a></dt>
        <dd class="form">
        <div class="batch">
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        	<input name="" onclick="del();" type="button" class="f_btn2" value="删除" /> 
        </div>
        <div class="search">
			 
			  <input type="text" id="vcCause" class="f_txt f_short" placeholder="司机姓名"/> 
			   <input type="text" id="driverTel" class="f_txt f_short" placeholder="手机号"/>  
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			
			</div>
			
			<!-- 车辆ID   -->
			<input type="hidden"  id="driverID" name="driverID"/>
			<!-- 司机主驾 副驾参数标识   1主驾   2副驾 -->
			<input type="hidden"  id="driverType" name="driverType"/>
		<table  id="dg"  >
	
</table>
			
		</dd>
    </dl>
</body>
</html>