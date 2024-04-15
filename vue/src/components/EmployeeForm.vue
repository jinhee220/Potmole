<template>
    <body class="main">
    <div class="employee">
        <form v-on:submit.prevent="submitForm">
            <div class="immutable_data">
            <h3 id="pothole_id">Pothole ID: {{ updatedPothole.potHoleId }}</h3>
            <h3 id="longitude">Longitude: {{ updatedPothole.longitude }}</h3>
            <h3 id="latitude">Latitude: {{ updatedPothole.latitude }}</h3>
            <h3 id="addressStreet">Street: {{ deliminatedAddress.street }}</h3>
            <h3 id="addressCityState">City, State: {{ deliminatedAddress.city }} {{ deliminatedAddress.state }}</h3>
            <h3 id="addressCountry">Country: {{ deliminatedAddress.country }}</h3>
            </div>
            <div class="mutable_data">
            <div class="statusField">
                <label for="currentStatus">Status: </label>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <select id="currentStatus" name="currentStatus" v-model="updatedPothole.currentStatus"
                            @change="toggleDropdown">
                            <option value="Reported">Reported</option>
                            <option value="Inspected">Inspected</option>
                            <option value="Repaired">Repaired</option>
                            <option value="Deleted">Deleted</option>
                        </select>
                    </div>
                </div>
            </div>
            <h3>Reported Date: {{ updatedPothole.reportedDate }}</h3>

            <div class="field">
                <label for="inspectedDate">Date Inspected: </label>
                <input type="text" id="inspectedDate" name="inspectedDate" v-model="updatedPothole.inspectedDate" />
            </div>
            <div class="field">
                <label for="repairedDate">Date Repaired: </label>
                <input type="text" id="repairedDate" name="repairedDate" v-model="updatedPothole.repairedDate" />
            </div>
            <div class="severityField">
                <label for="severity">Severity: </label>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <select id="severity" name="severity" v-model="updatedPothole.severity"
                            @change="toggleDropdown">
                            <option value="Uninspected">Uninspected</option>
                            <option value="Mild">Mild</option>
                            <option value="Moderate">Moderate</option>
                            <option value="Severe">Severe</option>
                        </select>
                    </div>
                </div>
            </div>
            <button type="submit">Submit</button>
        </div>
        </form>
        <div>
            <button type="button" class="btn btn-danger" v-on:click="hardDeletePothole">Delete</button>
        </div>
    </div>
</body>
</template>

<script>

import PotholeService from '../services/PotholeService';

export default {
    data() {
        return {
            showForm: false,
            isDropdownOpen: false,
            deliminatedAddress: {
                street: '',
                city: '',
                state: '',
                country: ''
            }
        }
    },
    props: {
        // bringing in pothole object from ReportPothole
        pothole: {
            type: Object,
            required: true,
        },
    },
    updated() {
        this.splitAddress();
    },
    computed: {
        updatedPothole() {
            return {
                potHoleId: this.pothole.potHoleId,
                userId: this.$store.state.user.id,
                streetAddress: this.pothole.streetAddress,
                longitude: this.pothole.longitude,
                latitude: this.pothole.latitude,
                currentStatus: this.pothole.currentStatus,
                reportedDate: this.pothole.reportedDate,
                inspectedDate: this.pothole.inspectedDate,
                repairedDate: this.pothole.repairedDate,
                severity: this.pothole.severity
            }
        },
       

    },
    methods: {
        splitAddress(){
            const addressArray = this.pothole.streetAddress.split(', ')
            if (addressArray.length>0){
            this.deliminatedAddress.street = addressArray[0];
            this.deliminatedAddress.city=addressArray[1];
            this.deliminatedAddress.state=addressArray[2];
            this.deliminatedAddress.country=addressArray[3];
            }
        },
        // Toggle's whether the selection menu acts as a dropdown, activated on using the dropdown
        toggleDropdown() {
            this.isDropdownOpen = !this.isDropdownOpen;
        },
        toggleForm() {
            this.showForm = !this.showForm;
        },
        submitForm() {

            PotholeService
                .updatePothole(this.updatedPothole)
                .then(response => {

                    if (response.status === 200) {
                        //this.$store.commit();
                        alert("Pothole successfully updated!")
                        this.$router.go();
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, 'updating');
                });
        },
        hardDeletePothole() {
            PotholeService
                .deletePothole(this.updatedPothole.potHoleId)
                .then(response => {

                    if (response.status === 204) {
                        alert("Pothole successfully deleted!");
                        this.$router.go();
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, 'deleting');
                });

        },
        handleErrorResponse(error, verb) {
            // store implementation needed to proceed
        },

    },
}
</script>

<style scoped>

#repairedDate {
    margin-left: 7px;
}
.severityField{
    display: flex;
    flex-direction: row;
}

#severity{
    margin-left: 5px;
}

.statusField{
    display: flex;
    flex-direction: row;
}

.btn{
    margin: 10px 0px;
}

h3 {
    margin: 10px;
    padding: 0px;
}

#currentStatus{
    margin-left: 5px;
}

.immutable_data {
    display: grid;
    grid-template-columns: 1fr 1fr;
    grid-template-rows: 1fr 1fr 1fr;
    grid-template-areas:
    "pothole_id addressStreet"
    "longitude addressCityState"
    "latitude addressCountry";
    text-align: center;
    }
#pothole_id {
    grid-area: pothole_id;
}
#longitude {
    grid-area: longitude;
}
#latitude {
    grid-area: latitude;
}
#addressStreet {
    grid-area: addressStreet;
}
#addressCityState {
    grid-area: addressCityState;
}
#addressCountry {
    grid-area: addressCountry;
}
.employee {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 80%;
    border: 1px solid black;
    border-radius: 10px;
    box-shadow: gray 5px 5px 5px ;
}

.mutable_data {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
}

.main {
    display: flex;
    flex-direction: column;
    align-items: center;
}
</style>