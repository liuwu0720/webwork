//该js是提供一些公用的js方法
/**
 * 获得url上的参数
 * create by hjx
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
    }
/**
 * 获得body宽度
 * 当有多个tabs 选择最宽的search的宽度
 * @param minWidth
 * create by hjx
 */
function getWidth(minWidth){
	var searchWidth = "";
	$(".search").each(function(){
		if(searchWidth==""){
			searchWidth = $(this).innerWidth();
		}else{
			if($(this).innerWidth()>searchWidth){
				searchWidth = $(this).innerWidth();
			}
		}
	});
	var t = searchWidth+2;
	// var t =document.body.clientWidth*0.9725;
	    if(minWidth>t){
	    	t=minWidth;
	    }
	    return t;
}
//计算列表的高度
function getHeight(id){
	var tt = $('#'+id).offset().top;
	var temp = windowHeight();
	var t =temp-tt-50;
	if(t>0){
		return t;
	}else{
		return 350;//如果t为小于0，给一个默认值
	}
	
}

function windowHeight() {
    var de = document.documentElement;
    return self.innerHeight||(de && de.clientHeight)||document.body.clientHeight;
}
//由列表的高度，计算列表展示的行数
function getPageSize(height){
	var size = parseInt(height / (400/12));//21
	if(eval(size)<5){
		size = 5;
	}else if(5<size && size<10){
		size = 5;
	}else if(10<size && size<15){
		size = 10;
	}else if(15<size && size<20){
		size = 15;
	}else if(20<size && size<25){
		size = 20;
	}else if(25<size && size<30){
		size = 25;
	}else if(30<size && size<35){
		size = 30;
	}else if(35<size && size<40){
		size = 35;
	}else if(40<size && size<45){
		size = 40;
	}else if(45<size && size<50){
		size = 45;
	}else if(size>50){
		size = 50;
	}
	return size;
}

/**
 * datagrid 列宽度自适应
 */
function fitWidth(data)
{
	 //datagrid头部 table 的第一个tr 的td们，即columns的集合
    var headerTds = $(".datagrid-header-inner table tr:first-child").children();
    //datagrid主体 table 的第一个tr 的td们，即第一个数据行
    var bodyTds = $(".datagrid-body table tr:first-child").children();
    var totalWidth = 0; //合计宽度，用来为datagrid头部和主体设置宽度
    //循环设置宽度
    bodyTds.each(function (i, obj) {
        var headerTd = $(headerTds.get(i));
        var bodyTd = $(bodyTds.get(i));
        $("div:first-child", headerTds.get(i)).css("text-align", "center");
        var headerTdWidth = headerTd.width(); //获取第i个头部td的宽度
        //这里加5个像素 是因为数据主体我们取的是第一行数据，不能确保第一行数据宽度最宽，预留5个像素。有兴趣的朋友可以先判断最大的td宽度都在进行设置
        var bodyTdWidth = bodyTd.width() + 0;
        var width = 0;
        //如果头部列名宽度比主体数据宽度宽，则它们的宽度都设为头部的宽度。反之亦然
        if (headerTdWidth > bodyTdWidth) {
            width = headerTdWidth;
            bodyTd.width(width);
            headerTd.width(width);
           // totalWidth += width;
        } else {
            width = bodyTdWidth;
            headerTd.width(width);
            bodyTd.width(width);
           // totalWidth += width;
        }
    });
   // var headerTable = $(".datagrid-header-inner table:first-child");
   // var bodyTable = $(".datagrid-body table:first-child");
    //循环完毕即能得到总得宽度设置到头部table和数据主体table中
    //headerTable.width(totalWidth);
    //bodyTable.width(totalWidth);
   // bodyTds.each(function (i, obj) {
       // var headerTd = $(headerTds.get(i));
       // var bodyTd = $(bodyTds.get(i));
       // var headerTdWidth = headerTd.width();
       // bodyTd.width(headerTdWidth);
   // });
    
   
}
/**
 * 该方法给datagrid的 onLoadSuccess 事件调用，主要是列内容太多，
 * 无法全部显示，则用这个方法，可以再td里面加上title属性，
 * 当鼠标在单元格上面会悬浮显示所有内容
 * create by hjx
 */
