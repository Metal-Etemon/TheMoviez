<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 회원 정보</title>
</head>
<body class="body-memberinfo">
	<header>
		<%@ include file="/header.jsp" %>
	</header>
	<main style="padding: 220px">
	<div class="container-memberinfo">
	<h1>회원 정보</h1>
		<br>
				<table class="memberinfo">
					<tr>
						<td class="memberinfo-td1">아이디</td>
						<td>${memberInfo.m_id}</td>
						<td class="memberinfo-td1">회원번호</td>
						<td>${memberInfo.m_num}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">이름</td>
						<td>${memberInfo.m_name}</td>
						<td class="memberinfo-td1">가입일</td>
						<td>${memberInfo.m_date}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">성별</td>
						<td>${memberInfo.m_gender}</td>
						<td class="memberinfo-td1">생일</td>
						<td>${memberInfo.m_birth}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">연락처</td>
						<td colspan="3">${memberInfo.m_tel}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">우편번호</td>
						<td colspan="3">${memberInfo.m_zipcode}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">주소</td>
						<td colspan="3">${memberInfo.m_addr}<br>${memberInfo.m_addrdetail}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">선호 장르</td>
						<td colspan="3">${memberInfo.m_like}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">IP 주소</td>
						<td colspan="3">${memberInfo.m_ip}</td>
					</tr>
				</table>
				<div class="div-center">
					<form action="memberDeleteUi.do" id="member" name="member" method="post">
						<input type="hidden" id="m_num" name="m_num" value="${memberInfo.m_num}">
						<input type="button" value="수정" onclick="location.href='memberEditUi.do?m_num=${memberInfo.m_num}'" class="box2 box3 board-box">
						<input type="submit" value="탈퇴" class="box2 box3 board-box">
					</form>
				</div>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>