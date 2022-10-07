<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 회원 탈퇴</title>
</head>
<body class="body-memberinfo">
	<header class="m_header">
		<c:import url="/header.jsp"></c:import>
	</header>
	<main style="padding: 220px">
	<div class="container-memberinfo">
	<h1>회원 탈퇴</h1>
	<br>
	<h3>회원 탈퇴를 원하시면 비밀번호를 입력해 주세요.</h3>
	<br>
	<form action="memberDelete.do" id="member" name="member">
	<input type="hidden" id="m_id" name="m_id" value="${m_id}">
	<input type="hidden" id="m_num" name="m_num" value="${memberInfo.m_num}">
	<input type="hidden" id="m_passwd" name="m_passwd" value="${memberInfo.m_passwd}">
		<table class="memberinfo">
			<tr>
				<td class="memberinfo-td1">비밀번호</td>
				<td>
					<input type="text" id="m_passwd_ck" name="m_passwd_ck" class="box1 wid1">
				</td>
			</tr>
		</table>
		<div class="div-center">
			<input type="button" value="회원 탈퇴" onclick="memberDelete_ck()" class="box2 board-box">
		</div>
	</form>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>