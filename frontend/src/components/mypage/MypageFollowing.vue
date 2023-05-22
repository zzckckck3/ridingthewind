<template>
    <div class="following">
        <!-- Component B 내용 -->
        <h2>following</h2>
        <div>
            <v-list>
                <v-list-item v-for="following in followings" :key="following">
                    <v-list-item-content>
                        {{ following }}
                    </v-list-item-content>
                    <v-list-item-action>
                        <v-btn @click="moveToUserInfo(following)">정보</v-btn>
                    </v-list-item-action>
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
    mounted() {
        // 1초마다 refresh 메서드 호출
        setInterval(this.refresh, 1000);
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
            http.get(getfollowingurl+"/"+this.userId).then(response => {
                this.followings = response.data;
            })
        },
        moveToUserInfo(userId){
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