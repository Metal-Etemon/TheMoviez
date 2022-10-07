<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="searchPost" name="searchPost" method="get" action="search.do" onsubmit="return search_ck()">
		<select id="method" name="method" class="box1 board-box">
			<option value="">선택하세요</option>
			<option value="com_title">제목</option>
			<option value="com_content">내용</option>
			<option value="m_id">작성자</option>
		</select> <input type="text" id="keyword" name="keyword"
			placeholder="검색어를 입력하세요" class="box1 board-box"> <input
			type="button" value="검색" onclick="search_ck()" class="box2 box3 board-box">
	</form>
</body>
</html>