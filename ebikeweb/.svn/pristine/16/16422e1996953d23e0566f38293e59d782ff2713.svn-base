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
		
	var paramType = "${paramType}";
	var eorderSta = "${sub.NEnableOrder}";
	if(paramType == "subInfoError")
	{
		$("#sub").hide();
		$("#msg").text("分供方信息未完善");
		
	}else if(eorderSta == 2)
	{
		$("#sub").hide();
		$("#msg").text("正在审批");
	}else if(eorderSta == 0)
	{
		$("#sub").hide();
		$("#msg").text("已审批通过");
	}else if(eorderSta == 3)
	{
		//审批未通过   确认后修改 已确认   重置t_subsuppliers  的下单状态 为不行
		var vcreamrks = "${appcord.vcRemarks}";
		$("#sub").hide();
		$("#againBut").show();
		
		$("#msg").text("审批没通过 原因 ："+vcreamrks);
	}
	
		

});

//重新申请  上传附件
function againApply()
{
	var subid = "${sub.id}";
	
	$.post("<%=basePath%>subsuppliersAction/againApplyOrder", 
					{ subID:subid},     
					   function (data, textStatus)
					   {     
							if(data.result="ok")
							{
								alert("保存成功.");
								window.location.href=window.location.href; 
								window.location.reload; 
							}else
							{
								alert("保存失败"+data.result);
							}
							
					   }
				  ,"json");
}

function checkfile()
{
	var filename = $("#upload").val();
	if($.trim(filename) != "")
	{
		$("#myform").submit();
		
	}else
	{
		alert("请选择文件.");
		return false;
	}
	
}
	 
</script>
</head>

<body class="main"  style="overflow-y: auto;height:500px;">
 <form action="<%=basePath %>subsuppliersAction/applyOrderUpload" method="post" enctype="multipart/form-data" id="myform">
	<dl class="tabs">
        <dd class="form">
       
<table width="100%" border="0" class="table_form">

  <tr>
    <td width="160px;" align="right">上传文件：</td>
    <td width="120px;"> <input type="file" id="upload" name="upload" /></td>
   
  </tr>
  <tr>
    <td colspan="2" align="center" width="200px;">文件大小限制为 3M 以内</td>
     
  </tr>
  <tr>
    <td colspan="2" align="center" width="200px;"> <span id="msg"></span></td>
     
  </tr>
  
  

 
</table>
		<div class="operate"><input name="" id="sub" type="button" onclick="checkfile();" value="确定上传" class="f_btn" />
			<input name="" id="againBut" type="button" onclick="againApply();" style="display: none;" value="重新申请" class="f_btn" />
			</div>
		
        </dd>
    </dl>
   
    <input type="hidden" id="id" name="id" value="${sub.id}"/>
    </form>
</body>
</html>