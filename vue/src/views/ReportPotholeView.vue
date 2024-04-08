<!-- this is where site visitors can report a pothole AND a pothole object is sent to server -->
<template>
	<div>
		<h2>Click a Pothole Location</h2>
		<MapBox @coordinates-selected="handleCoordinatesSelected" />
	</div>
	<div>
		<h1>Report a Pothole</h1>
		<PotholeForm v-bind:pothole="pothole" v-bind:coordinates="selectedCoordinates" />
	</div>
</template>

<script>
import PotholeForm from "@/components/PotholeForm.vue";
import MapBox from "@/components/MapBox.vue";

export default {
	components: {
		PotholeForm,
		MapBox
	},
	data() {
		return {
			pothole: {

				//userId: null,
				userId: this.$store.state.user.id,
				longitude: null,
				latitude: null,
				currentStatus: "reported",
				reportedDate: new Date().toDateString(),
				inspectedDate: "Not Inspected Yet",
				repairedDate: "Not Repaired Yet",
			}
		};
	},
	methods: {
		handleCoordinatesSelected(coordinates) {

			this.pothole.longitude = coordinates.longitude;
			this.pothole.latitude = coordinates.latitude;
		},
	},
};
</script>

<style scoped></style>
