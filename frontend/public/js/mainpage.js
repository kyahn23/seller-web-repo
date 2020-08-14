$(function(){
	//topBtn();

	// 메인 퀵메뉴 마우스 오버 (이미지, 버튼 색상 변경)
	$(".quickIconDiv").mouseover(function() {
		$(this).addClass("over");
	});

	// 메인 퀵메뉴 마우스 오버 (이미지, 버튼 색상 변경)
	$(".quickIconDiv").mouseleave(function() {
		$(".quickIconDiv").removeClass("over");
	});

	// 탑 버튼 클릭
	$('.btnGoTop').click(function(){
		$('html, body').animate({ scrollTop:0 },300);
		return false;
	});

	if ($(".bondBuying_tab_req").hasClass("on")) {
		$(".subHeader .h2").text("매입실행");
	}

	// 고정 탑 아이콘 퀵 메뉴 마우스 오버
	$(".goTop .quickIcon").mouseover(function() {
		if ($(this).hasClass("pw")) {
			$(this).find("img").prop("src", "../images/cmmn/quickPW_blue.png");
		} else if ($(this).hasClass("stat")) {
			$(this).find("img").prop("src", "../images/cmmn/quickStat_blue.png");
		} else if ($(this).hasClass("reserve")) {
			$(this).find("img").prop("src", "../images/cmmn/quickReserve_blue.png");
		} else if ($(this).hasClass("history")) {
			$(this).find("img").prop("src", "../images/cmmn/quickHistory_blue.png");
		} else if ($(this).hasClass("buying")) {
			$(this).find("img").prop("src", "../images/cmmn/quickBuying_blue.png");
		} else if ($(this).hasClass("member")) {
			$(this).find("img").prop("src", "../images/cmmn/quickMember_blue.png");
		}

		$(this).addClass("on");
		//$(this).find("input").show();
	});

	// 고정 탑 아이콘 퀵 메뉴 마우스 오버 해제
	$(".goTop .quickIcon").mouseleave(function() {
		if ($(this).hasClass("pw")) {
			$(this).find("img").prop("src", "../images/cmmn/quickPW.png");
		} else if ($(this).hasClass("stat")) {
			$(this).find("img").prop("src", "../images/cmmn/quickStat.png");
		} else if ($(this).hasClass("reserve")) {
			$(this).find("img").prop("src", "../images/cmmn/quickReserve.png");
		} else if ($(this).hasClass("history")) {
			$(this).find("img").prop("src", "../images/cmmn/quickHistory.png");
		} else if ($(this).hasClass("buying")) {
			$(this).find("img").prop("src", "../images/cmmn/quickBuying.png");
		} else if ($(this).hasClass("member")) {
			$(this).find("img").prop("src", "../images/cmmn/quickMember.png");
		}

		$(this).removeClass("on");
		//$(this).find("input").hide();
	})

	// 매입신청, 매입예약 탭 메뉴 클릭
	// 업로드 시 주석
	$(".tbMenu li").click(function() {
		$(".guide_res").hide();
		var tabName = $(this).attr("data-target");
		$("input[type=checkbox]").prop("checked", false).parents("tr").removeClass("checkedItem");	// 탭 옮기면 체크했던 항목 전부 해제
		$(".tbMenu li").removeClass("on");
		$(this).addClass("on");
		$(".bondBuying_tab, .bondBuying_reserve_tab").removeClass("on");
		$(".bondBuying_tab_" + tabName + ", .bondBuying_tab_" + tabName).addClass("on");

		if ($(".bondBuying_tab_req").hasClass("on")) {
			$(".subHeader .h2").text("매입실행");

		} else if ($(".bondBuying_tab_res").hasClass("on")) {
			$(".subHeader .h2").text("결과확인");
		
		} else if ($(".bondBuying_reserve_tab.bondBuying_tab_reg").hasClass("on")) {
			$(".subHeader .h2").text("예약등록");

		} else if ($(".bondBuying_reserve_tab.bondBuying_tab_mng").hasClass("on")) {
			$(".subHeader .h2").text("예약실행");

		} else if ($(".bondBuying_cancel_tab.req").hasClass("on")) {
			$(".subHeader .h2").text("매입취소신청");

		} else if ($(".bondBuying_tab_cancel_req").hasClass("on")) {
			$(".subHeader .h2").text("매입취소 실행");

		} else if ($(".bondBuying_tab_cancel_res").hasClass("on")) {
			$(".subHeader .h2").text("취소 결과확인");

		} else {
			$(".subHeader .h2").text("");
		}
	});

	// 삭제 버튼 클릭 -> 체크한 목록 체크 해제
	$(".clearChkBox").click(function() {
		$(".tblList .checkedItem input[type=checkbox]").each(function() {
			$(this).attr("checked", false).parents("tr").removeClass("checkedItem");
		});
	});

	// 매입예약 -> 매입예약관리 탭 메뉴 클릭
	$(".tbMenu_sub li.tab_sub").click(function() {
		var tabName = $(this).attr("data-target");
		
		$(".tbMenu_sub li").removeClass("on");
		$(this).addClass("on");
		$(".tab_content_sub").hide();
		$(".reserve_" + tabName).fadeIn(300);
	});
	
	// 일별 통계 탭 클릭 
	$(".tbMenu_stat .tab_sub").click(function() {
		$(".tbMenu_stat .tab_sub").removeClass("on");
		$(this).addClass("on");

		var tabName = $(this).attr("data-target");

		$(".stat_daily_tab").hide();
		$(".stat_daily_" + tabName).fadeIn(300);
	});

	// 회원관리 -> 회원정보 수정 탭 클릭
	$(".tbMenuSub li").click(function() {
		$(".tbMenuSub li").removeClass("on");
		$(this).addClass("on");

		var tabName = $(this).attr("data-target");

		$(".tbMenuSubContent").removeClass("on");
		// $(".tbMenuSubContent input").val('');
		// $(".tbMenuSubContent select").val('');
		//$(".tbMenuSubContent input[type=checkbox]").prop('checked', false);
		$("#tab_" + tabName).addClass("on");
	});

	// 개인정보처리(취급) 방침
	/* $(".personalInfoRule").click(function() {
		$('#popLayerPersonalInfoRule').popup("show");
	}); */

	// 메인화면 하단 아이콘 마우스 오버 시 아이콘 변경
	$(".mainLeftIcon").mouseover(function() {
		//$(".mainLeftIcon").removeClass("on");

		if ($(this).hasClass("icoPersonal")) {
			$(this).find("img").prop("src", "../images/main/footerl12_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoCompany")) {
			$(this).find("img").prop("src", "../images/main/footerl13_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoCard")) {
			$(this).find("img").prop("src", "../images/main/footerl14_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoFinance")) {
			$(this).find("img").prop("src", "../images/main/footerl21_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoFund")) {
			$(this).find("img").prop("src", "../images/main/footerl22_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoOwner")) {
			$(this).find("img").prop("src", "../images/main/footerl23_blue.jpg");
			$(this).addClass("on");
		} else if ($(this).hasClass("icoSales")) {
			$(this).find("img").prop("src", "../images/main/footerl24_blue.jpg");
			$(this).addClass("on");
		}
	});

	// 메인화면 하단 아이콘 마우스 커서 영역 이탈
	$(".mainLeftIcon").mouseleave(function() {
		if ($(this).hasClass("icoPersonal")) {
			$(this).find("img").prop("src", "../images/main/footerl12.jpg");
		} else if ($(this).hasClass("icoCompany")) {
			$(this).find("img").prop("src", "../images/main/footerl13.jpg");
		} else if ($(this).hasClass("icoCard")) {
			$(this).find("img").prop("src", "../images/main/footerl14.jpg");
		} else if ($(this).hasClass("icoFinance")) {
			$(this).find("img").prop("src", "../images/main/footerl21.jpg");
		} else if ($(this).hasClass("icoFund")) {
			$(this).find("img").prop("src", "../images/main/footerl22.jpg");
		} else if ($(this).hasClass("icoOwner")) {
			$(this).find("img").prop("src", "../images/main/footerl23.jpg");
		} else if ($(this).hasClass("icoSales")) {
			$(this).find("img").prop("src", "../images/main/footerl24.jpg");
		}
		$(".mainLeftIcon").removeClass("on");
	});
	
	// 비밀번호 90일 사용 변경 안내
	//$('#layerNoticeChgPw').popup("show");

	// 자동 로그아웃 안내
	//$('#layerNoticeAtLogout').popup("show");

	// 업무 영업시간 만료 안내
	//$('#layerNoticeSalesTime').popup("show");

	// 모바일 이용 안내
	if($('#layerNoticeDlApp').length){
		$('#layerNoticeDlApp').popup();
	}

	// 이용안내 레이어팝업
	if($('#popLayerUseGuideLink').length){
		$('#popLayerUseGuideLink').popup();
	}

	// 대량 등록 이용 가이드
	if($('#popLayerBlockUseGuideLink').length){
		$('#popLayerBlockUseGuideLink').popup();
	}

	// 매입 결과 이용 가이드
	if($('#popLayerResultUseGuideLink').length){
		$('#popLayerResultUseGuideLink').popup();
	}

	// 매입 결과 오류 확인
	if($('#popLayerResultError').length){
		$('#popLayerResultError').popup();
	}

	// 매입 신청 지점검색 영역 보임
	$(".showSearch").click(function() {
		$(".tblDataRow tr td:nth-child(1)").css("background-color", "#eee");
		$(".tblDataRow tr td:nth-child(2)").css("background-color", "white");
		$(".layerContainer.contentLeft .layerContent").css("width", "495px");
		$(".contentRight").show('slide', { direction: 'right' }, 200);
	});

	// 매입 신청 지점검색 영역 안보임
	/* $(".hideSearch").click(function() {
		$(".layerContainer.contentLeft .layerContent").css("width", "718px");
		$(".contentRight").hide('slide', { direction: 'right' }, 200);
	}); */
	
	$("#bondbuyModiPop .hideSearch").click(function() {
		$("#bondbuyModiPop .layerContainer.contentLeft .layerContent").css("width", "718px");
		$("#bondbuyModiPop .contentRight").hide('slide', { direction: 'right' }, 200);
	});
		
	$("#bondbuyAddPop .hideSearch").click(function() {
		$("#bondbuyAddPop .layerContainer.contentLeft .layerContent").css("width", "718px");
		$("#bondbuyAddPop .contentRight").hide('slide', { direction: 'right' }, 200);
	});
		
	// 매입신청 지점검색 결과 목록 중 선택
	$(".resultTable tr").click(function() {
		$(".resultTable tr").removeClass("on");
		$(this).addClass("on");
	});

	// 매입신청 팝업 수정 -> 비밀번호 찾기 안내
	$(".findPasswordInfo").click(function() {
		if ($(".fpi_detail").css("display") == "none") {
			$(".findPasswordInfo span").text("▲");
			$(this).children(".fpi_detail").slideDown();
		} else {
			$(".findPasswordInfo span").text("▼");
			$(this).children(".fpi_detail").slideUp();
		}
	});

	// 중복발행 거래 내역 안내 팝업
	//$("#popLayerDuplication").popup("show");

	// 결과확인 - 매입 조회기간 버튼 선택
	$(".periodBtns a").click(function() {
		$(".periodBtns a").removeClass("colorBlue");
		$(".periodBtns a").addClass("colorLight");
		$(this).removeClass("colorLight");
		$(this).addClass("colorBlue");
	});
	
	//화면 확대축소
	if(!$('.zoomBtn').length){
		var zoomLevel=100;
		
		$('.zoomIn').click(function() { zoomPage(25, $(this)); return false;});
		$('.zoomOut').click(function() { zoomPage(-25, $(this)); return false;});
		$('.zoomReset').click(function() { zoomPage(0, $(this)); return false;});
		
		function zoomPage(step, trigger){
			if(zoomLevel>=200 && step>0 ) { 
				alert('최대 200%까지 확대보기가 가능합니다. 더 이상 확대할 수 없습니다.');
				//$('.zoomReset').trigger('click');
				return;
			}

			if(zoomLevel<=75 && step<0) {
				alert('75%까지 화면 축소보기가 가능합니다. 더 이상 축소할 수 없습니다.');
				//$('.zoomReset').trigger('click');
				return;
			}

			if(step==0) {
				zoomLevel=100;
			} else {
				zoomLevel=zoomLevel+step;
			}
			
			if (jQuery.browser.mozilla){
				$('body').css({ transform: 'scale('+(zoomLevel/100)+')', transformOrigin: '50% 0'});
			} else {
				//IE,크롬,사파리,오페라
				$('body').css({zoom: zoomLevel/100});
			}
			
			//줌레벨에 따른 화면비율변경
			if(zoomLevel>100){
				$('body').css({ width:(zoomLevel)+'%'});
			} else {
				$('body').css({ width:'100%'});
			}
		}
	};
	
	// GNB 메뉴 펼침
	$(".topBoxHomeUtil ul").mouseover(function() {
		$("input[type=text]").blur();
		$(".topBoxGNB").slideDown(200);
	});

	// GNB 메뉴 접음
	$(".topMenuArea").mouseleave(function() {
		$(".topBoxHomeUtil li").css("color", "black");
		$(".topBoxGNB").slideUp(200);
	});

	// GNB 대메뉴 타이틀 마우스 오버 -> 색상 변경
	$(".GNB_title li").mouseover(function() {
		$(".GNB_title li").css("color", "black");
		$(this).css("color", "#0093e4");
	});

	// GNB 메뉴 항목 마우스 커서 오버 -> 항목 스타일 변경
	$(".topNaviSub li").mouseover(function() {
		var navOrder = $(this).parent("ul").attr("data-target");
		
		if ($(this).hasClass("light")) {
			$(".topBoxHomeUtil .GNB_title > ul li").css("color", "black");
			$(".topBoxHomeUtil li.subject" + navOrder).css("color", "#0093e4");
			
		} else {
			//$(".topBoxHomeUtil .GNB_title > ul li").css("color", "black");
			$(".topBoxHomeUtil li.subject" + navOrder).css("color", "#0093e4");
			$(this).find("a").css("color", "white");
		}
	});

	$(".topNaviSub li").mouseleave(function() {
		if ($(this).hasClass("light")) {
			
		} else {
			$(this).find("a").css("color", "black");
		}
	});

	// GNB 영역에서 마우스 커서 벗어남
	$("#GNB").mouseleave(function() {
		$(".GNB_title li").css("color", "black");
	});

	// GNB 활성화 후 닫기 버튼 클릭
	$(".btnGnbClose").click(function() {
		$(".topNaviSub li").trigger("mouseleave");
	});
	
	// LNB
	if( $('.accordion').length){
		$('.accordion').AccordionVertical();
	};
	
	// LNB 접고 펼치기
	if( $('.btnFold').length){
		$('.btnFold').click(LNB_FoldUnfold);
	};
	
	// 회원서비스 메뉴에 진입할 때 출금계좌 등록이 안되어있으면 보임
	//$("#popLayerSettingAccount").popup("show");
	
	// 서브 페이지 가이드 버튼 클릭 시 팝업 생성
//	$(".btnS.useGuide, .btnL, .btnM, .popGuide").click(function() {
//		$(".fpi_detail").hide();
//		var popName = $(this).attr("href").substring(1);
//		
//		$("#" + popName).popup("show");
//	});

//	// 확대/축소보기 안내 버튼 클릭
//	$(".zoomBtns").click(function() {
//		$('.zoomInfoLayer').popup("show");
//	});
//
//	// 확대/축소보기 안내 팝업 닫기
//	$(".popLayerZoomIn_close").click(function() {
//		$('.zoomInfoLayer').popup("hide");
//	});
	
	// 서브 페이지 이용 가이드 영역 펼치기 버튼 클릭
	$(".moreBtn span").click(function() {
		if ($(".infoBox .hide").css("display") == "block") {
			$(".infoBox .hide").slideUp(300);
			$(this).text("펼치기 ▼");
		} else {
			$(".infoBox .hide").slideDown(300);
			$(this).text("접기 ▲");	
		}
	});
	
	// 서브 페이지 이용 가이드 영역 펼치기 버튼 클릭
	// 업로드 시 주석
	/*$(".folder .moreBtn span").click(function() {
		if ($(".folder .infoBox .hide").css("display") == "block") {
			$(".folder .infoBox .hide").slideUp(300);
			$(this).text("펼치기 ▼");
		} else {
			$(".folder .infoBox .hide").slideDown(300);
			$(this).text("접기 ▲");	
		}
	});*/

		
	// 서브 페이지 이용 가이드 영역 펼치기 버튼 클릭
	// 업로드 시 주석
	/* $("#bondBuyingTabReq .moreBtn span").click(function() {
		if ($("#bondBuyingTabReq .infoBox .hide").css("display") == "block") {
			$("#bondBuyingTabReq .infoBox .hide").slideUp(300);
			$(this).text("펼치기 ▼");
		} else {
			$("#bondBuyingTabReq .infoBox .hide").slideDown(300);
			$(this).text("접기 ▲");	
		}
	});

	// 서브 페이지 이용 가이드 영역 펼치기 버튼 클릭
	$("#bondBuyingTabRes .moreBtn span").click(function() {
		if ($("#bondBuyingTabRes .infoBox .hide").css("display") == "block") {
			$("#bondBuyingTabRes .infoBox .hide").slideUp(300);
			$(this).text("펼치기 ▼");
		} else {
			$("#bondBuyingTabRes .infoBox .hide").slideDown(300);
			$(this).text("접기 ▲");	
		}
	}); */
	
	//틀고정 테이블
	tblHEAD_Fixed();
	
	//조회날짜선택
	DatePickerSET();
	
	//결과테이블 인쇄하기
	$('[id^=doPrint]').click(function(e){
		e.preventDefault();
		$('body').attr('id','setPrint');
		//$(this).parents().find('.alignBox').addClass('printIt');
		$(this).parents().find('.tblBackground').addClass('printIt');
		window.print();
	});

	// 체크박스 클릭시 행선택
	$('.tblList').find('td>input[type=checkbox]').click(function(){
		if($(this).is(':checked') == true){
			$(this).parents('tr').addClass('checkedItem');
		}
		if($(this).is(':checked') == false){
			$(this).parents('tr').removeClass('checkedItem');
		}
	});

	// 라디오박스 클릭시 행선택
	$('.tblList').find('td>input[type=radio]').click(function(){
		$(this).parents('tr').addClass('checkedItem').siblings('tr').removeClass('checkedItem');
	});

	//영수증 인쇄하기
	receiptPRINT();
	
	//레이어(layerSizeL) 높이 조정
	resizeLayerSizeL();

	// 월별통계 직접 입력 시 달력에 설정
	$(".periodSelect button").click(function() {
		var inputDate = $(this).parent(".datePickerUI").find("input");
		
		if (inputDate.attr("id") == "startMonth" || inputDate.attr("id") == "endMonth") {
			var inputYear = inputDate.val().substring(0, 4)
			var inputMonth = inputDate.val().substring(4, 6) - 1;

			inputDate.datepicker("setDate", new Date(inputYear, inputMonth, 1))
		} else {
			return false;
		}
	});

	// 직접 입력 시 날짜 맞도록 수정
	$("#startMonth, #endMonth").keyup(function() {
		if ($(this).val().length == 6) {
			var inputDate = $(this).parent(".datePickerUI").find("input");
			var inputYear = inputDate.val().substring(0, 4)
			var inputMonth = inputDate.val().substring(4, 6) - 1;

			inputDate.datepicker("setDate", new Date(inputYear, inputMonth, 1))
		}
	});
});

