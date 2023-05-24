<template>
    <v-container>
        <v-row>
            <v-col cols="1"></v-col>
            
            <v-col cols="10">
                <h1>공유 게시판</h1>
                <v-divider class="my-2"></v-divider>
            <v-row>
            <v-card min-height="500" class="py-4 col-9">
                <v-card class="mb-2 col-10 offset-1 font-weight-bold">
                    <v-col cols="12" sm="6" md="3">
                        <v-text-field
                            v-model="article.subject"
                            label="제목"
                            placeholder="제목"
                            >{{ article.subject }}</v-text-field
                        >
                    </v-col>
                </v-card>
                <v-card class="my-auto col-10 offset-1 font-weight-bold">
                    <v-row class="my-0 px-2 d-flex align-center">
                        <v-col class="col-1">
                            <v-avatar color="teal" size="40"></v-avatar>
                        </v-col>
                        <v-col>
                            <v-row align-content="center">
                                {{ article.memberId }}
                            </v-row>
                            <v-row align-content="center">
                                {{ article.registerTime }}
                            </v-row>
                        </v-col>
                    </v-row>
                </v-card>
                <v-card class="my-2 offset-1 col-10" min-height="400">
                    <v-col cols="12">
                        <v-textarea
                            height="310"
                            outlined
                            v-model="article.content"
                            label="내용"
                        >
                        </v-textarea>
                    </v-col>
                </v-card>
                <v-row class="mt-2 col-4 offset-7 d-flex justify-end">
                    <v-btn
                        class="my-auto mx-1 d-flex flex-row-reverse"
                        color="indigo"
                        @click="dialog = true"
                    >
                        <h4 style="color: white;">수정</h4>
                    </v-btn>
                    <v-btn
                        class="my-auto mx-1 d-flex flex-row-reverse"
                        color="indigo"
                        @click="moveToList"
                    >
                        <h4 style="color: white;">목록</h4>
                    </v-btn>
                    <modify-confirm-dialog
                        v-model="dialog"
                        :article="article"
                        :cards="cards"
                    ></modify-confirm-dialog>
                </v-row>
            </v-card>
            <v-card min-height="500" class="py-4 col-3">
                <v-container fluid style="max-height: 960px; overflow-y: auto">
                    <v-row
                        dense
                        class="card-list"
                        id="card-list"
                        style="min-height: 300px; min-width: 200px"
                    >
                        <v-col
                            v-for="(card, index) in cards"
                            :key="card.title"
                            :cols="card.flex"
                            :id="card.id"
                        >
                            <v-card>
                                <v-img
                                    :src="card.src"
                                    class="white--text align-end card-image"
                                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                    height="200px"
                                >
                                    <v-card-title style="font-size: medium">{{
                                        card.title
                                    }}</v-card-title>
                                </v-img>
                                <!-- eslint-disable-next-line -->
                                <v-card-actions>
                                    <!-- 버튼 추가 할거면 여기 -->
                                    <v-col>
                                        <v-row>
                                            <v-btn
                                                icon
                                                @click="tripDelete(index)"
                                            >
                                                <v-icon
                                                    :color="
                                                        card.like ? 'red' : ''
                                                    "
                                                    >mdi-delete</v-icon
                                                >
                                            </v-btn>
                                        </v-row>
                                    </v-col>
                                    <v-spacer style="font-size: small">{{
                                        card.addr_1
                                    }}</v-spacer>
                                    <div style="display: none" class="latitude">
                                        {{ card.latitude }}
                                    </div>
                                    <div
                                        style="display: none"
                                        class="longitude"
                                    >
                                        {{ card.longitude }}
                                    </div>
                                    <v-btn icon @click="card.show = !card.show">
                                        <v-icon>{{
                                            card.show
                                                ? "mdi-chevron-up"
                                                : "mdi-chevron-down"
                                        }}</v-icon>
                                    </v-btn>
                                </v-card-actions>
                                <v-expand-transition>
                                    <div v-show="card.show">
                                        <v-divider></v-divider>
                                        <v-card-text>{{
                                            card.overview
                                        }}</v-card-text>
                                    </div>
                                </v-expand-transition>
                            </v-card>
                            <v-icon class="my-6">mdi-arrow-down-circle</v-icon>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card>
        </v-row>
        </v-col>
        <v-col cols="1"></v-col>
    </v-row>
    </v-container>
</template>

<script>
import ModifyConfirmDialog from "@/components/layout/ModifyConfirmDialog.vue";

export default {
    name: "ArticleModify",
    data() {
        return {
            article: {},
            cards: [],
            dialog: false,
        };
    },
    created() {
        this.article = JSON.parse(this.$route.params.article);
        this.cards = JSON.parse(this.$route.params.cards);
    },
    methods: {
        moveToList() {
            this.$router.push({ name: "article" });
        },
        tripDelete(index) {
            this.cards.splice(index, 1);
        },
    },
    components: {
        ModifyConfirmDialog,
    },
};
</script>