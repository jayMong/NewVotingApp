/**
 *	ui.js
 *	KTDS TMS - UI관련 스크립트 
 */
$(function(){
	/**
	 *	fe_gnb
	 */
	var fe_gnb = {
		init: function(){
			if( fe_gnb.settings.gnb.size() == 0 ){ return false; }
			
			this.settings.mDep1Cd	= fe_code.gnbDep1,	//GNB 1뎁스 메뉴코드
			this.settings.mDep2Cd	= fe_code.gnbDep2	//GNB 2뎁스 메뉴코드
			
			this.settings.dep1Li 	= fe_gnb.settings.gnb.find("> ul > li");
			this.settings.dep1LiA 	= fe_gnb.settings.gnb.find("> ul > li > a");
			
			this.resetMenu();
			this.actions();
		},
		settings: {
			gnb: $(".gnb")
		},
		actions: function(){
			this.settings.gnb.bind({
				mouseleave: function(){
					fe_gnb.resetMenu();
				}
			});
			
			this.settings.dep1LiA.bind({
				mouseenter: function(){
					var idx = $(this).parent().index();
					
					fe_gnb.settings.dep1Li.each(function(i){
						var $self = $(this);
						
						if( idx == i ){
							imgReplace( $self.find("> a > img"), "on" );
							$self.find("div").show();
						} else{
							imgReplace( $self.find("> a > img"), "off" );
							$self.find("div").hide();
						}
					});
				},
				focusin: function(){
					$(this).mouseenter();
				}
			});
		},
		resetMenu: function(){
			fe_gnb.settings.dep1Li.each(function(){
				var $self = $(this);
				
				if( $self.attr("mcode") == fe_gnb.settings.mDep1Cd ){
					imgReplace( $self.find("> a > img"), "on" );
					//$self.find("div").show();
					
					$self.find("li").each(function(){
						if( $(this).attr("mcode") == fe_gnb.settings.mDep2Cd ){
							$(this).removeClass("active").addClass("active");
						}
					});
				} else{
					imgReplace( $self.find("> a > img"), "off" );
					$self.find("div").hide();
				}
			});
		}
	}
	fe_gnb.init();
	
	/**
	 *	fe_tree
	 */
	var fe_tree = {
		init: function(){
			if( fe_tree.settings.tree.size() == 0 ){ return false; }
			
			this.settings.mDep1Cd	= fe_code.treeDep1,	//트리 1뎁스 메뉴코드
			this.settings.mDep2Cd	= fe_code.treeDep2,	//트리 2뎁스 메뉴코드
			this.settings.mDep3Cd	= fe_code.treeDep3,	//트리 3뎁스 메뉴코드
			this.settings.mDep4Cd	= fe_code.treeDep4	//트리 4뎁스 메뉴코드
			
			this.settings.dep1Li 	= fe_tree.settings.tree.find("> ul > li");
			this.settings.dep1LiA 	= fe_tree.settings.dep1Li.find("> a");
			this.settings.dep2Li 	= fe_tree.settings.dep1Li.find("> ul > li");
			this.settings.dep2LiA 	= fe_tree.settings.dep2Li.find("> a");
			this.settings.dep3Li 	= fe_tree.settings.dep2Li.find("> ul > li");
			this.settings.dep3LiA 	= fe_tree.settings.dep3Li.find("> a");
			this.settings.dep4Li 	= fe_tree.settings.dep3Li.find("> ul > li");
			this.settings.dep4LiA 	= fe_tree.settings.dep4Li.find("> a");
			
			this.resetMenu();
			this.actions();
		},
		settings: {
			tree: $(".tree")
		},
		actions: function(){
			this.settings.dep1LiA.bind({
				click: function(){
					var idx = $(this).parent().index();
					
					fe_tree.settings.dep1Li.each(function(i){
						var $self = $(this);
						
						if( idx == i ){
							if( !$self.hasClass("active") ){
								$self.addClass("active");
							}
							
							if( idx == fe_tree.settings.tree.find("> ul > li").size()-1 ){
								var mPos = (1000-(fe_tree.settings.tree.find("> ul > li").size() * fe_tree.settings.tree.find("> ul > li").height()))*-1;
								fe_tree.settings.tree.find("> ul").css({"background-position":"0 " + mPos+"px"});
							} else{
								fe_tree.settings.tree.find("> ul").css({"background-position":"0 100%"});
							}
						} else{
							$self.removeClass("active");
						}
					});
					
					//event.preventDefault();
				},
				focusin: function(){
					$(this).click();
				}
			});
			
			this.settings.dep2LiA.bind({
				click: function(event){
					fe_tree.settings.dep2Li.each(function(i){
						var $self = $(this);
						$self.removeClass("active");
					});
					$(this).parent().addClass("active");
					
					event.preventDefault();
				}
			});
		},
		resetMenu: function(){
			fe_tree.settings.dep1Li.each(function(){
				var $self = $(this);
				
				if( $self.attr("mcode") == fe_tree.settings.mDep1Cd ){
					if( !$self.hasClass("active") ){
						$self.addClass("active");
					}
					
					if( fe_tree.settings.tree.find("> ul > li.active").index() == fe_tree.settings.tree.find("> ul > li").size()-1 ){
						var mPos = (1000-(fe_tree.settings.tree.find("> ul > li").size() * fe_tree.settings.tree.find("> ul > li").height()))*-1;
						fe_tree.settings.tree.find("> ul").css({"background-position":"0 " + mPos+"px"});
					} else{
						fe_tree.settings.tree.find("> ul").css({"background-position":"0 100%"});
					}
					
					$self.find("li").each(function(){
						if( $(this).attr("mcode") == fe_tree.settings.mDep2Cd ){
							if( !$(this).hasClass("active") ){
								$(this).addClass("active");
							}
						}
					});
				} else{
					$self.removeClass("active");
				}
			});
		}
	}
	//fe_tree.init();
	
	/**
	 *	fe_datepicker
	 */
	var fe_datepicker = {
		init: function(){
			if( $(".datepicker").size() == 0 ){ return false; }
			$.datepicker.setDefaults( $.datepicker.regional[ "ko" ] );
			$(".datepicker").datepicker({
				showOn: "button",
				buttonImage: "../images/icon_calendar.gif",
				buttonImageOnly: true
			});
			$(".ui-datepicker-trigger").attr("title","날짜선택");
		}
	}
	fe_datepicker.init();
	
	/**
	 *	fe_resizeFrame
	 */
	var fe_resizeFrame = {
		init: function(){
			if( fe_resizeFrame.settings.validObj.size() == 0 ){ return false; }
			
			$(window).bind({
				resize: function(){
					fe_resizeFrame.excute();
				}
			});
			
			fe_resizeFrame.excute();
		},
		settings: {
			validObj: $(".gnb")
		},
		excute: function(){
			$(".container").css({ height: "auto" });
			$(".height").css({ height: "auto" });
			$(".tree").css({ height: "auto" });
				
			var wH = $(window).height();
			var hH = $(".header").height();
			
			var cM = parseInt($(".container").css("margin-top")) + parseInt($(".container").css("margin-bottom"));
			var cP = parseInt($(".container").css("padding-top")) + parseInt($(".container").css("padding-bottom"));
			var cH = $(".container").height() + cM + cP;
			
			var fM = parseInt($(".footer").css("margin-top")) + parseInt($(".footer").css("margin-bottom"));
			var fP = parseInt($(".footer").css("padding-top")) + parseInt($(".footer").css("padding-bottom"));
			var fH = $(".footer").height() + fM + fP;
			
			if( $(window).height() > hH+cH+fH ){
				$(".container").css({ height: wH-hH-fH-(cP+cM) });
				$(".content").css({ height: wH-hH-fH-(cP+cM)-50 });
				$(".tree").css({ height: wH-hH-fH-(cP+cM)-50-33 });
			}
		}
	}
	//fe_resizeFrame.init();
	
	/**
	 *	fe_tableHeaderFixed
	 */
	var fe_tableHeaderFixed = {
		init: function(){
			$(".tbl_list").each(function(){
				if( $(this).attr("autoscroll") == "true" ){
					var $self = $(this);
					var getClassName = $self.attr("class");
					var setHeight = "125"; //스크롤바 기본 높이
					var tblId, tblClass;
					
					if($(this).hasClass("tbl_list")){
						tblClass = "tbl_list";
						var isie7=(/msie 7/i).test(navigator.userAgent); //ie 7
						if( isie7 ){ //접속 브라우저가 ie7인 경우
							if( $(this).find("input[type=checkbox]").size() > 0 ){
								tblClass += " ie7fix";
							}
						}
					}
					
					
					getClassName = getClassName.split(" ");
					for(var i=0; i<getClassName.length; i++){
						if(fe_tableHeaderFixed.validClass(getClassName[i]) == true){
							setHeight = getClassName[i].replace("h","");
							setHeight += "px";
						}
					}
					
					var tblHead = $('<div class="tbl_head"><table class="' +tblClass+ '"></table></div>');
					var tblBody = $('<div class="tbl_body"><table class="' +tblClass+ '"></table></div>');
					
					//아이디가 있는경우 아이디 셋팅
					if($(this).attr("id") != ""){
						tblId = $self.attr("id");
						tblHead.find("table").attr("id",tblId);
					}
					
					if(setHeight != 0){
						tblBody.css("height",setHeight);
					}
					
					$self.after(tblBody);
					$self.after(tblHead);
					$self.find("colgroup").clone().appendTo(tblBody.find("table"));
					$self.find("tbody").appendTo(tblBody.find("table"));
					$self.find("colgroup, thead").appendTo(tblHead.find("table"));
					tblHead.find(".tbl_list").show();
					
					tblBody.find(".tbl_list tr").bind("click", function(){
						if( !$(this).hasClass("blank") ){
							$(this).addClass("active").siblings().removeClass("active");
						}
					});
					
					tblBody.find(".tbl_list").show();
					$self.remove();
				}
			});
		},
		validClass: function(val){
			var pattern = /^h+[0-9-\.]+$/;
			return (pattern.test(val));
		}
	}
	fe_tableHeaderFixed.init();
	
	/**
	 *	fe_checkAll
	 */
	var fe_checkAll = {
		init: function(){
			if( fe_checkAll.settings.checkAll.size() == 0 ){ return false; }
			
			fe_checkAll.actions();
		},
		settings: {
			checkAll	: $("table").find(".checkAll"),
			checkRow	: $("table").find(".checkRow"),
			btnCheckAll	: $(".selectAll"),
			btnRemoveAll: $(".removeAll")
		},
		actions: function(){
			//전체 선택 체크박스 클릭시
			fe_checkAll.settings.checkAll.each(function(){
				var $self = $(this);
				var $loopTr; //tbody의 tr
				
				if( $self.parents(".tbl_head").size() > 0 ){ //스크롤 있는 테이블
					$loopTr = $self.parents(".tbl_head").next(".tbl_body").find("tbody tr");
				} else{ //스크롤 없는 테이블
					$loopTr = $self.parents("thead").next("tbody").find("tr");
				}
				
				fe_checkAll.checkStatus($self, $loopTr);
				
				$self.bind({
					change: function(){
						var checkLen = 0; //목록 중 활성화 된 갯수 체크
						var checkVal;
						var title; //타이틀
						
						//체크된 항목이 있는지 검사
						$loopTr.each(function(){
							$(this).find(".checkRow").each(function(){
								if( $(this).attr("checked") == "checked" ){
									checkLen += 1;
								}
							});
						});
						
						if( checkLen > 0){ //전부 해제
							checkVal = false;
							title = "전부 체크";
						} else{ //전부 체크
							checkVal = true;
							title = "전부 해제";
						}
						
						$loopTr.each(function(){
							$(this).find(".checkRow").each(function(){
								$(this).attr("checked",checkVal);
							});
						});
						
						$self.attr({
							"checked"	: checkVal,
							"title"		: title
						});
					}
				});
				
				//개별 선택 체크박스를 클릭시
				$loopTr.find(".checkRow").each(function(){
					var $checkRow = $(this);
					
					$checkRow.bind({
						change: function(){
							if($(this).attr("multi") == "none"){
								$(this).parents("tr").siblings("tr").each(function(){
									$(this).find(".checkRow").attr("checked", false);
								});
							}
							fe_checkAll.checkStatus($self, $loopTr);
						}
					});
				});
			});
			
			//전체선택 버튼 클릭시
			fe_checkAll.settings.btnCheckAll.bind({
				click: function(event){
					event.preventDefault();
					
					var $table = $("#"+$(this).attr("pair"));
					var $loopTr; //tbody의 tr
					
					if( $table.parents(".tbl_head").size() > 0 ){ //스크롤 있는 테이블
						$loopTr = $table.parents(".tbl_head").next(".tbl_body").find("tbody tr");
					} else{ //스크롤 없는 테이블
						$loopTr = $table.find("tbody").find("tr");
					}
					
					var checkLen = 0; //목록 중 활성화 된 갯수 체크
					var checkVal;
					var title; //타이틀
					
					//체크된 항목이 있는지 검사
					$loopTr.each(function(){
						$(this).find(".checkRow").each(function(){
							if( $(this).attr("checked") == "checked" ){
								checkLen += 1;
							}
						});
					});
					
					if( checkLen == 0){
						$table.find(".checkAll").trigger("click");
					}
				}
			});
			
			//선택취소 버튼 클릭시
			fe_checkAll.settings.btnRemoveAll.bind({
				click: function(event){
					event.preventDefault();
					
					var $table = $("#"+$(this).attr("pair"));
					var $loopTr; //tbody의 tr
					
					if( $table.parents(".tbl_head").size() > 0 ){ //스크롤 있는 테이블
						$loopTr = $table.parents(".tbl_head").next(".tbl_body").find("tbody tr");
					} else{ //스크롤 없는 테이블
						$loopTr = $table.find("tbody").find("tr");
					}
					
					var checkLen = 0; //목록 중 활성화 된 갯수 체크
					var checkVal;
					var title; //타이틀
					
					//체크된 항목이 있는지 검사
					$loopTr.each(function(){
						$(this).find(".checkRow").each(function(){
							if( $(this).attr("checked") == "checked" ){
								checkLen += 1;
							}
						});
					});
					
					if( checkLen > 0){
						$table.find(".checkAll").trigger("click");
					}
				}
			});
		},
		checkStatus: function($obj, $loopTr){
			var checkLen = 0; //목록 중 활성화 된 갯수 체크
			var checkVal;
			var title; //타이틀
			
			//체크된 항목이 있는지 검사
			$loopTr.each(function(){
				$(this).find(".checkRow").each(function(){
					if( $(this).attr("checked") == "checked" ){
						checkLen += 1;
					}
				});
			});
			
			if( checkLen > 0){ //전부 해제
				checkVal = true;
				title = "전부 해제";
			} else{ //전부 체크
				checkVal = false;
				title = "전부 체크";
			}
			
			$obj.attr({
				"checked"	: checkVal,
				"title"		: title
			});
		}
	}
	fe_checkAll.init();
	
	/**
	 *	fe_setFocus
	 *	검색필드의 첫번째 입력항목에 자동으로 포커스를 이동시킨다.
	 *	단, 입력항목을 지정하고 싶으면 setfocus="true" 속성을 추가하면 됨.
	 */
	var fe_setFocus = {
		init: function(){
			if( fe_setFocus.settings.searchField.size() == 0 ){ return false; }
			
			fe_setFocus.actions();
		},
		settings: {
			searchField: $(".search_field").eq(0).find("td").eq(0)
		},
		actions: function(){
			if( $("input[setfocus=true]").size() > 0 || $("select[setfocus=true]").size() > 0 ){
				$("input[setfocus=true], select[setfocus=true]").focus();
			} else{
				var $s = fe_setFocus.settings.searchField;
				$s.find("input[type=text], select").focus();
			}
		}
	}
	fe_setFocus.init();
});

