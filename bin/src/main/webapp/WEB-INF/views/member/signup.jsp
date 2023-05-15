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
    <div class="container">
    <!--Header -->
    <%@ include file="../common/header.jsp" %>
    <!--End of Header-->
        <!--====== FORM PART START ======-->
        <div class="form-wrapper justify-content-center col-xs-12 col-sm-10 col-9 col-md-8 mx-auto form-area my-5">
            <form id="signupForm" name="signupForm" class="col-8 mx-auto" action="" method="POST">
                     <input type="hidden" name="" value="join" />
                <h2 class="text-center pb-5">Sign Up</h2>
                <div class="row justify-content-center py-1">
                    <div class="col-8">
                        <input class="input-box sign-up-box" type="text" name="memberId" id="memberId" placeholder="ID를 입력하세요">
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
                        <span><label><img src="${root}/assets/img/signup/eye_closed.png" title="비밀번호 보이기" id="isShowMemberPwImg" style="width:30px; height:30px;"></label></span>
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
                    <div class="">
                        <input class="input-box sign-up-box" style="width: 50%;" type="text" name="memberName" id="memberName" placeholder="이름을 입력하세요" required>
                    </div>
                </div>
                <div class="row py-1">
                    <div class="col-3">
                        <input class="input-box sign-up-box text-center" type="tel" name="mobile1" id="mobile1" value="010" readonly>
                    </div>
                    <span class="text-center mb-2 p-0 align-self-center" style="width:10px;">-</span>
                    <div class="col-4">
                        <input class="input-box sign-up-box" type="tel" name="mobile2" id="mobile2" maxlength="4" placeholder="전화번호를 입력하세요" required>
                    </div>
                    <span class="text-center mb-2 p-0 align-self-center" style="width:10px;">-</span>
                    <div class="col-4">
                        <input class="input-box sign-up-box" type="tel" name="mobile3" id="mobile3" maxlength="4" placeholder="전화번호를 입력하세요" required>
                    </div>
                </div>
                <div class="row justify-content-center py-1">
                    <div class="col-4">
                        <input class="input-box sign-up-box" type="text" name="emailId" id="emailId" placeholder="이메일을 입력하세요" required >
                    </div>
                    <span class="col-1 text-center align-self-center" id="emailAtSign">@</span>
                    <div class="col-4">
                        <input class="input-box sign-up-box" type="text" name="emailDomain" id="emailDomain" placeholder="이메일을 입력하세요" required readonly >
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
                        <button type="submit" class="button-1" id="signupbtn">회원가입</button>
                    </div>
                    <div class="col-2"></div>
                    <div class="col-5 text-center">
                        <button class="button-1" onclick='location.href="${root}/member/login"'>홈</button>
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
      	var isMemberId = false;
     
      	document.querySelector("#memberId").addEventListener("keyup", function () {
			let memberId = this.value;
	  	 	let resultDiv = document.querySelector("#memberIdMessage");
	  	 	if(memberId.length < 6 || memberId.length > 16) {
	  		 	resultDiv.setAttribute("class", "mb-3 text-dark");
	  		 	resultDiv.textContent = "아이디는 6자 이상 16자 이하 입니다.";
	  		 	isMemberId = false;
	  	 	} else {
	  		 	fetch("${root}/member/check/" + memberId)
		   		.then(response => response.text())
		   		.then(data => {
		   			console.log(data)
			 		if(data == 0) {
			   			resultDiv.setAttribute("class", "mb-3 text-primary");
		       			resultDiv.textContent = memberId + "는 사용할 수 있습니다.";
		       			isMemberId = true;
			 		} else {
			   			resultDiv.setAttribute("class", "mb-3 text-danger");
	 		       		resultDiv.textContent = memberId + "는 사용할 수 없습니다.";
	 		       		isMemberId = false;
			 		}
	  		   });
	  	 	}
	    });
      	  
        document.querySelector("#signupbtn").addEventListener("click", function(){
            if(!document.querySelector("#memberId").value){
                alert("아이디를 입력해주세요");
            } else if(!document.querySelector("#memberPassword").value){
                alert("비밀번호를 입력해주세요");
            }else if (document.querySelector("#memberPassword").value != document.querySelector("#memberPwConfirm").value) {
                alert("비밀번호 불일치!!");
            } else if(!document.querySelector("#emailId").value){
                alert("이메일을 입력해주세요");
            } else if(!document.querySelector("#emailDomain").value){
                alert("도메인을 입력해주세요");
            } else if(!document.querySelector("#memberName").value){
                alert("이름을 입력해주세요");
            } else if(!document.querySelector("#mobile2").value){
                alert("전화번호를 입력하세요");
            } else if(!document.querySelector("#mobile3").value){
                alert("전화번호를 입력하세요");
            } else{
                let form = document.querySelector("#signupform");
                form.setAttribute("action","${root}/member/join");
                form.submit();
                alert("회원가입 되었습니다");
            }
        });
        /* 비밀번호 show/hide 이미지 */
        let isShowMemberPwImgElement = document.getElementById('isShowMemberPwImg');
	  	let isShow = true;
	  	/* 비밀번호 숨기기/보이기 */
	  	isShowMemberPwImgElement.addEventListener("click", function () {
	  	  let imgFile = ['${root}/assets/img/signup/eye_opened.png', '${root}/assets/img/signup/eye_closed.png'];
	  	  let imgTitle = ['비밀번호 보이기', '비밀번호 감추기'];
	  	  let memberPwElement = document.getElementById('memberPassword');
	  	  let memberPwElementConfirmElement = document.getElementById('memberPwConfirm');
	  	  if (isShow) {
	  	    memberPwElement.type = 'text';
	  	    memberPwElementConfirmElement.type = 'text';
	  	    isShowMemberPwImgElement.src = imgFile[0];
	  	    isShowMemberPwImgElement.title = imgTitle[0];
	  	    isShow = false;
	  	  } else {
	  	    memberPwElement.type = 'password';
	  	    memberPwElementConfirmElement.type = 'password';
	  	    isShowMemberPwImgElement.src = imgFile[1];
	  	    isShowMemberPwImgElement.title = imgTitle[1];
	  	    isShow = true;
	  	  }
	    });
	  	
	  	/* 비밀번호, 비밀번호 확인 매핑하기 */
	  	let memberPwElement = document.getElementById('memberPassword');
	  	let memberPwElementConfirmElement = document.getElementById('memberPwConfirm');
	  	memberPwElementConfirmElement.addEventListener('blur', function () {
	  	  //alert('비밀번호 확인에서 벗어났어요.');
	  	  let memberPwValue = document.getElementById('memberPassword').value;
	  	  let memberPwConfirmValue = document.getElementById('memberPwConfirm').value;
	  	  let memberPwMessageElement = document.getElementById('memberPwMessage');
	  	  if (memberPwValue != memberPwConfirmValue) {
	  	    memberPwMessageElement.innerText = '비밀번호 확인이 동일하지 않습니다.';
	  	    memberPwMessageElement.style.color = 'red';
	  	    memberPwMessageElement.style.fontWeight = 'bold';
	  	    memberPwElement.focus();
	  	  } else {
	  	    memberPwMessageElement.innerText = '비밀번호 확인이 동일합니다.';
	  	    memberPwMessageElement.style.color = 'green';
	  	  }
	  	});
	  	
	  	/* 휴대폰 중간번호 다음 입력항목 이동하기 */
	  	let mobile2Element = document.getElementById('mobile2');
	  	let mobile3Element = document.getElementById('mobile3');
	  	mobile2Element.addEventListener('keyup', function () {
	  	  if (mobile2Element.value.trim().length == 4) {
	  	    mobile3Element.focus();
	  	  }
	  	});

	  	/* 휴대폰 끝번호 다음 입력항목 이동하기 */
	  	let email1Element = document.getElementById('emailId');
	  	mobile3Element.addEventListener('keyup', function () {
	  	  if (mobile3Element.value.trim().length == 4) {
	  	    email1Element.focus();
	  	  }
	  	});
	  	
	  	/* 이메일 선택 및 이메일 직접입력 */
	  	let email2Element = document.getElementById('emailDomain');
	  	let emailChooseElement = document.getElementById('emailChoose');
	  	emailChooseElement.addEventListener('change', function () {
	  	  let email1ElementValue = email1Element.value.trim();
	  	  if (email1ElementValue == null || email1ElementValue.length == 0) {
	  	    email1Element.placeholder = '이메일 아이디를 입력하세요.';
	  	    email1Element.focus();
	  	    emailChooseElement.selectedIndex = 0;
	  	  } else {
	  	    let emailChooseElementValue = emailChooseElement.value;
	  	    switch (emailChooseElementValue) {
	  	      case 'NONE':
	  	        email2Element.value = '';
	  	        email2Element.readOnly = true;
	  	        break;
	  	      case 'member_INPUT':
	  	        email2Element.value = '';
	  	        email2Element.readOnly = false;
	  	        email2Element.focus();
	  	        break;
	  	      default:
	  	        email2Element.value = emailChooseElementValue;
	  	    	email2Element.readOnly = true;
	  	    }
	  	  }
	  	});
       
      </script>
</body>
</html>