<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
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

<script type="text/javascript" src="static/js/libs/jquery-1.7.2.min.js" language="javascript"></script>
<script type="text/javascript" src="static/js/libs/jquery.anythingslider.js"></script>
<script type="text/javascript" src="static/js/libs/jquery.placeholder.js"></script>
<script type="text/javascript" src="static/js/libs/jcarousellite.js"></script>
<script type="text/javascript" src="static/js/libs/Marquee.js"></script>
<script type="text/javascript" src="static/js/home/layout.js"></script>
<script type="text/javascript" src="static/js/libs/jquery-webox.js"></script>
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
	
	
</script>
<body>
<%@include file="./header.jsp" %> 
<div class="container">
	<div class="index">
    	<div class="index_right">
            <ul id="slider">
                <li><a href="#"><img src="static/image/home/ad1.jpg" /></a></li>
                <li><a href="#"><img src="static/image/home/ad1.jpg" /></a></li>
                <li><a href="#"><img src="static/image/home/ad1.jpg" /></a></li>
                <li><a href="#"><img src="static/image/home/ad1.jpg" /></a></li>
                <li><a href="#"><img src="static/image/home/ad1.jpg" /></a></li>
            </ul>
            <div class="notice">
            	<div class="notice_th"><a href="#">通知公告</a></div>
                <div class="notice_con marquee">
                     <ul class="list_news">
                        <li><a href="#">集团领导观看华侨城“夕阳红”书画展</a></li>
                        <li><a href="#">集团公司召开2010年度财务决算暨2011年度财务预算工作 </a></li>
                        <li><a href="#">集团领导观看华侨城“夕阳红”书画展</a></li>
                        <li><a href="#">集团公司召开2010年度财务决算暨2011年度财务预算工作 </a></li>
                        <li><a href="#">集团领导观看华侨城“夕阳红”书画展</a></li>
                        <li><a href="#">集团公司召开2010年度财务决算暨2011年度财务预算工作 </a></li>
                  	</ul>                
                </div>
            </div>        
        </div>
        <div class="index_left">
            <div class="block">
                <div class="block_th"><a href="#" class="ion1">用户登陆</a></div>
                <div class="block_con" id="login">
                	<div class="login">
                	<form action="j_spring_security_check" method="post">
                    	<div><input type="text" class="f_txt" placeholder="用户名/email" name="account" /></div>
                        <div><input type="password" class="f_txt" placeholder="密码" name="password" /></div>
                        <div><input type="submit" class="f_login" value=""/> <a href="#">忘记密码</a> <a href="javascript:showRegPage();" class="reg">免费注册</a> </div>
                    
                    </form>
                
                 	<p>我们为您提供做生意找代理商,一手创业咨询,小本投资,快速创业,代理商信息时时更新.代理商信息询,小本投资,快速创业,代理商信息时时更新.找特色 小本投资,快速创业,代理商信息...<a href="#" class="red">[了解详细]</a></p>
                    </div>
                </div>        
            </div>
        </div>
        <div class="clear"></div>
    </div>

    <div class="block" id="order">
        <div class="block_th"><a href="#" class="ion2">订单强抢</a></div>
        <div class="block_con">
            <div class="block_con_l">
<table width="100%" border="0" class="table_form">
  <tr class="line">
  	<th width="90">&nbsp;</th>
    <td><a href="javascript:;" onclick="openSkill();"><img src="static/image/home/an3.jpg" /></a></td>
    </tr>
  <tr>
    <th>时间:</th>
    <td>
      <input type="text" class="f_txt"/>
   </td>
  </tr>
  <tr>
    <th>起运地:</th>
    <td><input type="text" id="vcStart" name="vcStart"  class="f_txt" placeholder="城市名称(中文/拼音)"/></td>
  </tr>
  <tr>
    <th>目的地:</th>
    <td><input type="text"  id="vcEnd" name="vcEnd" class="f_txt" placeholder="城市名称(中文/拼音)"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="" type="submit" value="" class="f_search2" /></td>
  </tr>
</table>
				                    
          </div>
            <div class="block_con_r">
            	<div class="block_r_th">
               	  <div class="block_r_th_box">
                  	订单明细
                  		<div class="tab_date">
                        	<a href="#" class="active">日</a>
                            <a href="#">季度</a>
                            <a href="#">年度</a>
                        </div>  
                  </div>
            	</div>
                <div class="block_r_con">
