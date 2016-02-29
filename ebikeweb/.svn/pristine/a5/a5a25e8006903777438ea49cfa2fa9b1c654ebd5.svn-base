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
<title>我中标的列表</title>
<%@include file="../include/common.jsp" %>
<%@ include file="../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
		$('#dg').datagrid({     
		    url:'<%=basePath %>seckillAction/getMyBidList',
		    cache : false,
		    type : 'post',
		    title:'我中标记录', 
		    width:w,
		    height:h,
		    pageSize:size,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
				        {field:'vckillno',title:'抢单编号',width:80,align:'left'},     
				        {field:'nminprice',title:'最高价',width:50,align:'left'},
				        {field:'vcstart',title:'起始点',width:100,align:'left'},
				        {field:'vcend',title:'目的地',width:100,align:'left'},
				        {field:'dtarrivetime',title:'抵达时间',width:100,align:'left',
				        	formatter:function(value,rec,index){
				        		var unixTimestamp = new Date(value);  
				        		return unixTimestamp.toLocaleDateString()
				        	}},
				        {field:'dtkillstart',title:'开始时间',width:130,align:'left',
					        	formatter:function(value,rec,index){
					        		var unixTimestamp = new Date(value);  
			                        return unixTimestamp.toLocaleString();
					        	}},
				        {field:'dtkillend',title:'结束时间',width:130,align:'left',
						        	formatter:function(value,rec,index){
						        		var unixTimestamp = new Date(value);  
				                        return unixTimestamp.toLocaleString();
						        	}},
				        {field:'opt',title:'操作',width:50,align:'center',  
		                    formatter:function(value,rec,index){ 
		                    	var e = '<a href="javascript:;" class="rob" onclick="look(\''+rec.id+'\');" >查看</a> ';
	                    		return e;
		                    }  
		                  }  
				       
				    ]],
		              onLoadSuccess:function(data){
		              	 if(data.total==0){
		              		 //当没数据时，显示提示
		              		 $('#dg').datagrid('appendRow',{
		              			 vckillno: '没有相关记录'
		                 	 });
		                 	 
		                 	 $('#dg').datagrid('mergeCells',{
		                 		 index: 0,
		                 		 field:'vckillno',
		                 		 colspan:8
		                 	 });
		              	 }else{
		              		 //有数据时，td增加一个title属性，方便展示
		              	 	addTitle();
		              	 }
		               },
		     	onLoadError:function(){
                      alert("加载数据失败！");
                  }
		});   
	
	});
	
	
	//查看
	function look(id){
		var url = "<%=basePath%>seckillAction/openSeckillBid?type=1&productId="+id;
		$.webox({
			height:500,
			width:680,
			bgvisibel:true,
			title:'查看详情',
			iframe:url
		});	
	}
	
	//查询方法
	function seacher(){
		var jsonStr = {};
		var vcKillno = $("#vcKillno").val();
		if(null!=vcKillno && ""!=vcKillno){
			jsonStr.vcKillno = vcKillno;
		}
		var vcStart = $("#vcStart").val();
		if(null!=vcStart && ""!=vcStart){
			jsonStr.vcStart = vcStart;
		}
		var vcEnd = $("#vcEnd").val();
		if(null!=vcEnd && ""!=vcEnd){
			jsonStr.vcEnd = vcEnd;
		}
		$('#dg').datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}


	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  $('#dg').datagrid('reload'); 
		  }
		}

	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	    var h = getHeight('dg1');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size });
	}
	
	
	
</script>
</head>

<body class="main" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dt><a href="#"><span>中标管理</span></a></dt>
        <dd >
			<div class="search">
			秒杀编号：<input name="vcKillno" class="f_txt" id="vcKillno" style="width: 120px;" /> 起始地：<input  id="vcStart" name="vcStart" class="f_txt" />
			目的地：<input  id="vcEnd" name="vcEnd" class="f_txt" />
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />
			</div>
		<table  id="dg" ></table>
			
		</dd>
    </dl>
</body>
</html>