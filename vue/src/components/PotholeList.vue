<template>
    
    <div>
        <!-- Call MapBox component and set parentComponent prop -->
        <MapBox parent-component="PotholeList" />
    </div>
    
    <div class="filter">
        <h3>For Pothole Details, Select A Status: </h3>
            <div class="dropdown">
                    <div class="dropdown-content">
                        <select id="currentStatus" name="currentStatus" v-model="selectedOption">
                            <option value="">All</option>
                            <option value="Reported">Reported</option>
                            <option value="Inspected">Inspected</option>
                            <option value="Repaired">Repaired</option>
                            <option value="Deleted">Deleted</option>
                        </select>

                        <button v-on:click="getFilteredList()">Apply Filter</button>

                    </div>
            </div>
    </div>
    
    <div class="pothole-container">
        <PotholeDetail v-bind:key="pothole.id" v-for="pothole in filteredList" v-bind:pothole="pothole" />
    </div>

</template>

<script>
import PotholeDetail from "../components/PotholeDetail.vue";
import PotholeService from "../services/PotholeService.js";
import MapBox from "./MapBox.vue";

export default {

    data() {
        return {
            selectedOption: '',
            filteredList: this.$store.state.potholeList
        }
    },

    computed: {
        //call the PotholeService.js here
        potholeList() {
            return this.$store.state.potholeList
            //PotholeService.getPotholeList()
        },
    },
    components: {
        PotholeDetail,
        MapBox
    },
    methods: {

        getFilteredList() {
            this.filteredList = this.$store.state.potholeList.filter((pothole) => { return pothole.currentStatus.includes(this.selectedOption) } );
            //return this.$store.state.potholeList.filter((pothole) => { return pothole.currentStatus.includes(this.selectedOption) } );

        },
        created() {
            this.$store.commit("UPDATE_POTHOLE_LIST");
            this.getFilteredList();
        },

    }
}
</script>

<style>
.pothole-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}

.filter {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
}

h3 {
    padding-right: 10px;
}

#currentStatus {
    margin-right: 10px;
}
</style>