<table width="100%" border="0" class="table_data">
	<thead>
      <tr>
        <th>订单号</th>
        <th>货物名称</th>
        <th>日期</th>
        <th>起运地</th>
        <th>&nbsp;</th>
        <th>目的地</th>
        <th>数量</th>
        <th>&nbsp;</th>
      </tr>
  	</thead>
    <tbody>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
    <td>100</td>
    <td><a href="#" class="rob"></a></td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
    <td>100</td>
    <td><a href="#" class="rob"></a></td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
    <td>100</td>
    <td><a href="#" class="rob"></a></td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
    <td>100</td>
    <td><a href="#" class="rob"></a></td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
    <td>100</td>
    <td><a href="#" class="rob"></a></td>
  </tr>
  </tbody>
</table>

              </div>                    
        </div>
			<div class="clear"></div>
        </div>        
    </div>    
    <div class="block" id="express">
        <div class="block_th"><a href="#" class="ion3">运力服务</a></div>
        <div class="block_con">
            <div class="block_con_l">
<table width="100%" border="0" class="table_form">
  <tr class="line">
  	<th width="90">&nbsp;</th>
    <td><a href="#"><img src="static/image/home/an4.jpg" /></a></td>
    </tr>
  <tr>
    <th>车牌品:</th>
    <td>
      <input type="text" class="f_txt"/>
   </td>
  </tr>
  <tr>
    <th>起运点:</th>
    <td><input type="text" class="f_txt"/></td>
  </tr>
  <tr>
    <th>目的地:</th>
    <td><input type="text" class="f_txt"/></td>
  </tr>
  <tr>
    <th>车辆规格:</th>
    <td><input type="text" class="f_num"/>长 <input type="text" class="f_num"/>宽 <input type="text" class="f_num"/>高 (米)</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input name="" type="submit" value="" class="f_search2" /></td>
  </tr>
</table>
				                    
            </div>
            <div class="block_con_r">
            	<div class="block_r_th">
                	<div class="block_r_th_box">
                    	运力明细
                  		<div class="tab_date">
                        	<a href="#" class="active">日</a>
                            <a href="#">季度</a>
                            <a href="#">年度</a>
                        </div>                    
                    </div>
            	</div>
                <div class="block_r_con">
<table width="100%" border="0" class="table_data">
	<thead>
      <tr>
        <th>车牌号</th>
        <th>车规格(单位米)</th>
        <th>日期</th>
        <th>起运地</th>
        <th>&nbsp;</th>
        <th>目的地</th>
      </tr>
  	</thead>
    <tbody>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  <tr>
    <td>ZL0789</td>
    <td>长5 宽3 高3</td>
    <td>2014-07-10</td>
    <td>南昌</td>
    <td><img src="static/image/home/to.png" /></td>
    <td>深圳</td>
  </tr>
  </tbody>
