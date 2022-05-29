package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.dto.TipBoardDTO;

import java.util.List;

public interface TipBoardService {

    /* TipBoard 다건조회*/
    public List<TipBoardDTO> selectTipList()  throws Exception;

    /* TipBoard 단건조회*/
    public TipBoardDTO selectTipOne(TipBoardDTO TipBoardDTO) throws Exception;

    /* TipBoard 게시글 등록*/
    public void insertTipBoard(TipBoardDTO TipBoardDTO) throws Exception;


}
