<template>
  <div>
    <black-navi-bar></black-navi-bar>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-steelblue">
        <b-icon icon="newspaper"></b-icon> 부동산 뉴스
      </h3>
      <b-row>
        <b-col>
          <news-list></news-list>
        </b-col>
      </b-row>
    </b-container>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import NewsList from "@/components/news/NewsList.vue";
import { apiInstance } from "@/api/index";
import FooterBar from "@/components/layout/FooterBar.vue";
import BlackNaviBar from "@/components/layout/HeaderNaviBarBlack.vue";
const api = apiInstance();

export default {
  name: "NewsView",
  components: {
    NewsList,
    FooterBar,
    BlackNaviBar,
  },
  created() {
    api
      .get("/news")
      .then((res) => {
        console.log(res);
      })
      .catch((e) => console.log(e));
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
