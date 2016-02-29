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
<title>应收公里</title>

<%@ include file="../../include/common.jsp"%>
<script type="text/javascript">
		
		
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>arkilometerAction/saveArkilomter");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>arkilometerAction/saveArkilomter");
			//默认选中 起运城市  目的城市
			//prochange('stapro','stacity',"${stacity.id}");
		 $('select').attr("disabled",true)
			//prochange('endpro','endcity',"${endcity.id}");
		}
		
	$("#myform").validate({
				 rules: {
					 stacity : "required",
					 endcity : "required",
					 dtStart :"required",
					 dtEnd : "required",
					 NKilometer : "required"
				 },
				 messages:{
					// loadaddress:"<span ><font style='color: red;'>装货地址必须填写</font></span>",
					 stacity : "起始地不能为空",
					 endcity : "目的地不能为空",
					dtStart :"开始时间为空",
					dtEnd :"结束时间为空",
					 NKilometer:"公里数为空"
				 },
				  submitHandler:function(form){
				  	$(form).ajaxSubmit({
				        type:"post",
				        url:"<%=basePath%>arkilometerAction/saveArkilomter",
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
		
		//$("body").unmask();
		  if(json.isSuccess){
			window.parent.$('#dg').datagrid('reload');  
		    window.parent.weboxColse();  
		  }else{
			  alert(json.message);
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
	<form action="<%=basePath%>arkilometerAction/saveArkilomter"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>新增应收公里数</span> </a>
			</dt>
			<dd class="form">

				<table width="100%" border="0" class="table_form">



					<tr>
						<th>起运省份：</th>
						<td><select id="stapro" name="stapro"
							onchange="prochange('stapro','stacity');">
								<option value="0">---请选择---</option>
								<c:forEach items="${prolist}" var="pro">
									<c:choose>
										<c:when test="${pro.id == stapro.id}">
											<option value="${pro.id}" selected="selected">${pro.vcProvinceName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcProvinceName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select></td>
						<th>起运城市：</th>
						<td><select id="stacity" name="IStartId">
								<option value="">---请选择---</option>
						</select></td>
					</tr>
					<tr>
						<th>目的省份：</th>
						<td><select id="endpro" name="endpro"
							onchange="prochange('endpro','endcity')">
								<option value="0">---请选择---</option>
								<c:forEach items="${prolist}" var="pro">
									<c:choose>
										<c:when test="${pro.id == endpro.id}">
											<option value="${pro.id}" selected="selected">${pro.vcProvinceName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcProvinceName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select></td>
						<th>目的城市：</th>
						<td><select id="endcity" name="IEndId">
								<option value="">---请选择---</option>
						</select></td>
					</tr>
					<tr>
						<th width="110">开始时间：</th>
						<td><input class="f_txt Wdate" name="dtStart" type="text"
							id="dtStart"
							value="<fmt:formatDate value="${ark.dtStart}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>

						<th width="120">结束时间：</th>
						<td><input class="f_txt Wdate" name="dtEnd" type="text"
							id="dtEnd"
							value="<fmt:formatDate value="${ark.dtEnd}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>
					</tr>
					<tr>
						<th>公里数：</th>
						<td><input class="f_txt" type="text" id="NKilometer"
							name="NKilometer" value="${ark.NKilometer}" />
						</td>
						<th>客户：</th>
						<td><select id="iCustomerId" name="iCustomerId">
								<option value="0">---请选择---</option>
								<c:forEach items="${tCustomerList }" var="cu">
									<c:choose>
										<c:when test="${cu.id == ark.iCustomerId}">
											<option value="${ark.iCustomerId}"
												selected="selected">${cu.vcShortName}</option>
										</c:when>
										<c:otherwise>
											<option value="${cu.id}">${cu.vcShortName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>

				</table>
				<div class="operate">
					<input name="" type="submit" value="保存信息" class="f_btn" />
				</div>
			</dd>
		</dl>
		<input type="hidden" id="paramType" name="paramType"
			value="${paramType}" /> <input type="hidden" id="id" name="id"
			value="${ark.id}" /> <input type="hidden" id="NEnable"
			name="NEnable" value="${ark.NEnable}" /> <input type="hidden"
			id="vcSubno" name="vcSubno" value="${ark.vcSubno}" />

	</form>
</body>
</html>