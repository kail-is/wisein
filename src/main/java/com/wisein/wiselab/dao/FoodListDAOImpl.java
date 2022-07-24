package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.CompanyDTO;
import com.wisein.wiselab.dto.FoodListDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodListDAOImpl implements FoodListDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.foodListMapper";

    @Override
    public List<CompanyDTO> companyList() {
        return sql.selectList(NS + ".company");
    }

    @Override
    public List<CompanyDTO> matzipCount(String location) {
        return sql.selectList(NS+ ".matzipCount", location);
    }

    @Override
    public List<CompanyDTO> matzipList(String location) {
        return sql.selectList(NS + ".matzipList", location);
    }

    @Override
    public List<CompanyDTO> recmMatzipCount(int id) {
        return sql.selectList(NS + ".recmMatzip", id);
    }

    @Override
    public List<CompanyDTO> company() {
        return sql.selectList(NS + ".selectCompany");
    }

    @Override
    public List<CompanyDTO> selectCompany(String location) {
        return sql.selectList(NS + ".selectCompany2", location);
    }

    @Override
    public List<String> matzipData() {
        return sql.selectList(NS + ".matzipData");
    }


}