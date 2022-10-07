<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 비밀번호 변경</title>
</head>
<body class="body-memberinfo">
	<header class="m_header">
		<c:import url="/header.jsp"></c:import>
	</header>
	<main style="padding: 220px">
	<div class="container-memberinfo">
	<h1>비밀번호 변경</h1>
	<br>
	<form action="memberEditPw.do" id="member" name="member" method="post">
	<input type="hidden" id="m_num" name="m_num" value="${memberInfo.m_num}">
	<input type="hidden" id="m_passwd" name="m_passwd" value="${memberInfo.m_passwd}">
	
		<table class="memberinfo">
			<tr>
				<td class="memberinfo-td1">현재 비밀번호</td>
				<td>
					<input type="password" id="m_passwd_now" name="m_passwd_now" class="box1 wid1">
				</td>
			</tr>
			<tr>
				<td class="memberinfo-td1">새 비밀번호</td>
				<td>
					<input type="password" id="m_passwd_new" name="m_passwd_new" class="box1 wid1">
				</td>
			</tr>
			<tr>
				<td class="memberinfo-td1">새 비밀번호 확인</td>
				<td>
					<input type="password" id="m_passwd_new2" name="m_passwd_new2" class="box1 wid1">
				</td>
			</tr>
		</table>
		<div class="div-center">
			<input type="button" value="비밀번호 변경" onclick="memberEditPw_ck()" class="box2 board-box">
		</div>
	</form>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>