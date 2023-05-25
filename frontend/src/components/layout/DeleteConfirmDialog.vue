<template>
    <v-dialog v-model="dialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                게시글을 정말<br />삭제하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red darken-1" text @click.stop="deleteArticle">
                    삭제
                </v-btn>
                <v-btn color="grey darken-1" text @click.stop="dialog = false">
                    취소
                </v-btn>
            </v-card-actions>
            <v-bottom-sheet v-model="articleDeleteAlert" inset hide-overlay>
                <v-sheet class="sheet" height="56px">
                    <v-alert type="success">
                        게시글이 삭제되었습니다.
                    </v-alert>
                </v-sheet>
            </v-bottom-sheet>
        </v-card>
    </v-dialog>
</template>

<script>
import http from "@/axios/http.js";

export default {
    name: "DeleteConfirmDialog",
    data() {
        return {
            articleDeleteAlert: false,

        }
    },
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        articleNo: {
            type: Number,
            required: true,
        },
    },
    computed: {
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
        deleteArticle() {
            this.dialog = false;
            let articleNo = this.articleNo;
            http.delete(`/article/${articleNo}`)
                .then(({ data }) => {
                    if (data == "SUCCESS") {
                        this.articleDeleteAlert = true;
                    } else {
                        alert("삭제 실패");
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