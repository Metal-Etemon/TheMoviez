<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/TheMoviez/resources/js/TheMoviez_ck.js"></script>
<title>The Moviez : 영화 검색</title>
</head>
<body>
	<form action="movieSearch.do" id="moviesearch" name="moviesearch" method="get" onsubmit="return moviesearch_ck()">
		<input type="text" id="movie" name="movie" class="searchbox" placeholder="영화 제목을 검색해 주세요">
		<input type="button" value="" class="searchbox_btn" onclick="moviesearch_ck()">
	</form>
</body>
</html>