<template>
    <v-container class="update-container">
        <h2>내정보 수정</h2>
        <v-form class="update-form" @submit="submitForm">
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field
                        label="아이디"
                        v-model="id"
                        required
                        readonly
                    ></v-text-field>
                </v-col>
                <!--                <v-col cols="12" md="12">-->
                <!--                    <v-text-field label="비밀번호" v-model="password" type="password" required></v-text-field>-->
                <!--                </v-col>-->
                <!--                <v-col cols="12" md="12">-->
                <!--                    <v-text-field label="비밀번호확인" v-model="passwordcheck" type="password" required></v-text-field>-->
                <!--                </v-col>-->
            </v-row>
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field
                        label="이름"
                        v-model="name"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field
                        label="핸드폰 번호"
                        v-model="phoneNumber"
                        required
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="5">
                    <v-text-field
                        label="이메일"
                        v-model="emailId"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols="12" md="1"> @ </v-col>
                <v-col class="email-domain" cols="12" md="6">
                    <v-text-field v-model="emailDomain" required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6">
                    <v-text-field
                        label="생일"
                        v-model="birth"
                        type="date"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field
                        label="닉네임"
                        v-model="nickname"
                        required
                    ></v-text-field>
                </v-col>
            </v-row>
            <v-btn class="signup-btn" @click="update" color="primary"
                >수정완료</v-btn
            >
        </v-form>
    </v-container>
</template>

<script>
import { mapState } from "vuex";
import http from "@/axios/http";
export const geturl = "/member/info";
export const updateurl = "/member/update";
const memberStore = "memberStore";
export default {
    data() {
        return {
            updateUserInfo: null,
            id: "",
            // password: '',
            // passwordcheck: '',
            name: "",
            phoneNumber: "",
            emailId: "",
            emailDomain: "",
            birth: "",
            nickname: "",
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    created() {
        this.getMemberInfo();
        this.id = this.updateUserInfo.memberId;
    },
    methods: {
        getMemberInfo() {
            http.get(geturl + "/" + this.userInfo.data.memberId)
                .then((response) => {
                    this.updateUserInfo = response.data;
                    this.id = this.updateUserInfo.data.memberId;
                    this.name = this.updateUserInfo.data.memberName;
                    this.phoneNumber = this.updateUserInfo.data.memberPhone;
                    this.emailId = this.updateUserInfo.data.emailId;
                    this.emailDomain = this.updateUserInfo.data.emailDomain;
                    this.birth = this.updateUserInfo.data.birth;
                    this.nickname = this.updateUserInfo.data.nickname;
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        update() {
            if (
                this.id === "" ||
                this.name === "" ||
                this.phoneNumber === "" ||
                this.emailId === "" ||
                this.emailDomain === "" ||
                this.birth === "" ||
                this.nickname === ""
            ) {
                alert("값을 모두 입력해주세요");
                return;
            }

            let updateForm = {};
            updateForm.id = this.id;
            updateForm.name = this.name;
            updateForm.phoneNumber = this.phoneNumber;
            updateForm.emailId = this.emailId;
            updateForm.emailDomain = this.emailDomain;
            updateForm.birth = this.birth;
            updateForm.nickname = this.nickname;
            http.post(updateurl, updateForm).then((response) => {
                if (response.status == 202) {
                    alert("수정 완료");
                } else {
                    alert("수정 실패");
                }
                this.$router.push({ name: "home" });
            });
        },
    },
};
</script>
<style scoped>
.update-container {
    max-width: 400px;
    margin-top: 30px;
}

.update-form {
    margin-top: 20px;
}

.signup-btn {
    margin-top: 20px;
}
</style>