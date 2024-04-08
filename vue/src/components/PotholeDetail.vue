<!-- this is where site visitors can see the details of a pothole -->
<template>
	<div class="pothole">
		<!-- pothole detail placeholder -->
		<h3>Latitude: {{ pothole.latitude }}</h3>
		<h3>Longitude: {{ pothole.longitude }}</h3>
		<h3>Status: {{ pothole.currentStatus }}</h3>
		<h3>Reported Date: {{ pothole.reportedDate }}</h3>
		<h3>Inspected Date: {{ pothole.inspectedDate }}</h3>
		<h3>Repaired Date: {{ pothole.repairedDate }}</h3>
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

<style>
.pothole {
	border: 2px solid black;
	border-radius: 10px;
	width: 250px;
	height: 550px;
	margin: 20px;
}
</style>
