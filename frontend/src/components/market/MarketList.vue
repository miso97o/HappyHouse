<template>
  <div>
    <b-row class="mt-4 mb-4" align-h="center">
      <b-col cols="3" class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col cols="3" class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col cols="3" class="sm-3">
        <b-form-select v-model="dongCode" :options="dongs"></b-form-select>
      </b-col>
      <b-col cols="1" class="sm-3">
        <b-button variant="primary" @click="searchMarket"
          ><b-icon icon="search"></b-icon
        ></b-button>
      </b-col>
      <!--searchMarket은 맨 아래에-->
    </b-row>
    <b-container
      v-if="markets && markets.length != 0"
      class="bv-example-row mt-3"
    >
      <b-row>
        <b-col>
          <kakao-map-market :market="markets"></kakao-map-market>
        </b-col>
        <b-col>
          <b-table-simple>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>종류</b-th>
                <b-th>이름</b-th>
                <b-th>동 이름</b-th>
              </b-tr>
            </b-thead>
            <tbody>
              <market-list-item
                v-for="(market, index) in markets"
                :key="index"
                :market="market"
              />
            </tbody>
          </b-table-simple>
        </b-col>
      </b-row>
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>해당 지역의 상가가 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
/* eslint-disable */
import MarketListItem from "@/components/market/MarketListItem.vue";
import { mapState, mapActions, mapMutations } from "vuex";
import KakaoMapMarket from "../KakaoMapMarket.vue";

const marketStore = "marketStore";

export default {
  name: "MarketList",
  components: {
    MarketListItem,
    KakaoMapMarket,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(marketStore, ["sidos", "guguns", "dongs", "markets"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(marketStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getMarketList",
    ]),
    ...mapMutations(marketStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
    ]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchMarket() {
      if (this.dongCode) this.getMarketList(this.dongCode);
    },
  },
};
</script>

<style></style>
