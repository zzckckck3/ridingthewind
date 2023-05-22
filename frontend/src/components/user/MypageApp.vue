<template>
    <div class="mypage">
        <div class="top-space"></div>
        <MypageUserInfo :userId="userId"/>
        <MypageBoard />
        <div class="content">
            <div class="follower">
                <MypageFollower :userId="userId"/>
            </div>
            <div class="following">
                <MypageFollowing :userId="userId"/>
            </div>
        </div>
    </div>
</template>

<script>
import MypageBoard from "@/components/mypage/MypageBoard.vue";
import MypageFollowing from "@/components/mypage/MypageFollowing.vue";
import MypageFollower from "@/components/mypage/MypageFollower.vue";
import MypageUserInfo from "@/components/mypage/MypageUserInfo.vue";

export default {
    name: 'MypageApp',
    data(){
        return {
            userId : '',
        }
    },
    components: {
        MypageBoard,
        MypageFollowing,
        MypageFollower,
        MypageUserInfo
    },
    watch: {
        '$route'(to, from) {
            this.pageChange(to, from);
            this.getUserId();
        }
    },
    created() {
        this.getUserId();
    },
    methods: {
        getUserId() {
            this.userId = this.$route.params.userId;
            console.log("마이페이지 이동");
            console.log(this.userId);
        },
        pageChange(to, from) {
            // 현재 경로의 변화에 대한 처리를 수행합니다.
            console.log('Route changed from', from.path, 'to', to.path);


        }
    }
}
</script>

<style scoped>
.mypage {
    max-width: 800px;
    margin: 0 auto;
}

.content {
    display: flex;
    justify-content: space-between;
}
.top-space{
    height: 100px;
}
.follower,
.following {
    flex-basis: calc(50% - 10px); /* 배너의 절반 너비 설정, 여유 공간을 위해 10px 간격 제거 */
}
</style>