var g_cxt = '/seller-web/'
g_cxt = g_cxt.substring(0, g_cxt.length - 1)

$(function () {
    // 공통 레이어팝업 숨기기
    $('#layerNoticeCommon').popup("hide");

    $("body").click(function () {
        cf_initTimer();
    })

    // form 태그안에 input 필드에서 엔터 키 입력시 자동 submit 방지를 위한 로직
    $("form > input").keydown(function (event) {
        if (event.target.id !== "userId" && event.target.id !== "userPw" && event.keyCode === 13) {
            if (event.target.parentElement.tagName === "FORM") {
                $(event.target.parentElement).submit(function (e) {
                    e.preventDefault(e);
                })
            }
        }
    });
});

/*
 * ================= VueJs 공통설정 시작 =================
 */
Vue.mixin({
    created: function () {
        var el = this.$options.el;

        // 메인콘텐츠의 경우 vue 가 다 뜬다음 보여주도록 한다.
        var el_id = el.substring(1);
        if (document.getElementById(el_id).hasClass('main_contents')) {
            if (document.getElementById(el_id) != null) {
                document.getElementById(el_id).style.display = 'block'; // show
//				document.getElementById('myElement').style.display = 'none'; // hide
            }
        }
    },
    methods: {
        allCheckToggle: function (datalist) {

            var obj = event.target != undefined ? event.target : event.srcElement

            var chkval = obj.checked;
            for (var i = 0; i < datalist.length; i++) {
                datalist[i].chk = chkval;
            }
        },

    },
    directives: {
        /**
         * 처음 페이지가 열릴때 포커스가 선택되게 하는 directive
         */
        focus: {
            // 바인딩 된 엘리먼트가 DOM에 삽입되었을 때...
            inserted: function (el) {
                // 엘리먼트에 포커스를 줍니다
                el.focus()
            }
        },
        /**
         * 마우스가 선택될때 전체선택이 되도록 하는 directive
         */
        select: function (el, binding, vnode) {
            el.addEventListener('click', function (event) {
                this.select();
            }, false);
        },
        /**
         * 팝업을 열때 팝업의 Vue 인스턴스의 data 중 opener에 호출한 Vue 인스턴스를 넘기는 directive
         */
        popupopen: function (el, binding, vnode) {
            el.addEventListener('click', function (event) {
                var classList = this.className.replaceAll('\t', ' ').split(' ');
                var classname = "";
                for (var i = 0; i < classList.length; i++) {
                    classname = classList[i];
                    if (classname.length > 5 && classname.substring(classname.length - 5) === "_open") {
                        eval(classname.substring(0, classname.length - 5) + "_app.opener = vnode.context.$root");
                        break;
                    }
                }
            }, false);
        },
        /**
         * 한글만 입력이 되도록 하는 directive
         */
        hangle: function (el, binding, vnode) {

            var data = vnode.data.domProps.value;

            //한글만 입력가능
            data = data.replace(/[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g, "");

            var directives = vnode.data.directives;
            var dataexpression = "";
            for (var i = 0; i < directives.length; i++) {
                if (directives[i].name === "model") {
                    dataexpression = directives[i].expression;
                    break;
                }
            }
//		 	vnode.elm.value = data; // 이부분은 IE에서 '한글' 등의 단어를 입력할때 오류가 있어서 코멘트 처리함.
            eval("vnode.context.$root." + dataexpression + "=data;");
        },
        /**
         * 숫자만 formating 입력이 되도록 하는 directive
         */
        numform: function (el, binding, vnode) {

            var data = vnode.data.domProps.value;

            //숫자와 소수점만 입력가능
            data = data.replace(/[^-\.0-9]/g, "");

            var regx = new RegExp(/(-?\d+)(\d{3})/);
            var bExists = data.indexOf(".", 0);//0번째부터 .을 찾는다.
            var strArr = data.split('.');
            while (regx.test(strArr[0])) {//문자열에 정규식 특수문자가 포함되어 있는지 체크
                //정수 부분에만 콤마 달기
                strArr[0] = strArr[0].replace(regx, "$1,$2");//콤마추가하기
            }
            if (bExists > -1) {
                //. 소수점 문자열이 발견되지 않을 경우 -1 반환
                data = strArr[0] + "." + strArr[1];
            } else { //정수만 있을경우 //소수점 문자열 존재하면 양수 반환
                data = strArr[0];
            }

            var directives = vnode.data.directives;
            var dataexpression = "";
            for (var i = 0; i < directives.length; i++) {
                if (directives[i].name === "model") {
                    dataexpression = directives[i].expression;
                    break;
                }
            }
            vnode.elm.value = data;
            eval("vnode.context.$root." + dataexpression + "=data;");
        }
    }
});

/*
 * ================= VueJs 공통설정 끝 =================
 */

function cf_goAppPage(page) {
    var url = window.location.href;
    if (url.indexOf('/page/bondbuyResvPage') != -1) {
        if (app.bondbuyList.length > 0) {
            if ($('#popLayerResvNonBondBuy').length) {
                popLayerResvNonBondBuy_app.page = page;
                $('#popLayerResvNonBondBuy').popup("show");
            }
        } else {
            $(location).attr("href", g_cxt + page);
        }
    } else {
        $(location).attr("href", g_cxt + page);
    }

}

function cf_goExtpage(page) {
    var url = window.location.href;
    if (url.indexOf('/page/bondbuyResvPage') != -1) {
        if (app.bondbuyList.length > 0) {
            if ($('#popLayerResvNonBondBuy').length) {
                popLayerResvNonBondBuy_app.page = page;
                $('#popLayerResvNonBondBuy').popup("show");
            }
        } else {
            window.open(page, '_blank');
        }
    } else {
        window.open(page, '_blank');
    }
}

function cf_newTab(page) {
    window.open(g_cxt + page, '_blank');
}

function cf_newTabExtPage(page) {
    window.open(page, '_blank');
}

function cf_loadingbarShow() {
    $(".loadingDiv").show();
}

function cf_loadingbarHide() {
    $(".loadingDiv").hide();
}

function cf_nvl(obj, defval) {
    if (obj == null || obj == "null") return defval;
    return obj;
}

/**
 * ajax 통신함수
 *
 * @param url
 * @param param
 * @param callback
 * @returns
 */
var rsltFailArr = ['error', 'dev-error', 'FAIL'];

function cf_call(url, param, callback, options, isloadingbar) {

    if (cf_isEmpty(options)) options = {};

    if (isloadingbar !== false) {
        cf_loadingbarShow();
    }

    axios.post(g_cxt + url, param, options)
        .then(function (response) {
            cf_loadingbarHide();
//			console.log(response);
            if (rsltFailArr.includes(response.data.rsltStat)) {
                if (response.data.rsltStat == "dev-error" && !cf_isEmpty(response.data.errMsg)) {
                    alert(response.data.errMsg);
                } else {
                    alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
                }
            } else {
                if (callback != null) {
                    if (cf_whatIsIt(response.data) === "string" && response.data.indexOf("<!DOCTYPE html>") != -1) {
//						$(location).attr("href", g_cxt + "/");
                        alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
                    } else {
                        callback(response.data);
                    }
                }
            }
        })
        .catch(function (error) {
            cf_loadingbarHide();
            if (error.message == "Network Error") {
                alert("네트워크상태 또는 서버 구동상태를 확인해 주세요.");
            } else {
                alert("처리중 오류가 발생했습니다. \n관리자에게 문의하세요.");
            }
            console.log(error);
        });
}

/**
 * 파일 다운로드 함수
 * @param fileName
 * @returns
 */
function cf_downloadFile(fileName) {
    location.href = g_cxt + "/downloadFile?fileName=" + fileName;
}

function cf_excelDown(url, param) {

    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");
    form.setAttribute("action", g_cxt + url);

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "param");
    hiddenField.setAttribute("value", JSON.stringify(param));
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
    form.remove();
}

