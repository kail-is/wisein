package com.wisein.wiselab;

import com.wisein.wiselab.dao.memberDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class memberDAOTest {

    @Autowired
    private memberDAO dao;

    @Test
    public void getTest() {
        System.out.println(dao.getTest());
    }

}
