<template>
    <v-app-bar app color="grey-light" flat>

        <!-- 좌 상단 홈 버튼 -->
        <v-col cols="auto">
            <v-btn elevation="2" size="x-large" :to="{ name: 'home' }" class="white"><v-img src="@/assets/img/logo.png" :width="100" :to="{ name: 'home' }"></v-img></v-btn>
        </v-col>


        <!-- 중앙 메뉴 탭 -->
        <v-tabs centered class="ml-n9" color="black darken-1">
            <v-tab :to="{ name: 'home' }" >Home</v-tab>
            <v-tab :to="{ name: 'article' }">게시판</v-tab>
            <!--v-tab :to="{ name: 'notice' }">공지사항</!--v-tab>
            <v-tab-- :to="{ name: 'qna' }">Q&A</v-tab-->
            <v-tab :to="{ name: 'tour' }">여행정보</v-tab>
            <v-tab :to="{ name: 'plan' }">여행계획</v-tab>
        </v-tabs>

        <!-- 우측 회원 관리 창 -->
        <v-avatar
            class="hidden-sm-and-down"
            color="grey darken-1 shrink"
            size="32"
            style="margin-right: 10px;"
        ><img src="https://cdn.vuetifyjs.com/images/john.jpg" alt=""></v-avatar>

        <div class="text-center">
            <v-menu
                v-model="menu"
                :close-on-content-click="false"
                :nudge-width="200"
                offset-x
            >
                <template v-slot:activator="{ on, attrs }">
                    <v-btn
                        color="blue lighten-1 text-capitalize"
                        dark
                        v-bind="attrs"
                        v-on="on"
                        @click="isLogin ? (menu = true) : openLoginModal()"
                    >
                        {{ isLogin ? userInfo.data.memberId : 'Login' }}
                    </v-btn>
                </template>

                <v-card>
                    <v-list v-if="isLogin">
                        <v-list-item>
                            <v-list-item-avatar>
                                <img
                                    src="https://cdn.vuetifyjs.com/images/john.jpg"
                                    alt=""
                                >
                            </v-list-item-avatar>

                            <v-list-item-content>
                                <v-list-item-title> {{ isLogin ? userInfo.data.memberId : 'name' }}</v-list-item-title>
                                <v-list-item-subtitle>{{ isLogin ? userInfo.data.nickname : 'userId' }}</v-list-item-subtitle>
                            </v-list-item-content>

                        </v-list-item>

                        <v-divider></v-divider>

                        <v-list-item :to="{ name: 'update' }">
                            <v-list-item-content class="pl-3">
                                <v-list-item-title>내 정보 수정</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item :to="{ name: 'pwdupdate'}">
                            <v-list-item-content class="pl-3">
                                <v-list-item-title>비밀번호 변경</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item @click="showConfirmationDialog = true">
                            <v-list-item-content class="pl-3" >
                                <v-list-item-title>회원 탈퇴하기</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn
                            color="primary"
                            text
                            @click="onClickLogout"
                        >
                            Logout
                        </v-btn>
                    </v-card-actions>
                    </v-list>
                    <v-dialog v-model="showConfirmationDialog" max-width="500">
                        <v-card>
                            <v-card-title class="headline">회원 탈퇴</v-card-title>
                            <v-card-text>
                                정말로 회원을 탈퇴하시겠습니까?
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="red darken-1" text @click="showConfirmationDialog = false">취소</v-btn>
                                <v-btn color="green darken-1" text @click="onClickDeleteMember">확인</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog> 
                </v-card>
            </v-menu>
            <member-login ref="loginOverlay" @showSignup="openSignupModal"></member-login>
            <member-signup ref="signupOverlay" @showLogin="openLoginModal"></member-signup>
        </div>
    </v-app-bar>
</template>

<script>
// import http from "@/axios/http";
import { mapState, mapActions, mapGetters } from "vuex";
import MemberLogin from '@/components/user/MemberLogin.vue';
import MemberSignup from '@/components/user/MemberSignup.vue'
const memberStore = "memberStore";
export default {
    name: 'HeaderNaviBar',
    components: {
        MemberLogin,
        MemberSignup
    },
    data: () => ({
        menu: null,
        selectedTab: null,
        showConfirmationDialog: false,
    }),
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        ...mapGetters(['checkUserInfo']),
    },
    methods : {
        ...mapActions(memberStore, ["userLogout"]),

        onClickLogout() {
            this.menu = false;
            this.userLogout(this.userInfo.data.memberId);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/") this.$router.push({ name: "home" });
        },
        onClickDeleteMember(){
            this.showConfirmationDialog = false;
            this.$router.push({ name: "delete" });
        },
        async openLoginModal() {
            this.$refs.loginOverlay.openLoginModal();
        },
        async openSignupModal() {
            this.$refs.signupOverlay.openSignupModal();
        },
    }
};
</script>

<style>
router-link {
    text-decoration: none;
}
</style>