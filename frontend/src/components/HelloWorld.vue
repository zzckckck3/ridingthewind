<template>
    <v-main class="custom-main-backgoround" style="margin-bottom: 20px">
        <v-container>
            <v-row>
                <!--Left Container-->

                <!--Center Container-->
                <v-col class="text-center" cols="9" sm="9" style="margin-bottom: 50px;">
                    <v-sheet
                        rounded="lg"
                        elevation="8"
                        class="grey lighten-4 overflow-auto"
                        style="padding: 10px; padding-left: 20px; padding-right: 20px;"
                    >
                        <h1 style="padding-top: 20px; color: rgb(255, 205, 74); text-stroke: 1px black;">여행정보 검색</h1>
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
                        <v-sheet
                            class="grey lighten-4 rounded-lg"
                            style="padding-left: 40px; padding-right: 80px; padding-top: 30px; padding-bottom: 40px;"
                            elevation="6"
                            fluid
                        >
                        <v-subheader class="text-1">찜 많은순 ( 지역별 )</v-subheader>
                        
                        <v-card-text>
                            <v-chip-group
                                v-model="sidoSelection"
                                active-class="accent-4 white--text"
                                column
                            >
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 1) ? '' : 'none'}" >서울</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 2) ? '' : 'none'}" >인천</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 3) ? '' : 'none'}" >대전</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 4) ? '' : 'none'}" >대구</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 5) ? '' : 'none'}" >광주</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 6) ? '' : 'none'}" >울산</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 7) ? '' : 'none'}" >세종</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 8) ? '' : 'none'}" >부산</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 31) ? '' : 'none'}" >경기</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 32) ? '' : 'none'}" >강원</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 33) ? '' : 'none'}" >충북</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 34) ? '' : 'none'}" >충남</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 35) ? '' : 'none'}" >경북</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 36) ? '' : 'none'}" >경남</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 37) ? '' : 'none'}" >전북</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 38) ? '' : 'none'}" >전남</v-chip>
                                <v-chip class="custom-chip-like" :style="{display : cards.some(card => card.sidoCode === 39) ? '' : 'none'}" >제주</v-chip>

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
                                    :src="showImg(card.src)"
                                    height="300px"
                                    gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                    class="align-end"
                                    >
                                    <v-card-title class="text-h6 white--text pl-4 pt-4 d-inline-block font-weight-bold text-custom-2">{{ card.title }}</v-card-title>
                                    </v-img>
                                    <v-card-actions class="white justify-center">

                                    <v-icon class="custom-heart"  :color="'red'"> mdi-heart-multiple </v-icon><h5 class="custom-heart-text">{{ card.totalCount }}</h5>

                                    <v-btn
                                        :color="'green'"
                                        class="white--text"
                                        style="margin-left: 40px;"
                                        fab
                                        icon
                                        small
                                        @click="redirectToNaverSite(card.title)"
                                    >
                                        <v-icon> mdi-alpha-n-circle </v-icon>
                                    </v-btn>
                                    <v-btn
                                        :color="'indigo'"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                        @click="redirectToFaceBookSite(card.title)"
                                    >
                                        <v-icon> mdi-facebook </v-icon>
                                    </v-btn>
                                    <v-btn
                                        :color="'red lighten-3'"
                                        class="white--text"
                                        fab
                                        icon
                                        small
                                        @click="redirectToInstagramSite(card.title)"
                                    >
                                        <v-icon> mdi-instagram </v-icon>
                                    </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-col>
                            <v-spacer></v-spacer>
                        </v-row>
                        </v-sheet>
                    </v-row>

                    <!--하단 시도 별 postcount-->
                    <v-row class="margin-top">
                    <v-sheet
                        class="grey lighten-4 rounded-lg"
                        style="padding-left: 40px; padding-right: 80px; padding-top: 30px; padding-bottom: 40px;"
                        elevation="6"
                        fluid
                    >
                    <v-subheader class="text-1">포스팅 많은 순 ( 시도별 )</v-subheader>
                    
                    <v-card-text>
                        <v-chip-group
                            v-model="postSelection"
                            active-class="accent-4 white--text"
                            column
                        >
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 1) ? '' : 'none'}" >서울</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 2) ? '' : 'none'}" >인천</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 3) ? '' : 'none'}" >대전</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 4) ? '' : 'none'}" >대구</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 5) ? '' : 'none'}" >광주</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 6) ? '' : 'none'}" >부산</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 7) ? '' : 'none'}" >울산</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 8) ? '' : 'none'}" >세종</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 31) ? '' : 'none'}" >경기</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 32) ? '' : 'none'}" >강원</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 33) ? '' : 'none'}" >충북</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 34) ? '' : 'none'}" >충남</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 35) ? '' : 'none'}" >경북</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 36) ? '' : 'none'}" >경남</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 37) ? '' : 'none'}" >전북</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 38) ? '' : 'none'}" >전남</v-chip>
                            <v-chip class="custom-chip-post" :style="{display : popularSidoList.some(popularSido => popularSido.sidoCode === 39) ? '' : 'none'}" >제주</v-chip>

                        </v-chip-group>
                    </v-card-text>

                    <v-row>
                        <v-col
                            v-for="popularSido in filteredPopularSidos"
                            :key="popularSido.id"
                            cols="12"
                            sm="6"
                            md="3"
                        >
                            <v-card>
                                <v-img
                                :src="showImg(popularSido.src)"
                                height="300px"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                class="align-end"
                                >
                                <v-card-title class="text-h6 white--text pl-4 pt-4 d-inline-block font-weight-bold text-custom-2">{{ popularSido.title }}</v-card-title>
                                </v-img>
                                <v-card-actions class="white justify-center">

                                <v-icon class="custom-heart" :color="'gray'"> mdi-note-edit </v-icon><h5 class="custom-post-text ms-3">{{ popularSido.postCount }}</h5>

                                <v-btn
                                    :color="'green'"
                                    class="white--text"
                                    style="margin-left: 40px;"
                                    fab
                                    icon
                                    small
                                    @click="redirectToNaverSite(popularSido.title)"
                                >
                                    <v-icon> mdi-alpha-n-circle </v-icon>
                                </v-btn>
                                <v-btn
                                    :color="'indigo'"
                                    class="white--text"
                                    fab
                                    icon
                                    small
                                    @click="redirectToFaceBookSite(popularSido.title)"
                                >
                                    <v-icon> mdi-facebook </v-icon>
                                </v-btn>
                                <v-btn
                                    :color="'red lighten-3'"
                                    class="white--text"
                                    fab
                                    icon
                                    small
                                    @click="redirectToInstagramSite(popularSido.title)"
                                >
                                    <v-icon> mdi-instagram </v-icon>
                                </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>
                        <v-spacer></v-spacer>
                    </v-row>
                    </v-sheet>
                </v-row>

                <!--하단 구군 별 postcount-->
                <v-row class="margin-top">
                    <v-sheet
                        class="grey lighten-4 rounded-lg"
                        style="padding-left: 40px; padding-right: 80px; padding-top: 30px; padding-bottom: 40px;"
                        elevation="6"
                        fluid
                    >
                    <v-subheader class="text-1">포스팅 많은 순 ( 구군별 )</v-subheader>
                    
                    <v-card-text>
                        <v-chip-group
                            v-model="postSelectionByGugun"
                            active-class="accent-4 white--text"
                            column
                        >
                        <v-chip  v-for="gugun in popularGugunListCodeAndName" :key="gugun.gugunName" class="custom-chip-post" >
                            {{gugun.gugunName}}
                        </v-chip>

                        </v-chip-group>
                    </v-card-text>

                    <v-row>
                        <v-col
                            v-for="popularGugun in filteredPopularGuguns"
                            :key="popularGugun.id"
                            cols="12"
                            sm="6"
                            md="3"
                        >
                            <v-card>
                                <v-img
                                :src="showImg(popularGugun.src)"
                                height="300px"
                                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                                class="align-end"
                                >
                                <v-card-title class="text-h6 white--text pl-4 pt-4 d-inline-block font-weight-bold text-custom-2">{{ popularGugun.title }}</v-card-title>
                                </v-img>
                                <v-card-actions class="white justify-center">

                                <v-icon class="custom-heart"  :color="'gray'"> mdi-note-edit </v-icon><h5 class="custom-post-text ms-3">{{ popularGugun.postCount }}</h5>

                                <v-btn
                                    :color="'green'"
                                    class="white--text"
                                    style="margin-left: 40px;"
                                    fab
                                    icon
                                    small
                                    @click="redirectToNaverSite(popularGugun.title)"
                                >
                                    <v-icon> mdi-alpha-n-circle </v-icon>
                                </v-btn>
                                <v-btn
                                    :color="'indigo'"
                                    class="white--text"
                                    fab
                                    icon
                                    small
                                    @click="redirectToFaceBookSite(popularGugun.title)"
                                >
                                    <v-icon> mdi-facebook </v-icon>
                                </v-btn>
                                <v-btn
                                    :color="'red lighten-3'"
                                    class="white--text"
                                    fab
                                    icon
                                    small
                                    @click="redirectToInstagramSite(popularGugun.title)"
                                >
                                    <v-icon> mdi-instagram </v-icon>
                                </v-btn>
                                </v-card-actions>
                            </v-card>
                        </v-col>
                        <v-spacer></v-spacer>
                    </v-row>
                    </v-sheet>
                </v-row>
                </v-col>

                <!--Right Container-->
                <v-col class="text-center" cols="3" sm="3">
                    <v-sheet
                        class="grey lighten-5"
                        rounded="lg"
                        min-height="268"
                        elevation="8"
                    >
                    <v-list subheader>
                    <v-subheader class="custom-subheader">국내여행 관련 기사 (중앙일보)</v-subheader>

                    <v-divider></v-divider>
                    <v-list-item
                        v-for="crawledArticle in crawledArticles"
                        :key="crawledArticle.title"
                    >
                        <v-list-item-avatar height="70px" width="70px">
                        <v-img
                            :src="crawledArticle.src"
                        ></v-img>
                        </v-list-item-avatar>

                        <v-list-item-content>
                        <v-list-item-title>{{crawledArticle.title}}</v-list-item-title>
                        </v-list-item-content>

                        <v-list-item-icon>
                        <v-btn icon :href="crawledArticle.href" target="_blank">
                        <v-icon color="grey">
                            mdi-newspaper-variant-multiple-outline
                        </v-icon>
                        </v-btn>
                        </v-list-item-icon>
                    </v-list-item>
                    </v-list>
                    </v-sheet>
                </v-col>
            </v-row>
            <before-login-bottom-sheet> </before-login-bottom-sheet>
        </v-container>
        <v-spacer></v-spacer>
    </v-main>
