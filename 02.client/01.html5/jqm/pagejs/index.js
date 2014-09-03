$(document).on('pageinit','[data-role=page]', function(){
	$.load_home();	
});

$.load_home = function (){
	//$.slides();
	
	$.getJSON('pagedata/slides.json', function(data) {
		console.debug("开始加载幻灯片 ... ... ");

		imgtemplate=$('#slides-template');
		//console.debug(template);
		imgview= Mustache.to_html(imgtemplate.html(),data).replace(/^\s*/mg, '');
		//console.debug($('#slides'));
		$('#slides-list').html(imgview);//.trigger('create').listview('refresh');
		$.slides();
		console.debug("开始加载幻灯片 ... ... 完成");
	});

	$.getJSON('pagedata/news.json', function(data) {
		console.debug("开始加载新闻 ... ... 完成");

		template=$('#news-template');
		//console.debug(template);
		newsview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(newsview);
		$('#news-listview').empty().append(newsview).trigger('create').listview('refresh');
		console.debug("开始加载新闻 ... ... 完成");
	});

};


(function($){
	$.slides = function()
	{
		$('#slides').slidesjs({
			width: 940,
			height: 528,
			play: {
			  active: true,
			  auto: true,
			  interval: 4000,
			  swap: true
			}
		});		
	}
})(jQuery);
