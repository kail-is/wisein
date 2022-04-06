package com.wisein.wiselab;

import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class memberDAOTest {

    @Autowired
    private MemberDAO dao;

    @Autowired
    private MemberDTO dto;

    @Test
    public void register(MemberDTO dto) throws Exception {
       dao.register(dto);
    }

}
