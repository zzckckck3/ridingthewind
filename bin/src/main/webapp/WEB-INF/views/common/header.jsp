<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.ringdingdong.ridingthewind.model.MemberDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<%
 MemberDto memberInfo = (MemberDto) session.getAttribute("memberinfo");
if(memberInfo != null) {
%>
<%-- 
<%= userInfo.getName() %>
<%= userInfo.getUserId() %>
 --%>
<% 
}
%>

	<header	class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
		<a href="${root}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
			<span class="fs-4"><b>ENJOY TRIP</b></span>
		</a>
		<c:if test="${empty memberinfo}">
			<form action="">
			<ul class="nav nav-pills">
				<li class="nav-item"><a href="${root}/board/list?pgno=1&key=&word=" id="boardnotice" name="boardnotice" class="nav-link">공지사항</a></li>
				<li class="nav-item"><a href="${root}/member/login" class="nav-link">로그인</a></li>
				<li class="nav-item"><a href="${root}/member/join" class="nav-link">회원가입</a></li>
				<li class="nav-item"><a href="#" class="nav-link">FAQs</a></li>
			</ul>
		</form>
		</c:if>
		<c:if test="${not empty memberinfo}">
			<ul class="nav nav-pills">
				<li class="nav-item"><a href="${root}/board/list?pgno=1&key=&word=" id="boardnotice" name="boardnotice" class="nav-link">공지사항</a></li>
				<li class="nav-item"><a href="${root}/tour/share-list" class="nav-link">공유게시판</a></li>
				<li class="nav-item"><a href="#" class="nav-link">FAQs</a></li>
				<li class="nav-item"><a href="${root}/mypage/info" id="mvmypage" name="mvmypage" class="nav-link">마이페이지</a></li>
				<li class="nav-item"><a href="${root}/member/logout" class="nav-link" id="logout-btn">로그아웃</a></li>
			</ul>
		</c:if>
		
	</header>
	