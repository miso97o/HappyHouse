<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-steelblue">
        <b-icon icon="person-lines-fill"></b-icon> Member Service
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
  name: "MemberView",
  components: {
    FooterBar,
    BlackNaviBar,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
