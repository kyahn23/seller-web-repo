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
            <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />매장정보 관리
          </h2>
          <div class="pageLocation">
            <span>매장 관리</span>&gt;
            <strong>매장정보관리</strong>
          </div>
        </div>
        <!-- content group2 -->
        <div class="contentBlock">
          <div class="contentTitle">
            <div class="cTit">사업자 정보</div>
            <div class="cTitRight">
              <a href="#" class="btnM colorSky">
                <span>저장</span>
              </a>
            </div>
          </div>
          <!-- content -->
          <div class="contentDetail">
            <table class="tblDataRow mb15">
              <caption>기본정보</caption>
              <colgroup>
                <col style="width:140px" />
                <col style="width:*" />
                <col style="width:140px" />
                <col style="width:*" />
              </colgroup>
              <tbody>
                <tr>
                  <th>
                    회사명
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <input type="text" class="wPer" id placeholder="계약설명" value="ABC모바일" />
                  </td>
                </tr>
                <tr>
                  <th>
                    주소
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <input type="text" class="wZipcode" id placeholder="우편번호" value="11038" />
                    <a
                      href="#"
                      class="btnS colorWhite popLayerDaumPostcodeSrch_open"
                      onclick="layer_execDaumPostcode(); return false;"
                    >
                      <span>주소 검색</span>
                    </a>
                    <p class="br">
                      <input
                        type="text"
                        class="wAddr"
                        id
                        placeholder="주소 입력"
                        value="서울시 중구 서소문11길19"
                      />
                      <input
                        type="text"
                        class="wAddr"
                        id
                        placeholder="상세주소 입력"
                        value="정동, 배재정동빌딩 B동 10층"
                      />
                    </p>
                  </td>
                </tr>
                <!-- <tr>
												<th>담당자 이름<span class="icoMust"><em>필수</em></span></th>
												<td colspan="3">
													<input style="width: 100%;" type="text" id=""
														placeholder="담당자명을 입력해 주세요">
												</td>
												<th>담당자 연락처<span class="icoMust"><em>필수</em></span></th>
												<td><input style="width: 100%;" type="text" id=""
														placeholder="연락처를 입력해 주세요"></td>
                </tr>-->
                <tr>
                  <th>
                    사업자등록번호
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <input style="width: 33%;" type="text" id placeholder="000" />
                    <input style="width: 33%;" type="text" id placeholder="00" />
                    <input style="width: 33%;" type="text" id placeholder="00000" />
                  </td>
                </tr>
                <tr>
                  <th>
                    대표자명
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <input type="text" class="wPer" id placeholder="대표자명을 입력해주세요" />
                  </td>
                </tr>
                <tr>
                  <th>
                    업태/종목
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <input style="width: 49%;" type="text" id placeholder="업태명을 입력해주세요" />
                    /
                    <input
                      style="width: 49%;"
                      type="text"
                      id
                      placeholder="종목명을 입력해주세요"
                    />
                  </td>
                </tr>
                <tr>
                  <th>
                    증빙서류
                    <span class="icoMust">
                      <em>필수</em>
                    </span>
                  </th>
                  <td colspan="3">
                    <p class="desBr">신청 정보를 증명할 수 있는 사업자등록증 사본을 제출해 주세요</p>
                    <p class="br">
                      일반과세자(개인사업자)의 경우, 사업자등록증에 주민등록번호가 포함되어 있으면 뒷자리 7자리를
                      음영처리(masking)하여 제출 바랍니다.
                      <br />첨부파일은 육안으로 식별 가능해야 하며, 서류 검토 후 문제가
                      있을
                      경우 서비스 이용에 제한을 받을 수 있습니다.
                    </p>
                    <br />
                    <input type="text" id placeholder="우편번호" value="11038" />
                    <a href="#" class="btnS colorWhite">
                      <span>파일첨부</span>
                    </a>
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

<style lang="scss" scoped>
</style>