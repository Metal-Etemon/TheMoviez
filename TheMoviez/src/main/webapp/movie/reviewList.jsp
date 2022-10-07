<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Blaka+Ink&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Rubik+Dirt&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nabla&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500;700&display=swap" rel="stylesheet">



<title>리뷰 목록</title>
</head>
<body>
<table border="0" class="comment">
	<c:forEach var="revList" items="${revList}">
		<tr>
			<td colspan="3" class="comment-t"></td>
		</tr>
		<tr>
			<td rowspan="2" class="comment-td1">
				<img src='https://ifh.cc/g/lXN2no.jpg' style="background-color: #ecdfff; border: 2px solid #d28010; border-radius: 50%; width: 50px; height: 50px;">
			</td>
			<td colspan="2" style="text-align: left;" class="grey">
				${revList.m_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${revList.rev_writeday}
			</td>
		</tr>
		<tr>
			<td style="text-align: left; white-space: pre-line;">
				<p class="grey">${movie.title}</p>
				<span>${revList.rev_content}</span>
				<br>
			</td>
			<td colspan="2" style="text-align:right">
			<c:choose>
			<c:when test="${m_id eq revList.m_id}">
					<form action="revDelete.do" id="reviewdel" name="reviewdel" method="get" >
					<input type="hidden" name="movie" value="${movie.title}">
					<input type="hidden" name="link" value="${revList.link}">
					<input type="hidden" name="rev_num" value="${revList.rev_num}">
					<input type="button" value="삭제" onclick="revDel_ck()" class="cmtdel">
					</form>
			</c:when>	
			</c:choose>
			</td>
		</tr>
		<tr>
			<td colspan="3" class="comment-b"></td>
		</tr>
</c:forEach>
	</table>
		<br>
		<div class="div-center"><jsp:include page="revPage.jsp" flush="true"></jsp:include></div>
	
</body>
</html>