<template>
<v-app id="inspire">
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
                style="padding: 10px;"
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
                    <v-select
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
                    ></v-select>
                    <v-btn
                        @click="markList"
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
                <div class="mt-3 me-3">
                    <div id="map" class="" style="width: 100%; height: 700px;"></div>
                </div>

            </v-sheet>
            </v-col>
            <!-- Right tour List position -->
            <v-col cols=6>
            <v-sheet
                min-height="85vh"
                max-height="100"
                rounded="lg"
                elevation="8"
                class="grey lighten-5 overflow-auto"
                style="padding: 10px;"
            >
                <!--여기다가 card 추가-->
                <v-container fluid>
                <v-row dense>
                <v-col
                    v-for="(card, index) in cards"
                    :key="card.title"
                    :cols="card.flex"
                >
                    <v-card>
                    <v-img
                        :src="card.src"
                        class="white--text align-end"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="200px"
                    >
                        <v-card-title style="font-size: medium;">{{ card.title }}</v-card-title>
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
                            <v-btn icon @click="card.like = !card.like">
                            <v-icon :color="card.like ? 'red' : '' ">mdi-heart</v-icon>
                            </v-btn>
                        </v-row>
                        </v-col>
                        <v-spacer>{{ card.addr_1 }}</v-spacer>
                        
                        <v-col class="d-flex align-end">
                        <v-row><v-spacer></v-spacer></v-row>
                        <v-row>
                            <v-btn
                            icon
                            @click="card.show = !card.show"
                            >
                            <!-- <v-icon>{{ card.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon> -->
                            <v-icon>mdi-book-information-variant</v-icon>
                            </v-btn>
                        </v-row>
                        </v-col>
                    </v-card-actions>
                    <v-bottom-sheet
                        v-model="card.show"
                        inset: width=30%
                        >
                        <v-sheet class="custom-sheet text-center" >
                            <div>
                                <div weight="100px">
                                    <v-img 
                                    :src="card.src"
                                    height="200px"
                                    ></v-img>
                                </div>
                                <div class="pe-5 ps-5">
                                    <div><h4>{{ card.title }}</h4></div>
                                    <div>{{ card.addr_1 }}</div>
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
</v-app>
</template>



<script>
import http from "@/axios/http";

export default {
    name: 'TourSearchInfo',
    components: {
    },
    data() {
        return {
            map: null,
            sido: [],
            gugun: [],
            selectedSido: null,
            selectedGugun: null,
            selectedContentById: [],
            positions: [],
            markers: [],
            contentByType: [
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
            clickedOverlay: null
        };
    },
    created() {
        this.create_sido();
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
    watch: {
        selectedSido: "create_gugun"
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
        create_sido() {
            http.get(`/tour/sido`)
                .then((response) => {
                    console.log(response.data);
                    this.sido = response.data;
                });
        },
        create_gugun() {
            http.get(`/tour/gugun?search-area=${this.selectedSido}`)
                .then((response) => {
                    console.log(response.data);
                    this.gugun = response.data;
                });
        },
        markList() {
            http.get(`/tour/attraction-info?search-area=${this.selectedSido}&search-area-gu=${this.selectedGugun}`)
                .then((response) => {
                    this.positions.length = 0;
                    this.cards.length = 0;
                    // console.log(response.data);
                    response.data.forEach((area) => {
                        // 체크된것만!
                        if (this.selectedContentById.includes(area.contentTypeId)){
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
                                like: false
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
            console.log(index);
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
                    console.log(div);
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
</style>