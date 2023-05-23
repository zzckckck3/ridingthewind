<template>
    <div class="follower">
        <!-- Component A 내용 -->
        <div class="banner">
            <h2>팔로워 {{followernum}}</h2>
        </div>
        <div>
<!--            <v-list>-->
<!--                <v-list-item v-for="follower in followers" :key="follower">-->
<!--                    <v-list-item-content >-->
<!--                        {{ follower }}-->
<!--                    </v-list-item-content>-->
<!--                    <v-list-item-action>-->
<!--                        <v-btn @click="moveToUserInfo(follower)">정보</v-btn>-->
<!--                    </v-list-item-action>-->
<!--                </v-list-item>-->
<!--            </v-list>-->
            <v-card>
                <v-card-text class="scrollable-list">
                    <v-row dense>
                        <v-col cols="12" v-for="follower in followers" :key="follower">
                            <v-card class="banner">
                                <v-card-text>{{ follower }}</v-card-text>
                                <v-btn
                                    :color="'#8E9AFF'"
                                    fab
                                    x-small
                                    dark
                                    @click="moveToUserInfo(follower)"
                                    class="mt-3 mr-3"
                                >
                                    <v-icon>mdi-account-circle</v-icon>
                                </v-btn>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-card-text>
            </v-card>
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
            followerscheck : false,
            followernum : 0
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
        // followerscheck(){
        //     console.log("follows변경이 감지됨, getUserfollow호출");
        //     this.getUserFollowerList();
        // }
    },
    methods: {
        getUserFollowerList(){
            console.log("리스트 호출");
            http.get(getfollowerurl+"/"+this.userId).then(response => {
                console.log(response.data);
                this.followers = response.data;
                this.followernum = this.followers.length;
            })
        },
        moveToUserInfo(userId) {
            this.$router.push({name:"infopage", params: {userId : userId}});
        },
        // changecheck(){
        //     if(this.followerscheck) this.followerscheck = false;
        //     else this.followerscheck = true;
        //
        // }
    }
}
</script>

<style scoped>
.follower {
    padding: 20px;
}
.banner {
    display: flex;
    justify-content: center;
}
.scrollable-list {
    height: 300px;
    overflow-y: auto;
}
</style>