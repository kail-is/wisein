package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.dto.QaListDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QaListService {

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param : QaListDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    public List<QaListDTO> selectQaList(QaListDTO qaListDTO) throws Exception;

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
     * param : QaListDTO
     * return : int
     * 날짜 : 2022-06-04
     * */
    public int selectBoardTotalCount(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public LikeBoardDTO checkLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public int insertLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public int updateLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public List<LikeBoardDTO> selectLikeQaBoardList(MemberDTO member) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public LikeBoardDTO selectOneLikeQaBoard(LikeBoardDTO member) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    public void adoptQaBoard(QaListDTO dto) throws Exception;

    public void likeAddCount(LikeBoardDTO likeDTO) throws Exception;

    public void likeMinusCount(LikeBoardDTO likeDTO) throws Exception;

    public int selectAdpCount(QaListDTO qaListDTO) throws Exception;
}
