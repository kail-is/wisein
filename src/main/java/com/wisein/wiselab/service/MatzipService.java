package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.MatzipDTO;
import com.wisein.wiselab.dto.RecmDTO;

import java.util.List;


public interface MatzipService {

    public void insertMzBoard(MatzipDTO matzipDTO, RecmDTO recmDTO) throws Exception;

    public MatzipDTO selectMatzip(int matzipId) throws Exception;

    public List<RecmDTO> selectMzRecm(int matzipId) throws Exception;

}
