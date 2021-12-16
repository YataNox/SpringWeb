package com.ezen.shop.dto;

public class Paging {
	// Variable----------------------------------------------------------------
	private int page = 1; // 현재 화면에 표시할 페이지 번호
	private int totalCount; // 게시물 전체의 갯수
	
	private int displayRow = 10; // 한 페이지에 보일 게시물의 수
	private int  displayPage = 10; // 이전과 다음 버튼 사이의 보일 페이지의 수
	
	private int  beginPage; // 표시될 시작페이지 번호 1 or 11 or 21 or 31...
	private int  endPage; // 표시될 끝페이지 번호 10 or 20 or 30 or 40...
	
	private boolean prev; // prev 버튼이 보일건지 안보일 건지(true/false)
	private boolean next; // next 버튼이 보일건지 안보일 건지(true/false)
	
	private int startNum; // 현재 페이지에 표시될 게시물 번호의 시작 번호
	private int endNum; // 현재 페이지에 표시될 게시물 번호의 끝 번호
	
	private void Paging() {
		// 각 멤버변수 값을 세팅합니다.
		double temp = page / (double)displayPage; // 1 / 10.0 -> 0.1
		temp = Math.ceil(temp); // 소수점 첫 자리 올림 연산
		endPage = (int)(temp * displayPage); // 화면에 표시할 페이지 숫자를 곱셈
		// endPage = ((int)(Math.ceil(page/(double)displayPage))) * displayPage;
		beginPage = endPage - displayPage + 1;
		
		int totalPage = (int)Math.ceil(totalCount / (double)displayRow);
		// 총 게시물 수를 한 화면에 표시한 게시물 수로 나눠서 총 페이지 수를 계산
		
		if(totalPage < endPage) { // 총 페이지가 현재 endPage보다 작다면
			endPage = totalPage; // endPage를 총 페이지수로 대체하고
			next = false; // 다음 버튼은 표시하지 않는 것으로 설정
		}else { // endPage 뒤로 페이지가 더 있으니. next를 표시하는 것으로 설정
			next = true;
		}
			
		prev = (beginPage == 1)? false : true; // 시작페이지가 1인 경우만 표시 안함
		
		startNum = (page - 1) * displayRow + 1;
		// 현재 화면의 시작 게시물 번호
		endNum = page * displayRow;
		// 현재 화면의 끝 게시물 번호
		
		// System.out.println("beginPage : " + beginPage + ", endPage : " + endPage);
		// System.out.println("totalPage : " + totalPage + ", prev : " + prev + ", next : " + next);
		// System.out.println("startNum : " + startNum + ", endNum : " + endNum);
	}
		
	// Getter/Setter---------------------------------------------------------
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		Paging();
	}
	public int getDisplayRow() {
		return displayRow;
	}
	public void setDisplayRow(int displayRow) {
		this.displayRow = displayRow;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
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
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
}
