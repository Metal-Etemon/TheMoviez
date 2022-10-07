<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Community - 검색 결과</title>
</head>
<body class="body-list">
	<header>
		<c:import url="/header.jsp" />
	</header>
	<main style="padding: 220px">
		<div class="container-list">
			<h1>Community</h1>
			<c:choose>
				<c:when test="${method == 'com_title'}">
					<c:set var="method" value="제목" />
				</c:when>
				<c:when test="${method == 'com_content'}">
					<c:set var="method" value="내용" />
				</c:when>
				<c:when test="${method == 'm_id'}">
					<c:set var="method" value="작성자" />
				</c:when>
			</c:choose>

			<h3>"${method}"에 대한 "${keyword}"의 검색 결과</h3>

			<br>
			<table class="list">
				<tr>
					<th class="num">번호</th>
					<th class="title">제목</th>
					<th class="name">작성자</th>
					<th class="writeday">작성일</th>
					<th class="readnum">조회수</th>
				</tr>
				<c:forEach var="list" items="${list}">
					<c:set var="writeday" value="${list.com_writeday}" />
					<tr>
						<td class="grey">${list.com_num}</td>
						<td style="text-align: left;"><a
							href="readnum.do?com_num=${list.com_num}">${list.com_title}</a></td>
						<td>${list.m_id}</td>
						<td class="grey">${fn:substring(writeday,0,10)}</td>
						<td class="grey">${list.com_readnum}</td>
					</tr>
				</c:forEach>
			</table>
			<c:choose>
				<c:when test="${not empty m_id}">
					<input type="button" value="새 글"
						onclick="location.href='write.jsp'" class="box2 box3 board-box">
				</c:when>
			</c:choose>
			<div class="div-center">
				<c:import url="search.jsp" />
			</div>
			<div class="div-center">
				<c:import url="searchPage.jsp" />
			</div>
		</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>