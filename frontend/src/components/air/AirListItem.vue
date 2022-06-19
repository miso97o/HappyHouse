<template>
  <b-tr>
    <b-td>{{ air.station }} </b-td>
    <b-td>
      {{ air.date | dateFormat }}
    </b-td>
    <b-td
      ><b-icon :icon="setIcon(air.pollution)" font-scale="1.5"></b-icon>
      {{ air.pollution }}</b-td
    >
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "AirListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    air: Object,
  },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
    },
    setIcon(pollution) {
      if (pollution <= 20) return "emoji-sunglasses";
      else if (pollution <= 40) return "emoji-smile";
      else if (pollution <= 70) return "emoji-frown";
      else return "emoji-dizzy";
    },
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("MM.DD HH:00");
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
