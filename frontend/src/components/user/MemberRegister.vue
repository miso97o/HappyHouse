<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <register-form></register-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import RegisterForm from "./MemberRegisterForm.vue";
export default {
  name: "MemberRegister",
  components: {
    RegisterForm,
  },
};
</script>

<style></style>
