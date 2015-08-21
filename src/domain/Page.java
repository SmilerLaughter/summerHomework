package domain;

import java.util.List;

public class Page<T> {

	private int pageSize = 5;
	private int pageNo;
	private List<T> pageList;
	private int totalPageNumber;
	private long totleContent;
	
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNo() {
		if(pageNo < 1){
			pageNo = 1;
		}
		if(pageNo > getTotalPageNumber()){
			pageNo = getTotalPageNumber();
		}
		
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public List<T> getPageList() {
		return pageList;
	}
	
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	
	public int getTotalPageNumber() {
		totalPageNumber = (int)totleContent/pageSize;
		if (totleContent%pageSize != 0) {
			totalPageNumber++;
			
		}
		return totalPageNumber;
	}
	
	public void setTotalPageNumber(int totlePageNumber) {
		this.totalPageNumber = totlePageNumber;
	}
	
	public long getTotleContent() {
		return totleContent;
	}
	
	public void setTotleContent(long totleContent) {
		this.totleContent = totleContent;
	}
	
	public Page( int pageNo) {
		super();
	
		this.pageNo = pageNo;
	}
	
	

	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", pageList=" + pageList + ", totlePageNumber="
				+ totalPageNumber + ", totleContent=" + totleContent + "]";
	}
	
	public  boolean isHasNext(){
		if(getPageNo() < getTotalPageNumber()){
			return true;
		}else {
			return false;
		}
	}
	 public boolean isHasPrev(){
		 if(getPageNo() > 1){
			 return true;
		 }else{
			 return false;
		 }
	 }
	public  long getPrePage(){
		 if(isHasPrev()){
			 return getPageNo() - 1;
		 }else{
			 return getPageNo();
		 }
	 }
	
	public long getNextPage(){
		if(isHasNext()){
			return getPageNo() + 1;
		}else{
			return getPageNo();
		}
	}
	
	
	
}
