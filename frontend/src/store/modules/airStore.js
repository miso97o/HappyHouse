/* eslint-disable prettier/prettier */
import { sidoList, gugunList, airList} from "@/api/air.js";

const airStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    airs: [],
    air: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    SET_AIR_LIST: (state, airs) => {
      console.log(airs);
      state.airs = airs;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, data);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getAirList: ({ commit }, params) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      // const SERVICE_KEY =
      //   "poVId%2BbhY%2F97NQGw28%2F%2FVAYHFj4Pd8LlEgiztLhRLauyDjrOXZCh%2Fqesde%2BmBApvJNB4gP52EGjyePnSbVYxNQ%3D%3D";
      // const params = {
      //   // LAWD_CD: gugunCode,
      //   // DEAL_YMD: "202110",
      //   // serviceKey: decodeURIComponent(SERVICE_KEY),
      //   dong: dongCode,
      //   name: name,
      // };
      console.log(params);
      airList(
        params,
        (response) => {
          console.log(response.data);
          commit("SET_AIR_LIST", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default airStore;
