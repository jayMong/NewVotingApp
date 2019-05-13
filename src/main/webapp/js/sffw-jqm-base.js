$(document).bind('mobileinit', function(){
	$.extend($.mobile, {
		loadingMessage: '로딩중',
		pageLoadErrorMessage: '페이지 로딩 에러 발생',
		sessionExpiredMessage: '연결유지시간(30분)이 만료되었습니다.\n로그인화면으로 이동합니다.'
	});
});

$(window)
	.resize( function() {
		setDefaultPageConfByWidth();
	})
	.bind("orientationchange", function() {
		setDefaultPageConfByWidth();
	});
	
$(document).on('pagebeforeshow','div[data-role="page"]', function(e) {
		setDefaultPageConfByWidth();
	})
	.on('pageshow', 'div[data-role="page"]', function(e) {
		makeMenuSelected();
	});
	
function toggleMenu() {
	$('.content-secondary', $.mobile.activePage).toggle(); 
}

function addCommas(nStr) {
	if(isNaN(nStr) == true){
		return '0';
	}
    nStr += '';
    var x = nStr.split('.');
    var x1 = x[0];
    var x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    if((x1+x2) == ''){
    	return '0';
    }
    return x1 + x2;
}

function setDefaultPageConfByWidth() {
	// 1.toggleMenuByWidth
	// 2.setDefaultTransition
	var winwidth = $(window).width(), trans = null;
	if( winwidth >= 1000 ){
		$('.content-secondary').show(); 
		trans = "none";
	} else if(winwidth < 1000) {
		$('.content-secondary').hide(); 
		if( winwidth >= 650 ) {
			trans = "fade";
		} else {
			trans ="slide";
		}
	}
	$.mobile.defaultPageTransition = trans;
}

function getMonthTextWithIdx(year, month, idx) {
	var a = month + idx;
	if(a <= 0) {
		month = 12 + a;
		year--;
	} else if(a > 12){
		month = a - 12;
		year++;
	} else {
		month = a;
	}
	return year + '.' + month;
}

function adjustPageByWVParams($page, app_mng_id, app_svc_id) {

	$('div[data-role="header"]', $page).hide();
	
	// replace data-url-params to wv-provided-params
	var url = '';
	if(app_svc_id != null && app_svc_id.length > 0 && app_mng_id != null && app_mng_id.length > 0) {
		url = $page.attr('data-url').substr(0, $page.attr('data-url').indexOf('?')) + '?inWV=true&_appsvcid=' + app_svc_id + '&appsvcid=' + app_svc_id + '&appmngid=' + app_mng_id;
	} else if(app_mng_id != null && app_mng_id.length > 0){
		url = $page.attr('data-url') + '&inWV=true&appmngid=' + app_mng_id;
	} else {
		url = $page.attr('data-url') + '&inWV=true';
	}
	$page.attr('data-url', url);
	
	// replace menu-anchor-params to wv-provided-params
	$('div[data-role="content"] div[data-role="collapsible"] a', $page).each( function() {
		var $anchor = $(this);
		url = '';
		if($anchor.attr('href') != '#') {
			if(app_svc_id != null && app_svc_id.length > 0 && app_mng_id != null && app_mng_id.length > 0) {
				url = $anchor.attr('href').substr(0, $anchor.attr('href').indexOf('?')) + '?inWV=true&_appsvcid=' + app_svc_id + '&appsvcid=' + app_svc_id + '&appmngid=' + app_mng_id;
			} else if(app_mng_id != null && app_mng_id.length > 0){
				url = $anchor.attr('href') + '&inWV=true&appmngid=' + app_mng_id;
			} else {
				url = $anchor.attr('href') + '&inWV=true';
			}
			$anchor.attr('href', url);
		}
	});
}

function attachParamsToMenuAnchorHrefs($page, app_mng_id) {

	$page.attr('data-url', $page.attr('data-url') + '&appmngid=' + app_mng_id);
	$('div[data-role="content"] div[data-role="collapsible"] a', $page).each( function() {
		var $anchor = $(this);
		if($anchor.attr('href') != '#') {
			$anchor.attr('href', $anchor.attr('href') + '&appmngid=' + app_mng_id);
		}

	});
}

function makeMenuSelected() {

	$('.content-secondary div[data-role="collapsible"]', $.mobile.activePage).each( function() {
		var $collapsible = $(this), found = false;

		$('li', $collapsible).each( function() {
			var $li = $(this);
			$('a', $li).each( function() {
				var href = $(this).attr('href');
				if(href != '#') {
					if($.mobile.activePage.attr('data-url').indexOf(href) != -1) {
						$collapsible.trigger('expand');
						$li.attr("data-theme", "b").removeClass("ui-btn-up-e").addClass("ui-btn-up-b");
						found = true;
						return;
					}
				}
			});
			if(found) return;
		});
		if(found) return;
	});
}