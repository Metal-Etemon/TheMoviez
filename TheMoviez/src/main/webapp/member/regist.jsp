<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="/TheMoviez/resources/js/theMoviez_ck.js"></script>
<link rel="stylesheet" href="/TheMoviez/resources/css/theMoviezStyle.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Blaka+Ink&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Rubik+Dirt&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Nabla&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500;700&display=swap" rel="stylesheet">
<title>The Moviez : 회원 가입</title>
</head>
<body class="body-member">
   <div class="container">
    
    <form method="post" name="member" id="member" action="regist.do"> 
        <table class="member">
            <colgroup>
                <col width="120px">
                <col width="120px">
                <col width="120px">
            </colgroup>
            <tr>
                <th colspan="3">
                    <div class="m_logo" style="text-align: center"><a href="/TheMoviez/" style="font-size: 45pt">The Moviez</a></div>
                </th>
            </tr>
            <tr>
                <td colspan="2">
                    아이디<br>
                    <input type="text" class="box1 swid1" id="m_id" name="m_id" readonly placeholder="중복 검사를 이용해 주세요">
                </td>
                <td class="td4 bold"><br>
                	<input type="button" value="중복 검사" class="box2 swid2" onclick="newwin()">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    비밀번호<br>
                    <input type="password" class="box1 wid1" id="m_passwd" name="m_passwd" placeholder="영문, 숫자 조합 8~16자">
                </td>
            </tr>
            <tr>
                <td colspan="3" class="td1">
                    비밀번호 재확인<br>
                    <input type="password" class="box1 wid1" id="passwd2" name="m_passwd2">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    이름<br>
                    <input type="text" class="box1 wid1" id="m_name" name="m_name" placeholder="한글 이름">
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    성별<br>
                    <select id="m_gender" name="m_gender" class="box1 wid1">
                        <option value="">성별</option>
                        <option value="남자">남자</option>
                        <option value="여자">여자</option>
                    </select>
                </td>
            </tr>
            <tr>
            	<td colspan="3" class="td1">
            		생년월일(선택)<br>
            		<input type="date" id="m_birth" name="m_birth" class="box1 wid1">
            	</td>
            </tr>
            <tr>
                <td colspan="2">
                	연락처<br>
                    <input type="tel" id="m_tel" name="m_tel" class="box1 swid1 phoneNumber" placeholder="010-0000-0000">
                </td>
                <td class="td td4 bold">
                	<br>
                    <input type="button" value="인증번호 받기" class="box2 swid2">
                </td>
            </tr>
            <tr>
                <td colspan="3" class="td1">
                    <input type="text" placeholder="인증번호 입력하세요" class="box1 wid1" id="number" name="number">
                </td>
            </tr>
            <tr>
				<td colspan="2">우편번호<br>
					<input type="text" id="zipNo" name="zipNo" size="5" class="box1 swid1" readonly placeholder="주소 검색을 이용해 주세요">
				</td>
				<td class="td td4 bold"><br>
					<input type="button" value="주소 검색" class="box2 swid2" onClick="goPopup();">
				</td>
			</tr>
			<tr>
				<td colspan="3">주소<br>
					<input type="text"  id="roadAddrPart1" name="roadAddrPart1" size="40" class="box1 wid1" readonly placeholder="주소 검색을 이용해 주세요">
				</td>
			</tr>
			<tr>
				<td colspan="3" class="td1">상세주소<br>
					<input type="text" id="addrDetail" name="addrDetail" size="40" class="box1 wid1">
				</td>
			</tr>
			<tr>
				<td colspan="3">선호 장르
			</tr>
			<tr>
				<td colspan="3" class="td1">
					<input type="checkbox" id="m_like" name="m_like" value="SF"> SF &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="가족"> 가족 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="다큐"> 다큐 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="드라마"> 드라마 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="로맨스"> 로맨스 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="뮤지컬/음악"> 뮤지컬/음악 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="미스터리"> 미스터리 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="범죄/스릴러"> 범죄/스릴러 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="사극"> 사극 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="서부극"> 서부극 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="애니"> 애니 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="액션"> 액션 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="어드벤처"> 어드벤처 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="전쟁"> 전쟁 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="코미디"> 코미디 &nbsp;
					<input type="checkbox" id="m_like" name="m_like" value="판타지"> 판타지 &nbsp;
				</td>
			</tr>
            <tr>
                <td colspan="3" class="bold">
                    <input type="button" value="가입하기" class="box2 wid1" onclick="regist_ck()">
                </td>
            </tr>
            
        </table>
    </form>
</div>
<br>
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