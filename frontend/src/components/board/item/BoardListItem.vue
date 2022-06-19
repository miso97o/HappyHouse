<template>
  <b-tr>
    <b-td>{{ qnano }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'boardDetail', params: { articleno: qnano } }"
        >{{ question }}</router-link
      >
    </b-th>
    <b-td>{{ hit }}</b-td>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    qnano: Number,
    userid: String,
    question: String,
    hit: Number,
    regtime: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
