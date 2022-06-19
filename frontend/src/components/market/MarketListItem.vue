<template>
  <b-tr
    @mouseover="colorChange(true)"
    @mouseout="colorChange(false)"
    :class="{ 'mouse-over-bgcolor': isColor }"
  >
    <b-td>
      {{ market.marketKinds }}
    </b-td>
    <b-td>
      {{ market.marketName }}
    </b-td>
    <b-td>
      {{ market.dongName }}
    </b-td>
  </b-tr>
</template>

<script>
export default {
  name: "MarketListItem",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    market: Object,
  },
  methods: {
    colorChange(flag) {
      this.isColor = flag;
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
