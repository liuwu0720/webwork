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
<title>应收单价维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>driverCostAction/saveDriverCost");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>driverCostAction/saveDriverCost");
			//默认选中 起运城市  目的城市
			//prochange('stapro','stacity',"${stacity.id}");
		
			//prochange('endpro','endcity',"${endcity.id}");
			 $('select').attr("disabled",true)
		}
		
		//保存
		$("#myform").validate({
			rules: {
				dtStart : "required",
				dtEnd : "required"
				
			 },
			 messages:{
				 dtStart : "不能为空",
				 dtEnd : "不能为空"
				
			 },
			  submitHandler:function(form){
							  $(form).ajaxSubmit({
									        type:"post",
									        url:"<%=basePath%>driverCostAction/saveDriverCost",
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
	function showResponse(responseText){
		
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
	<form action="<%=basePath%>driverCostAction/saveDriverCost"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>应收单价编辑</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tDriverCost.id }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>生效日期</td>
						<td><input class="Wdate f_txt" name="dtStart"
							value="<fmt:formatDate value="${tDriverCost.dtStart}" pattern="yyyy-MM-dd" />" type="text" id="dtStart"
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
						</td>
						<td>失效日期</td>
						<td><input class="Wdate f_txt" name="dtEnd"
							value="<fmt:formatDate value="${tDriverCost.dtEnd}" pattern="yyyy-MM-dd" />" type="text" id="dtStart"
							onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
							</td>
					</tr>
					<tr>
						<td>单价</td>
						<td><input class="f_txt" name="nCost"
							value="${tDriverCost.nCost }" />
						</td>
						<td>车型</td>
						<td><select id="subCarStyle" name="ISubCarid">
								<option value="0">---请选择---</option>
								<c:forEach items="${subCarStyles }" var="sub">
									<c:choose>
										<c:when test="${sub.id == tDriverCost.ISubCarid}">
											<option value="${tDriverCost.ISubCarid}" selected="selected">${sub.vcCarStyle}</option>
										</c:when>
										<c:otherwise>
											<option value="${sub.id}">${sub.vcCarStyle}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td>起始地省份</td>
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
						</select>
						</td>
						<td>起始地城市</td>
						<td><select id="stacity" name="IStartId">
								<option value="">---请选择---</option>
						</select></td>
					</tr>
					<tr>
						<td>目的地省份：</td>
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
						</select>
						</td>
						<td>目的城市：</td>
						<td><select id="endcity" name="IEndId">
								<option value="">---请选择---</option>
						</select>
						</td>
					</tr>
					<tr>
						<td>客户：</td>
						<td><select id="ICustomerid" name="ICustomerid">
								<option value="0">---请选择---</option>
								<c:forEach items="${tCustomerList }" var="cu">
									<c:choose>
										<c:when test="${cu.id == tDriverCost.ICustomerid}">
											<option value="${tDriverCost.ICustomerid}"
												selected="selected">${cu.vcShortName}</option>
										</c:when>
										<c:otherwise>
											<option value="${cu.id}">${cu.vcShortName}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
						<td>支付方式</td>
						<td><select id="nType" name="nType">
							<option value="1">单公里</option>
							<option value="2">单台</option>
						   </select></td>
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