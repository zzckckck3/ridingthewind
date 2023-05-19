<template>
    <v-dialog v-model="dialog" max-width="300">
        <v-card>
            <v-card-title class="text-h6">
                게시글을 정말<br />수정하시겠습니까?
            </v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                    color="green darken-1"
                    text
                    @click.stop="modifyArticle">
                    수정
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
    name: "ModifyConfirmDialog",
    props: {
        value: {
            type: Boolean,
            required: true,
        },
        article: {
            type: Object,
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
        modifyArticle() {
            this.dialog = false;
            http.put(`/article`, JSON.stringify(this.article))
                .then(({ data }) => {
                    if (data == "SUCCESS") {
                        alert("수정 성공");
                    } else {
                        alert("수정 실패");
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