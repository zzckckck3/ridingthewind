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
                        <v-row v-if="faillogin" style='color: red' class="ml-3">
                            로그인에 실패하였습니다.
                            아이디와 비밀번호를 확인해주세요.
                        </v-row>
                        <v-btn
                            type="button"
                            color="blue lighten-1 text-capitalize"
                            depressed
                            large
                            block
                            dark
                            class="mb-3 mt-7"
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
        <v-bottom-sheet v-model="successlogin" inset hide-overlay>
            <v-sheet class="sheet" height="56px">
                <v-alert type="success">
                    로그인이 완료되었습니다.
                </v-alert>
            </v-sheet>
        </v-bottom-sheet>
    </v-dialog>
</template>

<script>
// import http from "@/axios/http";
import { mapState, mapActions } from "vuex";
import { currentRoute } from "vue-router";

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
            successlogin : false,
            faillogin : false,
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
                this.successlogin = true;
                this.loginModal = false;
                const currentPath = currentRoute.value.path;
                if (currentPath !== "/") {
                    this.$router.push({ name: "home" });
                }
            } else {
                this.faillogin = true;
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
          this.faillogin = false;
      },
  },
};
</script>