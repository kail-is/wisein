package com.wisein.wiselab.dto;

import com.wisein.wiselab.common.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegrationSearchDTO extends PaginationInfo {

	private int num;
	private String category;
	private String writer;
	private String subject;
	private String content;
	private String regDate;
	private String updDate;
	private String adpYn;
	private String delYn;
	private String boardType;
	private int parentNum;
}
