$(function () {

	//화면 확대축소
	if (!$('.zoomBtn').length) {
		var zoomLevel = 100;

		$('.zoomIn').click(function () {
			zoomPage(25, $(this));
			return false;
		});
		$('.zoomOut').click(function () {
			zoomPage(-25, $(this));
			return false;
		});
		$('.zoomReset').click(function () {
			zoomPage(0, $(this));
			return false;
		});

		function zoomPage(step, trigger) {
			if (zoomLevel >= 200 && step > 0) {
				alert('최대 200%까지 확대보기가 가능합니다. 더 이상 확대할 수 없습니다.');
				//$('.zoomReset').trigger('click');
				return;
			}
			if (zoomLevel <= 75 && step < 0) {
				alert('75%까지 화면 축소보기가 가능합니다. 더 이상 축소할 수 없습니다.');
				//$('.zoomReset').trigger('click');
				return;
			}

			if (step == 0) {
				zoomLevel = 100;
			} else {
				zoomLevel = zoomLevel + step;
			}

			if (jQuery.browser.mozilla) {
				$('body').css({
					transform: 'scale(' + (zoomLevel / 100) + ')',
					transformOrigin: '50% 0'
				});
			} else {
				//IE,크롬,사파리,오페라
				$('body').css({
					zoom: zoomLevel / 100
				});
			}

			//줌레벨에 따른 화면비율변경
			if (zoomLevel > 100) {
				$('body').css({
					width: (zoomLevel) + '%'
				});
			} else {
				$('body').css({
					width: '100%'
				});
			}
		}
	};

	//컨텐츠영역 기본사이즈 지정
	fixedHeight();
	$(window).resize(function () {
		fixedHeight();
	});

	//위로이동
	btnGoTop();

	//GNB
	if ($('#GNB').length) {
		$('#GNB').megaMenu();
	};

	//LNB
	if ($('.accordion').length) {
		$('.accordion').AccordionVertical();
	};

	//LNB 접고 펼치기
	if ($('.btnFold').length) {
		$('.btnFold').click(LNB_FoldUnfold);
	};

	//텝메뉴
	// if ( $('.tbMenuSub').length){
	// 	$('.tbMenuSub').each(function(){
	// 		$(this).tabMENU();
	// 	});
	// };

	// Place Holder
	$('input, textarea').placeholder({
		customClass: 'myPlaceholder'
	});

	//틀고정 테이블
	tblHEAD_Fixed();

	//테이블 셀 사이즈
	TBL_tblList();
	TBL_tblDataRow();

	//조회날짜선택
	DatePickerSET();

	//결과테이블 인쇄하기
	$('[id^=doPrint]').click(function (e) {
		e.preventDefault();
		$('body').attr('id', 'setPrint');
		//$(this).parents().find('.alignBox').addClass('printIt');
		$(this).parents().find('.tblBackground').addClass('printIt');
		window.print();
	});

	//영수증 인쇄하기
	receiptPRINT();

	//레이어(layerSizeL) 높이 조정
	//resizeLayerSizeL();

	//이용안내 레이어팝업
	//if($('#popLayerUseGuideLink').length){
	//	$('#popLayerUseGuideLink').popup();
	//}

	//사이트맵 회원권한메뉴(비로그인 상태) 링크삭제
	siteMapOnlyMember();

	//컨텐츠 접기 펼치기 슬라이드
	contentSlide();

	//셀렉트(페이징) 박스
	SelectBoxBasic();

	//제품추가 input(tr행추가)
	goodsAddRow();

});

/* ----- 제품추가 input(tr행추가) ----- */
function goodsAddRow() {
	$(function () {
		$('#btnGoodsAddRow').click(function () {
			var time = new Date().toLocaleTimeString();
			$('#goodsAddRow > tbody:first').before('<tr class="trInput"><td></td><td><input type="text" placeholder="제품명 입력"></td><td><input type="text" placeholder="제조사 입력"></td><td><input type="text" placeholder="제품 시리얼번호 입력"></td><td><input type="text" placeholder="자산관리번호 입력"></td><td><input type="text" placeholder="NFC 시리얼번호"></td></tr>');
			//.prepend(), .before(), .append()
		});
		// 삭제 사용안함 
		// $('#btn-delete-row').click(function() {
		//    $('#goodsAddRow > tbody:first > tr:first').remove();
		//  });

	});
}


