<template>
    <v-row justify="center">
        <v-dialog
        v-model="interDialog"
        max-width="290"
        >
        <v-card>
            <v-card-title class="text-h5">
                해당 관광지를 삭제하시겠습니까?
            </v-card-title>
    
            <v-card-text>
            삭제 시 추가된 해당 관광지가 사라집니다.
            </v-card-text>
    
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                color="error darken-1"
                text
                @click="agree"
            >
                삭제
            </v-btn>
            <v-btn
                color="darken-1"
                text
                @click="disagree"
            >
                취소
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