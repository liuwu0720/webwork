<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>获取城市信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%@ include file="../include/common.jsp"%>
    <script type="text/javascript">
    	$(document).ready(function(){
    		$('.cityBox ul li:eq(0)').click(function(){
    			$('.cityBox ul li:eq(1)').removeClass("on");
    			$('.cityBox ul li:eq(0)').addClass("on");
				$('#dl1').show();
				$('#dl2').hide();
        	});
    		$('.cityBox ul li:eq(1)').click(function(){
    			$('.cityBox ul li:eq(0)').removeClass("on");
    			$('.cityBox ul li:eq(1)').addClass("on");
    			$('#dl2 dd').empty();
				$('#dl2').show();
				$('#dl1').hide();
        	});
        });
    	//根据省id获取省下面所有的城市
    	var provinceId;
    	var provinceName;
    	function getCitys(id,value){
			$('.cityBox ul li:eq(0)').removeClass("on");
			$('.cityBox ul li:eq(1)').addClass("on");
			$('#dl1').hide();
			$('#dl2').show();
			provinceId=id;
			provinceName=value;
			$('#endAddr',window.parent.document).val(provinceName);//给输入框赋值
			//alert('省：'+provinceId+','+provinceName);
			$.get('<%=basePath%>cityAction/getCitysByProID?id='+id,
					function(data){
						$('#dl2 dd').empty();
						var citys=[];
						for(var i=0;i<data.length;i++){
							var str='<a href="javascript:;" onclick="selectCity(\''+data[i].id+'\',\''+data[i].cityname+'\')">'+data[i].cityname+'</a>';
							citys.push(str);
						}
						$('#dl2 dd').html(citys.join(''));
						//alert("html:"+$('#dl2 dd').html());
					},
					"json"
			);
        }
        function selectCity(cityId,cityName){
            var addr=provinceName+'/'+cityName;
            //alert(addr);
			$('#endAddr',window.parent.document).val(addr);//给输入框赋值
			$('#endpro',window.parent.document).val(provinceId);//给隐藏域省份赋值省
			$('#endcity',window.parent.document).val(cityId);//给隐藏域城市赋值
			window.parent.weboxClose(); 
        }
    </script>
  </head>
  
  <body >
    <div class="citySelector">
    	<div class="cityBox">
    		<ul >
    			<li class="on">省份</li>
    			<li>城市</li>
    		</ul>
    		<div class="hotCity">
    			<div class="cityTab">
    				<dl id="dl1">
    					<dt>&nbsp;</dt>
    					<dd>
    						<c:forEach items="${provinces}" var="province" >
    							<a href="javascript:;" onclick="getCitys(${province.id},'${province.vcProvinceName }')">${province.vcProvinceName }</a>
    						</c:forEach>
    					</dd>
    				</dl>
    				<dl id="dl2" class="hide">
    					<dt>&nbsp;</dt>
    					<dd>
    						
    					</dd>
    				</dl>
    			</div>
    		</div>
    	</div>
    </div>
  </body>
</html>