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
              max-height="800px"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 0;"
            >
              <v-btn class="ma-1" outlined color="indigo" @click="registAll()">모두 등록<v-icon>mdi-arrow-left</v-icon></v-btn>
              <v-btn class="ma-3" outlined color="red" @click="deleteRouteInfo()">경로선 지우기</v-btn>
              <v-btn class="ma-1" outlined color="indigo" @click="unregistAll()">모두 제거<v-icon>mdi-arrow-right</v-icon></v-btn>
              
              <div id="map" class="" style="width: 100%; height: 650px;"></div>
            </v-sheet>
          </v-col>

          <!-- Center tour pick position -->
          <v-col cols=3 >
            <v-sheet
              min-height="70vh"
              max-height="800px"  
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
              <v-btn class="ma-1" outlined color="indigo" @click="optimalButtonClicked()">최적경로 보기</v-btn>
              <v-btn class="ma-1" outlined color="indigo">공유!</v-btn>
              <v-btn class="ma-1" outlined color="indigo" @click="showRoute()">현재경로 보기</v-btn>
              <v-container fluid style="min-height: 500px;">
                <v-row dense class="card-list" id="card-list-custom" style="min-height: 300px; min-width: 200px;">

                </v-row>
              </v-container>
            </v-sheet>
          </v-col>

          <!-- Right tour just pick position -->
          <v-col cols=3 >
            <v-sheet
              min-height="70vh"
              max-height="800px"
              rounded="lg"
              elevation="8"
              class="grey lighten-5 overflow-auto"
              style="padding: 10px;"
            >
            
            <v-btn class="ma-1" outlined color="indigo" @click="viewSmall()">Size<v-icon>mdi-arrow-down</v-icon></v-btn>          
            <v-btn class="ma-1" outlined color="indigo" @click="viewBig()">Size<v-icon>mdi-arrow-up</v-icon></v-btn>
            <v-text-field label="검색" v-model="liveKeyword"></v-text-field>

              <v-container fluid style="min-height: 500px;">
                <v-row dense class="card-list" id="card-list" style="min-height: 300px; min-width: 200px;">
                  <v-col
                    v-for="(card, index) in displayedCards"
                    :key="card.title"
                    :cols="card.flex"
                    :id="card.id"
                  >
                    <v-card>
                    <v-img
                        :src="card.src"
                        class="white--text align-end card-image"
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
                            <v-btn icon @click="deleteOpenDialog(card.id)">
                            <v-icon :color="card.like ? 'red' : '' ">mdi-heart</v-icon>
                            </v-btn>
                        </v-row>
                        </v-col>
                        <v-spacer style="font-size: small;">{{ card.addr_1 }}</v-spacer>
                        <div style="display: none;" class="latitude">{{ card.latitude }}</div>
                        <div style="display: none;" class="longitude">{{ card.longitude }}</div>
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
                        <v-card-text>{{ card.overview }}</v-card-text>
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
    <delete-tour-dialog ref="deleteOverlay" @agreed="deleteAgree=true" ></delete-tour-dialog>
  </v-app>
</template>

<script>
import http from "@/axios/http";
import Sortable from 'sortablejs';
import DeleteTourDialog from '@/components/tour/DeleteTourDialog.vue';
import {mapState} from "vuex";
const memberStore = "memberStore";

