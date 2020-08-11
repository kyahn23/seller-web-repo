<template>
  <div id="app">
    <TopNavBar />
    <LeftNavBar />
    <router-view :key="$route.fullPath" />
    <Footer />
  </div>
</template>

<script>
import TopNavBar from '@/components/TopNavBar'
import LeftNavBar from '@/components/LeftNavBar'
import Footer from '@/components/Footer'

export default {
  name: 'App',
  components: {
    TopNavBar,
    LeftNavBar,
    Footer
  }
}
</script>

<style>
#app {
}
</style>