/* ----- 셀렉트(페이징) 박스 ----- */
function SelectBoxBasic() {
	$(".SelectBoxBasic").each(function () {
		var SelectVal = $(this).children('input').val();
		var SelectDefault = $(this).children('ul').children("#" + SelectVal);
		// if (SelectVal) { // INPUT TRUE
		// 	if ($(SelectDefault).html()) {
		// 		$(this).children('.DefaultName').html($(SelectDefault).html());
		// 	} else {
		// 		$(this).children('.DefaultName').html("VALUE ERROR");
		// 		$(this).children('input').val("");
		// 	}
		// }
	});
	$(".SelectBoxBasic").click(function () {
		$(this).children('ul').css("width", $(".SelectBoxBasic").width() - 2 + "px");
		$(this).children('ul').toggle(0);
		//슬라이딩효과$(this).children('ul').slideToggle(100); 
	});
	$(".SelectBoxBasic").mouseleave(function () {
		$(this).children('ul').fadeOut(0);
	});
	$(".SelectBoxBasic ul li").click(function () {
		$(this).parent('ul').siblings('.DefaultName').html($(this).text());
		$(this).parent('ul').siblings('input').val($(this).attr('id'));
	});
}

/* ----- 컨텐츠 접기 펼치기 슬라이드 ----- */
function contentSlide() {
	$(".contentSlide>.slideToggle>.cTit").click(function () {
		$(this).parent().next(".slideTogglebox").slideToggle("fast");
		if ($(this).children().hasClass('icon-close')) {
			$(this).children().removeClass('icon-close');
		} else {
			$(this).children().addClass('icon-close');
		}
	});
}

/* ----- 컨텐츠영역 기본사이즈 지정 ----- */
function fixedHeight() {
	var winH = $(window).height();
	var topH = $('#header').outerHeight();
	var btmH = $('#footer').outerHeight();
	var containerH = winH - (topH + btmH);
	$('#container>.fixedWidth, #container .sectionRight, #container .contentWrap').css({
		minHeight: containerH
	});
}

/* ----- 서브메뉴 접고 펼치기 ----- */
function LNB_FoldUnfold() {
	var $boxWrapper = $('#container > .fixedWidth');
	var $leftBOX = $boxWrapper.find('.sectionLeft');
	var $rightBOX = $boxWrapper.find('.sectionRight');

	//원래상태로
	if ($boxWrapper.is('.stateWide')) {
		$(this).removeClass('hide');
		$boxWrapper.removeClass('stateWide');
		$leftBOX.animate({
			width: '200',
			opacity: '1'
		}, 300).find('.accordion, .leftSignBox').show();

		//우측컨텐츠를 감싸는 DIV 모두 원래 사이즈로
		$rightBOX.find('.onlyWideView').animate({
			width: '1040',
			paddingLeft: '0'
		}, 300);
		$rightBOX.find('.contentWrap').animate({
			width: '1060'
		}, 300);
		$rightBOX.animate({
			width: '1060'
		}, 300)
	}
	//왼쪽메뉴 숨기고,컨텐츠영역 넓힘
	else {
		$(this).addClass('hide');
		$boxWrapper.addClass('stateWide');
		$leftBOX.animate({
			width: '0',
			opacity: '0'
		}, 300).find('.accordion, .leftSignBox').hide();

		//우측컨텐츠를 감싸는 DIV를 동시에 움직여야함
		$rightBOX.find('.onlyWideView').animate({
			width: '1240',
			paddingLeft: '0'
		}, 300);
		$rightBOX.find('.contentWrap').animate({
			width: '1260'
		}, 300);
		$rightBOX.animate({
			width: '1260'
		}, 300);
	}
	return false;
}

/* ----- 틀고정 테이블 ----- */
function tblHEAD_Fixed() {
	if (!$('.tblBackground').length) return;
	$('.tBodyScroll').each(function () {
		$(this).scroll(function () {
			var xPoint = $(this).scrollLeft(); //divBodyScroll의 x좌표가 움직인 거리
			$(this).parent().find('.tHeadScroll').scrollLeft(xPoint); //가져온 x좌표를 divHeadScroll에 적용시켜 같이 움직일수 있도록 함
		});
	});
}

