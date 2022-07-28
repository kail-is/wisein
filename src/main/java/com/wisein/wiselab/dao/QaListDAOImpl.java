package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.LikeBoardDTO;
import com.wisein.wiselab.dto.MemberDTO;
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
     * param : QaListDTO
     * return : int
     * 날짜 : 2022-06-04
     * */
	@Override
	public int selectBoardTotalCount(QaListDTO qaListDTO) throws Exception {
		return sql.selectOne(NS + ".selectBoardTotalCount", qaListDTO);
	}

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    @Override
    public LikeBoardDTO checkLikeQaBoard(LikeBoardDTO qa) throws Exception {
        return sql.selectOne(NS + ".checkLikeQaBoard", qa);
    }

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    @Override
    public int insertLikeQaBoard(LikeBoardDTO qa) throws Exception {
        return sql.insert(NS + ".insertLikeQaBoard", qa);
    }

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시
     * param : boardNum, num
     * return : int
     * 날짜 : 2022-07-24
     * */
    @Override
    public int updateLikeQaBoard(LikeBoardDTO qa) throws Exception {
        return sql.update(NS + ".updateLikeQaBoard", qa);
    }

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    @Override
    public List<LikeBoardDTO> selectLikeQaBoardList(MemberDTO member) throws Exception{
        return sql.selectList(NS + ".selectLikeQaBoardList", member);
    }

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    @Override
    public LikeBoardDTO selectOneLikeQaBoard(LikeBoardDTO member) throws Exception {
        return sql.selectOne(NS + ".selectOneLikeQaBoard", member);
    }

    /*
     * 작성자 : 이형근
     * 게시글 좋아요 클릭 시 신규/기본 여부 체크
     * param : boardNum, num
     * return :
     * 날짜 : 2022-07-24
     * */
    @Override
    public void adoptQaBoard(QaListDTO dto) throws Exception {
        sql.update(NS + ".adoptQaBoard", dto);
    }

    @Override
    public void likeAddCount(LikeBoardDTO likeDTO) throws Exception {
        System.out.println("likeDTO : " + likeDTO);
        sql.update(NS + ".likeAddCount", likeDTO);
    }
    @Override
    public void likeMinusCount(LikeBoardDTO likeDTO) throws Exception {
        System.out.println("likeDTO : " + likeDTO);
        sql.update(NS + ".likeMinusCount", likeDTO);
    }
    @Override
    public int selectAdpCount(QaListDTO qaListDTO) throws Exception{
        return sql.selectOne(NS + ".selectAdpCount", qaListDTO);
    }
}