/**
 * 파일을 업로드를 동반한 호출함수
 * @param filesArr
 * @param param
 * @param callback
 * @returns
 */
function cf_callWithFiles(url, fileList, param, callback) {

    var formData = new FormData();
    for (var i = 0; i < fileList.length; i++) {
        formData.append("fileList", fileList[i]);
    }
    formData.append("param", JSON.stringify(param));

    $.ajax({
        url: g_cxt + url,
        dataType: "json",
        data: formData,
        type: 'POST',
        contentType: false,
        processData: false,
        beforeSend: function () {
            cf_loadingbarShow();
        },
        success: function (data, status) {
            cf_loadingbarHide();
            if (rsltFailArr.includes(data.rsltStat)) {
                if (data.rsltStat == "user-error" && !cf_isEmpty(data.errMsg)) {
                    alert(data.errMsg);
                } else {
                    alert("업로드를 실패했습니다. 관리자에게 문의하세요.");
                }
            } else {
                if (callback != null) {
                    callback(data);
                }
            }
        },
        error: function () {
            cf_loadingbarHide();
            alert("업로드를 실패했습니다. 관리자에게 문의하세요.");
        }
    })
}

/**
 * 엑셀에서 매핑키값과 적용함수를 기반으로 json 데이터를 추출하는 함수
 *
 * @param elid 엑셀업로드가 적용되어있는 태그의 ID
 * @param excelInfo
 * @param callback
 * @returns
 */
