package com.wisein.wiselab.service;

import com.wisein.wiselab.common.FileUtils;
import com.wisein.wiselab.dao.CommentDAO;
import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dao.TipBoardDAO;
import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO dao;

    /* comment 다건조회 */
    @Override
    public List<CommentDTO> selectComment(CommentDTO dto) throws Exception {
        List<CommentDTO> comment = new ArrayList<>();

        comment = (List<CommentDTO>) dao.selectComment(dto);

        return comment;
    }

    /* comment 등록 */
    @Override
    public void insertComment(CommentDTO dto) throws Exception {
        dao.insertComment(dto);
    }

    /* comment 다건삭제 */
    @Override
    public void deleteAllComment(CommentDTO dto) throws Exception {
        dao.deleteAllComment(dto);
    }

    /* comment 단건삭제 */
    @Override
    public void deleteComment(CommentDTO dto) throws Exception {
        dao.deleteComment(dto);
    }

    /* comment 수정 */
    @Override
    public void updateComment(CommentDTO dto) throws Exception {
        dao.updateComment(dto);
    }

    /* 전체 comment 개수 조회 */
    @Override
    public int selectCommentTotalCount(CommentDTO dto) throws Exception {
        return dao.selectCommentTotalCount(dto);
    }
}
