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
<title>无标题文档</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/libs/ztree/css/demo.css" type="text/css"/>
<%@include file="../../include/common.jsp" %>
<%@include file="../../include/ztreeCommon.jsp" %>

<script type="text/javascript">
	var setting = {
		check : {
			enable : true,
			chkboxType : {
				"Y" : "",
				"N" : ""
			}
		},
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
				 vcResourceName: "required"
			 },
			 messages:{
				 vcResourceName:"资源名称必须填写"
			 },
			 submitHandler:function(form){
				 if($("#pc")[0].checked == true && $("#phone")[0].checked == true){
					
					$("#flag")[0].value=2;
				 }else if($("#pc")[0].checked == true){
					
					 $("#flag")[0].value=1;
				 }else if($("#phone")[0].checked == true){
					
					 $("#flag")[0].value=0;
				 }
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>resource/saveResource?time="+ (new Date()).getTime(),
			        //beforeSubmit: showRequest,
			        success: showResponse
			      });
			    },
			    showErrors: function(map, list) {
			    	topTip(map, list);
				}
			
		});
		
	});
	
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			window.parent.location.href = window.parent.location.href+"?idvalue="+$("#IParent").val();
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
			<table width="100%" border="0" class="table_form" style="float:right">
				<tr>
					<th width="130">所属档案：</th>
					<td><input class="f_txt" type="text" id="typeStr" style="width:84.6%;"
						name="typeStr" value="${typeStr }" onclick="showMenu();" placeholder="选择该资源所属档案" /> <input type="hidden"
						id="archiveTypeIds" name="archiveTypeIds" value="${typeId }" /></td>
					<th id="flagTh">权限分配:</th>
					<td width="200" id="flagTd">
					<input  id="flag" name="nFlag"  value="${resource.nFlag }" type="hidden"/>
					<input id="phone" type="checkbox" />手机端
					<input id="pc" type="checkbox" />PC端
					<script type="text/javascript">
					var flag = $("#flag").val();
					if(null!=flag&&""!=flag){
						if(flag==1){
							$("#pc").attr("checked", "checked");
						}else if(flag==0){
							$("#phone").attr("checked", "checked");
						}else if(flag==2){
							$("#phone").attr("checked", "checked");
							$("#pc").attr("checked", "checked");
						}
						
					}
					</script>
					</td>
				</tr>
				<tr>
					<th>资源名称：</th>
					<td width="200">
						<input class="f_txt" type="text" id="vcResourceName" name="vcResourceName"
						value="${resource.vcResourceName }" placeholder="输入资源名称"/>
						<input  type="hidden" id="id" name="id" value="${resource.id }"/>
						<input  type="hidden" id="IParent" name="IParent" value="${resource.IParent }"/>
						<input  type="hidden" id="NLeaf" name="NLeaf" value="${resource.NLeaf }"/>
						<input  type="hidden" id="NType" name="NType" value="${resource.NType }"/>
						</td>
					<th>是否有效：</th>
					<td>
						<input name="NEnable" type="radio" value="0"
						checked="checked" />有效 <input name="NEnable" type="radio"
						value="1" />无效
						<input type="hidden" id="NEnabletxt" name="NEnabletxt"  value="${resource.NEnable }" />
						<script type="text/javascript">
							var nable = $("#NEnabletxt").val();
							if(null!=nable&&""!=nable){
								$("input[type='radio'][name='NEnable'][value='"+nable+"']").attr("checked", "checked");
							}
						</script>
						<input type="hidden" id="vcIcon" name="vcIcon" value="${resource.vcIcon }"/>
					</td>
				</tr>
				
				<tr id="urlTr">
					<th>链接地址：</th>
					<td colspan="3"><input class="f_txt" type="text" id="vcUrl" name="vcUrl" style="width:84.6%;"
						value="${resource.vcUrl }" placeholder="输入链接地址" /></td>
				</tr>
				<tr>
					<th>资源描述：</th>
					<td colspan="3">
						<textarea style="width:84.6%;height:150px" name="vcDesc" placeholder="输入资源描述">${resource.vcDesc }</textarea>
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