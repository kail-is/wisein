package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.TipBoardDAO;
import com.wisein.wiselab.dto.TipBoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipBoardServiceImpl implements TipBoardService {

    @Autowired
    private TipBoardDAO dao;

    /* TipBoard 다건조회 */
    @Override
    public List<TipBoardDTO> selectTipList() throws Exception {
        return dao.selectTipList();
    }

    /* TipBoard 단건조회 */
    @Override
    public TipBoardDTO selectTipOne(TipBoardDTO dto) throws Exception {
        return dao.selectTipOne(dto);
    }

    /* TipBoard 게시글 등록 */
    @Override
    public void insertTipBoard(TipBoardDTO dto) throws Exception {
        dao.insertTipBoard(dto);
    }

    /* TipBoard 게시글 삭제 */
    @Override
    public void deleteTipBoard(int num) throws Exception {
        dao.deleteTipBoard(num);
    }

    /* TipBoard 게시글 수정 */
    @Override
    public void updateTipBoard(TipBoardDTO dto) throws Exception {
        dao.updateTipBoard(dto);
    }
}
