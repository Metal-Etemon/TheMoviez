<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.json.simple.JSONObject" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/TheMoviez/resources/js/index.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/TheMoviez/resources/css/theMoviezStyle.css">

<title>The Moviez : 영화 검색 결과</title>
</head>
<body class="body-list">
	<header>
		<%@ include file="/header.jsp" %>
	</header>
	<main style="padding: 220px">
	<div class="container-movie">
	<h1><i>"${movie}"</i> 에 대한 검색 결과</h1>
	<br>
	<table class="list">
	<c:forEach var="movie" items="${movieList}">
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
    	<tr>
    		<td class="movie-td">평점</td>
    		<td>${movie.userRating}점</td>
    	</tr>
    	<tr>
    		
    		<td style="border-bottom: 0;">
    			<form action="movieInsert.do" id="movieReview" name="movieReview">
    				<input type="hidden" id="title" name="title" value="${movie.title}">
    				<input type="hidden" id="link" name="link" value="${movie.link}">
    				<input type="hidden" id="image" name="image" value="${movie.image}">
    				<input type="hidden" id="subtitle" name="subtitle" value="${movie.subtitle}">
    				<input type="hidden" id="pubDate" name="pubDate" value="${movie.pubDate}">
    				<input type="hidden" id="director" name="director" value="${movie.director}">
    				<input type="hidden" id="actor" name="actor" value="${movie.actor}">
    				<input type="hidden" id="userRating" name="userRating" value="${movie.userRating}">
    				<input type="submit" value="리뷰 쓰기" class="revbox">   			
    			</form>
    		</td>
    		<td style="border-bottom: 0;" colspan="2"></td>
    	</tr>
    	<tr>
    		<td colspan="3"><br></td>
    	</tr>
    </c:forEach>
	</table>
	</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
</html>