<template>
    <div>
        <v-row justify="center">
            <v-dialog v-model="interDialog" max-width="320">
                <v-card>
                    <v-card-title class="text-h5"> 너무 많아요! </v-card-title>

                    <v-card-text>
                        9개 이하의 여행지에만 기능이 제공됩니다.
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