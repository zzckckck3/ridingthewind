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
                        :items="sppNums"
                        label="글 수"
                        dense
                        outlined
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
                <tr v-for="article in articles"
                    :key="article.articleNo">
                    <td>
                        <router-link :to="{ name: 'NoticeDetail', params: { articleNo: article.articleNo } }">
                            {{ article.subject }}
                        </router-link>
                    </td>
                    <td>{{ article.memberName }}</td>
                    <td>{{ article.hit }}</td>
                    <td>{{ article.like }}</td>
                    <td>{{ article.registerTime }}</td>
                </tr>
            </tbody>
        </v-simple-table>
        <div class="text-right">
            <v-btn
                color="secondary"
                elevation="3"
                plain
            >등록</v-btn>
        </div>
        <div class="text-center">
            <v-pagination
                v-model="curPage"
                :length="maxPage"
                :total-visible="7"
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
            sppNums: ['10', '20', '30', '40', '50'],
            articles: [],
            curPage: 1,
            spp: 10,
            maxPage: 10
        }
    },
    created() {
        this.getNewNoticeList(1);
    },
    watch: {
        pg() {
            this.getNewNoticeList();
        }
    },
    methods: {
        getNewNoticeList() {
            http.get(`/article`, {
                params: {
                    curPage: this.curPage,
                    spp: this.spp
                }
            })
                .then(({ data }) => {
                    this.articles = data;
                });
        }
    }
}
</script>
