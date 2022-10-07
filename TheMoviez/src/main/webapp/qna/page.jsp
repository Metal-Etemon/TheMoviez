<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="totalPage" value="${page.totalCount/page.perPage}" />
	<c:if test="${page.totalCount % page.perPage != 0}">
		<c:set var="totalPage" value="${totalPage+1}" />
	</c:if>
	<c:set var="i" value="0" />
	<c:forEach var="pglist" begin="1" end="${totalPage}">
		<c:set var="i" value="${i+1}" />
		<c:choose>
			<c:when test="${page.curPage==i}">
				<span class="page">${i}</span>
			</c:when>
			<c:otherwise>
				<a href="list.do?curPage=${i}"><span>${i}</span></a>&nbsp;
		</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>





