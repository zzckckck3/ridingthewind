<template>
    <v-app-bar app color="rgb(245, 245, 245);" height="75">
        <!-- 좌 상단 홈 버튼 -->
        <v-col cols="2" class="ms-5">
            <v-btn
                elevation="0"
                height="65"
                width="300"
                style="color:rgb(245, 245, 245);"
                :to="{ name: 'home' }"
                ><v-img
                    src="@/assets/img/logo.png"
                    :width="100"
                    :to="{ name: 'home' }"
                ></v-img
            ></v-btn>
        </v-col>

        <!-- 중앙 메뉴 탭 -->
        <v-tabs centered class="ml-n9" color="black darken-1" style="max-width: 68%;">
            <v-tab :to="{ name: 'home' }">Home</v-tab>
            <v-tab :to="{ name: 'article' }">게시판</v-tab>
            <v-tab :to="{ name: 'tour' }">여행정보</v-tab>
            <v-tab :to="{ name: 'plan' }">여행계획</v-tab>
        </v-tabs>
        <!-- 우측 회원 관리 창 -->

        <h5 class="me-5" color='black'>{{ isLogin ? `${userInfo.data.memberId}님 환영합니다.` : "" }}</h5>
        <div class="text-center">
            <v-menu
                v-model="menu"
                :close-on-content-click="false"
                :nudge-width="200"
                offset-x
            >
        
                <template v-slot:activator="{ on, attrs }" v-if="isLogin">
                    <v-btn
                        rounded
                        width="10"
                        x-small
                        v-bind="attrs"
                        v-on="on"
                        @click="menu = true"
                    >
                    <img src="@/assets/img/profile.png" alt=""  style="width: 40px; height: 40px; border-radius: 50%;"/>
                    </v-btn>
                </template>
                <template v-slot:activator="{ on, attrs }" v-else>
                    <v-btn
                        color="indigo"
                        style="margin-left:80px;"
                        dark
                        v-bind="attrs"
                        v-on="on"
                        @click="openLoginModal()"
                    >
                        {{ "Login" }}
                    </v-btn>
                </template>
                <v-card>
                    <v-list v-if="isLogin">
                        <v-list-item>
                            <v-list-item-avatar>
                                <img
                                    src="@/assets/img/profile.png"
                                    alt=""
                                />
                            </v-list-item-avatar>

                            <v-list-item-content>
                                <v-list-item-title>
                                    {{
                                        isLogin
                                            ? userInfo.data.memberId
                                            : "name"
                                    }}</v-list-item-title
                                >
                                <v-list-item-subtitle>{{
                                    isLogin ? userInfo.data.nickname : "userId"
                                }}</v-list-item-subtitle>
                            </v-list-item-content>
                        </v-list-item>

                        <v-divider></v-divider>

                        <v-list-item :to="{ name: 'update' }">
                            <v-list-item-content class="pl-3">
                                <v-list-item-title
                                    >내 정보 수정</v-list-item-title
                                >
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item :to="{ name: 'pwdupdate' }">
                            <v-list-item-content class="pl-3">
                                <v-list-item-title
                                    >비밀번호 변경</v-list-item-title
                                >
                            </v-list-item-content>
                        </v-list-item>
                        <v-list-item @click="showConfirmationDialog = true">
                            <v-list-item-content class="pl-3">
                                <v-list-item-title
                                    >회원 탈퇴하기</v-list-item-title
                                >
                            </v-list-item-content>
                        </v-list-item>

                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="primary" text @click="onClickLogout">
                                Logout
                            </v-btn>
                        </v-card-actions>
                    </v-list>
                    <v-dialog v-model="showConfirmationDialog" max-width="500">
                        <v-card>
                            <v-card-title class="headline"
                                >회원 탈퇴</v-card-title
                            >
                            <v-card-text>
                                정말로 회원을 탈퇴하시겠습니까?
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                    color="red darken-1"
                                    text
                                    @click="showConfirmationDialog = false"
                                    >취소</v-btn
                                >
                                <v-btn
                                    color="green darken-1"
                                    text
                                    @click="onClickDeleteMember"
                                    >확인</v-btn
                                >
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </v-card>
            </v-menu>
            <member-login ref="loginOverlay" @showSignup="openSignupModal"></member-login>
            <member-signup ref="signupOverlay" @showLogin="openLoginModal"></member-signup>
        </div>
        <v-snackbar
            v-model="snackbar"
            :timeout="1500"
            >
            {{ "정상적으로 로그아웃 되었습니다" }}

            <template v-slot:action="{ attrs }">
                <v-btn
                color="red"
                v-bind="attrs"
                @click="snackbar = false"
                >
                Close
                </v-btn>
            </template>
            </v-snackbar>
        </v-app-bar>
</template>

<script>
// import http from "@/axios/http";
import { mapState, mapActions, mapGetters } from "vuex";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberSignup from "@/components/user/MemberSignup.vue";
const memberStore = "memberStore";
export default {
    name: "HeaderNaviBar",
    components: {
        MemberLogin,
        MemberSignup,
    },
    data: () => ({
        menu: null,
        selectedTab: null,
        showConfirmationDialog: false,
        snackbar: false
    }),
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        ...mapGetters(["checkUserInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),

        onClickLogout() {
            this.menu = false;
            this.snackbar = true;
            this.userLogout(this.userInfo.data.memberId);
            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
            if (this.$route.path != "/") this.$router.push({ name: "home" });
        },
        onClickDeleteMember() {
            this.showConfirmationDialog = false;
            this.$router.push({ name: "delete" });
        },
        async openLoginModal() {
            this.$refs.loginOverlay.openLoginModal();
        },
        async openSignupModal() {
            this.$refs.signupOverlay.openSignupModal();
        },
        async openButtomSheet() {
            this.$refs.beforeloginBottomSheet.showAndHideButtomSheet();
        },
        
    },
};
</script>

<style>
router-link {
    text-decoration: none;
}
</style>