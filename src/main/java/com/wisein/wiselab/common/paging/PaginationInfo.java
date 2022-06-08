package com.wisein.wiselab.common.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationInfo {
	private Criteria criteria;
	
	private int totalRecordCount;
	private int totalPageCount;
	private int firstPage;
	private int lastPage;
	private int firstRecordIndex;
	private int lastRecordIndex;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	
	public PaginationInfo(Criteria criteria) {
		if (criteria.getCurrentPageNo() < 1) {
			criteria.setCurrentPageNo(1);
		}
		if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
			criteria.setRecordsPerPage(10);
		}
		if (criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
			criteria.setPageSize(10);
		}

		this.criteria = criteria;
	}


	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;

		if (totalRecordCount > 0) {
			calculation();
		}
	}

	
	private void calculation() {
		totalPageCount = (int)Math.ceil(totalRecordCount / (double)criteria.getRecordsPerPage());
		
		if (criteria.getCurrentPageNo() > totalPageCount) {
			criteria.setCurrentPageNo(totalPageCount);
		}

		firstPage = (((criteria.getCurrentPageNo() - 1) / criteria.getPageSize()) * criteria.getPageSize()) + 1;

		lastPage = criteria.getCurrentPageNo() - (criteria.getCurrentPageNo() % criteria.getPageSize()) + criteria.getPageSize();
		
		if (lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		firstRecordIndex = (criteria.getCurrentPageNo() - 1) * criteria.getRecordsPerPage();

		lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();

		hasPreviousPage = firstPage == 1 ? false : true;

		hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount ? true : false;
	
	}
}
