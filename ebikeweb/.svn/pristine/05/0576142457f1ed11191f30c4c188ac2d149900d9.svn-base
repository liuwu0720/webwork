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
<title>在途罚款详情</title>


<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-easyui-1.3.6/jqueryeasyui-detail.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zKaFos8O4VQfDffnnk6FqnMc"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery.JNMagnifier.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath%>transitFineApproveActon/getApproveListBySubNo',
		    cache : false,
		    type : 'post',
		    title:'在途罚款详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。	   
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns : [ [ {
				field : 'id',
				title : 'id',
				checkbox : true,
				width : 100
			}, {
				field : 'vcNserName',
				title : '申请人姓名',
				width : 100,
				align : 'center'
			}, {
				field : 'dtApplay',
				title : '申请时间',
				width : 100,
				align : 'center',
	        	formatter:function(value,rec,index)
	        	{
	        		var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
	        	}
			}, {
				field : 'vcApplyNote',
				title : '申请备注',
				width : 100,
				align : 'center'
			}, {
				field : 'vcFineSite',
				title : '罚款地点名字',
				width : 100,
				align : 'center'
			}, {
				field : 'vcShipno',
				title : '调度指令号',
				width : 100,
				align : 'center'
			}, {
				field : 'vcCarNo',
				title : '车牌号',
				width : 100,
				align : 'center'
			}, {
				field : 'nfine',
				title : '罚款金额',
				width : 100,
				align : 'center'
			}, {
				field : 'vcNote',
				title : '审批意见',
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
					}else{
						return "审核未通过";
					}
				}
			}, {		
				field : '',
				title : '查看地图',
				width : 100,
				align : 'center',
				formatter:function(value,row,index){
					var ids = row.id;
					return '<a href="javascript:serachMap('+ids+')">查看地图</a>';
				}
			}] ],
			view: detailview,
			detailFormatter:function(index,row){
				return '<div class="ddv" style="padding:5px 0"><table class="ddv"></table></div>';
			},
			onExpandRow: function(index,row){
				var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
				ddv.datagrid({
					url:'<%=basePath %>transitFineApproveActon/getPicDetail?damaId='+row.id,
					fitColumns:true,
					rownumbers:true,
					loadMsg:'正在加载...',
					height:500,
					columns:[[
						{field:'vcPicPath',title:'图片',width:150,align : 'center',formatter:function(value,row,index){	
							if(value == "" || value == null){
								return "无图片";
							}else{
								return  '<div id="'+index+'div"><img  src="'+value+'" alt="图片" width="700" height="700" style="display:block;left:20px;top:20px;" ></div>';
							}
						}
						}
					]],
					onResize:function(){
						$('#dg').datagrid('fixDetailRowHeight',index);
					},
					onLoadSuccess:function(){
						setTimeout(function(){
							$('#dg').datagrid('fixDetailRowHeight',index);
						},0);
					}
				});
				$('#dg').datagrid('fixDetailRowHeight',index);
			},
		    
		     onLoadError:function(){
	                  alert("加载数据失败！");
	        }
                  
		    
		});   
	
	});
function hideout(obj){
	$("#"+obj+"div").hide();
}

function showIn(obj){
	$("#"+obj+"div").show();
}
	//查询方法
	function seacher() {
		var jsonStr = {};
		var vcCarNo = $("#vcCarNo").val();
		var vcShipno = $("#vcShipno").val();
	
		if (null != vcCarNo && "" != vcCarNo) {
			jsonStr.vcCarNo = vcCarNo;
		}
		if (null != vcShipno && "" != vcShipno) {
			jsonStr.vcShipno = vcShipno;
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
			var vcNote = $("#note").val();
			$.post("<%=basePath%>transitFineApproveActon/sure", {
				"id" : selected.id,"vcResult":obj,"vcNote":vcNote
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
	
//查看地图
function serachMap(id){
	var tmp=window.open("about:blank","","width=790,height=590");  
	tmp.moveTo(300,300)  
	//tmp.resizeTo(screen.width-20,screen.height-20)  
	tmp.focus()  
	tmp.location="transitFineApproveActon/getCurrentMap?id="+id; 
	
}

function showBig(index){
	//var originalDiv = index+div;
	//alert(originalDiv)
	 $("#0div").JNMagnifier({
         renderTo:"#02div"
     });
}
	
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);">
	<dl class="tabs">
		<dt>
			<a href="#"><span>在途罚款</span> </a>
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

				<input type="text" id="vcShipno" class="f_txt f_short"
					placeholder="调度指令号" /> <input type="text" id="vcCarNo"
					class="f_txt f_short" placeholder="车牌号" /> <input name=""
					onclick="seacher();" type="button" class="f_btn3" />

			</div>
			<table id="dg" style="width:1000px;height:500px">

			</table>
			
		</dd>
	</dl>
</body>
</html>