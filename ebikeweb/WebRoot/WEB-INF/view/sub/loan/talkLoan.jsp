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
<style type="text/css">
ul{list-style-type:none; margin:0;width:100%; }
ul li{ width:50%; float:left;}
</style>
<script type="text/javascript">
function goback(){
	window.history.back(-1); 
}
$(document).ready(function(){ 
	$("input[type='radio']").change(function(){
		var temp = $("input[name='allFinance']:checked").val();
		if(temp=='1'){
			//清空金融公司的复选框
			$("input[type='checkbox'][name='cfinance']").each(function(){
				$(this).attr("checked", false);
			});
			$("#chooseFinances").show();
			$("#finTr").show();
		}else{
			$("#chooseFinances").hide();
			$("#finTr").hide();
		}
	});
});

//洽谈的处理js
function talk(){
	var temp = $("input[name='allFinance']:checked").val();
	var arr = new Array();
	var financeIds = "0";
	var loanId = $("#id").val();
	if(temp=='1'){
		$("input[type='checkbox'][name='cfinance']:checked").each(function(index){
			arr[index] = $(this).val();
		});
		var financeIds = arr.join(",");
	}
	var url = "<%=basePath%>loanAction/meetFinance";
	$("body").mask("数据处理中，请稍等……");
	$.post(url,{"loanId":loanId,"financeIds":financeIds},function(data){
		showResponse(data);
	});
	
}


function showResponse(responseText) {
	$("body").unmask();
	var json = eval("("+responseText+")");
	alert(json.message);
	  if(json.isSuccess){
		  goback();
	  }
	}
</script>
</head>

<body class="main"  >
	<dl class="tabs">
		<dd class="form">
		
			<div style="border:1px red;float:left;"></div>
			<input type="hidden" id="id" name="id" value="${loan.id }" />
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
				<tr>
					<th>询问全部</th>
					<td colspan="3">
						<input  type="radio"  name="allFinance" value="0"  checked="checked" />是		
						<input  type="radio"  name="allFinance" value="1" />否		
					</td>
				</tr>
				<tr id="chooseFinances" style="display: none;">
					<th>选择询问:</th>
					<td colspan="3"></td>
				</tr>
				<tr id="finTr" style="display: none;" >
					<th></th>
					<td colspan="3">
					<ul>
					<c:forEach var="finance" items="${finances}" varStatus="status">
						<c:if test="${(status.index>1)&&(status.index % 2 ==0) }"><br/></c:if>
						<li><input type="checkbox" name="cfinance" value="${finance.id }" name="financeIds" />${finance.vcAllName }</li>
					</c:forEach>
					</ul>
					</td>
				</tr>
			</table>
			<div class="ystep1"></div>
			<div class="operate">
				<input type="button" value="返回" class="f_btn"  onclick="goback();" />
				<input type="button" value="进行洽谈" class="f_btn"  onclick="talk();" />
			</div>
		</dd>
		
	</dl>
	<div id="menuContent" class="menuContent"
		style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree"
			style="margin-top:0; width:180px; height: 300px;"></ul>
	</div>

</body>
</html>