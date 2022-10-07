function idcheck_ck() {
	
	var member = document.member;
	var regExp_m_id = /^[a-z]+[a-z0-9]{5,19}$/g; // 영문자로 시작하는 영문자 또는 숫자 6~20자

	if (!regExp_m_id.test(member.m_id.value)) {
		alert("아이디는 영문자로 시작하는 영문자 또는 숫자 6~20자로 사용해야 합니다.");
		member.m_id.focus();
		return false;
	} 
	
	member.submit();
}

