<template>
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
                            color="blue lighten-1 text-capitalize"
                            depressed
                            large
                            block
                            dark
                            class="mb-3"
                            @click="loginSubmit"
                        >
                            Login
                        </v-btn>
                        <v-btn
                            @click="addUserShow"
                            color="blue lighten-1 text-capitalize"
                            depressed
                            large
                            block
                            dark
                        >
                            Sign Up
                        </v-btn>
                    </div>
                </v-card>
            </v-flex>
        </v-container>
    </v-dialog>
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
                alert("로그인 성공");
                this.loginModal = false;
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
      },
  },

};
</script>