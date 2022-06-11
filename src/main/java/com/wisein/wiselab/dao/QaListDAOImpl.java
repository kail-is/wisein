package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.QaListDTO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QaListDAOImpl implements QaListDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.qaListMapper";

    /*
     * 작성자 : 이형근
     * QA 목록 조회
     * param : QaListDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    @Override
    public List<QaListDTO> selectQaList(QaListDTO qaListDTO) {
        return sql.selectList(NS + ".selectQaList", qaListDTO);
    }

    /*
     * 작성자 : 이형근
     * QaBoard Insert
     * param : QaListDTO
     * return :
     * 날짜 : 2022-05-14
     * */
    @Override
    public void insertQaBoard(QaListDTO qaListDTO) throws Exception {
        sql.insert(NS + ".insertQaBoard", qaListDTO);
    }

    /*
     * 작성자 : 이형근
     * num에 해당하는 qa조회
     * param : QaListDTO
     * return : QaListDTO
     * 날짜 : 2022-05-18
     * */
    @Override
    public QaListDTO selectQaOne(QaListDTO qaListDTO) throws Exception {
        return sql.selectOne(NS + ".selectQaOne", qaListDTO);
    }

    /*
     * 작성자 : 박혜림
     * 게시글 총 개수
     * param : QaListDTO
     * return : int
     * 날짜 : 2022-06-04
     * */
	@Override
	public int selectBoardTotalCount(QaListDTO qaListDTO) throws Exception {
		return sql.selectOne(NS + ".selectBoardTotalCount", qaListDTO);
	}


}