/* ----- 테이블 ----- */
function TBL_tblList() {
	if (!$('.tblList').length) return;

	//사파리 브라우저 셀 넓이 조정을 위해 사용함
	if ($.browser.safari) {
		$('.tblList tr>*').each(function (i) {
			var colwidth = $('.tblList').find('col').eq(i).width() + '%';
			$('.tblList').find('tr:first>*').eq(i).css('width', colwidth);
		});
	}

	//체크박스 클릭시 행선택
	$('.tblList').find('td>input[type=checkbox]').click(function () {
		if ($(this).is(':checked') == true) {
			$(this).parents('tr').addClass('checkedItem');
		}
		if ($(this).is(':checked') == false) {
			$(this).parents('tr').removeClass('checkedItem');
		}
	});

	//라디오박스 클릭시 행선택
	$('.tblList').find('td>input[type=radio]').click(function () {
		$(this).parents('tr').addClass('checkedItem').siblings('tr').removeClass('checkedItem');
	});

	//tr 클릭시(checkbox없는경우) 행선택
	$(".trClick tr").click(function () {
		$(this).siblings('tr').removeClass('checkedItem');
		$(this).addClass('checkedItem');
		//$(this).parents('tr').addClass('highlighted').siblings('tr').removeClass('highlighted');
		//$(this).closest("tr").siblings().removeClass("highlighted");
		//$(this).toggleClass("highlighted");
	});

}

function TBL_tblDataRow() {
	if (!$('.tblDataRow').length) return;

	//사파리 브라우저 셀 넓이 조정을 위해 사용함
	if ($.browser.safari) {
		$('.tblDataRow tr').each(function (i) {
			var colwidthTd = $('.tblDataRow').find('col').eq(i).width() + '%';
			$('.tblDataRow').find('tr:first>*').eq(i).css('width', colwidthTd);
		});
	}
}


