<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>

<c:set var="checked" value="checked"></c:set>
<c:set var="saveId" value="${cookie.ssafy_id.value}"></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${root}/assets/css/index.css">
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
		rel="stylesheet".
		integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
		crossorigin="anonymous" />
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />

	<title>Enjoy Trip</title>
</head>
<body>
	<div class='container'>
		<!--Header -->
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<!--End of Header-->

		<!--Main-->
		<div class='container text-center'>
			<section class="py-5 container ">
				<div class="row py-lg-5"
					style='background-image: url(${root}/assets/img/trip_img_main.jpg); background-size: cover; border-radius: 10px;'>
					<div class="col-lg-6 col-md-8 mx-auto" id='introduce'>
						<h1 class="fw-light" style='font-size: 4.5em'>
							<b style="font-weight: 900 !important;">ENJOY TRIP</b>
						</h1>
						<p class="lead text-muted"
							style='color: white !important; font-size: 2em; font-weight: 900 !important'>
							<b>여행 계획을 손쉽게</b>
						</p>
						<p>
							<a href="${root}/tour/sido" class="btn btn-light my-2">여행정보 보기</a>
							 <a href="${root}/tour/share-list" class="btn btn-dark my-2">여행공유게시판 가기</a>
						</p>
					</div>
				</div>
			</section>
			<div class='container row row-cols-1 row-cols-sm-2 row-cols-md-4'
				id='card-list' style='margin-left: 6px;'>
				<div class="card col" style="width: 18rem;">
					<img src="${root}/assets/img/Haeundae.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">해운대로 떠나요</h5>
						<p class="card-text">피서철이 되면 뉴스에 단골로 등장할 만큼 국민이 아는 피서의 명소</p>
						<a href="tour?action=mvtrip" class="btn btn-primary" onclick='Haeundae()'>해운대
							관광!</a>
					</div>
				</div>

				<div class="card col" style="width: 18rem;">
					<img src="./assets/img/Juju.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">제주도로 떠나요</h5>
						<p class="card-text">어디를 가나 천혜의 아름다운 자연 경관을 만날 수 있어요</p>
						<a href="tour?action=mvtrip" class="btn btn-primary" onclick='Jeju()'>제주도 관광!</a>
					</div>
				</div>

				<div class="card col" style="width: 18rem;">
					<img src="./assets/img/Tongyung.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">통영으로 떠나요</h5>
						<p class="card-text">경상남도 남쪽 끝에 위치한 아름다운 도시</p>
						<a href="tour?action=mvtrip" class="btn btn-primary" onclick='Tong()'>통영 관광!</a>
					</div>
				</div>

				<div class="card col" style="width: 18rem;">
					<img src="./assets/img/Gwangju.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">광주로 떠나요</h5>
						<p class="card-text">노잼도시 아닙니다</p>
						<a href="tour?action=mvtrip" class="btn btn-primary" onclick='Gwang()'>광주 관광??;</a>
					</div>
				</div>

			</div>
			<hr>
		</div>
		<!--End of Main-->

		<!--Footer-->
		<%@ include file="common/footer.jsp" %>
		<!--End of Footer-->
	</div>
	<form id="testform" method="post">
		<input type="submit" id="test-btn" value="test">
	</form>
	<script src="./assets/js/main.js"></script>
	<script>
	document.querySelector("#test-btn").addEventListener("click", function (){
		let form = document.querySelector("#testform");
		form.setAttribute("action","${root}/member/update");
		form.submit();
	})
    </script>
</body>
</html>
