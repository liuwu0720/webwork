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
<title>司机信息</title>

<%@ include file="../../include/common.jsp"%>
<script type="text/javascript">
			
$(document).ready(function(){
		  		 
	$("#myform").validate({
				 rules: {
					 vcCarStyle : "required"
				 },
				 messages:{
					 vcCarStyle : "请填写商品车名字"
				 },
				  submitHandler:function(form){
				  	 $(form).ajaxSubmit({
						type:"post",
						 url:"<%=basePath%>subCarStyleAction/save",
					     success : showResponse,
					     error : function(data) {
								alert("error");
						}
				});
			},
			showErrors : function(map, list) {
				topTip(map, list);
			}

		});
	
	if('${tSubCarStyle.NEnable }' == 0){
		$("#enable").attr("checked",true);
	}else{
		$("#disable").attr("checked",true);
	}

	});

function showResponse(responseText) 
{

	var json = responseText;	
	  if(json.isSuccess){
		window.parent.$('#dg').datagrid('reload');  
	    window.parent.weboxColse();  
	  }else{
		  alert(json.message);
	  }
}
</script>
</head>

<body class="main">
	<form action="<%=basePath%>subCarStyleAction/save" method="post"
		id="myform">
		<dl class="tabs">

			<dd class="form">
				<table width="100%" border="0" class="table_form">
					<tr>
						<th>商品车车名</th>
						<td><input type="text" class="f_txt" name="vcCarStyle" value="${tSubCarStyle.vcCarStyle }"/></td>
					</tr>
					<tr>
					
						<th>有效性</th>
						<td><input id="enable" type="radio" name="NEnable" value="0"/>有效
						    <input id="disable" type="radio" name="NEnable" value="1"/>无 效
						 <input type="hidden" name="id" value="${tSubCarStyle.id }"/>   
						  <input type="hidden" name="vcSubno" value="${tSubCarStyle.vcSubno }"/>  
						 </td>
					</tr>
				</table>
				<div class="operate">
					<input name="" type="submit" value="保存信息" class="f_btn" />&nbsp;&nbsp;
				</div>
			</dd>
		</dl>
	</form>
</body>
</html>