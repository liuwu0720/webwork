<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户状态维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/saveUserStatus");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/saveUserStatus");
			
		}
		
		//保存
		$("#myform").validate({
			
			 rules: {
				 vcTag : "required",
			    
			 },
			 messages:{
				 vcTag : "不能为空",
				
			 },
			  submitHandler:function(form){
			  	 $(form).ajaxSubmit
				 ({
					        type:"post",
					        url:"<%=basePath%>basicManage/saveUserStatus",
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
		  
		  }
	}
		
	function prochange(pro,cit,selop)
	{
		var proid =  $("#"+pro).val();
		 var citsel = $("#"+cit)[0];
		 citsel.options.length = 1;
		 fullcity(proid,citsel,selop);
		
				  
				  
	}
	function fullcity(proid,citsel,selop)
	{
		
		$.post("<%=basePath%>customerAction/getAllCityByProID", {
			proID : proid
		}, function(data, textStatus) {
			if (data) {
				for ( var i = 0; i < data.length; i++) {
					var option = new Option(data[i].cityname, data[i].id);
					if (selop == data[i].id) {
						option.selected = true;
					}

					citsel.add(option);
				}
			}

		}, "json");
	}
</script>
</head>

<body class="main">
	<form action="<%=basePath%>basicManage/saveUserStatus" enctype="multipart/form-data"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>用户状态维护</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tUserStatus.id }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>用户状态</td>
						<td><input class="f_txt" name="vcStatus"
							value="${tUserStatus.vcStatus}" type="text" id="vcStatus" />
						</td>
						<th>是否有效：</th>
						<td><input name="nEnable" type="radio" value="0"
							checked="checked" />有效 <input name="nEnable" type="radio"
							value="1" />无效 <input type="hidden" id="nEnabletxt"
							name="NEnabletxt" value="${tUserStatus.nEnable }" /> <script
								type="text/javascript">
								var nable = $("#nEnabletxt").val();
								if (null != nable && "" != nable) {
									$("input[type='radio'][name='nEnable'][value='"+ nable + "']").attr(
											"checked", "checked");
								}
							</script>
							</td>
					</tr>
					<tr>
						<td>状态图标</td>
						<td>
							<input type="hidden" name="vcPath" value="${tUserStatus.vcPath}"/>
							<c:if test="${tUserStatus.vcPath != null}">
							<img src="<%=basePath%>static/statusimg/${tUserStatus.vcPath}" alt="" />
							</c:if>
						</td>
						
					</tr>
					<tr>
						<td>更改图标</td>
						<td>
						<input type="file" name="imageFile"/>
						</td>
					</tr>
				</table>
				<div class="operate">
					<input type="submit" value="保存信息" class="f_btn" />
				</div>
			</dd>
		</dl>

	</form>
</body>
</html>