function cf_extractExcelData(elid, excelInfo, callback) {

    var tmpel = $("#" + elid);
    var tmp = tmpel.val();
    if (tmp === "") {
        return;
    }

    var extension = cf_getExtensionOfFilename(tmp);
    if (extension != "xls" && extension != "xlsx") {
        alert("엑셀 파일만 첨부해 주세요.");
        tmpel.val(null);
        return;
    }

    var formData = new FormData();
    formData.append("excel", tmpel[0].files[0]);
    formData.append("excelInfo", JSON.stringify(excelInfo));

    $.ajax({
        url: g_cxt + "/extractExcelData",
        dataType: "json",
        data: formData,
        type: 'POST',
        contentType: false,
        processData: false,
        beforeSend: function () {
            cf_loadingbarShow();
        },
        success: function (rsltdata, status) {
            cf_loadingbarHide();
            tmpel.val(null);

            var mappingInfo = excelInfo.mappingInfo;
            var keys = Object.keys(mappingInfo)
            var fn, col, val;
            for (var i = 0; i < keys.length; i++) {
                fn = mappingInfo[keys[i]].fn;
                if (!cf_isEmpty(fn)) {
                    col = mappingInfo[keys[i]].col;
                    for (var j = 0; j < rsltdata.length; j++) {
                        val = rsltdata[j][col];
                        eval(fn);
                        rsltdata[j][col] = val;
                    }
                }
            }

            callback(rsltdata);
        },
        error: function () {
            cf_loadingbarHide();
            tmpel.val(null);
            //에러발생을 위한 code페이지
            alert("업로드를 실패했습니다. 서버상태를 확인해 주세요.");
        }
    })
}

/**
 * jsonAryData 에서 chk 값이 1인 데이터만 추출해서 리턴
 *
 * @param jsonAryData
 * @returns
 */
function cf_getCheckedData(jsonAryData) {
    var rslt = []
    var chkval;
    var jsondata;
    for (var i = 0; i < jsonAryData.length; i++) {
        chkval = jsonAryData[i].chk;
        if (chkval == "1") {
            rslt.push(jsonAryData[i]);
        }
    }
    return rslt;
}

/**
 * 공통코드를 가져오는 함수
 *    var params = [
 *        {
 *			disCd : "LATE_STCD", // 마스터코드
 *			sortTarget : "disDtlCd", // 정렬기준을 설정함. "disDtlCd" 또는 "disDtlCdNm". defalut값은 "disDtlCd"
 *			isAscent : true,  // 정렬의 오름차순여부. defalut값은 true
 *			excludeCode : "2,3"  // 제외대상코드
 *		},
 *        {
 *			disCd : "MNU_AUTH",
 *			sortTarget : "disDtlCdNm",
 *			isAscent : false,
 *		},
 *        {
 *			disCd : "REQ_RSP_DSCD",
 *		},
 *    ]
 *
 */
function cf_getCmmnCd(params, callback) {
    cf_call("/getCmmnCd", params, callback, null, false);
}

/**
 *
 * 20자리의 랜덤한 uuid 를 생성하는 javascript 함수
 * 예) 20ab62835ddea19b3f8f
 *
 * @returns
 */
function cf_genUUID() {
    return cf_genrandom() + cf_genrandom() + cf_genrandom() + cf_genrandom() + cf_genrandom();
}

/**
 * 4자리의 랜덤한 영어및 숫자의 값을 생성한다.
 * 예) fbb1
 * @returns
 */
function cf_genrandom() {
    return ((1 + Math.random()) * 0x10000 | 0).toString(16).substring(1);
}

/**
 * 해당객체의 타입명을 반환해주는 함수
 * @param obj
 * @returns
 */
function cf_whatIsIt(obj) {
    var stringConstructor = "test".constructor;
    var numberConstructor = Number("123").constructor;
    var arrayConstructor = [].constructor;
    var objectConstructor = {}.constructor;

    if (obj === null) {
        return "null";
    } else if (obj === undefined) {
        return "undefined";
    } else if (obj.constructor === stringConstructor) {
        return "string";
    } else if (obj.constructor === numberConstructor) {
        if (isNaN(obj)) return "nothing";
        return "number";
    } else if (obj.constructor === arrayConstructor) {
        return "array";
    } else if (obj.constructor === objectConstructor) {
        return "object";
    } else {
        return "nothing";
    }
}

/**
 * json list 에서 조건에 해당하는 첫번째 인덱스를 가지고 온다.
 *
 *    var users = [
 *    { 'user': 'barney',  'active': false },
 *    { 'user': 'fred',    'active': false },
 *    { 'user': 'pebbles', 'active': true }
 *    ];
 *
 *    var rslt_index = findIndex(users, { 'user': 'fred', 'active': false });
 *    ===> rslt_index 값은 1
 *
 * @param p_jsonList
 * @param p_condition
 * @returns
 */
function cf_findFirstIndex(p_jsonList, p_condition) {

    if (p_condition == undefined || p_condition == null || p_condition.length == 0) {
        return -1;
    }

    var paramkeys = Object.keys(p_condition)
    var tmpcnt;
    for (var i = 0; i < p_jsonList.length; i++) {
        tmpcnt = 0;
        for (var j = 0; j < paramkeys.length; j++) {
            if (p_jsonList[i][paramkeys[j]] === p_condition[paramkeys[j]]) {
                tmpcnt++;
            }
        }
        if (tmpcnt === paramkeys.length) {
            return i;
        }
    }
    return -1;
}

