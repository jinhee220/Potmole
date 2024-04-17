<template>
    <body class="main">
        <div class="employee">
            <form v-on:submit.prevent="submitForm">
                <div class="immutable_data">
                    <p id="pothole_id"><strong>Pothole ID: </strong>{{ updatedPothole.potHoleId }}</p>
                    <p id="longitude"><strong>Longitude: </strong>{{ updatedPothole.longitude }}</p>
                    <p id="latitude"><strong>Latitude: </strong>{{ updatedPothole.latitude }}</p>
                    <p id="addressStreet"><strong>Street: </strong>{{ deliminatedAddress.street }}</p>
                    <p id="addressCityState"><strong>City, State: </strong>{{ deliminatedAddress.city }} {{
                        deliminatedAddress.state }}</p>
                    <p id="addressCountry"><strong>Country: </strong>{{ deliminatedAddress.country }}</p>
                </div>
                <div class="mutable_data">
                    <div class="statusField">
                        <label for="currentStatus"><strong>Status: </strong></label>
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
                    <p><strong>Reported Date: </strong>{{ updatedPothole.reportedDate }}</p>

                    <div class="field">
                        <label for="inspectedDate"><strong>Date Inspected: </strong></label>
                        <input type="text" id="inspectedDate" name="inspectedDate" v-model="updatedPothole.inspectedDate" />
                    </div>
                    <div class="field">
                        <label for="repairedDate"><strong>Date Repaired: </strong></label>
                        <input type="text" id="repairedDate" name="repairedDate" v-model="updatedPothole.repairedDate" />
                    </div>
                    <div class="severityField">
                        <label for="severity"><strong>Severity: </strong></label>
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
                    <div class="button-container">
                        <button type="submit" class="button-form">Submit</button>
                        <button type="button" class="button-form" v-on:click="hardDeletePothole">Hard Delete</button>
                    </div>
                </div>
            </form>
            <!-- <div>
            <button type="button" class="button-form" v-on:click="hardDeletePothole">Delete</button>
        </div> -->
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
        splitAddress() {
            const addressArray = this.pothole.streetAddress.split(', ')
            if (addressArray.length > 0) {
                this.deliminatedAddress.street = addressArray[0];
                this.deliminatedAddress.city = addressArray[1];
                this.deliminatedAddress.state = addressArray[2];
                this.deliminatedAddress.country = addressArray[3];
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
.button-container {
    display: flex;
    flex-direction: row;
    gap: 20px;
    margin-bottom: 15px;
    padding-top: 10px;
}

.button-form {
    background-color: #FFBD9B;
    box-shadow: gray 2px 2px 2px;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    color: black;
    font-weight: bold;
}

button:hover {
    background-color: #D68548;
}

#repairedDate {
    margin-left: 7px;
}

.severityField {
    display: flex;
    flex-direction: row;
}

#severity {
    margin-left: 5px;
}

.statusField {
    display: flex;
    flex-direction: row;
}

.btn {
    margin: 10px 0px;
}

h3 {
    margin: 10px;
    padding: 0px;
}

#currentStatus {
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
    border-radius: 10px;
    box-shadow: gray 5px 5px 5px;
    background-color: white;
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

@media screen and (max-width: 400px) {
    .field {
        text-align: center;
    }

	
}
</style>