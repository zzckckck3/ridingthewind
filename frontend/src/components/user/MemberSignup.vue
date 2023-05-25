<template>
    <div>
    <v-dialog v-model="signupModal" max-width="500" persistent>
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
                    <v-col cols="9" md="9">
                        <v-text-field
                            label="인증번호 입력"
                            v-model="inputMailCode"
                            :readonly="isReadOnly"
                            required
                        ></v-text-field>
                    </v-col>
                    <v-col cols="3" md="3">
                        <v-btn class="mt-3" 
                        @click="send ? checkMail() : sendMail()" 
                        color="indigo"
                        width="90"
                        v-if="!certification"
                        >
                            <h5 style="color: white;">{{send ? "인증번호 확인" : "인증번호 발송"}}</h5>
                        </v-btn>
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
                <v-btn class="signup-btn" @click="signup" color="indigo"
                    ><h4 style="color: white;">가입하기</h4></v-btn
                >
                <v-btn
                    class="signup-btn ms-4"
                    @click="signupModal = false"
                    color="indigo"
                    ><h4 style="color: white;">취소</h4></v-btn
                >
            </v-form>
        </v-container>
    </v-dialog>
    <v-snackbar
        v-model="snackbar"
        :timeout="3000"
        >
        {{ "정상적으로 회원가입이 완료되었습니다." }}

        <template v-slot:action="{ attrs }">
            <v-btn
            color="indigo"
            text
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
            send: false,
            certification: false,
            isReadOnly: false,
            snackbar: false
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
                } else {
                    this.idcheckvalue = false;
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
                    this.snackbar = true;
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
                    this.send = true;
                } else {
                    this.send = false;
                    alert(
                        "이메일 전송에 실패하였습니다. 잠시 후 다시 시도해주세요"
                    );
                }
            });
        },
        checkMail() {
            if (this.mailCode !== null) {
                if (this.inputMailCode == this.mailCode) {
                    this.emailcheckvalue = true;
                    this.certification = true;
                    this.isReadOnly = true;
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
            this.send = false;
            this.certification = false;
            this.isReadOnly = false;
            this.snackbar = false;
        },
    },
};
</script>
<style scoped>
.signup-container {
    max-width: 500;
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