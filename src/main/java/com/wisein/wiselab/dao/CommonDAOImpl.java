package com.wisein.wiselab.dao;
import com.wisein.wiselab.dto.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDAOImpl implements CommonDAO {

    @Autowired
    private SqlSession sql;

    private static final String NS = "com.wisein.wiselab.mapper.commonMapper";

    @Override
    public void delUserImg(String fileNm) throws Exception {
        sql.update(NS + ".delImg", fileNm);
    }

    @Override
    public void uploadImgList(List<FileDTO> list) {
        sql.insert(NS + ".insertFileList", list);
    }

    @Override
    public List<FileDTO> selectFileList(String brdRef) throws Exception {
        return sql.selectList(NS + ".selectFileList", brdRef);
    }

    @Override
    public void updateHash(FileDTO dto) throws Exception {
        sql.update(NS + ".updateHash", dto);
    }


}