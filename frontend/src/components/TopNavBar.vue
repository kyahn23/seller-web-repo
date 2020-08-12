<template>
  <!-- header -->
  <div class="header">
    <div class="fixedWidth">
      <h1 class="logo">
        <a href="main.html">
          <img src="../assets/images/cmmn/logo.png" alt="PentaWorks" style="height: 30px;" />
        </a>
      </h1>
      <div class="headerRight">
        <p class="hdTime">
          <em>09:59</em>
          <a href="#" class="btnExtension">연장하기</a>
        </p>
        <p class="hdUser">
          <em>(주)이티스트</em>님 안녕하세요!
        </p>
        <p class="hdLogout">
          <a href="login.html" onclick="javascript:RemaLogoutCall();" title="로그아웃">logout</a>
        </p>
      </div>
    </div>
  </div>
  <!-- //header -->
</template>

<script>
export default {};
</script>

<style scoped>
</style>