/**
 * 배열값들을 순서대로 재배열하는 함수
 *
 * var strary = [ "티스토리", "Google", "#사람입니다.", "Ubah", "100", "25", "5", "40" ];
 * var intary = [ 2, 100, -23, 0, -1];
 *
 * rslt = cf_sortArray(strary, false);
 * ===> rslt 값은 [ "#사람입니다.", "100", "25", "40", "5", "Google", "Ubah", "티스토리"]
 *
 * rslt = cf_sortArray(intary, true);
 * ===> rslt 값은 [ 100, 2, 0, -1, -23]
 *
 * @param p_array
 * @param p_isDescent
 * @returns
 */
function cf_sortArray(p_array, p_isDescent) {
    return p_array.sort(function (a, b) {
        var comparison;
        if (a > b) {
            comparison = 1;
        } else if (a === b) {
            comparison = 0;
        } else {
            comparison = -1;
        }
        return p_isDescent == true ? comparison * -1 : comparison;
    });
}

/**
 * json list 를 해당 키의 값을 기준으로 정렬하는 함수
 *
 * var datas = [{ genre: 'Rap', band: 'Migos', albums: 2 }, { genre: 'Rock', band: 'Breaking Benjamins', albums: 1 }, { genre: 'Pop', band: 'Coldplay', albums: 4 }];
 *
 * var rslt = cf_sortJsonList(datas, 'band', true);
 * ==> rslt 값은 [{ genre: 'Rap', band: 'Migos', albums: 2 }, { genre: 'Pop', band: 'Coldplay', albums: 4 }, { genre: 'Rock', band: 'Breaking Benjamins', albums: 1 }];
 *
 * @param p_jsonList
 * @param p_key
 * @param p_isDescent
 * @returns
 */
function cf_sortJsonList(p_jsonList, p_key, p_isDescent) {
    p_jsonList.sort(function (a, b) {
        if (!a.hasOwnProperty(p_key) || !b.hasOwnProperty(p_key)) {
            return 0;
        }

        var varA = typeof a[p_key] === 'string' ? a[p_key].toUpperCase() : a[p_key];
        var varB = typeof b[p_key] === 'string' ? b[p_key].toUpperCase() : b[p_key];

        var comparison = 0;
        if (varA > varB) {
            comparison = 1;
        } else if (varA < varB) {
            comparison = -1;
        }
        return p_isDescent == true ? comparison * -1 : comparison;
    });
}

/**
 * RD레포트를 실행하는 함수
 *
 * @param url
 * @param param
 * @returns
 */
function cf_openRdReport(url, params) {
    window.open("", "reportTarget", "width=1130, height=860, toolbar=no, menubar=no, scrollbars=no, resizable=yes");

    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");
    form.setAttribute("action", g_cxt + url);
    form.setAttribute("target", "reportTarget");

    var hiddenField = document.createElement("input");
    hiddenField.setAttribute("type", "hidden");
    hiddenField.setAttribute("name", "params");
    hiddenField.setAttribute("value", JSON.stringify(params));
    form.appendChild(hiddenField);
    document.body.appendChild(form);
    form.submit();
    form.remove();
}

/**
 * 해당 파라메타가 비어있는지 확인
 *
 * @param obj
 * @returns
 */
function cf_isEmpty(obj) {
    var objtyp = cf_whatIsIt(obj);
    if (objtyp == "null") return true;
    else if (objtyp == "undefined") return true;
    else if (objtyp == "string" && obj == "") return true;
    else if (objtyp == "object" && obj == {}) return true;
    return false;
}

/**
 * 파일명에서 확장자명 추출
 * @param filename   파일명
 * @returns _fileExt 확장자명
 */
function cf_getExtensionOfFilename(filename) {

    var _fileLen = filename.length;

    /**
     * lastIndexOf('.')
     * 뒤에서부터 '.'의 위치를 찾기위한 함수
     * 검색 문자의 위치를 반환한다.
     * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
     */
    var _lastDot = filename.lastIndexOf('.');

    // 확장자 명만 추출한 후 소문자로 변경
    var _fileExt = filename.substring(_lastDot + 1, _fileLen).toLowerCase();

    return _fileExt;
}

//콤마찍기
function cf_numberFormat(num) {
    var pattern = /(-?[0-9]+)([0-9]{3})/;
    while (pattern.test(num)) {
        num = num.replace(pattern, "$1,$2");
    }
    return num;
}

//주민번호, 사업자번호, 채권번호 마스킹
function cf_maskFormat(str) {
    if (str.length == 10) {
        return str.substring(0, 3) + "-" + str.substring(3, 5) + "-*****";
    } else if (str.length == 13) {
        return str.substring(0, str.length - 7) + "-*******";
    } else if (str.length == 14) {
        return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 10) + "-" + str.substring(10, 14);
    }
    return "";
}

//콤마제거
function cf_unNumberFormat(num) {
    return (num.replace(/\,/g, ""));
}

var iMinute;
var iSecond;
var timerchecker = null;

function cf_startTimer() {
    cf_initTimer();
    cf_actTimer();
}

