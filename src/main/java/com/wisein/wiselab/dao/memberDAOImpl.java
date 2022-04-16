package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class memberDAOImpl implements MemberDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.memberMapper";

    @Override
    public void register(MemberDTO dto) {
        sql.insert(NS + ".register", dto);
    }

    @Override
    public MemberDTO login(MemberDTO dto) throws Exception {
        return sql.selectOne(NS + ".login", dto);
    }

    @Override
    public String findTempKey(String id) throws Exception {
        return sql.selectOne(NS + ".key", id);
    }

    @Override
    public void authStateUpdate(String id) throws Exception {
        sql.update(NS + ".authStateUpdate", id);
    }

}