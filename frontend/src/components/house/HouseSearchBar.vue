<template>
  <b-container class="text-center">
    <b-row class="mt-4 mb-4" align-h="center">
      <!-- <b-col class="sm-3">
        <b-form-input
          v-model.trim="dongCode"
          placeholder="동코드 입력...(예 : 11110)"
          @keypress.enter="sendKeyword"
        ></b-form-input>
      </b-col>
      <b-col class="sm-3" align="left">
        <b-button variant="outline-primary" @click="sendKeyword">검색</b-button>
      </b-col> -->
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
      <b-col cols="2" class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="interestOptions"
          @change="searchApt"
        ></b-form-select>
      </b-col>
      <b-col cols="1" v-if="dongCode"
        ><b-button variant="danger" v-if="checked"
          ><b-icon icon="heart-fill" @click="removeInt"></b-icon
        ></b-button>
        <b-button v-else
          ><b-icon icon="heart" @click="addInt"></b-icon
        ></b-button>
      </b-col>
    </b-row>
    <b-row class="mb-4" align-h="center">
      <b-col cols="7" class="sm-3">
        <b-form-input
          v-model="aptName"
          placeholder="아파트 이름 검색"
        ></b-form-input>
      </b-col>
      <b-col cols="1" class="sm-3">
        <b-button variant="primary" @click="searchApt"
          ><b-icon icon="search"></b-icon
        ></b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

  modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";
const memberStore = "memberStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      aptName: "",
      checked: false,
      interestOptions: [{ value: null, text: "관심지역 선택" }],
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs", "houses"]),
    ...mapState(memberStore, ["interest"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },
  created() {
    // this.$store.dispatch("getSido");
    // this.sidoList();
    this.CLEAR_SIDO_LIST();
    this.getSido();
    this.getInterest(sessionStorage.getItem("access-token"));
    this.interest.forEach((item) => {
      this.interestOptions.push({ value: item.dongCode, text: item.dongName });
    });
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapActions(memberStore, ["addIntList", "removeIntList", "getInterest"]),
    ...mapMutations(houseStore, [
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
    searchApt() {
      if (this.dongCode) {
        this.getHouseList({ dong: this.dongCode, name: this.aptName });
        this.setChecked();
      }
    },
    setChecked() {
      for (let i = 0; i < this.interest.length; ++i) {
        if (this.interest[i].dongCode == this.dongCode) {
          this.checked = true;
          break;
        } else this.checked = false;
      }
    },
    async addInt() {
      if (!this.checked) {
        let params = {};
        this.dongs.forEach((item) => {
          if (item.value == this.dongCode) {
            params = { token: sessionStorage.getItem("access-token"), ...item };
          }
        });
        this.addIntList(params)
          .then((res) => {
            console.log(res);
            this.checked = true;
            alert("관심지역에 등록했습니다.");
          })
          .catch((err) => {
            console.log(err);
            alert("관심지역 등록중 문제가 발생하였습니다.");
          });
      }
    },
    async removeInt() {
      if (this.checked) {
        let params = {
          token: sessionStorage.getItem("access-token"),
          dongcode: this.dongCode,
        };
        this.removeIntList(params)
          .then((res) => {
            console.log(res);
            this.checked = false;
            alert("관심지역에서 삭제했습니다.");
          })
          .catch((err) => {
            console.log(err);
            alert("관심지역 삭제중 문제가 발생하였습니다.");
          });
      }
    },
  },
};
</script>

<style></style>
