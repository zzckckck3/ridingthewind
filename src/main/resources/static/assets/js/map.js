// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

//일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

let areaUrl = "http://localhost/tour/sido";
fetch(areaUrl, { method: "POST" })
    .then((response) => response.json())
    .then((data) => makeOption(data));

function makeOption(data) {
    let areas = data;
    let sel = document.getElementById("search-area");
    areas.forEach((area) => {
        let opt = document.createElement("option");
        opt.setAttribute("value", area.sidoCode);
        opt.appendChild(document.createTextNode(area.sidoName));
        sel.appendChild(opt);
    });
}

// 시/도 선택 시 전국의 구 설정.
document.querySelector("#search-area").addEventListener("change", () => { 
    
    let select = document.querySelector("#search-area");
    let option = select.options[select.selectedIndex];
    let areaCode = option.value;

    let areaUrl = `http://localhost/tour/gugun?search-area=${areaCode}`; 

    fetch(areaUrl, { method: "GET" })
        .then((response) => response.json())
        .then((data) => makeOption2(data));

    function makeOption2(data) {
        let areas = data;
        
        let sel = document.getElementById("search-area-gu");
        if (sel.childElementCount > 1) {
            sel.replaceChildren();
        }
        areas.forEach((area) => {
            let opt = document.createElement("option");
            opt.setAttribute("value", area.gugunCode);
            opt.appendChild(document.createTextNode(area.gugunName));

            sel.appendChild(opt);
        });
    }
});

var contentType = [12, 14, 15, 25, 28, 32, 38, 39];
// 검색 버튼을 누르면..
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.querySelector("#btn-search").addEventListener("click", () => {
    let areaCode = document.getElementById("search-area").value;
    let gugunCode = document.getElementById("search-area-gu").value;
    let searchUrl = `http://localhost/tour/attraction-info?search-area=${areaCode}&search-area-gu=${gugunCode}`;

    fetch(searchUrl, { method: "POST" })
        .then((response) => response.json())
        .then((data) => makeList(data));
});

var positions; // marker 배열.
var overlay;
var contentTypeIds;
var mark_position;
function makeList(data) {
//    document.querySelector(".table").setAttribute("style", "display: ;");
    let trips = data;
    let tripList = ``;
    positions = [];
    contentTypeIds = [];
    trips.forEach((area) => {
		
        if(document.getElementById(`checkbox_${area.contentTypeId}`).checked){
            tripList += `
              <div class="card col" style="width: 18rem;">
			      <img src="${area.firstImage}" class="card-img-top" alt="areaImage" onerror=this.src="/assets/img/mark/noimg.png">
			      <div class="card-body">
			        <h5 class="card-title">${area.title}</h5>
			        <p class="card-text">${area.addr1} ${area.addr2}</p>
			        <a href="#" class="btn btn-primary" onclick="moveCenter(${area.latitude}, ${area.longitude});">위치 보기</a>
			        <div style="display: none" class="latitude">${area.latitude}</div>
			        <div style="display: none" class="longitude">${area.longitude}</div>
			        <button class="btn btn-secondary" id="trip-add-btn" onclick="addTour(${area.contentId})">여행지 추가</button>
			      </div>
			  </div>
            `;
        }

        let markerInfo = {
        	id: area.contentId,
            img: area.firstImage,
            title: area.title,
            addr_1: area.addr1,
            addr_2: area.addr2,
            zip: area.zipcode,
            tel: area.tel,
            latlng: new kakao.maps.LatLng(area.latitude, area.longitude),
        };
        positions.push(markerInfo);
        contentTypeIds.push(area.contentTypeId);
    });
    removeMarker();
    
    document.getElementById("trip-list").innerHTML = tripList;
    mark_position = [];
    displayMarker();
}


