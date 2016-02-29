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
<title>个人发布秒杀订单</title>

<%@include file="../include/common.jsp" %>
<%@ include file="../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg');
	var size = getPageSize(h);
	//未开始的订单
	$('#dg2').datagrid({  
		queryParams:{
    		type:'begin'
    	},   
	    url:'<%=basePath %>seckillAction/getMyProduct',
	    cache : false,
	    type : 'post',
	    title:'未开始订单', 
	    width:w,
	    height:400,
	    fitColumns:true,
	    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
	   // rownumbers:true,   //如果为true，则显示一个行号列。
	    iconCls:'icon-save',
	    striped:true,  //striped  是否显示斑马线效果。
	    sortName:'id',
	    sortOrder:'desc',
	    loadMsg:'信息加载中……',
	    columns:[[     
			        {field:'vcKillno',title:'抢单编号',width:80,align:'left'},     
			        {field:'nminPrice',title:'最低价',width:50,align:'right'},
			        {field:'vcStart',title:'起始点',width:100,align:'left'},
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
	                    	var e = '<a href="javascript:;" class="rob" onclick="edit(\''+rec.id+'\');" >编辑</a> ';
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
             			 vcKillno: '没有相关记录'
                	 });
                	 
                	 $('#dg2').datagrid('mergeCells',{
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
	
	//正在进行投标的订单
	$('#dg1').datagrid({ 
		queryParams:{
    		type:'between'
    	},    
	    url:'<%=basePath %>seckillAction/getMyProduct',
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
			        {field:'vcKillno',title:'抢单编号',width:80},     
			        {field:'nminPrice',title:'最低价',width:50},
			        {field:'vcStart',title:'起始点',width:100},
			        {field:'vcEnd',title:'目的地',width:100},
			        {field:'dtArriveTime',title:'抵达时间',width:100,
			        	formatter:function(value,rec,index){
			        		var unixTimestamp = new Date(value);  
			        		return unixTimestamp.toLocaleDateString()
			        	}},
			        {field:'dtKillStart',title:'开始时间',width:130,
				        	formatter:function(value,rec,index){
				        		var unixTimestamp = new Date(value);  
		                        return unixTimestamp.toLocaleString();
				        	}},
			        {field:'dtKillEnd',title:'结束时间',width:130,
					        	formatter:function(value,rec,index){
					        		var unixTimestamp = new Date(value);  
			                        return unixTimestamp.toLocaleString();
					        	}},
			        {field:'opt',title:'操作',width:50,align:'center',  
	                    formatter:function(value,rec,index){ 
	                    		var e = '<a href="javascript:;" class="rob" onclick="openLook(\''+rec.id+'\');" >查看</a> ';
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
             			 vcKillno: '没有相关记录'
                	 });
                	 
                	 $('#dg1').datagrid('mergeCells',{
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
	//初始化结束的订单
		var isArrive=$('#if_arrive').val();//默认显示未抵运的数据
		//alert('isArrive:'+isArrive);
		$('#dg').datagrid({     
			queryParams:{
				type:'end',
				isArrive:isArrive
			},
		    url:'<%=basePath %>seckillAction/getMyProduct',
		    cache : false,
		    type : 'post',
		    title:'结束的订单', 
		    width:w,
		    height:400,
		    autoRowHeight:true,
		    nowrap:false,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    //rownumbers:true,   //如果为true，则显示一个行号列。
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'ID',
		    sortOrder:'desc',
		    loadMsg:'请稍等……',
		    columns:[[  
						{field:'VC_KILLNO',title:'抢单编号',width:80},     
				        {field:'VC_START',title:'起始点',width:100},
				        {field:'VC_DETAIL_ADDRESS',title:'目的地',width:100},
				        {field:'DT_KILL_START',title:'开始时间',width:100,
					        	formatter:function(value,rec,index){
					        		var unixTimestamp = new Date(value);  
			                        return unixTimestamp.toLocaleString();
					        	}},
				        {field:'DT_KILL_END',title:'结束时间',width:100,
						        	formatter:function(value,rec,index){
						        		var unixTimestamp = new Date(value);  
				                        return unixTimestamp.toLocaleString();
						        	}},
						{field:'DT_ARRIVE_TIME',title:'抵达时间',width:100,
							       formatter:function(value,rec,index){
							        	var unixTimestamp = new Date(value);  
							        	return unixTimestamp.toLocaleDateString();
						}},
						{field:'N_MIN_PRICE',title:'最低价',width:100},
						{field:'N_MAX_PRICE',title:'最高价',width:100},
						{field:'N_PAYTYPE',title:'预付方式',width:100},
						{field:'N_PAYCYCLE',title:'支付周期',width:100},
						{field:'VC_NOTE',title:'特殊要求',width:100},
				        {field:'',title:'操作',width:100,align:'center',  
		                    formatter:function(value,rec,index){ 
						        var e;
		                    	if(rec.nbid=='0'){
		                    		e = '<a href="javascript:;" class="rob" onclick="openDetail(\''+rec.id+'\');" >查看</a> ';
		                    	}else{
		                    		e = '<a href="javascript:;" class="rob" onclick="openBidPag(\''+rec.id+'\');" >选标</a> ';
		              			}
		              			return e;
		                    }  
		                  }  
				       
			]],
			view:detailview,
			detailFormatter:function(index_dg,row_dg){
				return '<table id="view_'+index_dg+'"></table>';
			},
			onExpandRow:function(index_dg,row_dg){
				//alert('productId:'+row.ID);
				$('#view_'+index_dg).datagrid({
					url:'<%=basePath%>seckillAction/getOrdersByCarId?productCarStyleId='+row_dg.ID,
				    cache : false,
				    type : 'post',
				    autoRowHeight:true,
				    height: 'auto',
				    //title:'车队订单',
				    fitColumns:true,
				    //rownumbers:true,   //如果为true，则显示一个行号列。
				    loadMsg: '请稍等...',
				    columns:[[
				         {field:'VC_ORDERNO',title:'订单号',width:100,align:'center'},
				         {field:'VC_RECEIVE_ADDRESS',title:'目的地',width:100,align:'center'},
				         {field:'VC_CAR_NAME',title:'车型',width:100,align:'center'},
				         {field:'N_TOTAL_CAR',title:'数量',width:100,align:'center'}
				    ]],
					onResize: function () {
                        $('#dg').datagrid('fixDetailRowHeight', index_dg);
                    },
                    onLoadSuccess: function () {
                        setTimeout(function () {
                            $('#dg').datagrid('fixDetailRowHeight', index_dg);
                        }, 0);
                    },
					view:detailview,
					detailFormatter:function(index_view, row_view){
						return '<table id="subview_'+index_dg+index_view+'"></table>';
					},
					onCollapseRow: function (index_view, row_view) {
                        setTimeout(function () {
                            $('#view_'+index_dg).datagrid("fixRowHeight",index_view);
                            $('#dg').datagrid('fixRowHeight',index_dg);
                            $('#view_'+index_dg).datagrid("fixDetailRowHeight", index_view);
                            $('#dg').datagrid('fixDetailRowHeight',index_dg);
                        }, 0);
                    },
					onExpandRow:function(index_view, row_view){
						$('#subview_'+index_dg+index_view).datagrid({
							url:'<%=basePath%>seckillAction/getStatusByOid?orderId='+row_view.ID,
						    cache : false,
						    type : 'post',
						    //title:'订单状态',
						    height: 'auto',
							fitColumns:true,
							sortName:'DT_STATUS',
							rownumbers:true,   //如果为true，则显示一个行号列。
						    loadMsg: '请稍等...',
						    columns:[[
						              {field:'DT_STATUS',title:'状态生成时间',width:100,align:'center',
							              formatter:function(value,rec,index){
						            	  		var unixTimestamp = new Date(value);  
					                        	return unixTimestamp.toLocaleString();
							              }},
						              {field:'VC_STATUSNOTE',title:'状态',width:100,align:'center'},
						              {field:'num',title:'数量',width:100,align:'center'},
							]],
							onResize: function () {
                                $("#view_"+index_dg).datagrid("fixDetailRowHeight", index_view);
                                $('#dg').datagrid('fixDetailRowHeight', index_dg);
                            },
                            onLoadSuccess: function () {
                                setTimeout(function () {
                                    $("#view_"+index_dg).datagrid("fixRowHeight", index_view);
                                    $('#dg').datagrid('fixRowHeight', index_dg);
                                    $("#view_"+index_dg).datagrid("fixDetailRowHeight", index_view);
                                    $('#dg').datagrid('fixDetailRowHeight', index_dg);
                                }, 0)
                            }
						});
						$("#view_"+index_dg).datagrid("fixDetailRowHeight", index_view);
                        $('#dg').datagrid('fixDetailRowHeight', index_dg);
					}
				});
				$('#dg').datagrid('fixDetailRowHeight', index_dg);
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
	
	//选择中标的分供方
	function openBidPag(pid){
		var url = "<%=basePath%>seckillAction/openBidPage?pid="+pid;
		$.webox({
			height:400,
			width:600,
			bgvisibel:true,
			title:'选则中标者',
			iframe:url
		});	
	}
	
	//查看
	function openDetail(pid){
		var url = "<%=basePath%>seckillAction/openSeckillBid?productId="+pid;
		$.webox({
			height:500,
			width:680,
			bgvisibel:true,
			title:'查看详情',
			iframe:url
		});	
	}
	//编辑
	function edit(pid){
		var url = "<%=basePath%>seckillAction/openSeckillOrder?productId="+pid;
		$.webox({
			height:500,
			width:720,
			bgvisibel:true,
			title:'编辑',
			iframe:url
		});	
	}
	
	//查询方法
	function seacher(id){
		alert("id:"+id);
		var jsonStr = {};
		var vcKillno = $("#vcKillno"+id).val();
		if(null!=vcKillno && ""!=vcKillno){
			jsonStr.vcKillno = vcKillno;
		}
		var vcStart = $("#vcStart"+id).val();
		if(null!=vcStart && ""!=vcStart){
			jsonStr.vcStart = vcStart;
		}
		var vcEnd = $("#vcEnd"+id).val();
		if(null!=vcEnd && ""!=vcEnd){
			jsonStr.vcEnd = vcEnd;
		}
		var idstr = "#dg";
		if(id>0){
			idstr = idstr +id;
		}
		if(id==0){
			jsonStr.type = 'end';
			alert("isArrive:"+$('#if_arrive').val());
			jsonStr.isArrive=$('#if_arrive').val();
			
		}else if(id==1){
			jsonStr.type = 'between';
		}else if(id==2){
			jsonStr.type = 'begin';
		} 
		$(idstr).datagrid('load',jsonStr);
	}
	
	//关闭弹出窗
	function weboxColse(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	
	
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	    $('#dg').datagrid({width:t });
	    $('#dg1').datagrid({width:t });
	    $('#dg2').datagrid({width:t });
	}
	
	function reloadDatagrid(id){
		var t =getWidth(400);
		$('#'+id).datagrid({width:t });
	}
	
	//打开查看投标情况
	function openLook(id){
		var url = "<%=basePath%>seckillAction/lookSeckill?productId="+id;
		$.webox({
			height:500,
			width:680,
			bgvisibel:true,
			title:'查看',
			iframe:url
		});	
	}
	//未开始订单可删除
	function del(id){
		 if(confirm("确定要删除该秒杀订单吗？")){
			$("body").mask("数据处理中，请稍等……");
			var url ="<%=basePath%>seckillAction/delByProductId?productId="+id;
			$.post(url,function(data){
				showResponse(data);
			});
		 }
	}
	//删除成功后刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		$("body").unmask();
		  if(json.isSuccess){
			$('#dg2').datagrid('reload');  
		  }
		}
</script>
</head>

<body class="main" onresize="resizeGrid(400);" >
	<dl class="tabs">
    	<dt><a href="javascript:;" ><span>未开始订单</span></a><a href="javascript:;" onmouseover="reloadDatagrid('dg1'); "><span>进行时订单</span></a><a href="javascript:;" onmouseover="reloadDatagrid('dg'); "><span>结束订单</span></a></dt>
    	<dd >
			<div class="search">
			秒杀编号：<input name="vcKillno2" id="vcKillno2" class="f_txt" style="width: 120px;" /> 
			起始地：<input  id="vcStart2" name="vcStart2" class="f_txt" />
			目的地：<input  id="vcEnd2" name="vcEnd2" class="f_txt" />
			 <input name="" onclick="seacher(2);" type="button" class="f_btn3" />
			</div>
			<table  id="dg2"   ></table>
        </dd>
        
        <dd >
			<div class="search">
			秒杀编号：<input name="vcKillno1" id="vcKillno1" class="f_txt"  style="width: 120px;" /> 
			起始地：<input  id="vcStart1" name="vcStart1" class="f_txt" />
			目的地：<input  id="vcEnd1" name="vcEnd1" class="f_txt" />
			 <input name="" onclick="seacher(1);" type="button" class="f_btn3" />
			</div>
			<table  id="dg1"   ></table>
        </dd>
        
        <dd >
			<div class="search">
			秒杀编号：<input name="vcKillno0" id="vcKillno0" class="f_txt" style="width: 120px;"/> 
			起始地：<input  id="vcStart0" name="vcStart0" class="f_txt" />
			目的地：<input  id="vcEnd0" name="vcEnd0" class="f_txt" />
			<select name="" id="if_arrive">
				<option value="N" selected="selected">未完成</option>
				<option value="Y">完成</option>
			</select>
			<input name="" onclick="seacher(0);" type="button" class="f_btn3" />
			</div>
			<table  id="dg"   ></table>
		</dd>
    </dl>
</body>
</html>