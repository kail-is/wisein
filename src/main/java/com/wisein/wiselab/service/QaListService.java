package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.QaListDTO;

import java.util.List;

public interface QaListService {

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param :
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    public List<QaListDTO> selectQaList() throws Exception;

    /*
     * 작성자 : 이형근
     * QaBoard Insert
     * param : QaListDTO
     * return :
     * 날짜 : 2022-05-14
     * */
    public void insertQaBoard(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * num에 해당하는 qa조회
     * param : QaListDTO
     * return : QaListDTO
     * 날짜 : 2022-05-18
     * */
    public QaListDTO selectQaOne(QaListDTO qaListDTO) throws Exception;


}
