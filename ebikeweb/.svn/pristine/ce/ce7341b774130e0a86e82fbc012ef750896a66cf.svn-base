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
			data: {
				
				simpleData: {
					enable: true
				}
			},
			callback: {
				onDblClick: onClick,
				onRightClick: OnRightClick,
				beforeClick: function(treeId, treeNode) {
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					if (treeNode.isParent) {
						zTree.expandNode(treeNode);
						return false;
					}
				}
			}
		};
		
		function OnRightClick(event, treeId, treeNode) {
			if (treeNode ) {
				//有选择节点的
				node=treeNode;
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY,treeNode);
			}
		}
		//显示右键菜单
		function showRMenu(type, x, y,treeNode) {
			
			if (type !="root") {
				$("#rMenu ul").show();
				var type = treeNode.ntype;
				if(type==0){
					$("#m_add").show();
					$("#m_del").show();
					$("#g_add").show();
					
					
					$("#g_del").hide();					
					$("#c_add").hide();
					$("#c_del").hide();
				}else if(type==1){
					$("#c_add").show();
					$("#g_del").show();
					
					$("#m_add").hide();
					$("#g_add").hide();
					$("#m_del").hide();
					$("#c_del").hide();
				}else if(type==2){
					$("#c_del").show();
					
					$("#m_add").hide();
					$("#g_add").hide();
					$("#c_add").hide();
					$("#m_del").hide();
					$("#g_del").hide();
				}
				rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

				$("body").bind("mousedown", onBodyMouseDown);
			}
			
		}
		//隐藏右键菜单
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		//鼠标右键点击事件
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		
		var addCount = 1;
		function addTreeNode(resType) {
			hideRMenu();
			// 获得父id 
			var pid = node.id;
			var leef = 1;//是否叶子节点，默认不是
			if(resType==2)leef=0;//当 添加的是操作，则是叶子节点
			var addurl = "<%=basePath%>resource/openAddPage?resType="+resType+"&pid="+pid+"&leef="+leef;
			$.webox({
				height:580,
				width:800,
				bgvisibel:true,
				title:'新增资源',
				iframe:addurl
		});
			
		}

		
		//删除
		function removeTreeNode(resType) {
			hideRMenu();
			if(node.leaf>0){
				var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
				if (confirm(msg)==true){
					//删除并刷新页面
					del();
				}
			}else{
				//删除并刷新页面
				del();
			}
			
		}
		//删除并刷新页面
		function del(){
			var url = "<%=basePath%>resource/delResource?resourceId="+node.id;
			$.post(url,function(data){
				showResponse(data);
			})
		}



		//弹出页面
		function onClick(event, treeId, treeNode, clickFlag) {
			var id = treeNode.id;
			$.webox({
				height:580,
				width:800,
				bgvisibel:true,
				title:'资源详情',
				iframe:'<%=basePath%>resource/getResourceByid?resourceId=' + id+'&resType='+treeNode.ntype
		});

	}

	var zTree, rMenu;
	$(document).ready(function() {
		var zNodes = ${jsonStr};
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var idvalue = getQueryString("idvalue");
		if(null != idvalue){
			var zjson = zTree.getNodeByParam("id",idvalue);
			if(null !=zjson){
				zTree.expandNode(zjson,true,false,true);
			}
		}
		rMenu = $("#rMenu");

	});
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		  alert(json.message);
		  if(json.isSuccess){
			 window.location.href = window.location.href+"?idvalue="+node.pId;
			//location.reload(); 
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
<body class="main">
	<dl class="tabs">
		<dt><a href="#"><span>资源管理</span></a></dt>
		<dd class="form">
			<div style="border:1px red;float:left;"></div>
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</dd>
	</dl>
	<div id="rMenu">
		<ul>
			<li id="m_add" onclick="addTreeNode(0);">增加菜单</li>
			<li id="g_add" onclick="addTreeNode(1);">增加功能</li>
			<li id="c_add" onclick="addTreeNode(2);">增加操作</li>
			<li id="m_del" onclick="removeTreeNode(0);">删除菜单</li>
			<li id="g_del" onclick="removeTreeNode(1);">删除功能</li>
			<li id="c_del" onclick="removeTreeNode(2);">删除操作</li>
		</ul>
	</div>
</body>
</html>