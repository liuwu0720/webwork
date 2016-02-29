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
<title>app开关</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/js/libs/ztree/css/demo.css"
	type="text/css" />
<%@include file="../../include/common.jsp"%>
<%@include file="../../include/ztreeCommon.jsp"%>

<script type="text/javascript">
	var setting = {
		check :{enable: true,chkStyle: "radio",radioType: "level"},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onCheck : onCheck
		}
	};

	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		vid="";
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			vid += nodes[i].id+",";
		}
		if (v.length > 0 ) {
			v = v.substring(0, v.length-1);
			vid = vid.substring(0, vid.length-1);
		}
		$("#archiveTypeIds").val(vid);
		var cityObj = $("#typeStr");
		cityObj.attr("value", v);
	}

	function showMenu() {
		var cityObj = $("#typeStr");
		var cityOffset = $("#typeStr").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "typeStr" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	

	$(document).ready(function() {
		var zNodes = ${typeJson};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		var pid =  getQueryString("pid");
		if(null != pid){
			$("#IParent").val(pid);
		}
		var resType =  getQueryString("resType");
		if(null!=resType){
			//菜单资源，隐藏url
			if(resType=="0"){
				$("#urlTr").hide();
				$("#flagTh").hide();
				$("#flagTd").hide();
			}
			
			$("#NType").val(resType);
		}
		var leef = getQueryString("leef");
		if(null!=leef){
			$("#").val(leef);
		}
		
		$("#resourceForm").validate({
			 rules: {
				 vcSwitchName: "required"
			 },
			 messages:{
				 vcSwitchName:"不能为空"
			 },
			 submitHandler:function(form){
				 if($("#on")[0].checked == true){
					
					 $("#flag")[0].value=0;
				 }else if($("#off")[0].checked == true){
					
					 $("#flag")[0].value=1;
				 }
				 $(form).ajaxSubmit
				 ({
					        type:"post",
					        url:"<%=basePath%>basicManage/saveAppSwitch",
					        success: showResponse,
					        error:function(data){
					        	
					        	alert("error");
					        }
				});
			},
			showErrors : function(map, list) {
				topTip(map, list);
			}

		});

	});

	function showResponse(responseText) {
		var json = eval("(" + responseText + ")");
		alert(json.message);
		if (json.isSuccess) {
			window.parent.location.href = window.parent.location.href
					+ "?idvalue=" + $("#IParent").val();
			window.parent.weboxColse();
		}
	}
</script>
</head>

<body class="main">
	<dl class="tabs">
		<dd class="form">
			<div style="border:1px red;float:left;"></div>
			<form id="resourceForm" method="post" action="">
				<table width="100%" border="0" class="table_form"
					style="float:right">
					<tr>
						<th width="130">所属资源：</th>
						
						<td><input class="f_txt" type="text" id="typeStr"
							style="width:84.6%;" name="typeStr" 
							onclick="showMenu();" placeholder="选择该资源所属档案" value="${typeStr }"/>
						<input  name="id" value="${tAppSwitch.id }" type="hidden" />	
						<input  id="archiveTypeIds"  type="hidden" name="iResource"/>	
							</td>
						<th id="flagTh">是否开启:</th>
						<td width="200" id="flagTd"><input id="flag" name="nOnOff"
							value="${tAppSwitch.nOnOff }" type="hidden" /> <input id="on"
							name="nOnOff2" type="radio" />开启
							 <input id="off" type="radio" name="nOnOff2" />关闭 
							<script type="text/javascript">
								var flag = $("#flag").val();
								if (null != flag && "" != flag) {
									if (flag == 1) {
										$("#off").attr("checked", "checked");
									} else if (flag == 0) {
										$("#on").attr("checked", "checked");
									}

								}else{
									$("#on").attr("checked", "checked");
								}
							</script>
						</td>
					</tr>
					<tr>
						<th>开关名称：</th>
						<td width="200"><input class="f_txt" type="text"
							id="vcSwitchName" name="vcSwitchName"
							value="${tAppSwitch.vcSwitchName }" placeholder="输入开关名称" />
						</td>
						<th>备注：</th>
						<td><input class="f_txt" type="text" id="vcNote"
							name="vcNote" value="${tAppSwitch.vcNote }" placeholder="输入开关名称" />
						</td>

					</tr>
				</table>
				<div class="operate">
					<input type="submit" value="保存" class="f_btn" />
				</div>
			</form>
		</dd>
	</dl>
	<div id="menuContent" class="menuContent"
		style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree"
			style="margin-top:0; width:180px; height: 300px;"></ul>
	</div>

</body>
</html>