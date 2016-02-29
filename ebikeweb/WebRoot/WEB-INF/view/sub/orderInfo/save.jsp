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
<title>新建订单</title>
<%@ include file="../../include/common.jsp"%>
<!--  <script type="text/javascript" src="<%=basePath%>static/js/libs/citySelector.js"></script>-->
<script type="text/javascript">
		
$(document).ready(function() {
		var paramsType = "${paramType}";
		if(paramsType=="add")
		{
			$("#myform").attr("action","<%=basePath%>orderAction/save");
		}else if(paramsType=="update")
		{
			$("#myform").attr("action","<%=basePath%>orderAction/save");
			//默认选中 起运城市  目的城市
			//prochange('stapro','stacity',"${stacity.id}");
		
			//prochange('endpro','endcity',"${endcity.id}");
			
			$("#NPayType").val("${order.NPayType}");
			$("#ICustomerId").val("${order.ICustomerId}");
			changePayType();
		}
		
	$("#myform").validate({
				 rules: {
					 vcLoadAddress: "required",
					 vcLoadContact: "required",
					 vcLoadTel : "required",
					 stacity : "required",
					 endcity : "required",
					 NTotalCar: {number:true,required:true},
					 vcReceiveAddress:"required",
					 vcReceiveContact:"required",
					 vcReceiveTel:"required",
					 dtShip :"required",
					 dtArrive : "required",
					 ICarStyle:"required"
				 },
				 messages:{
					 vcLoadAddress:"<span ><font style='color: red;'>装货地址必须填写</font></span>",
					 vcLoadContact : "装货联系人必须填写",
					 vcLoadTel :  "装货联系电话必须填写",
					 stacity : "起始地不能为空",
					 endcity : "目的地不能为空",
					 NTotalCar : "数量不能为空,为整数",
					 vcReceiveAddress : "收货人地址",
					 vcReceiveContact : "收货联系人",
					 vcReceiveTel : "收货人电话",
					 dtShip :"发运时间为空",
					 dtArrive :"到货时间为空",
					 ICarStyle:"车型为空"
				 },
				 submitHandler:function(form){
					 var flag = true;
				  if($("#NPayType").val()==0){ //现金支付
					  var patrn=/^[0-9]+\.{0,1}[0-9]{0,2}$/; //只能为小数或者整数
					  if($("#NTotalPrice").val()==""){
						  alert("总价不能为空");
						  flag = false;
					  }
					 if(!patrn.exec($("#NTotalPrice").val())){
						 alert("总价请输入有效数字");
						 flag = false;
					 }
					  if(flag==true){
						  $(form).ajaxSubmit({
						        type:"post",
						        url:"<%=basePath%>orderAction/save",
						        success: showResponse,
						        error:function(data){
						        	alert("error");
						        }
						      });
					  }
				  }
				  if($("#NPayType").val()==1){ //客户支付
					  $("#costtr").show();
					  if($("#ICustomerId").val()==0){
						  alert("请选择客户");
						  flag = false;
					  }
					
						var ICarStyle = $("#ICarStyle").val();//商品车id
						var stacity =$("#stacity").val();//起始地城市
						var endcity = $("#endcity").val();//目的地城市
						var ICustomerId = $("#ICustomerId").val();//客户
						if(ICarStyle!="" && stacity!="" && endcity!="" && ICustomerId!=0){
							$.post("<%=basePath%>orderAction/getCost", {
								ICarStyle : ICarStyle,
								stacity: stacity,
								endcity: endcity,
								ICustomerId : ICustomerId
							}, function(data) {
								$("#warn").html("")
								$("#N_COST")[0].value = data;
								if(data == -1){
									$("#warn").html("应收单价还没有维护")
									$("#N_COST")[0].value = "";
									//  flag = false;
								}
									
									  if(flag==true){
										  $(form).ajaxSubmit({
										        type:"post",
										        url:"<%=basePath%>orderAction/save",
										        success: showResponse,
										        error:function(data){
										        	alert("error");
										        }
										      });
									  }
								
							})
						}
				  }
				
				
			     
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
				 
				
			});
		

});
function showResponse(responseText){
	console.log(responseText);
	var json = eval("("+responseText+")");
	
	  if(json.isSuccess){
		
		window.parent.$('#dg').datagrid('reload');  
	    window.parent.weboxColse();  
	  
	  }
	  alert(json.message);
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

	function changePayType() {
		var paytype = $("#NPayType").val();
		//现金支付
		if (paytype == 0) {
			$("#NTotalPrice").show();
			$("#ICustomerId").hide();
			$("#showMsg").text("总价:");
			$("#costtr").hide();
		} else {
			$("#NTotalPrice").hide();
			$("#ICustomerId").show();
			$("#showMsg").text("客户:");
		}

	}
	//根据条件查询应收单价
	function getDriverCost() {
		$("#costtr").show();
		var ICarStyle = $("#ICarStyle").val();//商品车id
		var stacity =$("#stacity").val();//起始地城市
		var endcity = $("#endcity").val();//目的地城市
		var ICustomerId = $("#ICustomerId").val();//客户
		if(ICarStyle!="" && stacity!="" && endcity!="" && ICustomerId!=0){
			$.post("<%=basePath%>orderAction/getCost", {
				ICarStyle : ICarStyle,
				stacity: stacity,
				endcity: endcity,
				ICustomerId : ICustomerId
			}, function(data) {
				if(data == -1){
					$("#warn").html("应收单价还没有维护")
					$("#N_COST")[0].value = "";
				}else{
					$("#warn").html("")
					$("#N_COST")[0].value = data;
				}
			})
		}
		
	}
	//获取起运城市
	function getStartCity(){
		var url='<%=basePath%>cityAction/intoStartAddr';
		$.webox({
			height:400,
			width:330,
			bgvisibel:true,
			title:'选择地址',
			iframe:url
		});
	}
	//获取目的城市
	function getEndCity(){
		var url='<%=basePath%>cityAction/intoEndAddr';
		$.webox({
			height:400,
			width:330,
			bgvisibel:true,
			title:'选择地址',
			iframe:url
		});
	}
	//关闭弹窗
	//function weboxClose(){
		//$('.webox').remove();
		//$('.background').remove();
	//}
</script>
</head>

<body class="main" style="overflow-y: auto;height:500px;">
	<form action="<%=basePath%>orderAction/save" method="post" id="myform">
		<dl class="tabs">
			<dd class="form">

				<table width="100%" border="0" class="table_form">
					<tr>
						<th width="120px;">订单号：</th>
						<td width="120px;"><input class="f_txt" type="text"
							value="系统自动生成" readonly="readonly" /></td>
						<th width="120px;">车型：</th>
						<td><select style="width: 200px;" id="ICarStyle"
							name="ICarStyle">
								<option value="">---请选择---</option>
								<c:forEach items="${substylist}" var="carsty">
									<c:choose>
										<c:when test="${carsty.id==order.ICarStyle}">
											<option selected="selected" value="${carsty.id}">${carsty.vcCarStyle}</option>
										</c:when>
										<c:otherwise>
											<option value="${carsty.id}">${carsty.vcCarStyle}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select>
						</td>

					</tr>
					<tr>
						<th>起运省份/城市:</th>
						<td>
							<input class="f_txt" type="text" id="startAddr" value="${stapro.vcProvinceName}<c:if test="${stapro.vcProvinceName!=null}">/</c:if>${stacity.cityname}" onfocus="getStartCity()"/>
							<input type="hidden" id="stapro" name="stapro" value="${stapro.id}"/>
							<input type="hidden" id="stacity" name="stacity" value="${stacity.id}"/>
							<!--<input type="hidden"  name="IStartId" value="${stacity.id}"/>-->
						</td>
						<th width="120px;">装货地址：</th>
						<td>
							<!--<input class="f_txt" type="text" id="vcLoadAddress"
							name="vcLoadAddress" value="${order.vcLoadAddress}" />-->
							<input type="text" class="f_txt" id="vcLoadAddress" value="${order.vcLoadAddress}" name="vcLoadAddress"
							/>
						</td>
					</tr>
					<tr>
						<th>目的省份/城市：</th>
						<td>
							<input class="f_txt" type="text" id="endAddr" value="${endpro.vcProvinceName}<c:if test="${endpro.vcProvinceName!=null}">/</c:if>${endcity.cityname}" onfocus="getEndCity()"/>
							<input type="hidden" id="endpro" name="endpro" value="${endpro.id}"/>
							<input type="hidden" id="endcity" name="endcity" value="${endcity.id}"/>
							<!--<input type="hidden"  name="IEndId" value="${endcity.id}"/>-->
						</td>
						<th>收货地址：</th>
						<td><input class="f_txt" type="text" id="vcReceiveAddress"
							name="vcReceiveAddress" value="${order.vcReceiveAddress}" />
						</td>
					</tr>
					<tr>
						<th>联系人：</th>
						<td><input class="f_txt" type="text" id="vcLoadContact"
							name="vcLoadContact" value="${order.vcLoadContact}" /></td>
						<th>联系电话：</th>
						<td><input class="f_txt" type="text" id="vcLoadTel"
							name="vcLoadTel" value="${order.vcLoadTel}" /></td>
					</tr>
					<tr>
						<th width="120px;">发运时间：</th>
						<td><input class="f_txt Wdate" name="dtShip" type="text"
							id="dtShip"
							value="<fmt:formatDate value="${order.dtShip}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />

						</td>
						<th width="120px;">到货时间：</th>
						<td><input class="f_txt Wdate" name="dtArrive" type="text"
							id="dtArrive"
							value="<fmt:formatDate value="${order.dtArrive}" pattern="yyyy-MM-dd" />"
							style="width: 120px;padding-bottom: 8px;"
							onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" />

						</td>
					</tr>
					<!--<tr>
						<th>起运省份：</th>
						<td><select id="stapro" name="stapro"
							onchange="prochange('stapro','stacity');">
								<option value="0">---请选择---</option>
								<c:forEach items="${prolist}" var="pro">
									<c:choose>
										<c:when test="${pro.id == stapro.id}">
											<option value="${pro.id}" selected="selected">${pro.vcProvinceName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcProvinceName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select>
						</td>
						<th>起运城市：</th>
						<td><select id="stacity" name="stacity">
								<option value="">---请选择---</option>
						</select>
						</td>
					</tr>
					<tr>
						<th>目的省份：</th>
						<td><select id="endpro" name="endpro"
							onchange="prochange('endpro','endcity')">
								<option value="0">---请选择---</option>
								<c:forEach items="${prolist}" var="pro">
									<c:choose>
										<c:when test="${pro.id == endpro.id}">
											<option value="${pro.id}" selected="selected">${pro.vcProvinceName}</option>
										</c:when>
										<c:otherwise>
											<option value="${pro.id}">${pro.vcProvinceName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select>
						</td>
						<th>目的城市：</th>
						<td><select id="endcity" name="endcity">
								<option value="">---请选择---</option>
						</select>
						</td>
					</tr>-->

					<tr>
						<th width="120px;">数量:</th>
						<td><input class="f_txt" type="text" id="NTotalCar"
							name="NTotalCar" value="${order.NTotalCar}" />
						</td>
						<th>收货人：</th>
						<td><input class="f_txt" type="text" id="vcReceiveContact"
							name="vcReceiveContact" value="${order.vcReceiveContact}" /></td>

					</tr>
					<tr>
						<th>联系电话：</th>
						<td><input class="f_txt" type="text" id="vcReceiveTel"
							name="vcReceiveTel" value="${order.vcReceiveTel}" /></td>
						<th>客户单号：</th>
						<td><input class="f_txt" type="text" id="vcCustOrderNo"
							name="vcCustOrderNo" value="${order.vcCustOrderNo}" /></td>
					</tr>
					<tr>
						<th>支付方式：</th>
						<td><select id="NPayType" name="NPayType"
							onchange="changePayType(this);">
								<option value="0">现金支付</option>
								<option value="1">客户支付</option>
						</select>
						</td>
						<th id="showMsg">总价：</th>
						<td><input class="f_txt" type="text" id="NTotalPrice"
							name="NTotalPrice" value="${order.NTotalPrice}" /> <select
							id="ICustomerId" name="ICustomerId" style="display: none;"
							onchange="getDriverCost();">
								<option value="0">---请选择---</option>
								<c:forEach items="${custlist}" var="cust">
									<c:choose>
										<c:when test="${cust.id == order.ICustomerId}">
											<option value="${cust.id}" selected="selected">${cust.vcCustomerName}</option>
										</c:when>
										<c:otherwise>
											<option value="${cust.id}">${cust.vcCustomerName}</option>
										</c:otherwise>
									</c:choose>

								</c:forEach>
						</select>
						</td>

					</tr>
					<tr id="costtr" style="display: none">
						<th>应收单价:</th>
						<td><input id="N_COST" type="text" name="NCost" class="f_txt" size="4"
							readonly="readonly" />
						<span id="warn" style="color: red"></span>	
						</td>
					</tr>
				</table>
				<div class="operate">
					<input name="" type="submit" value="保存信息" class="f_btn" />
				</div>
			</dd>
		</dl>
		<input type="hidden" id="paramType" name="paramType"
			value="${paramType}" /> <input type="hidden" id="id" name="id"
			value="${order.id}" /> <input type="hidden" id="NEnable"
			name="NEnable" value="${order.NEnable}" /> <input type="hidden"
			id="vcSubno" name="vcSubno" value="${order.vcSubno}" /> <input
			type="hidden" id="vcOrderno" name="vcOrderno"
			value="${order.vcOrderno}" /> <input type="hidden" id="IUser"
			name="IUser" value="${order.IUser}" /> <input type="hidden"
			id="NShipedQty" name="NShipedQty" value="${order.NShipedQty}" />
		<input type="hidden" name="nLoad" value="${order.nLoad }"/>
		<input type="hidden" name="vcLong" value="${order.vcLong}"/>
		<input type="hidden" name="vcLat" value="${order.vcLat}"/>
		<input type="hidden" name="vcLongStart" value="${order.vcLongStart}"/>
		<input type="hidden" name="vcLatStart" value="${order.vcLatStart}"/>
		<input type="hidden" name="iStoreId" value="${order.iStoreId}"/>
		<input type="hidden" name="vcStoreName" value="${order.vcStoreName}"/>
		<input type="hidden" name="nEvalution" value="${order.nEvalution}"/>
		<input type="hidden" name="NProduct" value="${order.NProduct}"/>
		<input type="hidden" name="ICarStyleId" value="${order.ICarStyleId}"/>
		<input type="hidden" name="nRatio" value="${order.nRatio}"/>
		<input type="hidden" name="nPayCtyle" value="${order.nPayCtyle}"/>
		<input type="hidden" name="vcNote" value="${order.vcNote}"/>
		<input type="hidden" name="NCost" value="${order.NCost}"/>
	</form>
</body>
</html>