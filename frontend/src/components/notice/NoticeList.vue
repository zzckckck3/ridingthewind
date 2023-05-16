<template>
    <v-container>
        <v-row>
            <v-row class="d-flex flex-row-reverse">
                <v-col cols="1">
                    <v-btn
                        color=""
                        elevation="3"
                        plain
                    >등록</v-btn>
                </v-col>
                <v-col cols="1">
                    <v-select
                        :items="sppList"
                        value="`${spp}`"
                        label="표시"
                        dense
                        outlined
                        @input="changeSpp"
                    ></v-select>
                </v-col>
            </v-row>
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
                    <td>{{ article.subject }}</td>
                    <td>{{ article.memberName }}</td>
                    <td>{{ article.hit }}</td>
                    <td>{{ article.like }}</td>
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
    data () {
        return {
            headers: ['제목', '작성자', '조회수', '추천수', '작성일'],
            sppList: ['10', '20', '30', '40', '50'],
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
        this.getNoticeList();
    },
    watch: {
        curPage() {
            this.getNoticeList();
        },
        spp() {
            this.getNoticeList();
        }
    },
    methods: {
        getNoticeList() {
            http.get(`/article`, {
                params: {
                    curPage: this.curPage,
                    spp: this.spp,
                    start: (this.curPage-1) * this.spp
                }
            })
                .then(({ data }) => {
                    this.articles = data.articles;
                    this.curPage = data.pageNavigation.curPage;
                    this.spp = data.pageNavigation.spp;
                    this.maxPage = data.pageNavigation.maxPage;
                    this.naviSize = data.pageNavigation.naviSize;
                });
        },
        moveToArticleDetail(articleNo) {
            this.$router.push({ name: 'NoticeDetail', params: { articleNo: articleNo } });
        },
        changeSpp(newSpp) {
            this.spp = newSpp;
        }
    }
}
</script>
