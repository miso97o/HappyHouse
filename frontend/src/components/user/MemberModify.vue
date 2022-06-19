<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>유저 정보 수정</h3></b-alert>
      </b-col>
    </b-row>
    <member-register-form type="modify" />
  </b-container>
</template>

<script>
import MemberRegisterForm from "@/components/user/MemberRegisterForm.vue";

export default {
  name: "MemberModify",
  components: {
    MemberRegisterForm,
  },
};
</script>

<style></style>
