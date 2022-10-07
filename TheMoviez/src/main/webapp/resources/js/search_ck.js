function search_ck() {
	
	var search = document.search;
	
	if (search.method.value=="") {
		alert("검색 방법을 선택하세요!");
		search.method.focus();
		return false;
	}
	
	if (search.keyword.value=="") {
		alert("검색할 내용을 입력하세요!");
		search.keyword.focus();
		return false;
	}
	search.submit();
}