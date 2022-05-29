package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.QaListDAO;
import com.wisein.wiselab.dto.PageDTO;
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
     * param : PageDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * 수정자 : 박혜림
     * 수정일 : 2022-05-30
     * */
    @Override
    public List<QaListDTO> selectQaList(PageDTO pd) throws Exception {
        return dao.selectQaList(pd);
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
     * param : PageDTO
     * return : int
     * 날짜 : 2022-05-30
     * */
	@Override
	public int listSearchCount(PageDTO pd) throws Exception {
		return dao.listSearchCount(pd);
	}
}