// 달력 오늘 버튼 클릭
$.datepicker._gotoToday = function(id) {
	var Ymd = new Date();
	var year = Ymd.getFullYear();
	var month = Ymd.getMonth() + 1;
	var date = Ymd.getDate();

	if (month < 10) {
		month = "0" + month;
	}

	if (date < 10) {
		date = "0" + date;
	}

	$(".ui-datepicker-close").trigger("click");
	$(id).val(year + "" + month + "" + date);
}

$(window).on("load", function() {
	$('.layerSizeL.new').css("height", "550px!important");
});

function goPage(page, target) {
	if (target == "blank") {
		window.open(page, '_blank');
	} else {
		$(location).attr("href", page);
	}
}

/* ----- 틀고정 테이블 ----- */
function tblHEAD_Fixed(){
	if( !$('.tblBackground').length ) return;
	$('.tBodyScroll').each(function(){
		$(this).scroll(function(){
			var xPoint = $(this).scrollLeft(); //divBodyScroll의 x좌표가 움직인 거리
			$(this).parent().find('.tHeadScroll').scrollLeft(xPoint); //가져온 x좌표를 divHeadScroll에 적용시켜 같이 움직일수 있도록 함
		});
	});
}

/* ----- 조회날짜선택 ----- */
function DatePickerSET() {
	if(!$('.datePickerUI').length) return;
	
	var thisDate = new Date();
	var thisYear = thisDate.getFullYear();

	var datePickerOptions = { dateFormat:'yymmdd', changeMonth:true, changeYear:true, showButtonPanel:true, yearRange: thisYear - 5 + ':' + thisYear, showOn: "button", buttonImage: "../images/cmmn/bg_ico_calender.png" };
	var monthPickerOptions = { dateFormat:'yymm', changeMonth:true, changeYear:true, showButtonPanel:true, yearRange: thisYear - 5 + ':' + thisYear, showOn: "button", buttonImage: "../images/cmmn/bg_ico_calender.png" };
	monthPickerOptions.closeText = "선택";

	monthPickerOptions.onClose = function(dateText, inst) {
		var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		
		$(this).datepicker("setDate", new Date(year, month, 1));
		$(this).change();
	}

	$('#startDate').datepicker(datePickerOptions);
	$('#endDate').datepicker(datePickerOptions);

	$('#startMonth').datepicker(monthPickerOptions);
	$('#endMonth').datepicker(monthPickerOptions);
	
	$.widget("ui.datePickerBTN", {
		_init: function() {
			var $el = this.element;
			$el.datepicker(this.options);
			
			if (this.options && this.options.trigger) {
				$(this.options.trigger).bind("click", function () {
					$el.datepicker("show");
					return false;
				});
			}
		}
	});

	$('#startDate').datePickerBTN({trigger:'#btnStartDate'});
	$('#endDate').datePickerBTN({trigger:'#btnFinishDate'});
}

