package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.ScrapBoardDTO;


public interface ScrapService {

    /* Scrap 여부 조회 */
    public String TipScrapYN(ScrapBoardDTO dto) throws Exception;

    /* Scrap 여부 조회 */
    public String QaScrapYN(ScrapBoardDTO dto) throws Exception;

    /* Scrap 등록 */
    public void insertScrap(ScrapBoardDTO dto) throws Exception;

    /* Scrap 재등록 */
    public void doScrap(ScrapBoardDTO dto) throws Exception;

    /* Scrap 해제 */
    public void undoScrap(ScrapBoardDTO dto) throws Exception;

    /* Scrap 등록시 게시글 ScrapCount 증가 */
    public void addTipScrapCount(int num) throws Exception;

    /* Scrap 해제시 게시글 ScrapCount 감소 */
    public void delTipScrapCount(int num) throws Exception;

    /* Scrap 등록시 게시글 ScrapCount 증가 */
    public void addQaScrapCount(int num) throws Exception;

    /* Scrap 해제시 게시글 ScrapCount 감소 */
    public void delQaScrapCount(int num) throws Exception;

    /* Scrap 등록 시 원본 게시글 count 증가를 위한 parentNum 조회 */
    public int getScrapParentNum(int num) throws Exception;
}
