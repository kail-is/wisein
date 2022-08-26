package com.wisein.wiselab.service;

import com.wisein.wiselab.dto.FileDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface CommonService {

    public List<FileDTO> selectImgList(String brd, String id) throws Exception;

    public String uploadImgList(String brd, String regId, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    public void delUserImg(String fileNm) throws Exception;

    public void updateHash(FileDTO dto) throws Exception;

}
