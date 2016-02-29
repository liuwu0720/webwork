<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<%@include file="../include/common.jsp" %>
<script type="text/javascript">
var row_cur_index = 1;// 插入行的当前索引
var temid = "";
$(document).ready(function(){
	  
	temid = $("#id").val();
    if( ""!=temid){
    	$("#vcKillno").removeAttr("disabled");
    	$("#vcKillno").attr("readonly","readonly");
    	$("#subBut").val("保存");
    }
	var index = $("#size").val();
	if(null!=index && ""!=index){
		row_cur_index =  parseInt(index)+1;
	}
	$("#orderForm").validate({
		 rules: {
			 vcStart:"required",
			 vcEnd: "required",
			 NMinPrice:{
				 required: true,
				 number:true
			 },
			 NKilometre:{
				 required: true,
				 number:true
			 },
			 dtKillStart:"required",
			 dtKillEnd:{
				 required: true,
				 compareDate: "#dtKillStart"
			 }
			 
		 },
		 messages:{
			 vcStart:"出发地必填",
			 vcEnd: "目的地必填",
			 NMinPrice:{
				 required: "起步价必填",
				 number:"请输入数字"
			 },
			 NKilometre:{
				 required: "公里数必填",
				 number:"请输入数字"
			 },
			 dtKillStart:"抢单开始时间必填",
			 dtKillEnd:{
				 required:"抢单结束时间必填",
				 compareDate: "结束日期必须大于开始日期!"
			 }
		 },
		 submitHandler:function(form){
			 $("body").mask("数据处理中，请稍等……");
			 var aurl = "<%=basePath%>seckillAction/saveSeckKillOrder";
		      $(form).ajaxSubmit({
		        type:"post",
		        url:aurl,
		        success: showResponse,
		        error:function(data){  
                    alert( "error"+data);  
                    $("body").unmask();
                }  
		      });
		    },
		    showErrors: function(map, list) {
		    	topTip(map, list);
			}
		
	});
	
	
});

//新增行
function addTr(){
	var id = "tr_"+row_cur_index;
	var trHtml = "<tr id=\""+id+"\"><th>车型：</th><td><input class=\"f_txt\"  style=\"width:150px;\" type=\"text\" id=\"vcCarStyle_"+row_cur_index+"\" name=\"vcCarStyle_"+row_cur_index+"\" placeholder=\"输入车型\"/></td>"
    			 +"<th>数量：</th><td><input class=\"f_txt\" type=\"text\" style=\"width:150px;\" id=\"NCarCount_"+row_cur_index+"\" name=\"NCarCount_"+row_cur_index+"\" placeholder=\"输入数量\"/></td>"
    			+"<th>单价：</th><td><input class=\"f_txt\" type=\"text\"  style=\"width:150px;\"id=\"NPrice_"+row_cur_index+"\" name=\"NPrice_"+row_cur_index+"\" placeholder=\"输入单价\"/>"
    			+"&nbsp;<img src=\"<%=basePath %>static/image/icons/cancel1.png\" alt=\"删除\" onclick=\"delTr('tr_"+row_cur_index+"')\" /></td></tr>";
	$("#size").val(row_cur_index);
    			row_cur_index = row_cur_index+1;
	$("#tbody").append(trHtml);
	$('input, textarea').placeholder();	
	
}
//删除行
function delTr(id){
	$("#"+id).remove();
}



function showResponse(responseText) {
	var json = eval("("+responseText+")");
	alert(json.message);
	$("body").unmask();
	  if(json.isSuccess){
		  if( ""!=temid){
			  //编辑
			  window.parent.$('#dg2').datagrid('reload');
			  window.parent.weboxColse();  
		  }else{
			  //新增
			  window.parent.webcolose();  
		  }
	    
	  }
	}
	
</script>

</head>

<body class="main"  >
<div style="overflow: scroll;height:520px;">
	<dl class="tabs">
        <dd class="form">
        <form id="orderForm" method="post" >
        <input type="hidden" id="size" name="size" value="${index }" />
        <input type="hidden" id="id" name="id" value="${product.id }" />
        
        <input type="hidden" id="NEnable" name="NEnable" value="${product.NEnable }" />
        <input type="hidden" id="dtRelease" name="dtRelease" value="${product.dtRelease }" />
        <input type="hidden" id="id" name="NBid" value="${product.NBid }" />
