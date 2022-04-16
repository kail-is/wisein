package com.wisein.wiselab.dao;

import com.wisein.wiselab.dto.MemberDTO;

public interface MemberDAO {

    public void register(MemberDTO dto) throws Exception;

    public MemberDTO login(MemberDTO dto) throws Exception;

    public String findTempKey(String id) throws Exception;

    public void authStateUpdate(String id) throws Exception;
}