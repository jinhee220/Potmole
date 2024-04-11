<template>
    <div class="employee">
        <button v-on:click.prevent="toggleForm">Toggle Form</button>
        <form v-show="showForm" v-on:submit.prevent="submitForm">
            <div class="field">
                <label for="currentStatus">Status: </label>
                <select id="currentStatus" name="currentStatus" size="4" v-model="updatedPothole.currentStatus">
                    <option value="reported">Reported</option>
                    <option value="inspected">Inspected</option>
                    <option value="repaired">Repaired</option>
                    <option value="deleted">Deleted</option>
                </select>
            </div>
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
    </div>
</template>

<script>

import PotholeService from '../services/PotholeService';

export default {
    data() {
        return {
            showForm: false,
            updatedPothole: {
                potHoleId: this.pothole.potHoleId,
                userId: this.$store.state.user.id,
                longitude: this.pothole.longitude,
                latitude: this.pothole.latitude,
                currentStatus: "",
                reportedDate: this.pothole.reportedDate,
                inspectedDate: "",
                repairedDate: "",
                severity: ""
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
    methods: {
        toggleForm() {
            this.showForm = !this.showForm;
        },
        submitForm() {

            PotholeService
                .updatePothole(this.updatedPothole)
                .then(response => {

                    if (response.status === 200) {
                        //this.$store.commit();
                        this.$router.push({ name: "home" });
                    }
                })
                .catch(error => {
                    this.handleErrorResponse(error, 'updating');
                });

        },
        handleErrorResponse(error, verb) {
            // store implementation needed to proceed
        }
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