<table width="100%" border="0" class="table_form">
  <tr>
    <th width="120">抢单编号：</th>
    <td width="160"><input class="f_txt" type="text"  placeholder="系统自动生成" id="vcKillno" name="vcKillno"  disabled="disabled"  value="${product.vcKillno}" /></td>
    <th width="160">最高价：</th>
    <td width="140"><input class="f_txt" type="text" id="NMinPrice" name="NMinPrice" placeholder="可由车型单价汇总"  value="${product.NMinPrice}"/></td>
  </tr>
  <tr>
    <th>起始点：</th>
    <td><input class="f_txt" id="vcStart" name="vcStart" type="text" placeholder="输入详细的起始地"  value="${product.vcStart}"/></td>
    <th>目的地：</th>
    <td><input class="f_txt" id="vcEnd" name="vcEnd" type="text" placeholder="输入详细的目的地" value="${product.vcEnd}"/></td>
  </tr>
  <tr>
    <th>公里数：</th>
    <td><input class="f_txt" id="NKilometre" name="NKilometre" type="text" placeholder="输入公里数" value="${product.NKilometre}" /></td>
    <th>抵达时间：</th>
    <td>
    
		<input class="Wdate f_txt"  id="dtArriveTime" name="dtArriveTime"  value="<fmt:formatDate value="${product.dtArriveTime }" pattern="yyyy-MM-dd" />" type="text"   onFocus="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
	</td>
  </tr>
  <tr>
    <th>开始时间：</th>
    <td>
    	<input class="Wdate f_txt"  id="dtKillStart" name="dtKillStart" value="<fmt:formatDate value="${product.dtKillStart }" pattern="yyyy-MM-dd HH:mm:ss"  />" type="text"   style="width: 200px" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
    </td>
    <th>结束时间：</th>
    <td>
    	<input class="Wdate f_txt" id="dtKillEnd" name="dtKillEnd" value="<fmt:formatDate value="${product.dtKillEnd }" pattern="yyyy-MM-dd HH:mm:ss" />" type="text"   style="width: 200px" onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
   </td>
  </tr>
  <tr>
    <th valign="top">特别要求：</th>
    <td colspan="3">
    	<textarea id="vcRequire" name="vcRequire" style="width:98%;height:150px;" >${product.vcRequire }</textarea>
    </td>
    
  </tr>
  </table>
  		<div class="block">
        	<div class="block_th">订单详情</div>
            <div class="block_con">
                <table width="100%" border="0" class="table_form">
                <tbody id="tbody">
                <c:choose>
                <c:when test="${!empty list}">
                	<c:forEach items="${list }" var="pcs" varStatus="status">
                		<tr id="tr_${status.index}">
		                    <th>车型：</th>
		                    <td><input class="f_txt" value="${pcs.vcCarStyle }" type="text" id="vcCarStyle_${status.index}" style="width:150px;"  name="vcCarStyle_${status.index}" placeholder="输入车型"/></td>
		                    <th>数量：</th>
		                    <td><input class="f_txt" value="${pcs.NCarCount }"  type="text" id="NCarCount_${status.index}" style="width:150px;"  name="NCarCount_${status.index}" placeholder="输入数量"/></td>
		                    <th>单价：</th>
		                    <td><input class="f_txt" value="${pcs.NPrice }"  type="text" id="NPrice_${status.index}" style="width:150px;" name="NPrice_${status.index}" placeholder="该单价别人看不到，计算出总价来"/>&nbsp;<img src="<%=basePath %>static/image/icons/edit_add1.png" alt="添加车型" onclick="addTr();" /></td>
		                  </tr>
                	</c:forEach>
                	</c:when>
                <c:otherwise>
                	<tr id="tr_0">
                    <th>车型：</th>
                    <td><input class="f_txt" type="text" id="vcCarStyle_0" name="vcCarStyle_0" style="width:150px;" placeholder="输入车型"/></td>
                    <th>数量：</th>
                    <td><input class="f_txt" type="text" id="NCarCount_0" name="NCarCount_0" style="width:150px;" placeholder="输入数量"/></td>
                    <th>单价：</th>
                    <td><input class="f_txt" type="text" id="NPrice_0" style="width:150px;" name="NPrice_0" placeholder="输入单价"/>&nbsp;<img src="<%=basePath %>static/image/icons/edit_add1.png" alt="添加车型" onclick="addTr();" /></td>
                  </tr>
                </c:otherwise>
                
                </c:choose>
                  
                  </tbody>
                </table> 
                           	
            </div>
        </div>
		<div class="operate"><input id="subBut"  type="submit" value="下单" class="f_btn" /></div>
		</form>
      </dd>
    </dl>
    </div>
</body>
</html>