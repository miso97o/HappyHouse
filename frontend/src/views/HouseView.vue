<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="mt-3 text-center fluid">
      <h3 class="underline-orange">
        <b-icon icon="house-fill"></b-icon> House Service
      </h3>
      <b-row>
        <b-col>
          <house-search-bar></house-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="right">
          <kakao-map></kakao-map>
        </b-col>
        <b-col cols="6" align="left">
          <house-list />
        </b-col>
      </b-row>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import KakaoMap from "@/components/KakaoMap.vue";
import BlackNaviBar from "@/components/layout/HeaderNaviBarBlack.vue";
import FooterBar from "@/components/layout/FooterBar.vue";

export default {
  name: "HouseView",
  components: {
    HouseSearchBar,
    HouseList,
    KakaoMap,
    FooterBar,
    BlackNaviBar,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>
