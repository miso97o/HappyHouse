<template>
  <div class="mapView">
    <div id="map"></div>
  </div>
</template>

<script>
/* eslint-disable */
import { mapState } from "vuex";
const houseStore = "houseStore";
export default {
  data() {
    return {
      map: null,
      markers: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 8,
      };
      this.map = new kakao.maps.Map(container, options);
      console.log(this.houses);
    },
    displayMarkers() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      this.houses.forEach((house) => {
        const infowindow = new kakao.maps.InfoWindow({
          content: `<div style="padding:5px; text-align:center;">${house.aptName}</div>`,
        });
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: new kakao.maps.LatLng(house.lat, house.lng),
          title: house.aptName,
        });
        kakao.maps.event.addListener(marker, "mouseover", () => {
          infowindow.open(this.map, marker);
        });
        kakao.maps.event.addListener(marker, "mouseout", () => {
          infowindow.close(this.map, marker);
        });
        this.markers.push(marker);
      });

      const bounds = this.houses.reduce(
        (bounds, house) =>
          bounds.extend(new kakao.maps.LatLng(house.lat, house.lng)),
        new kakao.maps.LatLngBounds()
      );
      this.map.setBounds(bounds);
    },
  },
  mounted() {
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=64c5d9dcda294d1eedb9aaba2846831f";
      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
        this.displayMarkers();
      });
      document.head.appendChild(script);
    } else {
      // console.log("이미 로딩됨: ", window.kakao);
      this.initMap();
      this.displayMarkers();
    }
  },
  watch: {
    houses: function (val) {
      this.displayMarkers();
    },
  },
};
</script>

<style>
#map {
  width: 570px;
  height: 600px;
  margin: 0 auto;
}
</style>
