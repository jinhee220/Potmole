<template>
	<div>
		<!-- <div>
			<h2>Click a Pothole Location</h2>
			<MapBox/>
		</div> -->

		<form>
			<div class="form-group">
				<h3>Longitude: {{ editPothole.longitude }}</h3>
				<h3>Latitude: {{ editPothole.latitude }}</h3>
				<h3>Date: {{ editPothole.reportedDate }}</h3>
			</div>
			<!-- Adding a input - description for a pothole(?) -->
			<!-- Adding a input - description for a img file(?) -->
		</form>
	</div>
</template>

<script>
//import MapBox from "@/components/MapBox.vue";
//import a service class here

export default {
	components: {
		//MapBox,
	},
	props: {
		// bringing in pothole object from ReportPothole
		pothole: {
			type: Object,
			required: true,
		},
	},
	
	computed: {
		
			// Since props are a read-only, creating an object that can be acted on
			editPothole() {
				return {
				userId: this.pothole.userId,
				longitude: this.pothole.longitude,
				latitude: this.pothole.latitude,
				status: "reported",
				reportedDate: this.pothole.reportedDate,
				inspectedDate: this.pothole.inspectedDate,
				repairedDate: this.pothole.repairedDate,
			}
		},
	},
	methods: {
		// logics to take in store information and commit to servers

		submitForm() {
			// call validateForm to check if form has required fields
			// call on service to add pothole
			// check if response.status === 201
			// if successful show a notice
			// push user to PotholeListsView
			// else, catch error (add this in later, just print console for now)
			// call handleErrorResponse
		},
		//cancelForm() {},
		handleErrorResponse(error, verb) {
			// store implementation needed to proceed
		},
		validateForm() {
			let msg = "";
			// check a required field's length - lat
			// add msg if 0
			// if msg length > 0 (requirements not met)
			//call a store commit to "SET_NOTIFICATION"
			// return false;
			return true;
		},
	},
};
</script>

<style></style>
