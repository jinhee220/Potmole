<!-- this is where site visitors can report a pothole AND a pothole object is sent to server -->
<template>
	<div class="spacer"></div>
	<div >
		<div class="header">
			<h1>Click a Pothole Location</h1>
			<img src="../assets/potholeMarker.png">
		</div>
		<!-- Call MapBox component and set parentComponent prop. 
			@coordinates-selected listens for $emit from child component MapBox and runs handleCoordinatesSelected method -->
		<MapBox class="mapbox" parent-component="ReportPotholeView" @coordinates-selected="handleCoordinatesSelected" />
	</div>
	<div>
		<div class="header">
			<h1>Report a Pothole</h1>
			<img src="../assets/potholeMarker.png">
		</div>
		<PotholeForm v-bind:pothole="pothole" />
	</div>
</template>

<script>
import PotholeForm from "../components/PotholeForm.vue";
import MapBox from "../components/MapBox.vue";

export default {
	components: {
		PotholeForm,
		MapBox
	},
	data() {
		return {
			/* pothole: this.$store.state.pothole,
			streetAddress: this.$store.state.streetAddress */
		};
	},

	computed: {

		pothole() { return this.$store.state.pothole; },

	},
	methods: {
		handleCoordinatesSelected(coordinates) {

			this.$store.state.pothole.longitude = coordinates.longitude;
			this.$store.state.pothole.latitude = coordinates.latitude;
			//this.$store.state.pothole.streetAddress = coordinates.streetAddress;
		},
	},
};
</script>

<style scoped>
.spacer {
	height: 66px;
	background-color: #bbbab9;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	z-index: -1;
}

h1 {
	text-align: center;
	color: black;
	font-size: 2rem;
	margin-bottom: 1rem;
}

.mapbox {
	width: 90%;
	margin: auto;
}

h2 {
	text-align: center;
	color: black;

}

.header {
	display: flex;
	justify-content: center;
	align-content: center;
	border: none;
	margin-top: 1rem;
}

img {
	width: 35px;
	height: 50px;
	padding: 10px;
	margin-top: 10px;
}
</style>
