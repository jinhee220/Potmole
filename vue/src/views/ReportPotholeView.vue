<!-- this is where site visitors can report a pothole AND a pothole object is sent to server -->
<template>
	<div>
		<h1>Report a Pothole</h1>
		<PotholeForm v-bind:pothole="pothole" />
	</div>
	s
</template>

<script>
import PotholeForm from "@/components/PotholeForm.vue";

export default {
	components: {
		PotholeForm,
	},
	data() {
		return {
			pothole: {
				userId: null,
				longitude: null,
				latitude: null,
				status: "reported",
				reportedDate: "",
				inspectedDate: "Not Inspected Yet",
				repairedDate: "Not Repaired Yet",
			},
		};
	},
};
</script>

<style scoped></style>
