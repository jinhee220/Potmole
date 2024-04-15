<template>
	<div class="map">
		<!-- The map container -->
		<div ref="map" class="map-container"></div>
	</div>
</template>
<script>
import mapboxgl from "mapbox-gl";
mapboxgl.accessToken = 'pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA'
export default {
    mounted() {
  const map = new mapboxgl.Map({
    container: this.$refs.map,
    style: "mapbox://styles/mapbox/streets-v12", // Replace with your preferred map style
    center: [-71.224518, 42.213995],
    zoom: 9,
  });
  this.map = map;
    }
}
</script>
<style>
.map-container {
  flex: 1;
}
</style>