package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.CommentDAO;
import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private CommentDAO dao;


    /* like 여부 조회 */
    @Override
    public LikeBoardDTO checkLikeTipBoard(LikeBoardDTO dto) throws Exception {

        return dto;
    }

    /* like 등록 */
    @Override
    public void doLike(LikeBoardDTO dto) throws Exception {
    }

    /* like 해제 */
    @Override
    public void undoLike(LikeBoardDTO dto) throws Exception {
    }

    /* like 등록시 게시글 likeCount 증가 */
    @Override
    public void addTipLikeCount(TipBoardDTO dto) throws Exception {
    }

    /* like 등록시 게시글 likeCount 증가 */
    @Override
    public void delTipLikeCount(TipBoardDTO dto) throws Exception {
    }

    /* 전체 Like 갯수 조회 */
    @Override
    public int selectTipLikeTotalCount(String brdRef) throws Exception {
        return 0;
//        return dao.selectTipLikeTotalCount(brdRef);
    }

}
