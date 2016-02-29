<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>司机基本工资</title>
    <%@include file="../../include/common.jsp" %>
	<%@ include file="../../include/datagrid.jsp"  %>
	<script type="text/javascript">
		$(document).ready(function (){
			var h = getHeight('dg');
			var size = getPageSize(h);
			var w = getWidth(400);
			
			$('#dg').datagrid({
				url:'<%=basePath%>driverAction/getDriverSalaryList',
				cache:false,
				type:'post',
				title:'司机工资信息',
				pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
				pageSize :2,
				pageList :[10,15,20],
				    width : w,
				    height:h,
				    fitColumns:true,
				    rownumbers:true,   //如果为true，则显示一个行号列。  
				    striped:true,  //striped  是否显示斑马线效果。
				 
				    singleSelect:true,  //如果为true，则只允许选择一行。
				    loadMsg:'正在加载,请稍等...',
				    nowrap:false,
				columns:[[
					{field:'id',title:'id',checkbox:true,width:'100',align:'left'},
					{field:'vcDriverName',title:'司机姓名',width:'120',align:'left'},
					{field:'nMonth',title:'月份',width:'120',align:'left'},
					{field:'nSalary',title:'工资',width:'120',align:'left'},
					{field:'dtStart',title:'生效日期',width:'120',align:'left',
						formatter:function(value,rec,index)
		        		{
		        			var unixTimestamp = new Date(value);   
                        	return unixTimestamp.toLocaleDateString();
		        		}
					},
					{field:'dtEnd',title:'失效日期',width:'120',align:'left',
						formatter:function(value,rec,index)
		        		{
		        			var unixTimestamp = new Date(value);   
                        	return unixTimestamp.toLocaleDateString();
		        		}
					},
					{field:'vcSubno',title:'分供方编号',width:'120',align:'left'},
					{field:'vcAddUser',title:'添加人',width:'120',align:'left'},
					{field:'dtAdd',title:'添加时间',width:'120',align:'left',
						formatter:function(value,rec,index)
		        		{
		        			var unixTimestamp = new Date(value);   
                        	return unixTimestamp.toLocaleDateString();
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
		//保存
		function save(){
			//发送请求获取增加的页面
			var url='<%=basePath%>driverAction/intoSave';
			$.webox({
				height:360,
				width:600,
				bgvisibel:true,
				title:'增加司机基本工资',
				iframe:url
		});
		};
		//修改
		function getupdate(){
			//发送请求获取修改的页面
			var selected=$('#dg').datagrid('getSelected');
			if(selected){
				var index = $('#dg').datagrid('getRowIndex', selected);
				alert("id="+selected.id);
				var url='<%=basePath%>driverAction/intoSave?id='+selected.id;
				$.webox({
					height:360,
					width:600,
					bgvisibel:true,
					title:'修改司机基本工资',
					iframe:url
				});
			}else{
				alert("请选择一条数据");
			};
		};
		//删除
		function del(){
			var selected=$('#dg').datagrid('getSelected');
			if(selected){
				var index=$('#dg').datagrid('getRowIndex',selected);
				$.post("<%=basePath%>driverAction/delDriverSalary",
					{id:selected.id},
					function (data){
						if(data.isSuccess){
							alert(data.message);
							$('#dg').datagrid('reload');
						}else{
							alert(data.message);
						};
						
					},
					"json"
				);
			}else{
				alert("请选择一条数据");
			};
		};
		//根据司机姓名和月份匹配查询
		function seacher(){
			var jsonstr={};
			var driverName=$('#vcDriverName').val();
			var nMonth=$('#nMonth').val();
			if(null!=driverName && ""!=driverName){
				jsonstr.driverName=driverName;
			};
			var regex=/^\d+$/;
			//alert("test:"+regex.test(nMonth));
			if(regex.test(nMonth)){
				var month=parseInt(nMonth);
				if(month>=1&&month<=12){
					jsonstr.nMonth=nMonth;
				}
			}
			$('#dg').datagrid('load',jsonstr);
		};
	</script>

  </head>
  
  <body class="main "  onresize="resizeGrid(400);">
    <dl class="tabs">
    	<dt><a href="#"><span>司机基本工资管理</span></a></dt>
    	<dd class="form">
    		<div class="batch">
        		<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        		<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        		<input name="" onclick="del();" type="button" class="f_btn2" value="删除" /> 
        	</div>
        	<div class="search">
        		<input type="text" id="vcDriverName" class="f_txt f_short" placeholder="司机姓名"/> 
			   	<input type="text" id="nMonth" class="f_txt f_short" placeholder="月份"/>  
			 	<input name="" onclick="seacher();" type="button" class="f_btn3" />
        	</div>
        	<table id="dg" style="width:1000px;height:500px"></table>
    	</dd>
    </dl>
  </body>
</html>
