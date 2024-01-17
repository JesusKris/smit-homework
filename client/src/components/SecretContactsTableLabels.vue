<script>
export default {
    props: {
        labels: Array,
        activeLabel: String,
        sortDirection: String
    },

    methods: {
        formatLabel(label) {
            if (label) {
                let editedLabel = label.replace("_", " ")

                if (editedLabel == "") {
                    return editedLabel
                }

                const words = editedLabel.split(" ");
                editedLabel = words.map((word) => {
                    return word[0].toUpperCase() + word.substring(1);
                }).join(" ");

                return editedLabel
            }

        },

        handleLabelClick(label) {
            this.$emit('label-clicked', label);
        }
    },
}
</script>


<template>
    <thead>
        <tr>
            <th class="label nexa-bold select-none" v-for="(value, key) in labels" :class="{ active: activeLabel == value }"
                :key="key" @click="handleLabelClick(value)">
                <div class="flex flex-row items-center">
                    {{ formatLabel(value) }}
                    <svg v-if="sortDirection === 'asc' && activeLabel == value" xmlns="http://www.w3.org/2000/svg"
                        height="16" width="12" viewBox="0 0 384 512">
                        <path
                            d="M214.6 41.4c-12.5-12.5-32.8-12.5-45.3 0l-160 160c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L160 141.2V448c0 17.7 14.3 32 32 32s32-14.3 32-32V141.2L329.4 246.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3l-160-160z" />
                    </svg>
                    <svg v-if="sortDirection === 'desc' && activeLabel == value" xmlns="http://www.w3.org/2000/svg"
                        height="16" width="14" viewBox="0 0 448 512">
                        <path
                            d="M413.1 222.5l22.2 22.2c9.4 9.4 9.4 24.6 0 33.9L241 473c-9.4 9.4-24.6 9.4-33.9 0L12.7 278.6c-9.4-9.4-9.4-24.6 0-33.9l22.2-22.2c9.5-9.5 25-9.3 34.3 .4L184 343.4V56c0-13.3 10.7-24 24-24h32c13.3 0 24 10.7 24 24v287.4l114.8-120.5c9.3-9.8 24.8-10 34.3-.4z" />
                    </svg>
                </div>
            </th>
        </tr>
    </thead>
</template>


<style>
.label {
    cursor: pointer;
    font-size: 20px;
}

.active {
    text-decoration: underline;
}
</style>