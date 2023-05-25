<template>
    <div>
        <v-bottom-sheet v-model="showAlert" inset hide-overlay>
            <v-sheet class="sheet" height="56px">
                <v-alert type="error" dark>
                    로그인 후 이용 가능합니다.
                </v-alert>
            </v-sheet>
        </v-bottom-sheet>
    </div>
</template>

<script>
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
    name: "AddTourDialog",
    data() {
        return {
            showAlert: false,
        };
    },
    computed: {
        ...mapState(memberStore, ["isLogin"]),
    },
    mounted() {},
    methods: {
        showAndHideButtomSheet() {
            this.showAlert = true;
            setTimeout(() => {
                this.showAlert = false;
            }, 1200); 
        },
    },
};
</script>


<style scope>
.sheet {
    border-radius: 5px;
}
</style>