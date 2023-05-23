<template>
    <div class="userinfo">
        <div class="button-container">
            <div id="usernamediv">
                <v-icon color="black" :size="40"> mdi-account </v-icon>
                {{ userId }}</div>
            <v-btn
                class="follow-button"
                :color="myFollowCheckValue ? 'warning' : 'gray'"
                v-if="userInfo.data.memberId !== userId"
                @click="clickfollow"
            >
                <v-icon color="black">mdi-handshake</v-icon>
            </v-btn>
        </div>
    </div>
</template>

<script>
import {mapState} from "vuex";
const memberStore = "memberStore";
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
    computed: {
        ...mapState(memberStore, ["userInfo"])
    },
    watch:{
        userId(){
            if(this.userId !== this.userInfo.data.memberId ){
                this.followCheck();
            }
        }
    },
    mounted() {
        if(this.userId !== this.userInfo.data.memberId ){
            this.followCheck();
        }
    },
    methods: {
        followCheck(){
            let from = this.userInfo.data.memberId;
            let to = this.userId;
            console.log("from" +from);
            console.log("to"+to);
            http.get(followcheckurl+"/"+from+"/"+to).then(response => {
                if(response.data === "SUCCESS"){
                    this.myFollowCheckValue = true;
                }else{
                    this.myFollowCheckValue = false;
                }
            })
        },
        async clickfollow(){

            // 팔로우상태인경우 삭제
            if(this.myFollowCheckValue){
                let from = this.userInfo.data.memberId;
                let to = this.userId;
                await http.delete(followcheckurl,{
                    params:{
                        from : from,
                        to : to
                    }
                }).then(response => {
                    console.log(response)
                })
                this.myFollowCheckValue = false;
            }else{
                const data = {};
                data.from = this.userInfo.data.memberId;
                data.to = this.userId;
                await http.post(followcheckurl,data).then(response => {
                    console.log(response);
                })
                this.myFollowCheckValue = true;
            }
            console.log("데이터 업데이트 완료");
            console.log("이벤트는 발생시킬텐데,, 부모로,,");
            this.$emit('follow-click');
        }
    }
}
</script>

<style scoped>
.userinfo {
    padding: 20px;
}

.follow-button {
    //background-color: #e2e2e2;
    background-color: deeppink;
    padding: 10px 20px;
    border-radius: 4px;
}
.button-container {
    display: flex;
    justify-content: space-between;
}
#usernamediv {
    font-size: 30px;
}
</style>