/* ----- 팝업 ----- */
function OpenWindow(url,intWidth,intHeight){
	window.open(url,"_blank","width="+intWidth+",height="+intHeight+",resizable=0,scrollbars=yes");
}

/* ----- 영수증 인쇄하기 ----- */
function receiptPRINT() {
	if(!$('#receiptPop').length) return;
	
	var $ReceiptWrap = $('#receiptPop');
	var $ReceiptBoxs = $ReceiptWrap.find('.printReceipt');
	var $ReceiptH    = $ReceiptWrap.find('.printReceipt:first-child').height();
	
	//인쇄준비
	$('<style type=\"text\/css\">@media print{@page{size:portrait;}}<\/style>').appendTo('head');
	$ReceiptWrap.css({ height:$ReceiptH});
	if( $ReceiptBoxs.length > 1){
		$ReceiptBoxs.css({ paddingBottom:'50px'});
		$ReceiptWrap.find('.printReceipt:last-child').css({ paddingBottom:'0'});
	}
	
	//인쇄하기 버튼
	$('#printReseipt').click(function(){
		window.print();
	});
}

// 스크롤 내리면 탑 버튼 보임
// $(window).scroll(function(){
// 	if ($(this).scrollTop() > 0) {
// 		$('.goTop').fadeIn();
// 	} else {
// 		$('.goTop').fadeOut();
// 	}
// });

// 탑 버튼 클릭
/* function topBtn(){
	var browserWidth = $(window).width();
	var contentWidth = 1010;
	var leftSpace = 20;
	var leftPosition = (browserWidth - contentWidth)*1/2 + contentWidth + leftSpace;
	
	$('.btnGoTop').css({left: leftPosition});
}; */

/* ----- 레이어(layerSizeL) 높이 조정 ----- */
function resizeLayerSizeL(){
	if(!$('.layerSizeL').length) return;
	
	var layerL_height = $(window).height()*.8;
	var layerL_scrollBox = (layerL_height - 135);
	
	$('.layerSizeL').not(".new").css('height',layerL_height);
	
	var scrollDIV = $('.layerSizeL').find('.scrollBox');
	if($(scrollDIV).length){
		$(scrollDIV).css('height',layerL_scrollBox);
	}
}

/* ----- 패밀리사이트 ----- */
function popup(ob){
	if(ob.selectedIndex <= 0){ return false; }
	window.open(ob.options[ob.selectedIndex].value,'_blank','')
}