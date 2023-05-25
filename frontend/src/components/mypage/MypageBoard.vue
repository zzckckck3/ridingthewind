<template>
    <div class="mypageBoard">
        <!-- 배너 내용 -->
        <div class="banner custom-font" >
            <h6>추천한 게시글</h6>
            <v-simple-table>
                <thead>
                    <tr>
                        <th
                            v-for="(header, idx) in headers"
                            :key="idx"
                            v-text="header"
                            class="text-center"
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
            <tr v-if="articles.length === 0" class="likeArtileCheck-row">
                추천한 게시글이 없습니다.
            </tr>
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
            articlesNullCheck: false,
        };
    },
    created() {
        this.getLikeList();
    },
    watch: {
        userId() {
            this.getLikeList();
        }
    },
    methods: {
        getLikeList(){
            http.get(`/article/${this.userId}/like`)
                .then( ({ data }) => {
                    this.articles = data;
                })
            if(this.articles.length === 0){
                this.articlesNullCheck = true;
            }else{
                this.articlesNullCheck = false;
            }
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
.likeArtileCheck-row {
    font-size: 14px; /* 폰트 크기 조정 */
    color: gray; /* 폰트 색상을 회색으로 설정 */
    display: flex; /* 가운데 정렬을 위해 flexbox를 사용 */
    justify-content: center; /* 가로 방향 가운데 정렬 */
    align-items: center; /* 세로 방향 가운데 정렬 */
}
</style>