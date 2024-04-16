<template>
	<div class="main-view">
		<div class="spacer"></div>
		<div class="header">
			<h2>Employee Hub</h2>
			<img src="../assets/potholeMarker.png">

		</div>

		<div class="container">
			<div class="upper-form">
				<div class="map">
					<MapBox class="mapbox" parentComponent="EmployeeFormView" @setPotholeId="setPotholeToUpdate" />
				</div>
				<div class="instruction">
					<p>Please Update Selected Pothole Below</p>
				</div>
			</div>
			<EmployeeForm v-bind:pothole="pothole" />
		</div>
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
	methods: {
		setPotholeToUpdate(id) {

			this.pothole = this.$store.state.potholeList.find(p => p.potHoleId === id);

		}
	}
}

</script>

<style scoped>
.spacer {
	height: 50px;
}

.upper-form {
	background-color: white;
}

.instruction {
	font-size: clamp(1rem, 5vw, 1.5rem);
	text-align: center;
	margin-bottom: 1rem;
}

.main-view {
	background: #bbbab9;
	width: 100%;
}

.container {
	display: flex;
	flex-direction: column;
	gap: 15px;
	padding-bottom: 25px;
}

.mapbox {
	width: 90%;
	margin: auto;
}

img {
	/* width: 35px;
	height: 50px; */
	width: clamp(25px, 5vw, 40px);
	height: clamp(30px, 5vw, 50px);
	padding: 10px;
	margin-top: 10px;
}

h2 {
	/* font-size: 2rem; */
	font-size: clamp(1rem, 6vw, 2rem);
	text-align: center;
	margin-bottom: 1rem;
}

.header {
	display: flex;
	justify-content: center;
	align-content: center;
	background-color: white;
	margin-top: 1rem;
	border: none;
}
</style>