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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机请假申请</title>


<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>

<script type="text/javascript">

$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath%>holidaysApprovalAction/getHolidayList',
		    cache : false,
		    type : 'post',
		    title:'司机请假申请', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。	   
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		   
		    loadMsg:'正在加载,请稍等...',
		    columns : [ [{
				field : 'id',
				title : 'id',
				width : 100,
				checkbox : true
			},
		      {
				field : 'vcUserName',
				title : '申请人姓名',
				width : 100,
				align : 'center',
				input:true
			}, {
				field : 'dtApply',
				title : '申请时间',
				width : 100,
				align : 'center',
				formatter:function(value,index){
					var newtime = new Date(value);
					return newtime.toLocaleString();
				}
			}, {
				field : 'iType',
				title : '请假类型',
				width : 100,
				align : 'center',
				formatter:function(value,index){
						if(value == 1){
							return "事假";
						}
						if(value == 2){
							return "病假";
						}
						if(value == 3){
							return "婚假";
						}
						if(value == 4){
							return "产假";
						}
						if(value == 5){
							return "年假";
						}
						if(value == 6){
							return "陪产假";
						}
						if(value == 7){
							return "丧假";
						}
					}
			}, {
				field : 'dtStart',
				title : '开始时间',
				width : 100,
				align : 'center',
				formatter:function(value,index){
						var newtime = new Date(value);
						return newtime.toLocaleString();
				}
			}, {
				field : 'dtEnd',
				title : '结束时间',
				width : 100,
				align : 'center',
				formatter:function(value,index){
					var newtime = new Date(value);
					return newtime.toLocaleString();
				}
			}, {
				field : 'vcApplyNote',
				title : '请假原因',
				width : 100,
				align : 'center'
			}, {
				field : 'vcApproveNote',
				title : '审批备注',
				width : 100,
				align : 'center'
			}, {
				field : 'vcApprveName',
				title : '审批人',
				width : 100,
				align : 'center'
			}, {
				field : 'nApproveResult',
				title : '审核结果',
				width : 100,
				align : 'center',
				formatter:function(value,index){
					if(value == 0){
						return "审核通过";
					}
					if(value == 2){
						return "审核不通过";
					}
				}
			}
			] ],
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
             fitWidth(data);
            },
		     onLoadError:function(){
                      alert("加载数据失败！");
            }
		});   
	
	});

	//查询方法
	function seacher() {
		var jsonStr = {};
		var vcUserName = $("#vcUserName").val();
	
		if (null != vcUserName && "" != vcUserName) {
			jsonStr.vcUserName = vcUserName;
		}
		
		$('#dg').datagrid('load', jsonStr);
	}

	function weboxColse() {

		$('.webox').css({
			display : 'none'
		});
		$('.background').css({
			display : 'none'
		});
	}

	function resizeGrid(minWidth) {
		var t = getWidth(minWidth);
		var h = getHeight('dg');
		var size = getPageSize(h);
		$('#dg').datagrid({
			width : t,
			height : h,
			pageSize : size
		});
	}
	
	function sure(obj){
		
		var selected = $("#dg").datagrid('getSelected');
		if(selected.length==0){
			alert("请至少选择一条");
			return false;
		}
		
		if(confirm("确认要审核通过所选记录吗？")){
			var vcApproveNote = $("#note").val();
			$.post("<%=basePath%>holidaysApprovalAction/sure", {
				"id" : selected.id,"vcResult":obj,"vcApproveNote":vcApproveNote
			}, function(data, textStatus) {
				if (data.isSuccess == true) {

					$.messager.show({ // show error message
						title : '提示',
						msg : data.message
					});
					$('#dg').datagrid('reload');
				} else {
					alert(data.message);
				}
			}, "json");
		}
	}
	

</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>绕路申请</span> </a>
		</dt>
		<dd class="form">
			<div class="batch">
				  审批意见：<input id="note" type="text" class="f_txt"/> 
				<input  onclick="sure(0);" type="button" class="f_btn2" size="5"
					value="同意" />
				<input  onclick="sure(2);" type="button" class="f_btn2" size="5"
					value="不同意" />		
			</div>
			<div class="search">

				<input type="text" id="vcUserName" class="f_txt f_short"
					placeholder="申请人" /> <input name=""
					onclick="seacher();" type="button" class="f_btn3" />

			</div>
			<table id="dg" style="width:1000px;height:500px">

			</table>
			
		</dd>
	</dl>

</body>
</html>