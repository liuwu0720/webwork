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
<title>司机工资系数维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		$("#myform").attr("action","<%=basePath%>driverAction/saveDriverSalaryCofflis");
		//保存
		$("#myform").validate({
			rules: {
				dtStart : "required",
				dtEnd : "required",
			    nPrice : "number"
			 },
			 messages:{
				 dtStart : "不能为空",
				 dtEnd : "不能为空",
				 nPrice:"有效数字"
			 },
			  submitHandler:function(form){
				  			if($("#nPrice").val()>1){
				  				alert("提成系数不能大于1");
				  				return;
				  			}
							  $(form).ajaxSubmit({
									        type:"post",
									        url:"<%=basePath%>driverAction/saveDriverSalaryCofflis",
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
	<form action="<%=basePath%>driverAction/saveDriverSalaryCofflis"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>司机工资系数编辑</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tSalaryCoefficient.id }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>生效日期</td>
					
						<td><input class="f_txt Wdate" name="dtStart" type="text"
							id="dtEnd"
							value="<fmt:formatDate value="${tSalaryCoefficient.dtStart}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>	
						<td>失效日期</td>
						<td><input class="f_txt Wdate" name="dtEnd" type="text"
							id="dtEnd"
							value="<fmt:formatDate value="${tSalaryCoefficient.dtEnd}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>	
					</tr>
					<tr>
						<td>单公里价</td>
						<td><input id="nPrice" class="f_txt" name="nPrice"
							value="${tSalaryCoefficient.nPrice }" />
						</td>
						
					</tr>
					<tr>
						<td>司机</td>
						<td><select id="stapro" name="iDriverId">
								<option value="0">---请选择---</option>
								<c:forEach items="${tDrivers}" var="pro">
									<c:choose>
										<c:when test="${pro.id == tSalaryCoefficient.iDriverId}">
											<option value="${pro.id}" selected="selected">${pro.vcDriverName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcDriverName}</option>
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
	<input type="hidden" id="paramType" name="paramType"
			value="${paramType}" />
	</form>
</body>
</html>