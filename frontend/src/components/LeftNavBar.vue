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
              <router-link :to="{ name: 'service-status' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-user" alt="아이콘" />
                <span>상담관리현황</span>
              </router-link>
            </li>
            <li @click="ActivateItem(3)" :class="isActive[3]">
              <router-link :to="{ name: 'service-marketing' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-user" alt="아이콘" />
                <span>마케팅대상관리</span>
              </router-link>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>SKT 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(4)" :class="isActive[4]">
              <router-link :to="{ name: 'carr-mntrt', params:{carrier:'skt'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(5)" :class="isActive[5]">
              <router-link :to="{ name: 'carr-deal', params:{carrier:'skt'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </router-link>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>KT 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(6)" :class="isActive[6]">
              <router-link :to="{ name: 'carr-mntrt', params:{carrier:'kt'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(7)" :class="isActive[7]">
              <router-link :to="{ name: 'carr-deal', params:{carrier:'kt'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </router-link>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>LGU+ 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(8)" :class="isActive[8]">
              <router-link :to="{ name: 'carr-mntrt', params:{carrier:'lgu'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>요금제등록관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(9)" :class="isActive[9]">
              <router-link :to="{ name: 'carr-deal', params:{carrier:'lgu'} }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-list" alt="아이콘" />
                <span>판매정책관리</span>
              </router-link>
            </li>
          </ul>
        </li>
        <li class="depthBox">
          <a href="#" class="dep1">
            <span>매장 관리</span>
          </a>
          <ul>
            <li @click="ActivateItem(10)" :class="isActive[10]">
              <router-link :to="{ name: 'shop-manage' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-vendor" alt="아이콘" />
                <span>매장정보관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(11)" :class="isActive[11]">
              <router-link :to="{ name: 'shop-employees' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-vendor" alt="아이콘" />
                <span>직원관리</span>
              </router-link>
            </li>
            <li @click="ActivateItem(12)" :class="isActive[12]">
              <router-link :to="{ name: 'shop-notice' }">
                <img src="../assets/images/cmmn/hidden.png" class="ico-vendor" alt="아이콘" />
                <span>공지사항관리</span>
              </router-link>
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