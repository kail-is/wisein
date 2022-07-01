package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.CommentDTO;

import java.util.List;

public interface CommentDAO {

    /* comment 다건조회 */
    public List<CommentDTO> selectComment(String brdRef) throws Exception;

    /* comment 등록 */
    public void insertComment(CommentDTO dto) throws Exception;

    /* comment 다건삭제 */
    public void deleteAllComment(String brdRef) throws Exception;

    /* comment 단건삭제 */
    public void deleteComment(CommentDTO dto) throws Exception;

    /* comment 수정 */
    public void updateComment(CommentDTO dto) throws Exception;

    /* 전체 comment 개수 조회 */
    public int selectCommentTotalCount(String brdRef) throws Exception;

}