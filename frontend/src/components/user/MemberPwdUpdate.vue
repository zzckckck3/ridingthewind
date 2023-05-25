<template>
    <v-container class="signup-container">
        <v-form @submit="changePassword" class="mt-16 mb-16">
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field
                        v-model="currentPassword"
                        label="현재 비밀번호"
                        type="password"
                        required
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field
                        v-model="newPassword"
                        label="새 비밀번호"
                        type="password"
                        required
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field
                        v-model="confirmNewPassword"
                        label="새 비밀번호 확인"
                        type="password"
                        required
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    <v-btn @click="changePassword" color="indigo"><h4 style="color: white;">비밀번호 변경</h4></v-btn>
                </v-col>
            </v-row>
        </v-form>
    </v-container>
</template>

<script>
import http from "@/axios/http";
import { mapState } from "vuex";

const pwdcheckurl = "/member/pwdcheck";
const setpwdurl = "/member/setpwd";
const memberStore = "memberStore";
export default {
    data() {
        return {
            currentPassword: "",
            newPassword: "",
            confirmNewPassword: "",
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        changePassword() {
            let userid = this.userInfo.data.memberId;
            http.get(pwdcheckurl, {
                params: {
                    userId: userid,
                    userPassword: this.currentPassword,
                },
            }).then((response) => {
                if (response.data !== "SUCCESS") {
                    alert("현재 비밀번호가 일치하지 않습니다.");
                    return;
                } else if (this.newPassword !== this.confirmNewPassword) {
                    alert("새 비밀번호가 일치하지 않습니다.");
                    return;
                } else {
                    http.post(setpwdurl, {
                        userId: this.userInfo.data.memberId,
                        userPassword: this.newPassword,
                    }).then((response) => {
                        console.log(response);
                    });
                    alert("비밀번호 변경이 완료되었습니다.");
                    if (this.$route.path != "/")
                        this.$router.push({ name: "home" });
                }
            });
        },
    },
};
</script>

<style scoped>
.signup-container {
    max-width: 400px;
    margin: 0 auto;
}
</style>