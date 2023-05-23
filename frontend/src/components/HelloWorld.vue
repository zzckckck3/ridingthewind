<template>
    <v-main class="white lighten-3" style="margin-bottom: 20px">
        <v-container>
            <v-row>
                <!--Left Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet
                        class="grey lighten-5"
                        rounded="lg"
                        min-height="268"
                        elevation="8"
                    >
                        Left Container
                    </v-sheet>
                </v-col>

                <!--Center Container-->
                <v-col class="text-center" cols="12" sm="8">
                    <v-sheet
                        class="grey lighten-5 text-center"
                        rounded="lg"
                        elevation="12">
                        <template>
                            <v-carousel>
                                <v-carousel-item v-for="article in articles" :key="article.id">
                                    <v-col>
                                        <v-card>
                                            <v-row>
                                                <v-col v-for="tour in article.tourList.slice(0, 3)" :key="tour.contentId" cols="4">
                                                    <v-card>
                                                        <v-img
                                                                :src="tour.firstImage"
                                                                class="white--text align-end card-image"
                                                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                                                height="200px"
                                                        >
                                                            <v-card-title style="font-size: medium">
                                                                {{ tour.title }}
                                                            </v-card-title>
                                                        </v-img>
                                                    </v-card>
                                                </v-col>
                                            </v-row>
                                            <v-row>
                                                <v-col v-for="tour in article.tourList.slice(3, 6)" :key="tour.contentId" cols="4">
                                                    <v-card>
                                                        <v-img
                                                                :src="tour.firstImage"
                                                                class="white--text align-end card-image"
                                                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                                                height="200px"
                                                        >
                                                            <v-card-title style="font-size: medium">
                                                                {{ tour.title }}
                                                            </v-card-title>
                                                        </v-img>
                                                    </v-card>
                                                </v-col>
                                            </v-row>
                                        </v-card>
                                    </v-col>
                                </v-carousel-item>
                            </v-carousel>
                        </template>
                        <v-col cols="3">
                            <v-select
                                v-model="selectedPeriod"
                                :items="periodList"
                                item-text="text"
                                item-value="value"
                                label="기간"
                                outlined
                                @input="getHotArticles"
                            ></v-select>
                        </v-col>
                    </v-sheet>
                </v-col>

                <!--Right Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet
                        class="grey lighten-5"
                        rounded="lg"
                        min-height="268"
                        elevation="8"
                    >
                        아마도 광고
                    </v-sheet>
                </v-col>
            </v-row>
        </v-container>
        <v-spacer></v-spacer>
    </v-main>
</template>

<script>
import http from "@/axios/http.js";

export default {
    data() {
        return {
            periodList: [
                { text: 'day', value: 1 },
                { text: 'week', value: 7 },
                { text: 'month', value: 30 },
            ],
            selectedPeriod: 1,
            articles: [],
        };
    },
    mounted() {
        this.getHotArticles();
    },
    methods: {
        getHotArticles() {
            http.get('/article/hot', {
                params: {
                    period: this.selectedPeriod
                }
            })
                .then(({ data }) => {
                    this.articles = data;
                })
                .catch((error) => {
                    this.$router.push("error/error", error);
                });
        },
    }
};
</script>