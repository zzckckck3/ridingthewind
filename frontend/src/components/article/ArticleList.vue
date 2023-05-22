<template>
    <v-container>
        <v-row>
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
                <v-col cols="1">
                    <v-btn
                        color=""
                        elevation="3"
                        plain
                        @click="getArticleList"
                    >검색</v-btn>
                </v-col>
            </v-col>
            <v-col class="d-flex flex-row-reverse" offset="2" cols="5">
                <v-col cols="4">
                    <v-btn
                        color="blue"
                        elevation="3"
                        plain
                        @click="$router.push({ name: 'plan'})"
                    >여행 경로 공유</v-btn>
                </v-col>
                <v-col cols="1">
                    <v-btn
                        color="blue"
                        elevation="3"
                        plain
                        @click="moveToArticleWrite"
                    >글 등록</v-btn>
                </v-col>
                <v-col cols="2">
                    <v-select
                        :items="sppList"
                        value="`${spp}`"
                        label="표시"
                        dense
                        outlined
                        @input="changeSpp"
                    ></v-select>
                </v-col>
            </v-col>
        </v-row>
        <v-simple-table>
            <thead>
                <tr>
                    <th v-for="(header, idx) in headers"
                        :key=idx
                        v-text="header"
                        class="text-h6 text-center"
                    ></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="article in articles" :key="article.articleNo" @click="moveToArticleDetail(article.articleNo)">
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
            ></v-pagination>
        </div>
    </v-container>
</template>

<script>
import http from "@/axios/http.js";

export default {
    name: "ArticleList",
    data () {
        return {
            headers: ['글번호', '추천수', '제목', '작성자', '조회수', '작성일'],
            sppList: ['10', '20', '30', '40', '50'],
            keyList: [
                { value: 'subject', text: '제목' },
                { value: 'member_id', text: '아이디' },
                { value: 'nickname', text: '별명' },
                { value: 'article_no', text: '글번호' },
            ],
            articles: [],
            curPage: 1,
            spp: 10,
            maxPage: 1,
            naviSize: 1,
            key: "",
            word: ""
        }
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
        }
    },
    methods: {
        getArticleList() {
            http.get(`/article`, {
                params: {
                    curPage: this.curPage,
                    spp: this.spp,
                    start: (this.curPage-1) * this.spp,
                    key: this.key,
                    word: this.word,
                }
            })
                .then(({ data }) => {
                    this.articles = data.articles;
                    this.curPage = data.pageNavigation.curPage;
                    this.spp = data.pageNavigation.spp;
                    this.maxPage = data.pageNavigation.maxPage;
                    this.naviSize = data.pageNavigation.naviSize;
                })
                .catch(( error ) => {
                    this.$router.push('error/error', error);
                });
        },
        moveToArticleWrite() {
            this.$router.push({ name: 'articleWrite'});
        },
        moveToArticleDetail(articleNo) {
            this.$router.push({ name: 'articleDetail', params: { articleNo: articleNo } });
        },
        changeSpp(newSpp) {
            this.spp = newSpp;
        },
        changeKey(newKey) {
            this.key = newKey;
        }
    }
}
</script>
