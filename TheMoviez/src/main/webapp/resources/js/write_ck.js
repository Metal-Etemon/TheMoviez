function newsletterWrite_ck() {
	
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

function communityWrite_ck() {
	
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

function eventWrite_ck() {
	
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

function questionWrite_ck() {
	
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

function answerWrite_ck() {
	
	var answer = document.answer;
	
	if (answer.a_passwd.value=="") {
		alert("비밀번호를 입력해 주세요!");
		answer.a_passwd.focus();
		return false;
	}
	
	if (answer.a_title.value=="") {
		alert("제목을 입력해 주세요!");
		answer.a_title.focus();
		return false;
	}
	
	answer.submit();
}