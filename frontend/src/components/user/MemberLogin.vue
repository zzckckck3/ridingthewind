<template>
    <div>
    <!-- Login Component -->
    <v-dialog v-model="loginModal" max-width="550">
        <v-container style="max-width: 550px; padding: 0" fill-height>
            <v-flex xs12>
                <v-card>
                    <div class="pa-10">
                        <h1 style="text-align: center" class="mb-10">Login</h1>
                        <v-text-field
                            label="ID"
                            prepend-inner-icon="mdi-account"
                            v-model="user.userId"
                            required
                        ></v-text-field>
                        <v-text-field
                            prepend-inner-icon="mdi-lock"
                            type="password"
                            label="Password"
                            v-model="user.userPwd"
                            required
                        >
                        </v-text-field>
                        <v-btn
                            type="button"
                            color="indigo"
                            depressed
                            large
                            block
                            dark
                            class="mt-3 mb-4"
                            @click="loginSubmit"
                        >
                            로그인
                        </v-btn>
                        <v-btn
                            @click="addUserShow"
                            color="indigo"
                            depressed
                            large
                            block
                            dark
                        >
                            회원가입
                        </v-btn>
                    </div>
                </v-card>
            </v-flex>
        </v-container>
    </v-dialog>
    <v-snackbar
        v-model="snackbar"
        :timeout="1500"
        >
        {{ `${user.userId}님 환영합니다.`}}

        <template v-slot:action="{ attrs }">
            <v-btn
            color="blue"
            v-bind="attrs"
            @click="snackbar = false"
            >
            Close
            </v-btn>
        </template>
    </v-snackbar>
    </div>
</template>

<script>
// import http from "@/axios/http";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";
export const loginurl = "/member/login";
export default {

    data() {
        return {
            user: {
                userId: "",
                userPwd: "",
            },
            loginModal: false,
            snackbar: false
        };
    },
    components: {},
    computed: {
        ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
        async loginSubmit() {
            await this.userConfirm(this.user);
            let token = sessionStorage.getItem("access-token");
            if (this.isLogin) {
                await this.getUserInfo(token);
                this.loginModal = false;
                this.snackbar = true;
            } else {
                alert("아이디와 비밀번호를 확인해주세요");
            }
        },
        addUserShow() {
            this.loginModal = false;
            this.$emit("showSignup");
        },
        openLoginModal() {
            this.user.userId = '',
            this.user.userPwd = '',
            this.loginModal = true;
            this.snackbar = false;
        },
    },

};
</script>

<style scoped>
</style>