<template>
  <v-container class="col-8 offset-2">
      <v-card min-height="500" class="py-4">
          <v-row class="col-4 offset-7 d-flex justify-end">
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click="moveToList">
                  목록
              </v-btn>
              <v-btn v-if="userInfo.data.role == 'ADMIN' || userInfo.data.memberId == article.memberId" class="my-auto mx-1 d-flex flex-row-reverse" @click="moveToModify">
                  수정
              </v-btn>
              <v-btn v-if="userInfo.data.role == 'ADMIN' || userInfo.data.memberId == article.memberId" class="my-auto mx-1 d-flex flex-row-reverse" @click.stop="dialog = true">
                  삭제
              </v-btn>
              <delete-confirm-dialog v-model="dialog" :articleNo="article.articleNo"></delete-confirm-dialog>
          </v-row>
          <v-card class="my-2 col-10 offset-1 font-weight-bold">
              <v-row class="my-0 px-8 d-flex">
                  {{ article.subject }}
              </v-row>
          </v-card>
          <v-card class="my-auto col-10 offset-1 font-weight-bold">
              <v-row class="my-0 px-2 d-flex align-center">
                  <v-col class="col-1">
                      <v-avatar color="teal" size="40"></v-avatar>
                  </v-col>
                  <v-col class="col-2">
                      <v-row align-content="center"> {{ article.nickname }} </v-row>
                      <v-row align-content="center"> {{ article.modifyTime }} </v-row>
                  </v-col>
                  <v-col class="col-2 offset-7">
                      <v-col>
                          <v-row align-content="center"> 조회: {{ article.hit }} </v-row>
                          <v-row align-content="center"> 추천: {{ article.like }} </v-row>
                      </v-col>
                  </v-col>
              </v-row>
          </v-card>
          <v-card class="my-2 offset-1 col-10" min-height="400">
              <v-row class="col-12 pa-6">
                  {{ article.content }}
              </v-row>
          </v-card>
          <v-card class="my-2 offset-1 col-10">
              <v-row class="col-12 pa-3">
                  댓글: {{ article.commentList.length }}
              </v-row>
              <v-row class="col-12 pa-6">
                  <v-row v-for="commentItem in article.commentList" :key="commentItem.commentNo" class="col-12 pa-3">
                      {{ commentItem.nickname }} : {{ commentItem.content }} ({{ commentItem.registerTime }})
                      <v-btn v-if="userInfo.data.role == 'ADMIN' || userInfo.data.memberId == commentItem.memberId" class="mx-3" small @click.stop="deleteComment(commentItem.commentNo, $event)">
                          삭제
                      </v-btn>
                  </v-row>
                  <comment-delete-confirm-dialog v-model="commentDialog" :commentNo=deleteCommentNo @updateCommentList="getArticle"></comment-delete-confirm-dialog>
              </v-row>
              <v-row class="col-12">
                  <v-text-field placeholder="댓글을 입력하세요" v-model="comment"></v-text-field>
                  <v-btn class="my-auto mx-3 d-flex flex-row-reverse" @click="addComment">
                      등록
                  </v-btn>
              </v-row>
          </v-card>
      </v-card>
  </v-container>
</template>

<script>
import http from "@/axios/http.js";
import {mapState} from "vuex";
const memberStore = "memberStore";
import DeleteConfirmDialog from "@/components/layout/DeleteConfirmDialog.vue";
import CommentDeleteConfirmDialog from "@/components/layout/CommentDeleteConfirmDialog.vue";

export default {
    name: "ArticleDetail",
    data() {
        return {
            article: {},
            comment: "",
            dialog: false,
            commentDialog: false,
            deleteCommentNo: 0,
        }
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
            http.get(`/article/${articleNo}`)
                .then(({ data }) => {
                    this.article = data;
                    this.comment = "";
                })
                .catch(( error ) => {
                    this.$router.push('error/error', error);
                });
        },
        moveToList() {
            this.$router.push({ name: "article" });
        },
        moveToModify() {
            this.$router.push({ name: "articleModify", params: { 'article': JSON.stringify(this.article) } });
        },
        addComment() {
            let newComment = {
                articleNo: this.article.articleNo,
                memberId: this.userInfo.data.memberId,
                content: this.comment,
            }

            http.post(`/comment`, JSON.stringify(newComment))
                .then(({ data }) => {
                    this.article = data;
                    this.getArticle();
                })
                .catch(( error ) => {
                    this.$router.push('/error/error', error);
                });
        },
        deleteComment(key) {
            this.commentDialog = true;
            this.deleteCommentNo = key;
        }
    },
    components: {
        CommentDeleteConfirmDialog,
        DeleteConfirmDialog,
    }
}
</script>