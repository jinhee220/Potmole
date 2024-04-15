<!-- this is where site visitors can see the details of a pothole -->
<template>
	<div class="pothole-card">

		<!-- pothole detail placeholder -->
		<h3 class="pothole-id">Pothole ID: {{ pothole.potHoleId }}</h3>
		<img class="pothole-image" src="../assets/pothole.jpg" />
		<!-- Going to replace long and lat with address here for user -->
		<!-- <h3>Latitude: {{ pothole.latitude }}</h3>
		<h3>Longitude: {{ pothole.longitude }}</h3> -->
		<p>Address: {{ pothole.streetAddress }}</p>
		<p>Status: {{ pothole.currentStatus }}</p>
		<p>Reported Date: {{ pothole.reportedDate }}</p>
		<p>Inspected Date: {{ pothole.inspectedDate }}</p>
		<p>Repaired Date: {{ pothole.repairedDate }}</p>
		<p>Severity: {{ pothole.severity }}</p>

	</div>
</template>

<script>
export default {
	props: {
		pothole: {
			type: Object,
			required: true,
		}
	}
}

</script>

<style scoped>
.pothole-card {
	border: 2px dashed #4f62cd;
	border-radius: 10px;
	max-width: 250px;
	max-height: 450px;
	margin: 20px;
	padding: 10px;
	background-color: white;
}

h3,
p {
	font-size: 1rem;
	margin: 5px;
}

p {
	font-size: 1rem;
}

.pothole-id {
	text-align: center;
}

.pothole-image {
	display: block;
	object-fit: cover;
	width: 220px;
	height: 150px;
	border-radius: 10px;
	margin: auto;
}
</style>
