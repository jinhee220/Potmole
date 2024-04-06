<template>
	<div class="map">
		<!-- The map container -->
		<div ref="map" class="map-container"></div>
	</div>
</template>

<script>
// Include Mapbox GL JS library from node-module after running npm install mapbox-gl in terminal
import mapboxgl from "mapbox-gl";
// Include Mapbox Geocoder plugin from node-module after running npm install @mapbox/mapbox-gl-geocoder in terminal
import MapboxGeocoder from "@mapbox/mapbox-gl-geocoder";

export default {
	data() {
		return {
			// change this to be a prop from the form
			pothole: {
				latitude: "",
				longitude: "",
			},
		};
	},
	mounted() {
		// Set your Mapbox access token
		mapboxgl.accessToken =
			"pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA";

		// Create the map instance
		// This is the map
		const map = new mapboxgl.Map({
			container: this.$refs.map,
			style: "mapbox://styles/mapbox/streets-v12",
			center: [-79.4512, 43.6568],
			zoom: 13,
		});

		// Add the geocoder control to the map
		// This is the search box
		const geocoder = new MapboxGeocoder({
			accessToken: mapboxgl.accessToken,
			mapboxgl: mapboxgl,
		});
		// This adds plugin to map
		map.addControl(geocoder);
		// This lets us click on map and get coords, event handler
		map.on("click", (coords) => {
			let coordinates = coords.lngLat;

			this.pothole.longitude = coordinates.lng;
			this.pothole.latitude = coordinates.lat;
		});
	},
};
</script>

<style scoped>
.map {
	width: 100%;
	height: 400px;
}

.map-container {
	width: 100%;
	height: 100%;
}
</style>
