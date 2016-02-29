<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>分供方信息初始化</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="IE=7;IE=9" />
    <%@include file="../../include/common.jsp" %>
	<%@ include file="../../include/datagrid.jsp"  %>
	<!-- 引入ystep插件 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js/libs/ystep/css/ystep.css" />
	<script src="${pageContext.request.contextPath}/static/js/libs/ystep/js/ystep.js"></script>
	<script type="text/javascript">
		var step=1;
		var max=11;
		$(document).ready(function(){
			getYstep();
		});
		function getYstep(){
			$(".ystep1").loadStep({
		      //ystep的外观大小
		      //可选值：small,large
		      size: "large",
		      //ystep配色方案
		      //可选值：green,blue
		      color: "green",
		      //ystep中包含的步骤
		      steps: [
		      {
		        //步骤名称
		        title: "承运方完善",
		        //步骤内容(鼠标移动到本步骤节点时，会提示该内容)
		        content: "承运方完善"
		      },
		      {
		        title: "客户信息",
		        content: "客户信息维护"
		      },
		      {
		        title: "商品车维护",
		        content: "商品车信息维护"
		      },
		      {
		        title: "应收公里数",
		        content: "应收公里数信息维护"
		      },
		      {
		        title: "应收单价",
		        content: "应收单价信息维护"
		      },
		      {
		        title: "司机维护",
		        content: "司机信息维护"
		      },
		      {
		        title: "运输车维护",
		        content: "运输车维护"
		      },
		      {
		        title: "工资系数",
		        content: "司机工资系数信息维护"
		      },
		      {
		        title: "基本工资",
		        content: "司机基本工资信息维护"
		      },
		      {
		        title: "司机补贴",
		        content: "司机补贴维护"
		      },
		      {
		        title: "完成",
		        content: "恭喜你，信息初始化成功"
		      }
		      ]
		    });
			//所有跳转操作，仅需在加载ystep的容器上调用即可
            //跳转到下一个步骤
            //$(".ystep1").nextStep();
            //跳转到上一个步骤
            //$(".ystep1").prevStep();
            //跳转到指定步骤
            $(".ystep1").setStep(step);
            //获取当前在第几步
            //$(".ystep1").getStep();
		}
		//上一步
		function goBack(){
		
			if(step>1){
				step--;
			}
			var url = getUrl(step);
			$(".ystep1").setStep(step);
			$('#iframe_1').attr("src",url);
		}
		//下一步
		function toNext(){
			
			if(step<max){
				step++;
			}
			var url = getUrl(step);
			$(".ystep1").setStep(step);
			$('#iframe_1').attr("src",url);
			
		}
		
		function getUrl(step){
				var url ;
				switch(step){
				case 1:
				url = url_1;
				break;
				case 2:
				url = url_2;
				break;
				case 3:
				url=url_3;
				break;
				case 4:
				url = url_4;
				break;
				case 5:
				url=url_5;
				break;
				case 6:
				url=url_6;
				break;
				case 7:
				url =url_7;
				break;
				case 8:
				url = url_8;
				break;
				case 9:
				url = url_9;
				break;
				case 10:
				url = url_10;
				break;
				default:
				url=url_11;
			}
			return url;		
		
		}
		
		var url_1='<%=basePath%>subsuppliersAction/SubsuppliersSaveBefore';
		var url_2='<%=basePath%>customerAction/customerList';
		var url_3='<%=basePath%>subCarStyleAction/intoAllSubCarStyle';
		var url_4='<%=basePath%>arkilometerAction/intogetAllArkilometer';
		var url_5='<%=basePath%>driverCostAction/intoGetAllDriverCost';
		var url_6='<%=basePath%>truckDriverAction/intogetAllDriversBysubno';
		var url_7='<%=basePath%>truckDriverAction/intogetAlltruckDriverBysubno';
		var url_8='<%=basePath%>driverAction/intoDriverSalaryCoefficient';
		var url_9='<%=basePath%>driverAction/intoDriverSalaryList';
		var url_10='<%=basePath%>driverAction/intoDriverSubsidies';
		var url_11='';
	</script>
  </head>
  
  <body class="main" >
    <dl class="tabs">
    	<dt><a href="#"><span>分供方信息初始化</span></a></dt>
        <dd class="form">
        	<div class="ystep1"></div>
        	<div style="margin:15px 0px 10px 5px">
        		<input type="button" value="上一步"  style="magin-left:200px" class="f_btn" onclick="goBack()"/>
        		<input type="button" value="下一步"  style="magin-left:350px" class="f_btn" onclick="toNext()"/>
        	</div>
        	<div style="clear:both"></div>
        	<iframe scrolling="auto" frameborder="0" title="承运方信息完善" id="iframe_1" src="<%=basePath%>subsuppliersAction/SubsuppliersSaveBefore"  height="600" width="100%"></iframe>
        </dd>
    </dl>
  </body>
</html>