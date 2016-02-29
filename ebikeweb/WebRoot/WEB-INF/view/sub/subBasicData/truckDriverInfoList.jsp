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
<title>车辆司机信息</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">

$(document).ready(function(){
	    var w=getWidth(400);
	    var h = getHeight('dg');
	    var size = getPageSize(h);
	    $('#dg').datagrid({     
		    url:'<%=basePath %>truckDriverAction/getAllTrucksBysubno',
		    cache : false,
		    type : 'post',
		    title:'车辆信息',
		    width:w,
		    //height:h, 
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    pageList :[10,15,20],
		   //  fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'id',checkbox:true,width:100},     
		        {field:'VC_CAR_NO',title:'车牌号',width:100,align:'left',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:showDriverInfo('+row.ID+');" >'+row.VC_CAR_NO+'</a>'; 
		        		return result;
		        		}
		        },     
		        {field:'VC_TYPE_NAME',title:'类型',width:100,align:'left'}, 
		        {field:'N_LENGTH',title:'长',width:100,align:'right'},
		        {field:'N_WIDTH',title:'宽',width:100,align:'right'},
		        {field:'N_HEIGHT',title:'高',width:100,align:'right'},
		        {field:'N_LOAN_WEIGHT',title:'载重',width:100,align:'right'} ,
		        
		        {field:'up',title:'操作',width:60,align:'center',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:updateDriverInfo('+row.ID+');" title="修改车辆和司机的关联" >修改</a>'; 
		        		return result;
		        		}
		        },  
		         {field:'del',title:'操作',width:60,align:'center',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:delDriverInfo('+row.ID+');" title="删除车辆和司机的关联" >删除</a>'; 
		        		return result;
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
		var url = "<%=basePath%>truckDriverAction/saveBefore";
		$.webox({
				height:360,
				width:600,
				bgvisibel:true,
				title:'新增车辆司机信息 ',
				iframe:url
		});
		
		
	}
	//打开新增司机信息页面 
	function saveDriverInfo()
	{
		var driID  = $("#driverID").val();
		var driverType = $("#driverType").val();
		
		var url = "<%=basePath%>truckDriverAction/saveDriverBefore?driID="+driID+"&driverType="+driverType;
	 
		$.webox({
				height:420,
				width:600,
				bgvisibel:true,
				title:'新增司机信息 ',
				iframe:url
		});
		
		
	}
	
	//显示车辆司机信息 
	function showDriverInfo(truckID)
	{
		var url = "<%=basePath%>truckDriverAction/getDriverInfoByTruckID?truckID="+truckID;
					$.webox({
							height:360,
							width:550,
							bgvisibel:true,
							title:' 司机详情 ',
							iframe:url
					});
	}
	
	//增加车辆司机信息 
	function updateDriverInfo(truckID)
	{
		var url = "<%=basePath%>truckDriverAction/intogetNoTruckDriversBysubno?truckID="+truckID;
					$.webox({
							height:560,
							width:600,
							bgvisibel:true,
							title:' 司机详情 ',
							iframe:url
					});
	}
	
	function delDriverInfo(truckID)
	{
		if(confirm("是否删除该车辆的司机关联?")){
		 
			$.post("<%=basePath%>truckDriverAction/delTruckDriverLink", 
					{ truckID:truckID },     
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
  		
 
	}
	function del()
	{
		var selected = $('#dg').datagrid('getSelected');
		
	    if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	   
	     	$.post("<%=basePath%>truckDriverAction/delTruck", 
					{ truckID:selected.ID },     
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
		     
		     	var url = "<%=basePath%>arkilometerAction/saveBefore?arkID="+selected.id;
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
	   
	    $('#dg').datagrid({width:t });
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

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>车辆司机管理</span></a></dt>
        <dd class="form">
        <div class="batch">
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	 
        	<input name="" onclick="del();" type="button" class="f_btn2" value="删除" /> 
        </div>
        <div class="search">
			 <input type="text" id="carNo" class="f_txt f_short" placeholder="车牌号"/> 
			  <input type="text" id="driverName" class="f_txt f_short" placeholder="司机姓名"/> 
			   <input type="text" id="driverTel" class="f_txt f_short" placeholder="手机号"/>  
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			
			</div>
			
			<!-- 车辆ID   -->
			<input type="hidden"  id="driverID" name="driverID"/>
			<!-- 司机主驾 副驾参数标识   1主驾   2副驾 -->
			<input type="hidden"  id="driverType" name="driverType"/>
		<table  id="dg" style="width:1000px;height:500px"  >
	
		</table>
			
		</dd>
    </dl>
</body>
</html>