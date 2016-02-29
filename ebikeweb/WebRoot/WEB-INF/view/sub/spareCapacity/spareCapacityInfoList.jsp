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
<title>空闲运力维护</title>


<%@include file="../../include/common.jsp" %>
<%@ include file="../../include/datagrid.jsp"  %>
<script type="text/javascript">
$(document).ready(function(){
	var w=getWidth(400);
	var h = getHeight('dg1');
	var size = getPageSize(h);
	  
		$('#dg1').datagrid({     
		    url:'<%=basePath%>spareCapacityAction/getAllCapacityByNotSelf',
		    cache : false,
		    type : 'post',
		    title:'其他运力信息', 
		    width:w,
		    height:h,
		    pageSize:size,
		    fitColumns:true,
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。 
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'ID',title:'',checkbox:true,width:30},
		        {field:'VC_USER_NAME',title:'发布人',width:100,align:'left'},      
		        {field:'DT_ADD',title:'发布时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        },      
		        {field:'DT_BEGIN',title:'开始时间',width:100,align:'left',
			        formatter:function(value,rec,index)
			        {
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
			        }
		        },   
		        {field:'DT_END',title:'结束时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        }, 
		        {field:'N_SPACE',title:'有效仓位',width:100,align:'left'},  
		        {field:'VC_PRICE',title:'仓位价格',width:100,align:'left'},
		        {field:'VC_START_ADDRESS',title:'起始地',width:100,align:'left'},
		        {field:'VC_END_ADDRESS',title:'目的地',width:100,align:'left'},
		        {
		        	field:'VC_LINK',title:'联系电话',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		 if(rec.ICOUNT == "0")
		        		 {
		        		 	return "<a href='javascript:;' onClick='showLink("+rec.ID+");'>点击获取联系方式</a>";	
		        		 }else
		        		 {
		        		 	return value;
		        		 }
		        		 
		        	} 
		        }
		   
		        
		    ]],
		      onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               //fitWidth(data);
               
               
            } ,   
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});    
	
	$('#dg').datagrid({     
		    url:'<%=basePath%>spareCapacityAction/getAllCapacityBySelf',
		    cache : false,
		    type : 'post',
		    title:'个人运力信息',
		    fitColumns:true, 
		    pagination:true,  //pagination  如果为true，则在DataGrid控件底部显示分页工具栏。 
		    rownumbers:true,   //如果为true，则显示一个行号列。
		    striped:true,  //striped  是否显示斑马线效果。
		    singleSelect:true,  //如果为true，则只允许选择一行。
		    loadMsg:'正在加载,请稍等...',
		    columns:[[     
		        {field:'id',title:'',checkbox:true,width:30},
		        {field:'vcUserName',title:'发布人',width:100,align:'left'},      
		        {field:'dtAdd',title:'发布时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        },      
		        {field:'dtBegin',title:'开始时间',width:100,align:'left',
			        formatter:function(value,rec,index)
			        {
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
			        }
		        },   
		        {field:'dtEnd',title:'结束时间',width:100,align:'left',
		        	formatter:function(value,rec,index)
		        	{
		        		var unixTimestamp = new Date(value);   
                        return unixTimestamp.toLocaleDateString();
		        	}
		        }, 
		        {field:'nspace',title:'有效仓位',width:100,align:'center'},  
		        {field:'vcPrice',title:'仓位价格',width:100,align:'left'},
		        {field:'vcStartAddress',title:'起始地',width:100,align:'left'},
		        {field:'vcEndAddress',title:'目的地',width:100,align:'left'},
		        {field:'vcLink',title:'联系电话',width:100,align:'left'}
		        
		    ]],
		      onLoadSuccess: function (data) {
			  //改变table  head和数据行的宽度    使其宽度一致  谁宽取谁的
               fitWidth(data);
            } ,   
		     onLoadError:function(){
                      alert("加载数据失败！");
                  }
                  
		    
		});  
		
	});
	
	function showLink(spareID)
	{ 
		if(confirm("获取联系方式需要扣除积分，确定获取吗？"))
		{
			$.post("<%=basePath%>spareCapacityAction/getLinkByIntegalCut", 
				{ spareID:spareID },     
				   function (json, textStatus)
				   {      
						alert(json.message); 
						  if(json.isSuccess){
							$('#dg1').datagrid('reload');  
						     
						  }
				   }
			  ,"json");
		}
		
	}
	function save()
	{
		var url = "<%=basePath%>spareCapacityAction/saveBefore";
		$.webox({
				height:580,
				width:800,
				bgvisibel:true,
				title:' ',
				iframe:url
		});
		
		
	}
	
	function del()
	{
		var selected = $('#dg').datagrid('getSelected');
		
	    if (selected){
	       var index = $('#dg').datagrid('getRowIndex', selected);
	       	 
			$.post("<%=basePath%>spareCapacityAction/del", 
				{ spareID:selected.id },     
				   function (data, textStatus)
				   {     
						
						if(data.isSuccess == true)
						{
							alert(data.message);
							$('#dg').datagrid('deleteRow', index);
							$('#dg').datagrid('reload'); 
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

	function getupdate()
	{
		var selected = $('#dg').datagrid('getSelected');
		 if (selected){
		       var index = $('#dg').datagrid('getRowIndex', selected);
		       
			   	var url = "<%=basePath%>spareCapacityAction/saveBefore?spareID="+selected.id;
					$.webox({
							height:450,
							width:780,
							bgvisibel:true,
							title:' ',
							iframe:url
					});
			        
		    }else
		    {
		    	alert("请选择一条数据.");
		    }
		
		
	}
	
	//查询方法
	function seacher(id){
		var jsonStr = {};
		var vcUserNo = $("#vcUserNo"+id).val();
		var vcStart = $("#vcStart"+id).val(); 
		var vcEnd = $("#vcEnd"+id).val(); 
		
		
		if(null!=vcStart && ""!= vcStart){
			jsonStr.vcStart=vcStart;
		} if(null!=vcEnd && ""!= vcEnd){
			jsonStr.vcEnd=vcEnd;
		} 
		var idstr = "#dg";
		if(id>0){
			if(null!=vcUserNo && ""!= vcUserNo){
				jsonStr.vcUserNo=vcUserNo;
			}
			idstr = idstr +id;
		} 
		 
		$(idstr).datagrid('load',jsonStr);
	}
	 
	function resizeGrid(minWidth){ 
	     var t =getWidth(minWidth);
	    var h = getHeight('dg');
		var size = getPageSize(h);
	    $('#dg').datagrid({width:t,height:h,pageSize:size }); 
	    $('#dg1').datagrid({width:t,height:h,pageSize:size }); 
	}
	
	function reloadDatagrid(id){
	
		 var t =getWidth(400);
	    var h = getHeight(id);
		var size = getPageSize(h);
		$("#"+id).datagrid({width:t,height:h,pageSize:size });
	}
	
	function weboxColse(){
	
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
</script>
</head>

<body class="main easyui-layout" >
	<dl class="tabs">
    	<dt>
    		<a href="javascript:;" ><span>查看其他运力</span></a>
    		<a href="javascript:;" onmouseover="reloadDatagrid('dg'); "><span>维护个人运力</span></a> 
    	</dt>
    	 
        
        <dd >
			<div class="search">
			发布人：<input id="vcUserNo1" name="vcUserNo1" class="f_txt"  style="width: 120px;"/>
			起始地：<input id="vcStart1" name="vcStart1" class="f_txt" style="width: 120px;"/>
			目的地：<input id="vcEnd1" name="vcEnd1" class="f_txt" style="width: 120px;"/>
			 <input name="" onclick="seacher(1);" type="button" class="f_btn3" />
			</div>
			<table  id="dg1"   ></table>
        </dd>
        
        <dd >
        	<div class="batch">
        	<input name="" id="" onclick="save();" type="button" class="f_btn2" value="新增" /> 
        	<input name="" id="" onclick="getupdate();" type="button" class="f_btn2" value="修改" /> 
        	<input name="" onclick="del();" type="button" class="f_btn2" value="删除" />
        	</div>
        	
			<div class="search">
			
			 起始地：<input  id="vcStart0" name="vcStart0" class="f_txt" />
			目的地：<input  id="vcEnd0" name="vcEnd0" class="f_txt" />
			 <input name="" onclick="seacher(0);" type="button" class="f_btn3" />
			</div>
			<table  id="dg"   ></table>
		</dd>
    </dl>
</body>
</html>