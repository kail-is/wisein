package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.LikeBoardDTO;

import java.util.List;

public interface LikeDAO {

    /* like 여부 조회 */
    public String checkLikeTipBoard(LikeBoardDTO dto) throws Exception;

    /* like 등록 */
    public void doLike(LikeBoardDTO dto) throws Exception;

    /* like 해제 */
    public void undoLike(LikeBoardDTO dto) throws Exception;

    /* like 등록시 게시글 likeCount 증가*/
    public void addTipLikeCount(int num) throws Exception;

    /* like 해제시 게시글 likeCount 감소- */
    public void delTipLikeCount(int num) throws Exception;

}