package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.QaListDTO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipBoardDAOImpl implements TipBoardDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.tipBoardMapper";

    /* TipBoard 다건조회 */
    @Override
    public List<TipBoardDTO> selectTipList(TipBoardDTO dto) throws Exception {
        return sql.selectList(NS + ".selectTipList");
    }

    /* TipBoard 단건조회 */
    @Override
    public TipBoardDTO selectTipOne(TipBoardDTO dto) throws Exception {
        return sql.selectOne(NS + ".selectTipOne", dto);
    }

    /* TipBoard 게시글 등록 */
    @Override
    public void insertTipBoard(TipBoardDTO dto) throws Exception {
        sql.insert(NS + ".insertTipBoard", dto);
    }

    /* TipBoard 게시글 삭제 */
    @Override
    public void deleteTipBoard(int num) throws Exception {
        sql.update(NS+ ".deleteTipBoard", num);
    }

    /* TipBoard 게시글 수정 */
    @Override
    public void updateTipBoard(TipBoardDTO dto) throws Exception {
        sql.update(NS+ ".updateTipBoard", dto);
    }

    /* TipBoard 현재 게시글 번호 조회*/
    @Override
    public TipBoardDTO selectTipNum(TipBoardDTO dto) throws Exception{
        return sql.selectOne(NS + ".selectTipNum");
    }

    /* TipBoard 다음 게시글 번호 조회*/
    @Override
    public int selectNextTipNum() throws Exception{
        return sql.selectOne(NS + ".selectNextTipNum");
    }

    /* 전체 게시글 개수 조회 */
    @Override
    public int selectBoardTotalCount(TipBoardDTO dto) throws Exception {
        return sql.selectOne(NS + ".selectBoardTotalCount", dto);
    }

    /* TipBoard 댓글 조회*/
    @Override
    public List<TipBoardDTO> selectTipComment(int num) throws Exception {
        return sql.selectList(NS + ".selectTipComment", num);
    }

    /* TipBoard 댓글 등록*/
    @Override
    public void insertTipComment(TipBoardDTO dto) throws Exception {
        sql.insert(NS + ".insertTipComment", dto);
    }

}