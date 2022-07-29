package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeDAOImpl implements LikeDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.LikeMapper";

    /* like 여부 조회 */
    @Override
    public String checkLikeTipBoard(LikeBoardDTO dto) throws Exception {
        return sql.selectOne(NS + ".checkLikeTipBoard", dto);
    }

    /* like 등록 */
    @Override
    public void doLike(LikeBoardDTO dto) throws Exception {
        sql.insert(NS + ".doLike", dto);
    }

    /* like 해제 */
    @Override
    public void undoLike(LikeBoardDTO dto) throws Exception {
        sql.update(NS+ ".undoLike", dto);
    }

    /* like 등록시 게시글 likeCount 증가- */
    @Override
    public void addTipLikeCount(int num) throws Exception {
        sql.update(NS+ ".addTipLikeCount", num);
    }

    /* like 해제시 게시글 likeCount 감소- */
    @Override
    public void delTipLikeCount(int num) throws Exception {
        sql.update(NS+ ".delTipLikeCount", num);
    }
}