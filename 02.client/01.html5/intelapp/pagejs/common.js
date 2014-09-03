
$(function(){
	console.debug("left menu dymn load from json file...");
	
	$.getJSON('pagedata/menu-left.json', function(data) {		
		template=$('#left-menu-template');
		//use source Mustarche pref 		
		mylistview1= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		$('#menuitem').empty().html(mylistview1);
	});
});


(function ($) {
    'use strict';
  $.mustache = function (template, view, partials) {
	return Mustache.render(template, view, partials);	
  };

  $.fn.mustache = function (view, partials) {
    return $(this).map(function (i, elm) {
		//console.debug($(elm).html());
      var template = $.trim($(elm).html());//jquery2html
	  	//console.debug(template);
		//console.debug(view);
      var output = $.mustache(template, view, partials);
      return $(output).get();//html2jquery
    });
  };

});


(function($){
	//$.getUrlParam('cid');
	$.getUrlParam = function(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
		}
});

function showHide(obj,objToHide){
		var el=$("#"+objToHide)[0];
		
		if(obj.className=="expanded"){
			obj.className="collapsed";
		}
		else{
			obj.className="expanded";
		}
		$(el).toggle();
		
}
