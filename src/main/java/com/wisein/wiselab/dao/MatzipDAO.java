package com.wisein.wiselab.dao;


import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.PageDataDTO;
import com.wisein.wiselab.dto.RecmDTO;

import java.util.List;

public interface MatzipDAO {

    public MatzipDTO selectMatzip(int matzipId) throws Exception;

    public RecmDTO selectRecm(int recmId) throws Exception;

    public List<RecmDTO> selectRecmList(int matzipId) throws Exception;

    public int selectRecmPostNum(RecmDTO dto) throws Exception;

    public void createMatzip(MatzipDTO matzipDTO) throws Exception;

    public void addRecm(RecmDTO recmDTO) throws Exception;

    public void delRecm(int recmId) throws Exception;

    public void updRecm(RecmDTO recmDTO) throws Exception;

    public int matzipCnt(int matzipId) throws Exception;

    public int recmCnt(int matzipId) throws Exception;

    public float avgRate(int matzipId) throws Exception;

    public void updClosedStat(int matzipId) throws Exception;

    public List<CompanyDTO> companyList();

    public List<CompanyDTO> matzipCount(String location);

//    public List<CompanyDTO> matzipList(String location);
    public List<CompanyDTO> matzipList(PageDataDTO pageDataDto);

    public List<CompanyDTO> recmMatzipCount(int id);

    public int matzipId(String loc);

    public int matzipExistCheck(int id);

    public List<CompanyDTO> companyCategory();

    public List<CompanyDTO> categoryDetail(String option);

    public int dataCount(String location);

    public int recmMatzipId(int recmId);
}