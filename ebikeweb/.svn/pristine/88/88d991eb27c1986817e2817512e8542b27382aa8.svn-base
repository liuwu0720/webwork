<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>评价权重保存</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="../../include/common.jsp" %>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$.validator.addMethod('isNum',function(value,element,param){
    			var regexp=/^\d{1,}$/;
    			if(regexp.test(value)&&value<=100){
    				return true;
    			}else{
    				return false;
    			}
    		})
    		$('#myform').validate({
    			rules:{
    				vcTableName:"required",
    				vcRow:"required",
    				nWeight:{
    					required:true,
    					isNum:true
    				}
    			},
    			messages:{
    				vcTableName:"请填写表名",
    				vcRow:"请填写纬度字段名称",
    				nWeight:{
    					required:"请填写权重（数字）",
    					isNum:"请填写一个100以内的正整数",
    				}
    			},
    			submitHandler:function(form){
					$(form).ajaxSubmit({
						type:"post",
						url:"<%=basePath%>assessWeightAction/save",
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
    		//下拉框change事件
    		$('#tables').change(function(){
    			var tableValue=$('#tables option:selected').val();
    			//alert("tableValue:"+tableValue);
    			$.post(
    				'<%=basePath%>assessWeightAction/getAllFields',
    				{tableName:tableValue},
    				function(data){
    					//var select=document.getElementById('fields');
    					//select.options.length=0;
    					$('#fields').empty();
    					for(var i=0;i<data.length;i++){
    						//alert(data[i].COLUMN_NAME+":"+data[i].COMMENTS);
    						var option=new Option(data[i].COMMENTS,data[i].COLUMN_NAME);
    						//select.options.add(option);
    						$('#fields').append(option);
    					}
    				},
    				"json"
    			);
    		});
    	});
    	function showResponse(responseText) 
		{
			var json = eval("("+responseText+")");
			alert(json.message);
			//$("body").unmask();
			 if(json.isSuccess){
				window.parent.$('#dg').datagrid('reload');  
				window.parent.weboxColse();
			  }
		}
    </script>
  </head>
  
  <body class="main">
    	<form action="<%=basePath%>assessWeightAction/save" method="post" id="myform">
    	<dl class="tabs">
    		<dt>
    			<a href="#"><span>评价权重编辑</span> </a>
    		</dt>
    		<dd class="form">
    			<table width="100%" border="0" class="table_form">
    				<tr>
    					<td align="right">表名<font color="red">*</font>：</td>
    					<td>
    					<select name="vcTableName" id="tables" >
    						<c:if test="${empty weight.vcTableName}" var="rs">
    							<option>--请选择--</option>
    						</c:if>
    						<<c:if test="${!rs}">
    								<option value="${weight.vcTableName}">${comment.COMMENTS }</option>
    						</c:if>
    						<c:forEach items="${tables}" var="table">
    							<c:if test="${weight.vcTableName!=table.TABLE_NAME}" var="r">
    								<option value="${table.TABLE_NAME}" >${table.COMMENTS}</option>
    							</c:if>
    						</c:forEach>
    					</select>
    					</td>
    				</tr>
    				<tr>
    					<td align="right">纬度字段名称<font color="red">*</font>：</td>
    					<td>
    						<select name="vcRow" id="fields" >
    							<c:if test="${empty weight.vcRow}" var="rs">
    								<option>--请选择--</option>
    							</c:if>
    							<c:if test="${!rs}">
    								<c:forEach items="${fields}" var="field" >
    									<c:if test="${field.COLUMN_NAME==weight.vcRow}" var="r">
    										<option value="${field.COLUMN_NAME }" selected="selected">${field.COMMENTS }</option>
    									</c:if>
    									<c:if test="${!r}">
    										<option value="${field.COLUMN_NAME }">${field.COMMENTS }</option>
    									</c:if>
    								</c:forEach>
    							</c:if>
    						</select>
    					</td>
    				</tr>
    				<tr>
    					<td align="right">权重<font color="red">*</font>：</td>
    					<td><input type="text" name="nWeight" class="f_txt" value="${weight.nWeight}"
    					style="width: 160px;padding-bottom: 8px;"/><span style="font-size:20px;font-weight:bold;margin-left:10px">%</span></td>
    				</tr>
    			</table>
    			<div class="operate"><input name="" type="submit"   value="保存信息" class="f_btn" />&nbsp;&nbsp; 
				</div>
				<input type="hidden" name="id" value="${weight.id}"/>
				<input type="hidden" name="NEnable" value="${weight.NEnable}"/>
				<input type="hidden" name="iUser" value="${weight.iUser}"/>
				<input type="hidden" name="dtAdd" value="<fmt:formatDate value="${weight.dtAdd}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				<input type="hidden" name="vcUsername" value="${weight.vcUsername}"/>
    		</dd>
    	</dl>
    	</form>
  </body>
</html>