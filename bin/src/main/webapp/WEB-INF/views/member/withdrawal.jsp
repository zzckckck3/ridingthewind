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
		<div id="signupForm" name="signupForm" class="col-8 mx-auto" action="" method="get">
		  <h2 class="text-center pb-5">Withdrawal</h2>
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
		    <div class="col-4 align-self-center">
		      <span><label><img src="${root}/assets/img/signup/eye_opened.PNG" title="비밀번호 보이기" id="isShowMemberPwImg" style="width:15px; height:15px;"></label></span>
		    </div>
		  </div>
		  <div class="row mt-5">
		    <div class="col-5 text-center">
		      <button type="button" class="button-1" id="submitwithdrawalButton">회원 탈퇴</button>
		    </div>
		    <div class="col-2"></div>
		    <div class="col-5 text-center">
		      <button class="button-1" onclick='location.href="${root}/mypage/info"'>취소</button>
		    </div>
		  </div>
		  </div>
		</div>
	<!--====== FORM PART END ======-->
	</div>

	<!--Footer-->
	<%@ include file="../include/footer.jsp" %>
	<!--End of Footer-->
	<script src="${root}/assets/js/withdrawal.js"></script>
</body>
</html>