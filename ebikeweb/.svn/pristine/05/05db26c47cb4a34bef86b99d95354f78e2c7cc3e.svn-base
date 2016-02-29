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
<title>我的消息</title>

<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg2');
	var size = getPageSize(h);
	//未忽略消息
	$('#dg2').datagrid({     
	    url:'<%=basePath %>messageAction/getNewMsg?type=nignore',
	    cache : false,
	    type : 'post',
	    title:'未开始订单', 
	    width:w,
	    height:h,
	    pageSize:size,
	    fitColumns:true,
	    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
	   // rownumbers:true,   //如果为true，则显示一个行号列。
	    iconCls:'icon-save',
	    striped:true,  //striped  是否显示斑马线效果。
	    sortName:'id',
	    sortOrder:'desc',
	    loadMsg:'信息加载中……',
	    columns:[[     
			        {field:'vcTitle',title:'标题',width:80,align:'left',
			        	formatter:function(value,rec,index){
			        		if( null==rec.vcLinkUrl){
			        			return value;
			        		}else{
				        		var e = '<a href="<%=basePath %>'+rec.vcLinkUrl+'" target="_self" >'+value+'</a>';
				        		return e;
			        		}
			        	}
			        },     
			        {field:'vcMsgType',title:'类型',width:80,align:'left'},     
			        {field:'vcContent',title:'内容',width:150,align:'left'},     
			        {field:'vcSendUserName',title:'发送人',width:50,align:'left'},
			        {field:'dtSend',title:'发送时间',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
			        		return unixTimestamp.toLocaleDateString()
			        	}},
			        {field:'opt',title:'操作',width:50,align:'center',  
	                    formatter:function(value,rec,index){ 
	                    	var e = '<a href="javascript:;" class="rob" onclick="ignore(\''+rec.id+'\');" >忽略</a> ';
	                    	var s = '<a href="javascript:;" class="rob" onclick="del(\''+rec.id+'\');" >删除</a> ';
                    		return e+s;
	                    }  
	                  }  
			       
			    ]],
	     	onLoadError:function(){
                  alert("加载数据失败！");
              },
              onLoadSuccess:function(data){
             	 if(data.total==0){
             		 //当没数据时，显示提示
             		 $('#dg2').datagrid('appendRow',{
             			vcTitle: '没有相关消息'
                	 });
                	 
                	 $('#dg2').datagrid('mergeCells',{
                		 index: 0,
                		 field:'vcTitle',
                		 colspan:6
                	 });
             	 }else{
             		 //有数据时，td增加一个title属性，方便展示
             	 	addTitle();
             	 }
              }
	}); 
	
	//忽略消息
	$('#dg1').datagrid({     
	    url:'<%=basePath %>messageAction/getNewMsg?type=ignore',
	    cache : false,
	    type : 'post',
	    title:'进行时订单', 
	    width:w,
	    height:400,
	    fitColumns:true,
	    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
	    //rownumbers:true,   //如果为true，则显示一个行号列。
	    iconCls:'icon-save',
	    striped:true,  //striped  是否显示斑马线效果。
	    sortName:'id',
	    sortOrder:'desc',
	    loadMsg:'信息加载中……',
	    columns:[[     
			        {field:'vcTitle',title:'标题',width:80,align:'left',
			        	formatter:function(value,rec,index){
			        		if(null==rec.vcLinkUrl){
			        			return value;
			        		}else{
				        		var e = '<a href="<%=basePath %>'+rec.vcLinkUrl+'" target="_self" >'+value+'</a>';
				        		return e;
			        		}
			        	}},     
			        {field:'vcMsgType',title:'类型',width:80,align:'left'},     
			        {field:'vcContent',title:'内容',width:150,align:'left'},     
			        {field:'vcSendUserName',title:'发送人',width:50,align:'left'},
			        {field:'dtSend',title:'发送时间',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
			        		return unixTimestamp.toLocaleDateString()
			        	}},
			        {field:'opt',title:'操作',width:50,align:'center',  
	                    formatter:function(value,rec,index){ 
	                    	var e = '<a href="javascript:;" class="rob" onclick="nignore(\''+rec.id+'\');" >恢复</a> ';
                  		return e;
	                    }  
	                  }  
			       
			    ]],
	     	onLoadError:function(){
                alert("加载数据失败！");
            },
            onLoadSuccess:function(data){
           	 if(data.total==0){
           		 //当没数据时，显示提示
           		 $('#dg1').datagrid('appendRow',{
           			vcTitle: '没有相关消息'
              	 });
              	 
              	 $('#dg1').datagrid('mergeCells',{
              		 index: 0,
              		 field:'vcTitle',
              		 colspan:6
              	 });
           	 }else{
           		 //有数据时，td增加一个title属性，方便展示
           	 	addTitle();
           	 }
            }
	}); 
	
	
	});
	
	//忽略消息动作
	function  ignore(id){
		
		$("body").mask("数据处理中，请稍等……");
		var url = "<%=basePath %>messageAction/ignoreMsg?ig=y&id="+id;
		$.post(url,function(data){
			showResponse(data);
		});
	}
	
	//恢复未忽略消息动作
	function nignore(id){
		$("body").mask("数据处理中，请稍等……");
		var url = "<%=basePath %>messageAction/ignoreMsg?ig=n&id="+id;
		alert(url);
		$.post(url,function(data){
			var json = eval("("+data+")");
			alert(json.message);
			$("body").unmask();
			  if(json.isSuccess){
				  $('#dg1').datagrid('reload'); 
			  }

		});
	}
	
	//删除消息
	function del(id){
		if(confirm("确认删除吗？")){
			$("body").mask("数据处理中，请稍等……");
			var url = "<%=basePath %>messageAction/delMsg?id="+id;
			$.post(url,function(data){
				showResponse(data);
			});
		}
	}
	
	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		$("body").unmask();
		  if(json.isSuccess){
			  $('#dg2').datagrid('reload'); 
		  }
		}
	
	function reloadDatagrid(){
		 var t =getWidth(400);
	    var h = getHeight('dg1');
		var size = getPageSize(h);
	    $('#dg1').datagrid({width:t,height:h,pageSize:size });
	}
	//查询方法
	function searcher(type){
		var json  = {};
		var vcTitle = $("#vcTitle_"+type).val();
		if(null != vcTitle && ""!=vcTitle){
			json.vcTitle = vcTitle;
		}
		var vcSendUserName = $("#vcSendUserName_"+type).val();
		if(null !=vcSendUserName && ""!=vcSendUserName){
			json.vcSendUserName = vcSendUserName;
		}
		var dtSend1 = $("#dtSend1_"+dtSend1).val();
		if(null != dtSend1 && ""!=dtSend1){
			json.dtSend1 = dtSend1;
		}
		var dtSend2 = $("#dtSend2_"+dtSend1).val();
		if(null != dtSend2 && ""!=dtSend2){
			json.dtSend1 = dtSend2;
		}
		$('#dg'+type).datagrid('load',json);
	}
