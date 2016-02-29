<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>app版本信息详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="../../include/common.jsp" %>
	<script type="text/javascript">
		$.validator.addMethod("nVersionCode",function(value,elment){
			var regexp=/^\d+$/;
			//alert(regexp.test(value));
			return regexp.test(value);
		},"版本号必须是数字");
		$(function(){
			$('#myform').validate({
				rules:{
					nVersionCode:{
						required:true,
						nVersionCode:true
					},
					vcVersionName:"required",
					nUpdateType:"required",
					//file:"required",
					vcNote:"required"
				},
				messages:{
					nVersionCode:{
						required:"请填写版本号",
						nVersionCode:"请填写一个整数",
					},
					vcVersionName:"请填写版本号名称",
			        nUpdateType:"请选择更新类型",
					//file:"请选择上传的文件",
					vcNote:"请填写版本说明"
				},
				submitHandler:function(form){
					$(form).ajaxSubmit({
						type:"post",
						url:"<%=basePath%>appVersionAction/save",
				        success: showResponse,
				        error:function(data){
				        	alert("error");
				        }
					});
				},
				showErrors: function(map, list){
					topTip(map, list);
				}
				
			});
		});
		function showResponse(responseText) 
		{
			var json = eval("("+responseText+")");
			alert(json.message);
			//$("body").unmask();
			 if(json.isSuccess){
				window.parent.$('#dg').datagrid('reload');  
				window.parent.weboxClose();
			  }
		}
	</script>
  </head>
  
  <body class="main">
    <form action="<%=basePath%>appVersionAction/save" method="post" id="myform" enctype="multipart/form-data">
    	<dl class="tabs">
    		<dt>
    			<a href="#"><span>app版本编辑</span> </a>
    		</dt>
    		<dd class="form">
    			<table width="100%" border="0" class="table_form">
    				<tr>
    					<td style="width:80px">版本号:</td>
    					<td>
    						<input type="text" name="nVersionCode" id="nVersionCode" class="f_txt" 
    						style="width: 120px;padding-bottom: 8px;" value="${appVersion.nVersionCode }"/>
    					</td>
    					<td>版本号名称:</td>
    					<td>
    						<input type="text" name="vcVersionName" id="vcVersionName" class="f_txt" 
    						style="width: 120px;padding-bottom: 8px;" value="${appVersion.vcVersionName}"/>
    					</td>
    				</tr>
    				<tr>
    					<td >更新类型:</td>
    					<td>
    						<select name="nUpdateType" id="nUpdateType">
    							<c:choose>
    								<c:when test="${appVersion.nUpdateType==null}">
    									<option value="0">---请选择---</option>
    									<option value="0">选择更新</option>
    									<option value="1">强制更新</option>
    								</c:when>
    								<c:when test="${appVersion.nUpdateType==0}">
    									<option value="${appVersion.nUpdateType}">选择更新</option>
    									<option value="1">强制更新</option>
    								</c:when>
    								<c:otherwise>
    									<option value="${appVersion.nUpdateType}">强制更新</option>
    									<option value="0">选择更新</option>
    								</c:otherwise>
    							</c:choose>
    							
    						</select>
    					</td>
    					<td>上传文件:</td>
    					<td>
    						<input type="file" name="file" />
    					</td>
    				</tr>
    				<c:if test="${appVersion.vcAppUrl!=null}">
    					<tr >
    						<td >app下载地址:</td>
    						<td colspan="3">${appVersion.vcAppUrl}</td>
    					</tr>
    				</c:if>
    				<tr>
    					<td >版本说明:</td>
    					<td colspan="3">
    						<textarea rows="10" cols="50" name="vcNote">${appVersion.vcNote}</textarea>
    					</td>
    				</tr>
    			</table>
    			<div class="operate"><input name="" type="submit"   value="保存信息" class="f_btn" />&nbsp;&nbsp; 
				</div>
    		</dd>
    	</dl>
    	<input type="hidden" name="id" value="${appVersion.id}"/>
    	<input type="hidden" name="NEnable" value="${appVersion.nEnable}"/>
    	<input type="hidden" name="dtAdd" value="<fmt:formatDate value="${appVersion.dtAdd}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
    	<input type="hidden" name="vcAppUrl" value="${appVersion.vcAppUrl}" />
    	<input type="hidden" name="IUser" value="${appVersion.iUser}" />
    	<input type="hidden" name="vcUserName" value="${appVersion.vcUserName}" />
    </form>
  </body>
</html>
