<template>
<v-app id="inspire">

    <v-main class="white">
      <v-container class="text-center">
        <h1>여행계획</h1>
        <v-divider class="my-2"></v-divider>
        <v-row style="margin-top: 10px;">
          <!-- Lefy Map Position -->
          <v-col cols="6">
            <v-sheet
              min-height="70vh"
              max-height="100"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <div id="map" class="" style="width: 100%; height: 650px;"></div>
            </v-sheet>
          </v-col>

          <!-- Center tour pick position -->
          <v-col cols=3 >
            <v-sheet
              min-height="70vh"
              max-height="100"  
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <v-container fluid style="min-width: 100px;">
                <v-row dense class="card-list">
                  
                </v-row>
              </v-container>
            </v-sheet>
          </v-col>

          <!-- Right tour just pick position -->
          <v-col cols=3 >
            <v-sheet
              min-height="70vh"
              max-height="100"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <v-container fluid>
                <v-row dense class="card-list">
                  <v-col
                    v-for="card in cards"
                    :key="card.title"
                    :cols="card.flex"
                  >
                  
                    <v-card >
                      <v-img
                        :src="card.src"
                        class="white--text align-end"
                        gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                        height="200px"
                      >
                        <v-card-title v-text="card.title" style="font-size: medium;"></v-card-title>
                      </v-img>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <!-- 버튼 추가 할거면 여기 -->
                        <v-btn icon>
                          <v-icon>mdi-bookmark</v-icon>
                        </v-btn>
                        <v-btn
                          icon
                          @click="card.show = !card.show"
                        >
                          <v-icon>{{ card.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                        </v-btn>
                      </v-card-actions>
                      <v-expand-transition>
                        <div v-show="card.show">
                          <v-divider></v-divider>
                          <v-card-text v-text="card.text"></v-card-text>
                        </div>
                      </v-expand-transition>
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
import Sortable from 'sortablejs';
import http from "@/axios/http";

export default {

  data: () => ({
    name: 'PersonalTrip',
    components: {},
    map: null,
    selectedContentById: [],
    positions: [],
    markers: [],
    cards: [
      { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 6 },
        { title: 'Favorite road trips', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', flex: 6 },
        { title: 'Best airlines', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 6 },
    ],
    overlay: [],
    clickedOverlay: null
  }),
  created() {
        this.create_sido();
    },
  mounted() {
    const columns = document.querySelectorAll(".card-list");

    columns.forEach((column) => {
      new Sortable(column, {
        group: "shared",
        animation: 150,
        ghostClass: "blue-background-class"
      });
    });
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

             // 마커를 클릭하면 오버레이를 띄운다
            for (let i = 0; i < this.markers.length; i++){
                window.kakao.maps.event.addListener(this.markers[i], 'click', () => {
                    // let display = "";
                    // 좋아요 저장할 때 쓸 꺼
                    // temp_id = this.positions[i].id;
                    // if(myLocationlist.includes(this.positions[i].id)){
                    //     display = "none";
                    // }
                    var content = '<v-hover>' + 
                    '      <v-card color="grey lighten-4" flat height="200px" tile>' + 
                    `           ${this.positions[i].title}` + 
                    '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
                    '        </v-card>' + 
                    '        <v-col class="body">' + 
                    '            <div class="img">' +
                    `                <img src="${this.positions[i].img}" onerror="this.src='/assets/img/mark/noimg.png'" width="73" height="70">` +
                    '           </div>' + 
                    '            <div class="desc">' + 
                    `                <div class="ellipsis">${this.positions[i].addr_1}</div>` + 
                    `                <div class="jibun ellipsis">(우) ${this.positions[i].zip}</br>(전화번호) ${this.positions[i].tel}</div>` + 
                    '            </div>' + 
                    '        </v-col>' +   
                    '</v-hover>';
                
                
                    this.overlay = new window.kakao.maps.CustomOverlay({
                        content: content,
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
        removeMarker() {
            for ( var i = 0; i < this.markers.length; i++ ) {
                this.markers[i].setMap(null);
            }   
            this.markers = [];
        }
        

    },
};
</script>

