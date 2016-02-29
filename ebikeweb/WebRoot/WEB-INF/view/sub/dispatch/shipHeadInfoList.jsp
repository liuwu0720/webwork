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

<%@include file="../../include/common.jsp"%>
<%@ include file="../../include/datagrid.jsp"%>

<script type="text/javascript">
var allcarcount=[];
var orderArr=[];
var driverID="";
var driId="";
var truckDriverId;
	$(document).ready(function(){
		
	
		// 触发左边菜单的隐藏   自动隐藏 因宽度不够 
		//window.parent.control.$("#iframe_btn").trigger("click");
		/*订单配载*/
		initLeftTab();
		initRightTab();
		//$("#iddl").mask("数据处理中，请稍等……"); 
		/*配载追加*/
		//initLeftTab2();
		//initRightTab2();
		/*取消配载*/
		
	});
	var w=getWidth(400);
	//var h = getHeight('dg');
	//var size = getPageSize(h);
	/*订单配载*/
	function initLeftTab()
	{
		
		$('#lefttab').datagrid({     
		    url:'<%=basePath%>shipheadAction/getTruckAndDriver',
		    type : 'post',
		    title:'车辆详情', 
		    //pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    //  fitColumns:true,
		    pageList :[10,15,20],
		    scrollbarSize:100,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
                {field:'c',title:'',width:30,formatter: function(value,row,index){
		        	 var s = '<input type="checkbox" id="dri'+index+'" name="dri" onclick="driClick(this,'+row.ID+',\''+ row.VC_CAR_NO + '\','+index+')"/>';
		        	 return s; 
		        	}
		        }, 
		        {field:'ID',title:'',width:1,hidden:true},    
		        {field:'VC_CAR_NO',title:'车牌号',width:80,align:'left'},
		        {field:'VC_DRIVER_NAME',title:'司机',width:80,align:'left'},
		        {field:'VC_DRIVER_TEL',title:'司机号码',width:80,align:'left'}
		       
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
              },
			  onLoadSuccess: function (data) {
			 
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
              //$('#pp').pagination({
				//layout:['first','prev','next','last'],
				//total:data.total, 
				//pageSize:10,
				//displayMsg:''
		
				//});
            }   
		    
		});   
		
	}
	function initRightTab()
	{
		$('#righttab').datagrid({     
		    url:'<%=basePath%>shipheadAction/getEnableOrdersBySubno',
		    cache : false,
		    type : 'post',
		    title:'订单详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 2,
		    pageList :[10,15,20],
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		         {field:'c',title:'',width:30,formatter: function(value,row,index){
		        	 var s = '<input type="checkbox" id="ord'+index+'" name="ord'+index+'" onclick="ordClick(this,\''+row.VC_ORDERNO+'\',\''+ row.VC_CAR_STYLE + '\','+ row.shipCount +','+index+');"/>';
		        	 return s; 
		        	}
		        },      
		        {field:'VC_ORDERNO',title:'订单号',width:100,align:'left'},     
		        {field:'VC_START_CITY',title:'起运城市',width:100,align:'left'}, 
		        {field:'VC_DEST_CITY',title:'目的城市',width:100,align:'left'},   
		        {field:'VC_RECEIVE_CONTACT',title:'收货人',width:100,align:'left'},
		        {field:'VC_RECEIVE_ADDRESS',title:'收货地址',width:180,align:'left'},
		        {field:'VC_RECEIVE_TEL',title:'收货人电话',width:100,align:'left'},
		        {field:'VC_CAR_STYLE',title:'车型',width:100,align:'left'},
		        {field:'N_SHIPEDQTY',title:'已发运',width:50,align:'right'},
		        {field:'shipCount',title:'发运数量',width:60,align:'center',
		        	formatter: function(value,row,index){
		        		 var s = '<input type="text" id="ordship'+index+'" name ="ordship'+index+'" style="width: 30px;" readonly="readonly" onblur="checkCount(this,\''+row.VC_ORDERNO+'\','+row.QUANTITY+');" value='+row.QUANTITY+' /> ';  
		        		 return s;
		        	}
		        },
		        {field:'N_TOTAL_CAR',title:'数量',width:50,align:'right'}
		    ]],
			  onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
			  
            },
		     onLoadError:function(){
                      alert("加载数据失败！");
            }
		});   
		
	}
	//左边车辆table的checkbox  单击
	function driClick(obj,carid,carno,index)
	{
		var carsp = $("#carno")[0];
		driverID = carid;
		var checklist = document.getElementsByName ("dri");
		for(var i=0;i<checklist.length;i++)
	   {
	       checklist[i].checked = false;
	   } 
		obj.checked = true;
		carsp.innerText=carno+" ";
	}
	
	function ordClick(obj,orderno,carsty,shipCount,index)
	{
		//发运数量
		var shipCount = $("#ordship"+index)[0];
		var carcount = new Object();
		carcount.orderno=orderno;
		carcount.carsty=carsty;
		carcount.index=index;
		carcount.shipCount=parseInt(shipCount.value);
		
		if(obj.checked==true)
		{
			allcarcount.push(carcount);
			
			shipCount.readOnly=false;
			shipCount.select();
		}else
		{
			
			for(var i=0;i<allcarcount.length;i++)
			{
				var car = allcarcount[i];
				if(carcount.orderno == car.orderno)
				{
					allcarcount[i]=[];
				}
				
			}
			
			
			shipCount.readOnly=true;
		}	
		
		
		
		showCarCount();
	}
	
	function showCarCount()
	{
		var showcar = $("#carCount")[0];
		var str="";
		var tolcount =0;
		for(var i=0;i<allcarcount.length;i++)
		{
			var car = allcarcount[i];
			
			if(typeof(car.orderno)!='undefined')
			{
				
				str +="\t\t\t"+ car.orderno+" "+car.carsty+" "+ car.shipCount +"\n ";
				tolcount += car.shipCount;
			}
			
		}
		showcar.innerText="\n"+str+" \n \t\t\t 总数 "+tolcount;
		
	}
	function checkCount(count,orderno,tolcount)
	{
		var countNum = parseInt(count.value);
		if(countNum > tolcount)
		{
			alert("输入的数量大于可用的数量");
			count.value = tolcount;
			count.select();
			return;
		}else if(countNum == 0){
			alert("数量不能为空");
			count.value = tolcount;
			count.select();
			return;
		}else{

			for(var i=0;i<allcarcount.length;i++)
			{
				var car = allcarcount[i];
				if(car.orderno == orderno )
				{
					allcarcount[i].shipCount = countNum;
					break;
				}
				
			}
			showCarCount();
	
		}
		
	}
	
	function loading(){                    //配载
		if($.trim(driverID)=="")
		{
			alert("请选择车辆");
			return;
		}
		var msgstr = "";
		for(var i=0;i<allcarcount.length;i++)
		{
			
			var car = allcarcount[i];
			if(typeof(car.orderno)!='undefined')
			{
				msgstr+=car.orderno+","+car.shipCount+";";
				
			}
			
		}
		if($.trim(msgstr)=="")
		{
			alert("请选择订单");
			return;
		}
		
		 $("body").mask("数据处理中，请稍等……");
		 
		$.post("<%=basePath%>shipheadAction/loading", 
					{ driverID:driverID,msgstr:msgstr },     
					   function (data, textStatus)
					   {     
					   		 
							$("body").unmask();
							
							if(data.resultCode == "ok")
							{
								alert(data.errorContent);
								window.location.href=window.location.href; 
								window.location.reload; 
							}else if(data.resultCode == "cai")
							{
								alert(data.errorContent);
								window.location.href=window.location.href; 
								window.location.reload; 
							}
							
							else
							{
								alert(data.errorContent);
							}
					   }
				  ,"json");
	}
	
	
	
	function sub() //指令生成
	{
		if($.trim(driverID)=="")
		{
			alert("请选择车辆");
			return;
		}
		var msgstr = "";
		for(var i=0;i<allcarcount.length;i++)
		{
			
			var car = allcarcount[i];
			if(typeof(car.orderno)!='undefined')
			{
				msgstr+=car.orderno+","+car.shipCount+";";
				
			}
			
		}
		if($.trim(msgstr)=="")
		{
			alert("请选择订单");
			return;
		}
		
		 $("body").mask("数据处理中，请稍等……");
		 
		$.post("<%=basePath%>shipheadAction/save", {
			driverID : driverID,
			msgstr : msgstr
		}, function(data, textStatus) {

			$("body").unmask();

			if (data.resultCode == "ok") {
				alert(data.errorContent);
				window.location.href = window.location.href;
				window.location.reload;
			} else if (data.resultCode == "cai") {
				alert(data.errorContent);
				window.location.href = window.location.href;
				window.location.reload;
			}

			else {
				alert(data.errorContent);
			}
		}, "json");
	}

	function queryCars() {
		var jsonStr = {};
		//var driverName = $("#driverName").val();
		var vcCarNo = $("#vcCarNo").val();
		//if (null != driverName) {
			//jsonStr.driverName = driverName;
		//}
		if (null != vcCarNo) {
			jsonStr.vcCarNo = vcCarNo;
		}
		$('#lefttab').datagrid('load', jsonStr);
	}
	function queryOrders() {
		var jsonStr = {};
		var orderNo = $("#orderNo").val();
		var staCity = $("#staCity").val();
		var destCity = $("#destCity").val();
		if (null != orderNo) {
			jsonStr.orderNo = orderNo;
		}
		if (null != staCity) {
			jsonStr.staCity = staCity;
		}
		if (null != destCity) {
			jsonStr.destCity = destCity;
		}
		$('#righttab').datagrid('load', jsonStr);
	}
	/*配载追加*/
	function initLeftTab2()
	{
		$('#lefttab_2').datagrid({     
		    url:'<%=basePath%>shipheadAction/getLoadedTrucks',
		    type : 'post',
		    title:'车辆详情', 
		    //pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    //  fitColumns:true,
		    pageList :[10,15,20],
		    scrollbarSize:100,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
				{field:'c',title:'',width:30,formatter: function(value,row,index){
	 				var s = '<input type="checkbox" id="dri'+index+'" name="trucks" onclick="leftClick(this,'+row.ID+',\''+ row.VC_CAR_NO + '\','+index+')"/>';
	 				return s; 
					}
				}, 
				{field:'ID',title:'',width:1,hidden:true},     
		        {field:'VC_CAR_NO',title:'车牌号',width:80,align:'left'},
		        {field:'VC_DRIVER_NAME',title:'司机',width:80,align:'left'},
		        {field:'VC_DRIVER_TEL',title:'司机号码',width:80,align:'left'}
		       
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
              },
			  onLoadSuccess: function (data) {
			 
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
              //$('#pp').pagination({
				//layout:['first','prev','next','last'],
				//total:data.total, 
				//pageSize:10,
				//displayMsg:''
		
				//});
            }   
		    
		});   
		
	}
	function initRightTab2()
	{
		$('#righttab_2').datagrid({     
		    url:'<%=basePath%>shipheadAction/getEnableOrdersBySubno',
		    cache : false,
		    type : 'post',
		    title:'订单详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 2,
		    pageList :[10,15,20],
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		         {field:'c',title:'',width:30,formatter: function(value,row,index){
		        	 var s = '<input type="checkbox" id="ord'+index+'" name="ord'+index+'" onclick="rightClick(this,\''+row.VC_ORDERNO+'\',\''+ row.VC_CAR_STYLE + '\','+ row.shipCount +','+index+');"/>';
		        	 return s; 
		        	}
		        },      
		        {field:'VC_ORDERNO',title:'订单号',width:100,align:'left'},     
		        {field:'VC_START_CITY',title:'起运城市',width:100,align:'left'}, 
		        {field:'VC_DEST_CITY',title:'目的城市',width:100,align:'left'},   
		        {field:'VC_RECEIVE_CONTACT',title:'收货人',width:100,align:'left'},
		        {field:'VC_RECEIVE_ADDRESS',title:'收货地址',width:180,align:'left'},
		        {field:'VC_RECEIVE_TEL',title:'收货人电话',width:100,align:'left'},
		        {field:'VC_CAR_STYLE',title:'车型',width:100,align:'left'},
		        {field:'N_SHIPEDQTY',title:'已发运',width:50,align:'right'},
		        {field:'shipCount',title:'发运数量',width:60,align:'center',
		        	formatter: function(value,row,index){
		        		 var s = '<input type="text" id="ordership'+index+'" name ="ordership'+index+'" style="width: 30px;" readonly="readonly" onblur="checkShipCount(this,\''+row.VC_ORDERNO+'\','+row.QUANTITY+');" value='+row.QUANTITY+' /> ';  
		        		 return s;
		        	}
		        },
		        {field:'N_TOTAL_CAR',title:'数量',width:50,align:'right'}
		    ]],
			  onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
			  
            },
		     onLoadError:function(){
                      alert("加载数据失败！");
            }
		});   
		
	}
	//追加配载左边列表点击事件
	function leftClick(obj,id,carno,index){
		//alert('isChecked:'+obj.checked);
		driId= id;
		if(obj.checked==true){
			var checkboxs=document.getElementsByName('trucks');
			for(var i=0;i<checkboxs.length;i++){
				checkboxs[i].checked=false;
			}
			obj.checked=true;
			//alert(carno);
			$('#carno_load').html(carno+'&nbsp;');//车辆编号
			$('#carno_add').html(carno+'&nbsp;');//车辆编号
			$.post('<%=basePath%>shipheadAction/getOrdersByTruckId',
					{truckId:id},
					function(json){
						//alert(json.message);
						var data=json.data;
						var msg='<br/>';
						var total=0;
						if(json.isSuccess){
							
							for(var i=0;i<data.length;i++){
								msg+=data[i].VC_ORDERNO+'&nbsp&nbsp'+data[i].VC_CAR_NAME+'&nbsp&nbsp'
								      +data[i].N_TOTAL_CAR+'&nbsp&nbsp'+data[i].VC_START_CITY+'(起运城市)<br/>';
							     total+=data[i].N_TOTAL_CAR;
							}
							
						}
						//alert(msg);
						msg+='总数：'+total;
						$('#carCount_load').html(msg);
					},
					'json'
			 );

		}else{
			//如果没选中则清空数据
			$('#carno_load').empty();
			$('#carCount_load').empty();
			$('#carno_add').empty();
		}
	}
	//追加配载右边列表点击事件
	function rightClick(obj,orderno,vcCarStyle,shipcount,index){
		
		if(obj.checked){
			$('#ordership'+index).attr('readOnly',false);
			$('#ordership'+index).select();
			//alert($('#ordership'+index).val());
			var shipCount=$('#ordership'+index).val();
			var order=new Object();
			order.orderno=orderno;
			order.vcCarStyle=vcCarStyle;
			order.shipCount=parseInt(shipCount);
			orderArr.push(order);
		}else{
			for(var i=0;i<orderArr.length;i++){
				if(orderno==orderArr[i].orderno){
					orderArr[i]={};
				}
			}
			$('#ordership'+index).attr('readOnly',true);
		}
		//alert(orderArr.length);
		showCount();
	}

	function appendLoading(){                    //追加配载
		if($.trim(driId)=="")
		{
			alert("请选择车辆");
			return;
		}
		var msgstr = "";
		for(var i=0;i<orderArr.length;i++)
		{
			
			var ord = orderArr[i];
			if(typeof(ord.orderno)!='undefined')
			{
				msgstr+=ord.orderno+","+ord.shipCount+";";
				
			}
			
		}
		if($.trim(msgstr)=="")
		{
			alert("请选择订单");
			return;
		}
		alert(driId+','+msgstr);
		 $("body").mask("数据处理中，请稍等……");
		 
		$.post("<%=basePath%>shipheadAction/appendLoading", 
					{ truckId:driId,msgstr:msgstr },     
					   function (data, textStatus)
					   {     
					   		 
							//$("body").unmask();
							
							if(data.isSuccess)
							{
								alert(data.message);
								window.location.href=window.location.href; 
								window.location.reload; 
							}else 
							{
								alert(data.message);
								window.location.href=window.location.href; 
								window.location.reload; 
							}
					   }
				  ,"json");
	}
	//核对数量
	function checkShipCount(count,orderno,tolcount)
	{
		var countNum = parseInt(count.value);
		if(countNum > tolcount)
		{
			alert("输入的数量大于可用的数量");
			count.value = tolcount;
			count.select();
			return;
		}else if(countNum == 0){
			alert("数量不能为空");
			count.value = tolcount;
			count.select();
			return;
		}else{

			for(var i=0;i<orderArr.length;i++)
			{
				var car = orderArr[i];
				if(car.orderno == orderno )
				{
					orderArr[i].shipCount = countNum;
					break;
				}
				
			}
			showCount()
	
		}
		
	}
	//显示追加数据
	function showCount(){
		$('#carCount_add').empty();
		var str='<br/>';
		var count=0;
		for(var i=0;i<orderArr.length;i++){
			if(typeof(orderArr[i].orderno)!='undefined'){
				str+=orderArr[i].orderno+' '+orderArr[i].vcCarStyle
				+' '+orderArr[i].shipCount+'<br/>';
				count+=orderArr[i].shipCount;
			}
		}
		str+='<br/>总数：'+count;
		$('#carCount_add').html(str);
	}
	function queryCars2() {
		var jsonStr = {};
		//var driverName = $("#driverName").val();
		var vcCarNo = $("#vcCarNo_2").val();
		//if (null != driverName) {
			//jsonStr.driverName = driverName;
		//}
		if (null != vcCarNo) {
			jsonStr.vcCarNo = vcCarNo;
		}
		$('#lefttab_2').datagrid('load', jsonStr);
	}
	function queryOrders2() {
		var jsonStr = {};
		var orderNo = $("#orderNo_2").val();
		var staCity = $("#staCity_2").val();
		var destCity = $("#destCity_2").val();
		if (null != orderNo) {
			jsonStr.orderNo = orderNo;
		}
		if (null != staCity) {
			jsonStr.staCity = staCity;
		}
		if (null != destCity) {
			jsonStr.destCity = destCity;
		}
		$('#righttab_2').datagrid('load', jsonStr);
	}
	/*取消配载*/
	function initLeftTab3()
	{
		$('#lefttab_3').datagrid({     
		    url:'<%=basePath%>shipheadAction/getLoadedTrucks',
		    type : 'post',
		    title:'车辆详情', 
		    //pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    //  fitColumns:true,
		    pageList :[10,15,20],
		    scrollbarSize:100,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
				{field:'c',title:'',width:30,formatter: function(value,row,index){
	 				var s = '<input type="checkbox" id="dri'+index+'" name="cars" onclick="leftClick2(this,'+row.ID+')"/>';
	 				return s; 
					}
				}, 
				{field:'ID',title:'',width:1,hidden:true},     
		        {field:'VC_CAR_NO',title:'车牌号',width:80,align:'left'},
		        {field:'VC_DRIVER_NAME',title:'司机',width:80,align:'left'},
		        {field:'VC_DRIVER_TEL',title:'司机号码',width:80,align:'left'}
		       
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
              },
			  onLoadSuccess: function (data) {
			 
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
              // fitWidth(data);
              //$('#pp').pagination({
				//layout:['first','prev','next','last'],
				//total:data.total, 
				//pageSize:10,
				//displayMsg:''
		
				//});
            }   
		    
		});   
		
	}
	function initRightTab3(truckId)
	{
		var w=getWidth(400);
		//h = getHeight('righttab_3');
		//var size = getPageSize(h);
		//alert("width:"+w);
		$('#righttab_3').datagrid({     
		    url:'<%=basePath %>shipheadAction/getCurrShipheadByTruckId',
		    queryParams:{
				truckId:truckId
			},
		    cache : false,
		    type : 'post',
		    title:'发运详情', 
		    singleSelect:false,  //如果为true，则只允许选择一行。
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    pageList :[10,15,20],
		    autoRowHeight: true,
		    fitColumns:true,
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:' ',width:80,
		        	formatter:function(value,row,index){
		        		var result = '<input type="checkbox" id="headId'+index+'" onclick="getOrderNoClick(this)"/>';
		        		return result;
		        	}	
		        }, 
		        {field:'vcShipno',title:'发运指令',width:150,align:'center'},     
		        {field:'dtCreate',title:'生成指令时间',width:150,align:'center',
					formatter:function(value,row,index){
						var date=new Date(value);
						return date.toLocaleString();
					}
			    },   
		        {field:'vcSubno',title:'分供方编号',width:150,align:'center'}
		    ]],
		    view:detailview,
		    detailFormatter:function(rowIndex,rowData){
				return '<table id="view'+rowIndex+'"></table>';
			},
			onExpandRow:function(rowIndex,rowData){
				$('#view'+rowIndex).datagrid({
					url:'<%=basePath %>shipheadAction/getOrdersByHeadId',
					queryParams:{
						headId:rowData.id
					},
					cache : false,
					type:'post',
					singleSelect:false,
					fitColumns:true,
					height: 'auto',
					iconCls:'icon-save',
					loadMsg:'正在加载,请稍等...',
					columns:[[
					 {field:'ID',title:'',align:'center',
				        	formatter:function(value,row,index){
			        		var result = '<input type="checkbox" id="id" onclick="getOrderNoClick(this)"/>';
			        		return result;
			        	}	
			        	},
					 {field:'VC_ORDERNO',title:'订单号',width:100,align:'center'},
					 {field:'VC_START_CITY',title:'起运城市',width:150,align:'center'},
					 {field:'VC_DEST_CITY',title:'目的城市',width:150,align:'center'},
					 {field:'VC_RECEIVE_CONTACT',title:'收货联系人',width:100,align:'center'},
					 {field:'VC_RECEIVE_ADDRESS',title:'收货地址',width:200,align:'center'},
					 {field:'Vc_Receive_Tel',title:'收货人电话',width:100,align:'center'},
					 {field:'VC_CAR_NAME',title:'车型',width:80,align:'center'},
					 {field:'N_SHIPEDQTY',title:'发运数量',width:80,align:'center'},
					 {field:'N_TOTAL_CAR',title:'总数',width:50,align:'center'}
					]],
					onResize: function () {
                        $('#righttab_3').datagrid('fixDetailRowHeight',rowIndex);
                    },
                    onLoadSuccess: function () {
                        setTimeout(function () {
                            $('#righttab_3').datagrid('fixDetailRowHeight',rowIndex);
                        }, 0);
                    }
				});
				$('#righttab_3').datagrid('fixDetailRowHeight',rowIndex);
			},
		    onLoadError:function(){
            	alert("加载数据失败！");
    		},
	  		onLoadSuccess: function (data) {
	 
	  			//改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
    			// fitWidth(data);
    			//$('#pp').pagination({
				//layout:['first','prev','next','last'],
				//total:data.total, 
				//pageSize:10,
				//displayMsg:''

				//});
  			}   
		});   
	}
	//取消配载左边选中
	function leftClick2(obj,truckId){
		cancelArr = new Array();//每次选中初始化数组
		//只能有一个选中的
		var carArr=document.getElementsByName('cars');
		for(var i=0;i<carArr.length;i++){
			carArr[i].checked=false;
		}
		obj.checked=true;
		truckDriverId=truckId;
		initRightTab3(truckId);//获取右边列表数据
		
	}
	//全局变量，保存要取消的订单编号或者指令号，不能重复
	var cancelArr = new Array();
	// 取消配载时，获得要取消的指令号和订单编号
	function getOrderNoClick(ele){
		
		var orderno = $(ele).parent().parent().next().children().html();
		if(ele.checked){
			if(cancelArr.indexOf(orderno)<0){
				cancelArr.push(orderno);
			}
		}else{
			
			//取消选择，去掉之前的订单编号
			if(cancelArr.indexOf(orderno) != -1){
				
				var cancelArr2 = new Array();
				for(var i=0 ; i<cancelArr.length;i++){
					
					if(cancelArr[i] != orderno){
						
						cancelArr2.push(cancelArr[i]);
						
					}
				}
				cancelArr = null;
				cancelArr = cancelArr2;
			}
		}
		//alert('cancelArr='+cancelArr);
	}


	//指令取消
	function shipCancel(){
		//alert("truckDriverId:"+!truckDriverId);
		if(!truckDriverId){
			alert("请选择车辆");
			return;
		}
		if(cancelArr.length==0){
			alert("请选择订单");
			return;
		}
		var temp = cancelArr.join(",");
		alert(temp);
		$.post('<%=basePath %>shipheadAction/shipCancel',
				{"no":temp,"truckId":truckDriverId},
				function(data){
					alert(data.message);
					initRightTab3(truckDriverId);//刷新右边列表
				},
				'json'
		);
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
	
	
	function sure()
	{
		
		 var count = $('input[name="headcheck"]:checked').length;
		var headids ="";
		 if (count ==1 ){
		 	
				 $('input[name="headcheck"]:checked').each(function(){
				 	
				 	var headid = $(this).attr("headid");
				 	headids =headid;
				 });
	 				
		 		
		 		alert(" "+headids);
		 		
		 		 $("body").mask("数据处理中，请稍等……");
		 		 
					$.post("<%=basePath%>shipheadAction/saveShipInfoCancel", 
					{ headids:headids },     
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
		     
		     	
		    }
		    else
		    {
		   		 alert("请选择一条数据.");
		    }
		
		
	}
	//查询方法
	function seacher(partype){
		var jsonStr = {};
		//var partype = $("#selpar").val();
		
		var shipno = $("#shipno").val();
		var drivername = $("#drivername").val();
		var driverno = $("#driverno").val();
		
		if(null!=shipno && "" != shipno){
			jsonStr.shipno=shipno;
		}if(null!=drivername && ""!=drivername){
			jsonStr.drivername=drivername;
		}
		if(null!=driverno && ""!=driverno){
			jsonStr.driverno=driverno;
		}
		
		$('#dg').datagrid('load',jsonStr);
		
		
	}
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	   
	    $('#dg').datagrid({width:t });
	}

	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	function initShipCancel(){
		var w=getWidth(400);
		var h = getHeight('dg');
		var size = getPageSize(h);
		var w=getWidth(400);
		//alert("width:"+w);
		$('#dg').datagrid({     
		    url:'<%=basePath %>shipheadAction/getNotShipInfo',
		    cache : false,
		    type : 'post',
		    width:1200,
		    height:h,
		    title:'发运详情', 
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    pageList :[10,15,20],
		    fitColumns:true,
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
		         
		        {field:'VC_SHIPNO',title:'发运指令',width:150,align:'left'},     
		        {field:'OPDATE',title:'指令时间',width:120,align:'left'},   
		        {field:'VC_CAR_NO',title:'车牌号',width:100,align:'left'},
		        {field:'VC_START_CITY',title:'起运地',width:100,align:'left'},
		        {field:'VC_DEST_CITY',title:'目的地',width:100,align:'left'},
		        {field:'SHIPCOUNT',title:'发运数量',width:100,align:'right',
		        	formatter:function(value,row,index){
		        		var result = '<a href="javascript:showheadDate('+row.HEADID+');" >'+row.SHIPCOUNT+'</a>';
		        		
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
            },onClickCell:function(index,field,value)
            {
            	//alert("fie"+field);
            	if(field=='SHIPCOUNT')
            	{
            		return;
            	}
            },
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});   
	}
	//加载数据(重新配载，取消配载)
	function loadData(paramType){
		if(paramType==3){
			//取消配载
			initLeftTab3();
			//initRightTab3();
		}else if(paramType==2){
			//重新配载
			//alert(arguments[0]+','+arguments[1]);
			//$('#'+arguments[0]).datagrid('reload');
			//$('#'+arguments[1]).datagrid('reload');
			initLeftTab2();
			initRightTab2();
			//初始化数据
			orderArr=[];
			showCount();
			$('#carno_load').empty();
			$('#carCount_load').empty();
			$('#carno_add').empty();
		}
	}


</script>
</head>


<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    <dt><a href="javascript:;" ><span>订单配载</span></a><a href="javascript:;" onmouseover="loadData('2')"><span>配载追加</span></a><a href="javascript:;" onmouseover="loadData('3')"><span>取消配载</span></a></dt>
    <!--订单配载-->
    <dd >
    	<div style="margin-bottom:10px">
    		<input name="" type="button" value="确定配载" class="f_btn" 
				onclick="loading();" />
    	</div>
		<table width="1200px;" style="overflow-x:auto;overflow-y:hidden;">
			<tr>
			<td width="150px;"><input type="text" id="vcCarNo"
				name="vcCarNo" style="width: 70px;" class="f_txt f_short"
				placeholder="车牌" /> <input name="" onclick="queryCars();"
				type="button" style="width: 30px;" class="f_btn3" /></td>
			<td width="10px;"></td>
			<td width="1000px;"><input type="text" id="orderNo"
				class="f_txt f_short" placeholder="订单号" /> <input type="text"
				id="staCity" class="f_txt f_short" placeholder="起运城市" /> <input
				type="text" id="destCity" class="f_txt f_short" placeholder="目的地" />
				<!--  <input type="text" id="shipDate" class="f_txt f_short" placeholder="发运日期"/> -->
				<input name="" onclick="queryOrders();" type="button" class="f_btn3" />
				 <!--<input name="" type="button" value="确定配载" class="f_btn" 
				onclick="loading();" />--></td>
		</tr>
		<tr>
			<td width="200px;">
				<table id="lefttab" width="200px;"
					style="height: 350px;overflow-y:auto;" border="0"
					class="table_data">

				</table></td>


			<td width="10px;"></td>
			<td width="1000px;">
				<table id="righttab" width="1000px;" style="height: 350px;"
					border="0" class="table_data">

				</table></td>
		</tr>
		</table>
		<div class="box" style="width:880px;margin-top: 30px;">
		<span class="num" id="carno"> </span> 运载情况： <span id="carCount"
			class="num"></span>
		</div>
		<!--<div class="operate" style="margin-top: 10px;"></div>-->
	</dd>
	<!--配载追加-->
	<dd>
		<div style="margin-bottom:10px">
    		<input name="" type="button" value="确定追加配载" class="f_btn" 
				onclick="appendLoading();" />
    	</div>
		<table width="1200px;" style="overflow-x:auto;overflow-y:hidden;">
			<tr>
			<td width="150px;"><input type="text" id="vcCarNo_2"
				name="vcCarNo" style="width: 70px;" class="f_txt f_short"
				placeholder="车牌" /> <input name="" onclick="queryCars2();"
				type="button" style="width: 30px;" class="f_btn3" /></td>
			<td width="10px;"></td>
			<td width="1000px;"><input type="text" id="orderNo_2"
				class="f_txt f_short" placeholder="订单号" /> <input type="text"
				id="staCity_2" class="f_txt f_short" placeholder="起运城市" /> <input
				type="text" id="destCity_2" class="f_txt f_short" placeholder="目的地" />
				<!--  <input type="text" id="shipDate" class="f_txt f_short" placeholder="发运日期"/> -->
				<input name="" onclick="queryOrders2();" type="button" class="f_btn3" />
				<!--<input name="" type="button" value="确定追加配载" class="f_btn" 
				onclick="loading();" />--></td>
		</tr>
		<tr>
			<td width="200px;">
				<table id="lefttab_2" width="200px;"
					style="height: 350px;overflow-y:auto;" border="0"
					class="table_data">

				</table></td>
			<td width="10px;"></td>
			<td width="1000px;">
				<table id="righttab_2" width="1000px;" style="height: 350px;"
					border="0" class="table_data">

				</table></td>
		</tr>
		</table>
		<div class="box" style="width:880px;margin-top: 30px;">
			<span class="num" id="carno_load"> </span> 已配载情况： <span id="carCount_load"
			class="num"></span>
		</div>
		<div class="box" style="width:880px;margin-top: 30px;">
			<span class="num" id="carno_add"> </span>  追加配载情况： <span id="carCount_add"
			class="num"></span>
		</div>
	</dd>
	<!--取消配载-->
    <dd >
    		<div style="margin-bottom:10px">
    			<input class="f_btn"  type="button" onclick="shipCancel();"  value=" 取消配载 "/>
    		</div>
			<!--<div class="search">
        	<input type="text" id="shipno" class="f_txt f_short" placeholder="指令号"/>
			 <input type="text" id="driverno" class="f_txt f_short" placeholder="车牌号"/>
			 <input name="" onclick="seacher();" type="button" class="f_btn3" /> --> 
			<!--<input class="f_btn"  type="button" onclick="sure();"  value=" 取消配载 "/>-->
			<!--</div>-->
		<table width="1200px;" style="overflow-x:auto;overflow-y:hidden;">
			<tr>
			<td width="150px;" ><input type="text" id="vcCarNo"
				name="vcCarNo" style="width: 70px;" class="f_txt f_short"
				placeholder="车牌" /> <input name="" onclick="queryCars();"
				type="button" style="width: 30px;" class="f_btn3" /></td>
			<td width="10px;"></td>
			<td width="1000px;"></td>
			</tr>
			<tr>
			<td width="200px;">
				<table id="lefttab_3" width="200px;"
					style="height: 335px;overflow-y:auto;" border="0"
					class="table_data">

				</table></td>
			<td width="10px;"></td>
			<td width="1000px;">
				<table id="righttab_3" width="1000px;" style="height: 350px;"
					border="0" class="table_data">

				</table></td>
		</tr>
		</table>
		
			<input type="hidden" id="statu" value="" />
	</dd>
    </dl>
	<!-- <table width="1200px;" style="overflow-x:auto;overflow-y:hidden;">
		<tr>
			<td width="150px;"><input type="text" id="vcCarNo"
				name="vcCarNo" style="width: 70px;" class="f_txt f_short"
				placeholder="车牌" /> <input name="" onclick="queryDate();"
				type="button" style="width: 30px;" class="f_btn3" /></td>
			<td width="10px;"></td>
			<td width="1000px;"><input type="text" id="orderNo"
				class="f_txt f_short" placeholder="订单号" /> <input type="text"
				id="staCity" class="f_txt f_short" placeholder="起运城市" /> <input
				type="text" id="destCity" class="f_txt f_short" placeholder="目的地" />-->
				<!--  <input type="text" id="shipDate" class="f_txt f_short" placeholder="发运日期"/> -->
				<!--  <input name="" onclick="queryDate2();" type="button" class="f_btn3" />
				<input name="" type="button" value="配载" class="f_btn2"
				onclick="loading();" /> <input name="" type="submit" value="指令生成"
				class="f_btn2" onclick="sub();" /></td>
		</tr>

		<tr>
			<td width="200px;">
				<table id="lefttab" width="200px;"
					style="height: 350px;overflow-y:auto;" border="0"
					class="table_data">

				</table></td>


			<td width="10px;"></td>
			<td width="1000px;">
				<table id="righttab" width="1000px;" style="height: 350px;"
					border="0" class="table_data">

				</table></td>
		</tr>
	</table>-->

	<!--  <div class="box" style="width:880px;margin-top: 460px;">
		<span class="num" id="carno"> </span> 运载情况： <span id="carCount"
			class="num"></span>
	</div>
	<div class="operate" style="margin-top: 10px;"></div>-->
</body>
</html>
