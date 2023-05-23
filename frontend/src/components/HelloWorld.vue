<template>
    <v-main class="white lighten-3" style="margin-bottom: 20px">
        <v-container>
            <v-row>
                <!--Left Container-->
                <v-col class="text-center" cols="12" sm="2">
                    <v-sheet cols="8" class="grey lighten-5" rounded="lg" width="200" min-height="268" elevation="8">
                        <h3 class="pt-2 pb-2">☆☆팔로워 순위!☆☆</h3>
                        <v-list>
                            <v-list-item
                                v-for="(follower, index) in followerList"
                                :key="follower"
                                :value="follower"
                                active-color="primary"
                                variant="tonal"
                            >
                                <v-col cols="3">
                                    <v-list-item-title>{{ index + 1 }}</v-list-item-title>
                                </v-col>
                                <v-col cols="6" offset="1">
                                    <v-list-item-title v-text="follower" @click="moveToUserInfo(follower)"></v-list-item-title>
                                </v-col>
                            </v-list-item>
                        </v-list>
                    </v-sheet>
                </v-col>

                <!--Center Container-->
                <v-col class="text-center" cols="12" sm="8">
                    <v-sheet
                        class="grey lighten-5 text-center"
                        rounded="lg"
                        elevation="12"
                    >
                        <template>
                            <v-carousel v-model="model">
                                <v-carousel-item
                                    v-for="(color, i) in colors"
                                    :key="color"
                                    :value="i"
                                >
                                    <v-sheet :color="color" height="100%" tile>
                                        <div
                                            class="d-flex fill-height justify-center align-center"
                                        >
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
const followerlisturl = "/member/followerlist";
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
            followerList : [],
        };
    },
    mounted() {
        this.getfollowList();
    },
    methods : {
        async getfollowList() {
            await http.get(followerlisturl).then(response => {
                console.log(response.data);
                this.followerList = response.data;
            })
        },
        moveToUserInfo(userId) {
            this.$router.push({name:"infopage", params: {userId : userId}});
        },
    },



};
</script>