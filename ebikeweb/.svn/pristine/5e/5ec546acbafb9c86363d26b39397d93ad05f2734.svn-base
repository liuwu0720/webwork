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
<title>对贷款申请的回复</title>

<%@include file="../../include/common.jsp" %>

<script type="text/javascript">
	//给予贷款 下拉框的 触发事件
	function displayTr(){
		var temp = $("#NRepulse").val();
		
		if(null!=temp　&& ""!=temp){
			if("0"==temp){
				$("#reject").hide();
				$("#vcCause").val("");
				$("#agree").show();
			}else{
				$("#reject").show();
				$("#agree").hide();
				$("#NAccrual").val("");
			}
		}else{
			$("#reject").hide();
			$("#agree").hide();
		}
	}
	// 提交数据
	function submitForm(){
		var NRepulse = $("#NRepulse").val();
		if(null==NRepulse　|| ""==NRepulse){
			alert("请选择是否给予贷款，并输入相关信息");
			return;
		}else{
			var url = "";
			var loanId = $("#loanId").val();
			var loanAskId = $("#loanAskId").val();
			if(null==loanAskId || ""==loanAskId)loanAskId="00";
			if(NRepulse=="0"){
				var NAccrual = $("#NAccrual").val();
				$("body").mask("数据处理中，请稍等……");
				$.post(url,{"loanAskId":loanAskId,"loanId":loanId,"NRepulse":NRepulse,"NAccrual":NAccrual},function(data){
					showResponse(data);
				});
			}else{
				var vcCause = $("#vcCause").val();
				$("body").mask("数据处理中，请稍等……");
				$.post(url,{"loanAskId":loanAskId,"loanId":loanId,"NRepulse":NRepulse,"vcCause":vcCause},function(data){
					showResponse(data);
				});
			}
		}
	}
	
	function showResponse(responseText) {
		$("body").unmask();
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
		    window.parent.weboxColse();
		  }
		}
</script>
</head>

<body class="main"  >
	<dl class="tabs">
		<dd class="form">
		
			<div style="border:1px red;float:left;"></div>
			<input type="hidden" name="loanId" id="loanId" value="${loan.id }" />
			<input type="hidden" name="loanAskId" id="loanAskId" value="${loanAsk.id }" />
			<table width="100%" border="0" class="table_form" style="float:right">
				<tr>
					<th width="120">贷款编号：</th>
					<td >${loan.vcLoanno }
						 </td>
					<th width="120">申请时间：</th>
					<td><fmt:formatDate value="${loan.dtApply }" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<th valign="top">申请理由：</th>
					<td colspan="3">
						${loan.vcExcuse }
					</td>
					
				</tr>
				<tr>
					<th width="120">贷款总额：</th>
					<td >${loan.NLoanTotal }
						 </td>
					<th width="120">期限：</th>
					<td><fmt:formatDate value="${loan.dtDeadline }" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr>
					<th width="120">抵押：</th>
					<td colspan="3">${loan.vcMortgage }
					</td>
				</tr>
				<tr>
					<th valign="top">申请内容：</th>
					<td colspan="3">
						${loan.vcNote }
					</td>
					
				</tr>
				<tr>
					<th width="120">是否给予贷款：</th>
					<td colspan="3">
						<select id="NRepulse" name="NRepulse" onchange="displayTr();" >
							<option value="">--请 选 择--</option>
							<option value="0">--给予贷款--</option>
							<option value="1">--拒绝贷款--</option>
						</select>
					</td>
				</tr>
				<tr id="reject" style="display:none;" >
					<th width="120">拒绝理由：</th>
					<td colspan="3">
						<textarea id="vcCause"  name="vcCause"  placeholder="可输入拒绝理由"  style="width:84.6%;height:150px"></textarea>
					</td>
				</tr>
				<tr id="agree" style="display:none;">
					<th width="120">利息：</th>
					<td colspan="3">
						<input type="text" id="NAccrual" name="NAccrual" placeholder="输入利息" />
					</td>
				</tr>
			</table>
			<div class="operate">
				<input type="button" value="返回" class="f_btn"  onclick="goback();" />
			</div>
		</dd>
	</dl>

</body>
</html>