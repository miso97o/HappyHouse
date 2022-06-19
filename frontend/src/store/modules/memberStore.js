import jwt_decode from "jwt-decode";
import {
  login,
  interestList,
  addInterest,
  removeInterest,
} from "@/api/member.js";
import { findById } from "../../api/member";
/* eslint-disable */
const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    interest: [],
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_INTEREST_LIST: (state, interest) => {
      state.interest = interest;
    },
    ADD_INTEREST_LIST: (state, interest) => {
      state.interest.push({
        dongCode: interest.dongcode,
        dongName: interest.dongname,
        id: interest.id,
      });
    },
    REMOVE_INTEREST_LIST: (state, params) => {
      state.interest = state.interest.filter((item) => {
        return item.dongCode != params.dongcode;
      });
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {}
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.id,
        (response) => {
          console.log(response);
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getInterest({ commit }, token) {
      let decode_token = jwt_decode(token);
      interestList(
        decode_token.id,
        (res) => {
          console.log(res);
          commit("SET_INTEREST_LIST", res.data);
        },
        (err) => {
          console.log(err);
        }
      );
    },
    addIntList({ commit }, params) {
      let decode_token = jwt_decode(params.token);
      const param = {
        id: decode_token.id,
        dongcode: params.value,
        dongname: params.text,
      };
      console.log(param);
      addInterest(
        param,
        (res) => {
          console.log(res);
          if (res.data == "success") {
            commit("ADD_INTEREST_LIST", { param });
          } else {
            console.log("추가 실패");
          }
        },
        (err) => {
          console.log(err);
        }
      );
    },
    removeIntList({ commit }, params) {
      let decode_token = jwt_decode(params.token);
      const param = { id: decode_token.id, dongcode: params.dongcode };
      console.log(param);
      removeInterest(
        param,
        (res) => {
          console.log(res);
          if (res.data == "success") {
            commit("REMOVE_INTEREST_LIST", { param });
          } else {
            console.log("삭제 실패");
          }
        },
        (err) => {
          console.log(err);
          console.log("삭제 실패");
        }
      );
    },
  },
};

export default memberStore;
