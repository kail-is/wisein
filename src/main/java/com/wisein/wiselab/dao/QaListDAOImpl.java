package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.PageDTO;
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
     * param : PageDTO
     * return : qaList
     * 날짜 : 2022-04-03
     * 수정자 : 박혜림
     * 수정일 : 2022-05-30
     * */
    @Override
    public List<QaListDTO> selectQaList(PageDTO pd) {
        return sql.selectList(NS + ".selectQaList", pd);
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
<<<<<<< HEAD
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

    /*
     * 작성자 : 이형근
     * 해당 게시글 num select
     * param :
     * return :
     * 날짜 : 2022-06-09
     * */
    @Override
    public int selectQaNum2() throws Exception {
        return sql.selectOne(NS + ".selectQaNum2");
    }

    /*
     * 작성자 : 이형근
     * 해당 게시글 num select
     * param :
     * return :
     * 날짜 : 2022-06-09
     * */
    @Override
    public QaListDTO selectQaNum(QaListDTO qaListDTO) throws Exception {
        return sql.selectOne(NS + ".selectQaNum", qaListDTO);
    }

    /*
     * 작성자 : 이형근
     * QaBoard Comment Insert
     * param : QaListDTO
     * return :
     * 날짜 : 2022-06-12
     * */
    @Override
    public void insertCommentQaBoard(QaListDTO qaListDTO) throws Exception {
        sql.insert(NS + ".insertCommentQaBoard", qaListDTO);
    }

    /*
     * 작성자 : 이형근
     * QA 댓글 목록 조회
     * param : int num
     * return : qaList
     * 날짜 : 2022-06-12
     * */
    @Override
    public List<QaListDTO> selectCommentQaList(int num) throws Exception {
        return sql.selectList(NS + ".selectCommentQaList", num);
    }
    /*
     * 작성자 : 박혜림
     * 게시글 총 개수
     * param : PageDTO
     * return : int
     * 날짜 : 2022-05-30
     * */
	@Override
	public int listSearchCount(PageDTO pd) throws Exception {
		return sql.selectOne(NS + ".listSearchCount", pd);
	}


}