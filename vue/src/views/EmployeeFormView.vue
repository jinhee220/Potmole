<template>
	<div class="spacer"></div>
	
	<div class="container">
		<MapBox parentComponent="EmployeeFormView" @setPotholeId="setPotholeToUpdate"/>
		<EmployeeForm v-bind:pothole="pothole"/>
	</div>
</template>

<script>
import EmployeeForm from "../components/EmployeeForm.vue";
import MapBox from "../components/MapBox.vue";
//import EmployeeList from "../components/EmployeeList.vue";


export default {
	data() {
		return {
			pothole: this.$store.state.pothole,
		}
	},
	components: {
    //EmployeeList,
    EmployeeForm,
    MapBox
},
methods : {
	setPotholeToUpdate(id){
    
	this.pothole = this.$store.state.potholeList.find(p => p.potHoleId === id);
	//this.pothole = this.$store.state.potholeList[index];
	}
}
}

</script>

<style>
.spacer {
	height: 1rem;
}

.container{
	display: flex;
	flex-direction: row;
}
</style>