</script>
</head>

<body class="main easyui-layout" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dt><a href="javascript:;"  ><span>未忽略消息</span></a><a href="javascript:;" onmouseover="reloadDatagrid('dg1'); "><span>已经忽略消息</span></a></dt>
    	<dd >
			<div class="search">
				<input name="vcTitle" class="f_txt" id="vcTitle_1" style="width: 150px;"  placeholder="标题" />  <input  id="vcSendUserName_1" name="vcSendUserName" class="f_txt" style="width: 120px;"  placeholder="发送人" /> 发送时间：<input  id="dtSend1_1" name="dtSend1" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /> >> <input  id="dtSend2_1" name="dtSend2" class="Wdate" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
			 <input name="" onclick="seacher(2);" type="button" class="f_btn3" />
			</div>
			<table  id="dg2"   ></table>
        </dd>
        
        <dd >
			<div class="search">
				<input name="vcTitle" class="f_txt" id="vcTitle_1" style="width: 150px;"  placeholder="标题" />  <input  id="vcSendUserName_1" name="vcSendUserName" class="f_txt" style="width: 120px;"  placeholder="发送人" /> 发送时间：<input  id="dtSend1_1" name="dtSend1" class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" /> >> <input  id="dtSend2_1" name="dtSend2" class="Wdate" onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
			 <input name="" onclick="seacher(1);" type="button" class="f_btn3" />
			</div>
			<table  id="dg1"   ></table>
        </dd>
        
       
    </dl>
</body>
</html>