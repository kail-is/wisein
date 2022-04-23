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
    public int idDupChk(String userId) throws Exception {
        return dao.idDupChk(userId);
    }

    @Override
    public MemberDTO login(MemberDTO dto) throws Exception {
       return dao.login(dto);
    }

    @Override
    public void logout(HttpSession session) throws Exception {
        session.invalidate();
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
