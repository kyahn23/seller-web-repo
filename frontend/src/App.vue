<template>
  <div id="app" class="wrapper">
    <TopNavBar />

    <div id="container">
      <div class="fixedWidth">
        <LeftNavBar />
        <!-- <router-view :key="$route.fullPath" /> -->
      </div>
    </div>
    <Footer />
  </div>
</template>

<script>
import TopNavBar from "@/components/TopNavBar";
import LeftNavBar from "@/components/LeftNavBar";
import Footer from "@/components/Footer";

require("@/assets/css/cmmn/public.css");

export default {
  name: "App",
  components: {
    TopNavBar,
    LeftNavBar,
    Footer,
  },
};
</script>

<style scoped src="@/assets/css/cmmn/public.css">
</style>
