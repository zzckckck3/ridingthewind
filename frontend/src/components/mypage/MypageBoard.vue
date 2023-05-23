<template>
    <div class="mypageBoard">
        <!-- 배너 내용 -->
        <div class="banner custom-font">
            추천한 게시글
        </div>
    </div>
</template>

<script>

export default {
    name: 'MypageBoard'
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