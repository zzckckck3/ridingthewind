<template>
  <v-dialog v-model="dialog" max-width="1200">
    <v-container>
      <v-row>
        <v-col cols="1"></v-col>
        <v-col cols="10">
          <h1>공유 게시판</h1>
          <v-divider class="my-2"></v-divider>
          <!-- 나머지 내용 생략 -->
        </v-col>
        <v-col cols="1"></v-col>
      </v-row>
    </v-container>
  </v-dialog>
</template>

<script>
import http from "@/axios/http.js";
import { mapState } from "vuex";
const memberStore = "memberStore";
import DeleteConfirmDialog from "@/components/layout/DeleteConfirmDialog.vue";
import CommentDeleteConfirmDialog from "@/components/layout/CommentDeleteConfirmDialog.vue";

export default {
  name: "ArticleDetailDialog",
  data() {
    return {
      article: {},
      cards: [],
      comment: "",
      dialog: false, // 다이얼로그 열림/닫힘 상태
      commentDialog: false,
      deleteCommentNo: 0,
    };
  },
  created() {
    this.getArticle();
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    getArticle() {
      let articleNo = this.$route.params.articleNo;
      http.get(`/articleapi/${articleNo}`)
        .then(({ data }) => {
          this.article = data;
          this.comment = "";
          this.cards = [];
          data.tourList.forEach((area) => {
            let card = {
              id: area.contentId,
              src: area.firstImage,
              title: area.title,
              addr_1: area.addr1,
              overview: area.overView,
              latitude: area.latitude,
              longitude: area.longitude,
              flex: 12,
              show: false,
              like: true,
            };
            this.cards.push(card);
          });
        })
        .catch((error) => {
          this.$router.push("error/error", error);
        });
    },
    moveToList() {
      this.$router.push({ name: "article" });
    },
    moveToModify() {
      this.$router.push({
        name: "articleModify",
        params: {
          article: JSON.stringify(this.article),
          cards: JSON.stringify(this.cards),
        },
      });
    },
    addComment() {
      let newComment = {
        articleNo: this.article.articleNo,
        memberId: this.userInfo.data.memberId,
        content: this.comment,
      };

      http.post(`/comment`, JSON.stringify(newComment))
        .then(({ data }) => {
          this.article = data;
          this.getArticle();
        })
        .catch((error) => {
          this.$router.push("/error/error", error);
        });
    },
    deleteComment(key) {
      this.commentDialog = true;
      this.deleteCommentNo = key;
    },
  },
  components: {
    CommentDeleteConfirmDialog,
    DeleteConfirmDialog,
  },
};
</script>
