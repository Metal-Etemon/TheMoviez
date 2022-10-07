function newsletterDelete_ck() {
	
	var newsletter = document.newsletter;
	if (confirm("정말 삭제하시겠습니까?")) {
		if (newsletter.news_passwd.value=="") {			// newsletter.m_passwd.value.lenght==0
			alert("비밀번호를 입력해 주세요!");
			newsletter.news_passwd.focus();
			return false;
		}
		newsletter.submit();
	} else {
		history.back();
	}
	
}

function communityDelete_ck() {
	
	var community = document.community;
	if (confirm("정말 삭제하시겠습니까?")) {
		if (community.com_passwd.value=="") {			// community.m_passwd.value.lenght==0
			alert("비밀번호를 입력해 주세요!");
			community.com_passwd.focus();
			return false;
		}
		community.submit();
	} else {
		history.back();
	}
	
}

function eventDelete_ck() {
	
	var event = document.event;
	if (confirm("정말 삭제하시겠습니까?")) {
		if (event.ev_passwd.value=="") {			
			alert("비밀번호를 입력해 주세요!");
			event.ev_passwd.focus();
			return false;
		}
		event.submit();
	} else {
		history.back();
	}
	
}

function questionDelete_ck() {
	
	var question = document.question;
	if (confirm("정말 삭제하시겠습니까?")) {
		if (question.q_passwd.value=="") {			
			alert("비밀번호를 입력해 주세요!");
			question.q_passwd.focus();
			return false;
		}
		question.submit();
	} else {
		history.back();
	}
	
}