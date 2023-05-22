<template>
    <div class="following">
        <!-- Component B 내용 -->
        <h2>following</h2>
        <div>
            <v-list>
                <v-list-item v-for="following in followings" :key="following">
                    <v-list-item-content @click="moveToUserInfo(following)">
                        {{ following }}
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </div>
    </div>
</template>

<script>
import http from "@/axios/http";
const getfollowingurl = "/member/following";
export default {
    data() {
        return{
            followings: []
        }
    },
    name: 'MypageFollowing',
    props:['userId'],
    created() {
        this.getUserFollowingList();
    },
    watch: {
        userId(){
            this.getUserFollowingList();
        }
    },
    methods: {
        getUserFollowingList(){
            console.log(this.userId);
            http.get(getfollowingurl+"/"+this.userId).then(response => {
                console.log(response);
                this.followings = response.data;
                console.log("팔로윙목록 출력");
                console.log(this.following);
            })
        },
        moveToUserInfo(userId){
            console.log("이동할 경로");
            console.log(userId);
            this.$router.push({name:"infopage", params: {userId : userId}});
            // window.location.reload();

        }
    }
}
</script>

<style>
.following {
    background-color: lightyellow;
    padding: 20px;
}
</style>