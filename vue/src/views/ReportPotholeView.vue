<!-- this is where site visitors can report a pothole AND a pothole object is sent to server -->
<template>
	<div class="entire-page">
			<div class="spacer"></div>
			
			<div class="header">
				<h1>Click a Pothole Location</h1>
				<img src="../assets/potholeMarker.png">
			</div>
			
		<div class = "upper-form">

			<div class="map-background">
				<!-- Call MapBox component and set parentComponent prop. 
			@coordinates-selected listens for $emit from child component MapBox and runs handleCoordinatesSelected method -->
					<MapBox class="mapbox" parent-component="ReportPotholeView" />
			</div>
			
			<div class="instruction">
				<p>Report a Pothole</p>
			</div>
			
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
};
</script>

<style scoped>
.spacer {
	height: 50px;
	background-color: #bbbab9;
	
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
	background-color: white;
}

.instruction {
	font-size: 1.5rem;
	text-align: center;
	margin-bottom: 1rem;
	padding-bottom: 0.2rem;
}

img {
	width: 35px;
	height: 50px;
	padding: 10px;
	margin-top: 10px;
}

.entire-page {
	background-color: #bbbab9;
}

.map-background {
	background-color: white;
	
}

.upper-form {
	background-color: white;
}

</style>
