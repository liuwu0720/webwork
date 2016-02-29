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
<title>图片路径维护</title>

<%@include file="../../include/common.jsp"%>

<script type="text/javascript">+
$(document).ready(function() {
		
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/saveGpsRate");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>basicManage/saveGpsRate");
			
		}
		
		//保存
		$("#myform").validate({
			
			 rules: {
				 nRate : "required"
			    
			 },
			 messages:{
				 nRate : "不能为空"
				
			 },
			  submitHandler:function(form){
			  	 $(form).ajaxSubmit
				 ({
					        type:"post",
					        url:"<%=basePath%>basicManage/saveGpsRate",
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
		alert(json.message);
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
	<form action="<%=basePath%>basicManage/saveGpsRate"
		method="post" id="myform">
		<dl class="tabs">
			<dt>
				<a href="#"><span>GPS定位频率维护</span> </a>
			</dt>
			<dd class="form">
				<input type="hidden" name="id" value="${tGpsrate.id }" />
				<input type="hidden" name="vcSubno" value="${tGpsrate.vcSubno }" />
				<input type="hidden" name="userId" value="${tGpsrate.userId }" />
				<table width="100%" border="0" class="table_form">
					<tr>
						<td>司机</td>
						<td><input class="f_txt" name="vcUserNo" readonly="readonly"
							value="${tGpsrate.vcUserNo}"  id="vcUserNo" />
						</td>
						<th>是否定位：</th>
						<td><input name="nGps" type="radio" value="0"
							checked="checked" />是 <input name="nGps" type="radio"
							value="1" />否 <input type="hidden" id="nEnabletxt"
							name="NEnabletxt" value="${tGpsrate.nGps }" /> <script
								type="text/javascript">
								var nable = $("#nEnabletxt").val();
								if (null != nable && "" != nable) {
									$(
											"input[type='radio'][name='nGps'][value='"
													+ nable + "']").attr(
											"checked", "checked");
								}
							</script>
							</td>
					</tr>
					<tr>
						<td>定位频率</td>
						<td>
							<input name="nRate" value="${tGpsrate.nRate }" class="f_txt"/>
						</td>
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