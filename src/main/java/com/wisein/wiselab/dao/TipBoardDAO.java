package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.TipBoardDTO;

import java.util.List;

public interface TipBoardDAO {

    /*
     * 작성자 : 주한나
     * tip board 목록 조회
     * param :
     * return :
     * 날짜 : 2022-05-15
     * */
    public List<TipBoardDTO> selectTipBoard() throws Exception;

    /*
     * 작성자 : 주한나
     * TipBoar Insert
     * param :
     * return :
     * 날짜 : 2022-05-15
     * */
    public void insertTipBoard(TipBoardDTO tipBoardDTO) throws Exception;


}