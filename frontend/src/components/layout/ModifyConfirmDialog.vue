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
        <v-bottom-sheet v-model="articleUpdateAlert" inset hide-overlay>
            <v-sheet class="sheet" height="56px">
                <v-alert type="success">
                    게시글이 수정되었습니다.
                </v-alert>
            </v-sheet>
        </v-bottom-sheet>
    </v-dialog>
</template>

<script>
import http from "@/axios/http.js";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
    name: "ModifyConfirmDialog",
    data() {
        return {
            articleUpdateAlert : false,
        }
    },
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

            http.put(`/article`, JSON.stringify(articleInfo))
                .then(({ data }) => {
                    if (data == "SUCCESS") {
                        this.articleUpdateAlert = true;
                    } else {
                        alert("수정 실패");
                    }
                    setTimeout(() => {
                        // 1초 후에 실행될 코드
                        this.$router.push({ name: "article" });
                    }, 1000); // 1000ms = 1초
                })
                .catch((error) => {
                    this.$router.push("error/error", error);
                });
        },
    },
};
</script>