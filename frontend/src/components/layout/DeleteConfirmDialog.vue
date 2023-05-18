<template>
    <v-dialog v-model="dialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                게시글을 정말<br />삭제하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="red darken-1"
                    text
                    @click.stop="deleteArticle">
                    삭제
                </v-btn>
                <v-btn
                    color="grey darken-1"
                    text
                    @click.stop="dialog = false">
                    취소
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import http from "@/axios/http.js";

export default {
    name: "DeleteConfirmDialog",
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        articleNo: {
            type: Number,
            required: true,
        }
    },
    computed: {
        dialog: {
            get() {
                return this.value;
            },
            set(value) {
                this.$emit('input', value);
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
                        alert("삭제 성공");
                    } else {
                        alert("삭제 실패");
                    }
                    this.$router.push({ name: "article" });
                })
                .catch(( error ) => {
                    this.$router.push('error/error', error);
                });
        }
    }
};
</script>