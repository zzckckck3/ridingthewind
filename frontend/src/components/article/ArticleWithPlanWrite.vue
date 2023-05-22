<template>
  <v-container>
      <v-row>
          <v-card min-height="500" class="py-4 col-9">
              <v-card class="mb-2 col-10 offset-1 font-weight-bold">
                  <v-col cols="12" sm="6">
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
                  <write-confirm-dialog v-model="dialog" :subject="subject" :content="content" :cards="cards"></write-confirm-dialog>
              </v-row>
          </v-card>
          <v-card min-height="500" class="py-4 col-3">
              <v-container fluid style="max-height: 960px; overflow-y: auto;">
                  <v-row dense class="card-list" id="card-list" style="min-height: 300px; min-width: 200px;">
                      <v-col
                          v-for="(card, index) in cards"
                          :key="index"
                          :cols="12"
                          :id="card.id"
                      >
                        <v-card>
                              <v-img
                                  :src="card.src"
                                  class="white--text align-end card-image"
                                  gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                  height="200px"
                              >
                                  <v-card-title style="font-size: medium;">{{ card.title }}</v-card-title>
                              </v-img>
                              <!-- eslint-disable-next-line -->
                              <v-card-actions >
                                  <!-- 버튼 추가 할거면 여기 -->
                                  <v-col>
                                      <v-row>
                                          <v-btn icon @click="tripDelete(card.id)">
                                              <v-icon :color="card.like ? 'red' : '' ">mdi-delete</v-icon>
                                          </v-btn>
                                      </v-row>
                                  </v-col>
                                  <v-spacer style="font-size: small;">{{ card.addr_1 }}</v-spacer>
                                  <div style="display: none;" class="latitude">{{ card.latitude }}</div>
                                  <div style="display: none;" class="longitude">{{ card.longitude }}</div>
                                  <v-btn
                                      icon
                                      @click="card.show = !card.show"
                                  >
                                      <v-icon>{{ card.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                                  </v-btn>
                              </v-card-actions>
                              <v-expand-transition>
                                  <div v-show="card.show">
                                      <v-divider></v-divider>
                                      <v-card-text>{{ card.overview }}</v-card-text>
                                  </div>
                              </v-expand-transition>
                          </v-card>
                        <v-icon class="my-6">mdi-arrow-down-circle</v-icon>
                    </v-col>
                  </v-row>
              </v-container>
          </v-card>
      </v-row>
  </v-container>
</template>

<script>
import WriteConfirmDialog from "@/components/layout/WriteConfirmDialog.vue";
import http from "@/axios/http";

export default {
    name: "ArticleWithPlanWrite",
    data() {
        return {
            subject: "",
            content: "",
            cards: [],
            dialog: false,
        }   
    },

    created() {
        this.customCardListId = this.$route.params.customCardListId;
        http.get("/tour/attraction-info-bycontentids", {
            params: {
                contentIds: this.$route.params.customCardListId.toString()
            }
        })
            .then(({ data }) => {
                console.log(data.length);
                data.forEach((area) => {
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
                        like: true
                    }
                    this.cards.push(card);
                })
            })
            .catch(( error ) => {
                this.$router.push('error/error', error);
            });
    },
    mounted() {

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