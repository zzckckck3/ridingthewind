<template>
<v-row justify="center">
    <v-dialog
    v-model="interDialog"
    max-width="290"
    >
    <v-card>
        <v-card-title class="text-h5">
            해당 관광지를 추가하시겠습니까?
        </v-card-title>

        <v-card-text>
        추가시 해당관광지는 추후 여행계획에 사용할 수 있습니다.
        </v-card-text>

        <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn
            color="error darken-1"
            text
            @click="disagree"
        >
            취소
        </v-btn>

        <v-btn
            color="green darken-1"
            text
            @click="agree"
        >
            추가
        </v-btn>
        </v-card-actions>
    </v-card>
    </v-dialog>
</v-row>
</template>

<script>
export default {
    name: "AddTourDialog",
    data() {
        return {
            interDialog: false
        }
    },
    methods: {
        openDialog() {
            this.interDialog = true;
        },
        agree() {
            this.interDialog = false;
            this.$emit("agreed");
        },
        disagree() {
            this.interDialog = false;
        }
    }
    };
</script>


<style>
</style>