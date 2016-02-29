<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中联物流股份有限公司</title>
<link href="static/css/style.css" rel="stylesheet" type="text/css" />
<link href="static/css/anythingslider.css" media="all" rel="stylesheet" type="text/css" />
<link href="static/css/jquery-webox.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery.placeholder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/ks-switch.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/backhome/backhome.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/getQS.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-webox.js"></script>
<%@ include file="../include/datagrid.jsp"  %>
</head>
<script type="text/javascript" >


    

	function showRegPage()
	{
		$.webox({
			height:500,
			width:700,
			bgvisibel:true,
			title:'用户注册',
			iframe:'<%=basePath%>userAction/registerUserBefore'
		});
		//window.showModalDialog("userAction/registerUserBefore",null,"dialogWidth=600px;dialogHeight=400px;scroll=no;resizable=yes");
	}
	//关闭弹出窗口
	function webcolose(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	$(document).ready(function(){
			$('#dg').datagrid({     
			    url:'<%=basePath %>seckillAction/getSecKillList',
			    cache : false,
			    type : 'post',
			    width:958,
			    height:400,
			    fitColumns:true,
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			    iconCls:'icon-save',
			    striped:true,  //striped  是否显示斑马线效果。
			    sortName:'id',
			    sortOrder:'desc',
			    loadMsg:'信息加载中……',
			    columns:[[     
			        {field:'vcKillno',title:'抢单编号',width:80,align:'left'},     
			        {field:'nminPrice',title:'最高价',width:50,align:'left'},
			        {field:'vcStart',title:'起始点',width:100,align:'right'},
			        {field:'vcEnd',title:'目的地',width:100,align:'left'},
			        {field:'dtArriveTime',title:'抵达时间',width:100,align:'left',
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
			        		return unixTimestamp.toLocaleDateString()
			        	}},
			        {field:'dtKillStart',title:'开始时间',width:130,align:'left',
				        	formatter:function(value,rec,index){
				        		var unixTimestamp = new Date(value);  
		                        return unixTimestamp.toLocaleString();
				        	}},
			        {field:'dtKillEnd',title:'结束时间',width:130,align:'left',
					        	formatter:function(value,rec,index){
					        		var unixTimestamp = new Date(value);  
			                        return unixTimestamp.toLocaleString();
					        	}},
			        {field:'opt',title:'操作',width:50,align:'center',  
	                    formatter:function(value,rec,index){  
	                        var e = '<a href="javascript:;" class="rob" onclick="openGrab('+rec.id+')"></a> ';  
	                        return e;  
	                    }  
	                  }  
			       
			    ]],
			    queryParams:{
			    	vcStart:$("#vcStart").val(),
			    	vcEnd:$("#vcEnd").val()
			    },			    
			     onLoadError:function(){
	                      alert("加载数据失败！");
	                  },
                 onLoadSuccess:function(data){
                	 if(data.total==0){
                		 //当没数据时，显示提示
                		 $('#dg').datagrid('appendRow',{
                			 vcKillno: '没有相关记录'
                   	 });
                   	 
                   	 $('#dg').datagrid('mergeCells',{
                   		 index: 0,
                   		 field:'vcKillno',
                   		 colspan:8
                   	 });
                	 }else{
                		 //有数据时，td增加一个title属性，方便展示
                	 	addTitle();
                	 }
                 }
			});   
		
		});
	
	//打开抢单页面
	function openGrab(id){
		var url = "<%=basePath %>seckillAction/grabSeckill?orderId="+id;
		window.location.href=url;
		
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
	
	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  $('#dg').datagrid('reload'); 
		  }
		}
	
</script>
<body>
<%@include file="./header.jsp" %> 
<div class="container">
	<div class="module">
    	<div class="module_th"><span class="icon_car">秒杀订单</span></div>

   	  <div class="module_con">
            <div class="search">
                <input class="f_txt" type="text" name="vcKillno" id="vcKillno" placeholder="抢单编号"/> <input  id="vcStart" name="vcStart" class="f_txt" type="text" placeholder="起始点" value="${vcStart }" /> <input class="f_txt" type="text" id="vcEnd" name="vcEnd"  value="${vcEnd }" placeholder="目的地"/>  <a href="#" class="f_btn2"><span class="icon_search">搜索</span></a>
            	
          </div>
            <div class="">
            
            <table  id="dg"  ></table>
            
            </div>
            	
            
          </div>            
            
            
            
            
      </div>
    <%@include file="./foot.jsp" %> 
</body>
</html>
