//Hide Header on on scroll down
var didScroll;
var lastScrollTop = 0;
var delta = 5;
var navbarHeight = $('header').outerHeight();

$(window).scroll(function(event) {
	didScroll = true;
});

setInterval(function() {
	if (didScroll) {
		hasScrolled();
		didScroll = false;
	}
}, 200);

function hasScrolled() {
	var st = $(this).scrollTop();

	// Make sure they scroll more than delta
	if (Math.abs(lastScrollTop - st) <= delta)
		return;

	// If they scrolled down and are past the navbar, add class .nav-up.
	// This is necessary so you never see what is "behind" the navbar.
	if (st > lastScrollTop && st > navbarHeight) {
		// Scroll Down
		$('header').removeClass('nav-down').addClass('nav-up');
	} else {
		// Scroll Up
		if (st + $(window).height() < $(document).height()) {
			$('header').removeClass('nav-up').addClass('nav-down');
		}
	}

	lastScrollTop = st;
}




/* 회원 가입 규정*/

function checkTermsAll() {
	// 전체 체크박스
	const checkboxes
		= document.querySelectorAll('input[name="terms"]');
	// 선택된 체크박스
	const checked
		= document.querySelectorAll('input[name="terms"]:checked');
	// select all 체크박스
	const termsAll
		= document.querySelector('input[name="termsall"]');

	if (checkboxes.length === checked.length) {
		termsAll.checked = true;
	} else {
		termsAll.checked = false;
	}

}

function termsAll(termsAll) {
	const checkboxes
		= document.getElementsByName('terms');

	checkboxes.forEach((checkbox) => {
		checkbox.checked = termsAll.checked
	})
}


function terms_ck() {

	var termsform = document.termsform;

	if (!termsform.termsall.checked) {
		alert("약관에 동의해 주세요!");
		return false;
	}

	termsform.submit();
}





/* 아이디 중복 확인 새 창*/


function newwin() {

	window.open("/TheMoviez/member/idCheck.jsp", "_blank", "width=500, height=200, left=300, top=200");
}

function idCheck_ck() {

	var member = document.member;
	var regExp_m_id = /^[a-z]+[a-z0-9]{5,19}$/g; // 영문자로 시작하는 영문자 또는 숫자 6~20자

	if (!regExp_m_id.test(member.m_id.value)) {
		alert("아이디는 영문자로 시작하는 영문자 또는 숫자 6~20자로 사용해야 합니다.");
		member.m_id.select();
		member.m_id.focus();
		return false;
	}

	member.submit();
}


function regist_ck() {

	var member = document.member;
	var regExp_m_passwd = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/; // 8~16자 영문, 숫자 조합
	var regExp_m_id = /^[a-z]+[a-z0-9]{5,19}$/g; // 영문자로 시작하는 영문자 또는 숫자 6~20자
	var regExp_m_name = /^[가-힣]{2,8}$/; //한글 이름 2~8자 이내
	//	var regExp_m_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //이메일 정규식
	var regExp_m_tel = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/; // 핸드폰 번호 정규식


	if (!regExp_m_id.test(member.m_id.value)) {
		alert("아이디는 영문자로 시작하는 영문자 또는 숫자 6~20자로 사용해야 합니다.");
		member.m_id.select();
		member.m_id.focus();
		return false;
	}


	if (member.m_passwd.value == "") {
		alert("비밀번호를 입력해 주세요!");
		member.m_passwd.focus();
		return false;
	}

	if (!regExp_m_passwd.test(member.m_passwd.value)) {
		alert("비밀번호는 8~16자 영문, 숫자 조합으로 사용해야 합니다.");
		member.m_passwd.select();
		member.m_passwd.focus();
		return false;
	}


	if (member.m_passwd2.value != member.m_passwd.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
		member.m_passwd.value = "";
		member.m_passwd2.value = "";
		member.m_passwd.focus();
		return false;
	}

	if (member.m_name.value == "") {
		alert("이름을 입력해 주세요!");
		member.m_name.focus();
		return false;
	}

	if (!regExp_m_name.test(member.m_name.value)) {
		alert("이름은 한글 2~8자 이내로 사용해야 합니다.");
		member.m_name.select();
		member.m_name.focus();
		return false;
	}


	if (member.m_gender.value == "") {
		alert("성별을 선택해 주세요!");
		member.m_gender.focus();
		return false;
	}


	if (member.m_tel.value == "") {
		alert("핸드폰 번호를 입력해 주세요!");
		member.m_tel.focus();
		return false;
	}

	if (!regExp_m_tel.test(member.m_tel.value)) {
		alert("핸드폰 번호가 올바르지 않습니다.");
		member.m_tel.select();
		member.m_tel.focus();
		return false;
	}

	if (member.zipNo.value == "") {
		alert("우편번호를 입력해 주세요!");
		member.zipNo.focus();
		return false;
	}

	if (member.roadAddrPart1.value == "") {
		alert("주소를 입력해 주세요!");
		member.roadAddrPart1.focus();
		return false;
	}

	if (member.addrDetail.value == "") {
		alert("상세 주소를 입력해 주세요!");
		member.addrDetail.focus();
		return false;
	}

	// 이메일
	//	if (!regExp_m_email.test(member.m_email.value)) {
	//		alert("이메일 형식이 올바르지 않습니다.");
	//		member.m_email.select();
	//		member.m_email.focus();
	//		return false;
	//	}


	member.submit();
	alert("♥회원 가입을 축하드립니다!♥");
}

