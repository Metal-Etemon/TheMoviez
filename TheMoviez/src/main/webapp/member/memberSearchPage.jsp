<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 회원 검색 결과</title>
</head>
<body class="body-list">
	<header>
		<%@ include file="/header.jsp" %>
	</header>
	<main style="padding: 220px">
	<div class="container-memberlist">
	<h1>회원 목록</h1>
	<c:choose>
				<c:when test="${method == 'm_id'}">
					<c:set var="method" value="아이디" />
				</c:when>
				<c:when test="${method == 'm_name'}">
					<c:set var="method" value="이름" />
				</c:when>
			</c:choose>

			<h3>"${method}"에 대한 "${keyword}"의 검색 결과</h3>
	<br>
	<table class="memberlist">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>성별</th>
			<th>생일</th>
			<th>연락처</th>
			<th>우편번호</th>
			<th>가입일</th>
			<th>IP주소</th>
			<th></th>
		</tr>
		<c:forEach var="dto" items="${memberList}">
			<tr>
				<td>${dto.m_num}</td>
				<td>${dto.m_id}</td>
				<td>${dto.m_name}</td>
				<td>${dto.m_gender}</td>
				<td>${dto.m_birth}</td>
				<td>${dto.m_tel}</td>
				<td>${dto.m_zipcode}</td>
				<td>${dto.m_date}</td>
				<td>${dto.m_ip}</td>
				<td>
					<a href="memberInfo.do?m_num=${dto.m_num}">회원정보</a> /
					<a href="memberDeleteUi.do?m_num=${dto.m_num}">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="div-center"><%@ include file="memberSearch.jsp" %></div>
	<div class="div-center"><jsp:include page="searchPage.jsp" flush="true"></jsp:include></div>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>