<template>
	<div class="spacer"></div>
	<!-- 5 buttons here for All Reported Inspected, Repaired, Deleted (v-if employee only)-->
	<div>
		<PotholeList />
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

<style>
.spacer {
	height: 1rem;
}
</style>
