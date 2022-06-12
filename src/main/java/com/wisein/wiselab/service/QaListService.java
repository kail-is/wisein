package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.PageDTO;
import com.wisein.wiselab.dto.QaListDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QaListService {

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
<<<<<<< HEAD
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
     * qaboard 이미지 url
     * param :
     * return :
     * 날짜 : 2022-06-09
     * */
    public String imgUrlReg(MultipartHttpServletRequest multipartHttpServletRequest, HttpSession session, Model model) throws Exception;

    /*
     * 작성자 : 이형근
     * qaboard num select
     * param : qaListDTO
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

    /*
     * 작성자 : 박혜림
     * 게시글 총 개수
     * param : PageDTO
     * return : int
     * 날짜 : 2022-05-30
     * */
    public int listSearchCount(PageDTO pd) throws Exception;

}
