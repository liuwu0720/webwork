<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建订单</title>

<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">
		
		 var stamap = '${staMapJson}';
	stamap = eval("("+stamap+")");
	
$(document).ready(function() {
	var w=getWidth(400);
	
	
	
	var headID = "${head.id}";
	var partype="${partype}";
	var shipno="${head.vcShipno}";
	//partype=="parsecure"
	//partype=="parEntrance"
	if(partype=="parsecure"){
		getAllShipVins(shipno);
	}else{
		initLeft(headID);
	}
	
});
	
	function initLeft(headID)
	{
		$('#dg').datagrid({     
			    url:'<%=basePath %>shipheadAction/getShipDetailsByID?headID='+headID,
			    cache : false,
			    type : 'post',
			    title:'指令详情', 
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			     fitColumns:true,
			    rownumbers:true,   //如果为true，则显示一个行号列。
			    striped:true,  //striped  是否显示斑马线效果。
			    singleSelect:true,  //如果为true，则只允许选择一行。
			    loadMsg:'正在加载,请稍等...',
			    columns:[[     
			        {field:'ID',title:'',checkbox:true,hidden:true, width:100},     
			        {field:'VC_ORDERNO',title:'订单号',width:100,align:'left'},     
			        {field:'VC_START_CITY',title:'起运城市',width:100,align:'left'}, 
			        {field:'VC_DEST_CITY',title:'目的城市',width:100,align:'left'},   
			        {field:'N_APKILOMETER',title:'公里数',width:100,align:'left'},
			        {field:'N_SHIP_QTY',title:'发货数量',width:100,align:'left'},
			     	{field:'LINESTATUS',title:'当前状态',width:80,align:'center',
		        	formatter:function(value,row,index){
		        		var result="";
		        		result = stamap[0][row.LINESTATUS];
		        		if(typeof(result) == 'undefined' )
		        		{
		        			result = "状态有误";
		        		}
		        		return result;
		        	}	
		        }
			      
			    ]], onLoadSuccess: function (data) {
				  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
	               //fitWidth(data);
	            },   
			     onLoadError:function(){
	                      alert("加载数据失败！");
	                  }
	                  
			    
			});   
	}
	function getAllShipVins(shipno){
		$('#dg').datagrid({
			url:'<%=basePath %>shipVinAction/getAllByShipno?shipno='+shipno,
			    cache : false,
			    type : 'post',
			    title:'vin码信息表', 
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			    fitColumns:true,
			    rownumbers:true,   //如果为true，则显示一个行号列。
			    striped:true,  //striped  是否显示斑马线效果。
			    singleSelect:true,  //如果为true，则只允许选择一行。
			    loadMsg:'正在加载,请稍等...',
			    columns:[[
			    	{field:'id',title:'id',checkbox:true,width:'100'},
			    	{field:'vcAdduser',title:'添加人姓名',width:'100',align:'center'},
			    	{field:'dtAdd',title:'添加时间',width:'100',align:'center',
			    		formatter:function(value,rec,index)
		        		{
		        			var unixTimestamp = new Date(value);   
                        	return unixTimestamp.toLocaleDateString();
		        		}
			    	},
			    	{field:'iUser',title:'添加人用户id',width:'100',align:'center'},
			    	{field:'vcVin',title:'vin码',width:'100',align:'center'},
			    	{field:'vcShipno',title:'发运指令号',width:'100',align:'center'}
			    ]],
			    onLoadSuccess: function (data) {
				  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
				  fitWidth(data);
	            },   
			     onLoadError:function(){
	                   alert("加载数据失败！");
	            }
		});
	}
</script>
</head>

<body class="main"  style="overflow-y: auto;height:500px;">
	<dl class="tabs">
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  <tr>
    <th  align="left">指令号：</th>
    <td > ${head.vcShipno} </td>
    <th >分供方编号：</th>
    <td>
    	${head.vcSubno}
    </td>
   
  </tr>
  <tr> 
   <th >车辆信息: </th>
    <td >  </td>
  </tr>
  
  <tr>
    <th>车辆编号：</th>
    <td >${truck.vcCarNo} </td>
   
  </tr>
  <c:choose>
  	<c:when test="${fn:length(driverlist) > 0}">
  		<c:forEach items="${driverlist}" var="driver">
  			<tr>
  				<td>司机姓名:</td>
  				<td>${driver.vcDriverName} </td>
  				<td>司机电话:</td>
  				<td>${driver.vcDriverTel} </td>
  			</tr>
  		</c:forEach>
  	</c:when>
  	<c:otherwise>
  		<tr>
  			<td colspan="2" align="center"><font style="font-family: 微软雅黑;font-weight: bold;">司机信息未维护！</font> </td>
  		</tr>
  	</c:otherwise>
  </c:choose>
<tr>
	
</tr>

</table>
<table id="dg">
	
</table>
		
        </dd>
    </dl>
 
    <input type="hidden" id="id" name="id" value="${sub.id}"/>
</body>
</html>