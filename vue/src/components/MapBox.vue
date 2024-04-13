<!-- Jay's Baby -->
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
import "../style/mapbox-gl.css";
// Import Mapbox-Geocoder CSS
import "../style/mapbox-gl-geocoder.css"

// Import PotholeService
import PotholeService from "../services/PotholeService.js"

export default {
	data() {
		return {
			// change this to be a prop from the form
			editCoordinates: {
				longitude: '',
				latitude: '',
				streetAddress: '',
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
		// Update the potholeList in store
		this.$store.commit("UPDATE_POTHOLE_LIST");

		// Set your Mapbox access token
		mapboxgl.accessToken =
			"pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA";

		// Create the map instance
		this.map = new mapboxgl.Map({
			container: this.$refs.map,
			style: "mapbox://styles/mapbox/streets-v12",
			center: [-98.00000, 39.00000],
			zoom: 3,
		});

		// Check if geolocation is available for the user's browser
		if (navigator.geolocation) {
			// Get user's current position
			navigator.geolocation.getCurrentPosition(
				(position) => {
					// Extract long and lat from coordinates
					const longitude = position.coords.longitude;
					const latitude = position.coords.latitude;
					// Set map center and zoom to reflect user location
					this.map.setCenter([longitude, latitude]);
					this.map.setZoom(10);
				}
			)
		}

		// Add the geocoder control to the map
		const geocoder = new MapboxGeocoder({
			accessToken: mapboxgl.accessToken,
			mapboxgl: mapboxgl,
		});

		// Add Geolocation for user location to mapboxgl
		this.map.addControl(
			new mapboxgl.GeolocateControl({
				positionOptions: {
					enableHighAccuracy: true
				},
				// When active the map will receive updates to the device's location as it changes.
				trackUserLocation: false,
				// Draw an arrow next to the location dot to indicate which direction the device is heading.
				showUserHeading: true
			}));


		// This adds Geocoder plugin to map
		this.map.addControl(geocoder);

		// Only allows the following code to run if component is being imported into PotholeList
		if (this.parentComponent === 'PotholeList' || this.parentComponent==='EmployeeFormView') {
			this.getPotholeList();
		}
		// Only allows the following code to run if component is being imported into ReportPotholeView
		if (this.parentComponent === 'ReportPotholeView') {

			// This lets us click on map and get coordinates and call methods
			this.map.on("click", (event) => {
				const coords = event.lngLat;


				// Call method that adds marker to the map
				this.addMarker(coords);

				// Update street address property of editCoordinates by feeding clicked coordinates
				// Currently is one click behind; NEEDS FIXING 
				
				// **** RICH DID IT ****
				//this.reverseGeocode(coords);

				// Update longitude and latitude properties of editCoordinates with clicked coordinates
				
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
			if (this.marker == null) {
				// Create a new element in instance of map
				const markerElement = document.createElement("div");
				// Assign class to element to be affected by style sheet
				markerElement.className = "marker";
				// Initialize marker with built in API mapboxgl.Marker method
				this.marker = new mapboxgl.Marker(markerElement, { offset: [-15, 1] })
			}
			// Assign coordinates to marker and add it to the map

			this.marker
				.setLngLat(coords)
				.addTo(this.map);

			this.reverseGeocode(coords);
		},
		addAllMarkers(potholeList) {
			// Loop through potholeList and add a marker to the map for each pothole
			for (const pothole of potholeList) {
				// Create a new element in instance of map
				const markerElement = document.createElement("div");
				// Assign class to element to be affected by style sheet
				markerElement.className = "marker";
				// Initialize marker with built in API mapboxgl.Marker method
				const marker = new mapboxgl.Marker(markerElement, { offset: [-15, 1] })


				marker.getElement().addEventListener("click", () => {
            // Emit the potholeId when the marker is clicked
            this.$emit("setPotholeId", pothole.potHoleId);
        });

				// Assign coordinates to marker and add it to the map
				marker
					.setLngLat([pothole.longitude, pothole.latitude])
					//Add popup on marker click
					.addTo(this.map);


				// If component is not a child of EmployeeFormView, add popup to marker
				if (this.parentComponent!=="EmployeeFormView"){
				// Create Popup const that will be assigned to each marker
				const detailPopup = this.createUserPopup(pothole);
				marker.setPopup(detailPopup)
				}
			}
		},
		createUserPopup(pothole) {
			return new mapboxgl.Popup({ offset: [-150, 100] }) // add popups
				.setHTML(
					`<div class='popup_details'>
						<h3 class='popup_id'>Pothole ID: ${pothole.potHoleId}</h3>
						<p class='popup_status'>Status: ${pothole.currentStatus}</p>
						<p class='popup_street_address'>${this.editCoordinates.streetAddress}</p>
						<p class='popup_reported_date'>Reported Date: ${pothole.reportedDate}</p>
						<p class='popup_inspected_date'>Inspected Date: ${pothole.inspectedDate}</p>
						<p class='popup_repaired_date'>Repaired Date: ${pothole.repairedDate}</p>
						<p class='popup_severity'>Severity: ${pothole.severity}</p>
						</div>`
				);
		},

		getPotholeList() {
			PotholeService.getPotholeList()
				.then((response) => {
					this.potholeList = response.data;

					this.addAllMarkers(this.potholeList);
				})
		},

		// Call the MapBox Geocoder API and retrieve a street address based on given coordinates
		reverseGeocode(coords) {
			// The API URL we will retrieve data from, it takes in coordinates and retrieves the place information from those coordinates
			const apiUrl = `https://api.mapbox.com/geocoding/v5/mapbox.places/${coords.lng},${coords.lat}.json`;
			// Our MapBox access token, it identifies who we are and is required to communicate with the API
			const accessToken = "pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA";

			// Fetch is a built in javascript function that is used to make HTTP Requests. It takes in a full url
			// Our fetch requires the base api url and our access token to prove our right to access the information
			fetch(`${apiUrl}?access_token=${accessToken}`)
				// The fetch method sends a requests for data from the URL and returns it as a response that we convert into json for easier manipulation
				.then((response) => response.json())
				.then((data) => {
					// The returned data is an object containing nested objects that we branch through to reach our 'place_name', a property that contains a full street address
					const address = data.features[0].place_name;

					//alert(address);

					//this.$store.commit("UPDATE_STREET_ADDRESS", address);

					this.$store.state.pothole.streetAddress = address;

					this.editCoordinates.streetAddress = address;
				})
				.catch((error) => {
					console.error("Error fetching address:", error);
				});
		},

	},
	

};
</script>

<style>


.map {
	width: 100%;
	height: 400px;

}



.map-container {
	width: 100%;
	height: 100%;
}

/* Set css style for marker */
.marker {
	background-image: url('../assets/potholePointer.png');
	background-size: contain;
	background-repeat: no-repeat;
	background-color: transparent;
	width: 50px;
	height: 50px;
	cursor: pointer;
	position: absolute;

}
</style>
