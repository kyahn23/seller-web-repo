<template>
  <!-- left -->
  <div class="sectionLeft">
    <div class="lnbBgBox">
      <ul class="accordion" id="LNB">
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>상담 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(0)" :class="isActive[0]">
              <router-link :to="{ name: 'service-inbox' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-vendor" alt="아이콘" />
                <span>상담접수관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(1)" :class="isActive[1]">
              <router-link :to="{ name: 'service-visit' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-user" alt="아이콘" />
                <span>방문개통관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(2)" :class="isActive[2]">
              <a href="service-status.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-pw" alt="아이콘" />
                <span>상담관리현황</span>
              </a>
            </li>
            <li @click="ActivateItem(3)" :class="isActive[3]">
              <a href="service-marketing.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-pw" alt="아이콘" />
                <span>마케팅대상관리</span>
              </a>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>SKT 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(4)" :class="isActive[4]">
              <a href="carr-mntrt-manage.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </a>
            </li>
            <li @click="ActivateItem(5)" :class="isActive[5]">
              <a href="carr-deal-manage.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </a>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>KT 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(6)" :class="isActive[6]">
              <a href="#">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </a>
            </li>
            <li @click="ActivateItem(7)" :class="isActive[7]">
              <a href="#">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </a>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>LGU+ 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(8)" :class="isActive[8]">
              <a href="#">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </a>
            </li>
            <li @click="ActivateItem(9)" :class="isActive[9]">
              <a href="#">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </a>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>매장 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(10)" :class="isActive[10]">
              <a href="shop-manage.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-nfc" alt="아이콘" />
                <span>매장정보관리</span>
              </a>
            </li>
            <li @click="ActivateItem(11)" :class="isActive[11]">
              <a href="shop-employees.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-nfc2" alt="아이콘" />
                <span>직원관리</span>
              </a>
            </li>
            <li @click="ActivateItem(12)" :class="isActive[12]">
              <a href="shop-notice.html">
                <img src="../assets/images/cmmn/hidden.png" class="ico-nfc2" alt="아이콘" />
                <span>공지사항관리</span>
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
  <!-- //left -->
</template>

<script>
export default {
  data() {
    return {
      isActive: [
        { current: true },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
        { current: false },
      ],
    };
  },
  methods: {
    ActivateItem: function (n) {
      for (var i = 0; i < this.isActive.length; i++) {
        this.isActive[i].current = false;
      }
      this.isActive[n].current = !this.isActive[n].current;
    },
  },
};
</script>

<style scoped>
</style>