</table>

                </div>                    
          </div>
			<div class="clear"></div>
        </div>        
    </div>
    <div class="block">
        <div class="block_th"><a href="#" class="ion4">金融服务</a></div>
        <div class="block_con">
            <div class="block_con_l">
            	<div class="list_a">                                    
                <a href="#">运费借款</a>
                <a href="#">团购保险</a>
                <a href="#">贷款购车</a>
                <a href="#">团购保险</a>
                <a href="#">贷款购车</a>
                <a href="#">理赔担保</a>                    
                <a href="#">贷款购车</a>
                <a href="#">理赔担保</a>
                <a href="#">贷款购车</a>
                <a href="#">理赔担保</a>                    
                <a href="#">贷款购车</a>
                <a href="#">理赔担保</a>                
                </div>               
            </div>
            <div class="block_con_r">
            	<div class="block_r_th">
                	<div class="block_r_th_box"></div>
            	</div>
                <div class="block_r_con">
                	<div id="slider_fin">
                        <ul>
                            <li><a href="#"><img src="static/image/home/fin_1.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_2.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_3.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_4.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_5.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_6.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_7.jpg" /></a></li>
                            <li><a href="#"><img src="static/image/home/fin_8.jpg" /></a></li>
                        </ul>
						<a href="#" class="prev"></a>
                        <a href="#" class="next"></a>
                    </div>
                </div>                    
            </div>
			<div class="clear"></div>
        </div>        
    </div>
    <div class="block">
        <div class="block_th"><a href="#" class="ion5">店铺信息</a></div>
        <div class="block_con">
            <div class="block_con_l">
            	<div class="list_a">
                <a href="#">整车</a>
                <a href="#">轮胎</a>
                <a href="#">配件</a>
                <a href="#">五金</a>
                <a href="#">整车</a>
                <a href="#">轮胎</a>                    
                <a href="#">配件</a>
                <a href="#">整车</a>
                <a href="#">轮胎</a>
                <a href="#">配件</a>
                <a href="#">五金</a>
                <a href="#">整车</a>
                <a href="#">轮胎</a>                    
                <a href="#">配件</a>
                <a href="#">整车</a>
                <a href="#">轮胎</a> 
                </div>                                   
            </div>
            <div class="block_con_r">
            	<div class="block_r_th">
                    <div class="block_r_th_box">
                    	按名称：<input type="text" class="f_txt2" />
                        按类别：<input type="text" class="f_txt2" />
                        按品牌：<input type="text" class="f_txt2" />
                    	<input name="" type="submit" value="" class="f_search" />
                    </div>
            	</div>
                <div class="block_r_con">
                	<ul class="list_width">
                        <li><a href="#">浙江锐野专用车辆有限公司</a></li>
                        <li><a href="#">青岛中集冷藏运输设备有限公司</a></li>
                        <li><a href="#">安徽星凯龙客车有限公司</a></li>
                        <li><a href="#">山推建友机械股份有限公司</a></li>
                        <li><a href="#">浙江锐野专用车辆有限公司</a></li>
                        <li><a href="#">青岛中集冷藏运输设备有限公司</a></li>
                        <li><a href="#">安徽星凯龙客车有限公司</a></li>
                        <li><a href="#">山推建友机械股份有限公司</a></li>
                        <li><a href="#">浙江锐野专用车辆有限公司</a></li>
                        <div class="clear"></div>
                    </ul>
                </div>                    
            </div>
			<div class="clear"></div>
        </div>        
    </div>                    
    <div class="block">
        <div class="block_th"><a href="#" class="ion6">网点查询</a></div>
        <div class="block_con">
            <div class="block_con_l">
            	<div class="list_a">
                <a href="#">北京</a>
                <a href="#">上海</a>
                <a href="#">南昌</a>
                <a href="#">深圳</a>
                <a href="#">大连</a>
                <a href="#">中山</a>                    
                <a href="#">厦门</a>
                <a href="#">长沙</a>
                <a href="#">武汉</a>
                <a href="#">西安</a>
                <a href="#">温州</a>
                <a href="#">杭州</a>
                </div>                   
            </div>
            <div class="block_con_r">
            	<div class="block_r_th">
                	<div class="block_r_th_box">
                        按字母排序
                        <a href="#" class="active">A</a>
                        <a href="#">B</a> 
                        <a href="#">C</a> 
                        <a href="#">D</a> 
                        <a href="#">E</a> 
                        <a href="#">F</a> 
                        <a href="#">G</a> 
                        <a href="#">H</a> 
                        <a href="#">I</a> 
                        <a href="#">J</a> 
                        <a href="#">K</a> 
                        <a href="#">L</a> 
                        <a href="#">M</a> 
                        <a href="#">N</a> 
                        <a href="#">O</a> 
                        <a href="#">P</a>
                        <a href="#">Q</a>
                        <a href="#">R</a>
                        <a href="#">S</a>
                        <a href="#">T</a>
                        <a href="#">U</a>
                        <a href="#">V</a>
                        <a href="#">W</a>
                        <a href="#">X</a>
                        <a href="#">Y</a>
                        <a href="#">Z</a>
                    </div>
            	</div>
                <div class="block_r_con">
                	<a href="#">鞍山</a>
                	<a href="#"> 安阳 </a>
                    <a href="#"> 安庆 </a>
                    <a href="#"> 安顺 </a>
                    <a href="#"> 阿里 </a>
                    <a href="#"> 安康 </a>
                    <a href="#"> 阿克苏 </a>
                    <a href="#"> 澳门 </a>
                    <a href="#"> 安吉 </a>
                    <a href="#">安宁 </a>
                </div>                    
            </div>
			<div class="clear"></div>
        </div>        
    </div>    
    <%@include file="./foot.jsp" %> 
</body>
</html>
