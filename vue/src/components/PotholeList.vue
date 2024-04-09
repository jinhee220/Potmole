<template>
    <div>
        <!-- Call MapBox component and set parentComponent prop -->
        <MapBox parent-component="PotholeList"/>
    </div>
    <div class="pothole-container">
        <PotholeDetail v-bind:key="pothole.id" v-for="pothole in potholeList" v-bind:pothole="pothole" />
    </div>
</template>

<script>
import PotholeDetail from "../components/PotholeDetail.vue";
import PotholeService from "@/services/PotholeService.js";
import MapBox from "./MapBox.vue";

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
    MapBox
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