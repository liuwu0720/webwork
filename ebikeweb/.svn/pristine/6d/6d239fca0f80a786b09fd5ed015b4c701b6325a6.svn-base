// JavaScript Document
$(document).ready(function() {
//table
	$("table.table_data>tbody>tr:odd").addClass("odd");
	$("table.table_data>tbody>tr:even").addClass("even");
	$("table.table_data>tbody>tr").hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});	

//Form Tips
$('input, textarea').placeholder();	
//Tab
	$(".tabs>dt>a:first").addClass("first");
	$(".tabs>dt>a:last").addClass("last");
	$(".tabs").switchTab({titCell: "dt a", trigger: "mouseover", delayTime: 0});


	
	$("div.menu a").click(function(){
		//alert("go");								
	});
	
//Float mask iframe
	$('#outside').click(function(){
		$.webox({
			height:400,
			width:600,
			bgvisibel:true,
			title:'iframe',
			iframe:'iframe.html?'+Math.random
		});
	});	
});