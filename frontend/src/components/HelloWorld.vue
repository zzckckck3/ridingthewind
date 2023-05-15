<template>
    <v-main class="grey lighten-3">
        <v-container>
            <v-row>
                <!--Left Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet rounded="lg" min-height="268">
                        Left Container
                    </v-sheet>
                </v-col>

                <!--Center Container-->
                <v-col class="text-center" cols="12" sm="8">
                    <v-sheet min-height="70vh" rounded="lg"> hello </v-sheet>
                </v-col>

                <!--Right Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet rounded="lg" min-height="268">
                        Right Container
                    </v-sheet>
                </v-col>
            </v-row>
        </v-container>
    </v-main>
</template>

<script>
export default {};
</script>