/* ----- 조회날짜선택 ----- */
function DatePickerSET() {
	if (!$('.datePickerUI').length) return;

	var datePickerOptions = {
		dateFormat: 'yy-mm-dd',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		yearRange: 'c-1:c+0',
	};

	var startDatePickerOptions = {
		dateFormat: 'yy-mm-dd',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		yearRange: 'c-1:c+0',
		onClose: function (selectedDate) {
			$("#endDate").datepicker("option", "minDate", selectedDate);
		}
	};

	var endDatePickerOptions = {
		dateFormat: 'yy-mm-dd',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		yearRange: 'c-1:c+0',
		onClose: function (selectedDate) {
			$("#startDate").datepicker("option", "maxDate", selectedDate);
		}
	};

	var currentYear = (new Date()).getFullYear();
	var startYear = currentYear - 10;

	var monthPickerOptions = {
		startYear: startYear,
		finalYear: currentYear,
		pattern: 'yyyy-mm',
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
	};

	$('#startDate').datepicker(startDatePickerOptions);
	$('#endDate').datepicker(endDatePickerOptions);
	$('#selDate').datepicker(datePickerOptions);
	$('#visitDate').datepicker(datePickerOptions);
	$('#selYm').monthpicker(monthPickerOptions);

	$.widget("ui.datePickerBTN", {
		_init: function () {
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

	$.widget("ui.monthPickerBTN", {
		_init: function () {
			var $el = this.element;
			$el.datepicker(this.options);

			if (this.options && this.options.trigger) {
				$(this.options.trigger).bind("click", function () {
					$el.monthpicker("show");
					return false;
				});
			}
		}
	});

	$('#startDate').datePickerBTN({
		trigger: '#btnStartDate'
	});
	$('#endDate').datePickerBTN({
		trigger: '#btnFinishDate'
	});
	$('#selDate').datePickerBTN({
		trigger: '#btnSelDate'
	});
	$('#visitDate').datePickerBTN({
		trigger: '#btnVisitDate'
	});
	$('#selYm').monthPickerBTN({
		trigger: '#btnSelYm'
	});

	$("#startDate").datepicker("option", "dateFormat", "yy-mm-dd");
};



/* ----- 금액 자동 콤마 입력 ----- */
function cmaComma(obj) {
	var firstNum = obj.value.substring(0, 1); // 첫글자 확인 변수
	var strNum = /^[/,/,0,1,2,3,4,5,6,7,8,9,/]/; // 숫자와 , 만 가능
	var str = "" + obj.value.replace(/,/gi, ''); // 콤마 제거  
	var regx = new RegExp(/(-?\d+)(\d{3})/);
	var bExists = str.indexOf(".", 0);
	var strArr = str.split('.');

	if (!strNum.test(obj.value)) {
		alert("숫자만 입력하십시오.\n\n특수문자와 한글/영문은 사용할수 없습니다.");
		obj.value = 1;
		obj.focus();
		return false;
	}

	if ((firstNum < "0" || "9" < firstNum)) {
		alert("숫자만 입력하십시오.");
		obj.value = 1;
		obj.focus();
		return false;
	}

	while (regx.test(strArr[0])) {
		strArr[0] = strArr[0].replace(regx, "$1,$2");
	}
	if (bExists > -1) {
		obj.value = strArr[0] + "." + strArr[1];
	} else {
		obj.value = strArr[0];
	}
}
/*
function commaSplit(n) {// 콤마 나누는 부분
    var txtNumber = '' + n;
    var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
    var arrNumber = txtNumber.split('.');
    arrNumber[0] += '.';
    do {
        arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
    }
    while (rxSplit.test(arrNumber[0]));
    if(arrNumber.length > 1) {
        return arrNumber.join('');
    } else {
        return arrNumber[0].split('.')[0];
    }
}
 
function removeComma(n) {  // 콤마제거
    if ( typeof n == "undefined" || n == null || n == "" ) {
        return "";
    }
    var txtNumber = '' + n;
    return txtNumber.replace(/(,)/g, "");
}
*/

/* ----- 팝업 ----- */
function OpenWindow(url, intWidth, intHeight) {
	window.open(url, "_blank", "width=" + intWidth + ",height=" + intHeight + ",resizable=0,scrollbars=yes");
}

/* ----- 영수증 인쇄하기 ----- */
function receiptPRINT() {
	if (!$('#receiptPop').length) return;

	var $ReceiptWrap = $('#receiptPop');
	var $ReceiptBoxs = $ReceiptWrap.find('.printReceipt');
	var $ReceiptH = $ReceiptWrap.find('.printReceipt:first-child').height();

	//인쇄준비
	$('<style type=\"text\/css\">@media print{@page{size:portrait;}}<\/style>').appendTo('head');
	$ReceiptWrap.css({
		height: $ReceiptH
	});
	if ($ReceiptBoxs.length > 1) {
		$ReceiptBoxs.css({
			paddingBottom: '50px'
		});
		$ReceiptWrap.find('.printReceipt:last-child').css({
			paddingBottom: '0'
		});
	}

	//인쇄하기 버튼
	$('#printReseipt').click(function () {
		window.print();
	});
}

/* ----- 위로이동 ----- */
function btnGoTop() {
	if (!$('.btnGoTop').length) return;

	topBtn();

	function topBtn() {
		var browserWidth = $(window).width();
		var contentWidth = 1280;
		var leftPosition = contentWidth;
		$('.btnGoTop').css({
			left: leftPosition
		});
	};
	/*
	topBtn();
	function topBtn(){
		var browserWidth = $(window).width();
		var contentWidth = 1190;
		var leftSpace = 20;
		var leftPosition = (browserWidth - contentWidth)*1/2 + contentWidth + leftSpace;
		$('.btnGoTop').css({left: leftPosition});
	};
	*/
	$(window).on('resize', function () {
		topBtn();
	});

	$(window).scroll(function () {
		if ($(this).scrollTop() > 100) {
			$('.btnGoTop').fadeIn();
		} else {
			$('.btnGoTop').fadeOut();
		}
	});

	$('.btnGoTop').click(function () {
		$('html, body').animate({
			scrollTop: 0
		}, 300);
		return false;
	});
}

/* ----- 레이어(layerSizeL) 높이 조정 ----- 
function resizeLayerSizeL(){
	if(!$('.layerSizeL').length) return;
	
	var layerL_height = $(window).height()*.85;
	var layerL_scrollBox = (layerL_height - 170);
	
	$('.layerSizeL').css('height',layerL_height);
	
	var scrollDIV = $('.layerSizeL').find('.scrollBox');
	if($(scrollDIV).length){
		$(scrollDIV).css('height',layerL_scrollBox);
	}
}
*/
/* ----- 회원권한 메뉴(비로그인 상태 링크삭제)  ----- */
//GNB
function onlyMemberService() {
	var $memberServiceArea = $('.gnbBox').find('.onlyMemberService');
	var $serviceLink = $memberServiceArea.find('.dep2 a');

	if (!$memberServiceArea.length) return;
	$serviceLink.each(function () {
		$(this).replaceWith('<strong>' + $(this).html() + '</strong>');
	});
}
//Site map
function siteMapOnlyMember() {
	var $memberServiceArea = $('.menuGroup').find('.onlyMemberService');
	var $serviceLink = $memberServiceArea.find('li>a');

	if (!$memberServiceArea.length) return;
	$serviceLink.each(function () {
		$(this).replaceWith('<span>' + $(this).html() + '</span>');
	});
}

/* --------------------
 * 아코디언(LNB)
 * --------------------- */
(function ($) {
	$.fn.AccordionVertical = function () {
		return this.each(function () {

			var _this = $(this);
			var dep1 = _this.find('>li>a');
			var dep2 = _this.find('>li>ul>li>a');

			// .current로 메뉴 활성화
			if ($(_this.find('.current')).length) {
				$('.current').find('>ul').show();
				if ($('.current').parents('li>ul').length) {
					$('.current').closest('.depthBox').addClass('on');
					$('.current').parents('ul>li');
					$('.current').parents('ul>li').find('>ul').show();
				}
			}

			function subToggle() {
				var _this = $(this);
				_this.parent().siblings().find('>ul').slideUp(300);
				_this.parent().addClass('on');

				if (_this.siblings('ul').length) {
					if (_this.siblings('ul').is(':visible')) {
						_this.parent().removeClass('on');
						_this.siblings('ul').slideUp('fast');
					} else {
						_this.parent().siblings().removeClass('on');
						_this.siblings('ul').slideDown(300);
					}
					return false;
				} else {
					//서브메뉴 없음
					_this.parent().siblings().removeClass('on');
				}
			}

			$(dep1).click(subToggle);
			$(dep2).click(subToggle);
		});
	};
})(jQuery);

/* --------------------
 *  Loading Layer
 * --------------------- */
(function ($) {
	/*!
	 * Center-Loader PACKAGED v1.0.0
	 * http://plugins.rohitkhatri.com/center-loader/
	 * MIT License
	 * by Rohit Khatri
	 */
	$.fn.loader = function (action, spinner) {
		var action = action || 'show';
		if (action === 'show') {
			if (this.find('.loader').length == 0) {
				parent_position = this.css('position');
				this.css('position', 'relative');
				paddingTop = parseInt(this.css('padding-top'));
				paddingRight = parseInt(this.css('padding-right'));
				paddingBottom = parseInt(this.css('padding-bottom'));
				paddingLeft = parseInt(this.css('padding-left'));
				width = this.innerWidth();
				height = this.innerHeight();

				$loader = $('<div class="loader"></div>').css({
					'position': 'fixed',
					'top': 0,
					'left': 0,
					'width': '100%',
					'height': '100%',
					'z-index': 1000,
					'background-color': 'rgba(0,0,0,0.2)',
					'border-radius': ''
				});

				$loader.attr('parent_position', parent_position);

				$spinner = $(spinner).css({
					'position': 'absolute',
					'top': '50%',
					'left': '50%',
					'color': '#000',
					'margin-top': '-' + paddingTop + 'px',
					'margin-right': '-' + paddingRight + 'px',
					'margin-bottom': '-' + paddingBottom + 'px',
					'margin-left': '-' + paddingLeft + 'px'
				});

				$loader.html($spinner);
				this.prepend($loader);
				marginTop = $spinner.height() / 2;
				marginLeft = +$spinner.width() / 2;
				$spinner.css({
					'margin-top': '-' + marginTop + 'px',
					'margin-left': '-' + marginLeft + 'px'
				});
			}
		} else if (action === 'hide') {
			this.css('position', this.find('.loader').attr('parent_position'));
			this.find('.loader').remove();
		}
	};

})(jQuery);