function cf_initTimer() {
    iMinute = 0
    9;  // 초기값 10분으로 초기화
    iSecond = iMinute * 60;
}

function cf_actTimer() {
    var rMinute = parseInt(iSecond / 60);
    var rSecond = iSecond % 60;
    if (iSecond > 0) {
        $("#timer").html((rMinute + "").lpad(2, "0") + " : " + (rSecond + "").lpad(2, "0"));
        iSecond--;
        timerchecker = setTimeout("cf_actTimer()", 1000); // 1초 간격으로 체크
        if (iSecond == 60) {  //1분 남았을 경우
            cf_oneminuteLogoutNoti();
        }
    } else {
        cf_logout();
    }
}

function cf_logout() {
    location.href = g_cxt + "/logout";
}

var noticeLayerBtn = "";

function cf_setNoticeLayerBtn(color, title, func) {
    noticeLayerBtn = "<a class='btnL " + color + " layerNoticeCommon_close' onclick=\"" + func + "\">"
        + "<span>" + title + "</span>"
        + "</a>";
    return noticeLayerBtn;
}

function cf_guide4MemberReg() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("회원가입 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("settingAccountWrapper");
    var noticeTxt = '<p class="popSubTitle">대표회원</p>'
        + '<p><b>·</b> 가까운 \'우리은행 영업점\'에서 국민주택채권 전용 홈페이지 이용 서비스 계약을 체결하시면 대표 회원으로 가입하실 수 있습니다.</p>'
        + '<p>&nbsp;</p>'
        + '<p class="popSubTitle">소속회원</p>'
        + '<p><b>·</b> 이용서비스 계약을 체결한 대표회원에게 회원 등록 요청을 하시면 소속회원으로 가입하실 수 있습니다.</p>';
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_guide4PwSearch() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("비밀번호 찾기 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("settingAccountWrapper");
    var noticeTxt = '<p class="popSubTitle">대표회원</p>'
        + '<p><b>·</b> 가까운 \'우리은행 영업점\'에서 비밀번호 재설정 가능합니다.</p>'
        + '<p>&nbsp;</p>'
        + '<p class="popSubTitle">소속회원</p>'
        + '<p><b>·</b> 소속 대표회원에게 비밀번호 재설정을 요청하셔야 합니다.</p>';
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_failLginDayCntOver() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("비밀번호 변경 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("chgPwWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p class='subj'><img src='" + g_cxt + "/resources/images/cmmn/exclam_mark.png'>비밀번호 90일 사용</h1></p>"
        + "<div class='subjLine'></div>"
        + "<p>&nbsp;</p>"
        + "<p>고객님의 비밀번호를 90일(3개월) 사용하였습니다.</p>"
        + "<p>안전한 금융거래를 위해서 비밀번호를</p>"
        + "<p>변경하시는 것을 추천드립니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(
        cf_setNoticeLayerBtn("colorBlue", "비밀번호 변경", "cf_goAppPage('/page/passwdChangePage')")
        + cf_setNoticeLayerBtn("colorLight", "다음에 변경", "")
    );
    $('#layerNoticeCommon').popup("show");
}

function cf_failLginAvlNo() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("로그인오류");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p>로그인이 불가합니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_failPwerrCntOverMst() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("비밀번호 오류");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("alertWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p>비밀번호를 5회 이상 잘못 입력하셨습니다.</p>"
        + "<p>가까운 '우리은행 영업점'에서 비밀번호 재설정 가능합니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_failPwerrCntOverSub() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("비밀번호 오류");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("alertWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p>비밀번호를 5회 이상 잘못 입력하셨습니다.</p>"
        + "<p>소속 대표회원에게 비밀번호 재설정 요청하셔야 합니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_failPwerr(pwerrCn) {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("비밀번호 오류");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("alertWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p>비밀번호를 " + pwerrCn + "회 잘못 입력하셨습니다.</p>"
        + "<p>5회 잘못 입력하실 경우 비밀번호 재설정이 필요합니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_failLgin() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("로그인오류");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("alertWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p>존재하지 않는 ID거나 비밀번호 오류입니다.</p>"
        + "<p>비밀번호는 반드시 암호 키패드를 통해 입력해야 합니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", "$('#userId').focus()"));
    $('#layerNoticeCommon').popup("show");
}

function cf_logoutByDuplLogin() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("자동 로그아웃 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("autoLogoutWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p class='subj'><img src='" + g_cxt + "/resources/images/cmmn/exclam_mark.png'>자동 로그아웃 안내</h1></p>"
        + "<p class='detail'><span class='colorTextRed'><b>로그아웃되었습니다.</b></span></p>"
        + "<div class='subjLine'></div>"
        + "<p>&nbsp;</p>"
        + "<p>다른사람의 동일아이디 로그인으로 인해 강제 로그아웃 되었습니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", ""));
    $('#layerNoticeCommon').popup("show");
}

