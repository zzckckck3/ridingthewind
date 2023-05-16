<template>
    <v-simple-table>
        <template v-slot:default>
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
        </template>
    </v-simple-table>
</template>

<script>
import http from "@/axios/http.js";

export default {
    data () {
        return {
            headers: ['제목', '작성자', '조회수', '추천수', '작성일'],
            articles: []
        }
    },
    created() {
        http.get(`/article`)
            .then(({ data }) => {
            this.articles = data;
        });
    },
}
</script>
