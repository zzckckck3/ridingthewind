<template>
    <v-container class="signup-container">
        <h2>회원 가입</h2>
        <v-form class="signup-form" @submit="submitForm">
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field label="아이디" v-model="id" required></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field label="비밀번호" v-model="password" type="password" required></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field label="비밀번호확인" v-model="passwordcheck" type="password" required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="12">
                    <v-text-field label="이름" v-model="name" required></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field label="핸드폰 번호" v-model="phoneNumber" required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="5">
                    <v-text-field label="이메일" v-model="emailId" required></v-text-field>
                </v-col>
                <v-col cols="12" md = "1">
                    @
                </v-col>
                <v-col class="email-domain" cols="12" md="6">
                    <v-text-field v-model="emailDomain" required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="6">
                    <v-text-field label="생일" v-model="birthday" type="date" required></v-text-field>
                </v-col>
                <v-col cols="12" md="12">
                    <v-text-field label="닉네임" v-model="nickname" required></v-text-field>
                </v-col>
            </v-row>
            <v-btn class="signup-btn" @click="signup" color="primary">가입하기</v-btn>
        </v-form>
    </v-container>
</template>

<script>
import http from "@/axios/http";
export const signupurl = "/member/join";
export default {
    data(){
        return{
            id: '',
            password: '',
            passwordcheck: '',
            name: '',
            phoneNumber: '',
            emailId: '',
            emailDomain: '',
            birthday: '',
            nickname: ''
        }
    },
    methods: {
        signup(){
            if(this.id === '' || this.password === '' || this.name === '' || this.phoneNumber === ''||
                this.emailId === '' || this.emailDomain === '' || this.birthday === '' || this.nickname === ''){
                alert("값을 모두 입력해주세요");
                return;
            }

            let signupForm = {};
            signupForm.id = this.id;
            signupForm.password = this.password;
            signupForm.name = this.name;
            signupForm.phoneNumber = this.phoneNumber;
            signupForm.emailId = this.emailId;
            signupForm.emailDomain = this.emailDomain;
            signupForm.birthday = this.birthday;
            signupForm.nickname = this.nickname;

            http.post(signupurl, signupForm).then(response => {
                if(response.status == 202){
                    alert("성공");
                    this.$router.push({name:'signin'});
                }else{
                    alert("실패");
                    this.$router.push({name:'home'});
                }
            })
        },
    }
}
</script>
<style scoped>
.signup-container {
    max-width: 400px;
    margin: 0 auto;
}

.signup-form {
    margin-top: 20px;
}

.signup-btn {
    margin-top: 20px;
}
</style>