<template>
  <div id="app">
    <router-view style="position: relative; z-index: 1" />
    <!-- <footer-bar></footer-bar> -->
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}

a.router-link-exact-active {
  font-weight: 900;
  color: #1c9354;
}
</style>
