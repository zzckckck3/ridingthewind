<template>
    <div class="mypageBoard">
        <!-- 배너 내용 -->
        <div class="banner custom-font">
            <v-simple-table>
                <thead>
                    <tr>
                        <th
                            v-for="(header, idx) in headers"
                            :key="idx"
                            v-text="header"
                            class="text-h6 text-center"
                        ></th>
                    </tr>
                </thead>
                <tbody>
                    <tr
                        v-for="article in articles"
                        :key="article.articleNo"
                        @click="moveToArticleDetail(article.articleNo)"
                    >
                      <td>{{ article.articleNo }}</td>
                      <td>{{ article.like }}</td>
                      <td>{{ article.subject }}</td>
                      <td>{{ article.nickname }}</td>
                      <td>{{ article.hit }}</td>
                      <td>{{ article.registerTime }}</td>
                    </tr>
                </tbody>
            </v-simple-table>
        </div>
    </div>
</template>

<script>
import http from "@/axios/http";

export default {
    name: 'MypageBoard',
    props:['userId'],
    data() {
        return {
            headers: ["글번호", "추천수", "제목", "작성자", "조회수", "작성일"],
            articles: [],
        };
    },
    created() {
        this.getLikeList();
    },
    watch(){
        this.getLikeList();
    },
    methods: {
        getLikeList(){
            http.get(`/article/${this.userId}/like`)
                .then( ({ data }) => {
                    this.articles = data;
                })
        },
        moveToArticleDetail(articleNo) {
            this.$router.push({
                name: "articleDetail",
                params: { articleNo: articleNo },
            });
        },
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