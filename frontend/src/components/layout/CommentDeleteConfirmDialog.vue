<template>
    <v-dialog v-model="commentDialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                댓글을 정말<br />삭제하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="red darken-1" text @click.stop="deleteComment">
                    삭제
                </v-btn>
                <v-btn
                    color="grey darken-1"
                    text
                    @click.stop="commentDialog = false"
                >
                    취소
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
import http from "@/axios/http.js";

export default {
    name: "CommentDeleteConfirmDialog",
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        commentNo: {
            type: Number,
            required: true,
        },
    },
    computed: {
        commentDialog: {
            get() {
                return this.value;
            },
            set(value) {
                this.$emit("input", value);
            },
        },
    },
    methods: {
        deleteComment() {
            this.commentDialog = false;
            let commentNo = this.commentNo;
            http.delete(`/comment/${commentNo}`)
                .then(({ data }) => {
                    if (data != "SUCCESS") {
                        alert("삭제 실패");
                    } 

                    this.$emit("updateCommentList");
                })
                .catch((error) => {
                    this.$router.push("error/error", error);
                });
        },
    },
};
</script>