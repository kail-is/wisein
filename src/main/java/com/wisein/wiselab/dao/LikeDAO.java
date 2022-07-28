package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.TipBoardDTO;

import java.util.List;

public interface LikeDAO {

    /* like 여부 조회 */
    public LikeBoardDTO checkLikeTipBoard(LikeBoardDTO dto) throws Exception;

    /* like 등록 */
    public void doLike(LikeBoardDTO dto) throws Exception;

    /* like 해제 */
    public void undoLike(LikeBoardDTO dto) throws Exception;

    /* like 등록시 게시글 likeCount 증가 */
    public void addTipLikeCount(TipBoardDTO dto) throws Exception;

    /* like 등록시 게시글 likeCount 증가 */
    public void delTipLikeCount(TipBoardDTO dto) throws Exception;

    /* 전체 Like 갯수 조회 */
    public int selectTipLikeTotalCount(String brdRef) throws Exception;

}