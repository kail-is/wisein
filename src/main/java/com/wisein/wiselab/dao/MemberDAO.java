package com.wisein.wiselab.dao;

import com.wisein.wiselab.dto.MemberDTO;

public interface MemberDAO {

    public String getTest();
    public void register(MemberDTO dto) throws Exception;
}