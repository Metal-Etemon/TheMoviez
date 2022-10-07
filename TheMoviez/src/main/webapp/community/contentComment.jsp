<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Community - Content</title>
</head>
<body class="body-content">
	<header>
		<c:import url="/header.jsp"></c:import>
	</header>
	<main style="padding: 220px">
		<div class="container-content">
			<h1>
				<a href="list.do">Community</a>
			</h1>
			<br>
			<table border="0" class="content">
				<tr>
					<td colspan="5" class="content-menu"><a href="list.do"> >
							Community</a></td>
				</tr>
				<tr>
					<td colspan="5" class="content-title">${content.com_title}</td>
				</tr>
				<tr>
					<td class="content-etc1">작성자</td>
					<td colspan="3" class="content-etc2">${content.m_id}</td>
					<td></td>
				</tr>
				<tr>
					<td class="content-etc1">작성일</td>
					<td class="content-etc3">${content.com_writeday}</td>
					<td class="content-etc1">조회수</td>
					<td class="content-etc2">${content.com_readnum}</td>
					<td></td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<td colspan="5" style="white-space: pre-line" class="content-cont">${content.com_content}</td>
				</tr>
			</table>
			<table class="content-button">
				<tr>
					<td><input type="button" value="목록"
						onclick="location.href='list.do'" class="box2 box3 board-box">
						<c:choose>
							<c:when test="${not empty m_id}">
								<input type="button" value="새 글"
									onclick="location.href='write.jsp'" class="box2 box3 board-box">
							</c:when>
						</c:choose></td>
					<td class="content-button-right"><c:choose>
							<c:when test="${content.m_id == m_id}">
								<input type="button" value="수정"
									onclick="location.href='editui.do?com_num=${content.com_num}'"
									class="box2 box3 board-box">
								<input type="button" value="삭제"
									onclick="location.href='delete.do?com_num=${content.com_num}'"
									class="box2 box3 board-box">
							</c:when>
						</c:choose></td>
				</tr>
			</table>
			<br>
			<div class="div-center">
				<c:import url="commentList.jsp" />
			</div>
			<br>
			<c:if test="${not empty m_id}">
				<c:import url="commentWrite.jsp" />
			</c:if>
		</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>