<template>
  <b-row
    class="m-2"
    @click="selectHouse"
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <!-- <b-col cols="2" class="text-center align-self-center">
      <b-img
        thumbnail
        src="https://picsum.photos/250/250/?image=58"
        alt="Image 1"
      ></b-img>
    </b-col> -->
    <b-col cols="8" class="align-self-center">
      {{ house.aptName }}
    </b-col>
    <b-col
      ><b-button @click="$bvModal.show(house.aptName)"
        >상세보기</b-button
      ></b-col
    >
    <b-modal :id="house.aptName">
      <template #modal-title> 아파트 상세 </template>
      <b-row>
        <b-col>
          <b-alert show variant="primary"
            >아파트 이름 : {{ house.aptName }}
          </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="info">법정동 : {{ house.dongName }} </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="warning">지번주소 : {{ house.jibun }}</b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="success"
            >건축연도 : {{ house.buildYear }}년</b-alert
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="danger"
            >거래금액 :
            {{
              (parseInt(house.recentPrice.replace(",", "")) * 10000) | price
            }}원</b-alert
          >
        </b-col>
      </b-row>
    </b-modal>
  </b-row>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.isModalOpen = true;
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
