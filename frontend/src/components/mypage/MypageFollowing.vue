<template>
    <div class="following">
        <!-- Component B 내용 -->
        <div class="banner">
            <h2>팔로잉 {{followingnum}}</h2>
        </div>
<!--        <div>-->
<!--            <v-list>-->
<!--                <v-list-item v-for="following in followings" :key="following">-->
<!--                    <v-list-item-content>-->
<!--                        {{ following }}-->
<!--                    </v-list-item-content>-->
<!--                    <v-list-item-action>-->
<!--                        <v-btn @click="moveToUserInfo(following)">정보</v-btn>-->
<!--                    </v-list-item-action>-->
<!--                </v-list-item>-->
<!--            </v-list>-->
<!--        </div>-->
        <v-card>
            <v-card-text class="scrollable-list">
                <v-row dense>
                    <v-col cols="12" v-for="following in followings" :key="following">
                        <v-card class="banner">
                            <v-card-text>{{ following }}</v-card-text>
                            <v-btn
                                :color="'#8E9AFF'"
                                fab
                                x-small
                                dark
                                @click="moveToUserInfo(following)"
                                class="mt-3 mr-3"
                            >
                                <v-icon>mdi-account-circle</v-icon>
                            </v-btn>
<!--                            <v-btn @click="moveToUserInfo(following)" class="mt-2 mr-2">보러가기</v-btn>-->
                        </v-card>
                    </v-col>
                </v-row>
            </v-card-text>
        </v-card>
    </div>
</template>

<script>
import http from "@/axios/http";
const getfollowingurl = "/member/following";
export default {
    data() {
        return{
            followings: [],
            followingnum : 0
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
                this.followingnum = this.followings.length;
            })
        },
        moveToUserInfo(userId){
            this.$router.push({name:"infopage", params: {userId : userId}});
            // window.location.reload();

        }
    }
}
</script>

<style scoped>
.following {
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