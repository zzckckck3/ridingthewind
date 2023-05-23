<template>
    <v-dialog v-model="signupModal" max-width="450" persistent>
        <v-container class="signup-container" fill-height>
            <h2>회원 가입</h2>
            <v-form class="signup-form">
                <v-row>
                    <v-col cols="12" md="12">
                        <v-text-field
                            label="아이디"
                            v-model.lazy="id"
                            @blur="idcheck"
                            :error="iderror"
                            :success="idsuccess"
                            required
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="12">
                        <v-text-field
                            @blur="passwordcheckmethod"
                            label="비밀번호"
                            v-model="password"
                            type="password"
                            required
                        ></v-text-field>
                    </v-col>
                    <v-col cols="12" md="12">
                        <v-text-field
                            @blur="passwordcheckmethod"
                            label="비밀번호확인"
                            v-model="passwordcheck"
                            type="password"
                            required
                        ></v-text-field>
                    </v-col>
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
                        <v-text-field
                            v-model="emailDomain"
                            required
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col cols="12" md="12">
                        <v-text-field
                            label="인증번호 입력"
                            v-model="inputMailCode"
                            required
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-btn class="mr-5" @click="sendMail">
                        인증번호 발송
                    </v-btn>
                    <v-btn @click="checkMail"> 인증번호 확인 </v-btn>
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
                <v-btn class="signup-btn" @click="signup" color="primary"
                    >가입하기</v-btn
                >
                <v-btn
                    class="signup-btn ms-4"
                    @click="signupModal = false"
                    color="primary"
                    >취소</v-btn
                >
            </v-form>
        </v-container>
    </v-dialog>
</template>

<script>
import http from "@/axios/http";
export const signupurl = "/member/join";
const sendmailurl = "/member/sendmail";
const idcheckurl = "/member/check";
export default {
    data() {
        return {
            id: "",
            password: "",
            passwordcheck: "",
            name: "",
            phoneNumber: "",
            emailId: "",
            emailDomain: "",
            birth: "",
            nickname: "",
            mailCode: null,
            inputMailCode: "",
            idcheckvalue: false,
            pwdcheckvalue: false,
            emailcheckvalue: false,
            signupModal: false,
        };
    },
    computed: {
        iderror() {
            if (this.id.length === 0) return false;
            else if (this.id.length < 6 || !this.idcheckvalue) return true;
            else return false;
        },
        idsuccess() {
            if (this.id.length >= 6 && this.idcheckvalue) return true;
            else return false;
        },
    },
    methods: {
        idcheck() {
            const memberId = this.id;
            if (this.id.length === 0) return;
            http.get(idcheckurl + "/" + memberId).then((response) => {
                if (response.data === "SUCCESS") {
                    this.idcheckvalue = true;
                }
            });
        },
        passwordcheckmethod() {
            if (this.password === this.passwordcheck) this.pwdcheckvalue = true;
            else this.pwdcheckvalue = false;
        },
        signup() {
            if (
                this.id === "" ||
                this.password === "" ||
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

            if (!this.idcheckvalue) {
                alert("사용할 수 없는 아이디입니다.");
                return;
            } else if (!this.pwdcheckvalue) {
                alert("비밀번호가 일치하지 않습니다.");
                return;
            } else if (!this.emailcheckvalue) {
                alert("이메일 인증을 완료하지 않았습니다.");
                return;
            }

            let signupForm = {};
            signupForm.id = this.id;
            signupForm.password = this.password;
            signupForm.name = this.name;
            signupForm.phoneNumber = this.phoneNumber;
            signupForm.emailId = this.emailId;
            signupForm.emailDomain = this.emailDomain;
            signupForm.birth = this.birth;
            signupForm.nickname = this.nickname;

            http.post(signupurl, signupForm).then((response) => {
                if (response.status === 202) {
                    alert("성공");
                    this.signupModal = false;
                    this.$emit("showLogin");
                }else{
                    alert("실패");
                    this.$router.push({ name: "home" });
                }
            });
        },
        sendMail() {
            const email = this.emailId + "@" + this.emailDomain;
            http.get(sendmailurl + "/" + email).then((response) => {
                this.mailCode = response.data.toString();
                // sessionStorage.setItem()
                if (response.status === 202) {
                    alert("이메일이 전송되었습니다.");
                } else {
                    alert(
                        "이메일 전송에 실패하였습니다. 잠시 후 다시 시도해주세요"
                    );
                }
            });
            alert("메일전송");
        },
        checkMail() {
            if (this.mailCode !== null) {
                if (this.inputMailCode == this.mailCode) {
                    this.emailcheckvalue = true;
                    alert("회원인증이 완료되었습니다.");
                } else {
                    alert("인증코드가 잘못되었습니다. 다시 확인해주세요");
                }
            } else {
                alert("먼저 인증코드를 발송해주세요");
            }
        },
        openSignupModal() {
            this.id = '',
            this.password = '',
            this.passwordcheck = '',
            this.name = '',
            this.phoneNumber = '',
            this.emailId = '',
            this.emailDomain = '',
            this.birth = '',
            this.nickname = '',
            this.inputMailCode = '',
            this.signupModal = true;
        },
    },
};
</script>
<style scoped>
.signup-container {
    max-width: 450px;
    background-color: white;
    padding: 50px;
}

.signup-form {
    margin-top: 20px;
}

.signup-btn {
    margin-top: 20px;
}
</style>