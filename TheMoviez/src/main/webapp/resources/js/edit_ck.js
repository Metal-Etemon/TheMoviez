function newsletterEdit_ck() {
	
	var newsletter = document.newsletter;
	
	if (newsletter.news_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		newsletter.news_passwd.focus();
		return false;
	}
	
	if (newsletter.news_title.value=="") {
		alert("제목을 입력해 주세요!");
		newsletter.news_title.focus();
		return false;
	}
	
	newsletter.submit();
}

function communityEdit_ck() {
	
	var community = document.community;
	
	if (community.com_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		community.com_passwd.focus();
		return false;
	}
	
	if (community.com_title.value=="") {
		alert("제목을 입력해 주세요!");
		community.com_title.focus();
		return false;
	}
	
	community.submit();
}

function eventEdit_ck() {
	
	var event = document.event;
	
	if (event.ev_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		event.ev_passwd.focus();
		return false;
	}
	
	if (event.ev_title.value=="") {
		alert("제목을 입력해 주세요!");
		event.ev_title.focus();
		return false;
	}
	
	event.submit();
}

function questionyEdit_ck() {
	
	var question = document.question;
	
	if (question.q_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		question.q_passwd.focus();
		return false;
	}
	
	if (question.q_title.value=="") {
		alert("제목을 입력해 주세요!");
		question.q_title.focus();
		return false;
	}
	
	question.submit();
}