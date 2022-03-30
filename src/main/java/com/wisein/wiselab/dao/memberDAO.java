package com.wisein.wiselab.dao;

import com.wisein.wiselab.dto.memberDTO;

public interface memberDAO {

    public String getTest();
    public void register(memberDTO dto) throws Exception;
}