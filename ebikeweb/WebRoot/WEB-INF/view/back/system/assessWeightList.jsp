<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>评价权重</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@include file="../../include/common.jsp" %>
	<%@ include file="../../include/datagrid.jsp"  %>
	<script type="text/javascript">
		$(document).ready(function(){
			var h = getHeight('dg');
			var size = getPageSize(h);
			var w = getWidth(400);
			$('#dg').datagrid({
				url:'<%=basePath%>assessWeightAction/findAll',
				cache:false,
				type:'post',
				title:'评价权重列表',
				pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
				pageSize : size,
				width : w,
				height:h,
		        fitColumns:true,
				rownumbers:true,   //如果为true，则显示一个行号列。  
				striped:true,  //striped  是否显示斑马线效果。
				singleSelect:true,  //如果为true，则只允许选择一行。
				loadMsg:'正在加载,请稍等...',
				nowrap:false,
				columns:[[
					{field:'id',title:'',checkbox:true,width:'100',align:'center'},
					{field:'vcTableName',title:'表名',width:'200',align:'center'},
					{field:'vcRow',title:'纬度字段名称',width:'200',align:'center'},
					{field:'nWeight',title:'权重',width:'200',align:'center',
						formatter:function(value,row,index){
							return value+"%";
						}
					},
					{field:'iUser',title:'添加人userId',width:'200',align:'center'},
					{field:'dtAdd',title:'添加时间',width:'200',align:'center',
						formatter:function(value,row,index){
							var date=new Date(value);
							return date.toLocaleString(); 
						}
					},
					{field:'vcUsername',title:'添加人姓名',width:'200',align:'center'},
				]]
			});
		});
		//增加
		function save(){
			var url='<%=basePath%>assessWeightAction/intoSave';
			$.webox({
				width:600,
				height:400,
				bgvisibel:true,
				title:'增加评价权重',
				iframe:url
			});
		}
		//修改
		function getupdate(){
			var selected=$('#dg').datagrid('getSelected');
			if(selected){
				var index=$('#dg').datagrid('getRowIndex',selected);
				var url='<%=basePath%>assessWeightAction/intoSave?id='+selected.id;
				$.webox({
					width:600,
					height:400,
					bgvisibel:true,
					title:'修改评价权重',
					iframe:url
				});
			}else{
				alert('请选择一条数据');
			}
		}
		//删除
		function del(){
			var selected=$('#dg').datagrid('getSelected');
			if(selected){
				$.post('<%=basePath%>assessWeightAction/del',
						{id:selected.id},
						function(data){
							alert(data.message);
							if(data.isSuccess){
								$('#dg').datagrid('reload');
							}
						},
						'json');
			}else{
				alert('请先选择一条数据');
			}
		}
	</script>
  </head>
  <body class="main easyui-layout">
    <dl class="tabs">
    	<dt><a href="#"><span>评价权重</span></a></dt>
    	<dd class="form">
    		<div class="batch">
        		<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        		<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        		<input name="" onclick="del();" type="button" class="f_btn2" value="删除" /> 
        	</div>
        	<div class="search"></div>
        	<table id="dg" style="width:1000px;height:500px"></table>
    	</dd>
    </dl>
  </body>
</html>