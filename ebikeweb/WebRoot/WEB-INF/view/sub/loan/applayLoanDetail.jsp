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
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/libs/ystep/css/ystep.css" />

<%@include file="../../include/common.jsp" %>
<!-- 引入ystep插件 -->
<script src="${pageContext.request.contextPath}/static/js/libs/ystep/js/ystep.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(".ystep1").loadStep({
		      //ystep的外观大小
		      //可选值：small,large
		      size: "large",
		      //ystep配色方案
		      //可选值：green,blue
		      color: "blue",
		      //ystep中包含的步骤
		      steps: [{
		        //步骤名称
		        title: "填写申请",
		        //步骤内容(鼠标移动到本步骤节点时，会提示该内容)
		        content: "${msg1}"
		      },{
		        title: "提交申请",
		        content: "${msg2}"
		      },{
		        title: "洽谈中",
		        content: "${msg3}"
		      },{
		        title: "申请结束",
		        content: "${msg4}"
		      }]
		    });

		//设置到第几步了
		var step = '${step}';
		$(".ystep1").setStep(step);

	});
	function goback(){
		window.history.back(-1); 
	}
</script>
</head>

<body class="main"  >
	<dl class="tabs">
		<dd class="form">
		
			<div style="border:1px red;float:left;"></div>
			
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
					<th width="120">最佳贷款公司：</th>
					<td colspan="3">
						${loan.vcFinanceName }
					</td>
				</tr>
			</table>
			<div class="ystep1"></div>
			<div class="operate">
				<input type="button" value="返回" class="f_btn"  onclick="goback();" />
			</div>
		</dd>
	</dl>

</body>
</html>