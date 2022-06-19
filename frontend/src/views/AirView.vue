<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-steelblue">
        <b-icon icon="cloud-haze"></b-icon> 미세먼지
      </h3>
      <b-row>
        <b-col>
          <air-list></air-list>
        </b-col>
      </b-row>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import AirList from "@/components/air/AirList.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import BlackNaviBar from "@/components/layout/HeaderNaviBarBlack.vue";

export default {
  name: "AirView",
  components: {
    AirList,
    FooterBar,
    BlackNaviBar,
  },
};
</script>
<!--어차피 나중에는 파일이 하나로 합쳐지기때문에 css를 어디에 선언하든 전역이 돼버림, 지역css를 사용하고 싶다면 scoped 라고 추가-->
<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(56, 233, 40, 0.3) 30%
  );
}
</style>
