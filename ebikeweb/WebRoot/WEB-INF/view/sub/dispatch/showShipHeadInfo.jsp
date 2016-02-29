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
<title>显示发运详情</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-easyui-1.3.6/jqueryeasyui-detail.js"></script>

<script type="text/javascript">

	$(document).ready(function(){
		var headID = "${headID}";
			$('#dg').datagrid({    
			    url:'<%=basePath %>shipheadAction/getShipInfoByHeadID?headID='+headID,
			    cache : false,
			    type : 'post',
			    width:850,
			    fitColumns:true,
			    height :600,
			    title:'发运详情', 
			    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
			    pageSize : 5,
			    pageList :[5,10,15],
			    rownumbers:true,   //如果为true，则显示一个行号列。		    
			    striped:true,  //striped  是否显示斑马线效果。
			    loadMsg:'正在加载,请稍等...',
			    columns:[[     
			        {field:'ID',title:'ID',width:1,hidden:true},
			        {field:'ORDERNO',title:'订单号',width:150,align:'left'},     
			        {field:'SHIPQTY',title:'发运数量',width:100,align:'right'},   
			        {field:'N_ARRIVEQTY',title:'抵达数量',width:100,align:'right'},
			        {field:'NQTY',title:'结算数量',width:100,hidden:true,align:'right',
			        	formatter:function(value,row,index){
			        		if(row.N_ARRIVEQTY>0){
			        			var result = '<input type="text"   id="NQTY'+row.ID+'"   style="width:30px;" value="'+row.N_ARRIVEQTY+'"   />';
				        		
				        		return result;
			        		}else{
			        			return "未抵达，不能回单"
			        		}
			        		
			        	}		
			        }    
			        
			    ]],
			    
			    view: detailview,
				detailFormatter:function(index,row){
					return '<div class="ddv" style="padding:5px 0"><table class="ddv"></table></div>';
				},
				onExpandRow: function(index,row){
					var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
					ddv.datagrid({
						url:'<%=basePath %>shipheadAction/getPicDetail?damaId='+row.ID,
						fitColumns:true,
						rownumbers:true,
						loadMsg:'正在加载...',
						height:500,
						columns:[[
							
							{field:'vcPicPath',title:'图片',width:150,align : 'center',formatter:function(value,row,index){	
								if(value == "" || value == null){
									return "无图片";
								}else{
									return  '<div id="'+index+'div"><img  src="'+value+'" alt="图片" width="800" height="800" style="display:block;left:20px;top:20px;" ></div>';
								}
							}
							}
						]],
						onResize:function(){
							$('#dg').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#dg').datagrid('fixDetailRowHeight',index);
							},0);
						}
					});
					$('#dg').datagrid('fixDetailRowHeight',index);
				},
			    
			     onLoadError:function(){
	                      alert("加载数据失败！");
	                  }
	                  
			    
			});
			
			var partype = "${partype}";
			
			if(partype!="" && partype =="parReturn")
			{
				$('#dg').datagrid('showColumn',"NQTY");	
				$("#but").css("display","block");
			}else
			{
				$('#dg').datagrid('hideColumn',"NQTY");	
			}
			
		});
	
	function resizeGrid(minWidth){
	    var t =getWidth(minWidth);
	   
	    $('#dg').datagrid({width:t });
	}
	
	function sub()
	{
		var rows= $('#dg').datagrid('getRows');
		var lineids ="";
		for(var i=0;i<rows.length;i++)
		{
			var lineID = rows[i].ID;
			var shipcount = rows[i].SHIPQTY;
			var nqty = $("#NQTY"+lineID);
			var countNum = parseInt(nqty.val());
			if (isNaN(nqty.val())  || $.trim(nqty.val())=="")
			{
				alert("请输入数字类型.");
				nqty.val("0"); 
				nqty.focus();
				return;
			}
			if(countNum > shipcount)
			{
				alert("结算数量大于抵达的数量.");
				nqty.val(shipcount) ;
				nqty.select();
				return;
			}
			lineids += lineID+","+countNum+";";
		}
		
		//alert("lineids "+lineids);
		 $("body").mask("数据处理中，请稍等……");
 		 
			$.post("<%=basePath%>shipheadAction/saveDespatchInfo", 
			{ partype:"parReturn",shipnos:lineids },     
			   function (data, textStatus)
			   {      
					$("body").unmask();
						
					if(data.isSuccess == true)
					{
						alert(data.message);
						window.parent.$('#dg').datagrid('reload');  
					    window.parent.weboxColse();  
					}else
					{
						alert(data.message);
					}
			   }
		  ,"json");
	}
	
</script>
  </head>
  
  <body  class="main" onresize="resizeGrid(400);" >
    	<dl class="tabs">
    	
        <dd >
       
		<table  id="dg"  style="overflow: scroll" >
	
		</table>
			 <input name="but" id="but" style="display: none;" type="button" value="保存信息" class="f_btn" onclick="sub();" />    
		</dd>
    </dl>
  </body>
</html>
