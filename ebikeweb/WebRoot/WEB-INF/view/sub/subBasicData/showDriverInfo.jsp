<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机信息</title>

<%@ include file="../../include/common.jsp" %>
<script type="text/javascript">
		
</script>
</head>

<body class="main" >
 <form action="" method="post" id="myform">
	<dl class="tabs">
    	
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
 
  <tr>
    <td colspan="4">
    <font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">主驾司机信息:</font> 
    </td>
  </tr> 
   <c:choose>
   	<c:when test="${mainDriver!=null}">
	   <tr>
	   <th>姓名：</th>
	    <td>
	     	${mainDriver.vcDriverName}   
	    </td>
	    <th>手机号：</th>
	    <td>
	      ${mainDriver.vcDriverTel}  
	    </td>
	 </tr>
	 <tr>
	   <th>性别：</th>
	    <td>
	      	${mainDriver.vcSex}  
	    </td>
	    <th>身份证号：</th>
	    <td>
	      ${mainDriver.vcCard}  
	    </td>
	 </tr>
	 <tr>
	   <th>籍贯：</th>
	    <td>
	     	${mainDriver.vcPlace}   
	    </td>
	    <th>出生日期：</th>
	    <td>
	      	${mainDriver.dtBirthday}	
	    </td>
	 </tr>
   	</c:when>
   	<c:otherwise>
   		<tr>
   			<td colspan="4" align="center">
   				 <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">暂无主驾司机信息</font> 
   			</td>
   		</tr>
   	</c:otherwise>
   </c:choose> 
   
  
  	<tr>
    <td colspan="4">
    <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">副驾司机信息:</font> 
    </td>
  </tr> 
   <c:choose>
   	<c:when test="${copilotDriver!=null}">
	   <tr>
	   <th>姓名：</th>
	    <td>
	     	${copilotDriver.vcDriverName}   
	    </td>
	    <th>手机号：</th>
	    <td>
	      ${copilotDriver.vcDriverTel}  
	    </td>
	 </tr>
	 <tr>
	   <th>性别：</th>
	    <td>
	      	${copilotDriver.vcSex}  
	    </td>
	    <th>身份证号：</th>
	    <td>
	      ${copilotDriver.vcCard}  
	    </td>
	 </tr>
	 <tr>
	   <th>籍贯：</th>
	    <td>
	     	${copilotDriver.vcPlace}   
	    </td>
	    <th>出生日期：</th>
	    <td>
	       
	    </td>
	 </tr>
	 <tr></tr>
   	</c:when>
   	<c:otherwise>
   		<tr>
   			<td colspan="4" align="center">
   				 <font id="" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;">暂无副驾司机信息</font> 
   			</td>
   		</tr>
   	</c:otherwise>
   </c:choose> 
   
  
 
</table>
		<div class="operate">
		</div>
        </dd>
    </dl>
    
    <input type="hidden" id="mainDriverID" name="mainDriverID" value="${mainDriver.id}"/>
    <input type="hidden" id="copilotDriverID" name="copilotDriverID" value="${copilotDriver.id}"/>
     
    
    </form>
</body>
</html>