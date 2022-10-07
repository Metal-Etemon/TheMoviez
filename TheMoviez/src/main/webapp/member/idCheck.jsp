<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/TheMoviez/resources/js/theMoviez_ck.js"></script>
<script>
function closewin() {
	
	window.opener.document.member.m_id.value="${idCheck.m_id}";
	window.close();
}
</script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link rel="stylesheet" href="../resources/css/theMoviezStyle.css">
<title>The Moviez : 아이디 중복 검사</title>
</head>
<body class="body-regist textCenter">
<div class="container-idcheck">
<div style="text-align: center; padding: 20px 0px 5px 0;">
	<h2>아이디 중복 검사</h2>
	<c:choose>
		<c:when test="${empty idCheck.m_id}">
			<form name="member" action="idCheck.do" method="post" onSubmit="return idCheck_ck();">
				<table>
					<tr>
						<td class="td">
							<input type="text" id="m_id" name="m_id" placeholder="영문자로 시작, 6~20자" class="box1" autofocus> 
							<input type="button" value="아이디 중복 검사" class="box2" onclick="idCheck_ck()" style="width: 130px">
						</td>
					</tr>
				</table>
			</form>
		</c:when>
		<c:when test="${idCheck.m_id == '0'}">
			<script>
				alert("이미 존재하는 아이디입니다.");
				history.back();
			</script>
		</c:when>
		<c:otherwise>
			<br>
			<p><i><span style="color:#8000ff">${idCheck.m_id}</span></i> 는 사용 가능한 아이디입니다.</p>
			<br>
			<input type="button" value="사용하기" class="box2 swid2" onclick="closewin()">
		</c:otherwise>
	</c:choose>
</div>
</div>
</body>
</html>