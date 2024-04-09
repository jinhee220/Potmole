<template>
	<div class="spacer"></div>
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
