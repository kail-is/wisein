package com.wisein.wiselab.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    //회사 위치 id값
    private int id;

    private String location;
    private String companydata;
    //회사 위치
    private String companyLoc;
    //상호명
    private String companyName;
    //해당 회사 지역 맛집 갯수
    private int matzipCount;
}
