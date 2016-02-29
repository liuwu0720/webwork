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
<%@include file="../../include/common.jsp" %>
<%@include file="../../include/ztreeCommon.jsp" %>
<script type="text/javascript">
	var node;
		var setting = {
				check: {
					enable: true,
					chkboxType : {
						"Y" : "",
						"N" : ""
					}
				},
			data: {
				
				simpleData: {
					enable: true
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
			$("#roleids").val(vid);
			
		}
		
	var zTree, rMenu;
	$(document).ready(function() {
		var zNodes = ${roleTree};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
	});
	//保存用户角色关联
	function save(){
		var ids = $("#roleids").val();
		if(null==ids&&ids==""){
			onCheck(e, treeId, treeNode);
		}
		var ids = $("#roleids").val();
		var userId = getQueryString("userId");
		var url ="<%=basePath%>userAction/saveUserRole?userId="+userId+"&roleids="+ids;
		$.post(url,function(data){
			showResponse(data);
		});
	}
	
	
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		  alert(json.message);
		  if(json.isSuccess){
			 window.parent.weboxColse();
		  }
		}
</script>
<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #555;
	text-align: left;
	padding: 2px;
}

div#rMenu ul li {
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #DFDFDF;
}
</style>

</head>
<body class="main" style="overflow-y: auto;height:530px;">
	<dl class="tabs">
		<dd class="form">
			<div style="border:1px red;float:left;"></div>
			<div class="zTreeDemoBackground left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<input type="hidden" id="roleids" />
			<div class="operate">
				<input type="submit" value="保存" onclick="save();" class="f_btn" />
			</div>
		</dd>
	</dl>
	
</body>
</html>