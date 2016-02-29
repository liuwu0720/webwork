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
<title>贷款信息</title>

<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">

$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
	var loanId = getQueryString("id");
	//初始化刚提交的申请
	$('#dg').datagrid({     
		    url:'<%=basePath %>loanAskAction/getMeetList?loanId='+loanId,
		    cache : false,
		    type : 'post',
		    title:'提供贷款', 
		    width:w,
		    height:h,
		    pageSize:size,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    //rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'NAccrual',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
				        {field:'vcLoanno',title:'贷款编号',width:60,align:'left'},     
				        {field:'vcFinanceno',title:'公司编号',width:60,align:'left'},     
				        {field:'nrepulse',title:'给予贷款',width:60,align:'left',
				        	formatter:function(value,rec,index){ 
	                    		var e = '';
	                    		if(value==0){
	                    			e='给予';
	                    		}else if(value==1){
	                    			e='拒绝';
	                    		}else{
	                    			e='未做决定';
	                    		}
	                    		return e;
		                    } 
				        },     
				        {field:'naccrual',title:'利息',width:100,align:'right'},     
				        {field:'NAccrualLast',title:'复利息',width:100,align:'right'},     
				        {field:'dtAccrual',title:'出息时间',width:100,align:'left',
				        	formatter:function(value,rec,index){
				        		if(null!=value && ""!=value){
					        		var unixTimestamp = new Date(value);  
					        		return unixTimestamp.toLocaleString();
				        			
				        		}
				        	}},
		                  {field:'opt',title:'操作',width:50,align:'center',  
			                    formatter:function(value,rec,index){ 
			                    	if(rec.nrepulse=='1'){
			                    		//TODO hjx 如果拒绝，可以查看拒绝理由
			                    		var e = '<a href="javascript:;" class="rob" onclick="openDetail(\''+rec.id+'\');" >查看</a> ';
			                    		return e;
			                    	}else if(rec.nrepulse=='0'){
			                    		//TODO hjx 如果同意给予贷款，则进行给予复利息，并给分供方发消息
			                    		var e = '<a href="javascript:;" class="rob" onclick="openHandle(\''+rec.id+'\');" >处理</a> ';
			                    		return e;
			                    	}else{
			                    		var e = '';
			                    		return e;
			                    	}
			                    }  
			                  }
				    ]],
		     		onLoadError:function(){
                      alert("加载数据失败！");
                  },
                  onLoadSuccess:function(data){
                 	 if(data.total==0){
                 		 //当没数据时，显示提示
                 		 $('#dg').datagrid('appendRow',{
                 			vcLoanno: '没有相关记录'
                    	 });
                    	 
                    	 $('#dg').datagrid('mergeCells',{
                    		 index: 0,
                    		 field:'vcLoanno',
                    		 colspan:7
                    	 });
                 	 }else{
                 		 //有数据时，td增加一个title属性，方便展示
                 	 	addTitle();
                 	 }
                  }
		});
	
	});
	


	//查看
	function openDetail(pid){
		var url = "<%=basePath%>loanAction/openTalkLoan?id="+pid;
		window.location.href = url;
	}
	

	//查询方法
	function seacher(){
		var jsonStr = {};
		var vcFinanceName = $("#vcFinanceName").val();
		if(null!=vcFinanceName && ""!=vcFinanceName){
			jsonStr.vcFinanceName = vcFinanceName;
		}
		$("#dg").datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	
	
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	    var h = getHeight('dg');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size });
	}
	// 返回贷款信息页面
	function goTo(){
		var url = "<%=basePath%>loanAction/openAllLoanPage";
		window.location.href = url;
	}
	
</script>
<style type="text/css">
.sclass{
background: #ECA58E;padding: 5px 5px 5px 5px;cursor:pointer;
}
</style>
</head>

<body class="easyui-layout" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dd >
    		<!-- 申请中 -->
			<div class="search">
			金融公司名称：<input name="vcFinanceName" class="f_txt" id="vcFinanceName" style="width: 120px;" /> 
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />&nbsp;&nbsp;<input onclick="goTo();" type="button" class="f_btn2" value="返回" />
			</div>
			<table  id="dg"   ></table>
        </dd>
        
        
    </dl>
</body>
</html>