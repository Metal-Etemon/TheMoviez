<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/TheMoviez/resources/js/theMoviez_ck.js"></script>

<title>The Moviez : Login</title>
</head>
<body>
<c:choose>
	<c:when test="${m_id=='admin'}">
		${m_id} 님 환영합니다.
		<a href="#" onclick="logout_ck()">로그아웃</a>
		<a href="/TheMoviez/member/memberList.do" target="_top">회원목록보기</a>
	</c:when>
	<c:when test="${not empty m_id}">
		${m_id} 님 환영합니다.
		<a href="#" onclick="logout_ck()">로그아웃</a>
		<a href="/TheMoviez/member/memberInfo.do?m_num=${m_num}" target="_top">회원정보수정</a>
	</c:when>
	<c:when test="${empty m_id}">
		<div class="modal hidden">
  	  <div class="modal_body">
  	  	
			<form action="/TheMoviez/login/login.do" method="post" id="login" name="login" onsubmit="return login_ck()">
				<table class="login">
					<tr>
						<td>아이디 : </td>
						<td>
							&nbsp; <input type="text" id="m_id" name="m_id" class="login-box">&nbsp; 
						</td>
						<td>비밀번호 : </td>
						<td>
							&nbsp; <input type="password" id="m_passwd" name="m_passwd" class="login-box">
						</td>
						<td>
							&nbsp; <input type="button" class="login-button" value="로그인"  onclick="login_ck()">
						</td>
					</tr>
				</table>
			</form>
		
	</div>
	</div>
	<span class="btn-open-popup"><a>로그인</a></span> <a href="/TheMoviez/member/terms.jsp">회원가입</a>
	</c:when>
</c:choose>
</body>
<script type="text/javascript" src="/TheMoviez/resources/js/loginModal.js"></script>
</html>