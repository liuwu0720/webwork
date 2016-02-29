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
<title>角色编辑</title>
<%@include file="../../include/common.jsp" %>

<script type="text/javascript">
	
	

	$(document).ready(function() {
		
		
		$("#roleForm").validate({
			 rules: {
				 vcRoleName: "required",
				 vcRole: "required"
			 },
			 messages:{
				 vcRoleName:"角色名称必填",
				 vcRole: "角色代码必填"
			 },
			 submitHandler:function(form){
				 $("body").mask("数据处理中，请稍等……");
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>subAdminStaffAction/save",
			        success: showResponse
			      });
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
			
		});
		
	});
	
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		$("body").unmask();
		  if(json.isSuccess){
			  window.parent.$('#dg').datagrid('reload');  
		    window.parent.weboxColse();
		    
		  }
		}
	
</script>
</head>

<body class="main">
	<dl class="tabs">
		<dd class="form">
			<div style="border:1px red;float:left;"></div>
			<form id="roleForm" method="post" >
			<table width="100%" border="0" class="table_form" style="float:right">
				<tr>
					<th width="130">角色名称：</th>
					<td colspan="3"><input class="f_txt" type="text" id="vcRoleName" style="width:84.6%;"
						name="vcRoleName" value="${role.vcRoleName }"  placeholder="输入角色名称" />
						 </td>
				</tr>
				<tr>
					<th>角色代码：</th>
					<td width="200">
						ROLE_<input class="f_txt" type="text" id="vcRole" name="vcRole" style="width:60%"
						value="${role.vcRole }" placeholder="输入角色代码"/>
						<input  type="hidden" id="id" name="id" value="${role.id }"/>
						</td>
					<th>是否有效：</th>
					<td>
						<input name="NEnable" type="radio" value="0"
						checked="checked" />有效 <input name="NEnable" type="radio"
						value="1" />无效
						<input type="hidden" id="NEnabletxt" name="NEnabletxt"  value="${role.NEnable }" />
						<script type="text/javascript">
							var nable = $("#NEnabletxt").val();
							if(null!=nable&&""!=nable){
								$("input[type='radio'][name='NEnable'][value='"+nable+"']").attr("checked", "checked");
							}
						</script>
					</td>
				</tr>
				<tr >
					<th valign="top">角色描述：</th>
					<td colspan="3" >
						<textarea style="width:84.6%;height:150px" name="vcDesc" placeholder="输入角色描述">${role.vcDesc }</textarea>
						</td>
				</tr>
			</table>
			<input type="hidden" name="subbo" value="${subbo }"/>
			<div class="operate">
				<input type="submit" value="保存" class="f_btn" />
			</div>
			</form>
		</dd>
	</dl>
</body>
</html>