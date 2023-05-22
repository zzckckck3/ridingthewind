<template>
    <div class="userinfo">
        <!-- 배너 내용 -->
        <h1>{{ userId }}</h1>
        <button
            class="follow-button"
        >
            <v-icon color="black">mdi-handshake</v-icon>
        </button>
    </div>
</template>

<script>
import http from "@/axios/http";

const followcheckurl = "/member/followcheck";

export default {
    name: 'MypageUserInfo',
    props: ['userId'],
    data () {
        return{
            myFollowCheckValue : false,
        }
    },
    methods() {
        followCheck(){
            http.get(followcheckurl, )
        }
    }
}
</script>

<style scoped>
.userinfo {
    background-color: red;
    padding: 20px;
}

.follow-button {
    //background-color: #e2e2e2;
    background-color: deeppink;
    padding: 10px 20px;
    border-radius: 4px;
}

</style>