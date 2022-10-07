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

<title>The Moviez : 회원 가입 약관</title>

</head>
<body class="body-terms">
	<div class="container-terms">
		<form action="/TheMoviez/member/regist.jsp" id="termsform" name="termsform" method="post">
			<table class="terms">
				<tr>
               		<th colspan="2" class="th">
                    	<div class="m_logo" style="text-align: center"><a href="/TheMoviez/" style="font-size: 45pt">The Moviez</a></div>
                	</th>
            	</tr>
				<tr>
					<td colspan="2" class="terms-td1">이용약관 안내</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea class="terms-box" readonly><%@ include file="terms1.jsp" %></textarea>
					</td>
				</tr>
				<tr>
					<td><label for="terms1">(필수)이용약관에 대해 동의합니다.</label></td>
					<td><input type="checkbox" name="terms" id="terms1" onclick="checkTermsAll()"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2" class="terms-td1">개인정보 수집 및 이용 안내</td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea class="terms-box" readonly><%@ include file="terms2.jsp" %></textarea>
					</td>
				</tr>
				<tr>
					<td><label for="terms2">(필수)개인정보 수집 및 이용 동의에 동의합니다.</label></td>
					<td><input type="checkbox" name="terms" id="terms2" onclick="checkTermsAll()"></td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td><label for="termsall">회원가입 약관에 모두 동의합니다.</label></td>
					<td><input type="checkbox" id="termsall" name="termsall" onclick="termsAll(this)"></td>
				</tr>
				<tr>
                <td colspan="2" class="td bold">
                    <input type="button" value="가입하기" class="box2 wid1" onclick="terms_ck()">
                </td>
            </tr>
			</table>
		</form>
	</div>
<footer>
	<c:import url="/footer.jsp" />
</footer>
</body>
<script>
	function checkTermsAll()  {
		  // 전체 체크박스
		  const checkboxes 
		    = document.querySelectorAll('input[name="terms"]');
		  // 선택된 체크박스
		  const checked 
		    = document.querySelectorAll('input[name="terms"]:checked');
		  // select all 체크박스
		  const termsAll 
		    = document.querySelector('input[name="termsall"]');
		  
		  if(checkboxes.length === checked.length)  {
		    termsAll.checked = true;
		  }else {
		    termsAll.checked = false;
		  }
	
		}
	
		function termsAll(termsAll)  {
		  const checkboxes 
		     = document.getElementsByName('terms');
		  
		  checkboxes.forEach((checkbox) => {
		    checkbox.checked = termsAll.checked
		  })
		}
</script>
</html>