<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>

<%@include file="../include/common.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
	
	$.validator.addMethod("isMobile", function(value, element) {
		  var length = value.length;
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		  //&& mobile.test(value)
		  return this.optional(element) || (length == 11 );
		 }, "请正确填写您的手机号码");
		 
		 
		$("#typeTab").show();
		$("#contentTab").hide();
		
		$("#myform").validate({
				 rules: {
					 vcAccount: "required",
					 vcPassword:
					 {
					 	required: true,
					 	 minlength: 6
					 },
					 vcPassword1:
					 {
					 	required: true,
					 	 minlength: 6,
					 	 equalTo: "#upass"
					 	 
					 },
					 phone:
					 {
						 required: true,
						 isMobile:true
					 },
					 ucheck :
					 {
					 	required: true
					 },
					 email:{
						required:true
					}
					 
				 },
				 messages:{
					 vcAccount:"用户名不能为空",
					 vcPassword : {
					 	required : "密码不能为空",
					 	minlength : "密码不能低于6位"
					 },
					 vcPassword1 : {
					 	required : "请输入确认密码",
					 	minlength : "确认密码不能低于6位",
					 	equalTo  : "两次密码输入不一致"
					 },
					phone :{
						required : "请输入手机号码",
						isMobile: "请输入正确的手机号码"
					},
					ucheck :
					 {
					 	required: "未选择协议"
					 },
					 email:{
						required:"请填写邮箱"
					}
				 },
				 submitHandler:function(form){
				 	var vcAccount = $.trim($("#uname").val());
				 	
				 	$.post("<%=basePath%>userAction/checkRegisterUname", 
					{ vcAccount:vcAccount },     
					   function (data, textStatus)
					   {     
							$("body").unmask();
							if(data.isSuccess == true)
							{
								 $("body").mask("数据处理中，请稍等……");
								 
							      $(form).ajaxSubmit({
							        type:"post",
							        url:"<%=basePath%>userAction/register",
							        success: showResponse,
							        error:function(data){
							        	alert("error");
							        }
							      });
							      
							}else
							{
								alert(data.message);
							}
					   }
				  ,"json");
				 
				 
				 
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
				 
				
			});
		

	});
	
	
function showResponse(json) 
	{

		//var json = eval("("+responseText+")");
		$("body").unmask();
		 if(json.isSuccess == true)
		{
			mnLogin();
		}else
		{
			alert(json.message);
		}
	}
	
	
	function toNext()
	{
		var selcal = $("input[name='usertypeID']:checked").val();
		if(typeof(selcal)=='undefined')
		{
		alert("请选择用户类型.");
		return;	
		}
		$("#typeTab").hide();
		$("#contentTab").show();
		
	}
	
	function mnLogin(){
		var uname = $("#uname").val();
		var upass = $("#upass").val();
		window.parent.$("#account").val(uname);
		window.parent.$("#password").val(upass);
		window.parent.webcolose(); 
		window.parent.$("body").mask("自动登录中，请稍等……");
		window.parent.$("form:first").trigger("submit");
		
	}
	
</script>
</head>

<body class="main" style="overflow-y: auto;">
<form action="userAction/register" id="myform" method="post">
	<dl class="tabs">
        <dd class="form">
        
        <table  width="100%" border="0" class="table_form" style="margin:100px 0 0 0; " id="typeTab">
        	<tr>
        		<td width="200px;"></td>
        		<td width="80px;" style="margin-left: 100px;" valign="top" ><font style="font-family: 微软雅黑;font-size: 20px;font-weight:bold;">我是:</font></td>
        		<td align="left">
        			<c:forEach items="${typelist}" var="artype">
    					<input type="radio" id="utypeID"  name="usertypeID" value="${artype.id}"/><font style="font-family: 微软雅黑;font-weight:bold;">${artype.vcArchive}</font></br>
    				</c:forEach> 
        		</td>
        	</tr>
        	<tr >
        		<td width="200px;"></td>
		    	<th>&nbsp;</th>
		    	<td><input name="" type="button" onclick="toNext();" value="下一步" class="f_btn" /></td>
  			</tr>  
        </table>
       
<table width="100%"  border="0" class="table_form" style="margin:50px 0 0 0;" id="contentTab">
  <tr>
    <th width="40%">用户名：</th>
    <td><input class="f_txt" type="text" id="uname" name="vcAccount"/></td>
  </tr>
  <tr>
    <th>登陆密码：</th>
    <td><input class="f_txt" type="password" id="upass" name="vcPassword" /></td>
  </tr>
   <tr>
    <th>确认密码：</th>
    <td><input class="f_txt" type="password" id="upass1" name="vcPassword1" /></td>
  </tr>
   <tr>
    <th>手机号码：</th>
    <td><input class="f_txt" type="text" id="phone" name="phone" /></td>
  </tr>
  <tr>
    <th>用户邮箱：</th>
    <td><input class="f_txt" type="text" id="email" name="email" /></td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><input name="ucheck" type="checkbox" value="" id="ucheck" /> 
      我已阅读，并同意协议</td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><input name="" type="submit"   value="注册" class="f_btn" /></td>
  </tr>  
  </table>
        </dd>
    </dl>
    </form>
</body>
</html>