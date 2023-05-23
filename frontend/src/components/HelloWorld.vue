<template>
    <v-main class="white" style="margin-bottom: 20px">
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
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 5) ? '' : 'none'}" >광주</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 6) ? '' : 'none'}" >부산</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 7) ? '' : 'none'}" >울산</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 8) ? '' : 'none'}" >세종</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 31) ? '' : 'none'}" >경기</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 32) ? '' : 'none'}" >강원</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 33) ? '' : 'none'}" >충북</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 34) ? '' : 'none'}" >충남</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 35) ? '' : 'none'}" >경북</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 36) ? '' : 'none'}" >경남</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 37) ? '' : 'none'}" >전북</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 38) ? '' : 'none'}" >전남</v-chip>
                                <v-chip class="custom-chip" :style="{display : cards.some(card => card.sidoCode === 39) ? '' : 'none'}" >제주</v-chip>
                                

                            </v-chip-group>
                        </v-card-text>

                        <v-row>
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
                                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                    >
                                    <span
                                        class="text-h5 white--text pl-4 pt-4 d-inline-block"
                                        v-text="card.title"
                                    ></span>
                                    </v-img>


                                    <v-card-actions class="white justify-center">
                                    <v-btn
                                        :color="'green'"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                        @click="redirectToNaverSite"
                                    >
                                        <v-icon> mdi-alpha-n-circle </v-icon>
                                    </v-btn>
                                    <v-btn
                                        :color="'indigo'"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                        @click="redirectToFaceBookSite"
                                    >
                                        <v-icon> mdi-facebook </v-icon>
                                    </v-btn>
                                    <v-btn
                                        :color="'red lighten-3'"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                        @click="redirectToInstagramSite"
                                    >
                                        <v-icon> mdi-instagram </v-icon>
                                    </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-col>
                            <v-spacer></v-spacer>
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
            /* 상단 여행 정보 표시 변수 */
            sido: [],
            gugun: [],
            selectedSido: null,
            selectedGugun: null,
            selectedContentById: [],
            contentByType: [
                { id: 1, value: "전체" },
                { id: 12, value: "관광지" },
                { id: 14, value: "문화시설" },
                { id: 15, value: "축제공연행사" },
                { id: 25, value: "여행코스" },
                { id: 28, value: "레포츠" },
                { id: 32, value: "숙박" },
                { id: 38, value: "쇼핑" },
                { id: 39, value: "음식점" }
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
        this.create_sido();
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
                    this.tempSelection = val + 30;
                } else {
                    console.log(val);
                    this.tempSelection = val + 1;
                }
            }
        },
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
        getLikelist() {
            http.get(`recommend/like`)
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
        sendToTripSearch() {
            this.$router.push({
                name: 'tourinfo',
                params: {
                    selectedSido: this.selectedSido,
                    selectedGugun: this.selectedGugun,
                    selectedContentById: this.selectedContentById,
                }
            });
        },
        redirectToFaceBookSite() {
            const searchTerm = '도산공원';
            const searchUrl = `https://www.facebook.com/search/posts/?q=${encodeURIComponent(searchTerm)}`;
            window.location.href = searchUrl;
        },
        redirectToInstagramSite() {
            const searchTerm = '도산공원';
            const searchUrl = `https://www.instagram.com/explore/tags/${encodeURIComponent(searchTerm)}/`;
            window.location.href = searchUrl;
        },
        redirectToNaverSite() {
            const searchTerm = '도산공원';
            const searchUrl = `https://search.naver.com/search.naver?query=${encodeURIComponent(searchTerm)}`;
            window.location.href = searchUrl;
        }
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