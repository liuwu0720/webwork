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
					enable: true
				},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck : onCheck
			}
		};
		
		
		//选择触发事件
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
			$("#resourceIds").val(vid);
			
		}
		
		
		
		

	var zTree;
	$(document).ready(function() {
		
		var html = $("#typeSel").html();
		var zNodes = "";
		if(null!=${resourceList} && ""!=${resourceList}){
			zNodes = ${resourceList};
		}
		if(zNodes !="" ){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var idvalue = getQueryString("idvalue");
			if(null != idvalue){
				var zjson = zTree.getNodeByParam("id",idvalue);
				if(null !=zjson){
					zTree.expandNode(zjson,true,false,true);
				}
			}
		}
		
		
		$("#typeSel").change(function(){ 
			var typeid = $("#typeSel").val(); 
			if(null!=typeid&&""!=typeid){
				var roleId = getQueryString("roleId");
				var url = '<%=basePath%>role/getByRoleidAndTypeId?roleId='+roleId+'&typeId='+typeid;
				$.post(url,function(data){
						referTree(data);
				});
			}
		});
	});
	//档案类型下拉框触发事件
	function schange(){
		var typeid = $("#typeSel").val();
		if(null!=typeid&&""!=typeid){
			var roleId = getQueryString("roleId");
			var url = '<%=basePath%>role/getByRoleidAndTypeId?roleId='+roleId+'&typeId='+typeid;
			$.post(url,function(data){
				referTree(data);
			});
		}
	}
	
	//保存角色和资源的关联
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  window.parent.weboxColse();
		  }
		}
	//刷新树结构
	function referTree(responseText) {
		var json = eval("("+responseText+")");
		  if(json.isSuccess){
			  if(null==json.message && ""==json.message){
				  alert("该档案没有关联资源");
			  }
			  $.fn.zTree.init($("#treeDemo"), setting, json.message);
		  }else{
			  alert(json.message);
		  }
		}
	
	
	//保存用户角色关联
	function save(){
		var ids = $("#resourceIds").val();
		if(null==ids&&ids==""){
			onCheck(e, treeId, treeNode);
		}
		var ids = $("#resourceIds").val();
		var roleId = getQueryString("roleId");
		var typeid = $("#typeSel").val();
		var url ="<%=basePath%>role/saveRoleResource?roleId="+roleId+"&resourceIds="+ids+"&typeId="+typeid;
		$.post(url,function(data){
			showResponse(data);
		});
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
<body class="main">
	<dl class="tabs">
		<dt>
			<a href="#">关联资源</a>
			<select id="typeSel" >
				<c:forEach  items="${typeList }" var="ty">
					<option value="${ty.id }">${ty.vcArchive }</option>
				</c:forEach>
			</select>
		</dt>
		<dd class="form">
			<div style="border:1px red;float:left;"></div>
			<div class="zTreeDemoBackground left">
				
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<input type="hidden" id="resourceIds" />
			<div class="operate">
				<input type="submit" value="保存" onclick="save();" class="f_btn" />
			</div>
		</dd>
	</dl>
	
</body>
</html>