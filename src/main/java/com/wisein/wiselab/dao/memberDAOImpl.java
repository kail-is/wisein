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

}