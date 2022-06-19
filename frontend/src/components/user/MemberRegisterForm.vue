<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group
              label="아이디:"
              label-for="userid"
              v-if="this.type == 'modify'"
            >
              <b-form-input
                id="userid"
                v-model="user.id"
                disabled
                required
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="아이디:" label-for="userid" v-else>
              <b-form-input
                id="userid"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.password"
                required
                placeholder="비밀번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                type="text"
                id="username"
                v-model="user.name"
                required
                placeholder="이름 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="useremail">
              <b-form-input
                type="email"
                id="useremail"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="주소:" label-for="useraddress">
              <b-form-input
                type="text"
                id="useraddress"
                v-model="user.address"
                required
                placeholder="주소 입력...."
              ></b-form-input>
            </b-form-group>
            <b-form-group label="전화번호:" label-for="userphone">
              <b-form-input
                type="tel"
                id="userphone"
                v-model="user.phone"
                required
                placeholder="전화번호 입력...."
              ></b-form-input>
            </b-form-group>
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="modifyUser"
              v-if="this.type == 'modify'"
              >수정</b-button
            >
            <b-button
              type="button"
              variant="primary"
              class="m-1"
              @click="registMember"
              v-else
              >회원가입</b-button
            >
            <b-button
              type="button"
              variant="danger"
              class="m-1"
              @click="movePage"
              >초기화</b-button
            >
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
/* eslint-disable */
import { apiInstance } from "@/api/index.js";
import { register, update } from "@/api/member.js";
import { mapState } from "vuex";

const memberStore = "memberStore";
const api = apiInstance();
export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
        name: null,
        email: null,
        address: null,
        phone: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    type: { type: String },
  },
  mounted() {
    if (this.type == "modify") {
      this.user = this.userInfo;
    }
  },
  methods: {
    movePage() {
      this.$router.push({ name: "home" });
    },
    registMember() {
      console.log(JSON.stringify(this.user));
      register(
        {
          id: this.user.id,
          password: this.user.password,
          name: this.user.name,
          email: this.user.email,
          address: this.user.address,
          phone: this.user.phone,
        },
        (res) => {
          if (res.data == "success") {
            alert("회원가입이 성공적으로 처리되었습니다.");
            this.movePage();
          } else {
            alert("회원가입 처리중 오류가 발생했습니다.");
          }
        },
        (e) => {
          console.log(e);
        }
      );
    },
    modifyUser() {
      update(this.user, (res) => {
        console.log(res);
        if (res.data == "success") {
          alert("수정이 성공적으로 처리되었습니다.");
        } else alert("수정중 문제가 발생했습니다.");
        this.$router.push({ name: "mypage" });
      }),
        (error) => {
          console.log(error);
        };
    },
  },
};
</script>

<style></style>
