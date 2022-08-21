package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.CommonDAO;
import com.wisein.wiselab.dao.MatzipDAO;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatzipServiceImpl implements MatzipService{

    @Autowired
    private MatzipDAO dao;

    @Autowired
    private CommonDAO comDAO;

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

        List<RecmDTO> list = dao.selectRecmList(matzipId);

        for (int i = 0 ; i < list.size(); i++ ) {
            RecmDTO oneRecm = list.get(i);
            String brdRef = "recm||" + oneRecm.getNum();
            oneRecm.setRecmImgList(comDAO.selectFileList(brdRef));
        }

        return list;
    }

    @Override
    public RecmDTO selectRecm(int recmId) throws Exception {
        RecmDTO post = dao.selectRecm(recmId);
        String brdRef = "recm||" + recmId;
        post.setRecmImgList(comDAO.selectFileList(brdRef));
        return post;
    }

    @Override
    public int delRecm(int num) throws Exception {

       dao.delRecm(num);
       int cnt = dao.recmCnt(num);

       return 0;
    }

    @Override
    public int updRecm(RecmDTO dto) throws Exception {
        dao.updRecm(dto);
        return 0;
    }

    @Override
    public int recmCnt(int matzipId) throws Exception {
        return dao.recmCnt(matzipId);
    }

    @Override
    public void updClosedStat(int matzipId) throws Exception {
        dao.updClosedStat(matzipId);
    }

    @Override
    public List<CompanyDTO> companyList() {
        return dao.companyList();
    }

    @Override
    public List<CompanyDTO> matzipCount(String location) {
        return dao.matzipCount(location);
    }

    @Override
    public List<CompanyDTO> matzipList(String location) {
        return dao.matzipList(location);
    }

    @Override
    public List<CompanyDTO> recmMatzipCount(int id) {
        return dao.recmMatzipCount(id);
    }

    @Override
    public List<CompanyDTO> company() {
        return dao.company();
    }

    @Override
    public List<CompanyDTO> selectCompany(String location) {
        return dao.selectCompany(location);
    }

    @Override
    public int matzipId(String loc) {
        return dao.matzipId(loc);
    }

}
