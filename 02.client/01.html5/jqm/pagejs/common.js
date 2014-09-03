//服务器地址
server_url="http://localhost:8080/fastweb-mongodb/";

$(document).on('pageinit','[data-role=page]', function(){
    //$('[data-position=fixed]').fixedtoolbar({ tapToggle:false });
    //console.debug($( ".lineview" ));
	//$.lineview();		

	console.debug("开始自动登陆 ... ... ");	
	console.debug($('[data-role=header]'));
	 

		
		
	//$('#login').hide();
	//$('#login-info').show();
//	$('#login').remove();
//	$('#login-info').show();
//	$('#login-info').trigger('create');

	//$('#login-info').remove();
	//$('#login').show();
	//$('#login').trigger('create');
	//$('#login').textinput();
	$.login ();
	
	$.mobile.resetActivePageHeight();
	
// $.mobile.loading( "show", {
//         text: "欢迎使用",
//         textVisible: "true",
//         theme: "a",
//         textonly: "true",
//         html: "...html代码.."
//  });
	
	
});

$(function(){
	
	
});


(function($){
	$.login = function(name,password)
	{
		console.debug("登陆加载......");		
		
		
		$.getJSON('pagedata/signin.json', function(data) {
			console.debug(data);
			template=$('#login-template');
			//console.debug(template);
			view= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
			//console.debug($('#slides'));
			
			//$('#login').hide();
			//console.debug($('#login-info .ui-btn-text'));
			//$('#login-info .ui-btn-text').html(view);
			//$('#login-info').show();  //TODO
			$('#login').empty().append('<a href="#popupLogin" id="login-info"  data-position-to="window" data-role="button" data-inline="true" data-icon="info"  data-transition="pop">{{name}},你好! </a>').buttonMarkup();
//			$('#popupLogin11').page();

			console.debug("登陆加载......完成");		


			//$('[data-role=header]').append(view).trigger('create').button("refresh");//.page();
		});		
	}
})(jQuery);

$(function(){
	console.debug("动态加载左侧菜单数据...");
	
	menu_url=server_url+"menu/list/0/100.jsonp?callback=?"
	//'pagedata/menu-left.json'
	
	$.getJSON(menu_url,{}, function(data) {	
		menudata={"data":data};
		console.debug("menu data:"+menudata.data[0].subtitle);
		console.debug("menu data:"+menudata.data[0].items[0].title);
		template=$('#left-menu-template');
		mylistview1= Mustache.to_html(template.html(),menudata).replace(/^\s*/mg, '');
		$('#menuitem').empty().append(mylistview1).trigger('create').collapsibleset('refresh');
		console.debug("动态加载左侧菜单数据...完成");
	});
	
});

//模板引擎定义
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

})(jQuery);

//获取参数值
(function($){
	//$.getUrlParam('cid');
	$.getUrlParam = function(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
		}
})(jQuery)

//返回结果处理
function callback(data){
	console.debug("此方法可以预定义......");
	console.debug(data);
}

//提交数据基础方法
function sendData(jsonurl,jsondata,ajaxtype,callback) {
	$.ajax({
	    type:ajaxtype,
	    url: jsonurl,
	    data:JSON.stringify(jsondata), // or JSON.stringify ({name: 'jonas'}),//data:data
	    success: callback,
	    contentType: "application/json",
	    dataType: 'json'
	});    
}

//POST方式提交数据
function postData(url,data) {
	sendData(url,data,"POST",callback);
}

//更新数据
function updateData(url,data) {
	sendData(url,data,"PUT",callback);
}

//删除数据
function deleteData(url) {
	sendData(url,{},'DELETE',callback);
}
//批量删除数据
function deletelist(url,data) {
	sendData(url,data,'DELETE',callback);
}