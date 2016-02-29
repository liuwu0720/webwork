<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>司机基本工资信息</title>
    <%@include file="../../include/common.jsp" %>
	<script type="text/javascript">
		$(document).ready(function (){
			//select的change事件
			$('#iDriverId').change(function (){
				var  listmonths = $("input:[name='nMonths']");
				listmonths.each(function(){
					$(this).removeAttr("checked");
					$(this).removeAttr("disabled");
				});
				var value=$('#iDriverId option:selected').val();
				if(value!=null){
					var driverId=parseInt(value);
					//alert("driverId:"+driverId);
					$.post("<%=basePath%>driverAction/getMonthsExist",
						{driverId:driverId},
						function (data){
							if(data.isSuccess){
								var list=data.data;
								for(var i=0;i<list.length;i++){
									var month=list[i];
									listmonths.each(function(){
										if(month == ($(this).val())){
											$(this).attr("checked",true);
											$(this).attr("disabled",true);
										}
									});
									/**
									for(var j in listmonths){
											if(month == (listmonths[j].val())){
												listmonths[j].checked = true;
											}
									}**/
										
								}
							}
						},
						"json"
					);
				};
			});
			$.validator.addMethod("isMonth", function(value, element){
				return this.optional(element)||(value>=1&&value<=12);
			},"请确保月份在1-12之间");
			$("#myform").validate({
				rules:{
					vcDriverName:"required",
					nMonth:{required:true,isMonth:true},
					nSalary:"required",
					dtStart:"required",
					dtEnd:"required"
				},
				messages:{
					vcDriverName:"请填写司机姓名",
					nMonth:{
						required:"请填写月份",
						isMonth:"请确保月份在1-12之间"
					},
					nSalary:"请填写工资",
					dtStart:"请填写开始时间",
					dtEnd:"请填写结束时间"
				},
				submitHandler:function(form){
					$(form).ajaxSubmit({
						type:"post",
						url:"<%=basePath%>driverAction/saveDriverSalary",
				        success: showResponse,
				        error:function(data){
				        	alert("error");
				        }
					});
				},
				showErrors: function(map, list){
					topTip(map, list);
				}
			});
			
		});
		function showResponse(responseText) 
		{
			var json = eval("("+responseText+")");
			alert(json.message);
			//$("body").unmask();
			 if(json.isSuccess){
				window.parent.$('#dg').datagrid('reload');  
				window.parent.weboxClose();
			  }
		}
		
	</script>
  </head>
  
  <body class="main" >
  	<form action="<%=basePath%>driverAction/saveDriverSalary" method="post" id="myform">
    <dl class="tabs">
    	<dd class="form" >
    		<table width="100%" border="0" class="table_form">
    			<tr>
    				<td colspan="4">
    					<font id="headStr" style="font-family: 微软雅黑;font-size: 18px;font-weight:bold;"> 司机信息:</font> 
    				</td>
  				</tr>
  				<tr>
  					<th>司机姓名:</th>
  					<td>
  						<select name="iDriverId" id="iDriverId" style="width:120px">
  							<c:choose>
  								<c:when test="${driverSalary.vcDriverName!=null}">
  									<option value="${driverSalary.iDriverId}">${driverSalary.vcDriverName}</option>
  								</c:when>
  								<c:otherwise><option>--请选择--</option></c:otherwise>
  							</c:choose>
  							<c:forEach items="${drivers}" var="driver">
  								<c:if test="${driver.id!=driverSalary.iDriverId}">
  									<option value="${driver.id}">${driver.vcDriverName}</option>
  								</c:if>
  							</c:forEach>
  						</select>
  					</td>
  					<th>工资:</th>
  					<td>
  						<input class="f_txt"  style="width: 120px;" type="text" name="nSalary" 
  						value="${driverSalary.nSalary }" id="nSalary"/>
  					</td>
  				</tr> 
  				<!--  <tr>
  					<th>月份:</th>
  					<td>
  						<input class="f_txt"  style="width: 120px;" type="text" name="nMonth" 
  						value="${driverSalary.nMonth}" id="nMonth"/>
  					</td>
  					<th>工资:</th>
  					<td>
  						<input class="f_txt"  style="width: 120px;" type="text" name="nSalary" 
  						value="${driverSalary.nSalary }" id="nSalary"/>
  					</td>
  				</tr>-->
  				<tr>
  					<th>开始日期:</th>
  					<td>
  						<input class="f_txt"  style="width: 120px;padding-bottom: 8px;" type="text" name="dtStart" id="dtStart"
  						value="<fmt:formatDate value="${driverSalary.dtStart}" pattern="yyyy-MM-dd" />"
  						onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
  					</td>
  					<th>结束日期:</th>
  					<td>
  						<input class="f_txt"  style="width: 120px;padding-bottom: 8px;" type="text" name="dtEnd" id="dtEnd"
  						value="<fmt:formatDate value="${driverSalary.dtEnd}" pattern="yyyy-MM-dd" />"
  						onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
  					</td>
  				</tr>
  				<c:choose>
  					<c:when test="${driverSalary.vcDriverName!=null}">
  						<tr>
  							<th rowspan="4">月份:</th>
  							<td colspan="3">
  								<input type="text" name="nMonth" id="nMonth" style="width:120px" value="${driverSalary.nMonth}"/>
  							</td>
  						</tr>
  					</c:when>
  					<c:otherwise>
  					<tr>
  					<th rowspan="4">月份:</th>
  					<td colspan="3">
  						<input type="checkbox" name="nMonths" value="1"> 1月
  						<input type="checkbox" name="nMonths" value="2"> 2月
  						<input type="checkbox" name="nMonths" value="3"> 3月
  						
  					</td>
  					</tr>
  					<tr>
  					<td colspan="3">
  						<input type="checkbox" name="nMonths" value="4"> 4月
  						<input type="checkbox" name="nMonths" value="5"> 5月
  						<input type="checkbox" name="nMonths" value="6"> 6月
  					</td>
  					</tr>
  					<tr>
  					<td colspan="3">
  						<input type="checkbox" name="nMonths" value="7"> 7月
  						<input type="checkbox" name="nMonths" value="8"> 8月
  						<input type="checkbox" name="nMonths" value="9"> 9月
  					</td>
  					</tr>
  					<tr>
  					<td colspan="3">
  						<input type="checkbox" name="nMonths" value="10">10月
  						<input type="checkbox" name="nMonths" value="11">11月
  						<input type="checkbox" name="nMonths" value="12">12月
  					</td>
  					</tr>
  					</c:otherwise>
  				</c:choose>
  			</table>
    		<div class="operate"><input name="" type="submit"   value="保存信息" class="f_btn" />&nbsp;&nbsp; 
			</div>
    	</dd>
    </dl>
    	<input type="hidden" name="id" id="id" value="${driverSalary.id }"/>
    	<input type="hidden" name="vcDriverName" id="vcDriverName" value="${driverSalary.vcDriverName}"/>
    	<input type="hidden" name="nEnable" id="nEnable" value="${driverSalary.nEnable }"/>
    	<input type="hidden" name="vcAddUser" id="vcAddUser" value="${driverSalary.vcAddUser}"/>
    	<input type="hidden" name="dtAdd" id="dtAdd" value="<fmt:formatDate value="${driverSalary.dtAdd}" pattern="yyyy-MM-dd" />"/>
    	<input type="hidden" name="vcSubno" id="vcSubno" value="${driverSalary.vcSubno}"/>
    </form>
  </body>
</html>
