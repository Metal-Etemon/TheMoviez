<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
</head>
<body>
	<form action="revWrite.do" method="post" id="review" name="review">
		<input type="hidden" name="movie" value="${movie.title}">
		<input type="hidden" id="link" name="link" value="${movie.link}">
		<input type="hidden" id="m_id" name="m_id" value="${m_id}">
		<table class="comment">
			<tr>
				<td width="50" style="text-align: center">
				<img src='https://ifh.cc/g/lXN2no.jpg' style="background-color: #ecdfff; border: 2px solid #d28010; border-radius: 50%; width: 50px; height: 50px;">
					<br>
					${m_id}
				</td>
				<td rowspan="2">
					<textarea id="rev_content" name="rev_content" class="comment-box"></textarea>
					<br>
					<input type="button" value="리뷰 작성" onclick="revWrite_ck()" class="comment-btn">
				</td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
		</table>
	</form>
</body>
</html>