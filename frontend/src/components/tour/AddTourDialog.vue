<template>
<div>
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
            color="green darken-1"
            text
            @click="agree"
            >
            추가
        </v-btn>
        <v-btn
            color="error darken-1"
            text
            @click="disagree"
        >
            취소
        </v-btn>
</v-card-actions>
    </v-card>
    </v-dialog>
</v-row>

    <v-bottom-sheet v-model="showAlert" inset hide-overlay>
        <v-sheet class="sheet" height=56px>
        <v-alert type="warning">
            로그인 먼저 진행한 후 저장 가능합니다.
        </v-alert>
        </v-sheet>
    </v-bottom-sheet>

</div>
</template>

<script>
import {mapState} from "vuex";
const memberStore = "memberStore";
export default {
    name: "AddTourDialog",
    data() {
        return {
            interDialog: false,
            showAlert: false
        }
    },
    computed: {
        ...mapState(memberStore, ["isLogin"]),
    },
    mounted(){
    },
    methods: {
        openDialog() {
            this.interDialog = true;
        },
        agree() {
            this.interDialog = false;
            if(this.isLogin){
                this.$emit("agreed");
            }else{
                this.showAndHideAlert();
            }
        },
        disagree() {
            this.interDialog = false;
        },
        showAndHideAlert() {
        this.showAlert = true;
        setTimeout(() => {this.showAlert = false;}, 2000); // 2초 후에 알람을 숨깁니다.
        }
    }
    };
</script>


<style scope>
.sheet {
    border-radius: 5px;
}
</style>