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

// Import Mapbox CSS
import "mapbox-gl/src/css/mapbox-gl.css";

// Import PotholeService
import PotholeService from "@/services/PotholeService.js"

export default {
	data() {
		return {
			// change this to be a prop from the form
			editCoordinates: {
				longitude: '',
				latitude: '',
			},
			// Set null map variable to allow the map to updated
			map: null,
			// Set null marker variable to allow a single marker to be reassigned on click
			marker: null,
			// Set empty potholeList array
			potholeList: [],
		};
	},
	props: {
		coordinates: {
			type: Object,
			required: true,
		},
		parentComponent: {
			type: String,
			required: true,
		}
	},
	mounted() {
		// Set your Mapbox access token
		mapboxgl.accessToken =
			"pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA";

		// Create the map instance
		this.map = new mapboxgl.Map({
			container: this.$refs.map,
			style: "mapbox://styles/mapbox/streets-v12",
			center: [-79.4512, 43.6568],
			zoom: 13,
		});



		// Add the geocoder control to the map
		const geocoder = new MapboxGeocoder({
			accessToken: mapboxgl.accessToken,
			mapboxgl: mapboxgl,
		});

		// This adds plugin to map
		this.map.addControl(geocoder);

		// Only allows the following code to run if component is being imported into PotholeList
		if (this.parentComponent==='PotholeList') {
		this.getPotholeList();
		}
		// Only allows the following code to run if component is being imported into ReportPotholeView
		if (this.parentComponent==='ReportPotholeView'){

		// This lets us click on map and get coordinates and call methods
		this.map.on("click", (event) => {
			const coords = event.lngLat;

			// Call method that adds marker to the map
			this.addMarker(coords);

			// Update editCoordinates data with clicked coordinates
			this.editCoordinates.longitude = coords.lng.toFixed(5);
			this.editCoordinates.latitude = coords.lat.toFixed(5);

			// Send coordinates-selected event with updated coordinates for parent class to listen for
			this.$emit("coordinates-selected", this.editCoordinates);
		});
	}
	},

	// Method to create an element for marker, instantiate marker, and add marker to map
	methods: {
		addMarker(coords) {
			// Check if marker is null. If it is, create element in map document and initialize marker with map.Marker
			// If marker is not null, skip if statement and only set marker coordinates and add to map
			if (this.marker==null){
				// Create a new element in instance of map
				const markerElement = document.createElement("div");
				// Assign class to element to be affected by style sheet
				markerElement.className = "mapboxgl-marker";
				// Initialize marker with built in API mapboxgl.Marker method
				this.marker = new mapboxgl.Marker(this.markerElement)
			}
			// Assign coordinates to marker and add it to the map
				this.marker
				.setLngLat(coords)
				.addTo(this.map);
				
		},
		addAllMarkers(potholeList) {
			// Loop through potholeList and add a marker to the map for each pothole
			for (const pothole of potholeList){
				// Create a new element in instance of map
				const markerElement = document.createElement("div");
				// Assign class to element to be affected by style sheet
				markerElement.className = "mapboxgl-marker";
				// Initialize marker with built in API mapboxgl.Marker method
				const marker = new mapboxgl.Marker(this.markerElement)
			
			// Assign coordinates to marker and add it to the map
				marker
				.setLngLat([pothole.longitude, pothole.latitude])
				.addTo(this.map);
            }	
		},
        getPotholeList() {
            PotholeService.getPotholeList()
                .then((response) => {
                    this.potholeList = response.data;
                    
                    this.addAllMarkers(this.potholeList);
                })
        },

	},

};
</script>

<style>
.map {
	width: 100%;
	height: 400px;
	border: solid black;

}

.map-container {
	width: 100%;
	height: 100%;
}

/* Set css style for marker */
.mapboxgl-marker {
	background-image: url('../assets/potholeMarker.png');
	background-size: cover;
	width: 50px;
	height: 50px;
	border-radius: 50%;
	cursor: pointer;
	position: absolute;

}
</style>
