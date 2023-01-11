package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.ScrapDAO;
import com.wisein.wiselab.dto.ScrapBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ScrapServiceImpl implements ScrapService {

    @Autowired
    private ScrapDAO dao;


    /* Scrap 여부 조회 */
    @Override
    public String TipScrapYN(ScrapBoardDTO dto) throws Exception {
        return dao.TipScrapYN(dto);
    }

    /* Scrap 여부 조회 */
    @Override
    public String QaScrapYN(ScrapBoardDTO dto) throws Exception {
        return dao.QaScrapYN(dto);
    }

    /* Scrap 등록 */
    @Override
    public void insertScrap(ScrapBoardDTO dto) throws Exception {
        dao.insertScrap(dto);
    }

    /* Scrap 재등록 */
    @Override
    public void doScrap(ScrapBoardDTO dto) throws Exception {
        dao.doScrap(dto);
    }

    /* Scrap 해제 */
    @Override
    public void undoScrap(ScrapBoardDTO dto) throws Exception {
        dao.undoScrap(dto);
    }

    /* Scrap 등록시 게시글 ScrapCount 증가 */
    @Override
    public void addTipScrapCount(int num) throws Exception {
        dao.addTipScrapCount(num);
    }

    /* Scrap 해제시 게시글 ScrapCount 감소 */
    @Override
    public void delTipScrapCount(int num) throws Exception {
        dao.delTipScrapCount(num);
    }

    /* Scrap 등록시 게시글 ScrapCount 증가 */
    @Override
    public void addQaScrapCount(int num) throws Exception {
        dao.addQaScrapCount(num);
    }

    /* Scrap 해제시 게시글 ScrapCount 감소 */
    @Override
    public void delQaScrapCount(int num) throws Exception {
        dao.delQaScrapCount(num);
    }

    /* Scrap 등록 시 원본 게시글 count 증가를 위한 parentNum 조회 */
    @Override
    public int getScrapParentNum(int num) throws Exception {
        return dao.getScrapParentNum(num);
    }

}
