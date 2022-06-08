package com.wisein.wiselab.dto;

import com.wisein.wiselab.common.paging.Criteria;
import com.wisein.wiselab.common.paging.PaginationInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDTO extends Criteria{
	private PaginationInfo paginationInfo;

}
