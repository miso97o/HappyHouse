<template>
  <div>
    <b-navbar>
      <b-navbar-brand href="#">
        <router-link to="/">
          <img
            src="@/assets/tree-house.png"
            class="d-inline-block align-middle"
            width="65px"
            height="65px"
            alt="ssafy"
          />
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#"
            ><router-link :to="{ name: 'home' }" class="link">
              <b-icon icon="house" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >Home</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'board' }" class="link"
              ><b-icon icon="journal" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >공지사항</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'air' }" class="link"
              ><b-icon icon="cloud-haze" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >미세먼지</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'house' }" class="link"
              ><b-icon icon="house-fill" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >주택거래정보</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'market' }" class="link"
              ><b-icon icon="shop" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >상권정보</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item href="#"
            ><router-link :to="{ name: 'news' }" class="link"
              ><b-icon icon="newspaper" font-scale="1.5"></b-icon>
              <span class="underline-green" style="font-size: 25px"
                >뉴스</span
              ></router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center" style="margin-right: 20px"
            ><b-avatar
              variant="primary"
              v-text="userInfo ? userInfo.id.charAt(0).toUpperCase() : ''"
            ></b-avatar>
            {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.</b-nav-item
          >
          <b-nav-item class="align-self-center"
            ><router-link
              :to="{ name: 'mypage' }"
              class="link align-self-center"
              >내정보보기</router-link
            ></b-nav-item
          >
          <b-nav-item
            class="link align-self-center"
            @click.prevent="onClickLogout"
            >로그아웃</b-nav-item
          >
        </b-navbar-nav>
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signUp' }" class="link"
                ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
              ></b-dropdown-item
            >
            <b-dropdown-item href="#"
              ><router-link :to="{ name: 'signIn' }" class="link"
                ><b-icon icon="key"></b-icon> 로그인</router-link
              ></b-dropdown-item
            >
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
// import ms from "@/store/modules/memberStore";

const memberStore = "memberStore";

export default {
  name: "HeaderNaviBar",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
  },
};
</script>

<style scoped>
.underline-green {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(31, 210, 159, 0.3) 30%
  );
}
.link {
  color: rgb(0, 0, 0);
}
</style>
