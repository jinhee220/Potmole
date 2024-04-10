<template>
	<div class="spacer"></div>
	<div>
		<EmployeeList/>
	</div>
</template>

<script>
import EmployeeList from "../components/EmployeeList.vue";


export default {
	components: {
		EmployeeList,
	}
}

</script>

<style>
.spacer {
	height: 1rem;
}
</style>