<script>
export default {
    props: {
        countNr: Number,
        data: Object,
        id: Number
    },


    data() {
        return {
            isHovered: false
        }
    },


    methods: {
        removeIdKey(obj) {

            const copy = { ...obj }

            delete copy.id

            return copy
        },

        getRowClass() {
            return this.countNr % 2 !== 0 ? "odd-row" : ""
        },

        formatDate() {
            if (this.data) {
                this.data.created_at = new Date(this.data.created_at).toLocaleString()
            }
        },

        handleMouseOut() {
            console.log(this.data)
            this.isHovered = false
        },

        handleMouseOver() {
            this.isHovered = true;
        },

        deleteEntity() {
            let result = confirm("Are you sure you want to delete this secret contact?");
            if (result) {
                this.$emit('entity-deleted', this.id)
            }
        }
    },


    created() {
        this.formatDate()
    },

    updated() {
        this.formatDate()
    }
}
</script>


<template>
    <tr :class="getRowClass()" class="entity" @mouseover="handleMouseOver" @mouseout="handleMouseOut">
        <td>{{ countNr }}</td>

        <td v-for="(value, key) in removeIdKey(data)">{{ value != null ? value : "-" }}</td>

        <td class="pt-2 trash"><svg :class="{ hovering: isHovered }" @click="deleteEntity"
                xmlns="http://www.w3.org/2000/svg" height="16" width="14" viewBox="0 0 448 512">
                <path
                    d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
            </svg></td>
    </tr>
</template>


<style>
.trash {
    fill: rgb(162, 161, 161);
    cursor: pointer;
}

.hovering {
    fill: black !important
}

.entity:hover {
    background-color: yellow;
}

.odd-row {
    background-color: hsla(0, 1%, 18%, 0.052);
}
</style>