function cf_oneminuteLogoutNoti() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeM");
    $("#noticeTlt").text("자동 로그아웃 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("autoLogoutWrapper");
    $("#noticeTxt").addClass("contentCenter");
    var noticeTxt = "<p class='subj'><img src='" + g_cxt + "/resources/images/cmmn/exclam_mark.png'>자동 로그아웃 안내</h1></p>"
        + "<p class='detail'><span class='colorTextRed'><b>[1분 남았습니다.]</b></span></p>"
        + "<div class='subjLine'></div>"
        + "<p>&nbsp;</p>"
        + "<p>고객님의 안전한 금융거래를 위해서 로그인 후</p>"
        + "<p>약 10분 동안 서비스 이용이 없으면 로그아웃 됩니다.</p>";
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorLight", "확인", ""));
    $('#layerNoticeCommon').popup("show");
}

function cf_zoomScreenGuide() {
    $('#layerNoticeCommon').removeClass();
    $('#layerNoticeCommon').addClass("layerWrapper");
    $('#layerNoticeCommon').addClass("layerSizeML");
    $('#layerNoticeCommon').addClass("zoomInfoLayer");
    $("#noticeTlt").text("확대/축소보기 안내");
    $("#noticeTxt").removeClass();
    $("#noticeTxt").addClass("guidanceWrapper");
    var noticeTxt = '<strong class="titleOne">확대/축소보기 안내</strong>'
        + '<div class="titleOneContent">'
        + '<p class="logoutTimer">다음과 같은 방법으로 화면의 확대/축소 기능을 이용하실 수 있습니다.</p>'
        + '</div>'
        + '<strong class="h3">화면확대</strong>'
        + '<p class="zoom"><img src="' + g_cxt + '/resources/images/cmmn/layer_zoomin.jpg" alt="키보드 \'ctrl\'키를 누른 상태로 \'+\'키를 누르시면 확대됩니다."></p>'
        + '<strong class="h3">화면축소</strong>'
        + '<p class="zoom"><img src="' + g_cxt + '/resources/images/cmmn/layer_zoomout.jpg" alt="키보드 \'ctrl\'키를 누른 상태로 \'-\'키를 누르시면 축소됩니다."></p>'
    $("#noticeTxt").html(noticeTxt);
    $("#layerNoticeBtnGroup").html(cf_setNoticeLayerBtn("colorBlue", "확인", ""));
    $('#layerNoticeCommon').popup("show");
}

function cf_dateFormate(datestr) {
    return datestr.substring(0, 4) + "-" + datestr.substring(4, 6) + "-" + datestr.substring(6);
}

function cf_gotoUrl(url, param) {
    var form = document.createElement("form");
    form.setAttribute("charset", "UTF-8");
    form.setAttribute("method", "Post");
    form.setAttribute("action", g_cxt + url);

    var paramkeys = Object.getOwnPropertyNames(param);
    for (var i = 0; i < paramkeys.length; i++) {
        var hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", paramkeys[i]);
        hiddenField.setAttribute("value", param[paramkeys[i]]);
        form.appendChild(hiddenField);
    }
    document.body.appendChild(form);
    form.submit();
}

/* ----- 팝업 ----- */
function cf_openWindow(url, intWidth, intHeight) {
    window.open(g_cxt + url, "_blank", "width=" + intWidth + ",height=" + intHeight + ",resizable=0,scrollbars=yes");
}


function fn_checkNumber(t) {
    if (t.value == "") {
        return;
    }
    for (var int = 0; int < t.value.length; int++) {
        if (!isNumber(t.value)) {
            alert("숫자만 입력가능합니다.");
            t.value = "";
        } else if (-1 != t.value.indexOf(".")) {
            alert("숫자만 입력 가능합니다.");
            t.value = "";
        }
    }
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

//////////////////////////////////// Custom Prototypes /////////////////////////////////////////

/**
 * 문자열이 대상 문자열과 동일한 값인지의 여부를 반환한다.
 *
 * @param tagetStr -
 *            비교대상 문자열
 * @return 동일값 여부
 */
String.prototype.eq = function (tagetStr) {
    return (tagetStr != null && typeof (tagetStr) != 'undefined' && this == tagetStr);
}

/**
 * 문자열의 byte 길이를 반환한다.
 *
 * @return 문자열의 byte 길이
 */
String.prototype.getByte = function () {
    var cnt = 0;
    for (var i = 0; i < this.length; i++) {
        if (this.charCodeAt(i) > 127) {
            cnt += 2;
        } else {
            cnt++;
        }
    }
    return cnt;
}

/**
 * 문자열이 지정한 최소길이 이상인지의 여부를 반환한다.
 *
 * @param minLen -
 *            최소길이
 * @return 최소길이 이상인지의 여부
 */
String.prototype.isMin = function (minLen) {
    return this.length >= minLen;
}

/**
 * 문자열이 지정한 최대길이 이하인지의 여부를 반환한다.
 *
 * @param maxLen -
 *            최대길이
 * @return 최대길이 이하인지의 여부
 */
String.prototype.isMax = function (maxLen) {
    return this.length <= maxLen;
}

/**
 * 문자열이 지정한 최소바이트수 이상인지의 여부를 반환한다.
 *
 * @param minByte -
 *            최소바이트수
 * @return 최소바이트수 이상인지의 여부
 */
String.prototype.isMinByte = function (minByte) {
    return this.getByte() >= minByte;
}

/**
 * 문자열이 지정한 최대바이트수 이하인지의 여부를 반환한다.
 *
 * @param maxByte -
 *            최대바이트수
 * @return 최대바이트수 이하인지의 여부
 */
String.prototype.isMaxByte = function (maxByte) {
    return this.getByte() <= maxByte;
}

/**
 * 문자열이 영문인경우 대문자로 치환한다.
 *
 * @return 대문자로 치환된 문자열
 */
String.prototype.upper = function () {
    return this.toUpperCase();
}

/**
 * 문자열이 영문인경우 소문자로 치환한다.
 *
 * @return 소문자로 치환된 문자열
 */
String.prototype.lower = function () {
    return this.toLowerCase();
}

/**
 * 문자열 좌우 공백을 제거한다.
 *
 * @return 좌우 공백 제거된 문자열
 */
String.prototype.trim = function () {
    return this.replace(/^\s+/g, '').replace(/\s+$/g, '');
}

/**
 * 문자열 좌 공백을 제거한다.
 *
 * @return 좌 공백 제거된 문자열
 */
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/, "");
}

