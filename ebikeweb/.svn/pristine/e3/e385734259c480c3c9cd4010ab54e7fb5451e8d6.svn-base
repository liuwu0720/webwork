// JavaScript Document
$(document).ready(function() {
//menu
	var $li_obj = $("ul.submenu>li:has(ul)");
	//初始化
	$li_obj.siblings().children("ul").slideUp();
	$li_obj.addClass("has");
	$li_obj.each(function(i){
		 i=i +1;
		 $(this).addClass("has" + i);
		 if($(this).hasClass('active')){
			$(this).children("ul").slideDown();
		};		 
	});	
	//$li_obj.eq(0).children("ul").slideDown();
	//绑定对象及其函数
	$li_obj.click(function(){
		//alert("click");
		if ($(this).hasClass("active")){
			//alert("have");	
		}
		else{
			$(this).addClass("active");	
			$(this).siblings().removeClass("active");
			$(this).siblings().children("ul").stop(true,true).slideUp("fast");
			$(this).children("ul").stop(true,true).slideDown(400);			
		}
	});
//Top dropdown
	var $li_dropdown = $("div.top ul>li:has('div.dropdown')");
	$li_dropdown.addClass("has");
	$(".top li").has('div.dropdown').hover(function(){
		$(this).addClass("hover");
		$(this).find("div.dropdown").show();
	},function(){
		$(this).removeClass("hover");
		$(this).find("div.dropdown").hide();
	});	
//Scroll
//j2
    $("#slider_ad .scrollbox").jCarouselLite({
        btnNext: "#slider_ad .next",
        btnPrev: "#slider_ad .prev",
		visible: 4
    });
/*
	if($('.scrollbox li').length > 4){
		$("#slider_ad .prev").click(function(){
			$('.scrollbox li:last').css("margin-left","-206px").insertBefore($('.scrollbox li:first')).animate({'margin-left':'0'});
		});
		
		$("#slider_ad .next").click(function(){
			$('.scrollbox li:first').animate({"margin-left":"-206px"},function(){
				$(this).insertAfter('.scrollbox ul li:last').css('margin-left','0');				
			})
		});
	}
*/	
//table
	$("table.table_data>tbody>tr:odd").addClass("odd");
	$("table.table_data>tbody>tr:even").addClass("even");
	$("table.table_data>tbody>tr").hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});	
//Marquee
	$(".marquee").jMarquee({
		visible:2,
		step:1,
		direction:"up"
	});	
//Slider
	$("#slider").anythingSlider({
		autoPlay:true,
		buildArrows:false,
		buildNavigation:true,
		buildStartStop:false
	});

//Form Tips
$('input, textarea').placeholder();	
	/*
	$("#ad_slider").anythingSlider({
		autoPlay:true,
		buildArrows:false,
		buildNavigation:true,
		buildStartStop:false
	});*/
	$("ul.ul_list>li:odd").addClass("odd");	
	$("ul.ul_list>li:even").addClass("even");
});