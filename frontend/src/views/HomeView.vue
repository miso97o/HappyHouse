<template>
  <div>
    <navi-bar style="position: relative; z-index: 2" />
    <div
      class="background"
      style="
        text-align: center;
        vertical-align: middle;
        margin-top: -95px;
        position: relative;
        z-index: 1;
      "
    >
      <div class="brand">
        <h1 style="font-size: 200px">Happy House</h1>
      </div>
    </div>
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "HomeView",
  components: {
    NaviBar,
  },
  bodyClass: "index-page",
  props: {
    msg: String,
    image: {
      type: String,
      default: require("@/assets/img/vue-mk-header.jpg"),
    },
    leaf4: {
      type: String,
      default: require("@/assets/img/leaf4.png"),
    },
    leaf3: {
      type: String,
      default: require("@/assets/img/leaf3.png"),
    },
    leaf2: {
      type: String,
      default: require("@/assets/img/leaf2.png"),
    },
    leaf1: {
      type: String,
      default: require("@/assets/img/leaf1.png"),
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
    };
  },
  methods: {
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`,
      };
    },
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  },
};
</script>

<style scoped>
.brand {
  color: aliceblue;
  margin-top: 350px;
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}
.background {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/vue-mk-header.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
</style>
