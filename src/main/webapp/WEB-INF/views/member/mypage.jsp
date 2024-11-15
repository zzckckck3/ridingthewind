<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ringdingdong.ridingthewind.model.PersonalTripDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${root}/assets/css/mypage.css">
  <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.14.0/Sortable.min.js"></script>
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
  <title>Enjoy Trip</title>
</head>


<body>
  <div class='container text-center'>
    <!--Header -->
	<%@ include file="../common/header.jsp" %>
    <!--End of Header-->
    <% String memberid = memberInfo.getMemberId(); %>
    <hr>
    <h4>마이페이지</h4>
    <hr>
    <div class='container text-center'>
      <div class='row'>
        <div class='col-md-3' id='left-container'>
          <div class="text-center mt-3" id='profile'>
            <img src="${root}/assets/img/noimg.jpg" class="img-thumbnail" alt="프로필"/>
          </div>
          
          <div class="list-group">
            <a href="${root}/mypage/edit" class="list-group-item list-group-item-action">개인정보 수정</a>
            <a href="${root}/mypage/withdrawal" class="list-group-item list-group-item-action">회원 탈퇴</a>
            <a href="#" class="list-group-item list-group-item-action">핫플게시판</a>
          </div>
         
          
        </div>
        <div class='col-md-9'>
          <div id="map" style="width:100%;height:550px;"></div>
          
          <hr>
          	<div class="btn btn-primary" id="btn-optimal">
          		<h5 style="margin-bottom: 0px">최적 여행경로 설정</h5>
          	</div>
          	<div>
          		<span style="font-size: 0.5em;">카드를 드래그 해 보세요</span>
          	</div>
          <hr>
          
          <div class='container row row-cols-1 row-cols-sm-2 row-cols-md-4 card-list' id='card-list-custom' style="min-height: 50px">
            <!-- Draggable! -->
          </div>


          <div draggable="true" class='container row row-cols-1 row-cols-sm-2 row-cols-md-4 card-list' id='card-list' style='margin-left: 6px;'>

          </div>
        </div>
      </div>
      
    </div>

    <!--Footer-->
    <footer class="py-3 my-4">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">개인정보 처리방침</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">이용 약관</a></li>
        <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">오시는 길</a></li>
      </ul>
      <p class="text-center text-muted">&copy; SSAFY</p>
    </footer>
    <!--End of Footer-->
  </div>

  <script type="text/javascript" src="${root}/assets/js/mypage.js"></script>
  <script type="text/javascript" 
    src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5de6a7a2af636451cce596c34b311543"
  ></script>
  <script>
  	var positions; // marker 배열.
  	var latClass = document.getElementsByClassName("latitude");
  	var lngClass = document.getElementsByClassName("longitude");
  	var titleClass = document.getElementsByClassName("card-title");
	
  	var latList = [];
  	var lngList = [];
  	var titleList = [];
  
	positions = [];
    window.onload = function () {
        for (var i = 0; i < latClass.length; i++) {
        	latList[i] = latClass.item(i);
        	lngList[i] = lngClass.item(i);
			titleList[i] = titleClass.item(i);
			
        	let markerInfo = {
				title: titleList[i].innerHTML,
				latlng: new kakao.maps.LatLng(latList[i].innerHTML, lngList[i].innerHTML),
			};
			positions.push(markerInfo);
		}
        displayMarker();
        if(latList[0] != ""){
        	moveCenter(latList[0].innerHTML, lngList[0].innerHTML);	
        }
        
    }
	
	var container = document.getElementById('map');
	var options = {
		center: new kakao.maps.LatLng(37.500613, 127.036431),
		level: 5,
	};

	var map = new kakao.maps.Map(container, options);
	
    function displayMarker() {
        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i++) {
          // 마커 이미지의 이미지 크기 입니다
          var imageSize = new kakao.maps.Size(24, 35);

          // 마커 이미지를 생성합니다
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
          });
        }
      }
		
	function moveCenter(lat, lng) {
	  map.setCenter(new kakao.maps.LatLng(lat, lng));
	}
	
	var drawingFlag = false; // 선이 그려지고 있는 상태를 가지고 있을 변수입니다
	var moveLine; // 선이 그려지고 있을때 마우스 움직임에 따라 그려질 선 객체 입니다
	var clickLine; // 마우스로 클릭한 좌표로 그려질 선 객체입니다
	var distanceOverlay; // 선의 거리정보를 표시할 커스텀오버레이 입니다
	var dots = {}; // 선이 그려지고 있을때 클릭할 때마다 클릭 지점과 거리를 표시하는 커스텀 오버레이 배열입니다.

	// 지도에 클릭 이벤트를 등록합니다
	// 지도를 클릭하면 선 그리기가 시작됩니다 그려진 선이 있으면 지우고 다시 그립니다
	kakao.maps.event.addListener(map, 'click', function(mouseEvent) {

	    // 마우스로 클릭한 위치입니다 
	    var clickPosition = mouseEvent.latLng;

	    // 지도 클릭이벤트가 발생했는데 선을 그리고있는 상태가 아니면
	    if (!drawingFlag) {

	        // 상태를 true로, 선이 그리고있는 상태로 변경합니다
	        drawingFlag = true;
	        
	        // 지도 위에 선이 표시되고 있다면 지도에서 제거합니다
	        deleteClickLine();
	        
	        // 지도 위에 커스텀오버레이가 표시되고 있다면 지도에서 제거합니다
	        deleteDistnce();

	        // 지도 위에 선을 그리기 위해 클릭한 지점과 해당 지점의 거리정보가 표시되고 있다면 지도에서 제거합니다
	        deleteCircleDot();
	    
	        // 클릭한 위치를 기준으로 선을 생성하고 지도위에 표시합니다
	        clickLine = new kakao.maps.Polyline({
	            map: map, // 선을 표시할 지도입니다 
	            path: [clickPosition], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
	            strokeWeight: 3, // 선의 두께입니다 
	            strokeColor: '#db4040', // 선의 색깔입니다
	            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	            strokeStyle: 'solid' // 선의 스타일입니다
	        });
	        
	        // 선이 그려지고 있을 때 마우스 움직임에 따라 선이 그려질 위치를 표시할 선을 생성합니다
	        moveLine = new kakao.maps.Polyline({
	            strokeWeight: 3, // 선의 두께입니다 
	            strokeColor: '#db4040', // 선의 색깔입니다
	            strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
	            strokeStyle: 'solid' // 선의 스타일입니다    
	        });
	    
	        // 클릭한 지점에 대한 정보를 지도에 표시합니다
	        displayCircleDot(clickPosition, 0);

	            
	    } else { // 선이 그려지고 있는 상태이면

	        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();

	        // 좌표 배열에 클릭한 위치를 추가합니다
	        path.push(clickPosition);
	        
	        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
	        clickLine.setPath(path);

	        var distance = Math.round(clickLine.getLength());
	        displayCircleDot(clickPosition, distance);
	    }
	});
	    
	// 지도에 마우스무브 이벤트를 등록합니다
	// 선을 그리고있는 상태에서 마우스무브 이벤트가 발생하면 그려질 선의 위치를 동적으로 보여주도록 합니다
	kakao.maps.event.addListener(map, 'mousemove', function (mouseEvent) {

	    // 지도 마우스무브 이벤트가 발생했는데 선을 그리고있는 상태이면
	    if (drawingFlag){
	        
	        // 마우스 커서의 현재 위치를 얻어옵니다 
	        var mousePosition = mouseEvent.latLng; 

	        // 마우스 클릭으로 그려진 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	        
	        // 마우스 클릭으로 그려진 마지막 좌표와 마우스 커서 위치의 좌표로 선을 표시합니다
	        var movepath = [path[path.length-1], mousePosition];
	        moveLine.setPath(movepath);    
	        moveLine.setMap(map);
	        
	        var distance = Math.round(clickLine.getLength() + moveLine.getLength()), // 선의 총 거리를 계산합니다
	            content = '<div class="dotOverlay distanceInfo">총거리 <span class="number">' + distance + '</span>m</div>'; // 커스텀오버레이에 추가될 내용입니다
	        
	        // 거리정보를 지도에 표시합니다
	        showDistance(content, mousePosition);   
	    }             
	});                 

	// 지도에 마우스 오른쪽 클릭 이벤트를 등록합니다
	// 선을 그리고있는 상태에서 마우스 오른쪽 클릭 이벤트가 발생하면 선 그리기를 종료합니다
	kakao.maps.event.addListener(map, 'rightclick', function (mouseEvent) {

	    // 지도 오른쪽 클릭 이벤트가 발생했는데 선을 그리고있는 상태이면
	    if (drawingFlag) {
	        
	        // 마우스무브로 그려진 선은 지도에서 제거합니다
	        moveLine.setMap(null);
	        moveLine = null;  
	        
	        // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	    
	        // 선을 구성하는 좌표의 개수가 2개 이상이면
	        if (path.length > 1) {

	            // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
	            if (dots[dots.length-1].distance) {
	                dots[dots.length-1].distance.setMap(null);
	                dots[dots.length-1].distance = null;    
	            }

	            var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
	                content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
	                
	            // 그려진 선의 거리정보를 지도에 표시합니다
	            showDistance(content, path[path.length-1]);  
	             
	        } else {

	            // 선을 구성하는 좌표의 개수가 1개 이하이면 
	            // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
	            deleteClickLine();
	            deleteCircleDot(); 
	            deleteDistnce();

	        }
	        
	        // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
	        drawingFlag = false;          
	    }  
	});    

	// 클릭으로 그려진 선을 지도에서 제거하는 함수입니다
	function deleteClickLine() {
	    if (clickLine) {
	        clickLine.setMap(null);    
	        clickLine = null;        
	    }
	}

	// 마우스 드래그로 그려지고 있는 선의 총거리 정보를 표시하거
	// 마우스 오른쪽 클릭으로 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 생성하고 지도에 표시하는 함수입니다
	function showDistance(content, position) {
	    
	    if (distanceOverlay) { // 커스텀오버레이가 생성된 상태이면
	        
	        // 커스텀 오버레이의 위치와 표시할 내용을 설정합니다
	        distanceOverlay.setPosition(position);
	        distanceOverlay.setContent(content);
	        
	    } else { // 커스텀 오버레이가 생성되지 않은 상태이면
	        
	        // 커스텀 오버레이를 생성하고 지도에 표시합니다
	        distanceOverlay = new kakao.maps.CustomOverlay({
	            map: map, // 커스텀오버레이를 표시할 지도입니다
	            content: content,  // 커스텀오버레이에 표시할 내용입니다
	            position: position, // 커스텀오버레이를 표시할 위치입니다.
	            xAnchor: 0,
	            yAnchor: 0,
	            zIndex: 3  
	        });      
	    }
	}

	// 그려지고 있는 선의 총거리 정보와 
	// 선 그리가 종료됐을 때 선의 정보를 표시하는 커스텀 오버레이를 삭제하는 함수입니다
	function deleteDistnce () {
	    if (distanceOverlay) {
	        distanceOverlay.setMap(null);
	        distanceOverlay = null;
	    }
	}

	// 선이 그려지고 있는 상태일 때 지도를 클릭하면 호출하여 
	// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 표출하는 함수입니다
	function displayCircleDot(position, distance) {

	    // 클릭 지점을 표시할 빨간 동그라미 커스텀오버레이를 생성합니다
	    var circleOverlay = new kakao.maps.CustomOverlay({
	        content: '<span class="dot"></span>',
	        position: position,
	        zIndex: 1
	    });

	    // 지도에 표시합니다
	    circleOverlay.setMap(map);

	    if (distance > 0) {
	        // 클릭한 지점까지의 그려진 선의 총 거리를 표시할 커스텀 오버레이를 생성합니다
	        var distanceOverlay = new kakao.maps.CustomOverlay({
	            content: '<div class="dotOverlay">거리 <span class="number">' + distance + '</span>m</div>',
	            position: position,
	            yAnchor: 1,
	            zIndex: 2
	        });

	        // 지도에 표시합니다
	        distanceOverlay.setMap(map);
	    }

	    // 배열에 추가합니다
	    dots.push({circle:circleOverlay, distance: distanceOverlay});
	}

	// 클릭 지점에 대한 정보 (동그라미와 클릭 지점까지의 총거리)를 지도에서 모두 제거하는 함수입니다
	function deleteCircleDot() {
	    var i;

	    for ( i = 0; i < dots.length; i++ ){
	        if (dots[i].circle) { 
	            dots[i].circle.setMap(null);
	        }

	        if (dots[i].distance) {
	            dots[i].distance.setMap(null);
	        }
	    }

	    dots = [];
	}

	// 마우스 우클릭 하여 선 그리기가 종료됐을 때 호출하여 
	// 그려진 선의 총거리 정보와 거리에 대한 도보, 자전거 시간을 계산하여
	// HTML Content를 만들어 리턴하는 함수입니다
	function getTimeHTML(distance) {

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
	}
	
	var graph = [];
    var visited = new Array(latList.length).fill(false);
    var arr = []; var anslist = [];
	var answer = 999999999;
    
	document.getElementById("btn-optimal").addEventListener("click", function() {
		graph = [];
	    latNode = []; lngNode = [];
	    latNode = document.querySelectorAll("#card-list-custom .latitude");
	    lngNode = document.querySelectorAll("#card-list-custom .longitude");

	    latList = Array.from(latNode); lngList = Array.from(lngNode);
	    //거리계산 출력
	    //console.log(distanceInKilometerByHaversine(latList[0].innerHTML, lngList[0].innerHTML, latList[0].innerHTML, lngList[0].innerHTML));
	    
	    for (var i = 0; i < latList.length; i++) {
			var cells = [];
			for (var j = 0; j < lngList.length; j++) {
				cells[j] = distanceInKilometerByHaversine(latList[i].innerHTML, lngList[i].innerHTML, latList[j].innerHTML, lngList[j].innerHTML)
			}
			graph.push(cells);
		}
	    
	    //=================================================================
	    visited.fill(false);
	    arr = []; anslist = [];
	    answer = 999999999;
	    var length = latList.length;
        
		for(var i = 0; i < length; i++) {
            visited[i] = true;
            arr[0] = i;
            dfs(i, i, 0, 1, length);
            visited[i] = false;
        }
	    //=================================================================
	    for (var i = 0; i < length; i++) {
			var qa1 = new kakao.maps.LatLng(latList[anslist[i]].innerHTML, lngList[anslist[i]].innerHTML)
			
		    if (!drawingFlag) {
		        drawingFlag = true;
		        deleteClickLine();
		        deleteDistnce();
		        deleteCircleDot();
		        
		        clickLine = new kakao.maps.Polyline({
		            map: map, // 선을 표시할 지도입니다 
		            path: [qa1], // 선을 구성하는 좌표 배열입니다 클릭한 위치를 넣어줍니다
		            strokeWeight: 3, // 선의 두께입니다 
		            strokeColor: '#db4040', // 선의 색깔입니다
		            strokeOpacity: 1, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
		            strokeStyle: 'solid' // 선의 스타일입니다
		        });
		        moveLine = new kakao.maps.Polyline({
		            strokeWeight: 3, // 선의 두께입니다 
		            strokeColor: '#db4040', // 선의 색깔입니다
		            strokeOpacity: 0.5, // 선의 불투명도입니다 0에서 1 사이값이며 0에 가까울수록 투명합니다
		            strokeStyle: 'solid' // 선의 스타일입니다    
		        });
		    
		        // 클릭한 지점에 대한 정보를 지도에 표시합니다
		        displayCircleDot(qa1, 0);

		            
		    } else { // 선이 그려지고 있는 상태이면

		        // 그려지고 있는 선의 좌표 배열을 얻어옵니다
		        var path = clickLine.getPath();

		        // 좌표 배열에 클릭한 위치를 추가합니다
		        path.push(qa1);
		        
		        // 다시 선에 좌표 배열을 설정하여 클릭 위치까지 선을 그리도록 설정합니다
		        clickLine.setPath(path);

		        var distance = Math.round(clickLine.getLength());
		        displayCircleDot(qa1, distance);
		    }
		}
	    
	    if (drawingFlag) {
	        
	        // 마우스무브로 그려진 선은 지도에서 제거합니다
	        moveLine.setMap(null);
	        moveLine = null;  
	        
	        // 마우스 클릭으로 그린 선의 좌표 배열을 얻어옵니다
	        var path = clickLine.getPath();
	    
	        // 선을 구성하는 좌표의 개수가 2개 이상이면
	        if (path.length > 1) {

	            // 마지막 클릭 지점에 대한 거리 정보 커스텀 오버레이를 지웁니다
	            if (dots[dots.length-1].distance) {
	                dots[dots.length-1].distance.setMap(null);
	                dots[dots.length-1].distance = null;    
	            }

	            var distance = Math.round(clickLine.getLength()), // 선의 총 거리를 계산합니다
	                content = getTimeHTML(distance); // 커스텀오버레이에 추가될 내용입니다
	                
	            // 그려진 선의 거리정보를 지도에 표시합니다
	            showDistance(content, path[path.length-1]);  
	             
	        } else {

	            // 선을 구성하는 좌표의 개수가 1개 이하이면 
	            // 지도에 표시되고 있는 선과 정보들을 지도에서 제거합니다.
	            deleteClickLine();
	            deleteCircleDot(); 
	            deleteDistnce();

	        }
	        
	        // 상태를 false로, 그리지 않고 있는 상태로 변경합니다
	        drawingFlag = false;          
	    }  
	  })
	  
	function dfs(origin, start, sum, depth, length) {
        if(depth == length) {
        	if (sum < answer) {
        		answer = sum;
        		for(var i = 0; i < arr.length; i++) {
        			anslist[i] = arr[i];
                }
        	}
            return;
        }
       
        for(var i = 0; i < length; i++) {
            if(visited[i] || graph[start][i]== 0) continue;
            visited[i] = true;
            arr[depth] = i;
            dfs(origin, i, sum+graph[start][i], depth+1, length);
            visited[i] = false;
        }
    }
	
	function distanceInKilometerByHaversine(x1, y1, x2, y2) {
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
	}
	
  </script>

</body>
</html>