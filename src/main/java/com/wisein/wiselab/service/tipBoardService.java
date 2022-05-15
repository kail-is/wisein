package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.TipBoardDTO;

import java.util.List;

public interface tipBoardService {

    /*
     * 작성자 : 주한나
     * tip board list 조회
     * param :
     * return : tipBoardDTO
     * 날짜 : 2022-05-15
     * */
    public List<TipBoardDTO> selectTipBoard() throws Exception;

    /*
     * 작성자 : 주한나
     * tipBoard Insert
     * param : tipBoardDTO
     * return :
     * 날짜 : 2022-05-15
     * */
    public void insertTipBoard(TipBoardDTO tipBoardDTO) throws Exception;
}