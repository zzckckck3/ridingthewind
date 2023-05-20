<template>
  <v-container class="col-8 offset-2">
      <v-card min-height="500" class="pt-4">
          <v-card class="mb-2 col-10 offset-1 font-weight-bold">
              <v-col cols="12" sm="6" md="3">
                  <v-text-field v-model="subject" label="제목" placeholder="제목"></v-text-field>
              </v-col>
          </v-card>
          <v-card class="my-2 offset-1 col-10" min-height="400">
              <v-col cols="12">
                  <v-textarea
                      height="310"
                      outlined
                      v-model="content"
                      label="내용">
                  </v-textarea>
              </v-col>
          </v-card>
          <v-row class="col-4 offset-7 d-flex justify-end">
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click="dialog = true">
                  등록
              </v-btn>
              <v-btn class="my-auto mx-1 d-flex flex-row-reverse" @click="moveToList">
                  목록
              </v-btn>
              <write-confirm-dialog v-model="dialog" :subject="subject" :content="content"></write-confirm-dialog>
          </v-row>
      </v-card>
  </v-container>
</template>

<script>
import WriteConfirmDialog from "@/components/layout/WriteConfirmDialog.vue";

export default {
    name: "ArticleWrite",
    data() {
        return {
            subject: "",
            content: "",
            dialog: false,
        }
    },
    methods: {
        moveToList() {
            this.$router.push({name: "article"});
        },
    },
    components: {
        WriteConfirmDialog,
    }
}
</script>