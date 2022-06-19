<template>
  <div>
    <b-row class="mt-4 mb-4 text-center">
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="searchAir"
        ></b-form-select>
      </b-col>
    </b-row>
    <b-container v-if="airs && airs.length != 0" class="bv-example-row mt-3">
      <!-- <div>시간마다 미세먼지 수치가 갱신됩니다.</div> -->
      <b-table-simple>
        <b-thead head-variant="light">
          <b-tr>
            <b-th>구 이름</b-th>
            <b-th>시간</b-th>
            <b-th>미세먼지</b-th>
          </b-tr>
        </b-thead>
        <tbody>
          <air-list-item v-for="(air, index) in airs" :key="index" :air="air" />
        </tbody>
      </b-table-simple>
    </b-container>
    <b-container v-else class="bv-example-row mt-3">
      <b-row>
        <b-col><b-alert show>측정된 결과가 없습니다.</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
/* eslint-disable */
import AirListItem from "@/components/air/AirListItem.vue";
import { mapState, mapActions, mapMutations } from "vuex";

const airStore = "airStore";

export default {
  name: "AirList",
  components: {
    AirListItem,
  },
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  computed: {
    ...mapState(airStore, ["sidos", "guguns", "airs"]),
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
    ...mapActions(airStore, ["getSido", "getGugun", "getAirList"]),
    ...mapMutations(airStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    // sidoList() {
    //   this.getSido();
    // },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    searchAir() {
      if (this.gugunCode) this.getAirList(this.gugunCode);
      this.guguns.forEach((item) => {
        if (item.value == this.gugunCode) {
          this.getAirList(item.text);
        }
      });
    },
  },
};
</script>

<style></style>
