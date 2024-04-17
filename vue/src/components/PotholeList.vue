<template>
    <div class="container">
        <div class="title-map-filter">
            <div class="map">
                <!-- Call MapBox component and set parentComponent prop -->
                <MapBox class="mapbox" parent-component="PotholeList" v-bind:filteredList="filteredList"/>
            </div>

            <div class="filter">
                <h3>Search By Status: </h3>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <select id="currentStatus" name="currentStatus" v-model="selectedOption">
                            <option value="">All</option>
                            <option value="Reported">Reported</option>
                            <option value="Inspected">Inspected</option>
                            <option value="Repaired">Repaired</option>
                            <!-- <option value="Deleted">Deleted</option> -->
                        </select>

                        <button v-on:click="getFilteredList()"><strong>Apply Filter</strong></button>

                    </div>
                </div>
            </div>
        </div>

        <div class="pothole-container">
            <PotholeDetail v-bind:key="pothole.id" v-for="pothole in filteredList" v-bind:pothole="pothole" />
        </div>
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
    watch: {
        potholeList() {
            // Re-add markers whenever potholeList changes
            this.getFilteredList();
        }
    },
    methods: {

        getFilteredList() {
            this.filteredList = this.$store.state.potholeList.filter((pothole) => { return pothole.currentStatus.includes(this.selectedOption) });
            //return this.$store.state.potholeList.filter((pothole) => { return pothole.currentStatus.includes(this.selectedOption) } );

        },

    },
    created() {
        this.$store.commit("UPDATE_POTHOLE_LIST");
        this.getFilteredList();
    },
}
</script>

<style scoped>
.container {
    display: block;
    background: #bbbab9;

}

.title-map-filter {
    background-color: white;
    padding-bottom: 20px;
    display: grid;
}

.pothole-container {
    background: transparent;
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
    max-width: 85%;
    margin: auto;
}

/* .map {
    width: 95%;
    margin: auto;
    /* border: black 3px solid; 
}
*/

.mapbox {
    width: 90%;
    margin: auto;
}

.filter {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    padding: 0px 5px;
    margin: auto;
}

h3 {
    font-size: larger;
    padding-right: 10px;
}

#currentStatus {
    margin-right: 10px;
}

select {
    width: 6rem;
}



button {
    background-color: #D68548;
    background-color: #FFBD8B;
    box-shadow: gray 2px 2px 2px;
    padding: 5px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

@media screen and (max-width: 400px) {}
</style>