function goPopup() {

	window.open("jusoPopup.jsp", "pop", "width=570,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadAddrPart1, addrDetail, zipNo) {
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.member.roadAddrPart1.value = roadAddrPart1;
	document.member.addrDetail.value = addrDetail;
	document.member.zipNo.value = zipNo;

}


/* 로그인 */
function login_ck() {
	var login = document.getElementById("login");
	var m_id = document.getElementById("m_id");
	var m_passwd = document.getElementById("m_passwd");

	if (m_id.value == "" || m_passwd == "") {
		alert("아이디와 비밀번호를 입력해 주세요!");
		m_id.focus();
		return false;
	}

	login.submit();
}


/* 로그아웃 */
function logout_ck() {

	if (confirm("로그아웃 하시겠습니까?")) {
		location.href = "/TheMoviez/login/logout.do";
	}
}

/* 회원 정보 수정 */
function memberEdit_ck(){
	
	if (confirm("수정 하시겠습니까?")) {
		alert('수정 완료!');
		document.getElementById('member').submit();
	}
}


/* 비밀번호 변경 시 비밀번호 비교 */
function memberEditPw_ck() {

	var m_passwd = document.getElementById("m_passwd");
	var m_passwd_now = document.getElementById("m_passwd_now");
	var m_passwd_new = document.getElementById("m_passwd_new");
	var m_passwd_new2 = document.getElementById("m_passwd_new2");

	if (m_passwd_now.value == "") {
		alert("현재 비밀번호를 입력해 주세요!");
		m_passwd_now.focus();
		return false;
	}

	if (m_passwd_now.value != m_passwd.value) {
		alert("현재 비밀번호가 다릅니다!");
		m_passwd_new2.value == "";
		m_passwd_new.value == "";
		m_passwd_now.value == "";
		m_passwd_now.focus();
		return false;
	}

	if (m_passwd_new.value == "") {
		alert("새 비밀번호를 입력해 주세요!");
		m_passwd_new.focus();
		return false;
	}

	if (m_passwd_new2.value == "") {
		alert("새 비밀번호를 확인해 주세요!");
		m_passwd_new2.focus();
		return false;
	}

	if (m_passwd_new.value != m_passwd_new2.value) {
		alert("새 비밀번호가 일치하지 않습니다.");
		m_passwd_new.value == "";
		m_passwd_new2.value == "";
		return false;
	}

	alert("비밀번호가 변경되었습니다.");
	document.getElementById("member").submit();

}


/* 회원 탈퇴 비밀번호 확인 */
function memberDelete_ck() {

	var m_id = document.getElementById("m_id");
	var m_passwd = document.getElementById("m_passwd");
	var m_passwd_ck = document.getElementById("m_passwd_ck");

	if (confirm("정말 탈퇴하시겠습니까?")) {
		if (m_id.value != "admin") {
			if (m_passwd.value != m_passwd_ck.value) {
				alert("비밀번호를 확인해 주세요!");
				return false;
			} else {
				document.getElementById("member").submit();
				alert("회원 탈퇴하셨습니다. T_T");
			}

		} else if (m_id.value == "admin") {
			document.getElementById("member").submit();
			alert("회원 탈퇴하셨습니다. T_T");
		}


	}
}



/* 영화 검색 */
function moviesearch_ck() {

	var movie = document.getElementById("movie");

	if (movie.value == "") {
		alert("영화 제목을 입력해 주세요.");
		movie.focus();
		return false;
	}

	document.getElementById("moviesearch").submit();
}

/* 리뷰 작성 */
function revWrite_ck() {
	var content = document.getElementById("rev_content");

	if (content.value == "") {
		alert("내용을 작성해 주세요!");
		return false;
	}

	document.getElementById("review").submit();
}

/* 리뷰 삭제 */
function revDel_ck() {

	if (confirm("삭제하시겠습니까?") == true) {
		document.getElementById('reviewdel').submit();
		alert("삭제되었습니다.");
	} else {
		return false;
	}
}


/* 뉴스레터 작성 및 수정 */
function newsWrite_ck() {
	var title = document.getElementById("news_title");
	var content = document.getElementById("news_content");

	if (title.value == "") {
		alert("제목을 입력해 주세요!");
		title.focus();
		return false;
	}
	if (content.value == "") {
		alert("내용을 입력해 주세요!");
		content.focus();
		return false;
	}

	document.getElementById("newsletter").submit();
}

/* 뉴스레터 삭제 */
function newsDelete_ck() {
	if (confirm("정말 삭제하시겠습니까?") == true) {
		document.getElementById("newsletter").submit();
		alert("삭제되었습니다.");
	}
}



/* 커뮤니티 작성 및 수정 */
function comWrite_ck() {
	var title = document.getElementById("com_title");
	var content = document.getElementById("com_content");

	if (title.value == "") {
		alert("제목을 입력해 주세요!");
		title.focus();
		return false;
	}
	if (content.value == "") {
		alert("내용을 입력해 주세요!");
		content.focus();
		return false;
	}

	document.getElementById("community").submit();
}

/* 커뮤니티 삭제 */
function comDelete_ck() {
	if (confirm("정말 삭제하시겠습니까?") == true) {
		document.getElementById("community").submit();
		alert("삭제되었습니다.");
	}
}



/* Q&A 작성 */
function qnaWrite_ck() {
	var title = document.getElementById("qna_title");
	var passwd = document.getElementById("qna_passwd");
	var content = document.getElementById("qna_content");

	if (passwd.value == "") {
		alert("비밀번호를 입력해 주세요!");
		passwd.focus();
		return false;
	}

	if (title.value == "") {
		alert("제목을 입력해 주세요!");
		title.focus();
		return false;
	}

	if (content.value == "") {
		alert("내용을 입력해 주세요!");
		content.focus();
		return false;
	}

	document.getElementById("qna").submit();
}

/* Q&A 삭제 */
function qnaDelete_ck() {

	var passwd = document.getElementById("qna_passwd");
	var m_id = document.getElementById("m_id");

	if (confirm("정말 삭제하시겠습니까?") == true) {
		
		if (m_id.value == 'admin') {
			document.getElementById("qna").submit();
			alert("삭제되었습니다.");
		}
		
		if (m_id.value != 'admin') {
			var ck = prompt('작성 시 비밀번호를 입력해 주세요.', '');
			if (ck == passwd.value) {
				document.getElementById("qna").submit();
				alert("삭제되었습니다.");
			} else {
				alert("비밀번호가 올바르지 않습니다.");
				return false;
			}
		}

	}
}


/* Q&A 수정 */
function qnaEdit_ck() {
	var title = document.getElementById("qna_title");
	var passwd = document.getElementById("qna_passwd");
	var passwd_ck = document.getElementById("qna_passwd_ck");
	var content = document.getElementById("qna_content");

	if (passwd_ck.value == "") {
		alert("비밀번호를 입력해 주세요!");
		passwd.focus();
		return false;
	}

	if (title.value == "") {
		alert("제목을 입력해 주세요!");
		title.focus();
		return false;
	}

	if (content.value == "") {
		alert("내용을 입력해 주세요!");
		content.focus();
		return false;
	}

	if (passwd_ck.value != passwd.value) {
		alert("비밀번호가 일치하지 않습니다!\n게시글 작성 시 사용했던 비밀번호를 입력해 주세요!");
		return false;
	}

	document.getElementById("qna").submit();
}






/* 게시물 및 회원 검색 */
function search_ck() {
	var method = document.getElementById('method');
	var keyword = document.getElementById('keyword');

	if (method.value == "") {
		alert('검색 방법을 선택해 주세요.');
		method.focus();
		return false;
	}

	if (keyword.value == "") {
		alert("검색할 내용을 입력해 주세요.");
		keyword.focus();
		return false;
	}

	document.getElementById('searchPost').submit();
}



/* 댓글 작성 */
function cmtWrite_ck() {
	var content = document.getElementById("cmt_content");

	if (content.value == "") {
		alert("내용을 입력해 주세요!");
		content.focus();
		return false;
	}

	document.getElementById("comment").submit();
}


/*댓글 삭제*/
function cmtDel_ck() {

	var cmtDel = document.getElementById('cmtDel');

	var conf = confirm("댓글을 삭제하시겠습니까?");
	if (conf == true) {
		alert("댓글이 삭제되었습니다.");
		cmtDel.submit();
	} else {
		return false;
	}


}


