<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机信息</title>

<%@ include file="../../include/common.jsp" %> 
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">
		
$(document).ready(function(){
 
		$('#dg').datagrid({     
		    url:'<%=basePath %>truckDriverAction/getNoTruckDriversBysubno',
		    cache : false,
		    type : 'post',
		    title:'司机信息', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 2,
		    pageList :[5,10,15],
		   //  fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'ID',title:' ',width:100,hidden:true},     
		       
		        {field:'VC_DRIVER_NAME',title:'姓名',width:100,align:'left'}, 
		        {field:'VC_DRIVER_TEL',title:'电话',width:100,align:'right'},
		        {field:'VC_CARD',title:'身份证',width:100,align:'right'},
		       
		        {field:'1',title:'操作',width:60,align:'center',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:upDriverInfo(1,'+row.ID+',\''+row.VC_DRIVER_NAME+'\');" >主驾</a>'; 
		        		return result;
		        		}
		        } ,  {field:'2',title:'操作',width:60,align:'center',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:upDriverInfo(2,'+row.ID+',\''+row.VC_DRIVER_NAME+'\');" >副驾</a>'; 
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
	
	function upDriverInfo(paramType,driverID,driverName)
	{
		if(paramType==1)
		{
			$("#maintd").text("更改主驾信息  "+driverName);
			$("#mainDriverID").val(driverID); 
		}
		if(paramType==2)
		{
			$("#futd").text("更改副驾信息  "+driverName);
			$("#copilotDriverID").val(driverID); 
		}
		
		 
	}
	
	function sure()
	{
		var truckID = $("#truckID").val();
		var mainDriverID =  $.trim($("#mainDriverID").val());
		var copilotDriverID =  $.trim($("#copilotDriverID").val());
		var vcCarNo=$("#vcCarNo").val();//获取车牌号
		var ITuckType=$("#ITuckType").val();//获取车辆ID
		//alert("ITuckType:"+ITuckType);
		$.post("<%=basePath%>truckDriverAction/updateCardNoAndCarStyle",
				{truckID:truckID,vcCarNo:vcCarNo,ITuckType:ITuckType},
				function(data){
					alert(data.message);
				},
				"json");
		if(mainDriverID==copilotDriverID)
		{
			alert("主驾司机和副驾司机一个人,请重新选择.");
			return;
		}
		else
		{
			  $.post("<%=basePath%>truckDriverAction/updateMainDeiver", 
						{ truckID:truckID,mainDriverID:mainDriverID,copilotDriverID:copilotDriverID },     
						   function (data )
						   {     
								
								if(data.isSuccess == true)
								{
									
									window.parent.$('#dg').datagrid('reload');  
		   							 window.parent.weboxColse();  
								}else
								{
									alert(data.message);
								}
						   }
					  ,"json");
		}
	}
</script>
</head>

<body class="main" > 
	<dl class="tabs">
    	
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  <tr>
    <td colspan="4">
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">车辆信息:</font> 
    </td>
  </tr> 
    
	<tr>
	   <th>车牌号：</th>
	    <td>
	     	<input name="vcCarNo" id="vcCarNo" value="${truck.vcCarNo}"/>   
	    </td>
	    <th>车辆类型：</th>
	    <td>
	      <select style="width: 150px;" id="ITuckType" name="ITuckType">
    	 	<option value="${ttype.id}">${ttype.vcTypeName}</option>
    	 	<c:forEach items="${truckTypeList}" var="trType">
    	 		<c:if test="${ttype.id!=trType.id}">
    	 			<option value="${trType.id}">${trType.vcTypeName}</option>
    	 		</c:if>
    	 	</c:forEach>
    	 </select>
	    </td>
	 </tr>
	 
  <tr>
    <td colspan="4">
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">主驾司机信息:</font> 
    </td>
  </tr> 
   <c:choose>
   	<c:when test="${mainDriver!=null}">
	   <tr>
	   <th>姓名：</th>
	    <td>
	     	${mainDriver.vcDriverName}   
	    </td>
	    <th>手机号：</th>
	    <td>
	      ${mainDriver.vcDriverTel}  
	    </td>
	 </tr>
	 <tr>
	   <th>性别：</th>
	    <td>
	      	${mainDriver.vcSex}  
	    </td>
	    <th>身份证号：</th>
	    <td>
	      ${mainDriver.vcCard}  
	    </td>
	 </tr>
	  
   	</c:when>
   	<c:otherwise>
   		<tr>
   			<td colspan="4" align="center">
   				 <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">暂无主驾司机信息</font> 
   			</td>
   		</tr>
   	</c:otherwise>
   </c:choose> 
   
  
  	<tr>
    <td colspan="4">
    <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">副驾司机信息:</font> 
    </td>
  </tr> 
   <c:choose>
   	<c:when test="${copilotDriver!=null}">
	   <tr>
	   <th>姓名：</th>
	    <td>
	     	${copilotDriver.vcDriverName}   
	    </td>
	    <th>手机号：</th>
	    <td>
	      ${copilotDriver.vcDriverTel}  
	    </td>
	 </tr>
	 <tr>
	   <th>性别：</th>
	    <td>
	      	${copilotDriver.vcSex}  
	    </td>
	    <th>身份证号：</th>
	    <td>
	      ${copilotDriver.vcCard}  
	    </td>
	 </tr>
	  
	 <tr></tr>
   	</c:when>
   	<c:otherwise>
   		<tr>
   			<td colspan="4" align="center">
   				 <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">暂无副驾司机信息</font> 
   			</td>
   		</tr>
   	</c:otherwise>
   </c:choose> 
 
 <tr>
 	<td align="center" colspan="2" id="maintd"></td>
 	<td align="center" colspan="2" id="futd"></td>
 </tr>
 
</table>
		 
		 <table id="dg"  ></table> 
        </dd>
    </dl>
     
     <input type="hidden" id="truckID" name="truckID" value="${truck.id}"/>
   <input type="hidden" id="mainDriverID" name="mainDriverID"/>
   <input type="hidden" id="copilotDriverID" name="copilotDriverID"/>
    <input name="" id="subval" type="button" style="position: absolute;margin-top: 450px;margin-left: 300px;" value="确定" class="f_btn" onclick="sure();" />  
</body>
</html>