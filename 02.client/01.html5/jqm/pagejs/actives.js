$(document).on('pageinit','[data-role=page]', function(){
	//$.load_persons();
});

$(document).on('pageshow','[data-role=page]', function(){
	//动态构造提示框；创建提示框；显示提示框
	template=$('#p-template');
	data={msg:"这是我定制的提示信息",title:"提示信息!"};
	pupopview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
	$('#p-view').empty().append(pupopview).trigger('create');//.popup('refresh');
	$('#popupBasic').popup();
	$('#popupBasic').popup('open');	
//	$('#popupBasic').popup('open', { dismissible: "flow" });
//	$('#popupBasic').popup().popup("open");
//	$('#popupBasic').popup("open");
});


(function ($) {  
  
$.load_persons = function (){
	console.debug("动态加载页面数据 ... ... ");
	console.debug("动态加载页面数据 ... ... 完成");
};  

})(jQuery);
