<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez</title>
</head>
<body class="">
	<section class="section-main">
		<header class="m_header">
			<c:import url="/mainHeader.jsp"></c:import>
		</header>
		<div class="mh_padding">
			<div class="m_logo"><a href="/TheMoviez/" style="font-size: 60pt">The Moviez</a></div>
			<div class="main">
				<span style="font-size: 30pt;">더 넓은 세상, 더 무비즈<br></span>
				<span>이야기하고 싶은 영화를 검색해 주세요. <br></span>
			</div>
			<br>
			<div>
			<c:import url="/movie/movieSearch.jsp"></c:import>
			</div>	
		</div>
	</section>
</body>
</html>