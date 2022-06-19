<template>
  <b-tr>
    <b-td>{{ newsno }}</b-td>
    <b-td>
      <dt>
        <b-link :href="newslink">{{ newstitle }}</b-link>
      </dt>
      <dd>{{ newscontent }}</dd>
    </b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "NewsListItem",
  props: {
    newsno: Number,
    newstitle: String,
    newscontent: String,
    newslink: String,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
