<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${root}/assets/css/index.css">
  <link rel="stylesheet" href="${root}/assets/css/tour.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
  <title>Enjoy Trip</title>
</head>

<body>
  <div class='container'>
    <!--Header -->
	<%@ include file="../common/header.jsp" %>
    <!--End of Header-->
	
	
    <!--Main-->
    <div class='container text-center'>
      <hr>
      <h4>어디로 떠날까요?</h4>
      <hr> 
      
	  <!--====== SEARCH PART START ======-->
		<div class="search-area">
			<div class="container bg-body rounded shadow pt-4 pb-4">
				<div class="" >
					<form action="#" onsubmit="return false;" role="search">
						<div class="row ms-5">
							<div class="col-lg-3 col-sm-5 col-10">
								<div class="">
									<label for="location"><i class="lni lni-map-marker theme-color"></i></label> 
									<select class="select-box" name="search-area" id="search-area">
										<option value="0" selected disabled>시, 도</option>
									</select>
								</div>
							</div>
							<div class="col-lg-1"></div>
							<div class="col-lg-3 col-sm-5 col-10">
								<div class="">
									<label for="location"><i class=""></i></label> 
									<select class="select-box" name="search-area-gu" id="search-area-gu">
										<option value="0" selected disabled>군, 구</option>
									</select>
								</div>
							</div>
							<div class="col-lg-1"></div>
							<div class="col-lg-2 col-sm-5 col-10">
								<button class="rounded-pill px-3 btn-search mt-4" id='btn-search'>
									검색 
								</button>
							</div>
							
							<div class="col-lg-2 col-sm-5 col-10 p-0 text-center align-center">
								<div class="mt-2">
									<label class="switch">
									  <input class="m-0 p-0 d-flex justify-content-center" type="checkbox" id="slide_all">
									  <span class="slider round"></span> 
									</label>
								</div>
								<div class=""><h6>전체 선택</h6></div>
							</div>
						</div>
						
					</form>
					<div class="mt-5">
						<form class="row">
							<div class="col row d-flex justify-content-center"><input type='checkbox' style="width:30px; height:30px;" id="checkbox_12" value="12">
							<label class="row justify-content-center">관광지</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_14" value="14"> 
							<label class="row justify-content-center">문화시설</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_15" value="15"> 
							<label class="row justify-content-center">축제공연행사</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_25" value="25"> 
							<label class="row justify-content-center">여행코스</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_28" value="28"> 
							<label class="row justify-content-center">레포츠</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_32" value="32"> 
							<label class="row justify-content-center">숙박</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_38" value="38"> 
							<label class="row justify-content-center">쇼핑</label></div>
							<div class="col row d-flex justify-content-center"><input type='checkbox'  style="width:30px; height:30px;" id="checkbox_39" value="39"> 
							<label class="row justify-content-center">음식점</label></div>
						</form>
					</div>
		
				</div>
			</div>
		</div>
		<!--====== SEARCH PART END ======-->
		
       	<!--====== MAP START ======-->
		<div class="mt-3 me-3">
			<div id="map" class="" style="width: 100%; height: 700px;"></div>
		</div>
		<!--====== MAP END ======-->
		
		
     	<!-- View! -->
     	<div class="row row-cols-4 justify-content-center" id='trip-list'>
     	</div> 
		<!-- View! end --> 

    </div>
    <!--End of Main-->

    <!--Footer-->
    <%@ include file="../common/footer.jsp" %>
    <!--End of Footer-->
    
  </div>


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
    crossorigin="anonymous"></script>
  <!--====== Kakao Map api ======-->
  <script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5de6a7a2af636451cce596c34b311543"></script>
  <script type="text/javascript" src="${root}/assets/js/map.js"> </script>
</body>

</html>
