<template>
    <v-app>
        <v-main>
            <navi-bar />
            <router-view />
        </v-main>
    </v-app>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNavi.vue";

export default {
    name: "App",

    data: () => ({
        //
    }),
    components: {
        NaviBar,
    },
};
</script>
