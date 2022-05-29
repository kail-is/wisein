package com.wisein.wiselab.dto;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageDTO {
    private int page = 1;			    // 현재 페이지
    private int perPageNum = 10;	    // 페이지당 데이터 개수
    private String searchType;		    // 검색할 카테고리
    private String keyword;			    // 검색 내용
    private String arayType;		    // 정렬할 컬럼
    private int totalCount;				// 총 개수
    private int totalStartPage = 1;		// 시작 페이지
    private int totalEndPage;			// 마지막 페이지
    private int startPage;				// PageDTO에서 시작 페이지 번호
    private int endPage;				// PageDTO에서 마지막 페이지 번호
    private boolean prev;				// 이전 페이지 번호 목록 이동
    private boolean next;				// 다음 페이지 번호 목록 이동
    private int displayPageNum = 10;	// PageDTO에서 사용자에게 제공하는 한 화면에서 보여줄 페이지 개수
    private int pageCnt;                // 페이지 LIMIT

    @Override
    public String toString() {
        return "PageDTO [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", keyword="
                + keyword + ", arayType=" + arayType + ", totalCount=" + totalCount + ", totalStartPage="
                + totalStartPage + ", totalEndPage=" + totalEndPage + ", startPage=" + startPage + ", endPage="
                + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", pageCnt="
                + pageCnt + "]";
    }

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0) {
            page = 1;
        }
        this.pageCnt = (page*displayPageNum)-displayPageNum;
        this.page = page;
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int perPageNum) {
        if(perPageNum <= 0 || perPageNum > 100) {
            perPageNum = 10; 
        }
        this.perPageNum = perPageNum;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getSearchType() {
        return searchType;
    }
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    public String getArayType() {
        return arayType;
    }
    public void setArayType(String arayType) {
        this.arayType = arayType;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    private void calcData() {
        totalStartPage=1;
        totalEndPage=(int)Math.ceil(totalCount/(double)perPageNum);
        endPage = (int) (Math.ceil(page / (double) displayPageNum) * displayPageNum);
        startPage = endPage - displayPageNum + 1;
        if(totalEndPage < endPage) {
            endPage = totalEndPage;
        }

        if(startPage < 1) {
            startPage = 1;
        }
        if(startPage == 1) {
            prev = false;
        }else {
            prev = true;
        }
        if(endPage == totalEndPage) {
            next = false;
        }else {
            next = true;
        }
    }
    public int getTotalStartPage() {
        return totalStartPage;
    }
    public void setTotalStartPage(int totalStartPage) {
        this.totalStartPage = totalStartPage;
    }
    public int getTotalEndPage() {
        return totalEndPage;
    }
    public void setTotalEndPage(int totalEndPage) {
        this.totalEndPage = totalEndPage;
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
    public int getPageCnt() {
        return pageCnt;
    }

    public String makeSearch() {
        UriComponents u= UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", perPageNum)
                .queryParam("pageCnt", pageCnt)
                .queryParam("searchType", searchType)
                .queryParam("arayType", arayType)
                .queryParam("keyword", keyword)
                .build();
        return u.toUriString();
    }

    public String makeSearch(int page) {
        UriComponents u=UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("perPageNum", perPageNum)
                .queryParam("pageCnt", pageCnt)
                .queryParam("searchType", searchType)
                .queryParam("arayType", arayType)
                .queryParam("keyword", keyword)
                .build();
        return u.toUriString();
    }

    public String makePage(int page) {
        UriComponents u=UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageCnt", pageCnt)
                .queryParam("perPageNum", perPageNum)
                .build();
        return u.toUriString();
    }
}