function addTour(contentId) {
  let root = ""
  fetch(`${root}/tour/addtour/${contentId}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      contentId: contentId
    })
  })
  .then(response => {
    if (response.ok) {
      alert("마이페이지에 여행지가 추가되었습니다.");
      return response.json();
    } else if (response.status == 500) {
    	alert("추가중 에러 발생. 이미 추가된 여행지입니다.")
    	throw new Error('Network response was not ok.');
    }
  })
}

//myLocationList 받아오기
areaUrl = "http://localhost/tour/myLocationList";
fetch(areaUrl, { method: "GET" })
	.then((response) => response.json())
	.then((data) => makeOption3(data));

var myLocationlist = [];
function makeOption3(data) {
    let areas = data.mylocation;
    areas.forEach((area) => {
		myLocationlist.push(area.attraction_id);
    });
}

var markers = [];
var clickedOverlay = null;
let temp_id;

function displayMarker() {
	console.log(myLocationlist);
    var imageSrc = "";
    let index = 0;
     // 마커 이미지
    for (var i = 0; i < positions.length; i++) {
        if(!document.getElementById(`checkbox_${contentTypeIds[i]}`).checked){
            continue;
        }
        mark_position.push(i);

        if (contentTypeIds[i] == 12) {
            imageSrc = "/assets/img/mark/mark1.png";
        }else if(contentTypeIds[i] == 14){
            imageSrc = "/assets/img/mark/mark2.png";
        }else if(contentTypeIds[i] == 15){
            imageSrc = "/assets/img/mark/mark3.png";
        }else if(contentTypeIds[i] == 25){
            imageSrc = "/assets/img/mark/mark4.png";
        }else if(contentTypeIds[i] == 28){
            imageSrc = "/assets/img/mark/mark5.png";
        }else if(contentTypeIds[i] == 32){
            imageSrc = "/assets/img/mark/mark6.png";
        }else if(contentTypeIds[i] == 38){
            imageSrc = "/assets/img/mark/mark7.png";
        }else if(contentTypeIds[i] == 39){
            imageSrc = "/assets/img/mark/mark8.png";
        }
        

        // 마커 이미지의 이미지 크기 입니다
        /*var imageSize = new kakao.maps.Size(100, 80);*/
		var imageSize = new kakao.maps.Size(100, 69); // 마커이미지의 크기입니다
	
        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        markers[index++] = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
        });
    }

    // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
    map.setCenter(positions[0].latlng);
    
    // 마커를 클릭하면 오버레이를 띄운다
    for (let i = 0; i < markers.length; i++){
		kakao.maps.event.addListener(markers[i], 'click', function () {
			let display = "";
			temp_id = positions[mark_position[i]].id;
			if(myLocationlist.includes(positions[mark_position[i]].id)){
				display = "none";
			}
			var content = '<div class="wrap">' + 
		    '    <div class="info">' + 
		    '        <div class="title">' + 
		    `           ${positions[mark_position[i]].title}` + 
		    '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' + 
		    '        </div>' + 
		    '        <div class="body">' + 
		    '            <div class="img">' +
		    `                <img src="${positions[mark_position[i]].img}" onerror="this.src='/assets/img/mark/noimg.png'" width="73" height="70">` +
		    '           </div>' + 
		    '            <div class="desc">' + 
		    `                <div class="ellipsis">${positions[mark_position[i]].addr_1}</div>` + 
		    `                <div class="jibun ellipsis">(우) ${positions[mark_position[i]].zip}</br>(전화번호) ${positions[mark_position[i]].tel}</div>` + 
		    `                <div><img src="/assets/img/mark/love.png" width="25" height="25" style="float:right; margin-bottom:12px; margin-right:5px;" onclick="addRoute()"></div>` +
		    '            </div>' + 
		    '        </div>' + 
		    '    </div>' +    
		    '</div>';
		
	    
		    overlay = new kakao.maps.CustomOverlay({
		        content: content,
		        map: map,
		        position: positions[mark_position[i]].latlng,   
		    });
		    
		    // 마커 클릭 시 부드럽게 가운데로 이동시키기 
		   	if(clickedOverlay){
				clickedOverlay.setMap(null);
			}
    		overlay.setMap(map);
    		clickedOverlay = overlay;
    		
    		map.panTo(overlay.getPosition());
		});
	}	
}

var slide_state = document.querySelector("#slide_all");
slide_state.addEventListener("click", () => {
	if(!slide_state.checked){
		for (var i = 0; i < 8; i++) {
	        if(document.getElementById(`checkbox_${contentType[i]}`).checked){
	        	document.getElementById(`checkbox_${contentType[i]}`).checked = false;
	        }
		}
	}else{
		for (var i = 0; i < 8; i++) {
	        if(!document.getElementById(`checkbox_${contentType[i]}`).checked){
	        	document.getElementById(`checkbox_${contentType[i]}`).checked = true;
	        }
		}
	}
	
});

function moveCenter(lat, lng) {
    map.setCenter(new kakao.maps.LatLng(lat, lng));
}

// 마크 제거하기
function removeMarker() {
    for ( var i = 0; i < markers.length; i++ ) {
        markers[i].setMap(null);
    }   
    markers = [];
}

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다 
function closeOverlay() {
    overlay.setMap(null);     
}

function getContextPath() {
    var hostIndex = location.href.indexOf( location.host ) + location.host.length;
    return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
};

function addRoute() {
	alert("저장되었습니다!!");
	let sendData = { "id": temp_id};

	const data = {
	  method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(sendData)
	};
	
	 fetch('http://localhost:8080/enjoyTrip/location?action=save', data)
    	.then((res) => res.text())
    	.then(console.log);
	
}


