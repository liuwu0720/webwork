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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息推送</title>
<%@include file="../../include/common.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#myform").attr("action","<%=basePath%>basicManage/savePushShareTag");
		//保存
		$("#myform").validate({
			
			 rules: {
				 shareTitle : "required",
			    
			 },
			 messages:{
				 shareTitle : "不能为空",
				
			 },
			  submitHandler:function(form){
			  	 $(form).ajaxSubmit
				 ({
					        type:"post",
					        url:"<%=basePath%>basicManage/savePushShareTag",
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
</script>
</head>

  
  <body class="main">
    	<form action="<%=basePath%>basicManage/savePushShareTag"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>消息推送</span> </a>
			</dt>
			<dd class="form">
				<table width="100%" border="0" class="table_form">
					<tr>
						<th>消息标题: </th>
						<td><input class="f_txt" name="shareTitle"
							value="${shareTitle}" type="text" id="vcTag" />
						</td>
						
					</tr>
					<tr>
						<th>推送范围:</th>
						<td>
							<select name="type">
								<option selected="selected" value="0">所有</option>
								<option value="1">内部</option>
								<option value="2">分供方</option>
								<option value="3">司机</option>
								<option value="4">金融公司</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="operate">
					<input type="submit" value="确认" class="f_btn" />
				</div>
			</dd>
		</dl>

	</form>
  </body>
</html>
