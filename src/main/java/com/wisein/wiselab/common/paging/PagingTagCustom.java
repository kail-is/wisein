package com.wisein.wiselab.common.paging;

import org.springframework.stereotype.Service;

@Service("PagingTagCustom")
public class PagingTagCustom extends AbstractPagingCustom {

	@Override
	String getFirstPagetag() {
		return "<a href='{0}'>&laquo;</a>";
	}

	@Override
	String getPreviousPagetag() {
		return "<a href='{0}'>&lt;</a>";
	}

	@Override
	String getPageNumTag() {
		return "<a href='{0}' class='{1}'>{2}</a>";
	}

	@Override
	String getNextPagetag() {
		return "<a href='{0}'>&gt;</a>";
	}

	@Override
	String getLastPagetag() {
		return "<a href='{0}'>&raquo;</a>";
	}
	
}
