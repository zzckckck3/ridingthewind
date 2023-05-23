<template>
    <div>
        <v-row justify="center">
            <v-dialog v-model="interDialog" max-width="320">
                <v-card>
                    <v-card-title class="text-h5"> 여행지가 없어요! </v-card-title>

                    <v-card-text>
                        여행지를 드래그해서 넣어주세요!
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="agree">
                            확인
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </div>
</template>

<script>
export default {
    name: "CantRecommendDialog",
    data() {
        return {
            interDialog: false,
        };
    },
    mounted() {},
    methods: {
        openDialog() {
            this.interDialog = true;
        },
        agree() {
            this.interDialog = false;
        },
    },
};
</script>


<style scope>
.sheet {
    border-radius: 5px;
}
</style>