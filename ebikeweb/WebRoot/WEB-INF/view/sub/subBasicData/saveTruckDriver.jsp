<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增司机信息</title>

<%@ include file="../../include/common.jsp" %>
<script type="text/javascript">
		
		
$(document).ready(function() {
		$.validator.addMethod("isMobile", function(value, element) {
		  var length = value.length;
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		  //&& mobile.test(value)
		  return this.optional(element) || (length == 11 );
		 }, "请正确填写您的手机号码");
		 
		 $.validator.addMethod("isCardNo", function(value, element) {
		  return this.optional(element) || isIdCardNo(value);
		 }, "请正确填写司机身份证号码");
		 
		 
		 
		 $("#driverDiv").hide();
		 
	$("#truckForm").validate({
				 rules: {
					 vcCarNo : "required",
					 ITruckTypeID : "required"
				 },
				 messages:{
					 vcCarNo : "请填写车牌号.",
					 ITruckTypeID : "请选择车辆类型."
					
				 },
				  submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
				  
				  
					 $(form).ajaxSubmit
					 ({
						        type:"post",
						        url:"<%=basePath%>truckDriverAction/saveTruckDriver",
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
		
		$("#driverForm").validate({
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
						        url:"<%=basePath%>truckDriverAction/saveDriverAndLink",
						        success: driverSuccess,
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

//增加车辆信息的 回调函数
	function showResponse(json) 
	{
		var partype =$("#paramType").val();
		//var json = eval("("+responseText+")");
		  if(json.isSuccess){  
		  	 var msg = json.message.split(";")[0];
		  	 var truckID = json.message.split(";")[1];
		  	 alert(msg);
		  	 alert("车辆ID    "+truckID+" "+partype);
			 
			if(partype=="addtruck")
			{
				window.parent.$('#dg').datagrid('reload');   
				window.parent.weboxColse();
				
			}else if(partype=="addDriver")
			{
				
				$("#driID").val(truckID); 
				$("#driType").val("1"); 
				$("#truckDiv").hide();
				$("#driverDiv").show();
			    //window.parent.showDriverInfo();
			} 
		  }else
		  {
		  	alert(json.message);
		  }
	}
	
	//司机保存 
	function driverSuccess(json)
	{
		var partype =$("#paramType").val();
		//var json = eval("("+responseText+")");
		 
		  if(json.isSuccess){
		  	 var msg = json.message.split(";")[0];
		  	 var truckID = json.message.split(";")[1];
		  	 alert(msg);
		  	  alert("车辆ID    "+truckID+" "+partype);
			 
			if(partype=="addDriverEnd")
			{
				window.parent.$('#dg').datagrid('reload');  
				window.parent.weboxClose();
			}else if(partype=="addDriverNext")
			{
				$("#driID").val(truckID); 
				$("#driType").val("2");
				$("#truckDiv").hide();
				$("#driverDiv").show();
				
				$("#vcDriverName").val("");
				$("#vcDriverTel").val("");
				$("#vcCard").val("");
				$("#vcPlace").val("");
				$("#dtBirthday").val("");
				
				$("#headStr").text("副驾司机信息:");
				$("#but_addDriverInfo").hide();
			
				//保存主驾之后 保存副驾  显示副驾
			    //window.parent.showDriverInfo();
			} 
		    
		  }else
		  {
		  	alert(json.message);
		  }
	}
	//增加司机 
	 
	//保存车辆信息
	  function addtruck(partype)
	  {
	  
		$("#paramType").val(partype);
		$("#truckForm").trigger("submit");
	  }
	  
	  //司机信息保存
	  function addDriverInfo(partype)
	  {
	  
		$("#paramType").val(partype);
		$("#driverForm").trigger("submit");
	  }
	  
	  
</script>
</head>

<body class="main" >
<div id="truckDiv">
	<form action="" method="post" id="truckForm">
	<dl class="tabs">
    	
        <dd class="form">
       
       <div id="truckTab">
       
       </div>
<table width="100%" border="0"  class="table_form">
  <tr>
  	<td colspan="4">
  	<font style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">车辆信息:</font> 
  	</td>
  </tr>  
  <tr>
    <th width="70px;">车牌号：</th>
    <td>
    	<input class="f_txt" style="width: 80px;" type="text" id="vcCarNo" name="vcCarNo"  placeholder="输入车牌号"/>	
    </td>
    <th>类型：</th>
    <td width="180px;">
    	 <select style="width: 150px;" id="ITruckTypeID" name="ITruckTypeID">
    	 	<option value="">---请选择---</option>
    	 	<c:forEach items="${truckTypeList}" var="trType">
    	 		<option value="${trType.id}">${trType.vcTypeName}</option>
    	 	</c:forEach>
    	 </select>
    </td>
     <th> </th>
    <td>
    	 
    </td>
  </tr>
 <tr>
 	<th>长：</th>
    <td>
    	<input class="f_txt" style="width: 80px;" type="text" id="NLength" name="NLength"  />	
    </td>
    <th>宽：</th>
    <td>
    	<input class="f_txt"  style="width: 80px;" type="text" id="NWidth" name="NWidth"  />	
    </td>
    <th  >高：</th>
    <td align="left" >
    	<input class="f_txt"  style="width: 80px;" type="text" id="NHeight" name="NHeight"  />	
    </td>
 </tr>
 <tr>
 	<th>自重：</th>
    <td>
    	<input class="f_txt"  style="width: 80px;" type="text" id="NSelfWeight" name="NSelfWeight" />	
    </td>
    <th>载重：</th>
    <td>
    	<input class="f_txt"  style="width: 80px;" type="text" id="NLoanWeight" name="NLoanWeight"  />	
    </td>
 </tr>
 
 
</table>
<div class="operate"><input name="" type="button" onclick="addtruck('addtruck');" value="保存信息" class="f_btn" />&nbsp;&nbsp; <input name="" onclick="addtruck('addDriver');" type="button" value="保存并添加司机信息" class="f_btn" /></div>
        </dd>
    </dl>
     
    
    </form>
	
</div>
 
    
    
<div id="driverDiv">
	
 <form action="" method="post" id="driverForm">
	<dl class="tabs">
    	
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  
	<tr>
    <td colspan="4">
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">主驾司机信息:</font> 
    </td>
  </tr>  
  <tr>
   <th>姓名：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcDriverName" name="vcDriverName"  />  
    </td>
    <th>手机号：</th>
    <td>
      <input class="f_txt"  style="width: 120px;" type="text" id="vcDriverTel" name="vcDriverTel"  />  
    </td>
 </tr>
 <tr>
   <th>性别：</th>
    <td>
    	<select id="vcSex" name="vcSex">
    		<option value="男">--男--  </option>
    		<option value="女">--女--  </option>
    	</select>
    </td>
    <th>身份证号：</th>
    <td>
      <input class="f_txt"  style="width: 160px;" type="text" id="vcCard" name="vcCard"  />  
    </td>
 </tr>
 <tr>
   <th>籍贯：</th>
    <td>
      <input class="f_txt"  style="width: 80px;" type="text" id="vcPlace" name="vcPlace"  />  
    </td>
    <th>出生日期：</th>
    <td>
      <input class="f_txt" name="dtBirthday" type="text" id="dtBirthday"  style="width: 120px;"   onfocus="WdatePicker({startDate:'%y-%M-01 ',dtBirthday:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>
    	<img src="<%=basePath%>static/js/libs/My97DatePicker/skin/datePicker.gif" width="24" height="33" alt=""    onclick="WdatePicker({el:'dtBirthday',startDate:'%y-%M-01 ',dateFmt:'yyyy-MM-dd ',alwaysUseStartDate:true})"/>	
    </td>
 </tr>
  
 
</table>
		<div class="operate"><input name="" type="button" onclick="addDriverInfo('addDriverEnd');" value="保存信息" class="f_btn" />&nbsp;&nbsp; 
			<input  id="but_addDriverInfo" type="button" onclick="addDriverInfo('addDriverNext');" value="保存并增加副驾" class="f_btn" /></div>
        </dd>
    </dl>
    
    <input type="hidden" id="driID" name="driID" value="${driID}"/>
    <input type="hidden" id="driType" name="driType" value="${driverType}"/>
    <input type="hidden" id="paramType" name="paramType"  />
    
    
    </form>

</div>
		
</body>
</html>