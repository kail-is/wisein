package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.CommentDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.commentMapper";

    /* comment 다건조회 */
    @Override
    public List<CommentDTO> selectComment(String brdRef) throws Exception {
        return sql.selectList(NS + ".selectComment", brdRef);
    }

    /* comment 등록 */
    @Override
    public void insertComment(CommentDTO dto) throws Exception {
        sql.insert(NS + ".insertComment", dto);
    }

    /* comment 삭제 */
    @Override
    public void deleteComment(int num) throws Exception {
        sql.update(NS+ ".deleteComment", num);
    }

    /* comment 수정 */
    @Override
    public void updateComment(CommentDTO dto) throws Exception {
        sql.update(NS+ ".updateComment", dto);
    }

    /* 전체 comment 개수 조회 */
    @Override
    public int selectCommentTotalCount(String brdRef) throws Exception {
        return sql.selectOne(NS + ".selectCommentTotalCount", brdRef);
    }

}