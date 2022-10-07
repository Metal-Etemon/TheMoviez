<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 목록</title>
</head>
<body>
<table border="0" class="comment">
	<c:forEach var="cmtList" items="${cmtList}">
		<tr>
			<td colspan="3" class="comment-t"></td>
		</tr>
		<tr>
			<td rowspan="2" class="comment-td1">
				<img src='https://ifh.cc/g/nL6c2V.png' style="background-color: #ecdfff; border: 2px solid #d28010; border-radius: 50%; width: 50px; height: 50px;">
			</td>
			<td colspan="2" style="text-align: left;" class="grey">
				${cmtList.m_id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${cmtList.cmt_writeday}
			</td>
		</tr>
		<tr>
			<td style="text-align: left; white-space: pre-line;">
				<span>${cmtList.cmt_content}</span>
				<br>
			</td>
			<td colspan="2" style="text-align:right">
			<c:choose>
			<c:when test="${m_id eq cmtList.m_id}">
					<form action="cmtDelete.do" id="cmtDel" name="cmtDel" method="get" >
					<input type="hidden" name="com_num" value="${cmtList.com_num}">
					<input type="hidden" name="cmt_num" value="${cmtList.cmt_num}">
					<input type="button" value="삭제" onclick="cmtDel_ck()" class="cmtdel">
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
	<div class="div-center"><jsp:include page="cmtPage.jsp" flush="true"></jsp:include></div>
</body>
</html>