<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>司机支出审核</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">
var shipno="";
$(document).ready(function(){

	var h = getHeight('dg');
	var size = getPageSize(h);
	var w = getWidth(400);	
		$('#dg').datagrid({     
		    url:'<%=basePath %>arorderDriverAction/getArOrderDriverlist',
		    cache : false,
		    type : 'post',
		    title:'司机支出审核', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : size,
		    width : w,
		    height:h,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		   // singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'司机支出审核表ID',checkbox : true,align:'left'},    
		        {field:'vcShipNo',title:'指令号',width:100,align:'left'},
		        {field:'vcStartCity',title:'起运地',width:100,align:'left'},
		        {field:'vcEndCity',title:'目的地',width:100,align:'left'},
		        {field:'vcCarStyle',title:'车型',width:50,align:'right' },
		        {field:'nQty',title:'发运数量',width:50,align:'right'},
		        {field:'nTotalPrice',title:'总价',width:50,align:'right'},
		        {field:'vcDriver',title:'司机',width:50,align:'right'},
		        {field:'nBill',title:'是否审核',width:50,align:'right',formatter:function(value,row,index){
		        	if(value == 1){  		        		
		        		return "<p style='color: red'>结算已审核</p>";
		        	}else {//客户支付
		        		return "结算未审核";
		        	}
		        }},	
		        {field:'dtAudit',title:'审核时间',width:100,align:'left',formatter:function(value,index){
		        	var unixTimestamp = new Date(value);   
                    return unixTimestamp.toLocaleDateString();
		        }},
		        {field:'vcAuditUser',title:'审核人',width:100,align:'left'},
		        {field:'vcAuditNote',title:'审核意见',width:100,align:'left'}
		    ]],
		    onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
            },
            onLoadError:function(){
                alert("加载数据失败！");
            }
		    
		});   
	
	});
	
	
	function showDates(obj)
	{
		WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true});
	}

	//审核
	function audit(obj)
	{
		
		var selected = $('#dg').datagrid('getSelections');
		var Ids=[];
		for(var i=0;i<selected.length;i++){
			var Id = selected[i].id;
			
			Ids.push(Id);
		}
		
		if(selected.length>0){
			var vcAuditNote = $("#vcAuditNote").val();
			$.post("<%=basePath %>arorderDriverAction/audit", {
				"array[]":Ids,"vcAuditNote":vcAuditNote,"vcResult":obj
			},function(data, textStatus){
				if(data.isSuccess == true)
				{
					$.messager.show({ 
						title : '提示',
						msg :data.message
					});
					$('#dg').datagrid('reload'); 
				}else
				{
					alert(data.message);
				}
			},"json")
		}
		
	}	
	
	
	
	//查询方法
	function seacher(){
	 	
		var jsonStr = {};
		var vcDriver = $("#vcDriver").val();
		
		if(null!=vcDriver && ""!= vcDriver){
			jsonStr.vcDriver=vcDriver;
		}
	
	
		$('#dg').datagrid('load',jsonStr);
	}
	
	function resizeGrid(minWidth){
		var t = getWidth(minWidth);
		var h = getHeight('dg');
		var size = getPageSize(h);
		$('#dg').datagrid({
			width : t,
			height : h,
			pageSize : size
		});
	}
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>司机工资支出审核</span> </a></dt>
        <dd class="form">
        <div class="batch">
        		 审批意见：<input id="vcAuditNote" type="text" class="f_txt"/>
        	<input name="" id="" onclick="audit(1)" type="button" class="f_btn2" value="审核通过" /> 
        	<input name="" id="" onclick="audit(0)" type="button" class="f_btn2" value="取消审核" /> 
        </div>
        <div class="search">
			 <input type="text" id="vcDriver" class="f_txt f_short" placeholder="司机姓名"/> 
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
				
			</div>
		<table id="dg" style="width:1000px;height:500px">
	
			</table>
			
		</dd>
    </dl>
</body>
</html>