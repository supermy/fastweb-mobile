$(document).on('pageinit','[data-role=page]', function(){
	$.load_persons()	
});

$.load_persons = function (){
	console.debug("动态加载页面数据 ... ... ");
	$.getJSON('pagedata/persons.json', function(data) {
		template=$('#persons-template');
		//console.debug(template);
		personview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(personview);
		$('#person-listview').empty().append(personview).trigger('create').listview('refresh');
		console.debug("动态加载页面数据 ... ... 完成");
		$.lineview();		
			
	});

};

$.lineview = function () {
	
console.debug("为每行增加点击事件");
$( ".lineview" ).on( "click", function() {
		var target = $( this ),
			brand = target.find( "h2" ).html(),
			model = target.find( "p" ).html(),
			short = target.attr( "id" ),
			closebtn = '<a href="#"  data-rel="back" data-role="button" data-theme="a" data-icon="delete" data-iconpos="notext" data-shadow="false" data-iconshadow="false" class="ui-btn-right">关闭</a>',
			header = '<div data-role="header"><h2>' + brand + ' ' + model + '</h2></div>',
			img = '<img src="../js/_assets/img/' + short + '.jpg" alt="' + brand + '" class="photo">',
			popup = '<div data-role="popup" id="popup-' + short + '" data-short="' + short +'" data-theme="none" data-overlay-theme="a" data-corners="false" data-tolerance="15">' + closebtn + header + img + '</div>';
			

		//创建弹出页面.
		$.mobile.activePage.append( popup ).trigger( "pagecreate" );
		// Wait with opening the popup until the popup image has been loaded in the DOM.
		// This ensures the popup gets the correct size and position
		$( ".photo", "#popup-" + short ).load(function() {
			var height = $( this ).height(),
				width = $( this ).width();
			// Set height and width attribute of the image
			$( this ).attr({ "height": height, "width": width });
			// Open the popup
			$( "#popup-" + short ).popup( "open" );
			// Clear the fallback
			clearTimeout( fallback );
		});
		// Fallback in case the browser doesn't fire a load event
		var fallback = setTimeout(function() {
			$( "#popup-" + short ).popup( "open" );
		}, 2000);
	});

//	 Set a max-height to make large images shrink to fit the screen.
	$( document ).on( "popupbeforeposition", ".ui-popup", function() {
		// 68px: 2 * 15px for top/bottom tolerance, 38px for the header.
		var maxHeight = $( window ).height() - 68 + "px";
		$( "img.photo", this ).css( "max-height", maxHeight );
	});

	// Remove the popup after it has been closed to manage DOM size
	$( document ).on( "popupafterclose", ".ui-popup", function() {
		console.log('popupafterclose......');
		
		var isLineId=$( this ).attr("id").indexOf("popup-");
//				alert(isLineId);
		console.log('popupafterclose......'+isLineId);
		if(isLineId>=0){
			$( this ).remove();	
		}
	});
	console.debug("为每行增加点击事件......完成");

};