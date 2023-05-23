<template>
    <div class="mypageBoard">
        <!-- 배너 내용 -->
        <div class="banner custom-font">
            추천한 게시글

        </div>
    </div>
</template>

<script>
// import http from "@/axios/http";
// const likelisturl = "/member/likelist";
export default {
    name: 'MypageBoard',
    props:['userId'],
    created() {
        this.getLikeList();
    },
    watch(){
        this.getLikeList();
    },
    methods: {
        getLikeList(){
            console.log("여기서"+ this.userId);
            http.get(likelisturl).then(response => {
                console.log(this.userId);
            })
        }
    }

}

</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto+Condensed&display=swap');
.custom-font {
    font-family: 'Source Sans Pro', sans-serif;
}
.mypageBoard {
    padding: 20px;
}
.banner {
    font-size: 25px;
}
</style>