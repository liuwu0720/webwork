<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建订单</title>

<%@ include file="../../include/common.jsp" %>
<script type="text/javascript">
		
		
$(document).ready(function() {
	
		

});
	
	
	function checkSub()
	{
		var subid = "${sub.id}";
		var selval = $("#checResult").val();
		var remarks = $("#remarks").val();
		
		$.post("<%=basePath%>subsuppliersAction/approvalApplyOrder", 
					{ subID:subid,selval:selval,remarks:remarks },     
					   function (data, textStatus)
					   {     
							if(data.result="ok")
							{
								alert("保存成功.");
								window.parent.$('#dg').datagrid('reload');  
		   						window.parent.weboxColse();  
							}else
							{
								alert("保存失败"+data.result);
							}
							
					   }
				  ,"json");
	}
</script>
</head>

<body class="main"  style="overflow-y: auto;height:500px;">
 <form action="<%=basePath %>subsuppliersAction/save" method="post" id="myform">
	<dl class="tabs">
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  <tr>
    <th width="120px;">分供方全称：</th>
    <td width="120px;"> ${sub.vcAllName} </td>
    <th width="120px;"  >分供方简称：</th>
    <td>
    	${sub.vcSmipleName}
    </td>
   
  </tr>
  <tr> 
   <th width="120px;">分供方地址: </th>
    <td>${sub.vcAddress} </td>
    <th width="120px;">规模：</th>
    <td>${sub.vcScale}</td>
   
  </tr>
  
  <tr>
    <th>所属省份：</th>
    <td>${pro.vcProvinceName} </td>
    <th>所属城市：</th>
    <td>
    	${city.cityname}
    </td>
  </tr>

 
  <tr>
    <th>所属地区：</th>
    <td>${sub.vcArea}</td>
    <th>详细地址：</th>
    <td>${sub.vcDetailedAddress}</td>
    
  </tr>
<tr>
	<th>公司注册地址：</th>
    <td>${sub.vcRegisterAddress} </td>
</tr>
<tr>
	<th>审批结果：</th>
    <td>
     	<select id="checResult" name="checResult" style="width: 130px;">
    		<option value="1">--同意--</option>
    		<option value="2">--不同意--</option>
    	</select>
     </td>
     <th>备注：</th>
    <td>
    	<input type="text" id="remarks"  name="remarks"/>
    </td>
</tr>
<tr>
	<th>审批附件：</th>
    <td><img src="${sub.vcOrderFile} " alt="" width="150px;" height="100px;" /></td>
   <th> </th>
    <td><a href="<%=basePath %>back/downFilebyPath?filepath=${sub.vcOrderFile}">附件下载</a></td>
</tr>

</table>
		<div class="operate"><input name="" id="sub" type="button" onclick="checkSub();" value="保存信息" class="f_btn" /></div>
		
        </dd>
    </dl>
 
    <input type="hidden" id="id" name="id" value="${sub.id}"/>
    </form>
</body>
</html>