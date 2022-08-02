package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.LikeBoardDTO;


public interface LikeService {

    /* like 여부 조회 */
    public String TipLikeYN(LikeBoardDTO dto) throws Exception;

    /* like 등록 */
    public void insertLike(LikeBoardDTO dto) throws Exception;

    /* like 상태변경 */
    public void updateLike(LikeBoardDTO dto) throws Exception;

    /* like 등록시 게시글 likeCount 증가 */
    public void addTipLikeCount(int num) throws Exception;

    /* like 해제시 게시글 likeCount 감소 */
    public void delTipLikeCount(int num) throws Exception;

}
