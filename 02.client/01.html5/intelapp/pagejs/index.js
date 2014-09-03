$(function(){
	
	$.getJSON('pagedata/news.json', function(data) {

		template=$('#news-template');
		//console.debug(template);
		newsview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(newsview);
		$('#news-listview').empty().html(newsview);
	});

});