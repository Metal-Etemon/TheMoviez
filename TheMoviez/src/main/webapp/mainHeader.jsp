<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/TheMoviez/resources/js/TheMoviez_ck.js"></script>

<link rel="stylesheet" href="/TheMoviez/resources/css/theMoviezStyle.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Blaka+Ink&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Rubik+Dirt&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nabla&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500;700&display=swap" rel="stylesheet">
<title>The Moviez : Main Header</title>
</head>
<body>
	<div>
		<nav class="m_nav m_nav2">
			<ul>
				<li><a href="/TheMoviez/newsletter/list.do">뉴스레터</a></li>
				<li><a href="/TheMoviez/community/list.do">커뮤니티</a></li>
				<li><a href="/TheMoviez/qna/list.do">문의사항</a></li>
			</ul>
		</nav>
		<nav class="m_nav m_nav3">
			<ul>
				<li>
				<c:import url="login/login.jsp" /></li>
			</ul>
		</nav>
	</div>
</body>
<script type="text/javascript" src="/TheMoviez/resources/js/loginModal.js"></script>
</html>