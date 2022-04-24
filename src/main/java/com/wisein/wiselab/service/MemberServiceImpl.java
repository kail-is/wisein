package com.wisein.wiselab.service;

import com.wisein.wiselab.dao.MemberDAO;
import com.wisein.wiselab.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDAO dao;

    @Override
    public void register(MemberDTO dto) throws Exception {
        dao.register(dto);
    }

    @Override
    public MemberDTO login(MemberDTO dto) throws Exception {
        return dao.login(dto);
    }

    @Override
    public String findTempKey(String id) throws Exception {
        return dao.findTempKey(id);
    }

    @Override
    public void logout(HttpSession session) throws Exception {
        session.invalidate();
    }

    @Override
    public void authStateUpdate(String id) throws Exception {
        dao.authStateUpdate(id);
    }

    @Override
    public int authIdExist(String id) throws Exception {
        return dao.authIdExist(id);
    }

    @Override
    public void modify(MemberDTO dto) throws Exception {
        dao.modify(dto);
    }

    @Override
    public void withdraw(MemberDTO dto, HttpSession session) throws Exception {
        dao.withdraw(dto);
        session.invalidate();
    }


}