<template>
  <!-- contents -->
  <div class="sectionRight" id="content">
    <div class="contentWrap">
      <div class="onlyWideView">
        <div class="subHeader">
          <div id="foldUnfold">
            <a href="#" class="btnFold">
              <span>좌측메뉴 접기/펴기</span>
            </a>
          </div>
          <h2 class="h2">
            <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />판매정책관리
          </h2>
          <div class="pageLocation">
            <span>{{$route.params.carrier.toUpperCase()}} 관리</span>&gt;
            <strong>판매정책관리</strong>
          </div>
        </div>

        <div class="searchArea">
          <ul>
            <li class="txtSize4">
              <label for="purchaes01">판매상태</label>
              <select id="purchaes01">
                <option value>전체</option>
                <option value>납품정보등록전</option>
                <option value>납품정보승인요청</option>
                <option value>납품정보승인반려</option>
              </select>
            </li>
            <li class="txtSize4">
              <label for="purchaes01">제조사</label>
              <select id="purchaes01">
                <option value>전체</option>
                <option value>납품정보등록전</option>
                <option value>납품정보승인요청</option>
                <option value>납품정보승인반려</option>
              </select>
            </li>
            <li class="txtSize3">
              <label for="purchaes03">모델명</label>
              <input type="text" id="purchaes04" placeholder="모델명 입력" />
            </li>
            <li class="txtSize4">
              <label for="purchaes01">통신유형</label>
              <select id="purchaes01">
                <option value>전체</option>
                <option value>납품정보등록전</option>
                <option value>납품정보승인요청</option>
                <option value>납품정보승인반려</option>
              </select>
            </li>
          </ul>
          <div class="btnSearch">
            <a href="#" class="btnM colorBlue">
              <span>검색</span>
            </a>
          </div>
        </div>

        <!-- content group1 -->
        <div class="contentBlock contentSlide">
          <div class="contentTitle slideToggle">
            <div class="cTit">
              제품 현황
              <span class="icon"></span>
            </div>
            <div class="cTitRight">
              <a href="#" class="btnM colorOrg">
                <span>판매중지</span>
              </a>
            </div>
          </div>
          <!-- slide content -->
          <div class="contentDetail slideTogglebox">
            <div class="tHeadScrollBG">
              <div class="tHeadScroll">
                <table class="tblList tHead">
                  <colgroup>
                    <col style="width:3%" />
                    <col style="width:7%" />
                    <col style="width:10%" />
                    <col style="width:12%" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:*" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th scope="col">
                        <input type="checkbox" title="행 선택" />
                      </th>
                      <th scope="col">판매상태</th>
                      <th scope="col">제조사</th>
                      <th scope="col">모델명</th>
                      <th scope="col">출고가</th>
                      <th scope="col">통신유형</th>
                      <th scope="col">용량</th>
                      <th scope="col">모델코드</th>
                      <th scope="col">등록자</th>
                      <th scope="col">최종변경일시</th>
                    </tr>
                  </thead>
                </table>
              </div>
            </div>
            <div class="tBodyScroll">
              <table class="tblList tBody trClick">
                <caption>납품정보등록 요청현황</caption>
                <colgroup>
                  <col style="width:3%" />
                  <col style="width:7%" />
                  <col style="width:10%" />
                  <col style="width:12%" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:*" />
                </colgroup>
                <thead>
                  <tr>
                    <th scope="col">
                      <input type="checkbox" title="행 선택" />
                    </th>
                    <th scope="col">판매상태</th>
                    <th scope="col">제조사</th>
                    <th scope="col">모델명</th>
                    <th scope="col">출고가</th>
                    <th scope="col">통신유형</th>
                    <th scope="col">용량</th>
                    <th scope="col">모델코드</th>
                    <th scope="col">등록자</th>
                    <th scope="col">최종변경일시</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">삼성전자</td>
                    <td class="alignC">갤럭시 노트20</td>
                    <td class="alignC">1,255,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">삼성전자</td>
                    <td class="alignC">갤럭시 S20</td>
                    <td class="alignC">1,165,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">삼성전자</td>
                    <td class="alignC">갤럭시 Z 폴드2</td>
                    <td class="alignC">2,189,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">256G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">LG전자</td>
                    <td class="alignC">LG V50</td>
                    <td class="alignC">955,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">LG전자</td>
                    <td class="alignC">LG VELVET</td>
                    <td class="alignC">890,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">64G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">APPLE</td>
                    <td class="alignC">아이폰11</td>
                    <td class="alignC">890,000</td>
                    <td class="alignC">LTE</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">APPLE</td>
                    <td class="alignC">아이폰11 PRO MAX</td>
                    <td class="alignC">1,190,000</td>
                    <td class="alignC">LTE</td>
                    <td class="alignC">256G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">APPLE</td>
                    <td class="alignC">아이폰 SE2</td>
                    <td class="alignC">590,000</td>
                    <td class="alignC">LTE</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">기타</td>
                    <td class="alignC">XIAOMI MI 10 LITE</td>
                    <td class="alignC">500,000</td>
                    <td class="alignC">5G</td>
                    <td class="alignC">128G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">판매중</td>
                    <td class="alignC">기타</td>
                    <td class="alignC">Redmi Note 9S</td>
                    <td class="alignC">375,000</td>
                    <td class="alignC">LTE</td>
                    <td class="alignC">64G</td>
                    <td class="alignC">SM-001</td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2020-01-01 12:20</td>
                  </tr>
                  <tr class="errorMessage">
                    <td colspan="10">
                      <div class="errorTxt">
                        <span>항목이 없습니다.</span>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- //slide content -->
        </div>
        <!-- //content group1 -->

        <!-- content group2 -->
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">번호이동정책</div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>번이정책 저장</span>
              </a>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblList alignC">
              <caption>납품 정보</caption>
              <colgroup>
                <col style="width:12%" />
                <col style="width:7%" />
                <col style="width:14%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:*" />
              </colgroup>
              <thead>
                <tr>
                  <th scope="col" rowspan="2">요금제</th>
                  <th scope="col" rowspan="2">월 기본요금</th>
                  <th colspan="3">공시지원금</th>
                  <th colspan="2">선택약정</th>
                  <th rowspan="2">부가서비스 정책</th>
                </tr>
                <tr>
                  <th scope="col">공시지원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>60,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                      value
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                      value
                    />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input style="width: 100%" class="wPer inNumber" type="text" id placeholder />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input style="width: 100%;" type="text" id />
                  </td>
                </tr>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>80,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                      value
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input style="width: 100%;" type="text" id />
                  </td>
                  <td>550,000</td>
                  <td>
                    <input style="width: 100%;" type="text" id />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
        </div>
        <!-- //content group2 -->

        <!-- content group3 -->
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">기기변경정책</div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>기변정책 저장</span>
              </a>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblList alignC">
              <caption>납품 정보</caption>
              <colgroup>
                <col style="width:12%" />
                <col style="width:7%" />
                <col style="width:14%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:*" />
              </colgroup>
              <thead>
                <tr>
                  <th colspan="5">공시지원금</th>
                  <th colspan="2">공시지원금</th>
                  <th rowspan="2">부가서비스 정책</th>
                </tr>
                <tr>
                  <th scope="col">요금제</th>
                  <th scope="col">월 기본요금</th>
                  <th scope="col">공시지원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>65,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input class="wPer inNumber" type="text" id placeholder />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input style="width: 100%" class="wS inNumber" type="text" id placeholder />
                  </td>
                </tr>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>110,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                      value
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%;"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>650,000</td>
                  <td>
                    <input class="wPer inNumber" type="text" id placeholder />
                  </td>
                  <td>550,000</td>
                  <td>
                    <input style="width: 100%" class="wS inNumber" type="text" id placeholder />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
        </div>
        <!-- //content group3 -->

        <!-- content group4 -->
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">신규가입정책</div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>신규정책 저장</span>
              </a>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblList alignC">
              <caption>납품 정보</caption>
              <colgroup>
                <col style="width:12%" />
                <col style="width:7%" />
                <col style="width:14%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:14%" />
                <col style="width:8%" />
                <col style="width:*" />
              </colgroup>
              <thead>
                <tr>
                  <th colspan="5">공시지원금</th>
                  <th colspan="2">공시지원금</th>
                  <th rowspan="2">부가서비스 정책</th>
                </tr>
                <tr>
                  <th scope="col">요금제</th>
                  <th scope="col">월 기본요금</th>
                  <th scope="col">공시지원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                  <th scope="col">추가지원금</th>
                  <th scope="col">할부원금</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>65,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input class="wPer inNumber" type="text" id placeholder />
                  </td>
                  <td>450,000</td>
                  <td>
                    <input style="width: 100%" class="wS inNumber" type="text" id placeholder />
                  </td>
                </tr>
                <tr>
                  <td class="alignC">ㅁㅁㅁㅁ요금제</td>
                  <td>110,000</td>
                  <td>
                    <input
                      style="width: 100%"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                      value
                    />
                  </td>
                  <td>
                    <input
                      style="width: 100%;"
                      class="inMoney"
                      type="text"
                      id
                      onkeyup="cmaComma(this);"
                      onchange="cmaComma(this);"
                    />
                  </td>
                  <td>650,000</td>
                  <td>
                    <input class="wPer inNumber" type="text" id placeholder />
                  </td>
                  <td>550,000</td>
                  <td>
                    <input style="width: 100%" class="wS inNumber" type="text" id placeholder />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
        </div>
        <!-- //content group4 -->
      </div>
    </div>
  </div>
  <!-- //contents -->
</template>

<script>
export default {};
</script>

<style scoped>
</style>