package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.MatzipDAO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatzipServiceImpl implements MatzipService{

    @Autowired
    private MatzipDAO dao;

    @Override
    public void insertMzBoard(MatzipDTO matzipDTO, RecmDTO recmDTO) throws Exception {

        int idChk = dao.recmCnt(matzipDTO.getId());

        if(idChk == 0) {
            dao.createMatzip(matzipDTO);
            dao.addRecm(recmDTO);
        }else {
            dao.addRecm(recmDTO);
        }

    }

    @Override
    public MatzipDTO selectMatzip(int matzipId) throws Exception {

        MatzipDTO dto = dao.selectMatzip(matzipId);
        dto.setRate(dao.avgRate(matzipId));
        dto.setCount(dao.recmCnt(matzipId));
        // dto를 null이 아닌 값으로 넘겨주기
        return dto;
    }

    @Override
    public List<RecmDTO> selectMzRecm(int matzipId) throws Exception {
        return dao.selectRecmList(matzipId);
    }
}
