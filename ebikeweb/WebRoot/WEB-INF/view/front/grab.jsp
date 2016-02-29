<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
	function webcolose(){
		$('.webox').css({display:'none'});
		$('.background').css({display:'none'});
	}
	
	var style = "0";//隐藏
	function detailTable(){   //订单明细
		
		if("1"==style){
			$("#tab_detail").hide();
			style = "0";
		}else{
			$("#tab_detail").show();
			style = "1";
		}
	}
	//统计价格
	function totalPrice(){
		var total =0 ;
		var flag = "0";
		$("input[name='nprice_']").each(function(){
			var temp = $(this).val();
			if(null==temp || ""==temp){
				
				return false;
			}
			if(isNaN(temp)){
				alert("所有单价请输入合法数字");
				return false;
				flag = "1";
			}else{
				total = total + parseFloat(temp);
			}
		});
		
		if(flag == "0"){
			$("#totalprice").val(total);
		}else{
			$("#totalprice").val("");
		}
		$("#totalprice").focus();
		$("#totalprice").focusout();
		
		
	}
	//我要抢单 触发事件，提交数据，以及提交前的验证
	function getSeckill(){
		var total =	$("#totalprice").val();
		if(null==total ||""==total){
			totalPrice();
		}
		total =	$("#totalprice").val();
		if(total == 0){
			alert("请输入大于0的数");
			return false;
		}
		if(null==total ||""==total){
			alert("您没有输入抢单价，请输入！");
			return false;
		}else{
			//验证，必须必起步价低
			var minPrice = $("#minPrice").val();
			minPrice = parseFloat(minPrice);
			total =  parseFloat(total);
			//console.log("minPrice="+minPrice+" total="+total);
			if(total>minPrice){
				alert("您输入的价格应低于起步价！请修正您的价格！");
				return false;
			}else{
				//获取数据
				var ids ="";
				var prices ="";
				var productId = $("#productId").val();
				$("input[name='nprice_']").each(function(){
					var temp = $(this).val();
					
					if(null!=temp||""!=temp){
						ids = ids +","+$(this).attr("alt");
						prices = prices + ","+temp;
					}
				});
				var url = "<%=basePath %>seckillAction/getSeckill";
				if(ids!=""){
					ids = ids.substring(1);
					prices = prices.substring(1);
					$.post(url,{"ids":ids,"prices":prices,"total":total,"productId":productId},function(data){
						showResponse(data);
					});
				}else{
					$.post(url,{"total":total,"productId":productId},function(data){
						showResponse(data);
					});
				}
			}
		}
	}
	
	//ajax 返回信息处理，成功刷新列表
	function showResponse(responseText) {
		var json = eval("("+responseText+")");
		alert(json.message);
		  if(json.isSuccess){
			  $('#dg').datagrid('reload'); 
		  }
		}
	
	$(document).ready(function(){
		var productId = $("#productId").val();
		$('#dg').datagrid({     
		    url:'<%=basePath %>seckillAction/openSeckillList?productId='+productId,
		    cache : false,
		    type : 'post',
		    width:920,
		    height:350,
		    fitColumns:true,
		    iconCls:'icon-save',
		    striped:true,  //striped  是否显示斑马线效果。
		    sortName:'id',
		    sortOrder:'desc',
		    loadMsg:'信息加载中……',
		    columns:[[     
		        {field:'vcAllName',title:'分供方',width:100,align:'left'},     
		        {field:'ntotalPrice',title:'总价',width:100,align:'right'}, 
		        {field:'dtBid',title:'时间',width:100,align:'left',
		        	formatter:function(value,rec,index){
		        		var unixTimestamp = new Date(value);  
                        return unixTimestamp.toLocaleString();
		        	}
		        }
		    ]],
		     onLoadError:function(){
                      alert("加载数据失败！");
                  },
                  onLoadSuccess:function(data){
                 	 if(data.total==0){
                 		 //当没数据时，显示提示
                 		 $('#dg').datagrid('appendRow',{
                 			vcUsreName: '没有相关记录'
                    	 });
                    	 
                    	 $('#dg').datagrid('mergeCells',{
                    		 index: 0,
                    		 field:'vcUsreName',
                    		 colspan:3
                    	 });
                 	 }else{
                 		 //有数据时，td增加一个title属性，方便展示
                 	 	addTitle();
                 	 }
                  }
		}); 
		
		//定时刷新
		window.setInterval("seacher()",20000);
	});
	
	
	//查询方法
	function seacher(){
		$('#dg').datagrid('reload');
	}
	
