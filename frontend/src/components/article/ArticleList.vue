<template>
    <v-container>
        <v-row>
            <v-col cols="1"></v-col>
            <v-col cols="10">
            <h1>공유 게시판</h1>
            <v-divider class="my-2"></v-divider>
            <v-row class="mt-3">
            <v-col class="d-flex" cols="5">
                <v-col cols="3">
                    <v-select
                        :items="keyList"
                        value="`${key}`"
                        label="검색 조건"
                        dense
                        outlined
                        @input="changeKey"
                    ></v-select>
                </v-col>
                <v-col cols="5">
                    <v-text-field v-model="word" outlined dense></v-text-field>
                </v-col>
                <v-col cols="1" style="padding-left: 0;">
                    <v-btn color="indigo" elevation="3" @click="getArticleList"
                        ><v-icon class="mt-1" color="white">mdi-magnify</v-icon></v-btn
                    >
                </v-col>
            </v-col>
            <v-col class="d-flex flex-row" style="align-items: center;" offset="2" cols="5">
                <v-col cols="6"></v-col>
                <v-col cols="2" class="p-0 custom-margin-top">
                    <v-select
                        style="width: 80%;"
                        :items="sppList"
                        value="`${spp}`"
                        label="표시"
                        dense
                        outlined
                        @input="changeSpp"
                    ></v-select>
                </v-col>
                <v-col cols="3" class="p-0" >
                    <v-btn
                        color="blue"
                        elevation="3"
                        plain
                        @click="$router.push({ name: 'plan'})"
                    >여행 경로 공유</v-btn>
                </v-col>
                <v-col cols="1" class="p-0"> 
                    <v-btn
                    class="mx-2"
                    fab
                    dark
                    large
                    color="indigo"
                    @click="moveToArticleWrite"
                    >
                    <v-icon dark>
                        mdi-pencil
                    </v-icon>
                    </v-btn>
                </v-col>
            </v-col>
        </v-row>
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
    name: "ArticleList",
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
        };
    },
    created() {
        this.getArticleList();
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
        getArticleList() {
            http.get(`/article`, {
                params: {
                    curPage: this.curPage,
                    spp: this.spp,
                    start: (this.curPage - 1) * this.spp,
                    key: this.key,
                    word: this.word,
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
            this.$router.push({ name: "articleWrite" });
        },
        moveToArticleDetail(articleNo) {
            this.$router.push({
                name: "articleDetail",
                params: { articleNo: articleNo },
            });
        },
        changeSpp(newSpp) {
            this.spp = newSpp;
        },
        changeKey(newKey) {
            this.key = newKey;
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