export default {
  name: 'TourSearchInfo',
  components: {
    DeleteTourDialog
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
      clickedOverlay: null,
      liveKeyword: '',
      deleteAgree: false,
      /*Display Route Variables*/
      latList : [],
      lngList : [],
      titleClass : [document.getElementsByClassName("card-title")],
      titleList: [],
      drawingFlag: false, // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
      moveLine: null, // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
      clickLine: null, // 마우스로 클릭한 좌표로 그려질 선 객체입니다
      distanceOverlay: null, // 선의 거리정보를 표시할 커스텀오버레이 입니다
      dots: [], // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.

      graph: [],
      visited: [],
      arr: [],
      anslist: [],
      answer: 99999999,
      /*Display Route Variables End*/

      /* Switching div Element Variables */
      customCards: [],
      customCardsShort: [],
      /* Switching div Element Variables End */
    };
  },
  created(){},
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
      this.makeList();
    };
    script.async = true;
    script.type = 'text/javascript';
    script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=1b7a0eb6294cdd4b1f985683a25bd972";
    document.head.appendChild(script);

  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
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
  watch:{
    deleteAgree: {
        handler(val) {
        if (val) {
            this. tripDelete(this.selectedId); // 예시로 this.id를 매개변수로 사용
            this.deleteAgree = false;
        }
        },
        immediate: false
    },
  },
  methods: {
    registAll() {
      this.customCards = document.querySelectorAll("#card-list > div");
      this.customCardsShort = [];
      for (let i = 0; i < this.customCards.length; i++){
        
        this.customCardsShort[i] = this.customCards[i];
        this.customCardsShort[i].classList.remove('fade-in');
        this.customCardsShort[i].classList.remove('show');
      }
      for (let i = 0; i < this.customCards.length; i++) {
        this.customCards[i].remove();
      }
      // customCardsShorts에 있는 요소들을 순회하면서 HTML에 추가
      const container = document.querySelector('#card-list-custom');

      for (let i = 0; i < this.customCardsShort.length; i++) {
        const card = this.customCardsShort[i];
        card.classList.add('fade-in');
        container.appendChild(card);

        setTimeout(() => {
          card.classList.add('show');
        }, i * 100); // 요소의 인덱스에 따라 시간 간격 설정
      }
    },
      unregistAll() {
      this.customCards = document.querySelectorAll("#card-list-custom > div");
      this.customCardsShort = [];
      for (let i = 0; i < this.customCards.length; i++){
        
        this.customCardsShort[i] = this.customCards[i];
        this.customCardsShort[i].classList.remove('fade-in');
        this.customCardsShort[i].classList.remove('show');
      }
      for (let i = 0; i < this.customCards.length; i++) {
        this.customCards[i].remove();
      }
      // customCardsShorts에 있는 요소들을 순회하면서 HTML에 추가
      const container = document.querySelector('#card-list');

      for (let i = 0; i < this.customCardsShort.length; i++) {
        const card = this.customCardsShort[i];
        card.classList.add('fade-in');
        container.appendChild(card);

        setTimeout(() => {
          card.classList.add('show');
        }, i * 100); // 요소의 인덱스에 따라 시간 간격 설정
      }
    },
    showRoute() {
      this.latList = [];
      this.lngList = [];
      let latNode = [];
      let lngNode = [];

      latNode = document.querySelectorAll("#card-list-custom .latitude");
      lngNode = document.querySelectorAll("#card-list-custom .longitude");
      this.latList = Array.from(latNode);
      this.lngList = Array.from(lngNode);

      let length = this.latList.length;
      for (let i = 0; i < length; i++) {
        let qa1 = new window.kakao.maps.LatLng(this.latList[i].innerHTML, this.lngList[i].innerHTML);
        
        if (!this.drawingFlag) {
          this.drawingFlag = true;
          this.deleteClickLine();
          this.deleteDistnce();
          this.deleteCircleDot();

          this.clickLine = new window.kakao.maps.Polyline({
            map: this.map,
            path: [qa1],
            strokeWeight: 3,
            strokeColor: '#db4040',
            strokeOpacity: 1,
            strokeStyle: 'solid'
          });
          this.moveLine = new window.kakao.maps.Polyline({
            strokeWeight: 3,
            strokeColor: '#db4040',
            strokeOpacity: 0.5,
            strokeStyle: 'solid'
          });

          this.displayCircleDot(qa1, 0);

        } else {
          let path = this.clickLine.getPath();
          path.push(qa1);
          this.clickLine.setPath(path);
          let distance = Math.round(this.clickLine.getLength());
          this.displayCircleDot(qa1, distance);
        }
      }

      if (this.drawingFlag) {
        this.moveLine.setMap(null);
        this.moveLine = null;
        let path = this.clickLine.getPath();

        if (path.length > 1) {
          if (this.dots[this.dots.length-1].distance) {
            this.dots[this.dots.length-1].distance.setMap(null);
            this.dots[this.dots.length-1].distance = null;
          }

          var distance = Math.round(this.clickLine.getLength());
          var content = this.getTimeHTML(distance);
          this.showDistance(content, path[path.length-1]);
        } else {
          this.deleteClickLine();
          this.deleteCircleDot();
          this.deleteDistnce();
        }

        this.drawingFlag = false;
        
      }
      //this.map.setCenter(new window.kakao.maps.LatLng(this.latList[0].innerHTML, this.lngList[0].innerHTML));
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
    tripDelete(id) {
      http.delete(`/mypage/delete/${this.userInfo.data.memberId}/${id}`)
        .then(() => {
          return this.makeList();
        })
        .catch((error) => {
          console.error(error);
      });
    },
    async deleteOpenDialog(id){
      this.$refs.deleteOverlay.openDialog();
      this.selectedId = id;
    },
    makeList() {
      //======================================
        // 바꿈!!
      //======================================
      http.get(`/mypage/list/${this.userInfo.data.memberId}`)
        .then((response) => {
          this.positions.length = 0;
          this.cards.length = 0;
        // console.log(response.data);
          response.data.forEach((area) => {
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
              flex: 6,
              show: false,
              like: true
            }
            this.positions.push(markerInfo);
            this.cards.push(card);
          });
        })
        .then(() => { 
          this.removeMarker();
          this.displayMarker();
          this.latList = Array.from(document.getElementsByClassName("latitude"));
          this.lngList = Array.from(document.getElementsByClassName("longitude"));
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
    },
    deleteClickLine() {
      if (this.clickLine) {
        this.clickLine.setMap(null);    
        this.clickLine = null;        
      }
    },
    showDistance(content, position) {
      if (this.distanceOverlay) { // 커스텀오버레이가 생성된 상태이면

      // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
      this.distanceOverlay.setPosition(position);
      this.distanceOverlay.setContent(content);

      } else { // 커스텀 오버레이가 생성되지 않은 상태이면
      // 커스텀 오버레이를 생성하고 지도에 표시합니다
        this.distanceOverlay = new window.kakao.maps.CustomOverlay({
          map: this.map, // 커스텀오버레이를 표시할 지도입니다
          content: content,  // 커스텀오버레이에 표시할 내용입니다
          position: position, // 커스텀오버레이를 표시할 위치입니다.
          xAnchor: 0,
          yAnchor: 0,
          zIndex: 3  
        });       
      }
    },
    deleteDistnce () {
      if (this.distanceOverlay) {
        this.distanceOverlay.setMap(null);
        this.distanceOverlay = null;
      }
    },
    displayCircleDot(position, distance) {
    // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
      var circleOverlay = new window.kakao.maps.CustomOverlay({
          content: '<span class="dot"></span>',
          position: position,
          zIndex: 1
      });
      // 지도에 표시합니다
      circleOverlay.setMap(this.map);

      if (distance > 0) {
        // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
        var distanceOverlay = new window.kakao.maps.CustomOverlay({
          content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
          position: position,
          yAnchor: 1,
          zIndex: 2
        });
        // 지도에 표시합니다
        distanceOverlay.setMap(this.map);
      }
      // 배열에 추가합니다
      this.dots.push({circle:circleOverlay, distance: distanceOverlay});
    },
    deleteCircleDot() {
      var i;
      for ( i = 0; i < this.dots.length; i++ ){
          if (this.dots[i].circle) { 
              this.dots[i].circle.setMap(null);
          }

          if (this.dots[i].distance) {
              this.dots[i].distance.setMap(null);
          }
      }
      this.dots = [];
    },
    getTimeHTML(distance) {

      // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다
      var walkkTime = distance / 67 | 0;
      var walkHour = '', walkMin = '';

      // 계산한 도보 시간이 60분 보다 크면 시간으로 표시합니다
      if (walkkTime > 60) {
          walkHour = '<span class="number">' + Math.floor(walkkTime / 60) + '</span>시간 '
      }
      walkMin = '<span class="number">' + walkkTime % 60 + '</span>분'

      // 자전거의 평균 시속은 16km/h 이고 이것을 기준으로 자전거의 분속은 267m/min입니다
      var bycicleTime = distance / 227 | 0;
      var bycicleHour = '', bycicleMin = '';

      // 계산한 자전거 시간이 60분 보다 크면 시간으로 표출합니다
      if (bycicleTime > 60) {
          bycicleHour = '<span class="number">' + Math.floor(bycicleTime / 60) + '</span>시간 '
      }
      bycicleMin = '<span class="number">' + bycicleTime % 60 + '</span>분'

      // 거리와 도보 시간, 자전거 시간을 가지고 HTML Content를 만들어 리턴합니다
      var content = '<ul class="dotOverlay distanceInfo">';
      content += '    <li>';
      content += '        <span class="label">총거리</span><span class="number">' + distance + '</span>m';
      content += '    </li>';
      content += '    <li>';
      content += '        <span class="label">도보</span>' + walkHour + walkMin;
      content += '    </li>';
      content += '    <li>';
      content += '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
      content += '    </li>';
      content += '</ul>'

      return content;
    },
    deleteRouteInfo() {
      this.deleteCircleDot();
      this.deleteDistnce();
      this.deleteClickLine();
    },
    distanceInKilometerByHaversine(x1, y1, x2, y2) {
      var distance;
      var radius = 6371; // 지구 반지름(km)
      var toRadian = Math.PI / 180;

      var deltaLatitude = Math.abs(x1 - x2) * toRadian;
      var deltaLongitude = Math.abs(y1 - y2) * toRadian;

      var sinDeltaLat = Math.sin(deltaLatitude / 2);
      var sinDeltaLng = Math.sin(deltaLongitude / 2);
      var squareRoot = Math.sqrt(
          sinDeltaLat * sinDeltaLat +
          Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

      distance = 2 * radius * Math.asin(squareRoot);

      return distance;
    },
    dfs(origin, start, sum, depth, length) {
      if (depth === length) {
        if (sum < this.answer) {
          this.answer = sum;
          for (let i = 0; i < this.arr.length; i++) {
            this.anslist[i] = this.arr[i];
          }
        }
        return;
      }

      for (let i = 0; i < length; i++) {
        if (this.visited[i] || this.graph[start][i] == 0) continue;
        this.visited[i] = true;
        this.arr[depth] = i;
        this.dfs(origin, i, sum + this.graph[start][i], depth + 1, length);
        this.visited[i] = false;
      }
    },
    optimalButtonClicked() {
      this.latList = [];
      this.lngList = [];
      let latNode = [];
      let lngNode = [];

      latNode = document.querySelectorAll("#card-list-custom .latitude");
      lngNode = document.querySelectorAll("#card-list-custom .longitude");
      this.latList = Array.from(latNode);
      this.lngList = Array.from(lngNode);

      if (this.latList.length >= 9) {
        alert("9개 이하 여행지만 기능이 제공됩니다. ");
        return;
      }

      this.visited = [];
      this.visited = new Array(this.latList.length).fill(false);

      this.graph = [];
      for (let i = 0; i < this.latList.length; i++) {
        let cells = [];
        for (let j = 0; j < this.lngList.length; j++) {
          cells[j] = this.distanceInKilometerByHaversine(
            this.latList[i].innerHTML,
            this.lngList[i].innerHTML,
            this.latList[j].innerHTML,
            this.lngList[j].innerHTML
          );
        }
        this.graph.push(cells);
      }

      this.visited.fill(false);
      this.arr = [];
      this.anslist = [];
      this.answer = 9999999;
      let length = this.latList.length;

      for (let i = 0; i < length; i++) {
        this.visited[i] = true;
        this.arr[0] = i;
        this.dfs(i, i, 0, 1, length);
        this.visited[i] = false;
      }

      for (let i = 0; i < length; i++) {
        let qa1 = new window.kakao.maps.LatLng(this.latList[this.anslist[i]].innerHTML, this.lngList[this.anslist[i]].innerHTML);
        
        if (!this.drawingFlag) {
          this.drawingFlag = true;
          this.deleteClickLine();
          this.deleteDistnce();
          this.deleteCircleDot();

          this.clickLine = new window.kakao.maps.Polyline({
            map: this.map,
            path: [qa1],
            strokeWeight: 3,
            strokeColor: '#db4040',
            strokeOpacity: 1,
            strokeStyle: 'solid'
          });
          this.moveLine = new window.kakao.maps.Polyline({
            strokeWeight: 3,
            strokeColor: '#db4040',
            strokeOpacity: 0.5,
            strokeStyle: 'solid'
          });

          this.displayCircleDot(qa1, 0);

        } else {
          let path = this.clickLine.getPath();
          path.push(qa1);
          this.clickLine.setPath(path);
          let distance = Math.round(this.clickLine.getLength());
          this.displayCircleDot(qa1, distance);
        }
      }

      if (this.drawingFlag) {
        this.moveLine.setMap(null);
        this.moveLine = null;
        let path = this.clickLine.getPath();

        if (path.length > 1) {
          if (this.dots[this.dots.length-1].distance) {
            this.dots[this.dots.length-1].distance.setMap(null);
            this.dots[this.dots.length-1].distance = null;
          }

          var distance = Math.round(this.clickLine.getLength());
          var content = this.getTimeHTML(distance);
          this.showDistance(content, path[path.length-1]);
        } else {
          this.deleteClickLine();
          this.deleteCircleDot();
          this.deleteDistnce();
        }

        this.drawingFlag = false;
        
      }
      this.map.setCenter(new window.kakao.maps.LatLng(this.latList[this.anslist[0]].innerHTML, this.lngList[this.anslist[0]].innerHTML));

      this.customCards = document.querySelectorAll("#card-list-custom > div");
      this.customCardsShort = [];
      for (let i = 0; i < this.customCards.length; i++){
        
        this.customCardsShort[i] = this.customCards[this.anslist[i]];
        this.customCardsShort[i].classList.remove('fade-in');
        this.customCardsShort[i].classList.remove('show');
      }
      for (let i = 0; i < this.customCards.length; i++) {
        this.customCards[i].remove();
      }
      // customCardsShorts에 있는 요소들을 순회하면서 HTML에 추가
      const container = document.querySelector('#card-list-custom');

      for (let i = 0; i < this.customCardsShort.length; i++) {
        const card = this.customCardsShort[i];
        card.classList.add('fade-in');
        container.appendChild(card);

        setTimeout(() => {
          card.classList.add('show');
        }, i * 100); // 요소의 인덱스에 따라 시간 간격 설정
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
</style>

<style>
  .dot {overflow:hidden;float:left;width:12px;height:12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png');}    
  .dotOverlay {position:relative;bottom:10px;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;float:left;font-size:12px;padding:5px;background:#fff;}
  .dotOverlay:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}    
  .number {font-weight:bold;color:#ee6152;}
  .dotOverlay:after {content:'';position:absolute;margin-left:-6px;left:50%;bottom:-8px;width:11px;height:8px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png')}
  .distanceInfo {position:relative;top:5px;left:5px;list-style:none;margin:0;}
  .distanceInfo .label {display:inline-block;width:50px;}
  .distanceInfo:after {content:none;}
</style>

<style>
.fade-in {
  opacity: 0;
  transition: opacity 0.5s ease;
}

.fade-in.show {
  opacity: 1;
}
</style>