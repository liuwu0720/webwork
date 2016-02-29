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
var nflag = "1";

$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
	//初始化刚提交的申请
	$('#dg').datagrid({     
		    url:'<%=basePath %>loanAction/mangerLoanList',
		    cache : false,
		    type : 'post',
		    title:'贷款信息', 
		    width:w,
		    height:h,
		    pageSize:size,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    //rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
				        {field:'vcLoanno',title:'申请编号',width:60,align:'left'},     
				        {field:'vcSubno',title:'分供编号',width:60,align:'left'},     
				        {field:'vcSubAllName',title:'分供方',width:100,align:'left'},     
				        {field:'vcApplyUserName',title:'申请人',width:60,align:'left'},     
				        {field:'dtApply',title:'申请时间',width:100,align:'left',
				        	formatter:function(value,rec,index){
				        		if(null!=value && ""!=value){
					        		var unixTimestamp = new Date(value);  
					        		return unixTimestamp.toLocaleString();
				        			
				        		}
				        	}},
				        {field:'dtDeadline',title:'期限',width:130,align:'left',
					        	formatter:function(value,rec,index){
					        		if(null!=value && ""!=value){
						        		var unixTimestamp = new Date(value);  
				                        return unixTimestamp.toLocaleDateString();
					        			
					        		}
					        	}},
			        	{field:'nloanTotal',title:'贷款总额',width:50,align:'right'},
				        {field:'nflag',title:'状态',width:80,align:'left',  
		                    formatter:function(value,rec,index){ 
	                    		var e = '';
	                    		if(value==1){
	                    			e='申请中';
	                    		}else if(value==2){
	                    			e='洽谈中';
	                    		}else if(value==3){
	                    			e='已贷款';
	                    		}else if(value==4){
	                    			e='贷款失败';
	                    		}
	                    		return e;
		                    }  
		                  },
		                  {field:'opt',title:'操作',width:50,align:'center',  
			                    formatter:function(value,rec,index){ 
		                    		var e = '<a href="javascript:;" class="rob" onclick="lookDetail(\''+rec.id+'\');" >查看</a> ';
		                    		if(rec.nflag==1){
		                    			var s = '<a href="javascript:;" class="rob" onclick="openDetail(\''+rec.id+'\');" >洽谈</a> ';
		                    			return s;
		                    		}else{
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
                    		 colspan:9
                    	 });
                 	 }else{
                 		 //有数据时，td增加一个title属性，方便展示
                 	 	addTitle();
                 	 }
                  }
		});
	
	});
	


	//查看
	function lookDetail(id){
		var url = "<%=basePath%>loanAskAction/openMeetList?id="+id;
		window.location.href = url;
	}
	//洽谈
	function openDetail(id){
		var url = "<%=basePath%>loanAction/openTalkLoan?id="+id;
		window.location.href = url;
	}
	
	//状态检查
	function ztSearcher(nf){
		nflag = nf;
		seacher();
	}
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var vcSubAllName = $("#vcSubAllName").val();
		if(null!=vcSubAllName && ""!=vcSubAllName){
			jsonStr.vcSubAllName = vcSubAllName;
		}
		var dtApply = $("#dtApply").val();
		if(null!=dtApply && ""!=dtApply){
			jsonStr.dtApply = dtApply;
		}
		jsonStr.nflag = nflag;
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
			<span onclick="ztSearcher('1');" class="sclass">申请中</span> <span onclick="ztSearcher('2');" class="sclass">洽谈中</span> <span onclick="ztSearcher('3');" class="sclass">贷款成功</span> <span onclick="ztSearcher('4');" class="sclass">贷款失败</span> 
			分供方：<input name="vcSubAllName" class="f_txt" id="vcSubAllName" style="width: 120px;" /> 申请时间：<input  id="dtApply" name="dtApply" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
			<table  id="dg"   ></table>
        </dd>
        
        
    </dl>
</body>
</html>