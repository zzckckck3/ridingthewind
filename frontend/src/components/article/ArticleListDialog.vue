<template>
    <v-container>
        <v-row>
            <v-col cols="1"></v-col>
            
            <v-col cols="10">
                <h1 id="recommendArticleNo" style="display: none;">articleNo</h1>
                <h1 id="recommendArticleTitle">Title</h1>
                <v-divider class="my-2"></v-divider>
        
            <v-simple-table class="mt-3">
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
            <div class="text-center">
                <v-pagination
                    v-model="curPage"
                    :length="maxPage"
                    :total-visible="naviSize"
                    color="indigo"
                    dark
                ></v-pagination>
            </div>
        </v-col>
        <v-col cols="1"></v-col>
    </v-row>
    </v-container>
</template>

<script>
import http from "@/axios/http.js";

export default {
    name: "ArticleListDialog",
    components: {
    },
    data() {
        return {
            headers: ["글번호", "구분", "제목", "작성자", "조회수", "작성일"],
            sppList: ["10", "20", "30", "40", "50"],
            keyList: [
                { value: "subject", text: "제목" },
                { value: "member_id", text: "아이디" },
                { value: "nickname", text: "별명" },
                { value: "article_no", text: "글번호" },
            ],
            articles: [],
            curPage: 1,
            spp: 10,
            maxPage: 1,
            naviSize: 1,
            key: "",
            word: "",
            interDialog: false,
            dialog: false,
            articleNo: null,
            recommendArticleNo: null,
            selectedArticle: null,
        };
    },
    mounted() {
        // this.recommendArticleNo = document.getElementById("recommendArticleNo").innerHTML;
        // console.log(document.getElementById("recommendArticleNo").innerHTML);
        // console.log(this.recommendArticleNo);
        // this.getArticleList()

        this.Test();    

        
        
    },
    created() {  
        
        
    },
    watch: {
        curPage() {
            this.getArticleList();
        },
        spp() {
            this.getArticleList();
        },
    },
    methods: {
        getArticleList(recommendArticleNo) {
        
            http.get(`/articleapi/recommend`, {
                params: {
                curPage: this.curPage,
                spp: this.spp,
                start: (this.curPage - 1) * this.spp,
                key: this.key,
                word: this.word,
                contentId: recommendArticleNo,
                },
            })
                .then(({ data }) => {
                    this.articles = data.articles;
                    this.curPage = data.pageNavigation.curPage;
                    this.spp = data.pageNavigation.spp;
                    this.maxPage = data.pageNavigation.maxPage;
                    this.naviSize = data.pageNavigation.naviSize;
                })
                .catch((error) => {
                    this.$router.push("error/error", error);
                });
        },
        moveToArticleWrite() {
        /** */
            this.$router.push({ name: "articleWrite" });
        },
        moveToArticleDetail(articleNo) {
        /** */
            this.$router.push({
                name: "articleDetail",
                params: { articleNo: articleNo },
            });
        },
        openArticleDetailDialog(articleNo) {
        this.$refs.articleDetailDialog.openDialog(articleNo);
        },
        changeSpp(newSpp) {
            this.spp = newSpp;
        },
        changeKey(newKey) {
            this.key = newKey;
        },
        openDialog() {
            this.interDialog = true;
        },
        agree() {
            this.interDialog = false;
        },
        Test() {
            console.log(location.href);  
            const params = new URLSearchParams(location.href);
            console.log(params);
            let recommendArticleNo = params.get(`recommendArticleNo`);
            let recommendArticleTitle = params.get("recommendArticleTitle");
            
            console.log(recommendArticleNo);
            console.log(recommendArticleTitle);
            
            // 필요한 로직 수행
            this.getArticleList(recommendArticleNo);
        },
    },
};
</script>

<style scoped>
.p-0{
    padding: 0;
}
.custom-margin-top{
    margin-top: 25px;
}
</style>