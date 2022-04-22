package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.MemberDTO;

import javax.servlet.http.HttpSession;

public interface MemberService {

    public void register(MemberDTO dto) throws Exception;

    public MemberDTO login(MemberDTO dto) throws Exception;

    public String findTempKey(String id) throws Exception;

    public void logout(HttpSession session) throws Exception;

    public void authStateUpdate(String id) throws Exception;

    public int authIdExist(String id) throws Exception;
}

