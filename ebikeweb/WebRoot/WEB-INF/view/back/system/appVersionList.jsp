<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>app类型维护</title>
    <%@include file="../../include/common.jsp" %>
	<%@ include file="../../include/datagrid.jsp"  %>
	<script type="text/javascript">
		$(function(){
			var h = getHeight('dg');
			var size = getPageSize(h);
			var w = getWidth(400);
			$('#dg').datagrid({
				url:'<%=basePath%>appVersionAction/findAllAppVersions',
				cache:false,
				type:'post',
				title:'app版本信息',
				pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
				pageSize : size,
				width : w,
				height:h,
		        fitColumns:true,
				rownumbers:true,   //如果为true，则显示一个行号列。  
				striped:true,  //striped  是否显示斑马线效果。
				singleSelect:false,  //如果为true，则只允许选择一行。
				loadMsg:'正在加载,请稍等...',
				nowrap:false,
				columns:[[
					{field:'id' ,title:'id',checkbox:true,width:'50',align:'center'},
					{field:'iUser',title:'添加人id',width:'100',align:'center'},
					{field:'vcUserName',title:'添加人姓名',width:'100',align:'center'},
					{field:'dtAdd' ,title:'添加时间',width:'120',align:'center',
						formatter:function(value,rec,index)
		        		{
		        			var unixTimestamp = new Date(value);   
                        	return unixTimestamp.toLocaleString();
		        		}
		        	},
					{field:'nVersionCode' ,title:'版本号',width:'100',align:'center'},
					{field:'vcVersionName' ,title:'版本号名称',width:'100',align:'center'},
					{field:'nUpdateType' ,title:'更新类型',width:'100',align:'center',
						formatter:function(value,row,index){
							if(value==0){
								return "选择更新";
							}else{
								return "强制更新";
							}
						}
					},
					{field:'vcAppUrl' ,title:'app下载地址',width:'350',align:'center'},
					{field:'vcNote' ,title:'版本说明',width:'100',align:'center'},
					{field:'' ,title:'操作',width:'100',align:'center',
						formatter:function(value,row,index){
							return '<input type="button" onclick="getupdate('+row.id+')" value="修改"/>';
						}
					}
				]],
				onLoadSuccess: function (data) {
			      //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
                 	fitWidth(data);
                },
		        onLoadError:function(){
                      alert("加载数据失败！");
                }
			});
		});
	//增加
	function save(){
		var url='<%=basePath%>appVersionAction/intoSavepage';
		$.webox({
			height:400,
			width:600,
			bgvisibel:true,
			title:'增加app版本信息',
			iframe:url
		});
	}
	//获取修改的页面
	function getupdate(id){
		//发送请求获取修改的页面
		//var selected=$('#dg').datagrid('getSelected');
		var url='<%=basePath%>appVersionAction/intoSavepage?id='+id;
	    $.webox({
			height:420,
			width:600,
			bgvisibel:true,
			title:'修改app版本信息',
			iframe:url
		});
	}
	//删除
	function del(){
		var selections=$('#dg').datagrid('getSelections');
		var idStr='';
		for(var i=0;i<selections.length;i++){
			idStr+=selections[i].id;
			if(i!=selections.length-1){
				idStr+=',';
			}
		}
		alert('idStr:'+idStr);
		if(selections.length>0){
			$.post("<%=basePath%>appVersionAction/del",
					{idStr:idStr},
					function(data){
						if(data.isSuccess){
							alert(data.message);
							$('#dg').datagrid('reload');
						}else{
							alert(data.message);
						}
					},"json");
		}else{
			alert('请选择一条记录');
		}
	}
	</script>
 </head>
  
  <body class="main easyui-layout">
    <dl class="tabs">
    	<dt><a href="#"><span>app版本维护</span></a></dt>
    	<dd class="form">
    		<div class="batch">
        		<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        		<!--<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" />--> 
        		<input name="" onclick="del();" type="button" class="f_btn2" value="删除" /> 
        	</div>
        	<div class="search"></div>
        	<table id="dg" style="width:1000px;height:500px"></table>
    	</dd>
    </dl>
  </body>
</html>