</template>

<script>
import http from "@/axios/http";
import axios from "axios";
import BeforeLoginBottomSheet from '@/components/layout/BeforeLoginBottomSheet.vue';

// import { WEATHER_API_KEY } from '@/api/appKey.js';

export default {
    components: {
        BeforeLoginBottomSheet
    },
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
            tempSelection: 99,
            cards: [],

            postSelection: 0,
            tempPostSelection: 99,
            popularSidoList: [],

            popularGugunList: [],
            popularGugunListCodeAndName: [],
            postSelectionByGugun: 0,

            crawledArticles: [],
        };
    },
    created() {
        this.getLikelist();
        this.create_sido();
        this.getPostCount();
        this.getPostCountByGugun();
        this.performCrawling();
    },
    computed: {
        filteredCards() {
            const filtered = this.cards.filter(card => this.tempSelection === card.sidoCode);
            return filtered.slice(0, 4);
        },
        filteredPopularSidos() {
            return this.popularSidoList.filter(popularSido => this.tempPostSelection === popularSido.sidoCode);
            
        },
        filteredPopularGuguns() {
            const nowGugunCodeObj = this.popularGugunListCodeAndName[this.postSelectionByGugun];
            if (nowGugunCodeObj) {
            const nowGugunCode = nowGugunCodeObj.gugunCode;
            const nowSidoCode = nowGugunCodeObj.sidoCode;
            return this.popularGugunList.filter(popularGugun => nowSidoCode === popularGugun.sidoCode && nowGugunCode === popularGugun.gugunCode);
            } else {
            return [];
            }
        },
        showImg() { // 이미지 가져오기
            return (src) => {
                if (src) {
                // 실제 이미지 파일이 있는 경우
                return src;
                } else {
                // 이미지 파일이 없는 경우 noimg.png 사용
                return require('@/assets/mark/noimg2.jpg');
                }
            };
        },
    },
    watch: {
        sidoSelection: {
            handler(val) {
                if (val > 7) {
                    this.tempSelection = val + 23;
                } else {
                    this.tempSelection = val + 1;
                }
            }
        },
        postSelection: {
            handler(val) {
                if (val > 7) {
                    this.tempPostSelection = val + 23;
                } else {
                    this.tempPostSelection = val + 1;
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
                    response.data.forEach((area) => {
                        if (area.sidoCode < this.tempSelection) {
                            this.tempSelection = area.sidoCode;
                        }
                        let card = {
                            id: area.contentId,
                            src: area.firstImage,
                            title: area.title,
                            sidoCode: area.sidoCode,
                            totalCount: area.totalCount
                        }
                        this.cards.push(card);
                    });
                });
                this.tempSelection = this.tempSelection === 99 ? 1 : this.tempSelection;
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
        getPostCount() {
            let popularSidoList = [];
            http.get(`recommend/post-count/sido/post_count`)
                .then((response) => {
                    response.data.forEach((popularSido) => {
                        popularSidoList.push(popularSido.sidoCode);
                    })

                    const promises = popularSidoList.map((sido) => {
                        if (sido.sidoCode < this.tempPostSelection) {
                            this.tempPostSelection = sido.sidoCode;
                        }
                        return http.get(`recommend/get-popular-content/sido/${sido}`)
                            .then((response) => {
                                response.data.forEach((area) => {
                                    let card = {
                                        id: area.contentId,
                                        src: area.firstImage,
                                        title: area.title,
                                        sidoCode: area.sidoCode,
                                        postCount: area.postCount
                                    }
                                    this.popularSidoList.push(card)
                                });
                            });
                    });
                    this.tempPostSelection = this.tempPostSelection === 99 ? 1 : this.tempPostSelection;
                    return Promise.all(promises);
                })
                .catch((error) => {
                    console.error(error);
                }); 
            
        },
        getPostCountByGugun() {
            let popularSidoList = [];
            let popularGugunList = [];
            let popularCombine = [];

            http.get(`recommend/post-count/gugun/post_count`)
            .then((response) => {
                response.data.forEach((popular) => {
                    popularSidoList.push(popular.sidoCode);
                    popularGugunList.push(popular.gugunCode);
                })

                popularCombine = popularSidoList.map((sido, index) => {
                    return { sidoCode: sido, gugunCode: popularGugunList[index] };
                });
                //console.log(popularCombine);

                const promises = [];

                popularCombine.forEach((item) => {
                    promises.push(http.get(`recommend/get-popular-content/gugun/${item.sidoCode}/${item.gugunCode}`)
                        .then((response) => {
                            //console.log(response.data);
                            response.data.forEach((area) => {
                                let card = {
                                    id: area.contentId,
                                    src: area.firstImage,
                                    title: area.title,
                                    sidoCode: area.sidoCode,
                                    gugunCode: area.gugunCode,
                                    postCount: area.postCount
                                }
                                this.popularGugunList.push(card)
                            });
                        }));
                    promises.push(http.get(`/tour/gugun?search-area=${item.sidoCode}`)
                        .then((response) => {
                        let gugun = {
                            sidoCode: item.sidoCode,    
                            gugunCode: item.gugunCode,
                            gugunName: response.data[item.gugunCode-1].gugunName   
                        }
                        this.popularGugunListCodeAndName.push(gugun);
                        // console.log(response);
                        }));
                });
                console.log(this.popularGugunListCodeAndName);
                return Promise.all(promises);
            })
            .catch((error) => {
                console.error(error);
            });
        },
        redirectToFaceBookSite(searchTerm) {
            const searchUrl = `https://www.facebook.com/search/posts/?q=${encodeURIComponent(searchTerm)}`;
            window.open(searchUrl, "_blank");
        },
        redirectToInstagramSite(searchTerm) {
            const searchUrl = `https://www.instagram.com/explore/tags/${encodeURIComponent(searchTerm)}/`;
            window.open(searchUrl, "_blank");
        },
        redirectToNaverSite(searchTerm) {
            const searchUrl = `https://search.naver.com/search.naver?query=${encodeURIComponent(searchTerm)}`;
            window.open(searchUrl, "_blank");
        },
        async performCrawling() {
            try {
                axios.get(`/api`)
                    .then((response) => { 
                        const parser = new DOMParser();
                        const htmlDoc = parser.parseFromString(response.data, "text/html");
                        for(let i=0; i<5; i++){
                            const articleElement = this.findArticleElement(htmlDoc, i); // 기사가 있는 하위 태그 선택
                            if (articleElement) {
                                let tempTitle = articleElement.innerText; // 기사 내용 추출
                                const title = tempTitle.replace(/\n/g, "");
                                const anchorElement = articleElement.querySelector('a');
                                const imgElement = articleElement.querySelector('img');
                                let href = '';
                                let src = '';
                                if(anchorElement){
                                    href = anchorElement.getAttribute('href');
                                }
                                if(imgElement){
                                    src = imgElement.getAttribute('src');
                                }
                                let article = {
                                    title: title,
                                    href: href,
                                    src: src
                                }
                                this.crawledArticles.push(article);
                            } else {
                                console.log("기사를 찾을 수 없습니다.");
                            }
                        }
                        console.log(this.crawledArticles);
                    });
            } catch (error) {
                console.error(error);
            }
        },
        Test1() {
            // let Seoul = [60, 127];
            // let Incheon = [55, 124];
            // let Daejeon = [67, 100];
            // let Daegu = [89, 90];
            // let Gwangju = [58, 74];
            // let Pusan = [97, 76];
            // let Ulsan = [102, 84];
            // console.log(this.sido);
            // http.get(`https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=${WEATHER_API_KEY}&pageNo=1&numOfRows=12&dataType=JSON&base_date=20230524&base_time=0500&nx=${seoul[0]}&ny=${seoul[1]}`)
            //     .then((response) => {
            //         console.log(response.data.response.body.items.item);
            //     });
        },
        findArticleElement(htmlDoc, index) {
            const cardElements = htmlDoc.querySelectorAll('.card');
            if (cardElements.length > 1) {
                const nextCardElement = cardElements[index+6];
                return nextCardElement;
            } else {
                console.log("중복된 .card 클래스를 가진 요소가 부족합니다.");
            }
        },
    }
}
</script>

<style scoped>
    /* .custom-main-backgoround{
        background-image: linear-gradient(to right,  rgb(219, 217, 217), rgb(255, 245, 233));
    } */
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
    .custom-chip-like{
        font-size: large;
        padding: 20px;
        border-radius: 6px;
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.15);
        background-image: linear-gradient(to right, #8a77f7, #0767f7);
    }
    .custom-chip-post{
        font-size: large;
        padding: 20px;
        border-radius: 6px;
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.15);
        background-image: linear-gradient(to right, #FF6E40, #EA80FC);
    }
    .custom-heart{
        position: absolute;
        left: 0;
        margin-left:10px;
    }
    .custom-heart-text{
        color: red;
    }
    .custom-post-text{
        color: gray;
    }
    .font-weight-bold{
        font-style: oblique;
        text-shadow: 0px 2px 4px rgba(0, 0, 0, 0.5); 
    }
    .custom-subheader{
        font-size: unset;
        font-style: oblique;
        font-family: -webkit-body;
        font-weight: bold;
    }
</style>