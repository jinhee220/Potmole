<template>
	<div class="main-view">
		<!-- 5 buttons here for All Reported Inspected, Repaired, Deleted (v-if employee only)-->
		<div class="list">
			<h2>Potholes List</h2>
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

h2 {
	font-size: 2rem;
	text-align: center;
	background-color: white;
	margin-top: 1rem;
	margin-bottom: 0;
	padding: 1rem 0 1rem 0;
}

.list {
	height: 40rem;
	padding-top: 3rem;
}
</style>
