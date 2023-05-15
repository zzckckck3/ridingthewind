<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ringdingdong.ridingthewind.model.MemberDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../include/head.jsp" %>
	<link rel="stylesheet" href="${root}/assets/css/login.css">
</head>
<body>
	<div class="container">
    <!--Header -->
    <%@ include file="../common/header.jsp" %>
    <% String memberid = memberInfo.getMemberId(); %>
    <!--End of Header-->
		<!--====== FORM PART START ======-->
		<div class="form-wrapper justify-content-center col-xs-12 col-sm-10 col-9 col-md-8 mx-auto form-area my-5">
			<form id="editForm" name="editForm" class="col-8 mx-auto" action="edit" method="post">
         			<!-- input type="hidden" name="action" value="join" / -->
				<h2 class="text-center pb-5">Edit Profile</h2>
				<div class="row justify-content-center py-1">
					<div class="col-8">
						<input class="input-box sign-up-box" type="text" name="memberId" id="memberId" value="<%=memberid%>" readonly="readonly">
					</div>
					<div class="col-4">
						<span id="memberIdMessage"></span>
					</div>
				</div>
				<div class="row justify-content-center py-1">
					<div class="col-8">
						<input class="input-box sign-up-box" type="password" name="memberPassword" id="memberPassword" placeholder="PW를 입력하세요" required>
					</div>
					<div class="col-4 align-self-center" >
						<span><label><img src="${root}/assets/img/signup/eye_opened.PNG" title="비밀번호 보이기" id="isShowMemberPwImg" style="width:15px; height:15px;"></label></span>
					</div>
					
				</div>
				<div class="row justify-content-center py-1">
					<div class="col-8">
						<input class="input-box sign-up-box" type="password" name="memberPwConfirm" id="memberPwConfirm" placeholder="PW를 한번 더 입력하세요" required />
					</div>
					<div class="col-4">
						<span id="memberPwMessage"></span>
					</div>
				</div>
				<div class="row justify-content-center py-1">
					<div class="col-4">
						<input class="input-box sign-up-box" type="text" name="emailId" id="emailId" placeholder="이메일을 입력하세요" required>
					</div>
					<div class="col-1">
						<img src="${root}/assets/img/at.png" style="width: 30px; top:10px; position: relative;">
					</div>
					<div class="col-4">
						<input class="input-box sign-up-box" type="text" name="emailDomain" id="emailDomain" placeholder="이메일을 입력하세요" required>
					</div>
					<div class="col-3">
						<select class="input-box-2" name="emailChoose" id="emailChoose" style="height: 60px; padding-left: 10px">
							<option value="none" selected disabled>선택</option>
							<option value="samsung.com">삼성</option>
							<option value="naver.com">네이버</option>
							<option value="gmail.com">구글</option>
							<option value="member_INPUT">직접 입력</option>
						</select>
					</div>
					
				</div>
				<div class="row mt-5">
					<div class="col-5 text-center">
						<button type="submit" class="button-1" id="submitEditButton">변경 완료</button>
					</div>
					<div class="col-2"></div>
					<div class="col-5 text-center">
						<button class="button-1" onclick='location.href="${root}/mypage/info"'>취소</button>
					</div>
				</div>
			</form>
		</div>
	<!--====== FORM PART END ======-->
	</div>

	<!--Footer-->
	<footer class="py-3 my-4">
	    <%@ include file="../include/footer.jsp" %>
	</footer>
	<!--End of Footer-->
	<script>
	</script>
</body>
</html>
