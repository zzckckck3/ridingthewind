<template>
    <v-main class="white lighten-3" style="margin-bottom: 20px;">
        <v-container>
            <v-row>
                <!--Left Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet class="grey lighten-5" rounded="lg" min-height="268" elevation="8">
                        Left Container
                    </v-sheet>
                </v-col>

                <!--Center Container-->
                <v-col class="text-center" cols="12" sm="8" >
                    <v-sheet class="grey lighten-5 text-center" rounded="lg" elevation="12">
                        <template>
                            <v-carousel v-model="model">
                                <v-carousel-item
                                    v-for="(color, i) in colors"
                                    :key="color"
                                    :value="i"
                                >
                            <v-sheet
                                :color="color"
                                    height="100%"
                                    tile
                                >
                                <div class="d-flex fill-height justify-center align-center">
                                    <div class="text-h2">
                                        Manager Custom {{ i + 1 }}
                                    </div>
                                </div>
                            </v-sheet>
                                </v-carousel-item>
                            </v-carousel>
                        </template>
                    </v-sheet>
                </v-col>

                <!--Right Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet class="grey lighten-5" rounded="lg" min-height="268" elevation="8">
                        아마도 광고
                    </v-sheet>
                </v-col>
            </v-row>
        </v-container>
        <v-spacer></v-spacer>
    </v-main>
</template>

<script>
export default {};
</script>

<script>
    export default {
        data () {
            return {
                colors: [
                    'primary',
                    'secondary',
                    'yellow darken-2',
                    'red',
                    'orange',
                ],
                model: 0,
            }
        },
    }
</script>