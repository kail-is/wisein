package com.wisein.wiselab.dao;
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

    /* TipBoard 다건조회*/
    @Override
    public List<TipBoardDTO> selectTipList() throws Exception {
        return sql.selectList(NS + ".selectTipList");
    }

    /* TipBoard 단건조회*/
    @Override
    public TipBoardDTO selectTipOne(TipBoardDTO TipBoardDTO) throws Exception {
        return sql.selectOne(NS + ".selectTipOne", TipBoardDTO);
    }

    /* TipBoard 게시글 등록*/
    @Override
    public void insertTipBoard(TipBoardDTO TipBoardDTO) throws Exception {
        sql.insert(NS + ".insertTipBoard", TipBoardDTO);
    }

}