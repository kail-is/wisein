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

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO dao;

    /* comment 다건조회 */
    @Override
    public List<CommentDTO> selectComment(String brdRef) throws Exception {
        List<CommentDTO> Comment = new ArrayList<>();

        Comment = (List<CommentDTO>) dao.selectComment(brdRef);

        return Comment;
    }

    /* comment 등록 */
    @Override
    public void insertComment(CommentDTO dto) throws Exception {
        dao.insertComment(dto);
    }

    /* comment 삭제 */
    @Override
    public void deleteComment(int num) throws Exception {
        dao.deleteComment(num);
    }

    /* comment 수정 */
    @Override
    public void updateComment(CommentDTO dto) throws Exception {
        dao.updateComment(dto);
    }

    /* 전체 comment 개수 조회 */
    @Override
    public int selectCommentTotalCount(String brdRef) throws Exception {
        return dao.selectCommentTotalCount(brdRef);
    }
}
