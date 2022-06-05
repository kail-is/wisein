package com.wisein.wiselab.service;

import com.wisein.wiselab.common.paging.PaginationInfo;
import com.wisein.wiselab.dao.QaListDAO;
import com.wisein.wiselab.dto.QaListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QaListServiceImpl implements QaListService {

    @Autowired
    private QaListDAO dao;

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param : QaListDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * 수정자 : 박혜림
     * 수정일 : 2022-06-04
     * */
    @Override
    public List<QaListDTO> selectQaList(QaListDTO qaListDTO) throws Exception {
        List<QaListDTO> qaList = new ArrayList<>();

        int boardTotalCount = dao.selectBoardTotalCount(qaListDTO);
        
        PaginationInfo paginationInfo = new PaginationInfo(qaListDTO);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        
        qaListDTO.setPaginationInfo(paginationInfo);

        if(boardTotalCount > 0) {
        	qaList = (List<QaListDTO>) dao.selectQaList(qaListDTO);
        }
    	
        return qaList;
    }

    /*
     * 작성자 : 이형근
     * QaBoard Insert
     * param : QaListDTO
     * return :
     * 날짜 : 2022-05-14
     * */
    @Override
    public void insertQaBoard(QaListDTO qaListDTO) throws Exception {
        dao.insertQaBoard(qaListDTO);
    }

    /*
     * 작성자 : 이형근
     * num에 해당하는 qa조회
     * param : QaListDTO
     * return : QaListDTO
     * 날짜 : 2022-05-18
     * */
    @Override
    public QaListDTO selectQaOne(QaListDTO qaListDTO) throws Exception {
        return dao.selectQaOne(qaListDTO);
    }

    /*
     * 작성자 : 박혜림
     * 게시글 총 개수
     * param : QaListDTO
     * return : int
     * 날짜 : 2022-06-04
     * */
	@Override
	public int selectBoardTotalCount(QaListDTO qaListDTO) throws Exception {
		return dao.selectBoardTotalCount(qaListDTO);
	}
}
