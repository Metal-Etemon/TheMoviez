<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 영화 리뷰</title>
</head>
<body class="body-list">
	<header>
		<%@ include file="/header.jsp" %>
	</header>
	
	<main style="padding: 220px">
	<div class="container-movie">
	<h1>"${movie.title}"의 리뷰</h1>
	<br>
	
	<table class="list">
	   	<tr>
    		<td rowspan="6"><img alt="${movie.title}" src="${movie.image}"></td>
    	</tr>
    	<tr>
			<td class="movie-td"></td>
    		<td class="movie-td"><a href="${movie.link}">${movie.title}<br>(${movie.subtitle})</a></td>
    	</tr>
    	<tr>
    		<td class="movie-td">개봉년도</td>
    		<td>${movie.pubDate}</td>
    	</tr>
    	<tr>
    		<td class="movie-td">감독</td>
    		<td>${fn:replace(movie.director, '|', '&nbsp;')}</td>
    	</tr>
    	<tr>
    		<td class="movie-td">출연</td>
    		<td>${fn:replace(movie.actor, '|', '&nbsp;&nbsp;')}</td>
    	</tr>
	</table>
		<br><br>
			<c:if test="${not empty m_id}">
				<c:import url="reviewWrite.jsp" />
				<br><br>
			</c:if>
				<c:import url="reviewList.jsp" />
				<br>
	
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>