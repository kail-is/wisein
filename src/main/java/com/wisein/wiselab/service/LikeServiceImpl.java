package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.LikeDAO;
import com.wisein.wiselab.dto.LikeBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDAO dao;


    /* like 여부 조회 */
    @Override
    public String TipLikeYN(LikeBoardDTO dto) throws Exception {
        return dao.TipLikeYN(dto);
    }

    /* like 여부 조회 */
    @Override
    public String QaLikeYN(LikeBoardDTO dto) throws Exception {
        return dao.QaLikeYN(dto);
    }

    /* like 등록 */
    @Override
    public void insertLike(LikeBoardDTO dto) throws Exception {
        dao.insertLike(dto);
    }

    /* like 재등록 */
    @Override
    public void doLike(LikeBoardDTO dto) throws Exception {
        dao.doLike(dto);
    }

    /* like 해제 */
    @Override
    public void undoLike(LikeBoardDTO dto) throws Exception {
        dao.undoLike(dto);
    }

    /* like 등록시 게시글 likeCount 증가 */
    @Override
    public void addTipLikeCount(int num) throws Exception {
        dao.addTipLikeCount(num);
    }

    /* like 해제시 게시글 likeCount 감소 */
    @Override
    public void delTipLikeCount(int num) throws Exception {
        dao.delTipLikeCount(num);
    }

    /* like 등록시 게시글 likeCount 증가 */
    @Override
    public void addQaLikeCount(int num) throws Exception {
        dao.addQaLikeCount(num);
    }

    /* like 해제시 게시글 likeCount 감소 */
    @Override
    public void delQaLikeCount(int num) throws Exception {
        dao.delQaLikeCount(num);
    }

    /* like 등록 시 원본 게시글 count 증가를 위한 parentNum 조회 */
    @Override
    public int getQaParentNum(int num) throws Exception {
        return dao.getQaParentNum(num);
    }

}
