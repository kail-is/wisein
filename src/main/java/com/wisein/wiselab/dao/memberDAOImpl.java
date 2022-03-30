package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.memberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class memberDAOImpl implements memberDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.memberMapper";

    @Override
    public String getTest() {
        return sql.selectOne(NS + ".getTest");
    }

    @Override
    public void register(memberDTO dto) {

    }

}