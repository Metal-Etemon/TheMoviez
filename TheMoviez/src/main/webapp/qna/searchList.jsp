<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Q&A - 검색 결과</title>
</head>
<body class="body-list">
	<header>
		<c:import url="/header.jsp" />
	</header>
	<main style="padding: 220px">
		<div class="container-list">
			<h1>Q&A</h1>
			<c:choose>
				<c:when test="${method == 'qna_title'}">
					<c:set var="method" value="제목" />
				</c:when>
				<c:when test="${method == 'qna_content'}">
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
					<c:set var="writeday" value="${list.qna_writeday}" />
					<tr>
						<td class="grey">${list.qna_num}</td>
						<td style="text-align: left;"><c:forEach begin="1"
								end="${list.qna_repIndent}">
								<%="&nbsp;&nbsp;&nbsp;&nbsp;↳"%>
							</c:forEach> <a href="readnum.do?qna_num=${list.qna_num}">${list.qna_title}</a>
						</td>
						<td>${list.m_id}</td>
						<td class="grey">${fn:substring(writeday,0,10)}</td>
						<td class="grey">${list.qna_readnum}</td>
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