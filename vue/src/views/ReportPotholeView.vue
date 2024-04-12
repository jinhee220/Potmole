<!-- this is where site visitors can report a pothole AND a pothole object is sent to server -->
<template>
	<div class="spacer"></div>
	<div>
		<h2>Click a Pothole Location</h2>
		<!-- Call MapBox component and set parentComponent prop. 
			@coordinates-selected listens for $emit from child component MapBox and runs handleCoordinatesSelected method -->
		<MapBox parent-component="ReportPotholeView" @coordinates-selected="handleCoordinatesSelected" />
	</div>
	<div>
		<h1>Report a Pothole</h1>
		<PotholeForm v-bind:pothole="pothole" v-bind:streetAddress="streetAddress" />
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

		pothole() { return this.$store.state.pothole;},
		streetAddress() {return this.$store.state.streetAddress;}

	},
	methods: {
		handleCoordinatesSelected(coordinates) {

			this.$store.state.pothole.longitude = coordinates.longitude;
			this.$store.state.pothole.latitude = coordinates.latitude;
			this.$store.state.streetAddress = coordinates.streetAddress;
		},
	},
};
</script>

<style scoped>
.spacer {
	height: 1rem;
}
</style>
