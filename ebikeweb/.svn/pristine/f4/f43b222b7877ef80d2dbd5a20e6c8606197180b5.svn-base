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
<title>发运信息</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript"><!--
//p.pagination({
	//				layout:['first','prev','next','last','sep','links','sep','manual'],
	//				beforePageText:'Go Page',
	//				afterPageText:''
	//			});

var partypes = [];
var pt = new Object();
pt.index = 0;
pt.paramName = "parEntrance";
pt.parContents = "入场确定";
partypes.push(pt);
var pt = new Object();
pt.index = 1;
pt.paramName = "parload";
pt.parContents = "装车确定";
partypes.push(pt);
var pt = new Object();
pt.index = 2;
pt.paramName = "parship";
pt.parContents = "发运确定";
partypes.push(pt);

var pt = new Object();
pt.index = 3;
pt.paramName = "parArrived";
pt.parContents = "运抵确定";
partypes.push(pt);
var pt = new Object();
pt.index = 4;
pt.paramName = "parReturn";
pt.parContents = "回单确定";
partypes.push(pt);
var pt = new Object();
pt.index = 5;
pt.paramName = "parsecure";
pt.parContents = "买保险确定";
partypes.push(pt);
var shipno="";

$(document).ready(function(){
	// 初始化 获取需发运数量
	getShipCount();
	changecss('parEntrance');
	
	var w=getWidth(400);
		$('#dg').datagrid({     
		    url:'<%=basePath %>shipheadAction/getAllShipdetailBySubno',
		    type : 'post',
		    width:w,
		    title:'发运详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'HEADID',title:' ',width:40,
		        	formatter:function(value,row,index){
		        		var result = '<input type="checkbox" id="headcheck" shipno='+row.VC_SHIPNO+' headid='+row.HEADID+' name="headcheck"   />';
		        		return result;
		        	}	
		        }, 
		         
		        {field:'VC_SHIPNO',title:'发运指令',width:150,align:'left',
		        	formatter:function(value,row,index){
		        		//if(row.partype=="parsecure"){
		        			//var result = '<a href="javascript:showShipVins('+row.VC_SHIPNO+');" >'+row.VC_SHIPNO+'</a>';
		        		//}else{
		        			//var result = '<a href="javascript:showheadDetails('+row.HEADID+');" >'+row.VC_SHIPNO+'</a>';
		        		//}
		        		var partype=row.partype;
		        		//alert("partype = "+partype);
		        		var result = '<a href="javascript:;" onclick="showheadDetails('+row.HEADID+',\''+partype+'\');">'+row.VC_SHIPNO+'</a>';
		        		//alert("result ="+result);
		        		return result;
		        	}	
		        
		        },     
		        {field:'OPDATE',title:'指令时间',width:120,align:'left'},   
		        {field:'VC_CAR_NO',title:'车牌号',width:100,align:'left',
		        		formatter:function(value,row,index){
		        		var result = '<a href="javascript:showDriverInfo('+row.DRIVERID+');" >'+row.VC_CAR_NO+'</a>';
		        		
		        		return result;
		        	}	
		        }, 
		        {field:'VC_START_CITY',title:'起运地',width:100,align:'left'},
		        {field:'VC_DEST_CITY',title:'目的地',width:100,align:'left'},
		        {field:'SHIPCOUNT',title:'发运数量',width:100,align:'right',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:showheadDate('+row.HEADID+');" >'+row.SHIPCOUNT+'</a>';
		        		
		        		return result;
		        	}	
		        },
		        {field:'NQTY',title:'回单确认',width:80,hidden:true,align:'center',
		        	formatter:function(value,row,index){
		        		var result="";
		        	    result = '<a href="javascript:returnSub('+row.HEADID+');" >回单确认</a>';
		        		return result;
		        	}	
		        }
		        
		    ]],
		     onSelect:function(index,row){
            	var check = $("#ship_"+row.HEADID)[0] ;
            	//check.checked=true;
            	//shipClick(check,row.HEADID);
            },onUnselect:function(index,row)
            {
            	var check =$("#ship_"+row.HEADID)[0] ;
            	//check.checked=false;
            	//shipClick(check,row.HEADID);
            },
			  onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
            },onClickCell:function(index,field,value){
            	//alert("fie"+field);
            	if(field=='SHIPCOUNT')
            	{
            		return;
            	}
            },
		     onLoadError:function(){
                     // alert("加载数据失败！");
              }
                  
		    
		});   
		
	});
	//获取Vin码列表
	//function showShipVins(shipno){
		
		//$.webox({
			//height:360,
			//width:500,
			//bgvisibel:true,
			//title:'vin码列表',
			//iframe:url
		//});
	//}
	function returnSub(headid)
	{
		
		var url = '<%=basePath%>shipheadAction/IntogetShipInfoByHeadID?headID='+headid+'&partype=parReturn';
					$.webox({
							height:720,
							width:950,
							bgvisibel:true,
							title:' 发运详情 ',
							iframe:url
					});
	}
	
	
	function showheadDate(headid)
	{
		
		var url = "<%=basePath%>shipheadAction/IntogetShipInfoByHeadID?headID="+headid;
					$.webox({
							height:300,
							width:500,
							bgvisibel:true,
							title:' 发运详情 ',
							iframe:url
					});
	}
	
	function showheadDetails(headid,partype){
		//alert("partype ="+partype);
		var url="<%=basePath%>shipheadAction/intogetShipDetailsByID?headID="+headid+"&partype="+partype;
				$.webox({
							height:500,
							width:600,
						    bgvisibel:true,
							title:' 指令详情 ',
							iframe:url
					});
	}
	//显示车辆司机信息 
	function showDriverInfo(truckID)
	{
		var url = "<%=basePath%>truckDriverAction/getDriverInfoByTruckID?truckID="+truckID;
					$.webox({
							height:360,
							width:550,
							bgvisibel:true,
							title:' 司机详情 ',
							iframe:url
					});
	}
	//验证结算数量 类型 
	function checkCount(obj,shipcount,shipid)
	{
		
		var countNum = parseInt(obj.value);
		if (isNaN(obj.value)  || $.trim(obj.value)=="")
		{
			alert("请输入数字类型.");
			obj.value = 0;
			obj.select();
			return;
		}
		if(countNum > shipcount)
		{
			alert("结算数量大于发运的数量.");
			obj.value = shipcount;
			obj.select();
			return;
		}
	}
	
	
	//列表左边的checkbox 单击事件 
	function shipClick(obj,headID)
	{
		if($("#statu").val()=="parReturn")
		{
			if(obj.checked==true)
			{
				
				$("#NQTY"+headID)[0].readOnly=false;
				$("#NQTY"+headID)[0].focus();
				$("#NQTY"+headID)[0].select();
				
				//alert(" a "+$("#NQTY"+headID)[0].attr("readOnly"));
				//$("input[qlt='"+shipID+"']").each(function(){
					//$(this).attr("readOnly",false);
				//});
				//aa();
				
			}else
			{
				$("#NQTY"+headID)[0].readOnly=true;
				
			}
		}
		
	}
	
	
	function sure()
	{
		
		 var count = $('input[name="headcheck"]:checked').length;
		var shipnos ="";
		 if (count > 0){
		 	
		 		var partype = $("#statu").val();
		 		
	 			if(partype=="parReturn")
	 			{
	 			
	 				alert("请点击回单确认");
	 				return;
					 $('input[name="headcheck"]:checked').each(function(){
					 	
					 	var shipno = $(this).attr("shipno");
					 	var headid = $(this).attr("headid");
					 	var nqty = $("#NQTY"+headid).val();
					 	shipnos +=headid+","+nqty+";";
					 });
	 				
	 			}else
	 			{
	 				 $('input[name="headcheck"]:checked').each(function(){
					 	var shipno = $(this).attr("shipno");
					 	shipnos +=shipno+",";
					 });
					 
	 				
	 			}
		 			
		 			
		 		
		 		//alert(partype+" "+shipnos);
		 		
		 		 $("body").mask("数据处理中，请稍等……");
		 		 
					$.post("<%=basePath%>shipheadAction/saveDespatchInfo", 
					{ partype:partype,shipnos:shipnos },     
					   function (data, textStatus)
					   {     
							$("body").unmask();
								
							if(data.isSuccess == true)
							{
								alert(data.message);
								window.location.href=window.location.href; 
								window.location.reload; 
							}else
							{
								alert(data.message);
							}
					   }
				  ,"json");
		     
		     	
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
		
		
	}
	//查询方法
	function seacher(partype){
		changecss(partype);
	
		//alert(">>"+partype);
		var jsonStr = {};
		//var partype = $("#selpar").val();
		$("#statu").val(partype);
		var shipno = $("#shipno").val();
		var drivername = $("#drivername").val();
		var driverno = $("#driverno").val();
		
		if(null!=partype){
			jsonStr.partype=partype;
		}
		if(null!=shipno && "" != shipno){
			jsonStr.shipno=shipno;
		}if(null!=drivername && ""!=drivername){
			jsonStr.drivername=drivername;
		}
		if(null!=driverno && ""!=driverno){
			jsonStr.driverno=driverno;
		}
		
		$('#dg').datagrid('load',jsonStr);
		if($("#statu").val()=="parReturn")
		{
			$('#dg').datagrid('showColumn',"NQTY");	
		}else
		{
			$('#dg').datagrid('hideColumn',"NQTY");	
		}
		
	}
	
	function changecss(partype)
	{
		$("#"+partype).siblings().removeAttr("style");
		$("#"+partype).attr("style","font-size:20px;color:black;font-weight:bold;font-family:微软雅黑;");
		
		for(var i=0;i<partypes.length;i++)
		{
			  if(partypes[i].paramName == partype)
			  {
			  	$("#subval").val(partypes[i].parContents);
			  	break;
			  } 
		}
	}
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	   
	    $('#dg').datagrid({width:t });
	}
	function getSatMsg(paramName)
	{
		for(var i=0;i<partypes.length;i++)
		{
			var obj = partypes[i];
			if(obj.paramName==paramName)
			{
				return obj;
			}
		}
	}
	// 初始化获取 需装车 需装车 需发运 需回单 的指令数量
	function getShipCount()
	{
		$.post("<%=basePath%>shipheadAction/getShipCount", 
						{  },     
						   function (data, textStatus)
						   {     
								$("#entranceCount").html(" ( "+data.entranceCount+" ) ");
								$("#loadCount").html(" ( "+data.loadCount+" ) ");
								$("#shipCount").html(" ( "+data.shipCount+" ) ");
								$("#arrivedCount").html(" ( "+data.arrivedCount+" ) ");
								$("#returnCount").html(" ( "+data.returnCount+" ) ");	
								$('#secureCount').html("("+data.secureCount+")");
						   },"json");
	}
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
--></script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>发运管理</span></a></dt>
        <dd >
        <div class="batch1">
        	<a id="parEntrance" class="first" href="javascript:seacher('parEntrance')"><span>入场<span id="entranceCount"></span></span> <span class="dec">入场</span></a>
        	<a id="parload" href="javascript:seacher('parload')"><span>装车<span id="loadCount"></span></span> <span class="dec">装车</span></a>
        	<a id="parsecure" href="javascript:seacher('parsecure')"><span>买保险<span id="secureCount"></span></span> <span class="dec">买保险</span></a>
        	<a id="parship" href="javascript:seacher('parship')" ><span>发运<span id="shipCount"></span></span> <span class="dec">发运</span></a>
        	<a id="parArrived" href="javascript:seacher('parArrived')" ><span>运抵<span id="arrivedCount"></span></span> <span class="dec">运抵</span></a>
        	<a id="parReturn" class="last" href="javascript:seacher('parReturn')"><span>回单<span id="returnCount"></span></span> <span class="dec">回单</span></a>
        	<!-- 
        	<input name="" id="" onclick="sure('parEntrance');" type="button" class="f_btn2" value="入场确定" /> 
        	<input name="" id="" onclick="sure('parload');" type="button" class="f_btn2" value="装车确定" /> 
        	<input name="" id="" onclick="sure('parship');" type="button" class="f_btn2" value="发车确定" /> 
        	<input name="" id="" onclick="sure('parReturn');" type="button" class="f_btn2" value="回单确定" />
        	 --> 
        </div>
        <div class="search">
        	<!-- 
        	<select id="selpar" name="">
        		<option selected="selected" value="parEntrance">--未入场--</option>
        		<option value="parload">--未装车--</option>
        		<option value="parship">--未发车--</option>
        		<option value="parReturn">--未回单--</option>
        	</select>
        	 -->
			 <input type="text" id="shipno" class="f_txt f_short" placeholder="指令号"/>
			 
			 <input type="text" id="driverno" class="f_txt f_short" placeholder="车牌号"/>
			 <input name="" onclick="seacher($('#statu').val());" type="button" class="f_btn3" />  
			 <input name="" id="subval" type="button" value="确定" class="f_btn" onclick="sure();" />  
			</div>
		<table  id="dg"   >
	
		</table>
			
		</dd>
    </dl>
    <input type="hidden" id="statu" value="parEntrance" />
</body>
</html>