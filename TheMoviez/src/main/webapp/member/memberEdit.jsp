<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The Moviez : 회원 정보 수정</title>
</head>
<body class="body-memberinfo">
	<header class="m_header">
		<c:import url="/header.jsp"></c:import>
	</header>
	<main style="padding: 220px">
		<div class="container-memberinfo">
		<h1>회원 정보 수정</h1>
		<br>
			<form action="memberEdit.do" id="member" name="member" method="post">
				<input type="hidden" id="m_num" name="m_num"
					value="${memberInfo.m_num}">
				<table class="memberinfo">
					<tr height="60">
						<td class="memberinfo-td1">아이디</td>
						<td>${memberInfo.m_id}</td>
						<td class="memberinfo-td1">회원번호</td>
						<td>${memberInfo.m_num}</td>
					</tr>
					<tr height="60">
						<td class="memberinfo-td1">비밀번호</td>
						<td colspan="3"><input type="submit" value="비밀번호 변경"
							onclick="javascript: form.action='memberEditPwUi.do'"
							class="box2 board-box"></td>
					</tr>
					<tr>
						<td class="memberinfo-td1">이름</td>
						<td><input type="text" class="box1 wid1" id="m_name"
							name="m_name" value="${memberInfo.m_name}"></td>
						<td class="memberinfo-td1">가입일</td>
						<td>${memberInfo.m_date}</td>
					</tr>
					<tr>
						<td class="memberinfo-td1">성별</td>
						<td><select id="m_gender" name="m_gender" class="box1 wid1">
								<option value=""
									<c:if test="${memberInfo.m_gender==''}"> selected </c:if>>성별</option>
								<option value="남자"
									<c:if test="${memberInfo.m_gender=='남자'}"> selected </c:if>>남자</option>
								<option value="여자"
									<c:if test="${memberInfo.m_gender=='여자'}"> selected </c:if>>여자</option>
						</select></td>
						<td class="memberinfo-td1">생년월일</td>
						<td><input type="date" id="m_birth" name="m_birth"
							class="box1 wid1" value="${memberInfo.m_birth}"></td>
					</tr>
					<tr>
						<td class="memberinfo-td1">연락처</td>
						<td colspan="3"><input type="tel" id="m_tel" name="m_tel"
							class="box1 phoneNumber" value="${memberInfo.m_tel}"></td>
					</tr>
					<tr>
						<td class="memberinfo-td1">우편번호</td>
						<td colspan="3"><input type="text" id="zipNo" name="zipNo"
							size="5" class="box1" value="${memberInfo.m_zipcode}" readonly> <input
							type="button" value="주소 검색" class="box2 board-box"
							onClick="goPopup();"></td>
					</tr>
					<tr>
						<td class="memberinfo-td1">주소</td>
						<td colspan="3"><input type="text" id="roadAddrPart1"
							name="roadAddrPart1" size="40" class="box1"
							value="${memberInfo.m_addr}" readonly> &nbsp;&nbsp;&nbsp;&nbsp; <input
							type="text" id="addrDetail" name="addrDetail" size="40"
							class="box1" value="${memberInfo.m_addrdetail}"></td>
					</tr>
					<tr height="60">
						<td class="memberinfo-td1">선호 장르</td>
						<td colspan="3"><input type="checkbox" id="m_like"
							name="m_like" value="SF"
							<c:if test="${fn:contains(memberInfo.m_like, 'SF')}"> checked </c:if>>
							SF &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="가족"
							<c:if test="${fn:contains(memberInfo.m_like, '가족')}"> checked </c:if>>
							가족 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="다큐"
							<c:if test="${fn:contains(memberInfo.m_like, '다큐')}"> checked </c:if>>
							다큐 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="드라마"
							<c:if test="${fn:contains(memberInfo.m_like, '드라마')}"> checked </c:if>>
							드라마 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="로맨스"
							<c:if test="${fn:contains(memberInfo.m_like, '로맨스')}"> checked </c:if>>
							로맨스 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="뮤지컬/음악"
							<c:if test="${fn:contains(memberInfo.m_like, '뮤지컬/음악')}"> checked </c:if>>
							뮤지컬/음악 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="미스터리"
							<c:if test="${fn:contains(memberInfo.m_like, '미스터리')}"> checked </c:if>>
							미스터리 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="범죄/스릴러"
							<c:if test="${fn:contains(memberInfo.m_like, '범죄/스릴러')}"> checked </c:if>>
							범죄/스릴러 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="사극"
							<c:if test="${fn:contains(memberInfo.m_like, '사극')}"> checked </c:if>>
							사극 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="서부극"
							<c:if test="${fn:contains(memberInfo.m_like, '서부극')}"> checked </c:if>>
							서부극 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="애니"
							<c:if test="${fn:contains(memberInfo.m_like, '애니')}"> checked </c:if>>
							애니 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="액션"
							<c:if test="${fn:contains(memberInfo.m_like, '액션')}"> checked </c:if>>
							액션 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="어드벤처"
							<c:if test="${fn:contains(memberInfo.m_like, '어드벤처')}"> checked </c:if>>
							어드벤처 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="전쟁"
							<c:if test="${fn:contains(memberInfo.m_like, '전쟁')}"> checked </c:if>>
							전쟁 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="코미디"
							<c:if test="${fn:contains(memberInfo.m_like, '코미디')}"> checked </c:if>>
							코미디 &nbsp; <input type="checkbox" id="m_like" name="m_like"
							value="판타지"
							<c:if test="${fn:contains(memberInfo.m_like, '판타지')}"> checked </c:if>>
							판타지 &nbsp;</td>
					</tr>
					<tr height="60">
						<td class="memberinfo-td1">IP 주소</td>
						<td colspan="3">${memberInfo.m_ip}</td>
					</tr>
				</table>
				<div class="div-center">
					<input type="button" value="수정" onclick="memberEdit_ck()" class="box2 box3 board-box">
				</div>
			</form>
		</div>
	</main>
	<footer>
		<c:import url="/footer.jsp" />
	</footer>
</body>
<script>
	// 날짜 제한
	var now_utc = Date.now()
	var timeOff = new Date().getTimezoneOffset()*60000;
	var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
	document.getElementById("m_birth").setAttribute("max", today);
	
	// 하이픈(-) 자동 입력
	$(document).on("keyup", ".phoneNumber", function() {
		$(this).val($(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/, "$1-$2-$3").replace("--", "-"));
	});
</script>
</html>