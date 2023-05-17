<template>
  <v-container class="col-8 offset-2">
      <v-card min-height="500" class="pt-4">
          <v-card class="mb-2 col-10 offset-1 font-weight-bold">
              {{ article.subject }}
          </v-card>
          <v-card class="my-auto col-10 offset-1 font-weight-bold">
              <v-row class="my-0 px-2 d-flex align-center">
                  <v-col class="col-1">
                      <v-avatar color="teal" size="40"></v-avatar>
                  </v-col>
                  <v-col>
                      <v-row align-content="center"> {{article.memberId}} </v-row>
                      <v-row align-content="center"> {{article.registerTime}} </v-row>
                  </v-col>
              </v-row>
          </v-card>
          <v-card class="my-2 offset-1 col-10" min-height="400">
              <v-row class="col-12 pa-6">
                  {{article.content}}
              </v-row>
          </v-card>
          <v-row class="col-4 offset-7 d-flex justify-end">
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click="moveToList">
                  목록
              </v-btn>
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click="moveToModify">
                  수정
              </v-btn>
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click.stop="dialog = true">
                  삭제
              </v-btn>
              <delete-confirm-dialog v-model="dialog" :articleNo="article.articleNo"></delete-confirm-dialog>
          </v-row>
      </v-card>
  </v-container>
</template>

<script>
import http from "@/axios/http.js";
import DeleteConfirmDialog from "@/components/layout/DeleteConfirmDialog.vue";

export default {
    name: "NoticeDetail",
    data() {
        return {
            article: {},
            dialog: false,
        }
    },
    created() {
        let articleNo = this.$route.params.articleNo;
        http.get(`/article/${articleNo}`)
            .then(({ data }) => {
                this.article = data;
            })
            .catch(( error ) => {
                this.$router.push('error/error', error);
            });
    },
    methods: {
        moveToList() {
            this.$router.push({ name: "notice" });
        },
        moveToModify() {
            this.$router.push({ name: "noticeModify", params: { 'article': JSON.stringify(this.article) } });
        },
    },
    components: {
        DeleteConfirmDialog,
    }
}
</script>