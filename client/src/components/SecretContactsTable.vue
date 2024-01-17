<script setup>
import { SECRET_CONTACTS_API } from '@/main';
import SecretContactsTableEntity from './SecretContactsTableEntity.vue';
import SecretContactsTableLabels from './SecretContactsTableLabels.vue';
</script>


<script>
export default {

    data() {
        return {
            initialSecretContactsData: [],
            filteredAndSortedSecretContactsData: [],
            extractedLabes: [],
            sortBy: null,
            sortDirection: 'asc',
            searchTerm: '',
            error: null,
            nameInput: '',
            codeInput: '',
            phoneNumberInput: '',
        }
    },

    created() {
        this.fetchSecretContacts()
    },

    methods: {
        async fetchSecretContacts() {
            try {
                const response = await fetch(SECRET_CONTACTS_API, { method: "GET" });
                const parsedResponse = await response.json()

                this.initialSecretContactsData = parsedResponse.body.data.secret_contacts
                this.filteredAndSortedSecretContactsData = parsedResponse.body.data.secret_contacts

                this.extractedLabels = this.extractLabels()

            } catch (error) {
                this.error = error
            }
        },


        extractLabels() {
            if (this.initialSecretContactsData.length > 0) {
                const labels = Object.keys(this.initialSecretContactsData[0]).filter((a) => a !== "id")

                labels.unshift("")

                return labels
            }

        },


        updateFilteredAndSortedData(label) {
            const searchLower = this.searchTerm.toLowerCase();

            let filteredData = []

            if (searchLower == "") {
                filteredData = this.initialSecretContactsData
            } else {
                filteredData = this.initialSecretContactsData.filter((contact) => {
                    return Object.values(contact).some((value) => {
                        if (typeof value === 'string' || value instanceof String) {
                            return value.toLowerCase().includes(searchLower);
                        }
                        return false;
                    });
                });
            }

            if (label !== null) {

                //if clicked the third time, unsort to initial data
                if (this.sortBy === label && this.sortDirection == "desc") {
                    this.sortDirection = "asc"
                    this.filteredAndSortedSecretContactsData = filteredData
                    this.sortBy = null
                    return
                }

                //if clicked the second time, sort desc
                if (this.sortBy === label && this.sortDirection == "asc") {
                    this.sortDirection = "desc"
                }

                //if clicked the first time, sort asc
                if (this.sortBy !== label) {
                    this.sortDirection = "asc"
                    this.sortBy = label
                }
            }

            this.filteredAndSortedSecretContactsData = filteredData.slice().sort((a, b) => {
                const order = this.sortDirection === 'asc' ? 1 : -1;
                if (label === 'created_at') {
                    const dateA = new Date(a[label]);
                    const dateB = new Date(b[label]);
                    return dateA > dateB ? order : -order;
                } else {
                    return a[label] > b[label] ? order : -order;
                }
            });
        },


        async addSecretContact() {
            if (this.inputsAreNotFilled) {
                return
            }

            const queryParams = new URLSearchParams({
                name: this.nameInput,
                code: this.codeInput,
                phone_number: this.phoneNumberInput
            }).toString();

            try {

                await fetch(SECRET_CONTACTS_API + "?" + queryParams, { method: "POST" });

            } catch (error) {
                this.error = error
            }

            await this.fetchSecretContacts()
            this.nameInput = ""
            this.codeInput = ""
            this.phoneNumberInput = ""
        },


        async deleteEntity(id) {
            const queryParams = new URLSearchParams({
                id: id
            }).toString();

            try {
                await fetch(SECRET_CONTACTS_API + "?" + queryParams, { method: "DELETE" });
            } catch (error) {
                this.error = error
            }

            this.fetchSecretContacts();
        }
    },


    computed: {
        inputsAreNotFilled() {
            return (this.nameInput.trim() == '' || this.codeInput.trim() == '' || this.phoneNumberInput.trim() == '')
        }
    }
}
</script>

<template>
    <div class="search-bar-div w-full flex justify-center items-center">
        <div class="flex-1 flex pl-2">
            <input class="search-bar input" type="text" v-model="searchTerm" @input="updateFilteredAndSortedData"
                placeholder="Search for contacts...">
        </div>


        <div class="flex-1 flex justify-end items-center pr-5">
            <input type="text" class="input h-[40px]" placeholder="Name" v-model="nameInput">
            <input type="text" class="input h-[40px]" placeholder="Code" v-model="codeInput">
            <input type="text" class="input h-[40px]" placeholder="Phone number" v-model="phoneNumberInput">
            <button class="add-contact-button" @click="addSecretContact" :disabled="inputsAreNotFilled">Add</button>
        </div>
    </div>


    <div class="border-t border-solid h-[80vh] w-full flex justify-center items-center"
        v-if="initialSecretContactsData.length == 0 && error != null">
        An error has occured. Please try again later.
    </div>


    <div v-if="filteredAndSortedSecretContactsData.length > 0" class="border-t  border-solid">
        <table class="w-full">
            <SecretContactsTableLabels :sortDirection="sortDirection" :activeLabel="sortBy" :labels="extractedLabels"
                @label-clicked="updateFilteredAndSortedData" />

            <tbody>
                <SecretContactsTableEntity v-for="(contact, index) in filteredAndSortedSecretContactsData"
                    @entity-deleted="deleteEntity(contact.id)" :key="contact.id" :countNr="index + 1" :data="contact" />
            </tbody>
        </table>
    </div>
    <div v-else>
        <div class="border-t border-solid h-[80vh] w-full flex justify-center items-center">
            No secret contacts found
        </div>
    </div>
</template>


<style>
.add-contact-button {
    padding: 10px;
    border: 1px solid grey;
    border-radius: 5px;
    height: 40px;
}

.add-contact-button:not(:disabled):hover {
    border: 1px solid black;
}

.add-contact-button:disabled {
    background-color: #bdc3c7;
    opacity: 0.7;
    cursor: not-allowed;
}

.search-bar-div {
    height: 65px;
    position: relative;
}


.input {
    border: 1px solid grey;
    border-radius: 5px;
    padding-left: 10px;
    margin: 10px;
}

.search-bar {
    height: 40px;
    width: 35vw;
    max-width: 400px;
    min-width: 150px;
}

.input:focus {
    outline: none;
    border: 1px solid black;
}

.input::placeholder {
    font-size: 17px;
}

@media (min-width: 600px) {
    .search-bar-div {
        height: 80px;
    }
}
</style>