package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.QaListDTO;

import java.util.List;

public interface QaListDAO {

    /*
    * 작성자 : 이형근
    * QA 목록 조회
    * param :
    * return : qaList
    * 날짜 : 2022-04-03
    * */
    public List<QaListDTO> selectQaList() throws Exception;

}