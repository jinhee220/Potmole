<template>
	<!-- <div>
			<h2>Click a Pothole Location</h2>
			<MapBox/>
		</div> -->

	<body class="main">
		<div class="pothole">
			<form v-on:submit.prevent="submitForm">
				<div class="form-group">
					<p><strong>Longitude: </strong>{{ editPothole.longitude }}</p>
					<p><strong>Latitude: </strong>{{ editPothole.latitude }}</p>
					<p><strong>Address: </strong>{{ editPothole.streetAddress }}</p>
					<p><strong>Date: </strong>{{ editPothole.reportedDate }}</p>
					<div class="button-container">
						<button type="submit"><strong>Submit</strong></button>
					</div>
				</div>
				<!-- Adding a input - description for a pothole(?) -->
				<!-- Adding a input - description for a img file(?) -->

			</form>
		</div>
	</body>
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
	},

	computed: {

		// Since props are a read-only, creating an object that can be acted on
		editPothole() {
			return {
				userId: this.$store.state.user.id,
				streetAddress: this.pothole.streetAddress,
				longitude: this.pothole.longitude,
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
						alert("Pothole successfully reported!");
						this.$router.go();
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

<style scoped>
button[type="submit"] {
	background-color: #ffbd8b;
	color: black;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	box-shadow: gray 5px 5px 5px;
}

button[type="submit"]:hover {
	background-color: #d68548;
}

.main {
	display: flex;
	flex-direction: column;
	align-items: center;
	background-color: #bbbab9;
	padding-top: 10px;
	padding-bottom: 25px;
	width: 100%;
}

.pothole {
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 80%;
	border-radius: 10px;
	box-shadow: gray 5px 5px 5px;
	background-color: white;
}

.form-group {

	display: flex;
	flex-direction: column;
	align-items: center;

}

.button-container {

	margin-bottom: 20px;

}

* {
	color: black;
}
</style>
