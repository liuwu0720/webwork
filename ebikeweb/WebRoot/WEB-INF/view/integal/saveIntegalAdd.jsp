<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>积分增加类型</title>

<%@ include file="../include/common.jsp" %>
<script type="text/javascript">
		
		
$(document).ready(function() {
		  
	$("#myform").validate({
				 rules: {
					 vcCause : "required",
					 NIntegal : 
					 {
					 	required : true,
					 	number : true 
					 },
					 dtBegin : "required",
					 dtEnd : "required"
				 },
				 messages:{
					 vcCause : "请填写加分原因.",
					 NIntegal :{
					 		required : "请填写加分值.",
					 		number : "请正确填写加分值."
					 } ,
					 dtBegin : "请选择开始时间.",
					 dtEnd : "请选择结束时间."
					  
				 },
				  submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
				  
				  	 $(form).ajaxSubmit
					 ({
						        type:"post",
						        url:"<%=basePath%>integalAddAction/save",
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
		 
		  if(json.isSuccess){
		     window.parent.$('#dg').datagrid('reload');  
		    window.parent.weboxColse();  
		    
		  }else
		  {
		  	alert(json.message);
		  }
	}
	
	 
</script>
</head>

<body class="main" >
 <form action="<%=basePath %>arkilometerAction/saveArkilomter" method="post" id="myform">
	<dl class="tabs">
    	
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  
	<tr>
    <td colspan="4">
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;"> 加分类型:</font> 
    </td>
  </tr>  
  <tr>
   <th>加分原因：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcCause" name="vcCause"  value="${integalAdd.vcCause}" />  
    </td>
    <th>金额:</th>
    <td>
      <input class="f_txt"  style="width: 60px;" type="text" id="NCredit" name="NCredit"   value="${integalAdd.NCredit}"/>  
    </td>
 </tr>
 <tr>
   <th>类型编码：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcCode" name="vcCode"  value="${integalAdd.vcCode}" />  
    </td>
    <th> 积分值：</th>
    <td>
        <input class="f_txt"  style="width: 80px;" type="text" id="NIntegal" name="NIntegal"  value="${integalAdd.NIntegal}" /> 
    </td>
 </tr>
 <tr>
   <th>开始日期：</th>
    <td>
       <input class="f_txt  Wdate" name="dtBegin" type="text" id="dtBegin"  style="width: 120px;padding-bottom: 8px;"  value="<fmt:formatDate value="${integalAdd.dtBegin}" pattern="yyyy-MM-dd" />"   onfocus="WdatePicker({startDate:'%y-%M-01',dtBegin:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
    	
    </td>
    <th>结束日期：</th>
    <td>
      <input class="f_txt Wdate" name="dtEnd" type="text" id="dtEnd"  style="width: 120px;padding-bottom: 8px; "  value="<fmt:formatDate value="${integalAdd.dtEnd}" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({startDate:'%y-%M-01',dtEnd:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
    		
    </td>
 </tr>
  
 
</table>
		<div class="operate"><input name="" type="submit"   value="保存信息" class="f_btn" />&nbsp;&nbsp; 
			</div>
        </dd>
    </dl>
    
    <input type="hidden" id="id" name="id" value="${integalAdd.id}"/>
    
    <input type="hidden" id="paramType" name="paramType" value=" " />
   
    
    </form>
</body>
</html>