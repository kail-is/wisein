package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.MemberDTO;

import javax.servlet.http.HttpSession;

public interface MemberService {

    public void register(MemberDTO dto) throws Exception;

    public MemberDTO login(MemberDTO dto) throws Exception;

    public void logout(HttpSession session) throws Exception;

    public void modify(MemberDTO dto) throws Exception;

    public void withdraw(MemberDTO dto, HttpSession session) throws Exception;
}
