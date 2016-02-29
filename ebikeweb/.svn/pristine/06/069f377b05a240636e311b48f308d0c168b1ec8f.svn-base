<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@include file="../include/common.jsp" %>
<script type="text/javascript">
function colseDiv(){
	window.parent.weboxColse();
}

</script>
</head>

<body class="main"  style="overflow-y: auto;height:460px;">
	<dl class="tabs">
        <dd class="form">
<table width="100%" border="0" class="table_form">
  <tr>
    <th width="130">抢单编号：</th>
    <td width="200">${product.vcKillno}</td>
    <th width="130">最高价：</th>
    <td>${product.NMinPrice}</td>
  </tr>
  <tr>
    <th>起始点：</th>
    <td colspan="3">${product.vcStart}</td>
  </tr>
  <tr>
    <th>目的地：</th>
    <td colspan="3">${product.vcEnd}</td>
  </tr>
  <tr>
    <th>公里数：</th>
    <td>${product.NKilometre}</td>
    <th>要求抵达时间：</th>
    <td>
    	<fmt:formatDate value="${product.dtArriveTime }" pattern="yyyy-MM-dd" />
	</td>
  </tr>
  <tr>
    <th>抢单开始时间：</th>
    <td>
    	<fmt:formatDate value="${product.dtKillStart }" pattern="yyyy-MM-dd HH:mm:ss"  />
    </td>
    <th>抢单结束时间：</th>
    <td>
    	<fmt:formatDate value="${product.dtKillEnd }" pattern="yyyy-MM-dd HH:mm:ss" />
   </td>
  </tr>
  <tr>
    <th valign="top">特别要求：</th>
    <td colspan="3" valign="top">
    	${product.vcRequire }
    </td>
    
  </tr>
  </table>
  		<div class="block">
        	<div class="block_th">订单详情</div>
            <div class="block_con">
                <table width="100%" border="0" class="table_form">
                <tbody id="tbody">
                <c:choose>
                <c:when test="${!empty atype}">
                	<c:forEach items="${list }" var="pcs">
                		<tr id="tr_0">
		                    <th>车型：</th>
		                    <td>${pcs.vcCarStyle }</td>
		                    <th>数量：</th>
		                    <td>${pcs.NCarCount }</td>
		                  </tr>
                	</c:forEach>
                </c:when>
                <c:otherwise>
                	<c:forEach items="${list }" var="pcs">
                		<tr id="tr_0">
		                    <th>车型：</th>
		                    <td>${pcs.vcCarStyle }</td>
		                    <th>数量：</th>
		                    <td>${pcs.NCarCount }</td>
		                    <th>单价：</th>
		                    <td>${pcs.NPrice }</td>
		                  </tr>
                	</c:forEach>
                	
                </c:otherwise>
                </c:choose>
                	
                  </tbody>
                </table> 
            </div>
            <div class="block_th">中标分供方</div>
            <div class="block_con">
                <table width="100%" border="0" class="table_form">
                <tbody id="tbody">
                		<tr id="tr_0">
		                    <th>分供方：</th>
		                    <td>${kill.vcAllName }</td>
		                    <th>投标时间：</th>
		                    <td>
		                    	<fmt:formatDate value="${product.dtKillEnd }" pattern="yyyy-MM-dd HH:mm:ss" />
		                    </td>
		                    <th>总额：</th>
		                    <td>${kill.NTotalPrice }</td>
		                  </tr>
                  </tbody>
                </table> 
            </div>
        </div>
      </dd>
    </dl>
</body>
</html>