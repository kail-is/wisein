package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CommentService {

    /* comment 다건조회 */
    public List<CommentDTO> selectComment(CommentDTO dto) throws Exception;

    /* comment 등록 */
    public void insertComment(CommentDTO dto) throws Exception;

    /* comment 다건삭제 */
    public void deleteAllComment(CommentDTO dto) throws Exception;

    /* comment 단건삭제 */
    public void deleteComment(CommentDTO dto) throws Exception;

    /* comment 수정 */
    public void updateComment(CommentDTO dto) throws Exception;

    /* 전체 comment 개수 조회 */
    public int selectCommentTotalCount(CommentDTO dto) throws Exception;

}
