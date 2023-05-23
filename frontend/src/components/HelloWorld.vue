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
                        rounded="lg"
                        elevation="8"
                        class="lighten-5 overflow-auto"
                        style="padding: 10px; padding-left: 20px; padding-right: 20px;"
                    >
                        <h1>응애 뭐넣어</h1>
                        <div style="display:flex; margin-top: 20px;">
                            <v-select
                                id="search-area"
                                label="시, 도"
                                :items="sido"
                                item-text="sidoName"
                                item-value="sidoCode"
                                v-model="selectedSido"
                                outlined
                            >
                            </v-select>
                            <v-select
                                class="ms-4"
                                label="군, 구"
                                :items="gugun"
                                item-text="gugunName"
                                item-value="gugunCode"
                                v-model="selectedGugun"
                                outlined
                            ></v-select>
                        </div>
                        <div style="display:flex">
                            <v-autocomplete
                                class="me-2"
                                clearable
                                chips
                                label="여행지 종류"
                                :items="contentByType"
                                item-text="value"
                                item-value="id"
                                v-model="selectedContentById"
                                multiple
                                outlined
                            >
                            <template v-slot:selection="{ item }">
                                <!--추후 chips색깔 바꿀 예정-->
                                <v-chip 

                                >
                                {{ item.value }}
                                </v-chip>
                            </template>
                            </v-autocomplete>
                            <v-btn
                                @click="selectedSido !== null && selectedGugun !== null && sendToTripSearch()"
                                class="ma-2"
                                outlined
                                large
                                fab
                                color="indigo"
                                >
                                <v-icon
                                    large
                                    color="darken-2"
                                >
                                mdi-magnify
                                </v-icon>
                            </v-btn>
                        </div>
                    </v-sheet>
                    <!-- 하단 인기순 -->
                    <v-row class="margin-top">
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
            /* 상단 여행 정보 표시 변수 */
            sido: [],
            gugun: [],
            selectedSido: null,
            selectedGugun: null,
            selectedContentById: [],
            contentByType: [
                {id: 1, value: "전체"},
                { id: 12, value: "관광지" },
                { id: 14, value: "문화시설" },
                { id: 15, value: "축제공연행사" },
                { id: 25, value: "여행코스" },
                { id: 28, value: "레포츠" },
                { id: 32, value: "숙박" },
                { id: 38, value: "쇼핑" },
                { id: 39, value: "음식점" }
            ],
            selectAll: false,
            selectedId: null,

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
    created() {
        this.create_sido();
    },
    watch: {
        selectedSido: "create_gugun",
        // selectAll 감시
        selectAll: {
            // selectAll가 true이면 전체를 넣고
            handler(val) {
                if (val) {
                    this.selectedContentById = this.contentByType.map(item => item.id);
                // 아니면 한번더 눌렀으므로 빈거 출력
                } else {
                    this.selectedContentById = [];
                }
            }
        },
        // selectedContentById 감시
        selectedContentById: {
            // 전체 선택을 누른다면 selectAll = true;
            handler(val) {
                if (val.includes(1)) {
                    this.selectAll = true;
                } else {
                    this.selectAll = false;
                }
            },
            deep: true,
            immediate: true
        },
    },
    methods: {
        create_sido() {
            http.get(`/tour/sido`)
                .then((response) => {
                    this.sido = response.data;
                });
        },
        create_gugun() {
            http.get(`/tour/gugun?search-area=${this.selectedSido}`)
                .then((response) => {
                    this.gugun = response.data;
                });
        },
        getImage() {
            const min = 550;
            const max = 560;
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        sendToTripSearch() {
            this.$router.push({
                name: 'tourinfo',
                params: {
                    selectedSido: this.selectedSido,
                    selectedGugun: this.selectedGugun,
                    selectedContentById: this.selectedContentById,
                }
            });
        }
    },
}
</script>

<style scoped>
    .margin-top{
        margin-top: 50px;
    }
</style>