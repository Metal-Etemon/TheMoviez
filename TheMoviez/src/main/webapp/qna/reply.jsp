<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : Q&A - Reply Write</title>
</head>
<body onload="document.qna.qna_title.focus();">
	<header class="m_header">
		<c:import url="/header.jsp" />
	</header>
	<main style="padding: 220px">
	<div class="container-content">
	<h1><a href="list.do">Q&A</a></h1>
	<br>
	<form action="reply.do" id="qna" name="qna" method="post" accept-charset="UTF-8">
		<input type="hidden" name="qna_num" value="${content.qna_num}"/>
		<input type="hidden" name="qna_repRoot" value="${content.qna_repRoot}"/>
		<input type="hidden" name="qna_repStep" value="${content.qna_repStep}"/>
		<input type="hidden" name="qna_repIndent" value="${content.qna_repIndent}"/>		
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
				<td class="board-right">비밀번호</td>
				<td><input type="password" id="qna_passwd" name="qna_passwd" class="box1 board-box board-input-pw"></td>
			</tr>
			<tr>
				<td class="board-right">제목</td>
				<td><input type="text" id="qna_title" name="qna_title" value="${content.qna_title}의 답글" class="box1 board-box board-input"></td>
			</tr>
			<tr>
				<td class="board-right">내용</td>
				<td><textarea id="qna_content" name="qna_content" class="box1 board-input">질문 내용:${content.qna_content}<p>===========================</p></textarea></td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
		</table>
		<div class="div-center">
			<input type="button" value="등록" onclick="qnaWrite_ck()" class="box2 box3 board-box">
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