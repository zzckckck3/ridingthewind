<template>
    <div class="userinfo">
        <!-- 배너 내용 -->
        <h1>{{ userId }}</h1>
    </div>
</template>

<script>
export default {
    name: 'MypageUserInfo',
    props: ['userId']
}
</script>

<style scoped>
.userinfo {
    background-color: red;
    padding: 20px;
}
</style>