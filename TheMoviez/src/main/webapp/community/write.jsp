<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Community - Write</title>
</head>
<body onload="document.community.com_title.focus();">
	<header class="m_header">
		<c:import url="/header.jsp" />
	</header>
	<main style="padding: 220px">
		<div class="container-content">
			<h1>
				<a href="list.do">Community</a>
			</h1>
			<br>
			<form action="write.do" id="community" name="community" method="post"
				accept-charset="UTF-8">
				<input type="hidden" id="m_id" name="m_id" value="${m_id}">
				<table class="board" border="0">
					<tr>
						<td><br></td>
					</tr>
					<tr>
						<td class="board-right">아이디</td>
						<td>${m_id}</td>
					</tr>
					<tr>
						<td class="board-right">제목</td>
						<td><input type="text" id="com_title" name="com_title"
							class="box1 board-box board-input"></td>
					</tr>
					<tr>
						<td class="board-right">내용</td>
						<td><textarea id="com_content" name="com_content"
								class="box1 board-input"></textarea></td>
					</tr>
					<tr>
						<td><br></td>
					</tr>
				</table>
				<div class="div-center">
					<input type="button" value="등록" onclick="comWrite_ck()"
						class="box2 box3 board-box"> <input type="button"
						value="취소" onclick="location.href='list.do'"
						class="box2 box3 board-box">
				</div>
			</form>
		</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>