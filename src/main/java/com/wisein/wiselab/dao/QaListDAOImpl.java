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
     * param :
     * return : qaList
     * 날짜 : 2022-04-03
     * */
    @Override
    public List<QaListDTO> selectQaList() {
        return sql.selectList(NS + ".selectQaList");
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
     * 작성자 : 이형근
     * num에 해당하는 qa삭제
     * param : Integer
     * return :
     * 날짜 : 2022-05-29
     * */
    @Override
    public void deleteQaBoard(int num) throws Exception {
        sql.delete(NS + ".deleteQaBoard", num);
    }

    /*
     * 작성자 : 이형근
     * num에 해당하는 게시글 subject, content 수정
     * param : QaListDTO
     * return :
     * 날짜 : 2022-05-29
     * */
    @Override
    public void updateQaBoard(QaListDTO qaListDTO) throws Exception {
        sql.update(NS + ".updateQaBoard", qaListDTO);
    }


}