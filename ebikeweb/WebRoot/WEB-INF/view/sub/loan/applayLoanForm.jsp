<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<title>申请贷款</title>
<%@include file="../../include/common.jsp" %>
<script type="text/javascript">
		

	$(document).ready(function() {
		var fno = $("#fno").val();
		if(null!=fno && ""!=fno){
			$("#vcFinanceno").val(fno);
		}
		var temp = $("#id").val();
		if(null!=temp && ""!=temp){
			$("#vcLoanno").removeAttr("disabled");
			$("#vcLoanno").attr("readonly","readonly");
			$("#dtApply").removeAttr("disabled");
			$("#dtApply").attr("readonly","readonly");
			
			var no = $("#financeno").val();
			if(null!=no){
				$("#vcFinanceno").val(no);
				setName();
			}
		}
		
		$("#loanForm").validate({
			 rules: {
				 NLoanTotal:{
					 required: true,
					 number:true
				 },
				 dtDeadline: "required",
				 vcMortgage:"required",
				 vcExcuse:"required"
			 },
			 messages:{
				 NLoanTotal:{
					 required: "贷款总额必填",
					 number:"贷款总额必须是数字"
				 },
				 dtDeadline: "期限必填",
				 vcMortgage:"抵押必填",
				 vcExcuse:"申请理由必填"
			 },
			 submitHandler:function(form){
				 $("body").mask("数据处理中，请稍等……");
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>loanAction/saveLoan",
			        success: showResponse
			      });
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
			
		});
		
	});
	
	function showResponse(responseText) {
		$("body").unmask();
		var json = eval("("+responseText+")");
		alert(json.message);
		
		  if(json.isSuccess){
			window.parent.$('#dg').datagrid('reload');  
		    window.parent.weboxColse();
		  }
		}
	
	//获取预想贷款的公司名称
	function setName(){
		var name = $("#vcFinanceno").find("option:selected").text();
		if(null!=name && ""!=name){
			$("#vcFinanceName").val(name);
		}
	}
	//保存并提交
	function saveAndSubmit(){
		$("#applayType").val("applay");
		$("form").submit();
	}
</script>
</head>

<body class="main" style="overflow-y: auto;height:500px;" >
	<dl class="tabs">
		<dd class="form">
		
			<div style="border:1px red;float:left;"></div>
			<form id="loanForm" method="post" action="">
			<input type="hidden" id="id" name="id" value="${loan.id }" />
			<input type="hidden" id="NEnable" name="NEnable" value="${loan.NEnable }" />
			<input type="hidden" id="vcFinanceno" name="vcFinanceno" value="${loan.vcFinanceno }" />
			<input type="hidden" id="IApplyUser" name="IApplyUser" value="${loan.IApplyUser }" />
			<input type="hidden" id="vcApplyUserName" name="vcApplyUserName" value="${loan.vcApplyUserName }" />
			<input type="hidden" id="vcSubno" name="vcSubno" value="${loan.vcSubno }" />
			<input type="hidden" id="vcSubAllName" name="vcSubAllName" value="${loan.vcSubAllName }" />
			<input type="hidden" id="applayType" name="applayType" value="save" />
			
			
			<table width="100%" border="0" class="table_form" style="float:right">
				<tr>
					<th width="120">贷款编号：</th>
					<td ><input class="f_txt" type="text" id="vcLoanno" style="width:150px"
						name="vcLoanno" value="${loan.vcLoanno }" disabled="disabled"   placeholder="系统自动生成" />
						 </td>
					<th width="120">申请时间：</th>
					<td><input class="f_txt" type="text" id="dtApply" disabled="disabled"  name="dtApply" style="width:150px;"
						value="<fmt:formatDate value="${loan.dtApply }" pattern="yyyy-MM-dd HH:mm:ss" />" placeholder="默认提交的时间"/>
					</td>
				</tr>
				<tr>
					<th valign="top">申请理由：</th>
					<td colspan="3">
						<textarea id="vcExcuse" name="vcExcuse" placeholder="输入贷款申请理由" style="width:84.6%;height:100px">${loan.vcExcuse }</textarea>
					</td>
					
				</tr>
				<tr>
					<th width="120">贷款总额：</th>
					<td ><input class="f_txt" type="text" id="NLoanTotal" style="width:150px"
						name="NLoanTotal" value="${loan.NLoanTotal }"  placeholder="输入贷款总额" />
						 </td>
					<th width="120">期限：</th>
					<td><input class="Wdate " type="text" id="dtDeadline" name="dtDeadline" style="width:150px;"
						value="<fmt:formatDate value="${loan.dtDeadline }" pattern="yyyy-MM-dd" />"   onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
					</td>
				</tr>
				<tr>
					<th width="120">抵押：</th>
					<td colspan="3"><input class="f_txt" type="text" id="vcMortgage" style="width:95%"
						name="vcMortgage" value="${loan.vcMortgage }"  placeholder="输入贷款抵押" />
					</td>
				</tr>
				<tr>
					<th valign="top">申请内容：</th>
					<td colspan="3">
						<textarea id="vcNote"  name="vcNote"  placeholder="输入详细的贷款申请内容"  style="width:84.6%;height:150px">${loan.vcNote }</textarea>
					</td>
					
				</tr>
				<tr>
					<th width="120">最佳贷款公司：</th>
					<td colspan="3">
						<select id="vcFinanceno" name="vcFinanceno"  style="98%" onchange="setName();">${options}</select>
						<input type="hidden" id="vcFinanceName" name="vcFinanceName" value="${loan.vcFinanceName }" />
						<input type="hidden" id="fno" name="fno" value="${loan.vcFinanceno }" />
						
					</td>
				</tr>
			</table>
			<div class="operate">
				<input type="submit" value="保存" class="f_btn" />&nbsp;&nbsp;<input type="button" value="保存并提交" class="f_btn"  onclick="saveAndSubmit();" />
			</div>
			</form>
		</dd>
	</dl>

</body>
</html>