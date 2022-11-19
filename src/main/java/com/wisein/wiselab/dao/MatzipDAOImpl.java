package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.PageDataDTO;
import com.wisein.wiselab.dto.RecmDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatzipDAOImpl implements MatzipDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.matzipMapper";

    @Override
    public MatzipDTO selectMatzip(int matzipId) throws Exception {

        MatzipDTO dto = sql.selectOne(NS + ".selectMatzip", matzipId);
        return dto == null ? new MatzipDTO() : dto;
    }

    @Override
    public RecmDTO selectRecm(int recmId) throws Exception {
        return sql.selectOne(NS + ".selectRecm", recmId);
    }

    @Override
    public List<RecmDTO> selectRecmList(int matzipId) throws Exception {
        return sql.selectList(NS + ".selectRecmList", matzipId);
    }

    @Override
    public int selectRecmPostNum(RecmDTO dto) throws Exception {
        return sql.selectOne(NS + ".selectRecmPostNum", dto);
    }

    @Override
    public void createMatzip(MatzipDTO matzipDTO) throws Exception {
        sql.insert(NS + ".insertMatzip", matzipDTO);
    }

    @Override
    public void addRecm(RecmDTO recmDTO) throws Exception {
        sql.insert(NS + ".insertRecm", recmDTO);
    }

    @Override
    public void delRecm(int num) throws Exception {
        sql.update(NS + ".delRecm", num);
    }

    @Override
    public void updRecm(RecmDTO recmDTO) throws Exception {
        sql.update(NS + ".updateRecm", recmDTO);
    }

    @Override
    public int matzipCnt(int matzipId) throws Exception {
        return sql.selectOne(NS + ".matzipCnt", matzipId);
    }

    @Override
    public int recmCnt(int matzipId) throws Exception {
        return sql.selectOne(NS + ".recmCnt", matzipId);
    }

    @Override
    public float avgRate(int matzipId) throws Exception {
        return sql.selectOne(NS + ".avgRate", matzipId);
    }

    @Override
    public void updClosedStat(int matzipId) throws Exception {
        sql.update(NS + ".updClosedStat", matzipId);
    }

    @Override
    public List<CompanyDTO> companyList() {
        return sql.selectList(NS + ".companyList");
    }

    @Override
    public List<CompanyDTO> matzipCount(String location) {
        return sql.selectList(NS+ ".matzipCount", location);
    }

    @Override
    public List<CompanyDTO> matzipList(PageDataDTO pageDataDto) {
        return sql.selectList(NS+ ".matzipList", pageDataDto);
    }

    @Override
    public List<CompanyDTO> recmMatzipCount(int id) {
        return sql.selectList(NS + ".recmMatzip", id);
    }

    @Override
    public int matzipId(String loc) {
        return sql.selectOne(NS + ".matzipId",loc);
    }

    @Override
    public int matzipExistCheck(int id) {
        return sql.selectOne(NS + ".matzipExistCheck",id);
    }

    @Override
    public List<CompanyDTO> companyCategory() {
        return sql.selectList(NS + ".companyCategory");
    }

    @Override
    public List<CompanyDTO> categoryDetail(String option) {
        return sql.selectList(NS + ".categoryDetail", option);
    }

    @Override
    public int dataCount(String location) {
        return sql.selectOne(NS + ".dataCount", location);
    }

    @Override
    public int recmMatzipId(int recmId) {
        return sql.selectOne(NS + ".recmMatzipId", recmId);
    }


}