/**
 * 문자열 우 공백을 제거한다.
 *
 * @return 우 공백 제거된 문자열
 */
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/, "");
}

/**
 * 문자열에서 모든 교체할 문자열을 대체 문자열로 치환한다.
 *
 * @param pattnStr -
 *            찾을 문자열
 * @param chngStr -
 *            대체 문자열
 * @return 치환된 문자열
 */
String.prototype.replaceAll = function (pattnStr, chngStr) {
    var retsult = "";
    var trimStr = this.replace(/(^\s*)|(\s*$)/g, "");

    if (trimStr && pattnStr != chngStr) {
        retsult = trimStr;
        while (retsult.indexOf(pattnStr) > -1) {
            retsult = retsult.replace(pattnStr, chngStr);
        }
    }
    return retsult;
}

/**
 * 문자열을 거꾸로 치환한다.
 *
 * @return 거꾸로 치환된 문자열
 */
String.prototype.reverse = function () {
    var result = '';

    for (var i = this.length - 1; i > -1; i--) {
        result += this.substring(i, i + 1);
    }
    return result;
}

/**
 * 지정한 길이만큼 원본 문자열 왼쪽에 패딩문자열을 채운다.
 *
 * @param len -
 *            채울 길이
 * @param padStr -
 *            채울 문자열
 * @return 채워진 문자열
 */
String.prototype.lpad = function (len, padStr) {
    var result = '';
    var loop = Number(len) - this.length;
    for (var i = 0; i < loop; i++) {
        result += padStr.toString();
    }
    return result + this;
}

/**
 * 지정한 길이만큼 원본 문자열 오른쪽에 패딩문자열을 채운다.
 *
 * @param len -
 *            채울 길이
 * @param padStr -
 *            채울 문자열
 * @return 채워진 문자열
 */
String.prototype.rpad = function (len, padStr) {

    var result = '';
    var loop = Number(len) - this.length;
    for (var i = 0; i < loop; i++) {
        result += padStr.toString();
    }
    return this + result;
}

/**
 * 문자열이 공백이나 널인지의 여부를 반환한다.
 *
 * @return 공백이나 널인지의 여부
 */
String.prototype.isBlank = function () {
    var str = this.trim();
    for (var i = 0; i < str.length; i++) {
        if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n")
            && (str.charAt(i) != "\r")) {
            return false;
        }
    }
    return true;
}

/**
 * 문자열이 영어만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 영어만으로 구성되어 있는지의 여부
 */
