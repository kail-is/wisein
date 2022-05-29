package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.PageDTO;
import com.wisein.wiselab.dto.QaListDTO;

import java.util.List;

public interface QaListDAO {

    /*
    * 작성자 : 이형근
    * QA 목록 조회
    * param : PageDTO
    * return : qaList
    * 날짜 : 2022-04-03
    * 수정자 : 박혜림
    * 수정일 : 2022-05-30
    * */
    public List<QaListDTO> selectQaList(PageDTO pd) throws Exception;

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
    
    /*
     * 작성자 : 박혜림
     * 게시글 총 개수
     * param : PageDTO
     * return : int
     * 날짜 : 2022-05-30
     * */
    public int listSearchCount(PageDTO pd) throws Exception;

}