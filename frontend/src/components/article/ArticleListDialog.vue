<template>
  <v-dialog v-model="interDialog" max-width="1200">
    <div style="background-color: whitesmoke; text-align: center; padding: 10px;">
      <h1>~~~가 포함된 게시글 </h1>
    </div>
    <v-simple-table class="mt-3" style="background-color: whitesmoke; padding: 40px;">
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
            @click="openArticleDetailDialog(article.articleNo)"
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
    <div class="text-center" style="background-color: whitesmoke;">
      <v-pagination
        v-model="curPage"
        :length="maxPage"
        :total-visible="naviSize"
        color="indigo"
        dark
      ></v-pagination>
    </div>
  </v-dialog>
    
  
</template>

<script>
import http from "@/axios/http.js";
// import ArticleDetailDialog from './ArticleDetailDialog.vue';

export default {
  name: "ArticleListDialog",
  components: {
      // ArticleDetailDialog,
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
          selectedArticle: null,
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