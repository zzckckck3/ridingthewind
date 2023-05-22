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
                <v-col class="text-center" cols="12" sm="8">
                    <v-row>
                        <v-sheet class="grey lighten-5 text-center" rounded="lg" elevation="12" style="width:100%;">
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
                    </v-row>
                    <!-- 하단 인기순 -->
                    <v-row class="mt-5">
                        <v-container
                            v-for="type in types"
                            :key="type"
                            class="grey lighten-4"
                            style="padding-left: 80px; padding-right: 80px;"
                            fluid
                        >
                        <v-subheader>{{ type }}</v-subheader>
                        
                        <v-row>
                            <v-spacer></v-spacer>
                            <v-col
                                v-for="card in cards"
                                :key="card"
                                cols="12"
                                sm="6"
                                md="4"
                            >
                                <v-card>
                                    <v-img
                                    :src="`https://picsum.photos/200/300?image=${getImage()}`"
                                    height="300px"
                                    >
                                    <span
                                        class="text-h5 white--text pl-4 pt-4 d-inline-block"
                                        v-text="card"
                                    ></span>
                                    </v-img>

                                    <v-card-actions class="white justify-center">
                                    <v-btn
                                        v-for="(social, i) in socials"
                                        :key="i"
                                        :color="social.color"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                    >
                                        <v-icon>{{ social.icon }}</v-icon>
                                    </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-col>
                        </v-row>
                        </v-container>
                    </v-row>
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
    export default {
    data() {
        return {
            colors: [
                "primary",
                "secondary",
                "yellow darken-2",
                "red",
                "orange",
            ],
            model: 0,
            types: ["Places to Be", "Places to See"],
            cards: ["Good", "Best", "Finest"],
            socials: [
                {
                    icon: "mdi-facebook",
                    color: "indigo",
                },
                {
                    icon: "mdi-linkedin",
                    color: "cyan darken-1",
                },
                {
                    icon: "mdi-instagram",
                    color: "red lighten-3",
                },
            ],
        };
    },
    methods: {
        getImage() {
            const min = 550;
            const max = 560;
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
    },
}
</script>