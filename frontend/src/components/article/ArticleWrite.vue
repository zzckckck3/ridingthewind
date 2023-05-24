<template>
    <v-row>
        <v-col cols="1"></v-col>
        <v-col cols="10">
            <h1>공유 게시판</h1>
            <v-divider class="my-2"></v-divider>
        <v-container>
            <div class="mt-3 mb-1"><h2>글쓰기</h2></div>
            <v-card min-height="500" class="py-4 col-8 offset-2">
                <v-card class="mb-2 col-10 offset-1 font-weight-bold">
                    <v-col cols="12" sm="6">
                        <v-text-field
                            v-model="subject"
                            label="제목"
                            placeholder="제목"
                        ></v-text-field>
                    </v-col>
                </v-card>
                <v-card class="my-2 offset-1 col-10" min-height="400">
                    <v-col cols="12">
                        <v-textarea
                            height="310"
                            outlined
                            v-model="contents"
                            label="내용"
                        >
                        </v-textarea>
                    </v-col>
                </v-card>
                <v-row class="mt-2 col-4 offset-7 d-flex justify-end">
                    <v-btn
                        class="my-auto mx-1 d-flex flex-row-reverse"
                        @click="dialog = true"
                        color="indigo"
                    >
                        <h4 style="color: white;">등록</h4>
                    </v-btn>
                    <v-btn
                        class="my-auto mx-1 d-flex flex-row-reverse"
                        @click="moveToList"
                        color="indigo"
                    >
                        <h4 style="color: white;">목록</h4>
                    </v-btn>
                    <write-confirm-dialog
                        v-model="dialog"
                        :subject="subject"
                        :content="content"
                    ></write-confirm-dialog>
                </v-row>
            </v-card>
        </v-container>
        </v-col>
        <v-col cols="1"></v-col>
    </v-row>
</template>

<script>
import WriteConfirmDialog from "@/components/layout/WriteConfirmDialog.vue";

export default {
    name: "ArticleWrite",
    data() {
        return {
            subject: "",
            contents: "",
            dialog: false,
        };
    },
    methods: {
        moveToList() {
            this.$router.push({ name: "article" });
        },
    },
    computed: {
        content() {
            return this.contents.split('\n').join('<br>');
        },
    },
    components: {
        WriteConfirmDialog,
    },
};
</script>