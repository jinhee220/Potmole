<template>
        <div class="pothole-container">
            <PotholeDetail v-bind:key="pothole.id" v-for="pothole in potholeList" v-bind:pothole="pothole" />

        </div>
        <div class="pothole-container">
            <EmployeeForm v-bind:key="pothole.id" v-for="pothole in potholeList" v-bind:pothole="pothole" />
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
            potholeList: [],
            //PotholeService.getPotholeList()
        }
    },
    components: {
        PotholeDetail,
        EmployeeForm
    },
    methods: {
        getPotholeList() {
            PotholeService.getPotholeList()
                .then((response) => {
                    this.potholeList = response.data;
                });
        },
    },
    created() {
        this.getPotholeList();
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