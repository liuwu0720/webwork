<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建订单</title>

<%@ include file="../../include/common.jsp" %>

	<link rel="stylesheet" href="<%=basePath%>static/css/zTreeStyle.css" type="text/css"/> 
	<script type="text/javascript" src="<%=basePath%>static/js/libs/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/js/libs/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
	
<script type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
				
			},
			data: {
				simpleData: {
					enable:true,
					idKey:"id",
					pIdKey:"pid",
					rootPId:-2
				},
				key: {
					
					name:"name",
					title:"name"
					
				}
			}
		};
		//获取用户所属的档案类型的 角色信息
		var alllist =${applist};
		
		
$(document).ready(function() {
	$.fn.zTree.init($("#treeDemo"), setting, alllist);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
		

});
	
	function saveResource()
	{
	
		var appids = "";
		var nodes = zTree.getCheckedNodes();
		for(var i=0;i<nodes.length;i++)
		{
			var n =  nodes[i];
			//判断该节点是否是全选状态    半选状态是表示  当父节点有2个子节点 只有一个子节点选中  则父节点处于半选状态 
			 if(!n.getCheckStatus().half && n.id>=0)
			 { 
			 	appids += n.id+",";
			 }
		} 
		if($.trim(appids)!="")
		{
			$.post("<%=basePath%>subsuppliersAction/execApplyResource", 
					{ appids : appids },     
					   function (data, textStatus)
					   {     
							 alert(data.message); 
							  if(data.isSuccess){
								window.parent.$('#dg').datagrid('reload');  
		   						window.parent.weboxColse();  
							  }
					   }
				  ,"json");
			 
		}else
		{
			alert("请选择需要开通的角色权限！");
		}
	}
	 
</script>
</head>

<body class="main"  style="overflow-y: auto;height:500px;">
 <div id="divResourse"  >
    	 
    	权限菜单：
    	<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
				
		</div>
		<span id="msg"></span>
		
    	<input name="saveResource" id="saveResource" style="margin-top: 50px;margin-left: 150px;"  onclick="saveResource();"  type="button"  value="保存确定" class="f_btn" />
    	
    </div>
    
</body>
</html>