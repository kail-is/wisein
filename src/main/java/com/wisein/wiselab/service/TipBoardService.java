package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface TipBoardService {

    /* TipBoard 다건조회 */
    public List<TipBoardDTO> selectTipList(TipBoardDTO dto)  throws Exception;

    /* TipBoard 단건조회 */
    public TipBoardDTO selectTipOne(TipBoardDTO dto) throws Exception;

    /* TipBoard 게시글 등록 */
    public void insertTipBoard(TipBoardDTO dto) throws Exception;

    /* TipBoard 게시글 삭제 */
    public void deleteTipBoard(int num) throws Exception;

    /* TipBoard 게시글 수정 */
    public void updateTipBoard(TipBoardDTO dto) throws Exception;

    /* TipBoard 이미지 url */
    public String imgUrlReg(MultipartHttpServletRequest multipartHttpServletRequest,TipBoardDTO dto, HttpSession session) throws Exception;

    /* TipBoard 현재 게시글 번호 조회*/
    public TipBoardDTO selectTipNum(TipBoardDTO dto) throws Exception;

    /* 게시글 총 개수*/
    public int selectBoardTotalCount(TipBoardDTO dto) throws Exception;

    /* TipBoard 댓글 조회*/
    public List<TipBoardDTO> selectTipComment(int num) throws Exception;

    /* TipBoard 댓글 등록*/
    public void insertTipComment(TipBoardDTO dto) throws Exception;


}