function addTitle(){
	 $("div[class^='datagrid-cell']").each(function(){
		  var txt = $(this).text();
		  if(null!=txt&&""!=txt){
			  $(this).parent().attr("title",txt);
		  }
		  
	  });
}
//关闭弹窗
function weboxClose(){
	$('.webox').remove();
	$('.background').remove();
}

/**
 * 拓展了验证的提示 悬浮框
 * create by hjx
 */
function topTip(map, list) {
	var focussed = document.activeElement;
	if (focussed && $(focussed).is("input, textarea,select")) {
		if($(this).is("select")){
			$(this).removeAttr("class");
			$(this).removeAttr("aria-invalid");
			$(this).removeAttr("aria-required");
			$(this).removeAttr("aria-describedby");
		}
		$(this.currentForm).tooltip("close", {
			currentTarget: focussed
		}, true)
	}
	$(focussed).removeAttr("title").removeClass("ui-state-highlight");
	$.each(list, function(index, error) {
		$(error.element).attr("title", error.message).addClass("ui-state-highlight");
	});
	if (focussed && $(focussed).is("input, textarea,select")) {
		$(this.currentForm).tooltip("open", {
			target: focussed
		});
	}
}
/**
 * 做一些通用插件的初始化或者拓展功能
 * create by hjx
 */
$(document).ready(function() {
	//拓展了验证的提示 悬浮框
	$("form").tooltip({
		show: false,
		hide: false
	});
	//拓展了jquery验证插件的 时间比较方法
	$.validator.methods.compareDate = function(value, element, param) {
        var startDate = jQuery(param).val();
        var date1 = new Date(Date.parse(startDate.replace("-", "/")));
        var date2 = new Date(Date.parse(value.replace("-", "/")));
        return date1 < date2;
    };
});
	   


function isIdCardNo(num) {
   var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
   var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
   var varArray = new Array();
   var intValue;
   var lngProduct = 0;
   var intCheckDigit;
   var intStrLen = num.length;
   var idNumber = num;
   // initialize
   if ((intStrLen != 15) && (intStrLen != 18)) {
       return false;
   }
   // check and set value
   for (i = 0; i < intStrLen; i++) {
       varArray[i] = idNumber.charAt(i);
       if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
           return false;
       } else if (i < 17) {
           varArray[i] = varArray[i] * factorArr[i];
       }
   }

   if (intStrLen == 18) {
       //check date
       var date8 = idNumber.substring(6, 14);
       if (isDate8(date8) == false) {
           return false;
       }
       // calculate the sum of the products
       for (i = 0; i < 17; i++) {
           lngProduct = lngProduct + varArray[i];
       }
       // calculate the check digit
       intCheckDigit = parityBit[lngProduct % 11];
       // check last digit
       if (varArray[17] != intCheckDigit) {
           return false;
       }
   }
   else {        //length is 15
       //check date
       var date6 = idNumber.substring(6, 12);
       if (isDate6(date6) == false) {
           return false;
       }
   }
   return true;
}

function isDate6(sDate) {
   if (!/^[0-9]{6}$/.test(sDate)) {
       return false;
   }
   var year, month, day;
   year = sDate.substring(0, 4);
   month = sDate.substring(4, 6);
   if (year < 1700 || year > 2500) return false
   if (month < 1 || month > 12) return false
   return true
}

function isDate8(sDate) {
   if (!/^[0-9]{8}$/.test(sDate)) {
       return false;
   }
   var year, month, day;
   year = sDate.substring(0, 4);
   month = sDate.substring(4, 6);
   day = sDate.substring(6, 8);
   var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
   if (year < 1700 || year > 2500) return false
   if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
   if (month < 1 || month > 12) return false
   if (day < 1 || day > iaMonthDays[month - 1]) return false
   return true
}

/** 
 * 将数值四舍五入(保留2位小数)后格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.45' 
 * @type String 
 * hjx
 * 
 */  
function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
        num = "非数字！";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
} 
/** 
 * 去小数点，格式化成金额形式 
 * 
 * @param num 数值(Number或者String) 
 * @return 金额格式的字符串,如'1,234,567.45' 
 * @type String 
 * hjx
 * 
 */  
function formatCurrency2(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
        num = "非数字！";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num );  
}
	    