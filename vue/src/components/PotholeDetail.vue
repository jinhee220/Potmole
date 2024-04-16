<!-- this is where site visitors can see the details of a pothole -->
<template>
	<div class="pothole-card">

		<!-- pothole detail placeholder -->
		<h3 class="pothole-id"><strong>Pothole ID:</strong> {{ pothole.potHoleId }}</h3>
		<img class="pothole-image" src="../assets/pothole.jpg" />
		<!-- Going to replace long and lat with address here for user -->
		<!-- <h3>Latitude: {{ pothole.latitude }}</h3>
		<h3>Longitude: {{ pothole.longitude }}</h3> -->
		<p><strong>Address:</strong> {{ pothole.streetAddress }}</p>
		<p><strong>Status:</strong> {{ pothole.currentStatus }}</p>
		<p><strong>Reported Date:</strong> {{ pothole.reportedDate }}</p>
		<p><strong>Inspected Date:</strong> {{ pothole.inspectedDate }}</p>
		<p><strong>Repaired Date:</strong> {{ pothole.repairedDate }}</p>
		<p><strong>Severity:</strong> {{ pothole.severity }}</p>

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
	display: block;
	box-shadow: gray 3px 3px 3px;
	border-radius: 10px;
	max-width: 250px;
	max-height: 450px;
	margin: 20px;
	padding: 10px;
	padding-left: 12px;
	background-color: white;
}

h3 {
	font-size: 1rem;
	margin: 5px;
	color: black;
}

p {
	font-size: 1rem;
}

.pothole-id {
	text-align: center;
	color: black;
}

.pothole-image {
	display: block;
	object-fit: cover;
	width: 220px;
	height: 150px;
	border-radius: 10px;
	margin: auto;
}

strong {
	font-weight: 400px;
	color: rgb(0, 0, 0);
	padding-right: 2px;
}
</style>