String.prototype.isEng = function (exceptChar) {
    return (/^[a-zA-Z]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 숫자와 영어만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 숫자와 영어만으로 구성되어 있는지의 여부
 */
String.prototype.isEngNum = function (exceptChar) {
    return (/^[0-9a-zA-Z]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isKor = function (exceptChar) {
    return (/^[가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 숫자와 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 숫자와 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isKorNum = function (exceptChar) {
    return (/^[0-9가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 문자열이 영문과 한글만으로 구성되어 있는지의 여부를 반환한다.
 *
 * @param exceptChar -
 *            추가 허용할 문자
 * @return 영문과 한글만으로 구성되어 있는지의 여부
 */
String.prototype.isEngKor = function (exceptChar) {
    return (/^[a-zA-Z가-힣]+$/).test(this.remove(exceptChar)) ? true : false;
}

/**
 * 이메일 주소의 유효성 여부를 반환한다.
 *
 * @return 유효성 여부
 */
String.prototype.isEmail = function () {
    return (/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/).test(this.trim());
}

/**
 * 전화번호의 유효성 여부를 반환한다.
 *
 * @param dlm -
 *            구분자(default : '-')
 * @return 유효성 여부
 */
String.prototype.isPhone = function (dlm) {
    var arg = dlm != null && typeof (dlm) != 'undefined' && dlm.neq('') ? dlm
        : '-';
    return eval("(/(02|0[3-9]{1}[0-9]{1})" + arg + "[1-9]{1}[0-9]{2,3}" + arg
        + "[0-9]{4}$/).test(this)");
}

/**
 * 휴대폰번호 유효성 여부를 반환한다.
 *
 * @param dlm -
 *            구분자(default : '-')
 * @return 유효성 여부
 */
String.prototype.isMobile = function (dlm) {
    var arg = dlm != null && typeof (dlm) != 'undefined' && dlm.neq('') ? dlm
        : '-';
    return eval("(/01[016789]" + arg + "[1-9]{1}[0-9]{2,3}" + arg
        + "[0-9]{4}$/).test(this)");
}

/**
 * 날짜의 유효성 여부를 반환한다.
 *
 * @return 유효성 여부
 */
String.prototype.isDate = function () {
    var result = false;
    if (this.length == 8 && this.isNum()) {
        var y = Number(this.substring(0, 4));
        var m = Number(this.substring(4, 6));
        var d = Number(this.substring(6, 8));
        var inDate = new Date(y, m - 1, d);
        result = (Number(inDate.getFullYear()) == y
            && Number(inDate.getMonth() + 1) == m && Number(inDate
                .getDate()) == d);
    }
    return result;
}

/**
 * 파일의 확장자를 구하여 반환한다.
 * @return 확장자
 */
String.prototype.ext = function () {
    return (this.indexOf(".") < 0) ? "" : this.substring(
        this.lastIndexOf(".") + 1, this.length);
}

/**
 * 해당 클래스명이 있는지 확인
 */
HTMLElement.prototype.hasClass = function (cls) {
    var i;
    var classes = this.className.replaceAll('\t', ' ').split(" ");
    for (i = 0; i < classes.length; i++) {
        if (classes[i] == cls) {
            return true;
        }
    }
    return false;
};

/**
 * 해당클래스명을 추가
 */
HTMLElement.prototype.addClass = function (add) {
    if (!this.hasClass(add)) {
        this.className = (this.className + " " + add).trim();
    }
};

/**
 * 해당클래스명을 삭제
 */
HTMLElement.prototype.removeClass = function (remove) {
    var newClassName = "";
    var i;
    var classes = this.className.replace(/\s{2,}/g, ' ').split(" ");
    for (i = 0; i < classes.length; i++) {
        if (classes[i] !== remove) {
            newClassName += classes[i] + " ";
        }
    }
    this.className = newClassName.trim();
};

HTMLElement.prototype.remove = function () {
    if (this.parentNode !== null) {
        this.parentNode.removeChild(this);
    }
}


// 0825 주석
// Array.prototype.removeElement = function (idx) {
//     this.splice(idx, 1);
// }
//
// Array.prototype.addElement = function (idx, el) {
//     this.splice(idx, 0, el);
// }
//
// Array.prototype.addArray = function (idx, arry) {
//     for (var i = 0; i < arry.length; i++) {
//         this.splice(idx + i, 0, arry[i]);
//     }
// }
//
// Array.prototype.setOrderNumbering = function (key) {
//     for (var i = 0; i < this.length; i++) {
//         eval("this[i]." + key + "= (i + 1 + '')");
//     }
// }

/**
 * ie에서 배열의 includes 함수가 지원이 안되는 관계로 재정의
 *
 * var arr = ['a', 'b', 'c'];
 * arr.includes('a', -100); // true
 * arr.includes('b', -100); // true
 * arr.includes('c', -100); // true
 * arr.includes('a', -2); // false
 */
Array.prototype.includes = function (searchElement, fromIndex) {
    if (this == null) {
        throw new TypeError('"this" is null or not defined');
    }

    // 1. Let O be ? ToObject(this value).
    var o = Object(this);

    // 2. Let len be ? ToLength(? Get(O, "length")).
    var len = o.length >>> 0;

    // 3. If len is 0, return false.
    if (len === 0) {
        return false;
    }

    // 4. Let n be ? ToInteger(fromIndex).
    //    (If fromIndex is undefined, this step produces the value 0.)
    var n = fromIndex | 0;

    // 5. If n ≥ 0, then
    //  a. Let k be n.
    // 6. Else n < 0,
    //  a. Let k be len + n.
    //  b. If k < 0, let k be 0.
    var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

    function sameValueZero(x, y) {
        return x === y
            || (typeof x === 'number' && typeof y === 'number' && isNaN(x) && isNaN(y));
    }

    // 7. Repeat, while k < len
    while (k < len) {
        // a. Let elementK be the result of ? Get(O, ! ToString(k)).
        // b. If SameValueZero(searchElement, elementK) is true, return true.
        if (sameValueZero(o[k], searchElement)) {
            return true;
        }
        // c. Increase k by 1.
        k++;
    }

    // 8. Return false
    return false;
}

