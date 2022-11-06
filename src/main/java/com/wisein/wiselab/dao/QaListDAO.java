package com.wisein.wiselab.dao;

import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.MemberDTO;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.dto.ScrapBoardDTO;

import java.util.List;

public interface QaListDAO {

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
     * QA 목록 조회
     * param : QaListDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    public List<QaListDTO> selectQuestionsList(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param : QaListDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    public List<QaListDTO> selectCommentList(QaListDTO qaListDTO) throws Exception;

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
     * param : QaListDTO
     * return : int
     * 날짜 : 2022-06-04
     * */
    public int selectBoardTotalCount(QaListDTO qaListDTO) throws Exception;

    /*
     * 작성자 : 이형근
     * num에 해당하는 qa삭제
     * param : Integer
     * return :
     * 날짜 : 2022-05-29
     * */
    public void deleteQaBoard(int num) throws Exception;

    /* 게시글 삭제시 댓글도 삭제 */
    public void deleteCommentQaBoard(int num) throws Exception;


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

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public LikeBoardDTO checkLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public ScrapBoardDTO checkScrapQaBoard(ScrapBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public int insertLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public int insertScrapQaBoard(ScrapBoardDTO qa) throws Exception;


    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public int updateLikeQaBoard(LikeBoardDTO qa) throws Exception;

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    public int updateScrapQaBoard(ScrapBoardDTO qa) throws Exception;

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
    public List<ScrapBoardDTO> selectScrapQaBoardList(MemberDTO member) throws Exception;

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
    public ScrapBoardDTO selectOneScrapQaBoard(ScrapBoardDTO member) throws Exception;

    /* 게시글 채택시 원본 채택여부 업데이트 */
    public void adoptQaBoard(QaListDTO dto) throws Exception;

    /* 게시글 채택시 채택된 댓글 채택여부 업데이트 */
    public void adoptCommentQaBoard(QaListDTO dto) throws Exception;

    public void likeAddCount(LikeBoardDTO likeDTO) throws Exception;

    public void likeMinusCount(LikeBoardDTO likeDTO) throws Exception;

    public int selectAdpCount(QaListDTO qaListDTO) throws Exception;

    /* like 등록 시 원본 게시글 count 증가 */
    public void addQaLikeCount(int parentNum) throws Exception;

    /* like 해제 시 원본 게시글 count 감소 */
    public void delQaLikeCount(int parentNum) throws Exception;

    /* scrap 등록 시 원본 게시글 count 증가 */
    public void addQaScrapCount(int parentNum) throws Exception;

    /* scrap 등록 시 원본 게시글 count 감소 */
    public void delQaScrapCount(int parentNum) throws Exception;

    /* 게시글 수정 후 detail 조회를 위한 원본게시글 num조회 */
    public int getParentNum(QaListDTO qaListDTO) throws Exception;

    /* 작성자 meetLink */
    public String selectMeetLink(int num) throws Exception;

    /* 작성글 모아보기 페이징조회 */
    public int selectMemberQaTotalCount(QaListDTO dto) throws  Exception;

    /* 댓글 모아보기 페이징조회 */
    public int selectMemberQaCommentTotalCount(QaListDTO dto) throws  Exception;

}