<template>
    <div class="employee">
        <form v-on:submit.prevent="submitForm">
            <h3>Pothole ID: {{ updatedPothole.potHoleId }}</h3>
            <div class="field">
                <label for="currentStatus">Status: </label>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <select id="currentStatus" name="currentStatus" v-model="updatedPothole.currentStatus"
                            @change="toggleDropdown">
                            <option value="reported">Reported</option>
                            <option value="inspected">Inspected</option>
                            <option value="repaired">Repaired</option>
                            <option value="deleted">Deleted</option>
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
            <div class="field">
                <label for="severity">Severity: </label>
                <input type="text" id="severity" name="severity" v-model="updatedPothole.severity" />
            </div>
            <button type="submit">Submit</button>
        </form>
        <div>
            <button type="button" class="btn btn-danger" v-on:click="hardDeletePothole">Delete</button>
        </div>
    </div>
</template>

<script>

import PotholeService from '../services/PotholeService';

export default {
    data() {
        return {
            showForm: false,
            isDropdownOpen: false,
        }
    },
    props: {
        // bringing in pothole object from ReportPothole
        pothole: {
            type: Object,
            required: true,
        },
    },
    computed: {
        updatedPothole() {
            return {
                potHoleId: this.pothole.potHoleId,
                userId: this.$store.state.user.id,
                //address: this.pothole.address,
                longitude: this.pothole.longitude,
                latitude: this.pothole.latitude,
                currentStatus: this.pothole.currentStatus,
                reportedDate: this.pothole.reportedDate,
                inspectedDate: this.pothole.inspectedDate,
                repairedDate: this.pothole.repairedDate,
                severity: this.pothole.severity
            }
        }

    },
    methods: {
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
    // computed: {

    //     // Since props are a read-only, creating an object that can be acted on
    //     updatePothole() {
    //         return {
    //             userId: this.pothole.userId,
    //             longitude: this.pothole.longitude,
    //             latitude: this.pothole.latitude,
    //             currentStatus: "Reported",
    //             reportedDate: this.pothole.reportedDate,
    //             inspectedDate: this.pothole.inspectedDate,
    //             repairedDate: this.pothole.repairedDate,
    //         }
    //     },
    // },
}
</script>

<style scoped></style>