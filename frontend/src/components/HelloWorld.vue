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
                    <v-row class="margin-top">
                        <v-container
                            class="grey lighten-4 rounded-lg"
                            style="padding-left: 40px; padding-right: 80px; padding-top: 30px; padding-bottom: 40px;"
                            fluid
                        >
                        <v-subheader class="text-1">찜 많은순</v-subheader>
                        
                        <v-card-text>
                            <v-chip-group
                                v-model="sidoSelection"
                                active-class="accent-4 white--text"
                                column
                            >
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 1) ? '' : 'none'}" >서울</v-chip>

                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 2) ? '' : 'none'}" >인천</v-chip>
                                
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 3) ? '' : 'none'}" >대전</v-chip>

                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 4) ? '' : 'none'}" >대구</v-chip>
                            </v-chip-group>
                        </v-card-text>

                        <v-row>
                            <v-spacer></v-spacer>
                            <v-col
                                v-for="card in filteredCards"
                                :key="card.id"
                                cols="12"
                                sm="6"
                                md="3"
                            >
                                <v-card>
                                    <v-img
                                    :src="card.src"
                                    height="300px"
                                    >
                                    <span
                                        class="text-h5 white--text pl-4 pt-4 d-inline-block"
                                        v-text="card.title"
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
                    {{ tempSelection }}
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
import http from "@/axios/http";

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
            sidoSelection: 0,
            tempSelection: 1,
            types: ["인기 많은 순", "Places to See"],
            cards: [],
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
    created() {
        this.getLikelist();
    },
    computed: {
        filteredCards() {
            return this.cards.filter(card => this.tempSelection === card.sidoCode);
        }  
    },
    watch: {
        sidoSelection: {
            handler(val) {
                if (val > 8) {
                    this.tempSelection = val+30;
                } else {
                    console.log(val);
                    this.tempSelection = val+1;
                }
            }
        },  
    },
    methods: {
        getImage() {
            const min = 550;
            const max = 560;
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        getLikelist() {
            http.get(`/`)
                .then((response) => {
                    console.log(response.data);
                    response.data.forEach((area) => {
                        let card = {
                            id: area.contentId,
                            src: area.firstImage,
                            title: area.title,
                            sidoCode: area.sidoCode
                        }
                        this.cards.push(card);
                    });
                });
        },

    }
}
</script>

<style scoped>
    .margin-top{
        margin-top: 50px;
    }
    .text-1{
        font-size: x-large;
        font-weight: 600;
        color: navy;
        font-family: math;
        text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.15);
    }
    .custom-chip{
        font-size: large;
        padding: 20px;
        border-radius: 6px;
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.15);
        background-image: linear-gradient(to right, #8a77f7, #0767f7);
    }
</style>