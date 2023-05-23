<template>
    <v-dialog v-model="dialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                게시글을 정말<br />등록하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click.stop="writeArticle">
                    등록
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
    name: "WriteConfirmDialog",
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        subject: {
            type: String,
            required: true,
        },
        content: {
            type: String,
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
        writeArticle() {
            this.dialog = false;
            let cardList = Array.isArray(this.cards) ? this.cards : [];
            console.log(cardList);

            const articleAttractionList = cardList.map((card, index) => {
                return {
                    contentId: card.id,
                    order: index,
                };
            });

            let articleInfo = {
                memberId: this.userInfo.data.memberId,
                subject: this.subject,
                content: this.content,
                articleAttractionList: articleAttractionList,
            };

            let birthday = this.userInfo.data.birth;
            let birthyear = birthday.slice(0, 4);
            for (let i = 0; i < cardList.length; i++) {
                let sidoCode = cardList[i].sidoCode;
                let gugunCode = cardList[i].gugunCode;
                //let contentId = cardList[i].id;

                http.put(`/tour/sido/${sidoCode}/${birthyear}`)
                    .then(() => {})
                    .catch((error) => {
                        console.log(error);
                    });

                http.put(`/tour/gugun/${sidoCode}/${gugunCode}/${birthyear}`)
                    .then(() => {})
                    .catch((error) => {
                        console.log(error);
                    });
            }

            http.post(`/article`, JSON.stringify(articleInfo))
                .then(({ data }) => {
                    if (data == "SUCCESS") {
                        alert("등록 성공");
                    } else {
                        alert("등록 실패");
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