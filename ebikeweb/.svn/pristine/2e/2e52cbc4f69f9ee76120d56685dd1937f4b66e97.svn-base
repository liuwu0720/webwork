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
<title>司机工资补贴维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		$("#myform").attr("action","<%=basePath%>driverAction/saveDriverSalarySubside");
		//保存
		$("#myform").validate({
			rules: {
				dtStart : "required",
				dtEnd : "required",
				nNumber : "number"
			 },
			 messages:{
				 dtStart : "不能为空",
				 dtEnd : "不能为空",
				 nNumber:"有效数字"
			 },
			  submitHandler:function(form){
							  $(form).ajaxSubmit({
									        type:"post",
									        url:"<%=basePath%>driverAction/saveDriverSalarySubside",
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
		  
		  }else{
			  alert(json.message);
		  }
	}
		

</script>
</head>

<body class="main">
	<form action="<%=basePath%>driverAction/saveDriverSalarySubside"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>司机工资补贴编辑</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tDriverSubsides.id }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>生效日期</td>					
						<td><input class="f_txt Wdate" name="dtStart" type="text"
							id="dtEnd"
							value="<fmt:formatDate value="${tDriverSubsides.dtStart}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>	
						<td>失效日期</td>
						<td><input class="f_txt Wdate" name="dtEnd" type="text"
							id="dtEnd"
							value="<fmt:formatDate value="${tDriverSubsides.dtEnd}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>	
					</tr>
					<tr>
						<td>补贴名称</td>
						<td><input class="f_txt" name="vcName"
							value="${tDriverSubsides.vcName }" />
						</td>
						<td>补贴金额</td>
						<td><input class="f_txt" name="nNumber"
							value="${tDriverSubsides.nNumber }" />
						</td>
					</tr>
					
				</table>
				<div class="operate">
					<input type="submit" value="保存信息" class="f_btn" />
				</div>
			</dd>
		</dl>
	<input type="hidden" id="paramType" name="paramType"
			value="${paramType}" />
			
	</form>
</body>
</html>