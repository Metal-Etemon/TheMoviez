<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>
</head>
<body>
	<form action="cmtWrite.do" method="post" id="comment" name="comment">
		<input type="hidden" id="com_num" name="com_num"
			value="${content.com_num}"> <input type="hidden" id="m_id"
			name="m_id" value="${m_id}">
		<table class="comment" border="0">
			<tr>
				<td width="50" style="text-align: center">
				<img src='https://ifh.cc/g/nL6c2V.png' style="background-color: #ecdfff; border: 2px solid #d28010; border-radius: 50%; width: 50px; height: 50px;">
					<br>
					${m_id}
				</td>
				<td rowspan="2">
					<textarea id="cmt_content" name="cmt_content" class="comment-box"></textarea>
					<br>
					<input type="button" value="등록" onclick="cmtWrite_ck()" class="comment-btn">
				</td>
			</tr>
			<tr>
				<td colspan="3"></td>
			</tr>
		</table>
	</form>
</body>
</html>