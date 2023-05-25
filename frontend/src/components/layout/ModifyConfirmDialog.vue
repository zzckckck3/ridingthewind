<template>
    <v-dialog v-model="dialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                게시글을 정말<br />수정하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="green darken-1" text @click.stop="modifyArticle">
                    수정
                </v-btn>
                <v-btn color="grey darken-1" text @click.stop="dialog = false">
                    취소
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import http from "@/axios/http.js";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
    name: "ModifyConfirmDialog",
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        article: {
            type: Object,
            required: true,
        },
        cards: {
            type: Array,
            required: false,
        },
    },
    computed: {
        ...mapState(memberStore, ["userInfo"]),
        dialog: {
            get() {
                return this.value;
            },
            set(value) {
                this.$emit("input", value);
            },
        },
    },
    methods: {
        modifyArticle() {
            this.dialog = false;
            let cardList = Array.isArray(this.cards) ? this.cards : [];

            const articleAttractionList = cardList.map((card, index) => {
                return {
                    articleNo: this.article.articleNo,
                    contentId: card.id,
                    order: index,
                };
            });

            let articleInfo = {
                memberId: this.userInfo.data.memberId,
                articleNo: this.article.articleNo,
                subject: this.article.subject,
                content: this.article.content,
                articleAttractionList: articleAttractionList,
            };

            http.put(`/articleapi`, JSON.stringify(articleInfo))
                .then(({ data }) => {
                    if (data != "SUCCESS") {
                        alert("수정 실패");
                    } 
                    this.$router.push({ name: "article" });
                })
                .catch((error) => {
                    this.$router.push("error/error", error);
                });
        },
    },
};
</script>