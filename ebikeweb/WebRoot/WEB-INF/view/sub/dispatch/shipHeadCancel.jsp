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
<title>获取未发运的指令</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>

<script type="text/javascript">

var shipno="";

$(document).ready(function(){
	
	var w=getWidth(400);
	
		$('#dg').datagrid({     
		    url:'<%=basePath %>shipheadAction/getNotShipInfo',
		    cache : false,
		    type : 'post',
		    width:w,
		    title:'发运详情', 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。
		    pageSize : 10,
		    pageList :[10,15,20],
		 
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
		
	});
	
	function returnSub(headid)
	{
		var url = '<%=basePath%>shipheadAction/IntogetShipInfoByHeadID?headID='+headid+'&partype=parReturn';
					$.webox({
							height:350,
							width:500,
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
</script>
</head>

<body class="main" onresize="resizeGrid(400);">
	<dl class="tabs">
    	<dt><a href="#"><span>指令取消</span></a></dt>
        <dd >
      
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
			 <input name="" onclick="seacher();" type="button" class="f_btn3" />  
			<input class="f_btn"  type="button" onclick="sure();"  value=" 指令取消 "/>
			</div>
		<table  id="dg"   >
	
		</table>
			
		</dd>
    </dl>
    <input type="hidden" id="statu" value="" />
</body>
</html>