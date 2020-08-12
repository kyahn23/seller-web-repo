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
            <img src="../assets/images/cmmn/hidden.png" class="ico-vendor" alt="아이콘" />방문개통관리
          </h2>
          <div class="pageLocation">
            <span>상담 관리</span>&gt;
            <strong>방문개통관리</strong>
          </div>
        </div>

        <div class="searchArea">
          <ul>
            <li class="txtSize2">
              <label>방문예정일</label>
              <input type="text" class="wDate" id placeholder="방문예정일 선택" value="2020-06-21" />
              <span class="datePickerUI">
                <input type="hidden" id="srchDate" title="방문예정일 선택" />
                <a href="#" class="btnCalender" id="btnSrchDate">
                  <em>조회 날짜 선택</em>
                </a>
              </span>
            </li>
            <li class="txtSize5">
              <label for="visit01">고객명</label>
              <input type="text" id="visit01" placeholder="고객명 입력" value />
            </li>
            <li class="txtSize3">
              <label for="visit02">등록자</label>
              <select id="visit02">
                <option value>전체</option>
                <option value>매장판매원1</option>
                <option value>매장판매원2</option>
                <option value>매장판매원3</option>
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
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">방문 대상 현황</div>
            <div class="cTitRight">
              <div class="SelectBoxBasic selectPaging">
                <input type="hidden" value="1page" />
                <div class="DefaultName">선택하세요</div>
                <ul>
                  <li id="1page">1 page</li>
                  <li id="2page">2 page</li>
                  <li id="3page">3 page</li>
                  <li id="4page">4 page</li>
                  <li id="5page">5 page</li>
                  <li id="6page">6 page</li>
                  <li id="7page">7 page</li>
                  <li id="8page">8 page</li>
                  <li id="9page">9 page</li>
                  <li id="10page">10 page</li>
                  <li id="11page">11 page</li>
                  <li id="12page">12 page</li>
                  <li id="13page">13 page</li>
                  <li id="14page">14 page</li>
                </ul>
              </div>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail slideTogglebox">
            <div class="tHeadScrollBG">
              <div class="tHeadScroll">
                <table class="tblList tHead">
                  <colgroup>
                    <col style="width:*" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:10%" />
                    <col style="width:*" />
                    <col style="width:*" />
                    <col style="width:10%" />
                  </colgroup>
                  <tbody>
                    <tr>
                      <td>방문예정일시</td>
                      <td>고객명</td>
                      <td>통신사</td>
                      <td>개통유형</td>
                      <td>상담 요금제</td>
                      <td>휴대폰</td>
                      <td>등록자</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="tBodyScroll">
              <table class="tblList tBody trClick">
                <caption>방문 대상 현황 정보</caption>
                <colgroup>
                  <col style="width:*" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:10%" />
                  <col style="width:*" />
                  <col style="width:*" />
                  <col style="width:10%" />
                </colgroup>
                <thead>
                  <tr>
                    <td>방문예정일시</td>
                    <td>고객명</td>
                    <td>통신사</td>
                    <td>개통유형</td>
                    <td>상담 요금제</td>
                    <td>휴대폰</td>
                    <td>등록자</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="alignC">2020-06-21 15:00</td>
                    <td class="alignC">홍길동</td>
                    <td class="alignC">SKT</td>
                    <td class="alignC">번호이동</td>
                    <td class="alignC">LTE 55</td>
                    <td class="alignC">갤럭시 S20</td>
                    <td class="alignC">매장판매원1</td>
                  </tr>
                  <tr class="checkedItem">
                    <td class="alignC">2020-06-21 15:10</td>
                    <td class="alignC">고길동</td>
                    <td class="alignC">KT</td>
                    <td class="alignC">번호이동</td>
                    <td class="alignC">LTE 56</td>
                    <td class="alignC">갤럭시 S20</td>
                    <td class="alignC">매장판매원2</td>
                  </tr>
                  <tr>
                    <td class="alignC">2020-06-21 15:20</td>
                    <td class="alignC">이효리</td>
                    <td class="alignC">LGU+</td>
                    <td class="alignC">기기변경</td>
                    <td class="alignC">5G 55</td>
                    <td class="alignC">갤럭시 S10</td>
                    <td class="alignC">매장판매원3</td>
                  </tr>
                  <tr>
                    <td class="alignC">2020-06-21 15:30</td>
                    <td class="alignC">삼순이</td>
                    <td class="alignC">LGU+</td>
                    <td class="alignC">기기변경</td>
                    <td class="alignC">5G 76</td>
                    <td class="alignC">갤럭시 S20 Plus</td>
                    <td class="alignC">매장판매원1</td>
                  </tr>
                  <tr>
                    <td class="alignC">2020-06-21 15:40</td>
                    <td class="alignC">아무개</td>
                    <td class="alignC">SKT</td>
                    <td class="alignC">신규가입</td>
                    <td class="alignC">5G 90</td>
                    <td class="alignC">갤럭시 S10 Plus</td>
                    <td class="alignC">매장판매원2</td>
                  </tr>
                  <!-- 결과없음 -->
                  <tr class="errorMessage">
                    <td colspan="6">
                      <div class="errorTxt">
                        <span>항목이 없습니다.</span>
                      </div>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- //content -->
        </div>
        <!-- //content group1 -->

        <!-- content group2 -->
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">상담 접수 상세정보</div>
            <div class="cTitRight">
              <!-- !!! 여기가 팝업 버튼 !!! -->
              <div id="btn2">
                <a
                  href="#popLayerServiceResultAdd"
                  class="btnM colorGreen popLayerServiceResultAdd_open"
                >
                  <span>개통결과 등록</span>
                </a>
              </div>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblDataRow mb15">
              <colgroup>
                <col style="width:140px" />
                <col style="width:*" />
                <col style="width:140px" />
                <col style="width:*" />
              </colgroup>
              <tbody>
                <tr>
                  <th>통신사 / 개통유형</th>
                  <td>KT / 번호이동</td>
                  <th>상담요금제</th>
                  <td>LTE 56</td>
                </tr>
                <tr>
                  <th>휴대폰</th>
                  <td colspan="3">갤럭시 S20</td>
                </tr>
              </tbody>
            </table>

            <table class="tblDataRow mb15">
              <colgroup>
                <col style="width:140px" />
                <col style="width:*" />
                <col style="width:140px" />
                <col style="width:*" />
              </colgroup>
              <tbody>
                <tr>
                  <th
                    colspan="2"
                    style="padding: 6px 10px 6px 12px; text-align: center; background: #ebf3fd;"
                  >접수 시 판매 정책</th>
                  <th
                    colspan="2"
                    style="padding: 6px 10px 6px 12px; text-align: center; background: #fdebed;"
                  >현재 판매 정책</th>
                </tr>
                <tr>
                  <th style="background: #ebf3fd;">출고가</th>
                  <td style="text-align: right;">1,248,000 원</td>
                  <th style="background: #fdebed;">출고가</th>
                  <td style="text-align: right;">1,248,000 원</td>
                </tr>
                <tr>
                  <th style="background: #ebf3fd;">공시지원금</th>
                  <td style="text-align: right;">400,000 원</td>
                  <th style="background: #fdebed;">공시지원금</th>
                  <td style="text-align: right;">400,000 원</td>
                </tr>
                <tr>
                  <th style="background: #ebf3fd;">추가지원금</th>
                  <td style="color: #ff0000; text-align: right;">300,000 원</td>
                  <th style="background: #fdebed;">추가지원금</th>
                  <td style="text-align: right;">400,000 원</td>
                </tr>
                <tr>
                  <th style="background: #ebf3fd;">실구매가</th>
                  <td style="color: #ff0000; text-align: right;">546,000 원</td>
                  <th style="background: #fdebed;">실구매가</th>
                  <td style="text-align: right;">446,000 원</td>
                </tr>
                <tr>
                  <th style="background: #ebf3fd;">부가서비스</th>
                  <td style="color: #ff0000; text-align: right;">있음</td>
                  <th style="background: #fdebed;">부가서비스</th>
                  <td style="text-align: right;">없음</td>
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
            <div class="cTit">상담결과정보</div>
            <div class="cTitRight">
              <div id="btn2">
                <a href="#" class="btnM colorSky">
                  <span>방문예정일시 변경</span>
                </a>
              </div>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblDataRow mb15">
              <colgroup>
                <col style="width:140px" />
                <col style="width:*" />
                <col style="width:140px" />
                <col style="width:*" />
              </colgroup>
              <tbody>
                <tr>
                  <th>고객명</th>
                  <td>고길동</td>
                  <th>상담결과</th>
                  <td>방문예정</td>
                </tr>
                <tr>
                  <th>고객연락처</th>
                  <td>010-1111-1110</td>
                  <th>
                    방문예정일시
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td>
                    <input type="text" class="wDate" id placeholder="방문예정일시 선택" value="2020-06-21" />
                    <span class="datePickerUI">
                      <input type="hidden" id="visitDate" title="방문예정일시 선택" />
                      <a href="#" class="btnCalender" id="btnVisitDate">
                        <em>방문예정일시 선택</em>
                      </a>
                    </span>
                    <input type="text" class="wDate" id placeholder="방문예정일시 선택" value="15 : 10" />
                  </td>
                </tr>
                <tr>
                  <th
                    colspan="2"
                    style="padding: 6px 10px 6px 12px; text-align: center; background: #ebf3fd;"
                  >고객요청사항</th>
                  <th
                    colspan="2"
                    style="padding: 6px 10px 6px 12px; text-align: center; background: #ebfdeb;"
                  >상담내용</th>
                </tr>
                <tr>
                  <td colspan="2">
                    고객이 요청한 내용이
                    출력됨
                    <br />
                    <br />
                    <br />
                    <br />
                    <br />
                  </td>
                  <td colspan="2">
                    갤럭시 S20 PLUS로 기기 변경 가능
                    <br />요금제 변경
                    가능
                    <br />
                    <br />
                    <br />
                    <br />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
        </div>
        <!-- //content group3 -->
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