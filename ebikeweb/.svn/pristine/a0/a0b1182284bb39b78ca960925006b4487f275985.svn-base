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
<title>个人贷款信息</title>
<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	//初始化我的贷款列表
		$('#dg').datagrid({     
		    url:'<%=basePath %>loanAction/getLoanList',
		    cache : false,
		    type : 'post',
		    title:'我的贷款', 
		    width:w,
		    height:400,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    //rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
				        {field:'vcApplyUserName',title:'申请人姓名',width:80,align:'left'},     
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
				        {field:'nflag',title:'贷款状态',width:100,align:'left',
			        		formatter:function(value,rec,index){
			        			if(value==0){
			        				return "保存未提交";
			        			}else if(value==1){
			        				return "申请已经提交";
			        			}else if(value==2){
			        				return "申请在洽谈";
			        			}else if(value==3){
			        				return "已贷款";
			        			}else if(value==4){
			        				return "贷不到款";
			        			}
			        		}	
				        },
				        {field:'opt',title:'操作',width:50,align:'center',  
		                    formatter:function(value,rec,index){ 
		                    	if(rec.nflag !='0'){
		                    		var e = '<a href="javascript:;" class="rob" onclick="openDetail(\''+rec.id+'\');" >查看</a> ';
		                    		return e;
		                    	}else if(rec.nflag =='0'){
		                    		var e = '<a href="javascript:;" class="rob" onclick="edit(\''+rec.id+'\');" >编辑</a> ';
		                    		var s = '<a href="javascript:;" class="rob" onclick="del(\''+rec.id+'\');" >删除</a> ';
		                    		return e+s;
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
                 			vcApplyUserName: '没有相关记录'
                    	 });
                    	 
                    	 $('#dg').datagrid('mergeCells',{
                    		 index: 0,
                    		 field:'vcApplyUserName',
                    		 colspan:6
                    	 });
                 	 }else{
                 		 //有数据时，td增加一个title属性，方便展示
                 	 	addTitle();
                 	 }
                  }
		});   
		
	
	});
	
	//查看
	function openDetail(id){
		var url = "<%=basePath%>loanAction/lookLoanDetail?id="+id;
		window.location.href = url;
	}
	//编辑
	function edit(id){
		var url = "<%=basePath%>loanAction/openAddLoan?id="+id;
		$.webox({
			height:510,
			width:600,
			bgvisibel:true,
			title:'编辑',
			iframe:url
		});	
	}
	
	function del(id){
		var url ="<%=basePath%>loanAction/disableLoan?id="+id;
		$.post(url,function(data){
			showResponse(data);
		});
	}
	
	//查询方法
	function seacher(id){
		var jsonStr = {};
		var applayDate = $("#applayDate").val();
		if(null!=applayDate && ""!=applayDate){
			jsonStr.applayDate = applayDate;
		}
		var nflag = $("#nflag").val();
		if(null!=nflag && ""!=nflag){
			jsonStr.nflag = nflag;
		}
		
		$("#dg").datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	
	
	
	
	function reloadDatagrid(){
		var t =getWidth(400);
		$("#dg").datagrid({width:t });
	}
	
	
	//删除成功后刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		  if(json.isSuccess){
			$('#dg').datagrid('reload');  
		  }
		  alert(json.message);
		  $("body").unmask();
		}
	
	
	function openAdd(){
		var url = "<%=basePath%>loanAction/openAddLoan";
		$.webox({
			height:510,
			width:600,
			bgvisibel:true,
			title:'在线贷款',
			iframe:url
		});	
	}
</script>
</head>

<body class="main" onresize="reloadDatagrid(400);" >
	<dl class="tabs">
    	<dt><a href="javascript:;" ><span>贷款列表</span></a></dt>
    	<dd >
    		<div class="batch"><input onclick="openAdd();" type="button" class="f_btn2" value="新增" /> <!-- <input onclick="disableUser();" type="button" class="f_btn2" value="删除" /> --></div>
			<div class="search">
			申请时间：<input name="applayDate" class="f_txt" id="applayDate" style="width: 150px;" /> 贷款状态：<select id="nflag" name="nflag" ><option value="">--请选择--</option><option value="0">未提交</option><option value="1">已提交</option><option value="2">洽谈中</option><option value="3">已贷款</option><option value="4">贷不到款</option></select>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
			<table  id="dg"   ></table>
        </dd>
        
       
    </dl>
</body>
</html>