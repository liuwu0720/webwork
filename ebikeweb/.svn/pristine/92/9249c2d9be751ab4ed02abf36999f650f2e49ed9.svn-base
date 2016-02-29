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

<%@ include file="../../include/common.jsp" %>
<script type="text/javascript">
		
		
$(document).ready(function() {
		
	$("#myform").validate({
				 rules: {
					 dtStart :"required",
					 dtEnd : "required",
					  NCost:{
						 required: true,
						 number:true
					 }
					 
				 },
				 messages:{					
					 dtStart :"开始时间为空",
					 dtEnd :"结束时间为空",
					  NCost:{
						 required: "单价为空",
						 number: "必须为数字 "
					 }
				 },
				  submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>driverCostAction/arsaveByCost",
			        success: showResponse,
			        error:function(data){
			        	alert("error");
			        }
			      });
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
			    
				
			});
		

});
	function showResponse(responseText) 
	{
		var json = eval("("+responseText+")");
		alert(json.message);
		//$("body").unmask();
		  if(json.isSuccess){
			window.parent.$('#dg').datagrid('reload');  
		    window.parent.weboxColse();  
		  }
	}
		
	
	
</script>
</head>

<body class="main" >
 <form action="<%=basePath %>arkilometerAction/arbysaveArk" method="post" id="myform">
	<dl class="tabs">
    	<dt><a href="#"><span>新增单价</span></a></dt>
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
 
 
  <tr>
    <th>车牌号：</th>
    <td>
    	${driver.vcCarNo}
    	
    	</td>
    <th>司机姓名：</th>
    <td>
    	${driver.vcDriverName}
    </td>
  </tr>
  <tr>
    <th>司机电话：</th>
    <td>
   		 ${driver.vcDriverTel}
   	</td>
    <th> </th>
    <td>
    	 
    </td>
  </tr>
  <tr>
    <th width="110">开始时间：</th>
    <td><input class="f_txt" name="dtStart"  type="text" id="dtStart"  onfocus="WdatePicker({startDate:'%y-%M-01 ',dateFmt:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>
    	<img src="<%=basePath%>static/js/libs/My97DatePicker/skin/datePicker.gif"  width="24" height="33" alt=""   onclick="WdatePicker({el:'dtStart',startDate:'%y-%M-01 ',dateFmt:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>
    </td>
    <th width="120">结束时间：</th>
    <td> 
    <input class="f_txt" name="dtEnd" type="text" id="dtEnd"   onfocus="WdatePicker({startDate:'%y-%M-01 ',dateFmt:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>
    	<img src="<%=basePath%>static/js/libs/My97DatePicker/skin/datePicker.gif" width="24" height="33" alt=""    onclick="WdatePicker({el:'dtEnd',startDate:'%y-%M-01 ',dateFmt:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>
    </td>
  </tr>
  <tr>
    <th>单价：</th>
    <td><input class="f_txt" type="text" id="NCost" name="NCost"  /></td>
   
  </tr>

</table>
		<div class="operate"><input name="" type="submit" value="保存信息" class="f_btn" /></div>
        </dd>
    </dl>
    <input type="hidden" id="ITruckId" name="ITruckId" value="${driver.id}"/>
    
    </form>
</body>
</html>