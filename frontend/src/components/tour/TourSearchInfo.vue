<template>
    <v-app id="">
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

        <div>
            {{ selectedContentById }}
        </div>
    </v-app>


</template>



<script>
import http from "@/axios/http";

export default {
    name: 'TourSearchInfo',
    components: {},
    data() {
        return {
            map: null,
            sido: [],
            gugun: [],
            selectedSido: null,
            selectedGugun: null,
            selectedContentById: [],
            positions: [],
            contentByType: [{id: 12, value: "관광지"},
                            { id: 14, value: "문화시설" },
                            { id: 15, value: "축제공연행사" },
                            { id: 25, value: "여행코스" },
                            { id: 28, value: "레포츠" },
                            { id: 32, value: "숙박" },
                            { id: 38, value: "쇼핑" },
                            { id: 39, value: "음식점" }]
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
        // marklist() {
        //     http.get(`/tour/attraction-info?search-area=${this.selectedSido}&search-area-gu=${this.selectedGugun}`)
        //         .then((response) => {
        //             let mark
        //         });
        // },
        // displayMarker() {
        //     var imageSrc = "";
        //     let index = 0;
        //     // 마커 이미지
        //     for (var i = 0; i < positions.length; i++) {
        //         if(selectedContentById.includes){
        //             continue;
        //         }
        //         mark_position.push(i);

        //         if (contentTypeIds[i] == 12) {
        //             imageSrc = "/assets/img/mark/mark1.png";
        //         }else if(contentTypeIds[i] == 14){
        //             imageSrc = "/assets/img/mark/mark2.png";
        //         }else if(contentTypeIds[i] == 15){
        //             imageSrc = "/assets/img/mark/mark3.png";
        //         }else if(contentTypeIds[i] == 25){
        //             imageSrc = "/assets/img/mark/mark4.png";
        //         }else if(contentTypeIds[i] == 28){
        //             imageSrc = "/assets/img/mark/mark5.png";
        //         }else if(contentTypeIds[i] == 32){
        //             imageSrc = "/assets/img/mark/mark6.png";
        //         }else if(contentTypeIds[i] == 38){
        //             imageSrc = "/assets/img/mark/mark7.png";
        //         }else if(contentTypeIds[i] == 39){
        //             imageSrc = "/assets/img/mark/mark8.png";
        //         }
        

        //         // 마커 이미지의 이미지 크기 입니다
        //         /*var imageSize = new kakao.maps.Size(100, 80);*/
        //         var imageSize = new kakao.maps.Size(100, 69); // 마커이미지의 크기입니다
            
        //         // 마커 이미지를 생성합니다
        //         var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        //         // 마커를 생성합니다
        //         markers[index++] = new kakao.maps.Marker({
        //             map: map, // 마커를 표시할 지도
        //             position: positions[i].latlng, // 마커를 표시할 위치
        //             title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        //             image: markerImage, // 마커 이미지
        //         });
        //     }
        // }
        

    },
};
</script>


<style scoped></style>