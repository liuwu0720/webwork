<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>空闲运力维护</title>

<%@ include file="../../include/common.jsp" %>
<script type="text/javascript">
		
		
$(document).ready(function() {
		  
	$("#myform").validate({
				 rules: {
					 vcStartAddress : "required",
					 vcEndAddress : "required",
					 NSpace :"required",
					 vcPrice : "required",
					 vcLink : "required",
					 dtBegin : "required",
					 dtEnd : "required"
				 },
				 messages:{
					 vcStartAddress : "请填写起运地.",
					 vcEndAddress : "请填写目的地.",
					 NSpace :"请填写仓位数量.",
					 vcPrice : "请填写仓位价格.",
					 vcLink : "请填写联系电话.",
					 dtBegin : "请填写开始时间.",
					 dtEnd : "请填写结束时间."
				 },
				  submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
				  
				   $(form).ajaxSubmit({
								        type:"post",
								        url:"<%=basePath%>spareCapacityAction/saveCapatity",
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
		//$("body").unmask();
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
		$.post("<%=basePath%>orderAction/getAllCityByProID", 
					{ proID:proid },     
					   function (data, textStatus)
					   {     
							for(var i=0;i<data.length;i++)
							{
								 var option = new Option(data[i].cityname,data[i].id);
								if(selop == data[i].id)
								{
									option.selected= true;
								}
								
								 citsel.add(option);
							}
							
					   }
				  ,"json");
	}
	//获取车牌号
	function getCarNo(selectObj){
		var index=selectObj.selectedIndex;
		var truckId=$("#ITruckType").options[index].val();
	}
</script>
</head>

<body class="main" >
 <form action="<%=basePath %>spareCapacityAction/saveCapatity" method="post" id="myform">
	<dl class="tabs">
    	<dt><a href="#"><span>发布空闲运力</span></a></dt>
        <dd class="form">
       
<table width="100%" border="0" class="table_form">
  
  <tr>
    <th>起运地：</th>
    <td>
    	<input type="text" class="f_txt"  id="vcStartAddress" name="vcStartAddress" value="${capacity.vcStartAddress}"/> 
    </td>
    <th>目的地：</th>
    <td>
    	 <input type="text" class="f_txt"  id="vcEndAddress" name="vcEndAddress" value="${capacity.vcEndAddress}"/>
    </td>
  </tr>
  <tr>
    <th>有效仓位：</th>
    <td>
    	 <input type="text" class="f_txt"  id="NSpace" name="NSpace" value="${capacity.NSpace}"/>
   	</td>
    <th>仓位价格：</th>
    <td>
    	<input type="text" class="f_txt"  id="vcPrice" name="vcPrice" value="${capacity.vcPrice}"/>
    </td>
  </tr>
  <tr>
    <th>联系电话：</th>
    <td>
    	 <input type="text" class="f_txt"  id="vcLink" name="vcLink" value="${capacity.vcLink}"/>
   	</td>
    <th>总重：</th>
    <td>
    	<input type="text" class="f_txt"  id="vcLink" name="vcLink" value="${capacity.NWeight}"/>
    </td>
  </tr>
  <tr>
    <th width="110">开始时间：</th>
    <td>
    	<input class="f_txt Wdate" name="dtBegin" type="text" id="dtBegin" 
    	value="<fmt:formatDate value="${capacity.dtBegin}" pattern="yyyy-MM-dd" />"  
    	style="width: 120px;padding-bottom: 8px;"  
    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
    </td>
    <th width="120">结束时间：</th>
    <td> 
    	<input class="f_txt Wdate" name="dtEnd" type="text" id="dtEnd" 
    	value="<fmt:formatDate value="${capacity.dtEnd}" pattern="yyyy-MM-dd" />"  
    	style="width: 120px;padding-bottom: 8px;" 
    	onfocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />
    </td>
  </tr>
   <tr>
   	<th>车牌号码：</th>
   	<td>
   		<select id="ITruckType" name="ITruckType" class="f_txt" onchange="getCarNo(this)">
   			<option value="0">---请选择---</option>
   			<c:forEach items="${trucks}" var="truck">
   				<option value="${truck.id }">${truck.carNo }</option>
   			</c:forEach>
   		</select>
   	</td>
   </tr>
   <tr>
    <th>车长：</th>
    <td>
    	 <input type="text" class="f_txt"  id="NLength" name="NLength" value="${capacity.NLength}"/>
   	</td>
    <th>车宽：</th>
    <td>
    	<input type="text" class="f_txt"  id="NWidth" name="NWidth" value="${capacity.NWidth}"/>
    </td>
  </tr>
  <tr>
    <th>车高：</th>
    <td>
    	 <input type="text" class="f_txt"  id="NHeight" name="NHeight" value="${capacity.NHeight}"/>
   	</td>
    <th>描述：</th>
    <td>
    	<input type="text" class="f_txt"  id="vcDesc" name="vcDesc" value="${capacity.vcDesc}"/>
    </td>
  </tr>
</table>
		<div class="operate"><input name="" type="submit" value="保存信息" class="f_btn" /></div>
        </dd>
    </dl>
    <input type="hidden" id="paramType" name="paramType" value="${paramType}"/>
    <input type="hidden" id="id" name="id" value="${capacity.id}"/>
    <input type="hidden" id="IUser" name="IUser" value="${capacity.IUser}"/> 
    <input type="hidden" id="vcUserName" name="vcUserName" value="${capacity.vcUserName}"/> 
    <input type="hidden" id="NEnable" name="NEnable" value="${capacity.NEnable}"/>  
    <input type="hidden" id="dtAdd" name="dtAdd" value='<fmt:formatDate value="${capacity.dtAdd}" pattern="yyyy-MM-dd HH:mm:ss" />'/> 
     <input type="hidden" id="a.vcUsername" name="a.vcUsername" value="haha"/> 
    
    </form>
</body>
</html>