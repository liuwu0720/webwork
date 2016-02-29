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
		var alllist =${roleStr};
		
$(document).ready(function() { 
	$.fn.zTree.init($("#treeDemo"), setting, alllist);
	zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.expandAll(true);
			//showTree();
	 
	$("#vcScale").val("${sub.vcScale}");
	
	$("#myform").validate({
				 rules: {
					 vcAllName: "required",
					 vcSmipleName:"required",
					 vcAddress:"required",
					 vcScale:"required",
					 stacity:"required"
				 },
				 messages:{
					 vcAllName:" 分供方全称不能为空.",
				 	 vcSmipleName:"分供方简称不能为空.",
					 vcAddress:"分供方地址不能为空.",
					 vcScale:"分供方规模不能为空.",
					 stacity:"所属城市不能为空."
				 },
				 submitHandler:function(form){
				  //$("body").mask("数据处理中，请稍等……");
			      $(form).ajaxSubmit({
			        type:"post",
			        url:"<%=basePath%>subsuppliersAction/save",
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
			window.location.href=window.location.href; 
			window.location.reload; 
		  }
	}
	 
	function showTree()
	{
		var paramType = "${paramType}"; 
		//如果该分供方资料已经完善
		if($.trim(paramType) == "show")
		{ 
			var appSta="${user.NApplyResource}";
			
			if(appSta==0)
			{  
				$("#msg").html("<font style='font-size: 20px;color: red;text-align: center;margin-top: 50px;' >正在审批</font>"); 
				$("#msg").show();
			}else if(appSta==2)
			{ 
				$("#againBut").show();
				$("#treemsg").text("已审批(勾选的为通过审批的)，结果如下：");
				//用户申请节点
				var applist = ${applist};
				 
				// 获取所有的节点 包括子节点   如果直接用getNodes  只能获取根节点数据
				 var nodes = zTree.transformToArray(zTree.getNodes()); 
				 for(var i=0;i<nodes.length;i++)
				 {
				 	 var ifalg = 0;
				 	for(var j=0;j<applist.length;j++)
				 	{
				 		if(nodes[i].id == applist[j].IRole)
				 		{
				 			ifalg = 1;
				 			if(applist[j].NApplyStatus == 0)
				 			{
				 				zTree.checkNode(nodes[i], true, true);
				 			}
				 		}
				 	}
				 	
				 	if(ifalg == 0 && nodes[i].id>=0)
				 	{
				 		// alert("nodes[i].id "+nodes[i].id);
				 		var hidenode = zTree.getNodeByParam("id", nodes[i].id, null); 
						zTree.hideNode(hidenode); 
				 	}
				 	
				 	nodes[i].chkDisabled = true;
				 }
				  
				$("#divResourse").slideDown();
				
			}else
			{
				$("#applyResource").show();
				
			} 
			
		}else
		{  
			$("#subSave").show();
		}
	}

	
	function showRoleInfo()
	{
		 $("#myform").ajaxSubmit({
		        type:"post",
		        url:"<%=basePath%>subsuppliersAction/updateinfo",
		        success: function(data, textStatus){
		        	var json = eval("("+data+")");
		        	if(json.isSuccess){
		        		 $("#divSubinfo").slideUp();
		        		 $("#divResourse").slideDown();
		        		  $("#saveResource").show(); 
		        		  $("#applyResource").hide();
		        	}
		        },
		        error:function(data){
		        	alert("error");
		        }
		});
		
	}
	
	function saveApplyResource()
	{
		
		var roleids = "";
		var nodes = zTree.getCheckedNodes();
		for(var i=0;i<nodes.length;i++)
		{
			var n =  nodes[i];
			//判断该节点是否是全选状态    半选状态是表示  当父节点有2个子节点 只有一个子节点选中  则父节点处于半选状态 
			 if(!n.getCheckStatus().half && n.id>=0)
			 { 
			 	roleids += n.id+",";
			 }
		} 
		if($.trim(roleids)!="")
		{ 
			$.post("<%=basePath%>subsuppliersAction/saveApplyResource", 
					{ roleids : roleids },     
					   function (data, textStatus)
					   {     
							 alert(data.message); 
							  if(data.isSuccess){
								window.location.href=window.location.href; 
								window.location.reload; 
							  }
					   }
				  ,"json");
			 
		}else
		{
			alert("请选择需要开通的角色权限！");
		}
	}
	
	function againApply()
	{
		var usid = "${user.id}";
		$.post("<%=basePath%>userAction/resetUserApplyResource", 
					{ usid : usid },     
					   function (data, textStatus)
					   {     
							 alert(data.message); 
							  if(data.isSuccess){
								window.location.href=window.location.href; 
								window.location.reload; 
							  }
					   }
				  ,"json");
	}
	//地址选择
	function getCity(){
		var url='<%=basePath%>cityAction/intoAddr';
		$.webox({
			height:400,
			width:330,
			bgvisibel:true,
			title:'选择地址',
			iframe:url
		});
	} 
</script>
</head>

<body class="main"  style="overflow-y: auto;height:700px;">
<div id="divSubinfo">
 <form action="<%=basePath %>subsuppliersAction/save" method="post" id="myform">
	<dl class="tabs" >
        <dd class="form" style="min-height:300px;">
       
<table width="100%" border="0" class="table_form">
  <tr>
    <th width="120px;">分供方全称：</th>
    <td width="120px;"><input class="f_txt" id="vcAllName" name="vcAllName" type="text" value="${sub.vcAllName} "  /></td>
    <th width="120px;"  >分供方简称：</th>
    <td>
    	<input class="f_txt" type="text" id="vcSmipleName"  name="vcSmipleName" value="${sub.vcSmipleName} "  />
    </td>
   
  </tr>
  <tr> 
   <th width="120px;">分供方地址: </th>
    <td><input class="f_txt" type="text" id="vcAddress" name="vcAddress" value="${sub.vcAddress}" /> </td>
    <th width="120px;">规模：</th>
    <td>
    	<select id="vcScale" name="vcScale">
    		<option value="">---请选择---</option>
    		<option value="0-50辆车">0-50辆车</option>
    		<option value="50-100辆车">50-100辆车</option>
    		<option value="100-150辆车">100-150辆车</option>
    	</select>
    	</td>
   
  </tr>
  
  <tr>
    <th>所属省份/城市</th>
    <td>
    		<input class="f_txt" type="text" id="addr" value="${pro.vcProvinceName}<c:if test="${pro.vcProvinceName!=null}">/</c:if>${city.cityname}" onfocus="getCity()"/>
			<input type="hidden" id="pro" name="IProvince" value="${sub.IProvince}"/>
			<input type="hidden" id="city" name="ICity" value="${sub.ICity}"/>
    </td>
    <th>详细地址：</th>
    <td><input class="f_txt" type="text"  id="vcDetailedAddress" name="vcDetailedAddress" value="${sub.vcDetailedAddress} " /></td>
  </tr>
<tr>
	<th>公司注册地址：</th>
    <td><input class="f_txt" type="text" id="vcRegisterAddress" name="vcRegisterAddress" value="${sub.vcRegisterAddress} "/></td>
    <th>联系电话：</th>
    <td><input class="f_txt" type="text" id="vcTel" name="vcTel" value="${sub.vcTel} "/></td>
</tr>
<tr>
	<td colspan="3" align="center">
		<span id="msg" style="width: 300px;display: none;" ><font style="font-size: 20px;color: red;text-align: center;" >分供方资料已完善</font> </span>
	</td>
</tr>
</table>
		 
			<input name="subSave" id="subSave" type="submit" style="margin-left:250px"  value="保存信息" class="f_btn" /> <!-- style="display: none;margin-left: 50px;" -->
			<!--<input name="applyResource" id="applyResource" onclick="showRoleInfo();" style="display: none;margin-left: 100px;"   type="button"  value="申请权限" class="f_btn" />
			 <input name="againBut" id="againBut" type="button" onclick="againApply();" style="margin-left: 100px;display: none;" value="重新申请" class="f_btn" />-->
			 
	
    
        </dd>
    </dl>
    <input type="hidden" id="paramType" name="paramType" value="${paramType}"/>
    <input type="hidden" id="vcSubno" name="vcSubno" value="${sub.vcSubno}"/>
    <input type="hidden" id="id" name="id" value="${sub.id}"/>
    <input type="hidden" id="NEnable" name="NEnable" value="${sub.NEnable}"/>
    <input type="hidden" id="NEnableKill" name="NEnableKill" value="${sub.NEnableKill}"/>
    <input type="hidden" id="NEnableOrder" name="NEnableOrder" value="${sub.NEnableOrder}"/>
    <input type="hidden" id="NEnableApply" name="NEnableApply" value="${sub.NEnableApply}"/>
    
    
    
    </form>
   
    
    
     </div>
     <div id="divResourse" style="display: none;margin-left: 50px;margin-top: 30px;">
    	 
    	<span id="treemsg">权限菜单：</span> 
    	<div class="content_wrap">
						<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
						</div>
				
		</div>
		<span id="msg"></span>
		
    	<input name="saveResource" id="saveResource" style="margin-top: 50px;margin-left: 150px;display: none;"  onclick="saveApplyResource();"  type="button"  value="提交申请" class="f_btn" />
    	
    </div>
</body>
</html>