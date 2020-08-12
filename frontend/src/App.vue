<template>
  <div id="app">
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

export default {
  name: "App",
  components: {
    TopNavBar,
    LeftNavBar,
    Footer,
  },
};
</script>

<style>
#app {
}
</style>
