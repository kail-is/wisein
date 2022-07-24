package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.FoodListDAO;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.FoodListDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j

public class FoodListServiceImpl implements FoodListService {

    @Autowired
    private FoodListDAO dao;

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
    public List<String> matzipData() {
        return dao.matzipData();
    }

}
