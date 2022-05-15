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

    /*
     * 작성자 : 주한나
     * tip board 목록 조회
     * param :
     * return :
     * 날짜 : 2022-05-15
     * */
    @Override
    public List<TipBoardDTO> selectTipBoard() {
        return sql.selectList(NS + ".selectTipBoard");
    }

    /*
     * 작성자 : 주한나
     * TipBoar Insert
     * param :
     * return :
     * 날짜 : 2022-05-15
     * */
    @Override
    public void insertTipBoard(TipBoardDTO tipBoardDTO) throws Exception {
        sql.insert(NS + ".insertTipBoard", tipBoardDTO);
    }



}