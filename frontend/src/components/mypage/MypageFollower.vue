<template>
    <div class="follower">
        <!-- Component A 내용 -->
        <h2>follower</h2>
        <div>
            <v-list>
                <v-list-item v-for="follower in followers" :key="follower">
                    <v-list-item-content>
                        {{ follower }}
                    </v-list-item-content>
                    <v-list-item-action>
                        <v-btn @click="moveToUserInfo(follower)">정보</v-btn>
                    </v-list-item-action>
                </v-list-item>
            </v-list>
        </div>
    </div>
</template>

<script>
import http from "@/axios/http";
const getfollowerurl = "/member/follower";
export default {
    data() {
        return{
            followers : [],
            followerscheck : false
        }
    },
    created() {
        this.getUserFollowerList();
    },

    name: 'MypageFollower',
    props:['userId'],
    watch: {
        userId(){
            this.getUserFollowerList();
        },
        followerscheck(){
            this.getUserFollowerList();
        }
    },
    methods: {
        getUserFollowerList(){
            console.log(this.userId);
            http.get(getfollowerurl+"/"+this.userId).then(response => {
                console.log(response);
                this.followers = response.data;
            })
        },
        moveToUserInfo(userId) {
            this.$router.push({name:"infopage", params: {userId : userId}});
        },
        changecheck(){
            if(this.followerscheck) this.followerscheck = false;
            else this.followerscheck = true;

        }
    }
}
</script>

<style>
.follower {
    background-color: lightgray;
    padding: 20px;
}
</style>