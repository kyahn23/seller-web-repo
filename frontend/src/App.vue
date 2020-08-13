<template>
  <div id="app" class="wrapper">
    <div v-if="$route.fullPath === '/'">
      <MainPage />
    </div>
    <div v-else>
      <TopNavBar />

      <div id="container">
        <div class="fixedWidth">
          <LeftNavBar />
          <router-view :key="$route.fullPath" />
        </div>
      </div>
      <Footer />
    </div>
  </div>
</template>

<script>
import TopNavBar from "@/components/TopNavBar";
import LeftNavBar from "@/components/LeftNavBar";
import Footer from "@/components/Footer";
import MainPage from "@/views/main-page";

require("@/assets/css/cmmn/public.css");

require("@/assets/js/lib/jquery-1.10.2.min.js");
require("@/assets/js/lib/jquery-migrate-1.2.1.min.js");
require("@/assets/js/lib/jquery-ui.min.js");
require("@/assets/js/lib/jquery-ui-datepicker-ko.js");
require("@/assets/js/lib/jquery.mtz.monthpicker.js");
require("@/assets/js/lib/jquery.popupoverlay.js");
require("@/assets/js/lib/jquery.placeholder.min.js");
require("@/assets/js/public.js");

export default {
  name: "App",
  components: {
    MainPage,
    TopNavBar,
    LeftNavBar,
    Footer,
  },
};
</script>

<style scoped src="@/assets/css/cmmn/public.css">
</style>
<style scoped>
.fixedWidth {
  min-height: 890px;
}
</style>
