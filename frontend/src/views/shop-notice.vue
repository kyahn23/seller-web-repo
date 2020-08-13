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
            <img src="../assets/images/cmmn/hidden.png" class="ico-plus" alt="아이콘" />공지사항관리
          </h2>
          <div class="pageLocation">
            <span>매장 관리</span>&gt;
            <strong>공지사항관리</strong>
          </div>
        </div>

        <div class="searchArea">
          <ul>
            <li class="txtSize2">
              <label for="purchaes01">조회조건</label>
              <select id="purchaes01">
                <option value>제목</option>
                <option value>작성자</option>
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
            <div class="cTit">공지사항 현황</div>
            <div class="cTitRight">
              <a href="#popLayerNoticeAdd" class="btnM colorGreen popLayerNoticeAdd_open">
                <span>추가</span>
              </a>
              <a href="#" class="btnM colorOrg">
                <span>삭제</span>
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
                    <col style="width:*" />
                    <col style="width:12%" />
                    <col style="width:18%" />
                  </colgroup>
                  <tbody>
                    <tr>
                      <td scope="col">
                        <input type="checkbox" title="전체 선택" />
                      </td>
                      <td scope="col">제목</td>
                      <td scope="col">작성자</td>
                      <td scope="col">최종변경일시</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <div class="tBodyScroll">
              <table class="tblList tBody trClick">
                <caption>공지사항 현황</caption>
                <colgroup>
                  <col style="width:56px" />
                  <col style="width:*" />
                  <col style="width:12%" />
                  <col style="width:18%" />
                </colgroup>
                <thead>
                  <tr>
                    <td scope="col">
                      <input type="checkbox" title="전체 선택" />
                    </td>
                    <td scope="col">제목</td>
                    <td scope="col">작성자</td>
                    <td scope="col">최종변경일시</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">공지사항 제목1</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:00</td>
                  </tr>
                  <tr class="checkedItem">
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">공지사항 제목2</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:30</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">공지사항 제목3</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:10</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">공지사항 제목4</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:50</td>
                  </tr>
                  <tr>
                    <td class="alignC">
                      <input type="checkbox" title="행 선택" />
                    </td>
                    <td class="alignC">공지사항 제목5</td>
                    <td class="alignC">직원1</td>
                    <td class="alignC">2020-06-28 12:04</td>
                  </tr>
                  <!-- 결과없음 -->
                  <tr class="errorMessage">
                    <td colspan="4">
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
            <div class="cTit">공지사항 내용</div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>수정</span>
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
                  <th>제목</th>
                  <td colspan="3">
                    <input class="wPer" type="text" id placeholder="제목 기입" value="공지사항 제목2" />
                  </td>
                </tr>
                <tr>
                  <th style="color: #ff0000;">첨부파일</th>
                  <td>
                    <span style="width: 92%; text-decoration: underline; font-weight: bold;">
                      첨부파일
                      2020.01.20.pdf
                    </span>
                    <a href="#" class="btnS colorWhite">
                      <span>-</span>
                    </a>
                    <br />
                    <input class="wPer" style="width: 92% !important;" type="text" id value />
                    <a href="#" class="btnS colorWhite">
                      <span>+</span>
                    </a>
                  </td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <th>내용</th>
                  <td colspan="3" style="height: 100px">
                    <textarea style="width: 100%; height: 100%;">공지사항 내용 2....




</textarea>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <!-- //content -->
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