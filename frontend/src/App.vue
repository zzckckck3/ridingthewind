<template>
    <div id="app">
        <v-app>
            <v-main>
                <navi-bar />
                <router-view />
                <footer-bar />
            </v-main>
        </v-app>
    </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNavi.vue";
import FooterBar from "@/components/layout/FooterNavi.vue";

export default {
    name: "App",

    data: () => ({
        //
    }),
    components: {
        NaviBar,
        FooterBar,
    },
};
</script>
