package com.wisein.wiselab.config.interceptor;

public enum EsideYn {

	//주소를 등록하시오
	main("Y");

	private final String sideYn;

	EsideYn(String sideYn) { this.sideYn = sideYn;}

	public String getSideYn() { return sideYn; }

}
