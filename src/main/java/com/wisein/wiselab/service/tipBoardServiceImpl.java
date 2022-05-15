package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.TipBoardDAO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class tipBoardServiceImpl implements tipBoardService {

    @Autowired
    private TipBoardDAO dao;

    /*
     * 작성자 : 주한나
     * tipBoard 목록 조회
     * param :
     * return : qaList
     * 날짜 : 2022-05-15
     * */
    @Override
    public  List<TipBoardDTO> selectTipBoard() throws Exception{
        return dao.selectTipBoard();
    }
    /*
     * 작성자 : 주한나
     * tipBoard Insert
     * param : tipBoard
     * return :
     * 날짜 : 2022-05-15
     * */
    @Override
    public void insertTipBoard(TipBoardDTO tipBoardDTO) throws Exception {
        dao.insertTipBoard(tipBoardDTO);
    }
}
