<template>
<v-row justify="center">
    <v-btn
    color="primary"
    dark
    @click.stop="changeDialog"
    >
    Open Dialog
    </v-btn>

    <v-dialog
    v-model="interDialog"
    max-width="290"
    >
    <v-card>
        <v-card-title class="text-h5">
        Use Google's location service?
        </v-card-title>

        <v-card-text>
        Let Google help apps determine location. This means sending anonymous location data to Google, even when no apps are running.
        </v-card-text>

        <v-card-actions>
        <v-spacer></v-spacer>

        <v-btn
            color="green darken-1"
            text
            @click="changeDialog"
        >
            Disagree
        </v-btn>

        <v-btn
            color="green darken-1"
            text
            @click="changeDialog"
        >
            Agree
        </v-btn>
        </v-card-actions>
    </v-card>
    </v-dialog>
</v-row>
</template>

<script>
export default {
    name: "TourOverlayInfo",
    props: {
        dialog: Boolean,
    },
    computed: {
        interDialog: {
            get() {
                return this.dialog;
            },
            set(value) {
                this.$emit("update:dialog", !value);
            }
        }
    },
    methods: {
        changeDialog() {
            this.interDialog = !this.interDialog;
        }
    }
    };
</script>


<style>
</style>