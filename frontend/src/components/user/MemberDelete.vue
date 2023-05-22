<template>
    <v-container class="delete-container">
            <v-text-field
                    v-model="currentPassword"
                    label="현재 비밀번호"
                    type="password"
                    required
            ></v-text-field>
            <v-row>
                <v-col>
                    <v-btn @click="deleteUser">회원 삭제</v-btn>
                </v-col>
            </v-row>
    </v-container>
</template>

<script>
import http from "@/axios/http";
import {mapActions, mapState} from "vuex";

const deleteurl = "/member/delete";
const pwdcheckurl = "/member/pwdcheck";
const memberStore = "memberStore";
export default {
    data() {
        return {
            currentPassword: '',
        };
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(memberStore, ["userLogout"]),
        deleteUser(){
            let userid = this.userInfo.data.memberId;
            http.get(pwdcheckurl, {
                params: {
                    userId : userid,
                    userPassword : this.currentPassword
                }
            }).then(response => {
                if(response.data !== 'SUCCESS'){
                    alert("현재 비밀번호가 일치하지 않습니다.");
                    return;
                }else{

                    const memberId = this.userInfo.data.memberId.toString();
                    http.delete(deleteurl+"/"+memberId).then(response => {
                        if(response.status === 202){
                            if (this.$route.path != "/") this.$router.push({ name: "home" });
                            this.userLogout(this.userInfo.data.memberId);
                            sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
                            sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
                            if (this.$route.path != "/") this.$router.push({ name: "home" });
                            alert("회원탈퇴가 완료되었습니다.");

                        }else{
                            alert("회원탈퇴에 실패하였습니다. 잠시 후에 시도해주세요");
                        }
                    })

                }
                if (this.$route.path != "/") this.$router.push({ name: "home" });

            })
        },

    },
};
</script>

<style scoped>
.delete-container {
    max-width: 400px;
    margin: 0 auto;
}

</style>