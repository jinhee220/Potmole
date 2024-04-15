<template>
	<div class="main-view">
		<div class="spacer"></div>
		<!-- 5 buttons here for All Reported Inspected, Repaired, Deleted (v-if employee only)-->
		<div class="list">
			<div class="header">
				<h2>Potholes List</h2>
				<img src="../assets/potholeMarker.png">
			</div>
			<PotholeList />
		</div>
	</div>
</template>

<script>
import PotholeList from "../components/PotholeList.vue";

export default {
	components: {
		PotholeList,
	}
}

</script>

<style scoped>
.main-view {
	background: #bbbab9;
	width: 100%;
}

.header {
	display: flex;
	justify-content: center;
	align-content: center;
	background-color: white;
	margin-top: 1rem;
	border: none;
}

h2 {
	font-size: 2rem;
	text-align: center;
	margin-bottom: 1rem;
}

img {
	width: 35px;
	height: 50px;
	padding: 10px;
	margin-top: 10px;
}

.spacer {
	height: 50px;
}

.list {
	height: 40rem;
}
</style>
