package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.FileDTO;
import com.wisein.wiselab.dto.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public int idDupChk(String userId) throws Exception {
        return sql.selectOne(NS + ".idDupChk", userId);
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

    @Override
    public int authIdExist(String id) throws Exception {
        return sql.selectOne(NS + ".authId", id);
    }

    @Override
    public void modify(MemberDTO dto) throws Exception {
        sql.update(NS + ".modify", dto);
    }

    @Override
    public void delUserImg(String fileNm) throws Exception {
        sql.update(NS + ".delUserImg", fileNm);
    }

    @Override
    public void withdraw(MemberDTO dto) throws Exception {
        sql.update(NS + ".withdraw", dto);
    }

    @Override
    public void insertMemFileList(List<FileDTO> list) {
        sql.insert(NS + ".insertMemFileList", list);
    }

    @Override
    public List<FileDTO> selectMemFileList(String brdRef) throws Exception {
        return sql.selectList(NS + ".selectMemFileList", brdRef);
    }

}