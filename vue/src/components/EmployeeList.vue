<template>
    <!-- <div class="pothole-container">
        <PotholeDetail v-bind:key="pothole.potHoleId" v-for="pothole in potholeList" v-bind:pothole="pothole" />
    </div> -->
    <div class="pothole-container">
        <EmployeeForm v-bind:key="pothole.potHoleId" v-for="pothole in potholeList" v-bind:pothole="pothole" />
    </div>
</template>

<script>
import PotholeDetail from "../components/PotholeDetail.vue";
import PotholeService from "@/services/PotholeService.js";
import EmployeeForm from "./EmployeeForm.vue";


export default {
    data() {
        return {
            //call the PotholeService.js here
            potholeList: this.$store.state.potholeList,
            //PotholeService.getPotholeList()
        }
    },
    components: {
        EmployeeForm
    },
    // methods: {
    //     getPotholeList() {
    //         PotholeService.getPotholeList()
    //             .then((response) => {
    //                 this.potholeList = response.data;
    //             });
    //     },
    // },
    created() {
        this.$store.commit("UPDATE_POTHOLE_LIST");
    },
}

</script>

<style>
.pothole-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>