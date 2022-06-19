<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-hotpink">
        <b-icon icon="journal"></b-icon> 공지사항
      </h3>
      <router-view></router-view>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>
<script>
import FooterBar from "@/components/layout/FooterBar.vue";
import BlackNaviBar from "@/components/layout/HeaderNaviBarBlack.vue";

export default {
  name: "BoardView",
  components: {
    BlackNaviBar,
    FooterBar,
  },
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
