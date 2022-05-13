package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.QaListDAO;
import com.wisein.wiselab.dto.QaListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QaListServiceImpl implements QaListService {

    @Autowired
    private QaListDAO dao;

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param :
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    @Override
    public List<QaListDTO> selectQaList() throws Exception {
        return dao.selectQaList();
    }
}
