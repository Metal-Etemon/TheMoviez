package themoviez.page;

public class PageMaker {
	
	private PageVO pageVO;
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int displayPageNum = 10;
	
	public PageVO getPageVO() {
		return pageVO;
	}
	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}
	
	private void calcData() {
		// 화면에 보여 줄 끝 페이지 번호 = (현재 페이지 번호 / 화면에 보여질 페이지 번호의 개수) * 화면에 보여질 페이지 번호의 개수
		endPage = (int) (Math.ceil(pageVO.getPage() / (double) displayPageNum) * displayPageNum);
		// 마지막 페이지 번호 = 총 게시글 수 / 한 페이지당 보여 줄 게시글 개수
		int tempEndPage = (int) (Math.ceil(totalCount / (double) pageVO.getPerPageNum()));
		// 화면에 보여줄 끝 페이지의 번호가 실제 마지막 페이지 번호보다 클 경우
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// 시작 페이지 번호 = (끝 페이지 번호 - 화면에 보여질 페이지 번호의 개수) + 1
		startPage = (endPage - displayPageNum) + 1;
		if (startPage <= 0) startPage = 1;
		
		// 이전 버튼은 시작 페이지 번호가 1이 아니면 생김
		prev = startPage == 1 ? false : true;
		// 다음 버튼 생성 여부 
		next = endPage * pageVO.getPerPageNum() < totalCount ? true : false;
		
	}
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getDisplayPageNum() {
		return displayPageNum;
	}
	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}
	
	
	

}