</script>
<body>
<%@include file="./header.jsp" %> 
<div class="container">
	<div class="module">
    	<div class="module_th"><span class="icon_clock">订单强抢</span></div>
    	<div class="module_con">
<div class="module_sub">
            	<div class="module_sub_th">订单信息</div>
                <div class="module_sub_con">
<table width="100%" border="0" class="table_form">
  <tr>
    <th width="130">抢方编号：</th>
    <td colspan="2">
    <input type="hidden" id="productId" value="${product.id }" />
    ${product.vcKillno }</td>
    
  </tr>
   <tr>
    <th>起始地：</th>
    <td colspan="2">${product.vcStart }</td>
    </tr>
  <tr>
    <th>目的地：</th>
    <td colspan="2">${product.vcEnd }</td>
    </tr>
  <tr>
    <th>公里数：</th>
    <td>${product.NKilometre }</td>
    <td rowspan="4">
    	<ul class="ul_grab">
            <li><span class="price">起步价: ${product.NMinPrice } 元</span></li>
            <li>
            	<input type="hidden" id="minPrice" value="${product.NMinPrice }" />
            	<input class="f_txt" id="totalprice" name="totalprice"  type="text" placeholder="输入抢单价" />
            </li>
            <li><a href="javascript:;" onclick="getSeckill();" class="f_btn"><span class="icon_grab">我要抢单</span></a> <a href="javascript:;" onclick="detailTable();" class="f_btn"><span class="icon_search">订单详细</span></a></li>
            <!-- <li>
                <div class="grab_time">
                  <div class="grab_tnow" style="left:0;">08-03 09:20:12</div>
                  <div class="grab_tbox"><div class="grab_tcolor" style="width:50px;"></div></div>
                </div>
            </li> -->
        </ul>  
    </td>
    </tr>
  <tr>
    <th>要求抵达时间：</th>
    <td>
    <fmt:formatDate value="${product.dtArriveTime }" pattern="yyyy-MM-dd" />
   </td>
    </tr>
  <tr>
    <th>抢单开始时间：</th>
    <td>
 	   <fmt:formatDate value="${product.dtKillStart }" pattern="yyyy-MM-dd HH:mm:ss" />
    </td>
    </tr>
  <tr>
    <th>抢单结束时间：</th>
    <td>
    	<fmt:formatDate value="${product.dtKillEnd }" pattern="yyyy-MM-dd HH:mm:ss" />
    	<input type="hidden" id="dtKillEnd" name="dtKillEnd" value="${product.dtKillEnd }" />
    </td>
    </tr>
  </table>   

					<table id="tab_detail" style="display:none;" width="100%" border="0" class="table_list">
                        <thead>
                          <tr>
                            <td colspan="3">详细计价</td>
                          </tr>    
                        </thead>
                        <tbody>
                        <c:forEach items="${list }" var="car">
                        	<tr>
                            <td>车型：${car.vcCarStyle }</td>
                            <td>数量：${car.NCarCount }</td>
                            <td>出价:<input type="text" name="nprice_" alt="${car.id}" id="nprice_${car.id}" class="f_txt"  /></td>
                          </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="3" align="right"><input type="button" onclick="totalPrice();" name="button" id="button" value="汇总" class="f_btn_yellow" /></td>
                          </tr>  
                      </tbody>          
                    </table>  
			  </div>
            </div>
<div class="module_sub">
            	<div class="module_sub_th">竞价信息</div>
                <div class="module_sub_con">
                	<table   id="dg"></table>
                </div>
          </div>            
            
        </div>
    </div> 
    <%@include file="./foot.jsp" %> 
</body>
</html>
