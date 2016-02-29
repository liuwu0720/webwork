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
<title>用户</title>
<link href="<%=basePath%>static/css/backhome.css" rel="stylesheet"
	type="text/css" />
<%@include file="../../include/common.jsp" %>
<%@include file="../../include/ztreeCommon.jsp" %>
<script type="text/javascript">
		

	$(document).ready(function() {
		$("#userForm").validate({
			 rules: {
				 vcUsername: "required",
				 vcAccount: "required"
			 },
			 messages:{
				 vcUsername:"用户名称必填",
				 vcAccount: "登陆账号必填"
			 },
			 submitHandler:function(form){
				 $("body").mask("数据处理中，请稍等……");
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>subAdminStaffAction/saveUser",
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
			<form id="userForm" method="post" >
			<table width="100%" border="0" class="table_form" style="float:right">
				<tr>
					<th width="120">用户姓名：</th>
					<td ><input class="f_txt" type="text" id="vcUsername" style="width:150px"
						name="vcUsername" value="${tuser.vcUsername }"  placeholder="输入用户姓名" />
						 </td>
					<th width="120">账号：</th>
					<td><input class="f_txt" type="text" id="vcAccount" name="vcAccount" style="width:150px;"
						value="${tuser.vcAccount }" placeholder="输入登陆账号"/>
						<input  type="hidden" id="id" name="id" value="${tuser.id }"/></td>
				</tr>
				<tr>
					<th>密码：</th>
					<td>
						<input class="f_txt" type="text" id="vcRole" name="vcPassword" 
						value="${tuser.vcPassword }" style="width:150px;" placeholder="初始化密码默认：123456"/>
					</td>
					<th>是否有效：</th>
					<td>
						<input name="NEnable" type="radio" value="0"
						checked="checked" />有效 <input name="NEnable" type="radio"
						value="1" />无效
						<input type="hidden"  name="IArchiveType"  value="${tuser.IArchiveType }" />
						<input type="hidden"  name="NIntegral"  value="${tuser.NIntegral }" />
						<input type="hidden"  name="iArchive"  value="${tuser.iArchive }" />
						<input type="hidden"  name="NApplyResource"  value="${tuser.NApplyResource }" />
						<script type="text/javascript">
							var nable = $("#NEnabletxt").val();
							if(null!=nable&&""!=nable){
								$("input[type='radio'][name='NEnable'][value='"+nable+"']").attr("checked", "checked");
							}
						</script>
					</td>
				</tr>
			</table>
			<div class="operate">
				<input type="submit" value="保存" class="f_btn" />
			</div>
			</form>
		</dd>
	</dl>
	<div id="menuContent" class="menuContent"
		style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree"
			style="margin-top:0; width:180px; height: 300px;"></ul>
	</div>

</body>
</html>