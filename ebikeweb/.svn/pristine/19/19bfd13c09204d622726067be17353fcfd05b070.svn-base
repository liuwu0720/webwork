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
<title>客户维护</title>

<%@include file="../../include/common.jsp" %>


<script type="text/javascript">
$(document).ready(function() {
	$.validator.addMethod("isMobile", function(value, element) {
		  var length = value.length;
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
		  //&& mobile.test(value)
		  return this.optional(element) || (length == 11 );
		 }, "请正确填写您的手机号码");
	
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>customerAction/saveCustomer");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>customerAction/saveCustomer");
			//默认选中 起运城市  目的城市
			prochange('prov','city',"${city.id}");
		}
		
		//保存
		$("#myform").validate({
			
			 rules: {
				 vcShortName : "required",
				 provinceId : "required",
				 cityId : "required",
				 vcPhone :{
					 required:true,
					 isMobile:true
				 }
			 },
			 messages:{
				 vcShortName : "客户简称不能为空",
				 provinceId : "省份不能为空",
				 cityId  : "城市不能为空",
				 vcPhone  : {
					 required : "请输入手机号码",
					 isMobile: "请输入正确的手机号码"
				 }
			 },
			  submitHandler:function(form){
							  $(form).ajaxSubmit
								 ({
									        type:"post",
									        url:"<%=basePath%>customerAction/saveCustomer",
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
		    window.parent.weboxClose();  
		  }
	}
		
	function prochange(pro,city,cityId)
	{
		
		 var proid =  $("#"+pro).val();
		 var citsel = $("#"+city)[0];
		 citsel.options.length = 1;
		 fullcity(proid,citsel,cityId);
		  
				  
	}
	function fullcity(proid,citsel,selop)
	{
		
		$.post("<%=basePath%>customerAction/getAllCityByProID", {
			proID : proid
		}, function(data, textStatus) {
			for ( var i = 0; i < data.length; i++) {
				var option = new Option(data[i].cityname, data[i].id);
				if (selop == data[i].id) {
					option.selected = true;
				}

				citsel.add(option);
			}

		}, "json");
	}
	
	

</script>
</head>

<body class="main">
	<form action="<%=basePath%>customerAction/saveCustomer" method="post" id="myform" >
		<dl class="tabs">
			<dt>
				<a href="#"><span>客户编辑</span> </a>
			</dt>
			<dd class="form">

				<table width="100%" border="0" class="table_form">
					<tr>
						<td>客户编号</td>
						<td><input class="f_txt" name="vcCustomerNo"  id="vcCustomerNo" readonly="readonly"
							value="${customer.vcCustomerNo }" />
						</td>
						<td>客户简称</td>
						<td><input class="f_txt" name="vcShortName"  id="vcShortName"
							value="${customer.vcShortName }" />
						</td>
					</tr>
					<tr>
						<td>客户联系人</td>
						<td><input class="f_txt" name="vcLinkman"
							value="${customer.vcLinkman }" />
						</td>
						<td>联系电话</td>
						<td><input class="f_txt" name="vcPhone"
							value="${customer.vcPhone }" />
						</td>
					</tr>
					<tr>
						<td>公司注册地址</td>
						<td><input class="f_txt" name="vcRegAddress"
							value="${customer.vcRegAddress }" />
						</td>
						<td></td>
						<td>
						</td>
					</tr>
					<tr>
						<td>公司地址</td>
						<td><input class="f_txt" name="vcAddress"
							value="${customer.vcAddress }" />
						</td>
						<td>客户全名</td>
						<td><input class="f_txt" name="vcCustomerName"
							value="${customer.vcCustomerName }" />
						</td>
					</tr>
					<tr>
						<td>公司所属省份</td>
						<td><select id="prov" name="provinceId"
							onchange="prochange('prov','city');">
								<option value="0">---请选择---</option>
								<c:forEach items="${prolist}" var="pro">
									<c:choose>
										<c:when test="${pro.id == customer.IProvince}">
											<option value="${pro.id}" selected="selected">${pro.vcProvinceName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcProvinceName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select></td>
						<td>公司所属城市</td>
						<td><select id="city" name="cityId">
								<option value="">---请选择---</option>
						</select>
						</td>
					</tr>

				</table>
				<div class="operate">
					<input  type="submit" value="保存信息" class="f_btn"  />
				</div>
			</dd>
		</dl>
		<input type="hidden" id="paramType" name="paramType"
			value="${paramType}" /> <input type="hidden" id="id" name="id"
			value="${customer.id}" /> <input type="hidden" id="NEnable"
			name="NEnable" value="${customer.NEnable}" /> <input type="hidden"
			id="vcSubno" name="vcSubno" value="${customer.vcSubno}" />

	</form>
</body>
</html>