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
<script type="text/javascript">
			
$(document).ready(function(){
		  
		$.validator.addMethod("isMobile", function(value, element) {
		  var length = value.length;
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		  //&& mobile.test(value)
		  return this.optional(element) || (length == 11 );
		 }, "请正确填写您的手机号码");
		 
		 $.validator.addMethod("isCardNo", function(value, element) {
		  return this.optional(element) || isIdCardNo(value);
		 }, "请正确填写司机身份证号码");
		 
		 
		 
	$("#myform").validate({
				 rules: {
					 vcDriverName : "required",
					 vcDriverTel : 
					 {
					 	required : true,
					 	isMobile : true
					 },
					 vcCard : {
						 required : true,
						 isCardNo : true
					 }
				 },
				 messages:{
					 vcDriverName : "请填写司机姓名.",
					 vcDriverTel :{
					 		required : "请填写手机号.",
					 		isMobile : "请正确填写您的手机号码."
					 } ,
					 vcCard : {
					 	required : "请填写身份证号.",
					 	isCardNo : "请正确填写司机身份证号码."
					 }
					 
					
				 },
				  submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
				  
				  	 $(form).ajaxSubmit
					 ({
						        type:"post",
						        url:"<%=basePath%>truckDriverAction/saveDriver",
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


function showResponse(json){
	
	//var json = eval("("+responseText+")");
	
	  if(json.isSuccess){
		
		window.parent.$('#dg').datagrid('reload');  
	    window.parent.weboxClose();  
	  }else{
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
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;"> 司机信息:</font> 
    </td>
  </tr>  
  <tr>
   <th>姓名：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcDriverName" name="vcDriverName"  value="${driver.vcDriverName}" />  
    </td>
    <th>手机号：</th>
    <td>
      <input class="f_txt"  style="width: 120px;" type="text" id="vcDriverTel" name="vcDriverTel"  value="${driver.vcDriverTel}"/>  
    </td>
 </tr>
 <tr>
   <th>性别：</th>
    <td>
    	<select id="vcSex" name="vcSex">
    		<c:choose>
    			<c:when test="${driver.vcSex=='女'}">
    				<option value="男" >--男--  </option>
    				<option value="女" selected="selected">--女--  </option>
    			</c:when>
    			<c:otherwise>
    				<option value="男" selected="selected">--男--  </option>
    				<option value="女" >--女--  </option>
    			</c:otherwise>
    		</c:choose>
    		
    		
    	</select>
    </td>
    <th>身份证号：</th>
    <td>
      <input class="f_txt"  style="width: 160px;" type="text" id="vcCard" name="vcCard"  value="${driver.vcCard}" />  
    </td>
 </tr>
 <tr>
   <th>籍贯：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcPlace" name="vcPlace"  value="${driver.vcPlace}"  />  
    </td>
    <th>出生日期：</th>
    <td>
      <input class="f_txt Wdate" name="dtBirthday" type="text" id="dtBirthday" style="width: 120px;padding-bottom: 8px;"  value="<fmt:formatDate value="${driver.dtBirthday}" pattern="yyyy-MM-dd" />" onfocus="WdatePicker({startDate:'%y-%M-01',dtBirthday:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
    		
    </td>
 </tr>
  
 
</table>
		<div class="operate"><input name="" type="submit"   value="保存信息" class="f_btn" />&nbsp;&nbsp; 
			</div>
        </dd>
    </dl>
    
    <input type="hidden" id="id" name="id" value="${driver.id}"/>
    
    <input type="hidden" id="paramType" name="paramType" value="${paramType }" />
   
    
    </form>
</body>
</html>