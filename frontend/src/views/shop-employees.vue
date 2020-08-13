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
            <img src="../assets/images/cmmn/hidden.png" class="ico-plus" alt="아이콘" />직원관리
          </h2>
          <div class="pageLocation">
            <span>매장 관리</span>&gt;
            <strong>직원관리</strong>
          </div>
        </div>

        <div class="searchArea">
          <ul>
            <li class="txtSize2">
              <label for="purchaes01">조회조건</label>
              <select id="purchaes01">
                <option value>직원명</option>
                <option value>아이디</option>
              </select>
            </li>
            <li class="txtSize2">
              <input type="text" id="purchaes02" placeholder="검색어 입력" value />
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
            <div class="cTit">직원 현황</div>
            <div class="cTitRight">
              <a href="#popLayerEmployeeAdd" class="btnM colorGreen popLayerEmployeeAdd_open">
                <span>직원추가</span>
              </a>
              <a href="#" class="btnM colorOrg">
                <span>퇴사처리</span>
              </a>
            </div>
          </div>
          <!-- slide content -->
          <div class="contentDetail slideTogglebox">
            <div class="tHeadScrollBG">
              <div class="tHeadScroll">
                <table class="tblList tHead">
                  <colgroup>
                    <col style="width:56px" />
                    <col style="width:12%" />
                    <col style="width:*" />
                    <col style="width:*" />
                    <col style="width:12%" />
                    <col style="width:*" />
                  </colgroup>
                  <tbody>
                    <tr>
                      <td scope="col">
                        <input type="checkbox" title="전체 선택" />
                      </td>
                      <td scope="col">직원명</td>
                      <td scope="col">아이디</td>
                      <td scope="col">회원권한</td>
                      <td scope="col">등록자</td>
                      <td scope="col">최종변경일시</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="tBodyScroll">
              <table class="tblList tBody trClick">
                <caption>납품 제품 사전 등록 현황</caption>
                <colgroup>
                  <col style="width:56px" />
                  <col style="width:12%" />
                  <col style="width:*" />
                  <col style="width:*" />
                  <col style="width:12%" />
                  <col style="width:*" />
                </colgroup>
                <thead>
                  <tr>
                    <td scope="col">
                      <input type="checkbox" title="전체 선택" />
                    </td>
                    <td scope="col">직원명</td>
                    <td scope="col">아이디</td>
                    <td scope="col">회원권한</td>
                    <td scope="col">등록자</td>
                    <td scope="col">최종변경일시</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">1111@company.com</td>
                    <td class="alignC">대표</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:00</td>
                  </tr>
                  <tr class="checkedItem">
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">직원2</td>
                    <td class="alignC">2222@company.com</td>
                    <td class="alignC">권한1</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:30</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">직원3</td>
                    <td class="alignC">3333@company.com</td>
                    <td class="alignC">권한2</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:10</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">직원4</td>
                    <td class="alignC">4444@company.com</td>
                    <td class="alignC">권한1</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:50</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">직원5</td>
                    <td class="alignC">5555@company.com</td>
                    <td class="alignC">권한1</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:04</td>
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
          <!-- //slide content -->
        </div>
        <!-- //content group1 -->

        <!-- content group2 -->
        <!-- 2016-08-18 : tr 행 추가 : id="clickPick / href="#clickPick" / id="goodsAddRow" 추가   -->
        <div class="contentBlock" id>
          <div class="contentTitle">
            <div class="cTit">직원정보수정</div>
          </div>
          <!-- 비밀번호 재발급 -->
          <div class="contentTitle" style="margin: 12px 0 8px 0; height: 25px;">
            <div class="cTitLeft" style="float: left; margin: 0 0 1px 0;">
              <a href="#" class="btnM colorBlue">
                <span>비밀번호 재발급</span>
              </a>
              <a href="#" class="btnM colorWhite">
                <span>레벨 권한 변경</span>
              </a>
            </div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>직원정보 수정</span>
              </a>
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
                  <th>
                    비밀번호 설정
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td>
                    <input class="wPer" type="text" id placeholder="숫자 6자리 기입" value />
                  </td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th>
                    비밀번호 확인
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td>
                    <input class="wPer" type="text" id placeholder="위와 동일하게 기입" value />
                  </td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
          <!-- //비밀번호 재발급 -->
          <!-- 레벨 권한 변경 -->
          <div class="contentTitle" style="margin: 12px 0 8px 0; height: 25px;">
            <div class="cTitLeft" style="float: left; margin: 0 0 1px 0;">
              <a href="#" class="btnM colorWhite">
                <span>비밀번호 재발급</span>
              </a>
              <a href="#" class="btnM colorBlue">
                <span>레벨 권한 변경</span>
              </a>
            </div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>직원정보 수정</span>
              </a>
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
                  <th>
                    회원 권한
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td>
                    <select id>
                      <option value>권한1</option>
                      <option value>권한2</option>
                      <option value>권한3</option>
                    </select>
                  </td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
          <!-- //레벨 권한 변경 -->
        </div>
        <!-- //content group2 -->
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