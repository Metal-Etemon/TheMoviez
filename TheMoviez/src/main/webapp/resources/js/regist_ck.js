function newwin() {
	
	window.open("idcheck.jsp", "_blank", "width=500, height=150, left=300, top=200");
}

function regist_ck() {
	
	var member = document.member;
	
	if (member.m_id.value.length < 3 || member.m_id.value.length > 13) {
		alert("아이디는 3~12자 사이로 입력해 주세요.");
		member.m_id.select();
		member.m_id.focus();
		return false;
	}
	
	if (member.m_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		member.m_passwd.focus();
		return false;
	}

		
	if (member.m_passwd2.value != member.m_passwd.value) {
		alert("비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
		member.m_passwd.value="";
		member.m_passwd2.value="";
		member.m_passwd.focus();
		return false;
	}
	
	if (member.m_name.value=="") {
		alert("이름을 입력해 주세요!");
		member.m_name.focus();
		return false;
	}
	
	if (member.m_gender.value=="") {
		alert("성별을 선택해 주세요!");
		member.m_gender.focus();
		return false;
	}
	
	if (member.m_tel.value=="") {
		alert("전화번호를 입력해 주세요!");
		member.m_tel.focus();
		return false;
	}
	
	if (member.zipNo.value=="") {
		alert("우편번호를 입력해 주세요!");
		member.zipNo.focus();
		return false;
	}
	
	if (member.roadAddrPart1.value=="") {
		alert("주소를 입력해 주세요!");
		member.roadAddrPart1.focus();
		return false;
	}
	
	if (member.addrDetail.value=="") {
		alert("상세 주소를 입력해 주세요!");
		member.addrDetail.focus();
		return false;
	}
	
//	var likeCnt = 0;
//	
//	for (var i = 0; i < member.m_like.length; i++) {
//		if (member.m_like[i].checked) {
//			likeCnt++;
//		}
//	}
//	if(likeCnt > 3) {
//		alert("3개까지 체크할 수 있습니다.");
//		return false;
//	}
	
	member.submit();
}

function goPopup(){
	
	window.open("jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadAddrPart1,addrDetail,zipNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.member.roadAddrPart1.value = roadAddrPart1;
		document.member.addrDetail.value = addrDetail;
		document.member.zipNo.value = zipNo;
		
}



