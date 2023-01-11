package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.ScrapBoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScrapDAOImpl implements ScrapDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.scrapMapper";

    /* Scrap 여부 조회 */
    @Override
    public String TipScrapYN(ScrapBoardDTO dto) throws Exception {
        return sql.selectOne(NS + ".TipScrapYN", dto);
    }

    /* Scrap 여부 조회 */
    @Override
    public String QaScrapYN(ScrapBoardDTO dto) throws Exception {
        return sql.selectOne(NS + ".QaScrapYN", dto);
    }

    /* Scrap 등록 */
    @Override
    public void insertScrap(ScrapBoardDTO dto) throws Exception {
        sql.insert(NS + ".insertScrap", dto);
    }

    /* Scrap 재등록 */
    @Override
    public void doScrap(ScrapBoardDTO dto) throws Exception {
        sql.update(NS+ ".doScrap", dto);
    }

    /* Scrap 해제 */
    @Override
    public void undoScrap(ScrapBoardDTO dto) throws Exception {
        sql.update(NS+ ".undoScrap", dto);
    }

    /* Scrap 등록시 게시글 ScrapCount 증가- */
    @Override
    public void addTipScrapCount(int num) throws Exception {
        sql.update(NS+ ".addTipScrapCount", num);
    }

    /* Scrap 해제시 게시글 ScrapCount 감소- */
    @Override
    public void delTipScrapCount(int num) throws Exception {
        sql.update(NS+ ".delTipScrapCount", num);
    }

    /* Scrap 등록시 게시글 ScrapCount 증가- */
    @Override
    public void addQaScrapCount(int num) throws Exception {
        sql.update(NS+ ".addQaScrapCount", num);
    }

    /* Scrap 해제시 게시글 ScrapCount 감소- */
    @Override
    public void delQaScrapCount(int num) throws Exception {
        sql.update(NS+ ".delQaScrapCount", num);
    }

    @Override
    public int getScrapParentNum(int num) throws Exception {
        return sql.selectOne(NS + ".getScrapParentNum", num);
    }
}