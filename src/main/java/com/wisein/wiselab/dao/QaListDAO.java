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
     * 작성자 : 이형근
     * num에 해당하는 qa삭제
     * param : Integer
     * return :
     * 날짜 : 2022-05-29
     * */
    public void deleteQaBoard(int num) throws Exception;

    /*
     * 작성자 : 이형근
     * num에 해당하는 게시글 subject, content 수정
     * param : QaListDTO
     * return :
     * 날짜 : 2022-05-29
     * */
    public void updateQaBoard(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * 해당 게시글 num select
     * param :
     * return :
     * 날짜 : 2022-06-09
     * */
    public int selectQaNum2() throws Exception;

    /*
     * 작성자 : 이형근
     * 해당 게시글 num select
     * param :
     * return :
     * 날짜 : 2022-06-09
     * */
    public QaListDTO selectQaNum(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * QaBoard Comment Insert
     * param : QaListDTO
     * return :
     * 날짜 : 2022-06-12
     * */
    public void insertCommentQaBoard(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * QA 댓글 목록 조회
     * param : int num
     * return : qaList
     * 날짜 : 2022-06-12
     * */
    public List<QaListDTO> selectCommentQaList(int num) throws Exception;

}