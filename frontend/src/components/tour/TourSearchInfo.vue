<template>
<v-app>
    <v-main class="white">
    <v-container class="text-center">
    <h1>여행정보</h1>
    <v-divider class="my-2"></v-divider>
        <v-row style="margin-top: 10px;">
            <!-- Left tour map position -->
            <v-col cols="6">
            <v-sheet
                min-height="70vh"
                rounded="lg"
                elevation="8"
                class="lighten-5 overflow-auto"
                style="padding: 10px; margin-bottom: 30px;"
            >
                <div style="display:flex">
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
                        @click="selectedSido !== null && selectedGugun !== null ? (keyword.length==0 ? markList() : markListwithSearch()) : sidogugunException()"
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
                <div>
                    <v-switch
                        class="mt-0"
                        v-model="showKeyword"
                        color="indigo"
                        value="indigo"
                        hide-details
                    >
                    <template v-slot:label>
                        <span class="custom-search-label">상세 검색</span>
                    </template>
                    </v-switch>
                </div>
                <div style="display:flex" v-if="showKeyword">
                    <v-text-field label="키워드 입력" v-model="keyword" @keyup.enter="selectedSido !== null && selectedGugun !== null ? (keyword.length==0 ? markList() : markListwithSearch()) : sidogugunException()"></v-text-field>
                </div>
                <div class="mt-3 me-3">
                    <div id="map" class="custom-sheet" style="width: 102%; height: 600px;"></div>
                </div>

            </v-sheet>
            </v-col>
            <!-- Right tour List position -->
            <v-col cols=6>
            <v-sheet
                min-height="85vh"
                max-height="110"
                rounded="lg"
                elevation="8"
                class="grey lighten-5 overflow-auto"
                style="padding: 10px;"
            >
            <div v-if="rightElement">
                <v-btn class="ma-1" outlined color="indigo" @click="viewSmall()">Size Down<v-icon>mdi-arrow-down</v-icon></v-btn>            
                <v-btn class="ma-1" outlined color="indigo" @click="viewBig()">Size Up<v-icon>mdi-arrow-up</v-icon></v-btn>
                <v-text-field label="검색 범위 내 결과 재검색" v-model="liveKeyword"></v-text-field>
                <div v-if="!displayedCards.length" justify="center" align="center"  style="color: gray; opacity:0.3;">
                    <h1>검색 결과를 찾을 수 없습니다.</h1>
                </div>
            </div>
            <v-container fluid>
                <v-row dense>
                <v-col
                    v-for="(card, index) in displayedCards"
                    :key="card.title"
                    :cols="card.flex"
                >
                    <v-card>
                    <v-img
                        :src="card.src"
                        class="white--text align-end card-image"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="200px"
                    >
                        <v-card-title style="font-size: medium;">{{ card.title }}</v-card-title>
                        <v-btn
                            icon
                            class="icon-wrapper"
                            @click="card.show = !card.show"
                            style= "background-color: rgba(255,255,255,0.7);"
                            fab
                            small
                            absolute
                            >
                            <!-- <v-icon>{{ card.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon> -->
                            <v-icon color="indigo">mdi-book-information-variant</v-icon>
                        </v-btn>
                    </v-img>
                    <!-- eslint-disable-next-line -->
                    <v-card-actions >
                        <!-- 버튼 추가 할거면 여기 -->
                        <v-col>
                        <v-row>
                            <v-btn icon @click="moveCenter(index)" ref="click">
                            <v-icon color="black">mdi-map-search</v-icon>
                            </v-btn>
                        </v-row>
                        <v-row>
                            <v-btn icon @click.stop="card.like ? deleteOpenDialog(card.id) : addOpenDialog(card.id)">
                            <v-icon :color="card.like ? 'red' : ''">mdi-heart</v-icon>
                            </v-btn>
                        </v-row>
                        </v-col>
                        <v-spacer style="font-size: small;">{{ card.addr_1 }}</v-spacer>
                        
                    </v-card-actions>
                    <v-bottom-sheet
                        v-model="card.show"
                        inset: width=30%
                        >
                        <v-sheet class="custom-sheet text-center" >
                            <div>
                                <div weight="100px">
                                    <v-img 
                                    :src="showImg(card.src)"
                                    height="200px"
                                    ></v-img>
                                </div>
                                <div class="pe-5 ps-5">
                                    <div><h3>{{ card.title }}</h3></div>
                                    <div class="mt-2"><h4>{{ card.addr_1 }}</h4></div>
                                    <div class="my-3">
                                        {{ card.overview }}
                                    </div>
                                </div>
                                <v-btn
                                class="mt-6 mb-2"
                                text
                                color="error"
                                @click="card.show = !card.show"
                                >
                                닫기
                                </v-btn>
                            </div>
                        </v-sheet>
                    </v-bottom-sheet>
                    </v-card>
                </v-col>
                </v-row>
            </v-container>

            </v-sheet>
            </v-col>
        </v-row>
    </v-container>
    </v-main>
    <add-tour-dialog ref="addOverlay" @agreed="addAgree=true"></add-tour-dialog>
    <delete-tour-dialog ref="deleteOverlay" @agreed="deleteAgree=true" ></delete-tour-dialog>
</v-app>
</template>



<script>
import http from "@/axios/http";
import AddTourDialog from '@/components/tour/AddTourDialog.vue';
import DeleteTourDialog from '@/components/tour/DeleteTourDialog.vue';
import {mapState} from "vuex";
const memberStore = "memberStore";

export default {
    name: 'TourSearchInfo',
    components: {
        AddTourDialog,
        DeleteTourDialog
    },
    data() {
        return {
            map: null,
            sido: [],
            gugun: [],
            selectedSido: '',
            selectedGugun: '',
            keyword: '',
            liveKeyword: '',
            showKeyword: false,
            selectedContentById: [],
            positions: [],
            markers: [],
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
            cards: [],
            overlay: [],
            clickedOverlay: null,
            selectAll: false,
            selectedId: null,
            addAgree: false,
            deleteAgree: false,
            rightElement: false,
            loginLikeData: []
        };
    },
    created() {
        if (this.$route.params.selectedSido != null) {
            this.selectedSido = this.$route.params.selectedSido;
        }
        this.create_sido();
        if (this.$route.params.selectedGugun != null) {
            this.selectedGugun = this.$route.params.selectedGugun;
        }
        if (this.$route.params.selectedContentById != null) {
            this.selectedContentById = this.$route.params.selectedContentById;    
        }
        this.myLikeList();
    },
    mounted() {
        const script = document.createElement('script');
        script.onload = () => {
            if (window.kakao && window.kakao.maps) {
                window.kakao.maps.load(() => {
                    const mapContainer = document.getElementById("map"); // 지도를 표시할 div
                    const mapOption = {
                        center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
                        level: 5, // 지도의 확대 레벨
                    };

                    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
                    this.map = new window.kakao.maps.Map(mapContainer, mapOption);
                    this.createRightBar();
                });
            }
        };
        script.async = true;
        script.type = 'text/javascript';
        script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=1b7a0eb6294cdd4b1f985683a25bd972";
        document.head.appendChild(script);
    },
    computed: {
        ...mapState(memberStore, ["isLogin", "userInfo"]),
        showImg() { // 이미지 가져오기
            return (src) => {
                if (src) {
                // 실제 이미지 파일이 있는 경우
                return src;
                } else {
                // 이미지 파일이 없는 경우 noimg.png 사용
                return require('@/assets/mark/noimg.png');
                }
            };
        },
        filteredCards(){
            const tempKeyword = this.liveKeyword.replace(/\s/g, '').toLowerCase();
            return this.cards.filter(card => {
                // 검색어가 카드의 특정 속성에 포함되는지 확인 (공백 무시)
                const tempTitle = card.title.replace(/\s/g, '').toLowerCase();
                return tempTitle.includes(tempKeyword);
            });
        },
        displayedCards(){
            if(this.liveKeyword){
                return this.filteredCards;
            }else{
                return this.cards;
            }
        }
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
        addAgree: {
            handler(val) {
            if (val) {
                this.addTour(this.selectedId); // 예시로 this.id를 매개변수로 사용
                this.addAgree = false;
            }
            },
            immediate: false
        },
        deleteAgree: {
            handler(val) {
            if (val) {
                this. tripDelete(this.selectedId); // 예시로 this.id를 매개변수로 사용
                this.deleteAgree = false;
            }
            },
            immediate: false
        },
        // showKeyword가 false이면 keyword값 초기화
        showKeyword(newValue) {
            if (!newValue) {
                this.keyword = '';
            }
        }
    },
    methods: {
        createRightBar() {
            //일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
            this.mapTypeControl = new window.kakao.maps.MapTypeControl();

            // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
            // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
            this.map.addControl(this.mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT);

            // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
            var zoomControl = new window.kakao.maps.ZoomControl();
            this.map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);
        },
        addTour(id) {
            http.post(`/tour/addtour/${this.userInfo.data.memberId}/${id}`).then(response => {
                if (response.status === 200) {
                    // alert("마이페이지에 여행지가 추가되었습니다.");
                    // 해당 ID에 해당하는 요소를 찾고, like 속성을 변경
                    // this.loginLikeData.push(id); 해도 되구 안해도 되구???? 궁금!!
                    const card = this.cards.find((card) => card.id === id);
                    if (card) {
                        card.like = true;
                    }
                    return response.data;
                } else if (response.status === 500) {
                    alert("추가 중 에러 발생. 이미 추가된 여행지입니다.");
                    throw new Error('Network response was not ok.');
                }
            })
            .catch(error => {
                console.error(error);
            });    
            this.agree = false;
        },
        myLikeList() {
            if (this.userInfo.data.memberId !== null) {
                http.get(`/tour/mylikelist/${this.userInfo.data.memberId}`)
                .then((response) => {
                    this.loginLikeData = response.data.map((area) => area.contentId);
                });    
            }
        },
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
        markList() {
            this.rightElement = true;
            this.liveKeyword = null;
            http.get(`/tour/attraction-info?search-area=${this.selectedSido}&search-area-gu=${this.selectedGugun}`)
                .then((response) => {
                    this.positions.length = 0;
                    this.cards.length = 0;
                    response.data.forEach((area) => {
                        // 체크된것만!
                        if (this.selectedContentById.includes(area.contentTypeId)) {
                            let markerInfo = {
                                id: area.contentId,
                                img: area.firstImage,
                                title: area.title,
                                addr_1: area.addr1,
                                addr_2: area.addr2,
                                zip: area.zipcode,
                                tel: area.tel,
                                contenttypeid: area.contentTypeId,
                                latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude),
                            };
                            let card = {
                                id: area.contentId,
                                src: area.firstImage,
                                title: area.title,
                                addr_1: area.addr1,
                                overview: area.overView,
                                latitude: area.latitude,
                                longitude: area.longitude,
                                flex: 3,
                                show: false,
                                like: this.loginLikeData.includes(area.contentId)
                            }
                            this.positions.push(markerInfo);
                            this.cards.push(card);
                        }
                    });
                })
                .then(() => { 
                    this.removeMarker();
                    this.displayMarker();
                });
        },
        markListwithSearch() {
            this.rightElement = true;
            this.liveKeyword = null;
            http.get(`/tour/attraction-info-bykeyword?search-area=${this.selectedSido}&search-area-gu=${this.selectedGugun}&keyword=${this.keyword}`)
            .then((response) => { 
                    this.positions.length = 0;
                    this.cards.length = 0;
                    response.data.forEach((area) => {
                        // 체크된것만!
                        if (this.selectedContentById.includes(area.contentTypeId)) {
                            let markerInfo = {
                                id: area.contentId,
                                img: area.firstImage,
                                title: area.title,
                                addr_1: area.addr1,
                                addr_2: area.addr2,
                                zip: area.zipcode,
                                tel: area.tel,
                                contenttypeid: area.contentTypeId,
                                latlng: new window.kakao.maps.LatLng(area.latitude, area.longitude),
                            };
                            let card = {
                                id: area.contentId,
                                src: area.firstImage,
                                title: area.title,
                                addr_1: area.addr1,
                                overview: area.overView,
                                latitude: area.latitude,
                                longitude: area.longitude,
                                flex: 3,
                                show: false,
                                like: this.loginLikeData.includes(area.contentId)
                            }
                            this.positions.push(markerInfo);
                            this.cards.push(card);
                        }
                    });
                     // 체크된 항목이 없는 경우 카드를 제거
                    if (this.cards.length === 0) {
                        this.cards = [];
                    }
                })
                .then(() => { 
                    this.removeMarker();
                    this.displayMarker();
                });
        },
        displayMarker() {
            var imageSrc = "";
            // let index = 0;
            // 마커 이미지
            for (var i = 0; i < this.positions.length; i++) {
                if (this.positions[i].contenttypeid == 12) {
                    imageSrc = require("@/assets/mark/mark1.png");
                }else if(this.positions[i].contenttypeid == 14){
                    imageSrc = require("@/assets/mark/mark2.png");
                }else if(this.positions[i].contenttypeid == 15){
                    imageSrc = require("@/assets/mark/mark3.png");
                }else if(this.positions[i].contenttypeid == 25){
                    imageSrc = require("@/assets/mark/mark4.png");
                }else if(this.positions[i].contenttypeid == 28){
                    imageSrc = require("@/assets/mark/mark5.png");
                }else if(this.positions[i].contenttypeid == 32){
                    imageSrc = require("@/assets/mark/mark6.png");
                }else if(this.positions[i].contenttypeid == 38){
                    imageSrc = require("@/assets/mark/mark7.png");
                }else if(this.positions[i].contenttypeid == 39){
                    imageSrc = require("@/assets/mark/mark8.png");
                }
        
                // 마커 이미지의 이미지 크기 입니다
                /*var imageSize = new kakao.maps.Size(100, 80);*/
                var imageSize = new window.kakao.maps.Size(100, 69); // 마커이미지의 크기입니다
            
                // 마커 이미지를 생성합니다
                var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize);

                // 마커를 생성합니다
                this.markers[i] = new window.kakao.maps.Marker({
                    map: this.map, // 마커를 표시할 지도
                    position: this.positions[i].latlng, // 마커를 표시할 위치
                    title: this.positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image: markerImage, // 마커 이미지
                });
            }
            // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
            if(this.positions.length) this.map.setCenter(this.positions[0].latlng);

            this.showOverlay();
            

        },
        removeMarker() {
            for ( var i = 0; i < this.markers.length; i++ ) {
                this.markers[i].setMap(null);
            }   
            this.markers = [];
        },
        moveCenter(index) {
            if(this.clickedOverlay){
                this.clickedOverlay.setMap(null);
            }
            // console.log(index);
            window.kakao.maps.event.trigger(this.markers[index], 'click');
            
        },
        showOverlay() {
             // 마커를 클릭하면 오버레이를 띄운다
            for (let i = 0; i < this.markers.length; i++){
                window.kakao.maps.event.addListener(this.markers[i], 'click', () => {
                    // let display = "";
                    // 좋아요 저장할 때 쓸 꺼
                    // temp_id = this.positions[i].id;
                    // if(myLocationlist.includes(this.positions[i].id)){
                    //     display = "none";
                    // }
                    const noimg = require("@/assets/mark/noimg.png");
                    var content = '<div class="wrap">' + 
                    '    <div class="info">' + 
                    '        <div class="title">' + 
                    `           ${this.positions[i].title}` + 
                    '            <div class="close" title="닫기"></div>' + 
                    '        </div>' + 
                    '        <div class="body" style="background: white;">' + 
                    '            <div class="img">' +
                    `                <img class="imgtag" src="${this.positions[i].img}" onerror="this.src='${noimg}'" width="73" height="70">` +
                    '           </div>' + 
                    '            <div class="desc">' + 
                    `                <div class="ellipsis">${this.positions[i].addr_1}</div>` + 
                    `                <div class="jibun ellipsis">(우) ${this.positions[i].zip}</br>(전화번호) ${this.positions[i].tel}</div>` + 
                    '            </div>' + 
                    '        </div>' + 
                    '    </div>' +    
                    '</div>';

                    // 직접 div를 만들어서 처리
                    var div = document.createElement('div');
                    div.innerHTML = content;
                    div.querySelector('.close').addEventListener('click', () => {
                        this.overlay.setMap(null);  
                    });          

                    this.overlay = new window.kakao.maps.CustomOverlay({
                        content: div,
                        map: this.map,
                        position: this.positions[i].latlng,   
                    });
                    
                    // 마커 클릭 시 부드럽게 가운데로 이동시키기 
                    if(this.clickedOverlay){
                        this.clickedOverlay.setMap(null);
                    }
                    this.overlay.setMap(this.map);
                    this.clickedOverlay = this.overlay;
                    
                    this.map.panTo(this.overlay.getPosition());
                });
            }	
        },
        viewSmall() {
            const cardImages = document.querySelectorAll('.card-image');
            cardImages.forEach(cardImage => {
            cardImage.style.height = '80px';
            });
        },
        viewBig() {
            const cardImages = document.querySelectorAll('.card-image');
            cardImages.forEach(cardImage => {
            cardImage.style.height = '200px';
            });
        },
        tripDelete(id) {
            http.delete(`/mypage/delete/${this.userInfo.data.memberId}/${id}`)
            .then(() => {
                // 여기서도 loginLikeData를 pop() 해야하는지 의문?? 
                const card = this.cards.find((card) => card.id === id);
                if (card) {
                    card.like = false;
                }
            })
            .catch((error) => {
                console.error(error);
            });
        },
        async addOpenDialog(id) {
            this.$refs.addOverlay.openDialog();
            this.selectedId = id;
        },
        async deleteOpenDialog(id){
            this.$refs.deleteOverlay.openDialog();
            this.selectedId = id;
        },
        sidogugunException(){
            alert("시,도 혹은 군, 구를 반드시 선택해주세요!!");
        }
        

    },
};
</script>

<style>
.wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
.wrap * {padding: 0;margin: 0;}
.wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
.wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
.info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
.info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
.info .close:hover {cursor: pointer;}
.info .body {position: relative;overflow: hidden;}
.info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
.desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
.info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
.info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.custom-sheet{
    height: auto;
    max-height: calc(100vh - 200px); /* 원하는 높이 조정 */
    overflow-y: auto;
}
.icon-wrapper {
    position: absolute;
    bottom: 0;
    right: 0;
    margin:3px;
}
.custom-search-label {
    font-size: 13px;
    color: black;
}
</style>