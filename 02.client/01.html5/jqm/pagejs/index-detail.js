$(function(){
	console.debug("start dyna news ... ... ");
	
	$.getJSON('pagedata/new.json', function(data) {

		imgtemplate=$('#new-template');
		//console.debug(template);
		imgview= Mustache.to_html(imgtemplate.html(),data).replace(/^\s*/mg, '');
		//console.debug($('#slides'));
		$('#new').html(imgview).trigger('create');//.listview('refresh');
	});

});


(function($){
	$.example = function()
	{
		
	}
})(jQuery);
