<template>
    <v-app-bar app color="white" flat>
        <v-avatar
            :color="
                $vuetify.breakpoint.smAndDown ? 'black darken-1' : 'transparent'
            "
            size="32"
        ></v-avatar>

        <v-tabs centered class="ml-n9" color="black darken-1">
            <v-tab :to="{ name: 'home' }">홈</v-tab>
            <v-tab :to="{ name: 'signin' }">로그인</v-tab>
            <v-tab :to="{ name: 'boardList' }">공지사항</v-tab>
        </v-tabs>

        <v-avatar
            class="hidden-sm-and-down"
            color="grey darken-1 shrink"
            size="32"
        ></v-avatar>
    </v-app-bar>
</template>

<script>
export default {
    data: () => ({
        links: ["공지사항", "로그인", "회원가입", "마이페이지", "로그아웃", "FAQS"],
    }),
};
</script>

<style>
router-link {
    text-decoration: none;
}
</style>