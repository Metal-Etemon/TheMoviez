<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Q&A - Edit</title>
</head>
<body onload="document.qna.qna_title.focus();">
	<header class="m_header">
		<c:import url="/header.jsp" />
	</header>
	<main style="padding: 220px">
	<div class="container-content">
	<h1><a href="list.do">Q&A</a></h1>
	<br>
	<form action="edit.do" id="qna" name="qna" method="post" accept-charset="UTF-8">
		<input type="hidden" id="m_id" name="m_id" value="${content.m_id}">
		<input type="hidden" id="qna_num" name="qna_num" value="${content.qna_num}">
		<input type="hidden" id="qna_passwd" name="qna_passwd" value="${content.qna_passwd}">
		<table class="board" border="0">
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td class="board-right">아이디</td>
				<td>${content.m_id}</td>
			</tr>
			<tr>
				<td class="board-right">비밀번호</td>
				<td><input type="password" id="qna_passwd_ck" name="qna_passwd_ck" class="box1 board-box board-input-pw" placeholder="작성 시 사용했던 비밀번호를 입력해 주세요"></td>
			</tr>
			<tr>
				<td class="board-right">제목</td>
				<td><input type="text" id="qna_title" name="qna_title" class="box1 board-box board-input" value="${content.qna_title}"></td>
			</tr>
			<tr>
				<td class="board-right">내용</td>
				<td><textarea id="qna_content" name="qna_content" class="box1 board-input">${content.qna_content}</textarea></td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
		</table>
		<div class="div-center">
			<input type="button" value="등록" onclick="qnaEdit_ck()" class="box2 box3 board-box">
			<input type="button" value="취소" onclick="location.href='list.do'" class="box2 box3 board-box">
		</div>
	</form>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>