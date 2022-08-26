package com.wisein.wiselab.dao;

import com.wisein.wiselab.dto.FileDTO;

import java.util.List;

public interface CommonDAO {

    public void delUserImg(String fileNm) throws Exception;

    public void uploadImgList(List<FileDTO> list) throws Exception;

    public List<FileDTO> selectFileList(String brdRef) throws Exception;

    public void updateHash(FileDTO dto) throws Exception;
}