/**
 *	imgReplace
 *  이미지명 치환
 */
var imgReplace = function(obj,flag){
	var imgSrc = obj.attr("src");
	if(flag == "on"){
		imgSrc = imgSrc.replace("_off.","_on.");
	} else if(flag == "off"){
		imgSrc = imgSrc.replace("_on.","_off.");
	}
	obj.attr("src",imgSrc);
}


/**
 *	fe_popup
 *	@param a_str_windowURL,		// string	| URL
 *	@param a_str_windowName,	// string	| 윈도우 이름
 *	@param a_int_windowWidth,	// integer	| 윈도우 가로 사이즈
 *	@param a_int_windowHeight,	// integer	| 윈도우 세로 사이즈
 *	@param a_bool_scrollbars,	// boolean	| 스크롤바 유무
 *	사용예	: <a href="popup.html" target="_blank" onclick="newWindow(this.href,'popup',810,400,0); return false;">
 */
var fe_popup = function(a_str_windowURL, a_str_windowName, a_int_windowWidth, a_int_windowHeight, a_bool_scrollbars){
	var int_windowLeft 			= (screen.width		- a_int_windowWidth) 	/ 2;
	var int_windowTop 			= (screen.height 	- a_int_windowHeight) 	/ 2;
	var str_windowProperties 	= "height=" 		+ a_int_windowHeight
								+ ",width=" 		+ a_int_windowWidth
								+ ",top=" 			+ int_windowTop
								+ ",left=" 			+ int_windowLeft
								+ ",scrollbars="	+ a_bool_scrollbars
								+ ",resizable=" 	+ 0
								+ ",menubar=" 		+ 0
								+ ",toolbar=" 		+ 0
								+ ",location=" 		+ 0
								+ ",statusbar=" 	+ 0
								+ ",fullscreen=" 	+ 0 + "";
	var obj_window 				= window.open(a_str_windowURL, a_str_windowName, str_windowProperties);
	if(parseInt(navigator.appVersion) >= 4) {
		obj_window.window.focus();
	}
}
