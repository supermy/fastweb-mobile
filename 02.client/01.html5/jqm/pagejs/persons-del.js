$( document ).on( "pageinit", "#home-page", function() {
    //$('[data-position=fixed]').fixedtoolbar({ tapToggle:false });

	$.deleview();

});

$(function(){
	console.debug("start dyna persons ... ... ");
	$.getJSON('pagedata/persons.json', function(data) {
		template=$('#persons-del-template');
		//console.debug(template);
		personview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(personview);
		$('#person-del-listview').empty().append(personview).trigger('create').listview('refresh');
		$.deleview();		
	});

});


$.deleview = function () {
	//切换到删除界面
	$( document ).on( "swipeleft swiperight", "#list li.ui-li", function( event ) {
		var listitem = $( this ),
			//CSS切换
			dir = event.type === "swipeleft" ? "left" : "right",
			//(3D) CSS 切换效果
			transition = $.support.cssTransform3d ? dir : false;

			confirmAndDelete( listitem, transition );
	});

	// If it's not a touch device...
	if ( ! $.mobile.support.touch ) {

		// Remove the class that is used to hide the delete button on touch devices
		$( "#list" ).removeClass( "touch" );

		// Click delete split-button to remove list item
		$( ".delete" ).on( "click", function() {
			var listitem = $( this ).parent( "li.ui-li" );

			confirmAndDelete( listitem );
		});
	}
	
};

function confirmAndDelete( listitem, transition ) {
	// Highlight the list item that will be removed
	listitem.addClass( "ui-btn-down-d" );
	// Inject topic in confirmation popup after removing any previous injected topics
	$( "#confirm .topic" ).remove();
	listitem.find( ".topic" ).clone().insertAfter( "#question" );
	// Show the confirmation popup
	$( "#confirm" ).popup( "open" );
	// Proceed when the user confirms
	$( "#confirm #yes" ).on( "click", function() {
		// Remove with a transition
		if ( transition ) {

			listitem
				// Remove the highlight
				.removeClass( "ui-btn-down-d" )
				// Add the class for the transition direction
				.addClass( transition )
				// When the transition is done...
				.on( "webkitTransitionEnd transitionend otransitionend", function() {
					// ...the list item will be removed
					listitem.remove();
					// ...the list will be refreshed and the temporary class for border styling removed
					$( "#list" ).listview( "refresh" ).find( ".ui-li.border" ).removeClass( "border" );
				})
				// During the transition the previous list item should get bottom border
				.prev( "li.ui-li" ).addClass( "border" );
		}
		// If it's not a touch device or the CSS transition isn't supported just remove the list item and refresh the list
		else {
			listitem.remove();
			$( "#list" ).listview( "refresh" );
		}
	});
	// Remove active state and unbind when the cancel button is clicked
	//删除todo 执行
	$( "#confirm #cancel" ).on( "click", function() {
		listitem.removeClass( "ui-btn-down-d" );
		$( "#confirm #yes" ).off();
	});
	
	

};
