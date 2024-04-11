<template>
	<!-- <div>
			<h2>Click a Pothole Location</h2>
			<MapBox/>
		</div> -->

	<form v-on:submit.prevent="submitForm">
		<div class="form-group">
			<h3>Longitude: {{ editPothole.longitude }}</h3>
			<h3>Latitude: {{ editPothole.latitude }}</h3>
			<h3>Street Address: {{ streetAddress }}</h3>
			<h3>Date: {{ editPothole.reportedDate }}</h3>
			<button type="submit">Submit</button>
		</div>
		<!-- Adding a input - description for a pothole(?) -->
		<!-- Adding a input - description for a img file(?) -->

	</form>
</template>

<script>
//import MapBox from "@/components/MapBox.vue";
//import a service class here

import PotholeService from "../services/PotholeService"

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
		streetAddress: {
			type: String,
		}
	},

	computed: {

		// Since props are a read-only, creating an object that can be acted on
		editPothole() {
			return {
				userId: this.$store.state.user.id,
				longitude: this.$store.state.pothole.longitude,
				latitude: this.pothole.latitude,
				currentStatus: "Reported",
				reportedDate: this.pothole.reportedDate,
				inspectedDate: this.pothole.inspectedDate,
				repairedDate: this.pothole.repairedDate,
				severity: this.pothole.severity
			}
		},
	},
	methods: {
		// logics to take in store information and commit to servers

		submitForm() {

			// call validateForm to check if form has required fields

			// if (!this.validateForm()) {
			// 	return;
			// }

			// call on service to add pothole
			//if (this.editPothole.id === 0) {
			PotholeService
				.addPothole(this.editPothole)
				.then(response => {

					if (response.status === 201) {
						//this.$store.commit();
						this.$router.push({ name: "home" });
					}
				})
				.catch(error => {
					this.handleErrorResponse(error, 'adding');
				});
			//}

			// THIS ELSE STATEMENT MIGHT NOT BE NEEDED !!!!!

			// else {
			// 	potholeService
			// 		.updatePothole(this.editPothole)
			// 		.then(response => {
			// 			if(response.status === 200) {
			// 				this.$store.commit();
			// 				this.$router.push({ name: "PotholeListView"})
			// 			}
			// 		})
			// 		.catch(error => {
			// 			this.handleErrorResponse(error, 'updating');
			// 		});
			// }



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
