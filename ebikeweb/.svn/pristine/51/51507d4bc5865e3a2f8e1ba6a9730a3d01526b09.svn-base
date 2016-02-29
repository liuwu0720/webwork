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
<title>图片路径维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/savePicPath");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/savePicPath");
			
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
					        url:"<%=basePath%>basicManage/savePicPath",
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
	<form action="<%=basePath%>basicManage/savePicPath"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>图片路径维护</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tPicPath.id }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>图片路径</td>
						<td><input class="f_txt" name="vcPath"
							value="${tPicPath.vcPath}" type="text" id="vcTag" />
						</td>
						<th>是否有效：</th>
						<td><input name="nEnable" type="radio" value="0"
							checked="checked" />有效 <input name="nEnable" type="radio"
							value="1" />无效 <input type="hidden" id="nEnabletxt"
							name="NEnabletxt" value="${tPicPath.nEnable }" /> <script
								type="text/javascript">
								var nable = $("#nEnabletxt").val();
								if (null != nable && "" != nable) {
									$(
											"input[type='radio'][name='nEnable'][value='"
													+ nable + "']").attr(
											"checked", "checked");
								}
							</script>
							</td>
					</tr>
					<tr>
						<td>图片分类</td>
						<td>
							<select name="iPicType">
								<c:forEach items="${tPictypes }" var="tpic">
								<c:choose>
										<c:when test="${tpic.id == tPicPath.iPicType}">
											<option value="${tpic.id}" selected="selected">${tpic.vcPicType}</option>
										</c:when>
										<c:otherwise>
											<option value="${tpic.id}">${tpic.vcPicType}</option>
										</c:otherwise>
								</c:choose>		
								</c:forEach>
							</select>
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