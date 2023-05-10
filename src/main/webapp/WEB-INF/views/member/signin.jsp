<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../include/head.jsp" %>
    <link rel="stylesheet" href="${root}/assets/css/login.css">
</head>
<body>
	<c:if test="${cookie.memberInfo.value ne null}">
		<c:set var="idck" value=" checked"/>
		<c:set var="saveid" value="${cookie.memberInfo.value}"/>
	</c:if>
    <div class='container'>
	    <!--Header -->
	    <%@ include file="../common/header.jsp" %>
	    <!--End of Header-->
	    <!--Login-->
		<div class="form-wrapper justify-content-center col-xs-12 col-sm-10 col-9 col-md-8 mx-auto form-area my-5">
				<div class="ms-5 me-5">
					<form method="post" id="loginForm" class="col-8 mx-auto" action="" method="POST">
	          			<input type="hidden" name="action" value="login">
						<h2 class="text-center pb-5">Login</h2>
						<div>
							<input class="input-box sign-up-box" type="text" name="memberId" id="memberId" placeholder="ID를 입력하세요" value="${saveid}" autofocus required>  
						</div>
						<div align="right" >
							<label for="idsave" > 아이디저장 </label>
				            <span class="align-self-center"><input
				                class="form-check-input"
				                type="checkbox"
				                value="ok"
				                id="saveid"
				                name="saveid"
				                ${idck}/>
              				</span>  
						</div>
						<div class="mt-3 py-1 input-box" >
							<input class="input-box-2" type="password" name="memberPw" id="memberPw" placeholder="PW를 입력하세요" required>							
							<span style="float:right;"><label class="mt-2"><img src="${root}/assets/img/signup/eye_closed.png" title="비밀번호 보이기" id="isShowMemberPwImg" style="width: 35px; height: auto;"></label></span>
						</div>
						<div class="mt-5">
							<div class="row">
								<div class="col-5 text-center">
									<button type="submit" id="submitLoginButton" class="button-1">로그인</button>
								</div>
								<div class="col-2"></div>
								<div class="col-5 text-center">
									<button type="button" id="joinButton" class="button-1">
										회원가입
									</button>
								</div>
							</div>
						</div>
					</form>
				</div>
		</div>	
		<!--End of Login-->
	</div>
	<!--End of Main-->
        
	<!--Footer-->
	<footer class="py-3 my-4">
	    <%@ include file="../include/footer.jsp" %>
	</footer>
	<!--End of Footer-->
	
  	<script>
	  	document.querySelector("#submitLoginButton").addEventListener("click", function () {
		  	if (!document.querySelector("#memberId").value) {
	          alert("아이디 입력!!");
	          return;
	        } else if (!document.querySelector("#memberPw").value) {
	          alert("비밀번호 입력!!");
	          return;
	        } else {
	          let form = document.querySelector("#loginForm");
	          form.setAttribute("action", "${root}/member/login");
	          form.submit();
	        }
	   	});
	  	
	  	document.querySelector("#joinButton").addEventListener("click", function () {
	  		 location.href = "${root}/member/join";
	   	});
	  	
	  	/* 비밀번호 show/hide 이미지 */
	  	let isShowMemberPwImgElement = document.getElementById('isShowMemberPwImg');
	  	let isShow = true;

	  	/* 비밀번호 숨기기/보이기 */
	  	isShowMemberPwImgElement.addEventListener('click', function () {
	  	  let imgFile = ['${root}/assets/img/signup/eye_opened.png', '${root}/assets/img/signup/eye_closed.png'];
	  	  let imgTitle = ['비밀번호 보이기', '비밀번호 감추기'];
	  	  let memberPwElement = document.getElementById('memberPw');
	  	  if (isShow) {
	  	    memberPwElement.type = 'text';
	  	    isShowMemberPwImgElement.src = imgFile[0];
	  	    isShowMemberPwImgElement.title = imgTitle[0];
	  	    isShow = false;
	  	  } else {
	  	    memberPwElement.type = 'password';
	  	    isShowMemberPwImgElement.src = imgFile[1];
	  	    isShowMemberPwImgElement.title = imgTitle[1];
	  	    isShow = true;
	  	  }
	  	});
	  	
  	</script>
</body>
</html>