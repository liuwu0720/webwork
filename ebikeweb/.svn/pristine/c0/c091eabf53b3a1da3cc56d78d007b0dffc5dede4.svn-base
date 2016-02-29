<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html,#allmap {width: 100%;height: 95%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zKaFos8O4VQfDffnnk6FqnMc"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/libs/jquery-1.7.2.min.js"></script>
<title>设置驾车路线途经点</title>

<script type="text/javascript">
$(document).ready(function(){
	if('${error }'){
		//alert(" "+'${error }'+" ");
	}

// 百度地图API功能
var map = new BMap.Map("allmap");
map.centerAndZoom(new BMap.Point('${tFixTruck.vcLongitude}','${tFixTruck.vcLatitude}'), 11);
map.enableScrollWheelZoom(true);

//var p1 = new BMap.Point('${tFineApplay.vcLongitude}','${tFineApplay.vcLatitude}');
//var p2 = new BMap.Point(116.508328,39.919141);

//绘制罚款点坐标 
	var point = new BMap.Point('${tFixTruck.vcLongitude}','${tFixTruck.vcLatitude}');
	
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	var label = new BMap.Label("申请点的位置",{offset:new BMap.Size(20,-10)});
	marker.setLabel(label);

var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
//driving.search(p1, p2);//waypoints表示途经点
var arrList = '${result }';

var jsonArr =  eval("("+arrList+")");

var p1 = new BMap.Point(jsonArr[0].VC_LONG,jsonArr[0].VC_LAT);//起点
var p2 = new BMap.Point(jsonArr[jsonArr.length-1].VC_LONG,jsonArr[jsonArr.length-1].VC_LAT);//终点
var parry = new Array();
for(var i in jsonArr){
	if(i>=1){
		var p3 = new BMap.Point(jsonArr[i].VC_LONG,jsonArr[i].VC_LAT);
		parry.push(p3);
	}
}


driving.search(p1, p2,{waypoints:parry});//waypoints表示途经点



});

</script>
</head>
<body>
<div id="allmap">

</div>
<div id="error">${error }</div>
</body>
</html>