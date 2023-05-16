<template>
    <v-app id="">
        <div style="display:flex">
            <v-select
                label="시, 도"
                :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
                outlined
            ></v-select>
            <v-select
                class="ms-4"
                label="군, 구"
                :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
                outlined
            ></v-select>
        </div>
        <v-select
            clearable
            chips
            label="여행지 종류"
            :items="['California', 'Colorado', 'Florida', 'Georgia', 'Texas', 'Wyoming']"
            multiple
            outlined
        ></v-select>
        <v-btn>
            <v-icon
            color="red"
            large
            x-large
            ></v-icon>
        </v-btn>
        
    </v-app>
</template>



<script>
export default {
    name: 'TourSearchInfo',
    components: {},
    data() {
        return {
            message: '',
        };
    },
    created() {},
    methods: {},
};
</script>

<style scoped></style>