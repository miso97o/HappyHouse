<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-orange">
        <b-icon icon="shop"></b-icon> Market Service
      </h3>
      <b-row>
        <b-col>
          <market-list></market-list>
        </b-col>
      </b-row>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import MarketList from "@/components/market/MarketList.vue";
import FooterBar from "@/components/layout/FooterBar.vue";
import BlackNaviBar from "@/components/layout/HeaderNaviBarBlack.vue";

export default {
  name: "MarketView",
  components: {
    MarketList,
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
